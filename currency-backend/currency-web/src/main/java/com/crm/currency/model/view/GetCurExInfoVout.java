package com.crm.currency.model.view;

import com.crm.currency.model.bo.datamodel.CurrencyInfo;
import com.crm.currency.model.view.base.BaseVout;

public class GetCurExInfoVout extends BaseVout {
	private CurrencyInfo currencyInfo;

	public CurrencyInfo getCurrencyInfo() {
		return currencyInfo;
	}

	public void setCurrencyInfo(CurrencyInfo currencyInfo) {
		this.currencyInfo = currencyInfo;
	}
}
