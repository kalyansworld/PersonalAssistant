package com.prodapt.notification;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NotificationMessage {
	
	public String description;
	public int count;
	public Map<String, URL> messageData=new HashMap<String, URL>();
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Map<String, URL> getMessageData() {
		return messageData;
	}
	public void setMessageData(Map<String, URL> messageData) {
		this.messageData = messageData;
	}
	

}
