package com.crm.currency.service.bo;

import org.apache.commons.lang3.StringUtils;

import com.crm.currency.dao.entity.CurrencyNameMap;
import com.crm.currency.model.bo.base.BaseInputBo;
import com.crm.currency.util.LogUtil;

public class DeleteCurrencyInfoInput extends BaseInputBo {

	private CurrencyNameMap oriCurNameMap;

	public CurrencyNameMap getOriCurNameMap() {
		return oriCurNameMap;
	}

	public void setOriCurNameMap(CurrencyNameMap oriCurNameMap) {
		this.oriCurNameMap = oriCurNameMap;
	}

	@Override
	public boolean isValid() {
		boolean isValid = true;
		if (oriCurNameMap == null) {
			isValid = false;
			LogUtil.error(this.getClass(), "DeleteCurrencyInfoInput 缺少幣別資訊");
		}

		return isValid;
	}

}
