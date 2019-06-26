package com.woniuxy.i_upload;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class UploadController {

	@RequestMapping("upload")
	public String upload(@RequestParam CommonsMultipartFile photo, HttpServletRequest request) {
		
		try {
			// ��ȡwebӦ������ʱ���ڷ����������ϵ���ʵ·��
			String path = request.getRealPath("/images");
			// ��ȡ�ļ���׺
			int lastDot = photo.getOriginalFilename().lastIndexOf(".");
			String ext = photo.getOriginalFilename().substring(lastDot);
			// ����Ψһ������
			String newName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			// ����
			photo.transferTo(new File(path,newName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
