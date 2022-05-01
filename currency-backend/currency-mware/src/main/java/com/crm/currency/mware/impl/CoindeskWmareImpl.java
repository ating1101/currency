package com.crm.currency.mware.impl;

import javax.net.ssl.TrustManager;

import org.springframework.stereotype.Component;

import com.crm.currency.mware.ICoindeskWmare;
import com.crm.currency.mware.bo.ExchangeRate;
import com.crm.currency.mware.bo.GetExchangeRateInput;
import com.crm.currency.mware.bo.GetExchangeRateOutput;
import com.crm.currency.util.LogUtil;
import com.crm.currency.util.MwareHttpUtil;
import com.google.gson.Gson;

@Component
public class CoindeskWmareImpl implements TrustManager,  ICoindeskWmare {
	public GetExchangeRateOutput getExchangeRate(GetExchangeRateInput input) throws Exception {
		LogUtil.info(this.getClass(), "getExchangeRate start");
		GetExchangeRateOutput getExchangeRateOutput = new GetExchangeRateOutput();

		Gson gson = new Gson();
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

		// 2.call API
		MwareHttpUtil mwareHttpUtil = new MwareHttpUtil();
		String response = mwareHttpUtil.getResponseContent(url, "GET", null, null);
		LogUtil.info(this.getClass(), "getExchangeRate api return : " + response);
		ExchangeRate res = gson.fromJson(response, ExchangeRate.class);
		
		if (res != null) {
			getExchangeRateOutput.setSuccess(true);
			getExchangeRateOutput.setExchangeRate(res);
		} else {
			getExchangeRateOutput.setSuccess(false);
			getExchangeRateOutput.setReturnMessage("getExchagneRateInput fail");
		}

		LogUtil.info(this.getClass(), "getExchangeRate end");
		return getExchangeRateOutput;
	}
}
