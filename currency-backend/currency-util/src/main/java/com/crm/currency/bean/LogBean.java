package com.crm.currency.bean;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.ThreadContext;

public class LogBean {
	// Log 產出時間
	private String logTimeStamp;
	// 系統執行 Server Name
	private String host;
	// 系統執行 Server IP Address
	private String ip;
	// Log 輸出層級
	private String level;
	// 系統名稱
	private String systemName;
	// JVM thread name, 每個 Request 一個
	private String threadName;
	// 系統交易序號, 取 FetnetSessionId 每個登入一個
	private String sessionID;
	// 系統執行程式名稱
	private String pgName;

	/*
	 * 以下為自定義欄位
	 */

	// 頁面 URL
	private String pageUrl;
	// 訊息
	private String message;
	// 錯誤代碼
	private String errorCode;
	// Exception 內容
	private String stackTrace;
	// 紀錄 Log 的行數 (class 和 function 記錄在 pgName)
	private int pgLineNumber;

	public LogBean(Object messageObj, String errorCode, Throwable te, String level) {
		// stackTrace 要排除的檔案名稱
		final String[] IGNORE_FILES = { "Thread.java", "ElkLogBean.java", "BaseLogHandler.java", "LogUtil.java" };

		try {
			// 依參數改變的欄位
			this.errorCode = errorCode;
			this.level = level;
			if (messageObj != null) {
				this.message = messageObj.toString();
			}
			if (te != null) {
				this.stackTrace = ExceptionUtils.getMessage(te) + ExceptionUtils.getStackTrace(te);
			}

			// Thread 或 System 固定欄位
			this.threadName = Thread.currentThread().getName();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			this.logTimeStamp = sdf.format(new Date());

			InetAddress addr = InetAddress.getLocalHost();
			this.ip = addr.getHostAddress();
			this.host = addr.getHostName();
			this.systemName = ThreadContext.get("systemName");
			this.pageUrl = ThreadContext.get("pageUrl");
			this.sessionID = ThreadContext.get("sessionId");

			// 取得呼叫 Log 的位置
			StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
			List<String> ignoreFiles = Arrays.asList(IGNORE_FILES);
			for (StackTraceElement stackTrace : stackTraces) {
				if (!ignoreFiles.contains(stackTrace.getFileName())) {
					this.pgName = stackTrace.getFileName().replace(".java", "") + "." + stackTrace.getMethodName();
					this.pgLineNumber = stackTrace.getLineNumber();
					break;
				}
			}

		} catch (Exception e) {
			// Log 出錯不做任何處裡
			System.out.println(ExceptionUtils.getMessage(e));
		}
	}


	public String getLogTimeStamp() {
		return logTimeStamp;
	}

	public void setLogTimeStamp(String logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getPgName() {
		return pgName;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public int getPgLineNumber() {
		return pgLineNumber;
	}

	public void setPgLineNumber(int pgLineNumber) {
		this.pgLineNumber = pgLineNumber;
	}

}