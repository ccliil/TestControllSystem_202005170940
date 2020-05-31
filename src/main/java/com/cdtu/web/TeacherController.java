package com.cdtu.web;

import com.cdtu.entity.*;
import com.cdtu.service.*;
import com.cdtu.test.Vacant;
import com.cdtu.util.HrmConstants;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private UserService userService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ChioceTestService chioceTestService;
    @Autowired
    private JudgeTestService judgeTestService;
    @Autowired
    private VacantService vacantService;
    @Autowired
    private TestEntityService testEntityService;
    @Autowired
    private TestInfoService testInfoService;

    /**
     * 根据登陆老师的专业，查询所有的该专业文档
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/file/list")
    public String documentList(Model model, HttpSession session, Integer indexPage) {
        if (indexPage == null) {
            indexPage = 1;
        }
        Page startPage = PageHelper.startPage(indexPage, 5);
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<Material> list = documentService.findByMajorId(user.getMajorid());
        List<User> listUser = userService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("listUser", listUser);
        model.addAttribute("pages", startPage.getPages());
        return "/teacher/file/list";
    }

    /**
     * 根据文档列表页面上的查找信息和老师的专业，查询数据库中的具体信息送至文档列表界面
     *
     * @param majorId
     * @param find
     * @param value
     * @param model
     * @return
     */
    @RequestMapping(value = "/file/list", method = RequestMethod.POST)
    public String list(Long majorId, String find, String value, Model model) {

        if ("uid".equals(find)) {
            List<Material> list = documentService.findByMajAndUid(majorId, Long.parseLong(value));
            model.addAttribute("list", list);
        } else if ("filename".equals(find)) {
            List<Material> list = documentService.findByMajAndFil(majorId, value);
            model.addAttribute("list", list);
        }

        List<User> listUser = userService.findAll();
        model.addAttribute("listUser", listUser);

        return "/teacher/file/list";
    }

    /**
     * 根据请求路径上的文档id值删除对应的文档（包括本地文件以及学习端的pdf文档）
     *
     * @param id
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping("/file/delete")
    public ModelAndView delete(Long id, ModelAndView mv) throws Exception {
        Material material = documentService.findById(id);
        String location = material.getLocation();
        boolean flag = documentService.delete(id, location, material.getDataName());
        if (flag == true) {
            mv.addObject("msg", "删除成功");
        } else {
            mv.addObject("msg", "删除失败");
        }
        mv.setViewName("redirect:/teacher/file/list");
        return mv;
    }

    /**
     * 根据请求路径上的文档id值查询数据库中的具体文档信息送至文档修改界面（为修改提供使用专业和上传人）
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/file/update")
    public String toUpdate(Long id, Model model) {
        Material material = documentService.findById(id);
        List<User> listUser = userService.findAll();
        model.addAttribute("listUser", listUser);
        model.addAttribute("document", material);
        return "/teacher/file/update";
    }

    /**
     * 搜集文档修改界面上的所有信息并根据文档id将对应的文档信息在数据库中进行修改
     *
     * @param id
     * @param uId
     * @param title
     * @param remark
     * @param dataName
     * @param isLoad
     * @param mv
     * @return
     */
    @RequestMapping(value = "/file/update", method = RequestMethod.POST)
    public ModelAndView updateDocument(Long id, Long uId, String title, String remark, String dataName, Integer isLoad, ModelAndView mv) {
        Material material = new Material(id, uId, dataName, title, remark, isLoad);
        boolean flag = documentService.teacherUpdate(material);
        if (flag == true) {
            mv.addObject("msg", "修改成功");
        } else {
            mv.addObject("msg", "修改失败");
        }
        mv.setViewName("redirect:/teacher/file/list");
        return mv;
    }

    /**
     * 老师端文档页面的跳转
     *
     * @param model
     * @return
     */
    @RequestMapping("/file/add")
    public String add(Model model) {
        List<User> listUser = userService.findAll();
        List<Major> listMajor = majorService.findAll();
        model.addAttribute("listMajor", listMajor);
        model.addAttribute("listUser", listUser);
        return "/teacher/file/add";
    }

    /**
     * 将文档添加界面的文档信息插入数据库，并将文档存至本地的文件夹
     *
     * @param mv
     * @param file
     * @param title
     * @param remark
     * @param uId
     * @param majorId
     * @param createTime
     * @param isLoad
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/file/add", method = RequestMethod.POST)
    public ModelAndView addMaterial(ModelAndView mv, MultipartFile file, String title, String remark, Long uId, Long majorId, String createTime, Integer isLoad, HttpSession session) throws Exception {
        /**
         * 上传文件
         */
        String path = session.getServletContext().getRealPath("/upload/");
        String filename = file.getOriginalFilename();
        path = "D://designer//document";
        File tempFile = new File(path + File.separator + filename);
        tempFile.createNewFile();
        file.transferTo(tempFile);
        String location = path + "//" + filename;
        Material material = new Material(uId, majorId, filename, location, createTime, title, remark, isLoad);
        boolean flag = documentService.insert(material);
        if (flag == true) {
            mv.addObject("msg", "插入成功");
        } else {
            mv.addObject("msg", "插入失败");
        }
        mv.setViewName("redirect:/teacher/file/list");
        return mv;
    }

    /**
     * 根据登录老师的专业查询本专业所有的选择题
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/chioce/list")
    public String chioceList(Model model, HttpSession session, Integer indexPage) {
        if (indexPage == null) {
            indexPage = 1;
        }
        Page startPage = PageHelper.startPage(indexPage, 5);
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<ChioceTest> list = chioceTestService.findByMajorid(user.getMajorid());
        List<User> listUser = userService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("listUser", listUser);
        model.addAttribute("pages", startPage.getPages());
        return "/teacher/test/chioceList";
    }

    /**
     * 搜集老师端选择题列表的查询信息，在数据库中查询信息并返回列表界面
     *
     * @param uid
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/chioce/list", method = RequestMethod.POST)
    public String clist(Long uid, Model model, HttpSession session) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<ChioceTest> list = chioceTestService.findByUidAndMaj(user.getMajorid(), uid);
        List<User> listUser = userService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("listUser", listUser);
        return "/teacher/test/chioceList";
    }

    /**
     * 根据请求路径上的选择题id删除数据库中对应选择题
     *
     * @param cid
     * @param mv
     * @return
     */
    @RequestMapping("/chioce/delete")
    public ModelAndView deleteChioce(Long cid, ModelAndView mv) {
        boolean flag = chioceTestService.delete(cid);
        if (flag == true) {
            mv.addObject("msg", "删除失败");
        } else {
            mv.addObject("msg", "删除成功");
        }
        mv.setViewName("redirect:/teacher/chioce/list");
        return mv;
    }

    /**
     * 根据请求路劲上的选择题id查询数据库中该选择题的所有信息，展示于选择题修改页面，并跳转至修改页面
     *
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping("/chioce/update")
    public String updateChioce(Long cid, Model model) {
        ChioceTest chioceTest = chioceTestService.findByCid(cid);
        List<User> listUser = userService.findAll();
        model.addAttribute("listUser", listUser);
        model.addAttribute("chioce", chioceTest);
        return "/teacher/test/chioceUpdate";
    }

    /**
     * 搜集所有选择题页面上的信息，修改数据库中的选择题id对应的信息
     *
     * @param cid
     * @param account
     * @param aitem
     * @param bitem
     * @param citem
     * @param ditem
     * @param answer
     * @param score
     * @param uid
     * @param mv
     * @return
     */
    @RequestMapping(value = "/chioce/update", method = RequestMethod.POST)
    public ModelAndView chioceUpdate(Long cid, String account, String aitem, String bitem, String citem, String ditem, String answer, String score, Long uid, ModelAndView mv) {
        ChioceTest chioceTest = new ChioceTest(cid, account, aitem, bitem, citem, ditem, answer, Integer.parseInt(score), uid);
        boolean flag = chioceTestService.teacherUpdateChioce(chioceTest);
        if (flag == true) {
            mv.addObject("msg", "修改成功");
        } else {
            mv.addObject("msg", "修改失败");
        }
        mv.setViewName("redirect:/teacher/chioce/list");
        return mv;
    }

    /**
     * 选择题添加页面信息传送和页面跳转
     *
     * @param model
     * @return
     */
    @RequestMapping("/chioce/add")
    public String chioceAdd(Model model) {
        List<User> listUser = userService.findAll();
        List<Major> listMajor = majorService.findAll();
        model.addAttribute("listMajor", listMajor);
        model.addAttribute("listUser", listUser);
        return "/teacher/test/chioceAdd";
    }

    /**
     * 搜集选择题添加页面信息，存入数据库
     *
     * @param account
     * @param comment
     * @param aitem
     * @param bitem
     * @param citem
     * @param ditem
     * @param answer
     * @param score
     * @param uid
     * @param majorid
     * @param mv
     * @return
     */
    @RequestMapping(value = "/chioce/add", method = RequestMethod.POST)
    public ModelAndView insertChioce(String account, String comment, String aitem, String bitem, String citem, String ditem, String answer, String score, Long uid, Long majorid, ModelAndView mv) {
        ChioceTest chioceTest = new ChioceTest(account, aitem, bitem, citem, ditem, answer, Integer.parseInt(score), majorid, false, comment, uid);
        boolean flag = chioceTestService.addChioce(chioceTest);
        if (flag == true) {
            mv.addObject("msg", "题目添加成功");
        } else {
            mv.addObject("msg", "题目添加失败");
        }
        mv.setViewName("redirect:/teacher/chioce/list");
        return mv;
    }

    /**
     * 判断题列表信息传送以及页面跳转
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/judge/list")
    public String judgeList(Model model, HttpSession session, Integer indexPage) {
        if (indexPage == null) {
            indexPage = 1;
        }
        Page startPage = PageHelper.startPage(indexPage, 5);
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<JudgeTest> list = judgeTestService.findByMajorid(user.getMajorid());
        List<User> listUser = userService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("listUser", listUser);
        model.addAttribute("pages", startPage.getPages());
        return "/teacher/test/judgeList";
    }

    /**
     * 判断题列表的查询信息回收，并将查询信息进行数据库查询再传回页面
     *
     * @param model
     * @param uid
     * @param session
     * @return
     */
    @RequestMapping(value = "/judge/list", method = RequestMethod.POST)
    public String listJudge(Model model, Long uid, HttpSession session) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<JudgeTest> list = judgeTestService.findByMajAndUid(uid, user.getMajorid());
        List<User> listUser = userService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("listUser", listUser);
        return "/teacher/test/judgeList";
    }

    /**
     * 判断题id删除
     *
     * @param mv
     * @param jid
     * @return
     */
    @RequestMapping("/judge/delete")
    public ModelAndView deleteJudge(ModelAndView mv, Long jid) {
        boolean flag = judgeTestService.delete(jid);
        if (flag == true) {
            mv.addObject("msg", "删除成功");
        } else {
            mv.addObject("msg", "删除失败");
        }
        mv.setViewName("redirect:/teacher/judge/list");
        return mv;
    }

    /**
     * 判断题id查询并传送给判断题修改页面进行页面跳转
     *
     * @param model
     * @param jid
     * @return
     */
    @RequestMapping("/judge/update")
    public String updateJudge(Model model, Long jid) {
        JudgeTest judgeTest = judgeTestService.findByJid(jid);
        List<User> listUser = userService.findAll();
        model.addAttribute("judge", judgeTest);
        model.addAttribute("listUser", listUser);
        return "/teacher/test/judgeUpdate";
    }

    /**
     * 搜集判断题修改信息，修改对应数据库信息
     *
     * @param mv
     * @param jid
     * @param uid
     * @param account
     * @param answer
     * @param score
     * @param comment
     * @return
     */
    @RequestMapping(value = "/judge/update", method = RequestMethod.POST)
    public ModelAndView judgeUpdate(ModelAndView mv, Long jid, Long uid, String account, int answer, String score, String comment) {
        JudgeTest judgeTest = new JudgeTest(jid, account, answer, Integer.parseInt(score), comment, uid);
        boolean flag = judgeTestService.teacherUpdate(judgeTest);
        if (flag == true) {
            mv.addObject("msg", "修改成功");
        } else {
            mv.addObject("msg", "修改失败");
        }
        mv.setViewName("redirect:/teacher/judge/list");
        return mv;
    }

    /**
     * 判断题添加页面跳转
     *
     * @param model
     * @return
     */
    @RequestMapping("/judge/add")
    public String addJudge(Model model) {
        List<User> listUser = userService.findAll();
        List<Major> listMajor = majorService.findAll();
        model.addAttribute("listUser", listUser);
        model.addAttribute("listMajor", listMajor);
        return "/teacher/test/judgeAdd";
    }

    /**
     * 判断题添加信息存档
     *
     * @param mv
     * @param account
     * @param answer
     * @param comment
     * @param majorId
     * @param uid
     * @param score
     * @return
     */
    @RequestMapping(value = "/judge/add", method = RequestMethod.POST)
    public ModelAndView insertJudge(ModelAndView mv, String account, int answer, String comment, Long majorId, Long uid, String score) {
        JudgeTest judgeTest = new JudgeTest(account, answer, majorId, Integer.parseInt(score), comment, uid);
        boolean flag = judgeTestService.addJudge(judgeTest);
        if (flag == true) {
            mv.addObject("msg", "添加成功");
        } else {
            mv.addObject("msg", "添加失败");
        }
        mv.setViewName("redirect:/teacher/judge/list");
        return mv;
    }

    /**
     * 根据登陆老师专业查询所有填空题
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/vacant/list")
    public String vacantList(Model model, HttpSession session, Integer indexPage) {
        if (indexPage == null) {
            indexPage = 1;
        }
        Page startPage = PageHelper.startPage(indexPage, 5);
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<VacantTest> list = vacantService.findByMajorId(user.getMajorid());
        List<User> listUser = userService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("listUser", listUser);
        model.addAttribute("pages", startPage.getPages());
        return "/teacher/test/vacantList";
    }

    /**
     * 填空题列表查询信息搜集、查询并返回数据
     *
     * @param model
     * @param uid
     * @param session
     * @return
     */
    @RequestMapping(value = "/vacant/list", method = RequestMethod.POST)
    public String listVacant(Model model, Long uid, HttpSession session) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<VacantTest> list = vacantService.findByMajAndUid(user.getMajorid(), uid);
        List<User> listUser = userService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("listUser", listUser);
        return "/teacher/test/vacantList";
    }

    /**
     * 根据填空题id删除数据库对应题
     *
     * @param mv
     * @param vid
     * @return
     */
    @RequestMapping("/vacant/delete")
    public ModelAndView deleteVacant(ModelAndView mv, Long vid) {
        boolean flag = vacantService.delete(vid);
        if (flag == true) {
            mv.addObject("msg", "删除成功");
        } else {
            mv.addObject("msg", "删除失败");
        }
        mv.setViewName("redirect:/teacher/vacant/list");
        return mv;
    }

    /**
     * 根据请求路上的填空题id值查询具体的填空题信息，数据传送至填空题页面，并页面跳转
     *
     * @param model
     * @param vid
     * @return
     */
    @RequestMapping("/vacant/update")
    public String updateVacant(Model model, Long vid) {
        VacantTest vacantTest = vacantService.findByVid(vid);
        List<User> listUser = userService.findAll();
        model.addAttribute("vacant", vacantTest);
        model.addAttribute("listUser", listUser);
        return "/teacher/test/vacantUpdate";
    }

    /**
     * 搜集填空题修改的信息，并根据填空题id修改数据库对应的填空题信息
     *
     * @param mv
     * @param vid
     * @param account
     * @param comment
     * @param answer
     * @param score
     * @param uid
     * @return
     */
    @RequestMapping(value = "/vacant/update", method = RequestMethod.POST)
    public ModelAndView vacantUpdate(ModelAndView mv, Long vid, String account, String comment, String answer, String score, Long uid) {
        VacantTest vacantTest = new VacantTest(vid, account, answer, Integer.parseInt(score), comment, uid);
        boolean flag = vacantService.teacherUpdate(vacantTest);
        if (flag == true) {
            mv.addObject("msg", "修改成功");
        } else {
            mv.addObject("msg", "修改失败");
        }
        mv.setViewName("redirect:/teacher/vacant/list");
        return mv;
    }

    /**
     * 填空题添加并页面跳转
     *
     * @param model
     * @return
     */
    @RequestMapping("/vacant/add")
    public String addVacant(Model model) {
        List<User> listUser = userService.findAll();
        List<Major> listMajor = majorService.findAll();
        model.addAttribute("listUser", listUser);
        model.addAttribute("listMajor", listMajor);
        return "/test/vacantAdd";
    }

    /**
     * 填空题添加页面数据搜集并存入数据库
     *
     * @param mv
     * @param account
     * @param answer
     * @param comment
     * @param score
     * @param majorId
     * @param uid
     * @return
     */
    @RequestMapping(value = "/vacant/add", method = RequestMethod.POST)
    public ModelAndView vacantAdd(ModelAndView mv, String account, String answer, String comment, String score, Long majorId, Long uid) {
        VacantTest vacantTest = new VacantTest(account, answer, majorId, Integer.parseInt(score), comment, uid);
        boolean flag = vacantService.add(vacantTest);
        if (flag == true) {
            mv.addObject("msg", "添加成功");
        } else {
            mv.addObject("msg", "添加失败");
        }
        mv.setViewName("redirect:/teacher/vacant/list");
        return mv;
    }

    /**
     * 根据该老师的专业查询所有的选择、判断、填空题，为添加新试卷做准备，并跳转试卷添加页面
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/addTest")
    public String testAdd(Model model, HttpSession session) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<ChioceTest> cList = chioceTestService.findAllByMajorId(user.getMajorid());
        List<JudgeTest> jList = judgeTestService.findAllByMajorId(user.getMajorid());
        List<VacantTest> vList = vacantService.findAllByMajorId(user.getMajorid());
        model.addAttribute("user", user);
        model.addAttribute("cList", cList);
        model.addAttribute("jList", jList);
        model.addAttribute("vList", vList);
        return "/teacher/testAdd";
    }

    /**
     * 将老师信息，老师添加的所有题的id值和总分插入数据库
     *
     * @param mv
     * @param uid
     * @param majorid
     * @param createTime
     * @param testName
     * @param chioceList
     * @param judgeList
     * @param vacantList
     * @return
     */
    @RequestMapping(value = "/addTest", method = RequestMethod.POST)
    public ModelAndView addTest(ModelAndView mv, Long uid, Long majorid, String createTime, String testName, String chioceList, String judgeList, String vacantList) {
        int totalScore = chioceList.split(",").length * 15 + judgeList.split(",").length * 10 + vacantList.split(",").length * 10;
        TestEntity testEntity = new TestEntity(testName, majorid, uid, createTime, chioceList, judgeList, vacantList, totalScore, 0);
        boolean flag = testEntityService.insertTest(testEntity);
        if (flag == true) {
            mv.addObject("msg", "插入成功");
        } else {
            mv.addObject("msg", "插入失败");
        }
        mv.setViewName("redirect:/teacher/testList");
        return mv;
    }

    /**
     * 登陆老师专业所有的试卷套题展示列表
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/testList")
    public String testList(Model model, HttpSession session, Integer indexPage) {
        if (indexPage == null) {
            indexPage = 1;
        }
        Page startPage = PageHelper.startPage(indexPage, 5);
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<TestEntity> list = testEntityService.findAllByMajorId(user.getMajorid());
        model.addAttribute("list", list);
        model.addAttribute("pages", startPage.getPages());
        return "/teacher/testList";
    }

    /**
     * 根据请求路径上的试卷id值删除数据库中对应的试卷
     *
     * @param mv
     * @param tid
     * @return
     */
    @RequestMapping("/testDelete")
    public ModelAndView deleteById(ModelAndView mv, Long tid) {
        boolean flag = testEntityService.deleteTest(tid);
        if (flag == true) {
            mv.addObject("msg", "删除成功");
        } else {
            mv.addObject("msg", "删除失败");
        }
        mv.setViewName("redirect:/teacher/testList");
        return mv;
    }

    /**
     * 根据请求路径上的试卷id查询具体试卷，并将信息传值试卷修改页面
     *
     * @param model
     * @param tid
     * @return
     */
    @RequestMapping("/testUpdate")
    public String updateTest(Model model, Long tid) {
        TestEntity test = testEntityService.findByTid(tid);
        List<Major> listMajor = majorService.findAll();
        List<User> listUser = userService.findAll();
        model.addAttribute("test", test);
        model.addAttribute("listMajor", listMajor);
        model.addAttribute("listUser", listUser);
        return "/teacher/testUpdate";
    }

    /**
     * 搜集试卷修改页面的信息并修改对应数据库信息
     *
     * @param mv
     * @param tid
     * @param uid
     * @param majorid
     * @param tname
     * @param totalScore
     * @param isUsed
     * @return
     */
    @RequestMapping(value = "/testList/update", method = RequestMethod.POST)
    public ModelAndView testUpdate(ModelAndView mv, Long tid, Long uid, Long majorid, String tname, String totalScore, Integer isUsed) {
        if (totalScore != null && totalScore != "") {
            TestEntity testEntity = new TestEntity(tid, tname, majorid, uid, Integer.parseInt(totalScore), isUsed);
        } else {
            TestEntity testEntity = new TestEntity(tid, tname, majorid, uid, 0, isUsed);
        }
        TestEntity testEntity = new TestEntity(tid, tname, majorid, uid, Integer.parseInt(totalScore), isUsed);
        boolean flag = testEntityService.updateTest(testEntity);
        if (flag == true) {
            mv.addObject("msg", "修改成功");
        } else {
            mv.addObject("msg", "修改失败");
        }
        mv.setViewName("redirect:/teacher/testList");
        return mv;
    }

    /**
     * 根据请求路径上的试卷id值查询具体试卷信息展示一整套试卷
     *
     * @param model
     * @param tid
     * @return
     */
    @RequestMapping("/testLook")
    public String lookUp(Model model, Long tid) {
        TestEntity testEntity = testEntityService.findByTid(tid);
        String str = "(" + testEntity.getCidList() + ")";
        String str1 = "(" + testEntity.getJidList() + ")";
        String str2 = "(" + testEntity.getVidList() + ")";
        List<ChioceTest> chioceList = chioceTestService.findBtCids(str);
        List<JudgeTest> judgeList = judgeTestService.findByJids(str1);
        List<VacantTest> vacantList = vacantService.findByVids(str2);
        model.addAttribute("test", testEntity);
        model.addAttribute("chioceList", chioceList);
        model.addAttribute("judgeList", judgeList);
        model.addAttribute("vacantList", vacantList);
        return "/teacher/testSubject";
    }

    /**
     * 所有该老师专业学生考过的试卷信息列表
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/testInfo/list")
    public String testInfoList(Model model, HttpSession session, Integer indexPage) {
        if (indexPage == null) {
            indexPage = 1;
        }
        Page startPage = PageHelper.startPage(indexPage, 5);
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<TestInfo> list = testInfoService.findAllByMajorId(user.getMajorid());
        String[] listAnswer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String[] integerList = list.get(i).getjAnswerList().split(",");
            for (int k = 0; k < integerList.length; k++) {
                if ("0".equals(integerList[k])) {
                    if (k == 0) {
                        listAnswer[i] = "×,";
                    } else if (k == integerList.length - 1) {
                        listAnswer[i] += "×";
                    } else {
                        listAnswer[i] += "×,";
                    }
                } else if ("1".equals(integerList[k])) {
                    if (k == 0) {
                        listAnswer[i] = "√,";
                    } else if (k == integerList.length - 1) {
                        listAnswer[i] += "√";
                    } else {
                        listAnswer[i] += "√,";
                    }
                }
            }
            list.get(i).setjAnswerList(listAnswer[i]);
        }
        model.addAttribute("list", list);
        model.addAttribute("pages", startPage.getPages());
        return "/teacher/testInfoList";
    }

    /**
     * 根据考生试卷列表的查询信息查询数据库对应具体信息，返回列表
     *
     * @param model
     * @param find
     * @param findValue
     * @param session
     * @return
     */
    @RequestMapping(value = "/testInfo/list", method = RequestMethod.POST)
    public String listTestInfo(Model model, String find, String findValue, HttpSession session) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        if ("testName".equals(find)) {
            List<TestInfo> list = testInfoService.findByTnameAndMajor(findValue, user.getMajorid());
            String[] listAnswer = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                String[] integerList = list.get(i).getjAnswerList().split(",");
                for (int k = 0; k < integerList.length; k++) {
                    if ("0".equals(integerList[k])) {
                        if (k == 0) {
                            listAnswer[i] = "×,";
                        } else if (k == integerList.length - 1) {
                            listAnswer[i] += "×";
                        } else {
                            listAnswer[i] += "×,";
                        }
                    } else if ("1".equals(integerList[k])) {
                        if (k == 0) {
                            listAnswer[i] = "√,";
                        } else if (k == integerList.length - 1) {
                            listAnswer[i] += "√";
                        } else {
                            listAnswer[i] += "√,";
                        }
                    }
                }
                list.get(i).setjAnswerList(listAnswer[i]);
            }
            model.addAttribute("list", list);
        } else if ("userName".equals(find)) {
            List<TestInfo> list = testInfoService.findByUnameAndMajor(findValue, user.getMajorid());
            String[] listAnswer = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                String[] integerList = list.get(i).getjAnswerList().split(",");
                for (int k = 0; k < integerList.length; k++) {
                    if ("0".equals(integerList[k])) {
                        if (k == 0) {
                            listAnswer[i] = "×,";
                        } else if (k == integerList.length - 1) {
                            listAnswer[i] += "×";
                        } else {
                            listAnswer[i] += "×,";
                        }
                    } else if ("1".equals(integerList[k])) {
                        if (k == 0) {
                            listAnswer[i] = "√,";
                        } else if (k == integerList.length - 1) {
                            listAnswer[i] += "√";
                        } else {
                            listAnswer[i] += "√,";
                        }
                    }
                }
                list.get(i).setjAnswerList(listAnswer[i]);
            }
            model.addAttribute("list", list);
        }
        return "/teacher/testInfoList";
    }

    /**
     * 删除该请求路径上试卷id对应的试卷
     *
     * @param mv
     * @param fid
     * @return
     */
    @RequestMapping("/testInfo/delete")
    public ModelAndView deleteTestInfo(ModelAndView mv, Long fid) {
        boolean flag = testInfoService.deleteByFid(fid);
        if (flag == true) {
            mv.addObject("msg", "删除成功");
        } else {
            mv.addObject("msg", "删除失败");
        }
        mv.setViewName("redirect:/teacher/testInfo/list");
        return mv;
    }

    /**
     * 根据请求路径上的考生试卷id查看考试试卷的具体内容和正确答案
     *
     * @param model
     * @param fid
     * @return
     */
    @RequestMapping("/testInfo/lookTest")
    public String lookTest(Model model, Long fid) {
        TestInfo testInfo = testInfoService.findByFid(fid);
        String cidList = "(" + testInfo.getTest().getCidList() + ")";
        String jidList = "(" + testInfo.getTest().getJidList() + ")";
        String vidList = "(" + testInfo.getTest().getVidList() + ")";
        List<ChioceTest> chioceList = chioceTestService.findBtCids(cidList);
        List<JudgeTest> judgeList = judgeTestService.findByJids(jidList);
        List<VacantTest> vacantList = vacantService.findByVids(vidList);
        model.addAttribute("testInfo", testInfo);
        model.addAttribute("chioceList", chioceList);
        model.addAttribute("judgeList", judgeList);
        model.addAttribute("vacantList", vacantList);
        return "/teacher/stuTest";
    }

    /**
     * 根据登录老师专业统计该专业所有的试卷套题做题情况
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/testTotalMes")
    public String testTotalMes(Model model, HttpSession session) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        List<TestMessage> listTestMes = new ArrayList<>();
        List<TestEntity> listEntity = testEntityService.findByMajAndTea(user.getMajorid());
        for (int i = 0; i < listEntity.size(); i++) {
            TestMessage testMessage = new TestMessage();
            testMessage.setTid(listEntity.get(i).getTid());
            testMessage.setTname(listEntity.get(i).getTname());
            testMessage.setTotalNum(listEntity.get(i).getListInfo().size());
            int count = 0;
            for (int j = 0; j < listEntity.get(i).getListInfo().size(); j++) {
                if (listEntity.get(i).getListInfo().get(j).getScore() >= 60) {
                    count++;
                }
            }
            testMessage.setPassNum(count);
            if (listEntity.get(i).getListInfo().size() > 0) {
                testMessage.setPassRate(((double) count / listEntity.get(i).getListInfo().size()) * 100);
            } else {
                testMessage.setPassRate(0.0);
            }
            listTestMes.add(testMessage);
        }
        model.addAttribute("listTestMes", listTestMes);
        return "/teacher/testCheck/testTotalInfo";
    }

    /**
     * 根据请求路径上的试卷id值统计该套试卷的分数段情况
     *
     * @param model
     * @param tid
     * @return
     */
    @RequestMapping("/testMesInfo")
    public String testScore(Model model, Long tid) {
        TestEntity testEntity = testEntityService.findAllByTid(tid);
        int a = 0, b = 0, c = 0, d = 0, e = 0;
        List<Integer> listNum = new ArrayList<>();
        for (int i = 0; i < testEntity.getListInfo().size(); i++) {
            if (testEntity.getListInfo().get(i).getScore() >= 90 && testEntity.getListInfo().get(i).getScore() < 100) {
                a++;
            } else if (testEntity.getListInfo().get(i).getScore() >= 80 && testEntity.getListInfo().get(i).getScore() < 90) {
                b++;
            } else if (testEntity.getListInfo().get(i).getScore() >= 70 && testEntity.getListInfo().get(i).getScore() < 80) {
                c++;
            } else if (testEntity.getListInfo().get(i).getScore() >= 60 && testEntity.getListInfo().get(i).getScore() < 70) {
                d++;
            } else if (testEntity.getListInfo().get(i).getScore() < 60) {
                e++;
            }
        }
        listNum.add(a);
        listNum.add(b);
        listNum.add(c);
        listNum.add(d);
        listNum.add(e);
        model.addAttribute("test", testEntity);
        model.addAttribute("listNum", listNum);
        return "/teacher/testCheck/testScore";
    }

    /**
     * 根据请求路径上的考卷id统计该套试卷中选择题的做题情况
     *
     * @param model
     * @param tid
     * @return
     */
    @RequestMapping("/chioceInfo")
    public String chioceList(Model model, Long tid) {
        TestEntity testEntity = testEntityService.findAllByTid(tid);
        String chioce = "(" + testEntity.getCidList() + ")";
        List<ChioceTest> listChioce = chioceTestService.findBtCids(chioce);
        List<String> cNames = new ArrayList<>();
        List<Integer> cNums = new ArrayList<>();
        for (int i = 0; i < listChioce.size(); i++) {
            int count = 0;
            for (int j = 0; j < testEntity.getListInfo().size(); j++) {
                String[] str = testEntity.getListInfo().get(j).getcAnswerList().split(",");
                if (listChioce.get(i).getAnswer().equals(str[i])) {
                    count++;
                }
            }
            cNames.add("选择题" + (i + 1));
            cNums.add(count);
        }
        model.addAttribute("test", testEntity);
        model.addAttribute("listNames", cNames);
        model.addAttribute("listNums", cNums);
        return "/teacher/testCheck/chioceMes";
    }

    /**
     * 根据请求路径上的考卷id统计该套试卷中判断题的做题情况
     *
     * @param model
     * @param tid
     * @return
     */
    @RequestMapping("/judgeInfo")
    public String judgeList(Model model, Long tid) {
        TestEntity testEntity = testEntityService.findAllByTid(tid);
        String judge = "(" + testEntity.getJidList() + ")";
        List<JudgeTest> listJudge = judgeTestService.findByJids(judge);
        List<String> jNames = new ArrayList<>();
        List<Integer> jNums = new ArrayList<>();
        for (int i = 0; i < listJudge.size(); i++) {
            int count = 0;
            for (int j = 0; j < testEntity.getListInfo().size(); j++) {
                String[] str = testEntity.getListInfo().get(j).getjAnswerList().split(",");
                if (listJudge.get(i).getAnswer() == Integer.parseInt(str[i])) {
                    count++;
                }
            }
            jNames.add("判断题" + (i + 1));
            jNums.add(count);
        }
        model.addAttribute("test", testEntity);
        model.addAttribute("listNames", jNames);
        model.addAttribute("listNums", jNums);
        return "/teacher/testCheck/judgeMes";
    }

    /**
     * 根据请求路径上的考卷id统计该套试卷中填空题的做题情况
     *
     * @param model
     * @param tid
     * @return
     */
    @RequestMapping("/vacantInfo")
    public String vacantList(Model model, Long tid) {
        TestEntity testEntity = testEntityService.findAllByTid(tid);
        String vacant = "(" + testEntity.getVidList() + ")";
        List<VacantTest> listVacant = vacantService.findByVids(vacant);
        List<String> vNames = new ArrayList<>();
        List<Integer> vNums = new ArrayList<>();
        for (int i = 0; i < listVacant.size(); i++) {
            int count = 0;
            for (int j = 0; j < testEntity.getListInfo().size(); j++) {
                String[] str = testEntity.getListInfo().get(j).getvAnswerList().split(",");
                if (listVacant.get(i).getAnswer().equals(str[i])) {
                    count++;
                }
            }
            vNames.add("填空题" + (i + 1));
            vNums.add(count);
        }
        model.addAttribute("test", testEntity);
        model.addAttribute("listNames", vNames);
        model.addAttribute("listNums", vNums);
        return "/teacher/testCheck/vacantMes";
    }

}
