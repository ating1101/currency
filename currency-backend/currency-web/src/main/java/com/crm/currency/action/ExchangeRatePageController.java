package com.crm.currency.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.currency.bean.SysConst;
import com.crm.currency.facade.ICurrencyInfoFacade;
import com.crm.currency.model.bo.AddCurrencyInput;
import com.crm.currency.model.bo.AddCurrencyOutput;
import com.crm.currency.model.bo.DeleteCurrencyInput;
import com.crm.currency.model.bo.DeleteCurrencyOutput;
import com.crm.currency.model.bo.GetCurExInfoInput;
import com.crm.currency.model.bo.GetCurExInfoOutput;
import com.crm.currency.model.bo.GetCurrencyInput;
import com.crm.currency.model.bo.GetCurrencyOutput;
import com.crm.currency.model.bo.RrefreshExRateInput;
import com.crm.currency.model.bo.RrefreshExRateOutput;
import com.crm.currency.model.bo.UpdateCurrencyInput;
import com.crm.currency.model.bo.UpdateCurrencyOutput;
import com.crm.currency.model.common.ResultMessage;
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

@RestController
@RequestMapping(value = "/API/ExchangeRatePagenController")
public class ExchangeRatePageController extends BaseController {

	@Autowired
	ICurrencyInfoFacade currencyInfoFacade;


	// 新增對應幣別中文名稱對應資訊
	@RequestMapping(value = "/addCurrencyInfo.action", method = RequestMethod.POST)
	public AddCurrencyNameMapVout addCurrencyNameMap(@RequestBody AddCurrencyNameMapVin vin) {
		AddCurrencyNameMapVout vout = new AddCurrencyNameMapVout();

		AddCurrencyInput facadeInput = new AddCurrencyInput();
		facadeInput.setEngName(vin.getEngName());
		facadeInput.setChineseName(vin.getChineseName());
		// 未實做登入系統，先預設放系統建立
		facadeInput.setCreateBy("system");
		AddCurrencyOutput facadeOutput = currencyInfoFacade.addCurrency(facadeInput);

		ResultMessage resultMessage;
		if (facadeOutput.isSuccess()) {
			resultMessage = new ResultMessage(true, SysConst.successCode, SysConst.successMsg);
			vout.setCurrencyInfo(facadeOutput.getCurrencyInfo());
		} else {
			resultMessage = new ResultMessage(false, facadeOutput.getReturnCode(), facadeOutput.getReturnMessage());
		}

		vout.setResultMessage(resultMessage);

		return vout;
	}

	// 更新對應幣別中文名稱對應資訊
	@RequestMapping(value = "/updateCurrencyInfo.action", method = RequestMethod.POST)
	public UpdateCurrencyNameMapVout updateCurrencyNameMap(@RequestBody UpdateCurrencyNameMapVin vin) {
		UpdateCurrencyNameMapVout vout = new UpdateCurrencyNameMapVout();

		UpdateCurrencyInput facadeInput = new UpdateCurrencyInput();
		facadeInput.setEngName(vin.getEngName());
		facadeInput.setChineseName(vin.getChineseName());
		facadeInput.setUpdateBy(vin.getUpdateBy());
		facadeInput.setRemark(vin.getRemark());
		// 未實做登入系統，先預設放系統建立
		facadeInput.setUpdateBy("system");
		UpdateCurrencyOutput facadeOutput = currencyInfoFacade.updateCurrency(facadeInput);

		ResultMessage resultMessage;
		if (facadeOutput.isSuccess()) {
			resultMessage = new ResultMessage(true, SysConst.successCode, SysConst.successMsg);
			vout.setCurrencyInfo(facadeOutput.getCurrencyInfo());
		} else {
			resultMessage = new ResultMessage(false, facadeOutput.getReturnCode(), facadeOutput.getReturnMessage());
		}

		vout.setResultMessage(resultMessage);

		return vout;
	}

