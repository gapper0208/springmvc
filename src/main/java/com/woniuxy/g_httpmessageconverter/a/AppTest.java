package com.woniuxy.g_httpmessageconverter.a;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class AppTest {
	// ��ǰ�Ƿ�������
	public static void main(String[] args) throws Exception {
		// ����һ���׽��ַ���������Ҫָ���˿ڣ��������ֻ�����һ����Ҫһ�����ţ�
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("������������");
		// ���׽��ַ���������8888�˿�(���ֻ��������ֻ����������ڴ���״̬��ֱ����һ�����˸��㲦�ţ�
		// ͬ������״̬��ֱ���пͻ��˷��ʷ��������ͻ�ȡ����������������ִ��
		// ���»�ȡ����client������ͻ����������
		Socket client = ss.accept();  
		System.out.println("������Զ����������˵����");
		
		// ��������ͻ��˾�Ҫ�������ݣ�˫����ʼͨ����
		// ��ȡ�ͻ���������������������㣺�ͻ��ˣ��յ㣺������������Ͳ��
		InputStream in = client.getInputStream();
		// �ֽ����������㣺��ǰ������ڴ棬�յ㣺��ǰ���������һƬ�ڴ棬
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		byte[] buf = new byte[1028*8];
		
		// ��ѭ���ļ�ֵ���ڣ��ȴ�����������ݷ����굽��������
		while(in.available()==0);
		// ����һ��Ҫ��֤��������ڰ����ݷ��͸������������Ժ󣬲Ž������ѭ���� 
		// ����������û�а����ݷ��͵�������������ִ��������жϣ���in.available()����0
		while(in.available() > 0) {
			int len = in.read(buf);
			bos.write(buf,0,len);
		}
		
		// ��bos��������һƬ�ڴ��е����ݣ�ȡ��������byte[]��ʽ����
		byte[] bb = bos.toByteArray();
		
		// ���н���
		String str = new String(bb);
		
		System.out.println(str);
		
		bos.close();
		in.close();
		
		
	}
	
}
