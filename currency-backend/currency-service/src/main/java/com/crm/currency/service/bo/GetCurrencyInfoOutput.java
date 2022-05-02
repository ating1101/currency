package com.crm.currency.service.bo;

import com.crm.currency.dao.entity.CurrencyNameMap;
import com.crm.currency.model.bo.base.BaseOutputBo;
import com.crm.currency.model.bo.datamodel.CurrencyInfo;

public class GetCurrencyInfoOutput extends BaseOutputBo{
	private CurrencyInfo currencyInfo;
	private CurrencyNameMap currencyNameMap;

	public CurrencyInfo getCurrencyInfo() {
		return currencyInfo;
	}

	public void setCurrencyInfo(CurrencyInfo currencyInfo) {
		this.currencyInfo = currencyInfo;
	}

	public CurrencyNameMap getCurrencyNameMap() {
		return currencyNameMap;
	}

	public void setCurrencyNameMap(CurrencyNameMap currencyNameMap) {
		this.currencyNameMap = currencyNameMap;
	}

}
