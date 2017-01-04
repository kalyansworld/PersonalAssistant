package com.prodapt.notification.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.prodapt.notification.NotificationMessage;
import com.prodapt.notification.iNotification;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LateComingNotification implements iNotification {

	public static final String REST_SERVICE_URI = "http://onep.prodapt.com:8080/oneprodapt-new/framework/sats";
	public static final String SERVICE_NAME = "/getlopdetails";
	public static final String QUESTION_MARK = "?";
	public static final String USER_NAME = "empId=";

	public NotificationMessage getEvents(String userName) {

		NotificationMessage message = new NotificationMessage() {
		};

		Map<String, URL> lateComingMap = new HashMap<String, URL>();

		try {
			

			/*LateComingRecord[] lateComingDatas = (LateComingRecord[]) restTemplate.getForObject(
					REST_SERVICE_URI + SERVICE_NAME + QUESTION_MARK + USER_NAME + userName, 
					LateComingRecord[].class);*/
			
			String responseData=sendGet(userName);
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<Map<String,String>> lateComingDatas=mapper.readValue(responseData, new TypeReference<List<Map<String,String>>>() {
				
			});

			if (lateComingDatas != null && !lateComingDatas.isEmpty()) {

				for (Map<String,String> rec : lateComingDatas) {
					lateComingMap.put(rec.get("date"),
							new URL("http://oneplive.prodapt.com/oneprodapt-new/login.htm#/sats"));
				}

			}

			/*
			 * lateComingMap.put("1/1/2016", new
			 * URL("https://docs.oracle.com/javase/7/docs/api/java/net/URL.html"
			 * )); lateComingMap.put("3/1/2016", new
			 * URL("https://docs.oracle.com/javase/7/docs/api/java/net/URL.html"
			 * )); lateComingMap.put("9/1/2016", new
			 * URL("https://docs.oracle.com/javase/7/docs/api/java/net/URL.html"
			 * ));
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		message.setCount(3);
		message.setDescription("Late Coming");
		message.setMessageData(lateComingMap);

		return message;
	}
	
	private String sendGet(String userName) throws Exception {

		

		URL obj = new URL(REST_SERVICE_URI + SERVICE_NAME + QUESTION_MARK + USER_NAME + userName);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + obj.getPath());
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		return response.toString();

	}

	// DTO for holding all late comers record

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({ "attendanceId", "employeeId", "employeeName", "date", "supervisor1id", "supervisor1name",
			"supervisor1email", "supervisor2id", "supervisor2name", "supervisor2email", "waverId", "waver", "time",
			"reason", "absent", "mgrComment", "status", "waiveredBy", "dateForSort", "statusForSort", "transFlag",
			"isApplied", "comments", "email", "authorizedById", "authorizedByName", "waverCancelled", "isabsent" })
	public class LateComingRecord implements Serializable {

		@JsonProperty("attendanceId")
		private Integer attendanceId;
		@JsonProperty("employeeId")
		private Object employeeId;
		@JsonProperty("employeeName")
		private String employeeName;
		@JsonProperty("date")
		private Integer date;
		@JsonProperty("supervisor1id")
		private Object supervisor1id;
		@JsonProperty("supervisor1name")
		private Object supervisor1name;
		@JsonProperty("supervisor1email")
		private Object supervisor1email;
		@JsonProperty("supervisor2id")
		private Object supervisor2id;
		@JsonProperty("supervisor2name")
		private String supervisor2name;
		@JsonProperty("supervisor2email")
		private Object supervisor2email;
		@JsonProperty("waverId")
		private Integer waverId;
		@JsonProperty("waver")
		private Integer waver;
		@JsonProperty("time")
		private String time;
		@JsonProperty("reason")
		private Object reason;
		@JsonProperty("absent")
		private String absent;
		@JsonProperty("mgrComment")
		private Object mgrComment;
		@JsonProperty("status")
		private String status;
		@JsonProperty("waiveredBy")
		private Object waiveredBy;
		@JsonProperty("dateForSort")
		private Object dateForSort;
		@JsonProperty("statusForSort")
		private String statusForSort;
		@JsonProperty("transFlag")
		private Object transFlag;
		@JsonProperty("isApplied")
		private Object isApplied;
		@JsonProperty("comments")
		private Object comments;
		@JsonProperty("email")
		private Object email;
		@JsonProperty("authorizedById")
		private Object authorizedById;
		@JsonProperty("authorizedByName")
		private Object authorizedByName;
		@JsonProperty("waverCancelled")
		private Object waverCancelled;
		@JsonProperty("isabsent")
		private Object isabsent;
		private final static long serialVersionUID = 6484903595491849406L;

		/**
		 * No args constructor for use in serialization
		 * 
		 */
		public LateComingRecord() {
		}

		/**
		 * 
		 * @param mgrComment
		 * @param employeeId
		 * @param isApplied
		 * @param authorizedByName
		 * @param status
		 * @param reason
		 * @param waver
		 * @param supervisor1name
		 * @param transFlag
		 * @param waiveredBy
		 * @param absent
		 * @param statusForSort
		 * @param date
		 * @param supervisor2name
		 * @param waverId
		 * @param employeeName
		 * @param isabsent
		 * @param time
		 * @param attendanceId
		 * @param supervisor1email
		 * @param supervisor2email
		 * @param email
		 * @param dateForSort
		 * @param supervisor1id
		 * @param supervisor2id
		 * @param waverCancelled
		 * @param comments
		 * @param authorizedById
		 */

		public LateComingRecord(@JsonProperty("attendanceId") Integer attendanceId,
				@JsonProperty("employeeId") Object employeeId, @JsonProperty("employeeName") String employeeName,
				@JsonProperty("date") Integer date, @JsonProperty("supervisor1id") Object supervisor1id,
				@JsonProperty("supervisor1name") Object supervisor1name,
				@JsonProperty("supervisor1email") Object supervisor1email,
				@JsonProperty("supervisor2id") Object supervisor2id,
				@JsonProperty("supervisor2name") String supervisor2name,
				@JsonProperty("supervisor2email") Object supervisor2email, @JsonProperty("waverId") Integer waverId,
				@JsonProperty("waver") Integer waver, @JsonProperty("time") String time,
				@JsonProperty("reason") Object reason, @JsonProperty("absent") String absent,
				@JsonProperty("mgrComment") Object mgrComment, @JsonProperty("status") String status,
				@JsonProperty("waiveredBy") Object waiveredBy, @JsonProperty("dateForSort") Object dateForSort,
				@JsonProperty("statusForSort") String statusForSort, @JsonProperty("transFlag") Object transFlag,
				@JsonProperty("isApplied") Object isApplied, @JsonProperty("comments") Object comments,
				@JsonProperty("email") Object email, @JsonProperty("authorizedById") Object authorizedById,
				@JsonProperty("authorizedByName") Object authorizedByName,
				@JsonProperty("waverCancelled") Object waverCancelled, @JsonProperty("isabsent") Object isabsent) {
			super();
			this.attendanceId = attendanceId;
			this.employeeId = employeeId;
			this.employeeName = employeeName;
			this.date = date;
			this.supervisor1id = supervisor1id;
			this.supervisor1name = supervisor1name;
			this.supervisor1email = supervisor1email;
			this.supervisor2id = supervisor2id;
			this.supervisor2name = supervisor2name;
			this.supervisor2email = supervisor2email;
			this.waverId = waverId;
			this.waver = waver;
			this.time = time;
			this.reason = reason;
			this.absent = absent;
			this.mgrComment = mgrComment;
			this.status = status;
			this.waiveredBy = waiveredBy;
			this.dateForSort = dateForSort;
			this.statusForSort = statusForSort;
			this.transFlag = transFlag;
			this.isApplied = isApplied;
			this.comments = comments;
			this.email = email;
			this.authorizedById = authorizedById;
			this.authorizedByName = authorizedByName;
			this.waverCancelled = waverCancelled;
			this.isabsent = isabsent;
		}

		@JsonProperty("attendanceId")
		public Integer getAttendanceId() {
			return attendanceId;
		}

		@JsonProperty("attendanceId")
		public void setAttendanceId(Integer attendanceId) {
			this.attendanceId = attendanceId;
		}

		public LateComingRecord withAttendanceId(Integer attendanceId) {
			this.attendanceId = attendanceId;
			return this;
		}

		@JsonProperty("employeeId")
		public Object getEmployeeId() {
			return employeeId;
		}

		@JsonProperty("employeeId")
		public void setEmployeeId(Object employeeId) {
			this.employeeId = employeeId;
		}

		public LateComingRecord withEmployeeId(Object employeeId) {
			this.employeeId = employeeId;
			return this;
		}

		@JsonProperty("employeeName")
		public String getEmployeeName() {
			return employeeName;
		}

		@JsonProperty("employeeName")
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}

		public LateComingRecord withEmployeeName(String employeeName) {
			this.employeeName = employeeName;
			return this;
		}

		@JsonProperty("date")
		public Integer getDate() {
			return date;
		}

		@JsonProperty("date")
		public void setDate(Integer date) {
			this.date = date;
		}

		public LateComingRecord withDate(Integer date) {
			this.date = date;
			return this;
		}

		@JsonProperty("supervisor1id")
		public Object getSupervisor1id() {
			return supervisor1id;
		}

		@JsonProperty("supervisor1id")
		public void setSupervisor1id(Object supervisor1id) {
			this.supervisor1id = supervisor1id;
		}

		public LateComingRecord withSupervisor1id(Object supervisor1id) {
			this.supervisor1id = supervisor1id;
			return this;
		}

		@JsonProperty("supervisor1name")
		public Object getSupervisor1name() {
			return supervisor1name;
		}

		@JsonProperty("supervisor1name")
		public void setSupervisor1name(Object supervisor1name) {
			this.supervisor1name = supervisor1name;
		}

		public LateComingRecord withSupervisor1name(Object supervisor1name) {
			this.supervisor1name = supervisor1name;
			return this;
		}

		@JsonProperty("supervisor1email")
		public Object getSupervisor1email() {
			return supervisor1email;
		}

		@JsonProperty("supervisor1email")
		public void setSupervisor1email(Object supervisor1email) {
			this.supervisor1email = supervisor1email;
		}

		public LateComingRecord withSupervisor1email(Object supervisor1email) {
			this.supervisor1email = supervisor1email;
			return this;
		}

		@JsonProperty("supervisor2id")
		public Object getSupervisor2id() {
			return supervisor2id;
		}

		@JsonProperty("supervisor2id")
		public void setSupervisor2id(Object supervisor2id) {
			this.supervisor2id = supervisor2id;
		}

		public LateComingRecord withSupervisor2id(Object supervisor2id) {
			this.supervisor2id = supervisor2id;
			return this;
		}

		@JsonProperty("supervisor2name")
		public String getSupervisor2name() {
			return supervisor2name;
		}

		@JsonProperty("supervisor2name")
		public void setSupervisor2name(String supervisor2name) {
			this.supervisor2name = supervisor2name;
		}

		public LateComingRecord withSupervisor2name(String supervisor2name) {
			this.supervisor2name = supervisor2name;
			return this;
		}

		@JsonProperty("supervisor2email")
		public Object getSupervisor2email() {
			return supervisor2email;
		}

		@JsonProperty("supervisor2email")
		public void setSupervisor2email(Object supervisor2email) {
			this.supervisor2email = supervisor2email;
		}

		public LateComingRecord withSupervisor2email(Object supervisor2email) {
			this.supervisor2email = supervisor2email;
			return this;
		}

		@JsonProperty("waverId")
		public Integer getWaverId() {
			return waverId;
		}

		@JsonProperty("waverId")
		public void setWaverId(Integer waverId) {
			this.waverId = waverId;
		}

		public LateComingRecord withWaverId(Integer waverId) {
			this.waverId = waverId;
			return this;
		}

		@JsonProperty("waver")
		public Integer getWaver() {
			return waver;
		}

		@JsonProperty("waver")
		public void setWaver(Integer waver) {
			this.waver = waver;
		}

		public LateComingRecord withWaver(Integer waver) {
			this.waver = waver;
			return this;
		}

		@JsonProperty("time")
		public String getTime() {
			return time;
		}

		@JsonProperty("time")
		public void setTime(String time) {
			this.time = time;
		}

		public LateComingRecord withTime(String time) {
			this.time = time;
			return this;
		}

		@JsonProperty("reason")
		public Object getReason() {
			return reason;
		}

		@JsonProperty("reason")
		public void setReason(Object reason) {
			this.reason = reason;
		}

		public LateComingRecord withReason(Object reason) {
			this.reason = reason;
			return this;
		}

		@JsonProperty("absent")
		public String getAbsent() {
			return absent;
		}

		@JsonProperty("absent")
		public void setAbsent(String absent) {
			this.absent = absent;
		}

		public LateComingRecord withAbsent(String absent) {
			this.absent = absent;
			return this;
		}

		@JsonProperty("mgrComment")
		public Object getMgrComment() {
			return mgrComment;
		}

		@JsonProperty("mgrComment")
		public void setMgrComment(Object mgrComment) {
			this.mgrComment = mgrComment;
		}

		public LateComingRecord withMgrComment(Object mgrComment) {
			this.mgrComment = mgrComment;
			return this;
		}

		@JsonProperty("status")
		public String getStatus() {
			return status;
		}

		@JsonProperty("status")
		public void setStatus(String status) {
			this.status = status;
		}

		public LateComingRecord withStatus(String status) {
			this.status = status;
			return this;
		}

		@JsonProperty("waiveredBy")
		public Object getWaiveredBy() {
			return waiveredBy;
		}

		@JsonProperty("waiveredBy")
		public void setWaiveredBy(Object waiveredBy) {
			this.waiveredBy = waiveredBy;
		}

		public LateComingRecord withWaiveredBy(Object waiveredBy) {
			this.waiveredBy = waiveredBy;
			return this;
		}

		@JsonProperty("dateForSort")
		public Object getDateForSort() {
			return dateForSort;
		}

		@JsonProperty("dateForSort")
		public void setDateForSort(Object dateForSort) {
			this.dateForSort = dateForSort;
		}

		public LateComingRecord withDateForSort(Object dateForSort) {
			this.dateForSort = dateForSort;
			return this;
		}

		@JsonProperty("statusForSort")
		public String getStatusForSort() {
			return statusForSort;
		}

		@JsonProperty("statusForSort")
		public void setStatusForSort(String statusForSort) {
			this.statusForSort = statusForSort;
		}

		public LateComingRecord withStatusForSort(String statusForSort) {
			this.statusForSort = statusForSort;
			return this;
		}

		@JsonProperty("transFlag")
		public Object getTransFlag() {
			return transFlag;
		}

		@JsonProperty("transFlag")
		public void setTransFlag(Object transFlag) {
			this.transFlag = transFlag;
		}

		public LateComingRecord withTransFlag(Object transFlag) {
			this.transFlag = transFlag;
			return this;
		}

		@JsonProperty("isApplied")
		public Object getIsApplied() {
			return isApplied;
		}

		@JsonProperty("isApplied")
		public void setIsApplied(Object isApplied) {
			this.isApplied = isApplied;
		}

		public LateComingRecord withIsApplied(Object isApplied) {
			this.isApplied = isApplied;
			return this;
		}

		@JsonProperty("comments")
		public Object getComments() {
			return comments;
		}

		@JsonProperty("comments")
		public void setComments(Object comments) {
			this.comments = comments;
		}

		public LateComingRecord withComments(Object comments) {
			this.comments = comments;
			return this;
		}

		@JsonProperty("email")
		public Object getEmail() {
			return email;
		}

		@JsonProperty("email")
		public void setEmail(Object email) {
			this.email = email;
		}

		public LateComingRecord withEmail(Object email) {
			this.email = email;
			return this;
		}

		@JsonProperty("authorizedById")
		public Object getAuthorizedById() {
			return authorizedById;
		}

		@JsonProperty("authorizedById")
		public void setAuthorizedById(Object authorizedById) {
			this.authorizedById = authorizedById;
		}

		public LateComingRecord withAuthorizedById(Object authorizedById) {
			this.authorizedById = authorizedById;
			return this;
		}

		@JsonProperty("authorizedByName")
		public Object getAuthorizedByName() {
			return authorizedByName;
		}

		@JsonProperty("authorizedByName")
		public void setAuthorizedByName(Object authorizedByName) {
			this.authorizedByName = authorizedByName;
		}

		public LateComingRecord withAuthorizedByName(Object authorizedByName) {
			this.authorizedByName = authorizedByName;
			return this;
		}

		@JsonProperty("waverCancelled")
		public Object getWaverCancelled() {
			return waverCancelled;
		}

		@JsonProperty("waverCancelled")
		public void setWaverCancelled(Object waverCancelled) {
			this.waverCancelled = waverCancelled;
		}

		public LateComingRecord withWaverCancelled(Object waverCancelled) {
			this.waverCancelled = waverCancelled;
			return this;
		}

		@JsonProperty("isabsent")
		public Object getIsabsent() {
			return isabsent;
		}

		@JsonProperty("isabsent")
		public void setIsabsent(Object isabsent) {
			this.isabsent = isabsent;
		}

		public LateComingRecord withIsabsent(Object isabsent) {
			this.isabsent = isabsent;
			return this;
		}

	}

}
