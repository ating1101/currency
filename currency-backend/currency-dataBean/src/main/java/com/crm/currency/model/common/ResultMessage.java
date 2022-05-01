package com.crm.currency.model.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class ResultMessage implements Serializable {

	/**
	 * 檢核結果Message資訊
	 */
	private static final long serialVersionUID = 1L;

	private boolean success;
	private String message = "";
	private String code;
	private String warning;

	private Set<String> promptMsgs; // 提示訊息
	private Set<String> confirmMsgs; // 確認訊息

	public ResultMessage() {
		promptMsgs = new HashSet<String>();
		confirmMsgs = new HashSet<String>();
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public ResultMessage(String errorMsg) {
		this.message = errorMsg;
	}

	public ResultMessage(boolean isSuccess, String code, String message) {
		this.success = isSuccess;
		this.message = message;
		this.code = code;
	}

	public ResultMessage(boolean isSuccess, String message) {
		this.success = isSuccess;
		this.message = message;
	}

	public ResultMessage(boolean isSuccess) {
		this.success = isSuccess;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean isSuccess) {
		this.success = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// 加入提示訊息
	public void addPromptMsgs(String promptMsg) {
		if (promptMsgs == null) {
			this.promptMsgs = new HashSet<String>();
		}
		if (StringUtils.isNotBlank(promptMsg)) {
			promptMsgs.add(promptMsg);
		}
	}

	// 加入提示訊息
	public void addPromptMsgs(Collection<String> promptMsg) {
		if (promptMsgs == null) {
			this.promptMsgs = new HashSet<String>();
		}
		if (promptMsg != null && promptMsg.size() > 0) {
			promptMsgs.addAll(promptMsg);
		}
	}

	// 加入確認訊息
	public void addConfirmMsgs(String confirmMsg) {
		if (confirmMsgs == null) {
			this.confirmMsgs = new HashSet<String>();
		}
		if (StringUtils.isNotBlank(confirmMsg)) {
			confirmMsgs.add(confirmMsg);
		}
	}

	// 加入確認訊息
	public void addConfirmMsgs(Collection<String> confirmMsg) {
		if (confirmMsgs == null) {
			this.confirmMsgs = new HashSet<String>();
		}
		if (confirmMsg != null && confirmMsg.size() > 0) {
			confirmMsgs.addAll(confirmMsg);
		}
	}

	// 刪除所有確認訊息
	public void removeAllConfirmMsgs() {
		confirmMsgs = null;
	}

	public Set<String> getPromptMsgs() {
		return promptMsgs;
	}

	public void setPromptMsgs(HashSet<String> promptMsgs) {
		this.promptMsgs = promptMsgs;
	}

	public Set<String> getConfirmMsgs() {
		return confirmMsgs;
	}

	public void setConfirmMsgs(HashSet<String> confirmMsgs) {
		this.confirmMsgs = confirmMsgs;
	}

	public void setPromptMsgs(Set<String> promptMsgs) {
		this.promptMsgs = promptMsgs;
	}

	public void setConfirmMsgs(Set<String> confirmMsgs) {
		this.confirmMsgs = confirmMsgs;
	}

	@Override
	public String toString() {
		return "ResultMessage [success=" + success + ", message=" + message + ", code=" + code + "]";
	}

}
