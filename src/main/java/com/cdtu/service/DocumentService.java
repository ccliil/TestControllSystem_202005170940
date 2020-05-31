package com.cdtu.service;

import com.cdtu.dao.DocumentDao;
import com.cdtu.entity.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentDao documentDao;
    public List<Material> findAll() {
        return documentDao.findAll();
    }

    public List<Material> findByUid(long value) {
        return documentDao.findByUid(value);
    }

    public List<Material> findByMajorId(long value) {
        return documentDao.findByMajorId(value);
    }

    public List<Material> findByFilename(String value) {
        return documentDao.findByFilename("%"+value+"%");
    }

    public boolean delete (Long id,String location,String fileName) {
        File file=new File(location);
        File file1=new File("D:\\designer\\TestControllSystem\\src\\main\\resources\\doc\\"+fileName+".pdf");
        if(file.exists() && !file1.exists()){
            return (file.delete())&&(documentDao.delete(id));
        }else if(!file.exists() && file1.exists()){
            return (file1.delete())&&(documentDao.delete(id));
        }else if(file.exists() && file1.exists()){
            return (file.delete())&&(file1.delete())&&(documentDao.delete(id));
        }
        return false;
    }

    public Material findById(Long id) {
        return documentDao.findById(id);
    }

    public boolean update(Material material) {
        return documentDao.update(material);
    }

    public boolean insert(Material material) {
        return documentDao.insert(material);
    }

    public List<Material> findByMaAndEndWith(Long majorid, String s) {
        s="%"+s;
        return documentDao.findByMaAndEndWith(majorid,s);
    }

    public List<Material> findByMajAndUid(Long majorId, long uid) {
        return documentDao.findByMajAndUid(majorId,uid);
    }

    public List<Material> findByMajAndFil(Long majorId, String name) {
        String filename="%"+name+"%";
        return documentDao.findByMajorAndFil(majorId,filename);
    }

    public boolean teacherUpdate(Material material) {
        return documentDao.teacherUpdate(material);
    }

    public List<Material> findAllDocumentEndwithDoc(Long majorid) {

        return documentDao.findAllDocumentEndwithDoc(majorid);
    }

    public List<Material> findByFilenameStu(Long majorId, String value) {

        return documentDao.findByfilenameStu(majorId);
    }
}
