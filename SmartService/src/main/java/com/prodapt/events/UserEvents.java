package com.prodapt.events;

import java.util.ArrayList;
import java.util.List;

import com.prodapt.notification.NotificationMessage;
import com.prodapt.notification.iNotification;
import com.prodapt.notification.impl.LateComingNotification;

public class UserEvents {

	final String userName;

	public UserEvents(final String userName) {
		this.userName = userName;
	}

	public List<NotificationMessage> getNotification() {

		List<NotificationMessage> notificationList = new ArrayList<NotificationMessage>();

		iNotification notification = new LateComingNotification();

		notificationList.add(notification.getEvents(userName));

		return notificationList;
	}

}