	// 取得對應幣別中文名稱對應資訊
	@RequestMapping(value = "/getCurrencyInfo.action", method = RequestMethod.POST)
	public FindCurNameMapByIdVout findCurrencyNameMapById(@RequestBody FindCurNameMapByIdVin vin) {
		FindCurNameMapByIdVout vout = new FindCurNameMapByIdVout();

		GetCurrencyInput facadeInput = new GetCurrencyInput();
		facadeInput.setEngName(vin.getEngName());
		GetCurrencyOutput facadeOutput = currencyInfoFacade.getCurrency(facadeInput);

		ResultMessage resultMessage;
		if (facadeOutput.isSuccess()) {
			resultMessage = new ResultMessage(true, SysConst.successCode, SysConst.successMsg);
			vout.setCurrencyInfo(facadeOutput.getCurrencyInfo());
		} else {
			resultMessage = new ResultMessage(false, facadeOutput.getReturnCode(), facadeOutput.getReturnMessage());
		}

		vout.setResultMessage(resultMessage);

		return vout;
	}

	// 刪除對應幣別中文名稱對應資訊
	@RequestMapping(value = "/deleteCurrencyInfo.action", method = RequestMethod.POST)
	public DeleteCurNameMapVout deleteCurrencyNameMap(@RequestBody DeleteCurNameMapVin vin) {
		DeleteCurNameMapVout vout = new DeleteCurNameMapVout();

		DeleteCurrencyInput facadeInput = new DeleteCurrencyInput();
		facadeInput.setEngName(vin.getEngName());
		DeleteCurrencyOutput facadeOutput = currencyInfoFacade.deleteCurrency(facadeInput);

		ResultMessage resultMessage;
		if (facadeOutput.isSuccess()) {
			resultMessage = new ResultMessage(true, SysConst.successCode, SysConst.successMsg);
		} else {
			resultMessage = new ResultMessage(false, facadeOutput.getReturnCode(), facadeOutput.getReturnMessage());
		}

		vout.setResultMessage(resultMessage);

		return vout;
	}

	// 從 coindesk 取得最新匯率
	@RequestMapping(value = "/refreshExRate.action", method = RequestMethod.POST)
	public RefreshRateVout refreshRate() {
		RefreshRateVout vout = new RefreshRateVout();
		String returnMessage = "";
		String returnCode = "";

		RrefreshExRateInput facadeInput = new RrefreshExRateInput();
		RrefreshExRateOutput facadeOutput = currencyInfoFacade.refreshExRate(facadeInput);

		// 組output
		ResultMessage resultMessage;
		if (facadeOutput.isSuccess()) {
			resultMessage = new ResultMessage(true, SysConst.successCode, SysConst.successMsg);
			vout.setExchangeRate(facadeOutput.getExchangeRate());
		} else {
			resultMessage = new ResultMessage(false, returnCode, returnMessage);
		}
		vout.setResultMessage(resultMessage);

		return vout;
	}

	// 取得對應幣別中文名稱及即時匯率資訊
	@RequestMapping(value = "/getCurExInfo.action", method = RequestMethod.POST)
	public GetCurExInfoVout getCurExInfo(@RequestBody GetCurExInfoVin vin) {
		GetCurExInfoVout vout = new GetCurExInfoVout();
		String returnMessage = "";
		String returnCode = "";

		GetCurExInfoInput facadeInput = new GetCurExInfoInput();
		facadeInput.setEngName(vin.getEngName());
		GetCurExInfoOutput facadeOutput = currencyInfoFacade.getCurExInfo(facadeInput);

		// 組output
		ResultMessage resultMessage;
		if (facadeOutput.isSuccess()) {
			resultMessage = new ResultMessage(true, SysConst.successCode, SysConst.successMsg);
			vout.setCurrencyInfo(facadeOutput.getCurrencyInfo());
		} else {
			resultMessage = new ResultMessage(false, returnCode, returnMessage);
		}
		vout.setResultMessage(resultMessage);

		return vout;
	}
}
