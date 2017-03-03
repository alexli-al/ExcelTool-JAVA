package com.missionsky.exceltool.util;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;


public class EmailIncludeTxt {
	
	/** logger */
	private static Logger logger = Logger.getLogger(EmailIncludeTxt.class);
	
    /**
     * 邮件发送程序
     * 
     * @param to
     *            接受人
     * @param subject
     *            邮件主题
     * @param content
     *            邮件内容
     * @throws Exception
     * @throws MessagingException
     */
    public static void sendEmail(String to, String subject, String content) throws Exception, MessagingException {
       
    	ResourceBundle msgBundle = ResourceBundle.getBundle("emailConfig", Locale.CHINA);
  
    	String host = msgBundle.getString("email.host");
    	////SMTP的缺省用户名,
        String address = msgBundle.getString("email.address.default");
        String from = msgBundle.getString("email.address.from");
        String password = msgBundle.getString("email.pwd");
//      if ("".equals(to) || to == null) {
//         to = "1960666531@qq.com";
//      }
        String port =msgBundle.getString("email.port");
        SendEmail(host, address, from, password, to, port, subject, content);
    }

    /**
     * 邮件发送程序
     * 
     * @param host
     *            邮件服务器 如：smtp.qq.com
     * @param address
     *            发送邮件的地址 如：545099227@qq.com
     * @param from
     *            来自： wsx2miao@qq.com
     * @param password
     *            您的邮箱密码
     * @param to
     *            接收人
     * @param port
     *            端口（QQ:25）
     * @param subject
     *            邮件主题
     * @param content
     *            邮件内容
     * @throws Exception
     */
    public static void SendEmail(String host, String address, String from, String password, String to, String port, String subject, String content) throws Exception {
        Multipart multiPart;
        String finalString = "";

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", address);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, null);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(finalString.getBytes(), "text/plain"));
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setDataHandler(handler);

        multiPart = new MimeMultipart();
        InternetAddress toAddress;
        toAddress = new InternetAddress(to);
        message.addRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject(subject);
        message.setContent(multiPart);
        message.setText(content);
        Transport transport = session.getTransport("smtp");
        transport.connect(host, address, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        //logger.info("发送给" + to + "的邮件\n\r主题：" + subject + "\n\r内容：\n\r" + content + "\n\r状态：已成功发送！\n\r");
        //System.out.println("发送给" + to + "的邮件\n\r主题：" + subject + "\n\r内容：\n\r" + content + "\n\r状态：已成功发送！\n\r");
    }
    
//    public static void main(String[] args) throws MessagingException, Exception {
//    	sendEmail("alex.li@missionsky.com", "Test by Alex", "Alex测试邮件发送!!!");
//	}
}