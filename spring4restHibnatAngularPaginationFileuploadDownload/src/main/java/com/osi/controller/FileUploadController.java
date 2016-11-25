package com.osi.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.osi.pojo.FilePojo;
import com.osi.service.FileUploadService;


@RestController
public class FileUploadController {
    
	@Autowired
	FileUploadService fileUploadService;
	
	
	@RequestMapping(value="/fileUpload",method = RequestMethod.POST)
	public void fileUpload(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request) throws IOException{
		String contextPath=request.getRealPath("/resources"); // can get the path of resource folder from jboss statndalone/tmp/vfs/tmp090090/springapp.war090/resources
		System.out.println("uploadedInputStream "+file.getOriginalFilename());
		String fileName = null;
		byte[] bytes = file.getBytes();
		System.out.println(file.getSize());
		fileName = file.getOriginalFilename();
        //BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File("E:/temp/" + fileName))); //to store file drives
		BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(contextPath+"/" + fileName)));
        buffStream.write(bytes);
        buffStream.close();
        
        fileUploadService.saveImage(bytes,fileName);
		
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(final HttpServletRequest request, final HttpServletResponse response){
		
		File file = new File ("E:/temp/photo.jpg");
		//String contextPath=request.getRealPath("/resources");  // can get the path of project resources folder from jboss statndalone/tmp/vfs/tmp090090/springapp.war090/resources
        //File file = new File (contextPath+"/"+" photo.jpg");
        try {
        	InputStream fileInputStream = new FileInputStream(file);
                OutputStream output = response.getOutputStream();

            response.reset();

            response.setContentType("application/octet-stream");
            response.setContentLength((int) (file.length()));

            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            response.setHeader("fileName", file.getName());

            IOUtils.copyLarge(fileInputStream, output);
            //output.flush();
        } catch (IOException e) {
            //log.error(e.getMessage(), e);
        }

    }
	
	
	@RequestMapping(value = "/downloadfile", method = RequestMethod.GET)
	public void downloadFile(@RequestParam("fileId") int fileId,final HttpServletRequest request, final HttpServletResponse response) throws IOException{
		FilePojo filePojo=fileUploadService.downLoadFile(fileId);
		byte[] file=filePojo.getBytes();
		InputStream inputStream = new ByteArrayInputStream(file);
		OutputStream output = response.getOutputStream();

        response.reset();

        response.setContentType("application/octet-stream");
        response.setContentLength((int) (file.length));

        response.setHeader("Content-Disposition", "attachment; filename=\"" + filePojo.getFileName() + "\"");
        response.setHeader("fileName", filePojo.getFileName());

        IOUtils.copyLarge(inputStream, output);
		
	}

}
