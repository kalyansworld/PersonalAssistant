package com.prodapt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prodapt.events.UserEvents;
import com.prodapt.notification.NotificationMessage;

@RestController
public class SmartBotController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		return "index";
	}

	@RequestMapping(value="/getNotifications", method = RequestMethod.GET)
	public ResponseEntity<List<NotificationMessage>> getnotification(
			@RequestParam(value = "userName") String userName) {

		UserEvents events = new UserEvents(userName);

		return new ResponseEntity<List<NotificationMessage>>(
				events.getNotification(), HttpStatus.OK);
	}

}
