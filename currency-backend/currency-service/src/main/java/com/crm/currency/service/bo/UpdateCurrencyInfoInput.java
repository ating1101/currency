package com.crm.currency.service.bo;

import org.apache.commons.lang3.StringUtils;

import com.crm.currency.dao.entity.CurrencyNameMap;
import com.crm.currency.model.bo.base.BaseInputBo;
import com.crm.currency.util.LogUtil;

public class UpdateCurrencyInfoInput extends BaseInputBo{
	private String engName;
	private String chineseName;
	private String updateBy;
	private String remark;;
	private CurrencyNameMap oriCurNameMap;
	
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
	

	public CurrencyNameMap getOriCurNameMap() {
		return oriCurNameMap;
	}

	public void setOriCurNameMap(CurrencyNameMap oriCurNameMap) {
		this.oriCurNameMap = oriCurNameMap;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public boolean isValid() {
		boolean isValid = true;
		if (StringUtils.isEmpty(this.engName)) {
			isValid = false;
			LogUtil.error(this.getClass(), "UpdateCurrencyInfoInput 缺少幣別名稱");
		}
		if (StringUtils.isEmpty(this.chineseName)) {
			isValid = false;
			LogUtil.error(this.getClass(), "UpdateCurrencyInfoInput 缺少中文名稱");
		}
		if (StringUtils.isEmpty(this.updateBy)) {
			isValid = false;
			LogUtil.error(this.getClass(), "UpdateCurrencyInfoInput 缺少更新幣別者");
		}
		if (this.oriCurNameMap == null) {
			isValid = false;
			LogUtil.error(this.getClass(), "UpdateCurrencyInfoInput 缺少原幣別資料");
		}
		return isValid;
	}
}
