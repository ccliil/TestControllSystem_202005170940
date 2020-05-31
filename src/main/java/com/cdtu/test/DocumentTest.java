package com.cdtu.test;

import com.cdtu.dao.DocumentDao;
import com.cdtu.entity.Material;
import com.cdtu.service.DocumentService;
import com.cdtu.util.Doc2HtmlUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.cdtu.util.Doc2HtmlUtil.getDoc2HtmlUtilInstance;

public class DocumentTest extends  BaseTest {
    @Autowired
    private DocumentDao documentDao;
    @Autowired
    private DocumentService documentService;
    @Test
    public void test(){
        List<Material> list=documentDao.findByFilename("xixi");
        System.out.println(list);
    }
    @Test
    public void documentTest() throws Exception{
        List<Material> list=documentDao.findAll();
        for(int i=0;i<list.size();i++){
            String location=list.get(i).getLocation();
            String[] str=location.split("//");
            location="";
            for(int j=0;j<str.length;j++){
                if(j<str.length-1){
                    location+=str[j]+"/";
                }else {
                    location+=str[j];
                }

            }
          list.get(i).setLocation(location);
            File file=new File(list.get(i).getLocation());
            if(file.exists()){
                System.out.println(file.getName());
            }
        }
    }
    @Test
    public void test1(){
        File file=new File("D:\\designer\\资料\\doc");
        if(file.isDirectory()){
            System.out.println(file.getAbsolutePath());
        }
    }
    @Test
    public void Test2() {
        Doc2HtmlUtil coc2HtmlUtil = getDoc2HtmlUtilInstance();
        File file = null;
        List<Material> list = documentService.findAllDocumentEndwithDoc(8l);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Material material = list.get(i);
                file = new File("D:\\designer\\TestControllSystem\\src\\main\\resources\\doc\\" + material.getDataName() + ".pdf");
                if (file.exists()) {
                    System.out.println(file.getName() + "已存在");
                } else {
                    File file1 = new File(material.getLocation() );
                    try {
                        coc2HtmlUtil.file2pdf(file1, "D:\\designer\\TestControllSystem\\src\\main\\resources\\doc", "doc");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @Test
    public  void test3(){
        List<Material> list = documentService.findAllDocumentEndwithDoc(8l);
        for(int i=0;i<list.size();i++){
            String filename=list.get(i).getDataName();
            String str=filename.substring(filename.lastIndexOf("."));
            System.out.println(list.get(i).getDataName()+"的后缀是："+str);
        }
    }
}
