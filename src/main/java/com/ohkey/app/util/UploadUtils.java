package com.ohkey.app.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UploadUtils.class);
	
	public static void uploadModelImg(MultipartFile file, String filename) throws IOException{
		
			// Creating the directory to store file
			String brandImgRootpath = Constant.UPLOAD_PATH_CONFIG.getString("image.model.upload.path");
			uploadFile(file,filename,brandImgRootpath);
		
	}
	
	public static void uploadFile(MultipartFile file, String filename,String fileRootpath) throws IOException{
		byte[] bytes = file.getBytes();
		File dir = new File(fileRootpath);
		if (!dir.exists())
			dir.mkdirs();
		File serverFile = new File(dir.getAbsolutePath()
				+ File.separator + filename);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
		logger.info("The File is uploaded : Location="+ serverFile.getAbsolutePath());
	}
	
}
