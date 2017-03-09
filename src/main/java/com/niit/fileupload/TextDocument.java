package com.niit.fileupload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class TextDocument {
	public static boolean UploadText(String path,MultipartFile file,String file_name){
		 
		
		try {
			InputStream in=file.getInputStream();
			File dir=new File(path);
			 if (!dir.exists()) {
				 dir.mkdirs();
				   }
			File  uploading_text=new File(dir.getAbsolutePath()+File.separator+file_name);

			@SuppressWarnings("resource")
			FileOutputStream  outputStream = new FileOutputStream(uploading_text);
			   int read = 0;
			   byte[] bytes = file.getBytes();

			   while ((read = in.read(bytes)) != -1) {

			    outputStream.write(bytes, 0, read);

			    
			   }
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
public static BufferedReader ReadText( String file_name) {

		 
    String path="E:\\Workspace\\ShopOnWeb\\src\\main\\webapp\\WEB-INF\\resources\\images\\product";

	

			File dir=new File(path);
			
			File  uploading_text=new File(dir.getAbsolutePath()+File.separator+file_name);  
			if(uploading_text.exists()){
			
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(uploading_text));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return br;

			}
			else{
				return null;

			}
			   			   
			
		
		
	}

	

}
