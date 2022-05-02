package com.crm.currency.model.view;

import com.crm.currency.model.view.base.BaseVin;

public class UpdateCurrencyNameMapVin extends BaseVin{
	private String engName;
	private String chineseName;
	private String updateBy;
	private String remark;
	
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
