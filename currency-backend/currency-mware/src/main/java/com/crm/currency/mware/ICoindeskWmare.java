package com.crm.currency.mware;


import com.crm.currency.mware.bo.GetExchangeRateInput;
import com.crm.currency.mware.bo.GetExchangeRateOutput;

public interface ICoindeskWmare {
	GetExchangeRateOutput getExchangeRate(GetExchangeRateInput input) throws Exception;
}
