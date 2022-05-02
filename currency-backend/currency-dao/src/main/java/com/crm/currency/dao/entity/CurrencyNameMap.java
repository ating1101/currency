package com.crm.currency.dao.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Currency_Name_Map")
public class CurrencyNameMap {
	@Id
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	@Column(name = "CHINESE_NAME")
	private String chineseName;
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	@Column(name = "UPDATE_BY")
	private String updateBy;
	@Column(name = "REMARK")
	private String remark;

	public CurrencyNameMap() {
	}

	public CurrencyNameMap(String englishName, String chineseName, Date createDate, String createBy, Date updateDate,
			String updateBy, String remark) {
		this.englishName = englishName;
		this.chineseName = chineseName;
		this.createDate = createDate;
		this.createBy = createBy;
		this.updateDate = updateDate;
		this.updateBy = updateBy;
		this.remark = remark;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	@Override
	public String toString() {
		return "Currency_Name_Map [englishName=" + englishName + ", chineseName=" + chineseName + ", createDate="
				+ createDate + ", createBy=" + createBy + ", updateDate=" + updateDate + ", updateBy=" + updateBy
				+ ", remark=" + remark + "]";
	}

}
