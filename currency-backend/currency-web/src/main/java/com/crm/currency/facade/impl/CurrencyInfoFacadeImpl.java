package com.crm.currency.facade.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crm.currency.bean.SysConst;
import com.crm.currency.dao.entity.CurrencyNameMap;
import com.crm.currency.dao.repository.CurrencyNameMapRepository;
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
import com.crm.currency.model.bo.datamodel.CurrencyInfo;
import com.crm.currency.mware.bo.Bpi;
import com.crm.currency.mware.bo.CurrencyExchangeInfo;
import com.crm.currency.service.ICurrencyService;
import com.crm.currency.service.bo.AddCurrencyInfoInput;
import com.crm.currency.service.bo.AddCurrencyInfoOutput;
import com.crm.currency.service.bo.DeleteCurrencyInfoInput;
import com.crm.currency.service.bo.DeleteCurrencyInfoOutput;
import com.crm.currency.service.bo.GetCurrencyInfoInput;
import com.crm.currency.service.bo.GetCurrencyInfoOutput;
import com.crm.currency.service.bo.GetRealExRateInfoOutput;
import com.crm.currency.service.bo.GetRealExRateInput;
import com.crm.currency.service.bo.UpdateCurrencyInfoInput;
import com.crm.currency.service.bo.UpdateCurrencyInfoOutput;
import com.crm.currency.util.DateUtil;
import com.crm.currency.util.LogUtil;

@Component
public class CurrencyInfoFacadeImpl implements ICurrencyInfoFacade {

	@Autowired
	ICurrencyService currencyService;

