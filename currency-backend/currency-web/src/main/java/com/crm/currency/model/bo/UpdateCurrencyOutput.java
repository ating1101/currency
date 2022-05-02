package com.crm.currency.model.bo;

import com.crm.currency.model.bo.base.BaseOutputBo;
import com.crm.currency.model.bo.datamodel.CurrencyInfo;

public class UpdateCurrencyOutput extends BaseOutputBo{
	private CurrencyInfo currencyInfo;

	public CurrencyInfo getCurrencyInfo() {
		return currencyInfo;
	}

	public void setCurrencyInfo(CurrencyInfo currencyInfo) {
		this.currencyInfo = currencyInfo;
	}
}
