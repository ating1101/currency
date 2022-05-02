package com.crm.currency.service.bo;

import com.crm.currency.model.bo.base.BaseOutputBo;
import com.crm.currency.mware.bo.ExchangeRate;

public class GetRealExRateInfoOutput extends BaseOutputBo {
	private ExchangeRate realTimeExRate;

	public ExchangeRate getRealTimeExRate() {
		return realTimeExRate;
	}

	public void setRealTimeExRate(ExchangeRate realTimeExRate) {
		this.realTimeExRate = realTimeExRate;
	}

}
