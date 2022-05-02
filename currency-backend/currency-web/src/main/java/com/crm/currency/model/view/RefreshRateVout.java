package com.crm.currency.model.view;

import com.crm.currency.model.view.base.BaseVout;
import com.crm.currency.mware.bo.ExchangeRate;

public class RefreshRateVout extends BaseVout {
	private ExchangeRate exchangeRate;

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
}
