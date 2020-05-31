package com.cdtu.web;

import com.cdtu.entity.*;
import com.cdtu.service.*;
import com.cdtu.util.HrmConstants;
import com.cdtu.util.StrDetalUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private VisvitedVideoService service;
    @Autowired
    private TestEntityService testEntityService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChioceTestService chioceTestService;
    @Autowired
    private JudgeTestService judgeTestService;
    @Autowired
    private VacantService vacantService;
    @Autowired
    private TestInfoService testInfoService;
    @Autowired
    private TimeService timeService;
    @Autowired
    private VistiedDocService vistiedDocService;
    private boolean flag=false;//用于判断学生是否可以考试的标志

    /**
     * 根据学生的专业查找所有资料库中的除视频外的文档类信息（具体操作在查找时进行）
     * @param majorid
     * @param model
     * @return
     */
    @RequestMapping("/document/list")
    public String documentList(Long majorid, Model model,Integer indexPage,HttpSession session){
        if(indexPage==null){
            indexPage=1;
        }
        if(majorid==null){
            majorid=((User)session.getAttribute("user")).getMajorid();
        }
        Page startPage= PageHelper.startPage(indexPage,5);
        System.out.println(indexPage+"------------"+startPage.getPages());
        List<Material> list=documentService.findAllDocumentEndwithDoc(majorid);
        System.out.println(indexPage);
        model.addAttribute("list",list);
        model.addAttribute("pages",startPage.getPages());
        return "/student/document/list";
    }

    /**
     * 搜集学生端文档列表界面上的查询信息，查询对应文档名的信息返回至文档列表界面（模糊查询的限制在数据库查询时进行）
     * @param majorId
     * @param value
     * @param mv
     * @return
     */
    @RequestMapping(value = "/document/list",method = RequestMethod.POST)
    public ModelAndView list(Long majorId,String value,ModelAndView mv,Integer indexPage){
        if(indexPage==null){
            indexPage=1;
        }
        Page startPage=PageHelper.startPage(indexPage,5);
        List<Material> list=documentService.findByFilenameStu(majorId,value);
        mv.addObject("list",list);
        mv.addObject("pages",startPage.getPages());
        return mv;
    }

    /**
     * 根据登陆人的专业查询所有的视频学习资料送至视频学习界面列表，并跳转页面（视频控制逻辑在前端）
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/study/video")
    public String studyVideo(Model model, HttpSession session){
        User user= (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<Material> list=documentService.findByMaAndEndWith(user.getMajorid(),".mp4");
        VistiedVideo vistiedVideo=service.findByUid(user.getId());
        if(vistiedVideo!=null){
            String[] videoNames=vistiedVideo.getVideo().split("//");
            for(int i=0;i<videoNames.length;i++){
                for(int j=0;j<list.size();j++){
                    if(videoNames[i].equals(list.get(j).getDataName())){
                        list.get(j).setDataName(list.get(j).getDataName()+"  已完成");
                    }
                }
            }
        }
        model.addAttribute("user",user);
        model.addAttribute("list",list);
        return "/student/document/studyOfVideo";
    }

    /**
     * 根据点击的视频学习界面的链接请求对应的视频播放（根据视频名请求查找）
     * @param response
     * @param name
     * @throws Exception
     */
    @RequestMapping("/study/video/{name:.+}")
    public void getVideo(HttpServletResponse response, @PathVariable("name") String name) throws Exception{
        Path bascPath= Paths.get("D:\\designer\\document");
        Path filePath=bascPath.resolve(name);
        if (Files.exists(filePath)) {
            response.getOutputStream().write(Files.readAllBytes(filePath));
        } else {
            response.setStatus(404);
        }

    }

    /**
     * 保存视频学习界面上的学习进度
     * @param uid
     * @param videoNames
     * @param response
     */
    @RequestMapping(value = "/study/save",method = RequestMethod.POST)
    public void studySave(Long uid,String videoNames,HttpServletResponse response){
        boolean sybol=false;
        VistiedVideo vVideo=service.findByUid(uid);
        if(vVideo==null){
            VistiedVideo vistiedVideo=new VistiedVideo(uid,videoNames);
            sybol=service.insert(vistiedVideo);
        }else {
            VistiedVideo vv=new VistiedVideo(vVideo.getId(),uid,vVideo.getVideo()+videoNames);
            sybol=service.updateInfo(vv);
        }
        if(sybol==true){
            try {
                response.getWriter().println("<script>alert('保存成功')</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().println("<script>alert('保存失败')</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据登录人查询视频学习和文档学习状况，以及管理员设置的时间范围，看登陆人是否具有考试资格
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/test/info")
    public String testInfo(Model model,HttpSession session){
        User user= (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<Material> list=documentService.findByMaAndEndWith(user.getMajorid(),".mp4");
        List<Material> listDoc=documentService.findAllDocumentEndwithDoc(user.getMajorid());
        VistiedVideo vistiedVideo=service.findByUid(user.getId());
        VistiedDoc vistiedDoc=vistiedDocService.findByUid(user.getId());
        if(vistiedVideo !=null && vistiedDoc !=null){
            String[] str=vistiedVideo.getVideo().split("//");
            List<String> videoList=Arrays.asList(str);
            String[] str1=vistiedDoc.getDoc().split("//");
            List<String> docList=Arrays.asList(str1);
            int count1=0,count2=0;
            for(int i=0;i<list.size();i++){
                if(videoList.contains(list.get(i).getDataName())){
                    count1++;
                }
            }
            for(int j=0;j<listDoc.size();j++){
                if(docList.contains(listDoc.get(j).getDataName())){
                    count2++;
                }
            }
            if(list.size()==count1 && listDoc.size()==count2){
                Time time=timeService.findTime();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String strTime=sdf.format(new Date());
                int sym= StrDetalUtils.dateComp(strTime,time.getStartTime(),time.getEndTime());
                if(sym==0){
                    flag=true;
                    model.addAttribute("msg",user.getName()+"同学，你可以考试咯！！");
                }else if(sym==-1){
                    flag=false;
                    model.addAttribute("msg",user.getName()+"同学，考试时间还没到请耐心等待！！");
                }else {
                    flag=false;
                    model.addAttribute("msg",user.getName()+"同学，考试时间已过，补考等候通知！！");
                }
            }else {
                flag=false;
                model.addAttribute("msg",user.getName()+"同学，你还没有学习完哦!!");
            }
        }else {
            flag=false;
            model.addAttribute("msg",user.getName()+"同学，你还没有学习完哦!!");
        }
        model.addAttribute("flag",flag);
        return "/student/noTest";
    }

    /**
     * 根据登陆人，看是否已考试，如果未考则可以根据链接跳转考试界面，否则提示已考（随机抽一套试卷）
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping("/test")
    public String testStart(Model model,Long uid){
        User user=userService.findById(uid);
        TestInfo testInfo=testInfoService.findByUid(uid);
        if(testInfo==null){
            List<TestEntity> list=testEntityService.findByMajorAndStart(user.getMajor().getMid(),1);
            Random random=new Random(list.size());
            int num=random.nextInt(list.size());
            TestEntity testEntity=list.get(num);//根据限定的随机数抽取某套试卷
           // TestEntity testEntity=testEntityService.findByMajorAndStart(user.getMajor().getMid(),1);
            String chioces="("+testEntity.getCidList()+")";//拼接要查询的选择题组
            String judges="("+testEntity.getJidList()+")";
            String vacants="("+testEntity.getVidList()+")";
            List<ChioceTest> chioceList=chioceTestService.findBtCids(chioces);//查询该套试卷中的所有选择题
            List<JudgeTest> judgeList=judgeTestService.findByJids(judges);
            List<VacantTest> vacantList=vacantService.findByVids(vacants);
            model.addAttribute("user",user);
            model.addAttribute("test",testEntity);
            model.addAttribute("chioceList",chioceList);
            model.addAttribute("judgeList",judgeList);
            model.addAttribute("vacantList",vacantList);
            return "/student/test2";
        }else {
            flag=false;
            model.addAttribute("msg",user.getName()+"同学，你已经考过试了哦！！");
            model.addAttribute("flag",flag);
            return "/student/noTest";
        }

    }

    /**
     * 搜集考试页面的考生信息，考生的各类题答案，并对比答案的正确度，最后将整个考卷信息存入数据库
     * @param model
     * @param tid
     * @param uid
     * @param majorid
     * @param chioceAnswers
     * @param judgeAnswers
     * @param vacantAnswers
     * @param createTime
     * @param session
     * @return
     */
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String getAnswer(Model model,Long tid,Long uid,Long majorid,String chioceAnswers,String judgeAnswers,String vacantAnswers,String createTime,HttpSession session){
        TestEntity testEntity=testEntityService.findByTid(tid);
        int score=0;
        System.out.println(chioceAnswers);
        if(chioceAnswers!=null && chioceAnswers!=""){
            String chioceIDS="("+testEntity.getCidList()+")";
            String[] cc=chioceAnswers.split(",");
            List<ChioceTest> chioceList=chioceTestService.findBtCids(chioceIDS);
            for(int i=0;i<cc.length;i++){
                if(cc[i]!=null && cc[i]!=""){
                    if(cc[i].equals(chioceList.get(i).getAnswer())){
                        score+=15;
                    }
                }
            }
        }
        if(judgeAnswers!=null && judgeAnswers!=""){
            String judgeIDS="("+testEntity.getJidList()+")";
            String[] jj=judgeAnswers.split(",");
            List<JudgeTest> judgeList=judgeTestService.findByJids(judgeIDS);
            for (int j=0;j<jj.length;j++){
                if(jj[j]!=null && jj[j]!=""){
                    if(Integer.parseInt(jj[j])==judgeList.get(j).getAnswer()){
                        score+=10;
                    }
                }

            }
        }
        if(vacantAnswers!=null && vacantAnswers!=""){
            String vacantIDS="("+testEntity.getVidList()+")";
            String[] vv=vacantAnswers.split(",");
            List<VacantTest> vacantList=vacantService.findByVids(vacantIDS);
            for (int k=0;k<vv.length;k++){
                if(vv[k]!=null && vv[k]!=""){
                    if(vv[k].equals(vacantList.get(k).getAnswer())){
                        score+=10;
                    }
                }
            }
        }

        TestInfo testInfo=new TestInfo(tid,uid,majorid,chioceAnswers,judgeAnswers,vacantAnswers,score,createTime,0);
        testInfoService.insertEntity(testInfo);
        User user= (User) session.getAttribute(HrmConstants.USER_SESSION);
        model.addAttribute("msg",user.getName()+"同学，你的得分为："+score);
        model.addAttribute("user",user);
        return "/student/testResoult";
    }

    /**
     * 根据登陆人查询该登陆人的考试信息，最后展示出来（连带正确答案）
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/test/information")
    public String testInformation(Model model,Long id){
        TestInfo testInfo=testInfoService.findByUid(id);
        if(testInfo!=null){
            String[] cAns=testInfo.getcAnswerList().split(",");
            String[] jAns=testInfo.getjAnswerList().split(",");
            String[] vAns=testInfo.getvAnswerList().split(",");
            TestEntity testEntity=testEntityService.findByTid(testInfo.getTid());
            String chioceIDS="("+testEntity.getCidList()+")";
            String judgeIDS="("+testEntity.getJidList()+")";
            String vacantIDS="("+testEntity.getVidList()+")";
            List<ChioceTest> chioceList=chioceTestService.findBtCids(chioceIDS);
            List<JudgeTest> judgeList=judgeTestService.findByJids(judgeIDS);
            List<VacantTest> vacantList=vacantService.findByVids(vacantIDS);
            //遍历交换该考生的做题答案和正确答案，方便展示试卷
            for (int i=0;i<(cAns.length<=chioceList.size()?cAns.length:chioceList.size());i++){
                String temp="";
                temp=chioceList.get(i).getAnswer();
                chioceList.get(i).setAnswer(cAns[i]);
                cAns[i]=temp;
            }
            //以流的形式，将带有正确答案的一组选择题和考生所给出的答案的一组选择题交换之后放入考试信息中，此处是将正确答案部分的选择题集合中的答案流化成一个字符串存入testinfo部分的考生选择题答案
            testInfo.setcAnswerList(Arrays.stream(cAns).collect(Collectors.joining(",")));
            for(int j=0;j<(jAns.length<=judgeList.size()?jAns.length:judgeList.size());j++){
                int temp=0;
                temp=judgeList.get(j).getAnswer();
                judgeList.get(j).setAnswer(Integer.parseInt(jAns[j]));
                jAns[j]=temp+"";
            }
            testInfo.setjAnswerList(Arrays.stream(jAns).collect(Collectors.joining(",")));
            for (int k=0;k<(vAns.length<=vacantList.size()?vAns.length:vacantList.size());k++){
                String temp="";
                temp=vacantList.get(k).getAnswer();
                vacantList.get(k).setAnswer(vAns[k]);
                vAns[k]=temp;
            }
            testInfo.setvAnswerList(Arrays.stream(vAns).collect(Collectors.joining(",")));
            String str="";
            String[] Jans=testInfo.getjAnswerList().split(",");
            for(int i=0;i<Jans.length;i++){
                if("0".equals(Jans[i])){
                    if(i==0){
                        str="×,";
                    }else if(i==Jans.length-1){
                        str+="×";
                    }else {
                        str+="×,";
                    }
                }else if("1".equals(Jans[i])){
                    if(i==0){
                        str="√,";
                    }else if(i==Jans.length-1){
                        str+="√";
                    }else {
                        str+="√,";
                    }
                }
            }
            testInfo.setjAnswerList(str);
            model.addAttribute("testInfo",testInfo);
            model.addAttribute("chioceList",chioceList);
            model.addAttribute("judgeList",judgeList);
            model.addAttribute("vacantList",vacantList);
            return "/student/testStuInfo";
        }else {
            flag=false;
            User user=userService.findById(id);
            model.addAttribute("msg",user.getName()+"同学，你还未考试哦！！");
            model.addAttribute("flag",flag);
            return "/student/noTest";
        }
    }

    /**
     * 根据登陆人专业查询所有非mp4的资料文档，将查询的信息传送至资料学习界面，并跳转
     * @param model
     * @param majorid
     * @param session
     * @return
     */
    @RequestMapping("/docment/studyDoc")
    public String docStudyList(Model model,Long majorid,HttpSession session){
        User user= (User) session.getAttribute(HrmConstants.USER_SESSION);
        //查询限制在数据库查询端
        List<Material> list=documentService.findAllDocumentEndwithDoc(majorid);
        //查询已经学习的文档列表
        VistiedDoc vistiedDoc=vistiedDocService.findByUid(user.getId());

        if(vistiedDoc!=null){
            String[] docs=vistiedDoc.getDoc().split("//");
            List<String> listDocs=new ArrayList<>();
            for(int i=0;i<docs.length;i++){
                listDocs.add(docs[i]);
            }
            model.addAttribute("listDocs",listDocs);
        }

        model.addAttribute("list",list);
        model.addAttribute("user",user);
        return "/student/document/stuList";
    }

    /**
     * 保存当前资料学习端的学习情况（逻辑在前端实现）
     * @param doc
     * @param uid
     * @param response
     */
    @RequestMapping(value = "/docment/studyDoc",method = RequestMethod.POST)
    public void studyDocStore(String doc,Long uid,HttpServletResponse response){
        VistiedDoc vistiedDoc=vistiedDocService.findByUid(uid);
        VistiedDoc vistiedDoc1=new VistiedDoc(uid,doc);
        boolean flag=false;
        if(vistiedDoc==null){
            flag=vistiedDocService.insertDoc(vistiedDoc1);
        }else {
            flag=vistiedDocService.updateInfo(vistiedDoc1,vistiedDoc.getDid());
        }
        if(flag==true){
            try {
                response.getWriter().println("<script>alert('保存成功')</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().println("<script>alert('保存失败')</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
