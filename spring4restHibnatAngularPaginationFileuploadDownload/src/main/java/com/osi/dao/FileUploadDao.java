package com.osi.dao;

import com.osi.pojo.FilePojo;

public interface FileUploadDao {
	public void saveImage(byte[] image,String fileName);
	public FilePojo downLoadFile(int fileId);
}
