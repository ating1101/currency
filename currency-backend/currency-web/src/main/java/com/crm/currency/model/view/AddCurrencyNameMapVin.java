package com.crm.currency.model.view;

import com.crm.currency.model.view.base.BaseVin;

public class AddCurrencyNameMapVin extends BaseVin {
	private String engName;
	private String chineseName;
	private String createBy;
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
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
