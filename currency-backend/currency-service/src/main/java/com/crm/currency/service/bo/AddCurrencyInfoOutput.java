package com.crm.currency.service.bo;

import com.crm.currency.model.bo.base.BaseOutputBo;
import com.crm.currency.model.bo.datamodel.CurrencyInfo;

public class AddCurrencyInfoOutput extends BaseOutputBo {
	private CurrencyInfo currencyInfo;

	public CurrencyInfo getCurrencyInfo() {
		return currencyInfo;
	}

	public void setCurrencyInfo(CurrencyInfo currencyInfo) {
		this.currencyInfo = currencyInfo;
	}

}