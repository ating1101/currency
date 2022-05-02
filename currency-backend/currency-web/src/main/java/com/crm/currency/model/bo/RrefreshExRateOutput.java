package com.crm.currency.model.bo;

import com.crm.currency.model.bo.base.BaseOutputBo;
import com.crm.currency.mware.bo.ExchangeRate;

public class RrefreshExRateOutput extends BaseOutputBo {
	private ExchangeRate exchangeRate;

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
}
