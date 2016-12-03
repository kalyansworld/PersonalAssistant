package com.prodapt.notification.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.prodapt.notification.NotificationMessage;
import com.prodapt.notification.iNotification;

public class LateComingNotification implements iNotification{

	public NotificationMessage getEvents(String userName) {

		NotificationMessage message=new NotificationMessage(){};
		
		Map<String,URL> lateComingMap=new HashMap<String,URL>();
		
		try {
			lateComingMap.put("1/1/2016", new URL("https://docs.oracle.com/javase/7/docs/api/java/net/URL.html"));
			lateComingMap.put("3/1/2016", new URL("https://docs.oracle.com/javase/7/docs/api/java/net/URL.html"));
			lateComingMap.put("9/1/2016", new URL("https://docs.oracle.com/javase/7/docs/api/java/net/URL.html"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		message.setCount(3);
		message.setDescription("Late Coming");
		message.setMessageData(lateComingMap);
		
		
		
		return message;
	}

}
