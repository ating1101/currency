package com.crm.currency.mware.bo;

import com.crm.currency.model.bo.base.BaseOutputBo;

public class GetExchangeRateOutput extends BaseOutputBo {
	private ExchangeRate exchangeRate;

	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
}
