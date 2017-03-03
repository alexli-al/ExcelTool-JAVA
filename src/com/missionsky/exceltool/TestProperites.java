package com.missionsky.exceltool;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestProperites {
	
	public static void main(String[] args){
		
		ResourceBundle msgBundle = ResourceBundle.getBundle("emailConfig", Locale.CHINA);
    	
    	
    	String host = msgBundle.getString("email.host");
    	////SMTP的缺省用户名
        String address = msgBundle.getString("email.address.default");
        String from = msgBundle.getString("email.address.from");
        String password = msgBundle.getString("email.pwd");
        
        System.out.println(host);
        System.out.println(address);
        System.out.println(from);
        System.out.println(password);
	}
}
