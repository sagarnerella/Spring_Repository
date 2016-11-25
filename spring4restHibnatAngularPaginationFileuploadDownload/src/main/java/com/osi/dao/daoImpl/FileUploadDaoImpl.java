package com.osi.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.osi.dao.FileUploadDao;
import com.osi.pojo.FilePojo;

@Repository
public class FileUploadDaoImpl implements FileUploadDao{

	
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public void saveImage(byte[] bytes,String fileName) {
		// TODO Auto-generated method stub
		
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		FilePojo filePojo=new FilePojo();
		filePojo.setBytes(bytes);
		filePojo.setFileName(fileName);
		session.save(filePojo);
		session.beginTransaction().commit();
		session.close();
	}
	
	public FilePojo downLoadFile(int fileId){
		
		
		
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		FilePojo filePojo=(FilePojo) session.get(FilePojo.class,fileId);
		session.close();
		return filePojo;
	}

}
