package com.osi.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.dao.FileUploadDao;
import com.osi.pojo.FilePojo;
import com.osi.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{

	@Autowired
	FileUploadDao fileUploadDao;
	@Override
	public void saveImage(byte[] image,String fileName) {
		// TODO Auto-generated method stub
		fileUploadDao.saveImage(image,fileName);
	}
	public FilePojo downLoadFile(int fileId){
		return fileUploadDao.downLoadFile(fileId);
		
	}

}
