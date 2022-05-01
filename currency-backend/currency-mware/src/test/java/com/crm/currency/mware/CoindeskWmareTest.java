package com.crm.currency.mware;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.currency.CurrencyApplication;
import com.crm.currency.mware.bo.GetExchangeRateInput;
import com.crm.currency.mware.bo.GetExchangeRateOutput;
import com.crm.currency.util.LogUtil;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CurrencyApplication.class)
public class CoindeskWmareTest {

    @Autowired
    ICoindeskWmare coindeskWmare;
    
	@Test
	public void getExchangeRate() {
		GetExchangeRateOutput output = null;
		try {
			GetExchangeRateInput input = new GetExchangeRateInput();
			output = coindeskWmare.getExchangeRate(input);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), "error", e);
		}

		Assert.assertNotNull(output.getExchangeRate());
		if (output.getExchangeRate() != null) {
			Assert.assertEquals(output.getExchangeRate().getChartName(), "Bitcoin");			
		}
	}
}
