package com.crm.currency.model.bo;

import com.crm.currency.model.bo.base.BaseInputBo;
import org.apache.commons.lang3.StringUtils;
import com.crm.currency.util.LogUtil;

public class GetCurrencyInput extends BaseInputBo{
	private String engName;
	
	
	public String getEngName() {
		return engName;
	}


	public void setEngName(String engName) {
		this.engName = engName;
	}


	@Override
	public boolean isValid() {
		boolean isValid = true;
		if (StringUtils.isEmpty(this.engName)) {
			isValid = false;
			LogUtil.error(this.getClass(), "input 缺少幣別名稱");
		}
		
		return isValid;
	}
	
}
