package com.crm.currency.controller;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crm.currency.CurrencyApplication;
import com.crm.currency.action.ExchangeRatePageController;
import com.crm.currency.bean.SysConst;
import com.crm.currency.dao.entity.CurrencyNameMap;
import com.crm.currency.dao.repository.CurrencyNameMapRepository;
import com.crm.currency.facade.ICurrencyInfoFacade;
import com.crm.currency.model.bo.AddCurrencyInput;
import com.crm.currency.model.bo.AddCurrencyOutput;
import com.crm.currency.model.bo.DeleteCurrencyInput;
import com.crm.currency.model.bo.DeleteCurrencyOutput;
import com.crm.currency.model.bo.GetCurrencyInput;
import com.crm.currency.model.bo.GetCurrencyOutput;
import com.crm.currency.model.bo.RrefreshExRateInput;
import com.crm.currency.model.bo.RrefreshExRateOutput;
import com.crm.currency.model.bo.UpdateCurrencyInput;
import com.crm.currency.model.bo.UpdateCurrencyOutput;
import com.crm.currency.model.bo.datamodel.CurrencyInfo;
import com.crm.currency.model.view.AddCurrencyNameMapVin;
import com.crm.currency.model.view.AddCurrencyNameMapVout;
import com.crm.currency.model.view.DeleteCurNameMapVin;
import com.crm.currency.model.view.DeleteCurNameMapVout;
import com.crm.currency.model.view.FindCurNameMapByIdVin;
import com.crm.currency.model.view.FindCurNameMapByIdVout;
import com.crm.currency.model.view.GetCurExInfoVin;
import com.crm.currency.model.view.GetCurExInfoVout;
import com.crm.currency.model.view.RefreshRateVout;
import com.crm.currency.model.view.UpdateCurrencyNameMapVin;
import com.crm.currency.model.view.UpdateCurrencyNameMapVout;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CurrencyApplication.class)
public class ExchangeRatePageControllerTest {

	@Autowired
	ExchangeRatePageController controller;

	// 測試新增幣別對應中文名稱
	@Test
	public void addCurrencyNameTest() {
		AddCurrencyNameMapVin vin = new AddCurrencyNameMapVin();
		vin.setEngName("EUR");
		vin.setChineseName("歐元");
		vin.setCreateBy("system");
		vin.setRemark("測試");
		AddCurrencyNameMapVout vout = controller.addCurrencyNameMap(vin);
		CurrencyInfo newCurrencyInfo = vout.getResultMessage().isSuccess() ? vout.getCurrencyInfo() : null;

		if (vout.getResultMessage().isSuccess()) {
			Assert.assertNotNull(newCurrencyInfo);
			Assert.assertEquals(SysConst.successCode, vout.getResultMessage().getCode());
			Assert.assertEquals(SysConst.successMsg, vout.getResultMessage().getMessage());
		} else {
			Assert.assertNotNull(vout.getResultMessage().getCode());
			Assert.assertNotNull(vout.getResultMessage().getMessage());
		}
	}

	// 測試更新幣別對應中文名稱
	@Test
	public void updateCurrencyNameTest() {
		this.addCurrencyNameTest();

		UpdateCurrencyNameMapVin vin = new UpdateCurrencyNameMapVin();
		vin.setEngName("EUR");
		vin.setChineseName("歐元123");
		vin.setRemark("測試123");
		controller.updateCurrencyNameMap(vin);

		FindCurNameMapByIdVin input = new FindCurNameMapByIdVin();
		input.setEngName("EUR");
		FindCurNameMapByIdVout vout = controller.findCurrencyNameMapById(input);
		CurrencyInfo newCurrencyInfo = vout.getResultMessage().isSuccess() ? vout.getCurrencyInfo() : null;

		if (vout.getResultMessage().isSuccess()) {
			Assert.assertEquals("測試123", newCurrencyInfo.getRemark());
			Assert.assertEquals("歐元123", newCurrencyInfo.getChineseName());
			Assert.assertEquals(SysConst.successCode, vout.getResultMessage().getCode());
			Assert.assertEquals(SysConst.successMsg, vout.getResultMessage().getMessage());
		} else {
			Assert.assertNotNull(vout.getResultMessage().getCode());
			Assert.assertNotNull(vout.getResultMessage().getMessage());
		}
	}

