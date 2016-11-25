package com.osi.service;

import com.osi.pojo.FilePojo;

public interface FileUploadService {

	
	public void saveImage(byte[] image,String fileName);
	public FilePojo downLoadFile(int fileId);
}
