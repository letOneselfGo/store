package com.store.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SendMail {
	public void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.����һ���������ʼ��������Ự���� Session

		final Properties props = new Properties();
		//���÷��͵�Э��
		props.setProperty("mail.transport.protocol", "SMTP");
		
		//���÷����ʼ��ķ�����
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");// ָ����֤Ϊtrue
		props.setProperty("mail.user", "158858478@qq.com");
		props.setProperty("mail.password", "hicziraoyujdcabf");

		// ������֤��
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				//���÷����˵��ʺź�����
				String username = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(username, password);
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.����һ��MimeMessage�����൱�����ʼ�����
		MimeMessage message = new MimeMessage(session);

		//���÷�����
		message.setFrom(new InternetAddress(props.getProperty("mail.user")));

		//���÷��ͷ�ʽ�������?
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//�����ʼ�����
		message.setSubject("�û�����");
		 
		//�����ʼ�����
		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.���� Transport���ڽ��ʼ�����
		Transport.send(message);
	}
}
