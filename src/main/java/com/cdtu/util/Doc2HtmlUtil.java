package com.cdtu.util;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.cdtu.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.ConnectException;

/**
 * 文档转换工具类
 */
public class Doc2HtmlUtil {
    private static Doc2HtmlUtil doc2HtmlUtil;
//    @Autowired
//    private static DocumentService documentService;
    /**
     * 获取Doc2HtmlUtil实例
     */
    public static synchronized Doc2HtmlUtil getDoc2HtmlUtilInstance() {
        if (doc2HtmlUtil == null) {
            doc2HtmlUtil = new Doc2HtmlUtil();
        }
        return doc2HtmlUtil;
    }

    /**
     * 转换文件成html
     *
     * @param file:
     * @throws IOException
     */
    public String file2Html(File file, String toFilePath, String type) throws IOException {
        InputStream fromFileInputStream=new FileInputStream(file);
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String timesuffix = sdf.format(date);
        String docFileName = null;
        String htmFileName = null;
        if("doc".equals(type)){
            docFileName = file.getName()+ ".doc";
            htmFileName =  file.getName()+ ".html";
        }else if("docx".equals(type)){
            docFileName = file.getName() + ".docx";
            htmFileName =  file.getName() + ".html";
        }else if("xls".equals(type)){
            docFileName =  file.getName() + ".xls";
            htmFileName = file.getName() + ".html";
        }else if("ppt".equals(type)){
            docFileName =  file.getName()+ ".ppt";
            htmFileName =  file.getName() + ".html";
        }else{
            return null;
        }

        File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        File docInputFile = new File(toFilePath + File.separatorChar + docFileName);
        if (htmlOutputFile.exists())
            htmlOutputFile.delete();
        htmlOutputFile.createNewFile();
        if (docInputFile.exists())
            docInputFile.delete();
        docInputFile.createNewFile();
        /**
         * 由fromFileInputStream构建输入文件
         */
        try {
            OutputStream os = new FileOutputStream(docInputFile);
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            os.close();
            fromFileInputStream.close();
        } catch (IOException e) {
        }

        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
        }
        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, htmlOutputFile);
        connection.disconnect();
        // 转换完之后删除word文件
        docInputFile.delete();
        return htmFileName;
    }

    /**
     * 转换文件成pdf
     *
     * @param file:
     * @throws IOException
     */
    public String file2pdf(File file, String toFilePath,String type) throws IOException {
        InputStream fromFileInputStream=new FileInputStream(file);
        String docFileName = null;
        String htmFileName = null;
        if("doc".equals(type) || "docx".equals(type)){
            docFileName = file.getName()+ ".doc";
            htmFileName =  file.getName() + ".pdf";
        }else if("xls".equals(type)){
            docFileName = file.getName() + ".xls";
            htmFileName = file.getName() + ".pdf";
        }else if("ppt".equals(type) || "pptx".equals(type)){
            docFileName = file.getName()+ ".ppt";
            htmFileName = file.getName() + ".pdf";
        }else{
            return null;
        }

        File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        File docInputFile = new File(toFilePath + File.separatorChar + docFileName);
        if (htmlOutputFile.exists())
            htmlOutputFile.delete();
        htmlOutputFile.createNewFile();
        if (docInputFile.exists())
            docInputFile.delete();
        docInputFile.createNewFile();
        /**
         * 由fromFileInputStream构建输入文件
         */
        try {
            OutputStream os = new FileOutputStream(docInputFile);
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fromFileInputStream.close();
        } catch (IOException e) {
        }

        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
        }
        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, htmlOutputFile);
        connection.disconnect();
        // 转换完之后删除word文件
        docInputFile.delete();
        return htmFileName;
    }

    public static void main(String[] args) throws IOException {
        Doc2HtmlUtil coc2HtmlUtil = getDoc2HtmlUtilInstance();
        File file = null;
        //FileInputStream fileInputStream = null;

//        file = new File("D:/poi-test/exportExcel.xls");
//        fileInputStream = new FileInputStream(file);
//        coc2HtmlUtil.file2pdf(fileInputStream, "D:/poi-test/openOffice/xls","xls");


//        List<Material> list=documentService.findAllDocumentEndwithDoc(8l);
//        if(list!=null){
//            for(int i=0;i<list.size();i++){
//                Material material=list.get(i);
//                file=new File("D:\\designer\\TestControllSystem\\src\\main\\resources\\doc"+material.getDataName()+".pdf");
//                if(file.exists()){
//                    System.out.println(file.getName()+"已存在");
//                }else {
//                    File file1=new File(material.getLocation()+material.getDataName());
//                    coc2HtmlUtil.file2pdf(file1,"D:\\designer\\TestControllSystem\\src\\main\\resources\\doc","doc");
//                }
//
//            }
//        }

        file = new File("D:/陈丽资料+PPt/day06-Tensorflow、神经网络.pptx");
        String fileName=file.getName();
        System.out.println(fileName.substring(fileName.lastIndexOf(".")+1));
       // coc2HtmlUtil.file2pdf(file, "D:\\designer\\TestControllSystem\\src\\main\\resources\\doc","pptx");


    }
}
