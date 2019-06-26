package com.woniuxy.g_httpmessageconverter.a;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class AppTest {
	// 当前是服务器端
	public static void main(String[] args) throws Exception {
		// 创建一个套接字服务器，需要指明端口（你买了手机，下一步需要一个卡号）
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("服务器已启动");
		// 让套接字服务器监听8888端口(把手机开机，手机开机，处于待机状态，直到有一个别人给你拨号）
		// 同步阻塞状态，直到有客户端访问服务器，就会取消阻塞，继续向下执行
		// 以下获取到的client，代表客户端浏览器！
		Socket client = ss.accept();  
		System.out.println("有朋自远方来，不亦说乎！");
		
		// 服务器与客户端就要交换数据（双方开始通话）
		// 获取客户端输入流，输入流的起点：客户端，终点：服务器。（听筒）
		InputStream in = client.getInputStream();
		// 字节输出流，起点：当前程序的内存，终点：当前程序的另外一片内存，
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		byte[] buf = new byte[1028*8];
		
		// 次循环的价值在于，等待浏览器把数据发送完到服务器！
		while(in.available()==0);
		// 我们一定要保证，浏览器在把数据发送给服务器发完以后，才进入这个循环！ 
		// 如果浏览器还没有把数据发送到服务器，而先执行了这个判断，则in.available()就是0
		while(in.available() > 0) {
			int len = in.read(buf);
			bos.write(buf,0,len);
		}
		
		// 把bos操作的那一片内存中的数据，取出来，以byte[]形式返回
		byte[] bb = bos.toByteArray();
		
		// 进行解码
		String str = new String(bb);
		
		System.out.println(str);
		
		bos.close();
		in.close();
		
		
	}
	
}
