package com.crm.currency.model.bo;

import org.apache.commons.lang3.StringUtils;

import com.crm.currency.model.bo.base.BaseInputBo;
import com.crm.currency.model.bo.datamodel.CurrencyInfo;
import com.crm.currency.util.LogUtil;

public class AddCurrencyInput extends BaseInputBo {
	private String engName;
	private String chineseName;
	private String createBy;

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

	@Override
	public boolean isValid() {
		boolean isValid = true;
		if (StringUtils.isEmpty(this.engName)) {
			isValid = false;
			LogUtil.error(this.getClass(), "AddCurrencyInfoInput 缺少幣別名稱");
		}
		if (StringUtils.isEmpty(this.chineseName)) {
			isValid = false;
			LogUtil.error(this.getClass(), "AddCurrencyInfoInput 缺少中文名稱");
		}
		if (StringUtils.isEmpty(this.createBy)) {
			isValid = false;
			LogUtil.error(this.getClass(), "AddCurrencyInfoInput 缺少建立幣別者");
		}
		return isValid;
	}

}
