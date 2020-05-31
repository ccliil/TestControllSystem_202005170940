package com.cdtu.web;

import com.cdtu.entity.Major;
import com.cdtu.entity.Material;
import com.cdtu.entity.User;
import com.cdtu.service.DocumentService;
import com.cdtu.service.MajorService;
import com.cdtu.service.UserService;
import com.cdtu.util.Doc2HtmlUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

import static com.cdtu.util.Doc2HtmlUtil.getDoc2HtmlUtilInstance;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private UserService userService;
    @Autowired
    private MajorService majorService;

    /**
     * 将所查询的所有资源信息送至管理员的文档列表界面
     * 用户信息和专业信息送过去是为查找做准备
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String listEntity(Model model,Integer indexPage){
        if(indexPage==null) {
            indexPage=1;
        }
        Page startPage= PageHelper.startPage(indexPage,5);
       List<Material> list=documentService.findAll();
       List<Major> listMajor=majorService.findAll();
       List<User> listUser=userService.findAll();
       model.addAttribute("list",list);
       model.addAttribute("listUser",listUser);
       model.addAttribute("listMajor",listMajor);
       model.addAttribute("pages",startPage.getPages());
        return "/document/list";
    }

    /**
     * 搜集管理员文档列表中返回回来的查询信息，并根据查询信息查找数据送回列表界面
     * @param find
     * @param value
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public String list(String find,String  value,Model model){

        if("uid".equals(find)){
            List<Material> list=documentService.findByUid(Long.parseLong(value));
            model.addAttribute("list",list);
        }else if("majorid".equals(find)){
            List<Material> list=documentService.findByMajorId(Long.parseLong(value));
            model.addAttribute("list",list);
        }else {
            List<Material> list=documentService.findByFilename(value);
            model.addAttribute("list",list);
        }

        List<Major> listMajor=majorService.findAll();
        List<User> listUser=userService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/document/list";
    }

    /**
     * 根据路径请求上所附带的文档id值删除数据库中对应部分（包括本地文件和在线查看文档部分的pdf文档）
     * @param id
     * @param mv
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public ModelAndView delete (Long id,ModelAndView mv,HttpServletResponse response)throws Exception{
        Material material=documentService.findById(id);
        String location=material.getLocation();
        boolean flag=documentService.delete(id,location,material.getDataName());
        if(flag==true){
            response.getWriter().println("<script>alert('删除成功')</script>");
        }else {
            response.getWriter().println("<script>alert('删除失败')</script>");
        }
        mv.setViewName("redirect:/document/list");
        return mv;
    }

    /**
     * 根据请求路径上的文档id值查询数据库，并将结果送至文档修改界面并跳转页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public  String toUpdate(Long id,Model model){
        Material material=documentService.findById(id);
        List<Major> listMajor=majorService.findAll();
        List<User> listUser=userService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        model.addAttribute("document",material);
        return "/document/update";
    }

    /**
     * 搜集文档修改界面上文档id所对应的所有信息，并修改数据库对应部分
     * @param id
     * @param uId
     * @param majorId
     * @param title
     * @param remark
     * @param dataName
     * @param isLoad
     * @param mv
     * @param response
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ModelAndView updateDocument(Long id,Long uId,Long majorId,String title,String remark,String dataName,Integer isLoad,ModelAndView mv,HttpServletResponse response){
        Material material=new Material(id,uId,majorId,dataName,title,remark,isLoad);
        boolean flag=documentService.update(material);
        if(flag==true){
            try {
                response.getWriter().println("<script>alert('修改成功')</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().println("<script>alert('修改失败')</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mv.setViewName("redirect:/document/list");
        return mv;
    }

    /**
     * 将所有用户和专业信息送至文档添加界面，并跳转页面
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model){
        List<Major> listMajor=majorService.findAll();
        List<User> listUser=userService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/document/add";
    }

    /**
     * 将文档添加页面上的所有文档信息搜集起来，将文档存入本地文件夹，将其信息存入数据库
     * @param mv
     * @param file
     * @param title
     * @param remark
     * @param uId
     * @param majorId
     * @param createTime
     * @param isLoad
     * @param session
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ModelAndView addMaterial(ModelAndView mv, MultipartFile file,String title,String remark,Long uId,Long majorId,String createTime,Integer isLoad, HttpSession session,HttpServletResponse response)throws Exception{
        /**
         * 上传文件
         */
        Doc2HtmlUtil doc2HtmlUtil=Doc2HtmlUtil.getDoc2HtmlUtilInstance();
       String fileName=file.getName();
       if(".doc".equals(fileName.substring(fileName.lastIndexOf("."))) || ".docx".equals(fileName.substring(fileName.lastIndexOf(".")))|| ".ppt".equals(fileName.substring(fileName.lastIndexOf("."))) || ".pptx".equals(fileName.substring(fileName.lastIndexOf("."))) || ".xls".equals(fileName.substring(fileName.lastIndexOf(".")))){
           String path = session.getServletContext().getRealPath("/upload/");
           String filename = file.getOriginalFilename();
           path = "D://designer//document";
           File tempFile = new File(path+File.separator+filename);
           tempFile.createNewFile();
           file.transferTo(tempFile);
           String location=path+"//"+filename;
           Material material=new Material(uId,majorId,filename,location,createTime,title,remark,isLoad);
           boolean flag=documentService.insert(material);
           if(flag==true){
               response.getWriter().println("<script>alert('添加成功')</script>");
               if(".ppt".equals(fileName.substring(fileName.lastIndexOf("."))) || ".pptx".equals(fileName.substring(fileName.lastIndexOf(".")))){
                   File file1=new File(location);
                   doc2HtmlUtil.file2pdf(file1,"D:\\designer\\TestControllSystem\\src\\main\\resources\\doc",fileName.substring(fileName.lastIndexOf(".")+1));
               }
           }else {
               response.getWriter().println("<script>alert('添加失败')</script>");
           }
       }else {
          response.getWriter().println("<script>alert('该文件格式不允许添加')</script>");
       }
        mv.setViewName("redirect:/document/list");
        return mv;
    }

    /**
     * 根据请求路径上的文档id值，查找数据库中的信息，然后根据具体路径将文档复制到客户端的下载文件夹
     * @param id
     * @param response
     */
    @RequestMapping("/downLoad")
    public void downLoadMaterial(Long id,HttpServletResponse response){
        Material material=documentService.findById(id);
            File file=  new File(material.getLocation());
            String filename=file.getName();
            if(file.exists()){
                try{
                    String encodedFileName = URLEncoder.encode(filename, "utf-8");
                    response.setHeader("content-type", "application/xlsx;application/xls");
                    response.addHeader("content-disposition",
                            "attachment;filename=\"" + encodedFileName + "\";filename*=utf-8''" + encodedFileName);
                    InputStream ins = new FileInputStream(file);
                    /* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
                    response.setContentType("multipart/form-data");
                    /* 设置文件头：最后一个参数是设置下载文件名 */
                    response.setHeader("Content-Disposition", "attachment;filename="+encodedFileName);
                    OutputStream os = response.getOutputStream();
                    byte[] b = new byte[1024];
                    int len;
                    while((len = ins.read(b)) > 0){
                        os.write(b,0,len);
                    }
                    os.flush();
                    os.close();
                    ins.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }

    /**
     * 根据请求路径上的文档id值，在数据库中查询具体的信息，将文档转换成pdf文档然后将pdf文档放置/resource/doc处，然后在浏览器中打开
     * @param fid
     * @param response
     */
    @RequestMapping("/lookUp")
    public void lookUp(Long fid,HttpServletResponse response ) {
    Doc2HtmlUtil coc2HtmlUtil = getDoc2HtmlUtilInstance();
    Material material = documentService.findById(fid);
    File file = null;
    if (material != null) {
        //查询在/resource/doc处该文档是否转换过，转换过直接打开，否则转换后打开
        file = new File("D:\\designer\\TestControllSystem\\src\\main\\resources\\doc\\" + material.getDataName() + ".pdf");
        if (file.exists()) {
            try {
                response.setContentType("application/pdf");
                FileInputStream in = new FileInputStream(file);
                OutputStream out = response.getOutputStream();
                byte[] b = new byte[1024];
                while ((in.read(b)) != -1) {
                    out.write(b);
                }
                out.flush();
                in.close();
                out.close();
            } catch (IOException e) {
                e.getMessage();
            }
        } else {
            File file1 = new File(material.getLocation());
            String fileName = file1.getName();
            try {
                //转换文档->pdf
                String filename = coc2HtmlUtil.file2pdf(file1, "D:\\designer\\TestControllSystem\\src\\main\\resources\\doc", fileName.substring(fileName.lastIndexOf(".")+1));
                File file2 = new File("D:\\designer\\TestControllSystem\\src\\main\\resources\\doc\\" + filename);
                response.setContentType("application/pdf");
                FileInputStream in = new FileInputStream(file2);
                OutputStream out = response.getOutputStream();
                byte[] b = new byte[1024];
                while ((in.read(b)) != -1) {
                    out.write(b);
                }
                out.flush();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }else {
        try {
            response.getWriter().println("<script>alert('文件不存在')</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}