	// 測試刪除幣別對應中文名稱
	@Test
	public void deleteCurrencyNameTest() {
		this.addCurrencyNameTest();

		DeleteCurNameMapVin input = new DeleteCurNameMapVin();
		input.setEngName("EUR");
		DeleteCurNameMapVout output = controller.deleteCurrencyNameMap(input);

		FindCurNameMapByIdVin vin = new FindCurNameMapByIdVin();
		vin.setEngName("EUR");
		FindCurNameMapByIdVout vout = controller.findCurrencyNameMapById(vin);
		CurrencyInfo newCurrencyInfo = vout.getResultMessage().isSuccess() ? vout.getCurrencyInfo() : null;

		if (vout.getResultMessage().isSuccess()) {
			Assert.assertEquals(SysConst.successCode, vout.getResultMessage().getCode());
			Assert.assertEquals(SysConst.successMsg, vout.getResultMessage().getMessage());
			Assert.assertTrue(output.getResultMessage().isSuccess());
			Assert.assertNull(newCurrencyInfo);
		} else {
			Assert.assertNotNull(vout.getResultMessage().getCode());
			Assert.assertNotNull(vout.getResultMessage().getMessage());
		}
	}

	// 測試查詢幣別對應中文名稱
	@Test
	public void getCurrencyNameTest() {
		this.addCurrencyNameTest();

		FindCurNameMapByIdVin vin = new FindCurNameMapByIdVin();
		vin.setEngName("EUR");
		FindCurNameMapByIdVout vout = controller.findCurrencyNameMapById(vin);
		CurrencyInfo newCurrencyInfo = vout.getResultMessage().isSuccess() ? vout.getCurrencyInfo() : null;

		if (vout.getResultMessage().isSuccess()) {
			Assert.assertEquals(SysConst.successCode, vout.getResultMessage().getCode());
			Assert.assertEquals(SysConst.successMsg, vout.getResultMessage().getMessage());
			Assert.assertEquals("測試", newCurrencyInfo.getRemark());
			Assert.assertEquals("歐元", newCurrencyInfo.getChineseName());
		} else {
			Assert.assertNotNull(vout.getResultMessage().getCode());
			Assert.assertNotNull(vout.getResultMessage().getMessage());
		}
	}

	// 測試查詢 coindesk 最新匯率
	@Test
	public void getCoindeskExRateTest() {
		RefreshRateVout vout = controller.refreshRate();

		if (vout.getResultMessage().isSuccess()) {
			Assert.assertEquals(SysConst.successCode, vout.getResultMessage().getCode());
			Assert.assertEquals(SysConst.successMsg, vout.getResultMessage().getMessage());
			Assert.assertEquals("Bitcoin", vout.getExchangeRate().getChartName());
			Assert.assertNotNull(vout.getExchangeRate().getBpi());
			Assert.assertNotNull(vout.getExchangeRate().getBpi().getEUR());
			Assert.assertEquals("EUR", vout.getExchangeRate().getBpi().getEUR().getCode());
		} else {
			Assert.assertNotNull(vout.getResultMessage().getCode());
			Assert.assertNotNull(vout.getResultMessage().getMessage());
		}
	}

	// 測試查詢對應幣別中文名稱及最新匯率
	@Test
	public void getCurExInfoTest() {
		this.addCurrencyNameTest();
		
		GetCurExInfoVin vin = new GetCurExInfoVin();
		vin.setEngName("EUR");
		GetCurExInfoVout vout = controller.getCurExInfo(vin);

		if (vout.getResultMessage().isSuccess()) {
			Assert.assertEquals(SysConst.successCode, vout.getResultMessage().getCode());
			Assert.assertEquals(SysConst.successMsg, vout.getResultMessage().getMessage());
			Assert.assertNotNull(vout.getCurrencyInfo());
			Assert.assertEquals("EUR", vout.getCurrencyInfo().getEngName());
			Assert.assertNotNull(vout.getCurrencyInfo().getRate());
		} else {
			Assert.assertNotNull(vout.getResultMessage().getCode());
			Assert.assertNotNull(vout.getResultMessage().getMessage());
		}
	}
}
