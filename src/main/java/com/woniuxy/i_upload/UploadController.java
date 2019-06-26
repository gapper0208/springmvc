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
			// 获取web应用运行时，在服务器机器上的真实路径
			String path = request.getRealPath("/images");
			// 获取文件后缀
			int lastDot = photo.getOriginalFilename().lastIndexOf(".");
			String ext = photo.getOriginalFilename().substring(lastDot);
			// 生成唯一的名字
			String newName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			// 存盘
			photo.transferTo(new File(path,newName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