	@Override
	public AddCurrencyOutput addCurrency(AddCurrencyInput input) {
		boolean goNext = true;
		String errCode = "";
		AddCurrencyOutput output = new AddCurrencyOutput();
		if (!input.isValid()) {
			goNext = false;
			errCode = "error-001-001";
			LogUtil.error(this.getClass(), errCode, "addCurrency input 驗證發生錯誤");
		}

		CurrencyInfo currencyInfo = null;
		if (goNext) {
			LogUtil.info(this.getClass(), "addCurrency 新增幣別中文名稱 start, 幣別名稱 : " + input.getEngName());
			try {
				AddCurrencyInfoInput serviceInput = new AddCurrencyInfoInput();
				serviceInput.setEngName(input.getEngName());
				serviceInput.setChineseName(input.getChineseName());
				serviceInput.setCreateBy(input.getCreateBy());
				AddCurrencyInfoOutput serviceOutput = currencyService.addCurrencyInfo(serviceInput);
				if (!serviceOutput.isSuccess()) {
					goNext = false;
					errCode = "error-001-002";
					LogUtil.error(this.getClass(), errCode, "addCurrency 新增幣別對應名稱失敗, 幣別名稱 : " + input.getEngName());
				} else {
					currencyInfo = serviceOutput.getCurrencyInfo();
				}
			} catch (Exception e) {
				goNext = false;
				errCode = "error-001-003";
				LogUtil.error(this.getClass(), errCode, "addCurrency 新增幣別對應名稱發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "addCurrencyInfo 新增幣別中文名稱 end");
		}

		if (goNext) {
			output.setSuccess(true);
			output.setCurrencyInfo(currencyInfo);
		} else {
			output.setSuccess(false);
			output.setReturnCode(errCode);
			output.setReturnMessage(String.format("系統發生錯誤, 請稍後再試 (%s)", errCode));
		}

		return output;
	}

	@Override
	public UpdateCurrencyOutput updateCurrency(UpdateCurrencyInput input) {
		boolean goNext = true;
		String errCode = "";
		UpdateCurrencyOutput output = new UpdateCurrencyOutput();
		if (!input.isValid()) {
			goNext = false;
			errCode = "error-002-001";
			LogUtil.error(this.getClass(), errCode, "updateCurrency input 驗證發生錯誤");
		}

		CurrencyNameMap currencyNameMap = null;
		if (goNext) {
			LogUtil.info(this.getClass(), "updateCurrency 查詢幣別中文名稱 start, 幣別名稱 : " + input.getEngName());
			try {
				GetCurrencyInfoInput serviceInput = new GetCurrencyInfoInput();
				serviceInput.setEngName(input.getEngName());
				GetCurrencyInfoOutput serviceOutput = currencyService.getCurrencyInfo(serviceInput);
				if (serviceOutput.isSuccess()) {
					if (serviceOutput.getCurrencyInfo() == null) {
						errCode = "error-003-002";
						LogUtil.warn(this.getClass(), errCode, "updateCurrency 查無幣別資料, 幣別名稱 : " + input.getEngName());
					} else {
						currencyNameMap = serviceOutput.getCurrencyNameMap();
					}
				} else if (!serviceOutput.isSuccess()) {
					errCode = "error-003-003";
					LogUtil.warn(this.getClass(), errCode, "updateCurrency 查詢幣別中文名稱回傳失敗, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				errCode = "error-003-004";
				LogUtil.error(this.getClass(), errCode, "updateCurrency 查詢幣別中文名稱發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "updateCurrency 查詢幣別中文名稱 end");
		}

		CurrencyInfo currencyInfo = null;
		if (goNext && currencyNameMap != null) {
			LogUtil.info(this.getClass(), "updateCurrency 更新幣別中文名稱 start, 幣別名稱 : " + input.getEngName());
			try {
				UpdateCurrencyInfoInput serviceInput = new UpdateCurrencyInfoInput();
				serviceInput.setEngName(input.getEngName());
				serviceInput.setChineseName(input.getChineseName());
				serviceInput.setUpdateBy(input.getUpdateBy());
				UpdateCurrencyInfoOutput serviceOutput = currencyService.updateCurrencyInfo(serviceInput);
				if (serviceOutput.isSuccess()) {
					currencyInfo = serviceOutput.getCurrencyInfo();
				} else {
					errCode = "error-002-005";
					LogUtil.warn(this.getClass(), errCode, "updateCurrency 更新幣別中文名稱回傳失敗, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				errCode = "error-002-006";
				LogUtil.error(this.getClass(), errCode, "updateCurrency 更新幣別對應名稱發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "updateCurrency 更新幣別中文名稱 end");
		}

		if (goNext) {
			output.setSuccess(true);
			output.setCurrencyInfo(currencyInfo);
		} else {
			output.setSuccess(false);
			output.setReturnCode(errCode);
			output.setReturnMessage(String.format("系統發生錯誤, 請稍後再試 (%s)", errCode));
		}

		return output;
	}

	@Override
	public GetCurrencyOutput getCurrency(GetCurrencyInput input) {
		boolean goNext = true;
		String errCode = "";
		GetCurrencyOutput output = new GetCurrencyOutput();
		if (!input.isValid()) {
			goNext = false;
			errCode = "error-003-001";
			LogUtil.error(this.getClass(), errCode, "getCurrency input 驗證發生錯誤");
		}

		CurrencyInfo currencyInfo = null;
		if (goNext) {
			LogUtil.info(this.getClass(), "getCurrency 查詢幣別中文名稱 start, 幣別名稱 : " + input.getEngName());
			try {
				GetCurrencyInfoInput serviceInput = new GetCurrencyInfoInput();
				serviceInput.setEngName(input.getEngName());
				GetCurrencyInfoOutput serviceOutput = currencyService.getCurrencyInfo(serviceInput);
				if (serviceOutput.isSuccess()) {
					if (serviceOutput.getCurrencyInfo() == null) {
						errCode = "error-003-002";
						LogUtil.warn(this.getClass(), errCode, "getCurrency 查無幣別資料, 幣別名稱 : " + input.getEngName());
					} else {
						currencyInfo = serviceOutput.getCurrencyInfo();
					}
				} else if (!serviceOutput.isSuccess()) {
					errCode = "error-003-003";
					LogUtil.warn(this.getClass(), errCode, "getCurrency 查詢幣別中文名稱回傳失敗, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				errCode = "error-003-004";
				LogUtil.error(this.getClass(), errCode, "getCurrency 查詢幣別中文名稱發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "getCurrency 查詢幣別中文名稱 end");
		}

		if (goNext) {
			output.setSuccess(true);
			output.setCurrencyInfo(currencyInfo);
		} else {
			output.setSuccess(false);
			output.setReturnCode(errCode);
			output.setReturnMessage(String.format("系統發生錯誤, 請稍後再試 (%s)", errCode));
		}

		return output;
	}

	@Override
	public DeleteCurrencyOutput deleteCurrency(DeleteCurrencyInput input) {
		boolean goNext = true;
		String errCode = "";
		DeleteCurrencyOutput output = new DeleteCurrencyOutput();
		if (!input.isValid()) {
			goNext = false;
			errCode = "error-004-001";
			LogUtil.error(this.getClass(), errCode, "deleteCurrency input 驗證發生錯誤");
		}

		CurrencyNameMap currencyNameMap = null;
		if (goNext) {
			LogUtil.info(this.getClass(), "deleteCurrency 查詢幣別中文名稱 start, 幣別名稱 : " + input.getEngName());
			try {
				GetCurrencyInfoInput serviceInput = new GetCurrencyInfoInput();
				serviceInput.setEngName(input.getEngName());
				GetCurrencyInfoOutput serviceOutput = currencyService.getCurrencyInfo(serviceInput);
				if (serviceOutput.isSuccess()) {
					if (serviceOutput.getCurrencyInfo() == null) {
						errCode = "error-004-002";
						LogUtil.warn(this.getClass(), errCode, "deleteCurrency 查無幣別資料, 幣別名稱 : " + input.getEngName());
					} else {
						currencyNameMap = serviceOutput.getCurrencyNameMap();
					}
				} else if (!serviceOutput.isSuccess()) {
					errCode = "error-004-003";
					LogUtil.warn(this.getClass(), errCode, "deleteCurrency 查詢幣別中文名稱回傳失敗, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				errCode = "error-004-004";
				LogUtil.error(this.getClass(), errCode, "deleteCurrency 查詢幣別中文名稱發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "deleteCurrency 查詢幣別中文名稱 end");
		}

		CurrencyInfo currencyInfo = null;
		if (goNext && currencyNameMap != null) {
			LogUtil.info(this.getClass(), "deleteCurrency 刪除幣別資訊 start, 幣別名稱 : " + input.getEngName());
			try {
				DeleteCurrencyInfoInput serviceInput = new DeleteCurrencyInfoInput();
				serviceInput.setOriCurNameMap(currencyNameMap);
				DeleteCurrencyInfoOutput serviceOutput = currencyService.deleteCurrencyInfo(serviceInput);
				if (!serviceOutput.isSuccess()) {
					errCode = "error-002-005";
					LogUtil.warn(this.getClass(), errCode, "deleteCurrency 刪除幣別資訊回傳失敗, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				errCode = "error-002-006";
				LogUtil.error(this.getClass(), errCode, "deleteCurrency 刪除幣別資訊發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "deleteCurrency 刪除幣別資訊 end");
		}

		if (goNext) {
			output.setSuccess(true);
		} else {
			output.setSuccess(false);
			output.setReturnCode(errCode);
			output.setReturnMessage(String.format("系統發生錯誤, 請稍後再試 (%s)", errCode));
		}

		return output;
	}

	@Override
	public RrefreshExRateOutput refreshExRate(RrefreshExRateInput input) {
		RrefreshExRateOutput output = new RrefreshExRateOutput();
		boolean goNext = true;
		String errCode = "";

		LogUtil.info(this.getClass(), "refreshExRate 取得即時匯率 start");
		GetRealExRateInfoOutput serviceOutput = null;
		try {
			GetRealExRateInput serviceInput = new GetRealExRateInput();
			serviceOutput = currencyService.getRealExRate(serviceInput);
			if (!serviceOutput.isSuccess() || serviceOutput.getRealTimeExRate() == null
					|| serviceOutput.getRealTimeExRate().getBpi() == null) {
				goNext = false;
				errCode = "error-005-001";
				LogUtil.error(this.getClass(), errCode, "refreshExRate 取得即時匯率失敗");
			}
		} catch (Exception e) {
			goNext = false;
			errCode = "error-005-002";
			LogUtil.error(this.getClass(), errCode, "refreshExRate 取得即時匯率發生錯誤", e);
		}
		LogUtil.info(this.getClass(), "refreshExRate 取得即時匯率 end");

		if (goNext) {
			output.setSuccess(true);
			output.setExchangeRate(serviceOutput.getRealTimeExRate());
		} else {
			output.setSuccess(false);
			output.setReturnCode(errCode);
			output.setReturnMessage(String.format("系統發生錯誤, 請稍後再試 (%s)", errCode));
		}

		return output;
	}

	@Override
	public GetCurExInfoOutput getCurExInfo(GetCurExInfoInput input) {
		String errCode = "";
		GetCurExInfoOutput output = new GetCurExInfoOutput();
		boolean goNext = true;

		if (!input.isValid()) {
			goNext = false;
			errCode = "error-006-001";
			LogUtil.error(this.getClass(), errCode, "getCurExInfo input 驗證發生錯誤");
		}

		CurrencyInfo currencyInfo = null;
		if (goNext) {
			LogUtil.info(this.getClass(), "getCurExInfo 查詢幣別中文名稱 start, 幣別名稱 : " + input.getEngName());
			try {
				GetCurrencyInfoInput serviceInput = new GetCurrencyInfoInput();
				serviceInput.setEngName(input.getEngName());
				GetCurrencyInfoOutput serviceOutput = currencyService.getCurrencyInfo(serviceInput);
				if (serviceOutput.isSuccess()) {
					if (serviceOutput.getCurrencyInfo() == null) {
						errCode = "error-006-002";
						LogUtil.warn(this.getClass(), errCode, "getCurExInfo 查無幣別資料, 幣別名稱 : " + input.getEngName());
					} else {
						currencyInfo = serviceOutput.getCurrencyInfo();
					}
				} else if (!serviceOutput.isSuccess()) {
					errCode = "error-006-003";
					LogUtil.warn(this.getClass(), errCode, "getCurExInfo 查詢幣別中文名稱回傳失敗, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				errCode = "error-006-004";
				LogUtil.error(this.getClass(), errCode, "getCurExInfo 查詢幣別中文名稱發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "getCurExInfo 查詢幣別中文名稱 end");
		}

		GetRealExRateInfoOutput serviceOutput = null;
		if (goNext) {
			LogUtil.info(this.getClass(), "getCurExInfo 取得即時匯率 start, 幣別名稱 : " + input.getEngName());
			try {
				GetRealExRateInput serviceInput = new GetRealExRateInput();
				serviceOutput = currencyService.getRealExRate(serviceInput);
				if (!serviceOutput.isSuccess() || serviceOutput.getRealTimeExRate() == null
						|| serviceOutput.getRealTimeExRate().getBpi() == null) {
					goNext = false;
					errCode = "error-006-005";
					LogUtil.error(this.getClass(), errCode, "getCurExInfo 取得即時匯率失敗, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				errCode = "error-006-006";
				LogUtil.error(this.getClass(), errCode, "getCurExInfo 取得即時匯率發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "getCurExInfo 取得即時匯率 end");
		}

		// 依幣別取得對應的中文名稱、即時匯率
		if (goNext) {
			Bpi bpi = serviceOutput.getRealTimeExRate().getBpi();
			CurrencyExchangeInfo curExInfo = null;
			switch (input.getEngName()) {
			case SysConst.USD:
				curExInfo = bpi.getUSD();
				break;
			case SysConst.GBP:
				curExInfo = bpi.getGBP();
				break;
			case SysConst.EUR:
				curExInfo = bpi.getEUR();
				break;
			default:
				LogUtil.warn(this.getClass(), "getCurExInfo 查無對應幣別的匯率資訊, 幣別名稱 : " + input.getEngName());
			}
			if (curExInfo != null) {
				String updateTime = serviceOutput.getRealTimeExRate().getTime().getUpdatedISO();
				Date updateDate = DateUtil.parse(updateTime);
				updateTime = DateUtil.format(updateDate, DateUtil.FORMAT_DateTime);
				currencyInfo.setRateUpdateTime(updateTime);
				currencyInfo.setRate(curExInfo.getRate());
			}
		}

		if (goNext) {
			output.setSuccess(true);
			output.setCurrencyInfo(currencyInfo);
		} else {
			output.setSuccess(false);
			output.setReturnCode(errCode);
			output.setReturnMessage(String.format("系統發生錯誤, 請稍後再試 (%s)", errCode));
		}

		return output;
	}

}
