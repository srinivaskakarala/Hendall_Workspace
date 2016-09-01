package com.hendall.surveyrest.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;

public class FileHelper {

	public FileHelper() {
		// TODO Auto-generated constructor stub
	}

	public String uploadFile(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, String path) {
		String uploadedFileLocation = path
	            + fileDetail.getFileName();

	    // save it
	    writeToFile(uploadedInputStream, uploadedFileLocation);

	    String output = "File uploaded to : " + uploadedFileLocation;
	    
	    return "success";

	    //return Response.status(200).entity(output).build();

	}
	
	private void writeToFile(InputStream uploadedInputStream,
	        String uploadedFileLocation) {

	    try {
	        OutputStream out = new FileOutputStream(new File(
	                uploadedFileLocation));
	        int read = 0;
	        byte[] bytes = new byte[1024];

	        out = new FileOutputStream(new File(uploadedFileLocation));
	        while ((read = uploadedInputStream.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    } catch (IOException e) {

	        e.printStackTrace();
	    }

	   }

	
}
