package com.crm.currency.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crm.currency.dao.entity.CurrencyNameMap;
import com.crm.currency.dao.repository.CurrencyNameMapRepository;
import com.crm.currency.model.bo.datamodel.CurrencyInfo;
import com.crm.currency.mware.ICoindeskWmare;
import com.crm.currency.mware.bo.GetExchangeRateInput;
import com.crm.currency.mware.bo.GetExchangeRateOutput;
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
public class CurrencyServiceImpl implements ICurrencyService {

	@Autowired
	CurrencyNameMapRepository currencyNameMapRepository;

	@Autowired
	ICoindeskWmare coindeskMware;

	// 查詢對應幣別中文名稱對應資訊
	@Override
	public GetCurrencyInfoOutput getCurrencyInfo(GetCurrencyInfoInput input) {
		GetCurrencyInfoOutput output = new GetCurrencyInfoOutput();
		boolean goNext = true;

		if (!input.isValid()) {
			goNext = false;
			LogUtil.error(this.getClass(), "getCurrencyInfo input 驗證發生錯誤");
		}

		CurrencyNameMap currencyNameInfo = null;
		if (goNext) {
			LogUtil.info(this.getClass(), "getCurrencyInfo 查詢幣別中文名稱 start, 幣別名稱 : " + input.getEngName());
			try {
				currencyNameInfo = currencyNameMapRepository.findByEnglishName(input.getEngName());
				if (currencyNameInfo == null) {
					LogUtil.warn(this.getClass(), "getCurrencyInfo 查無幣別資料, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				LogUtil.error(this.getClass(), "getCurrencyInfo 更新幣別對應名稱發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "getCurrencyInfo 查詢幣別中文名稱 end");
		}

		if (goNext) {
			output.setSuccess(true);
			CurrencyInfo currencyInfo = currencyNameInfo != null ? this.transCurrencyInfo(currencyNameInfo) : null;
			output.setCurrencyInfo(currencyInfo);
			output.setCurrencyNameMap(currencyNameInfo);
		} else {
			output.setSuccess(false);
		}

		return output;
	}

	// 新增對應幣別中文名稱對應資訊
	@Override
	public AddCurrencyInfoOutput addCurrencyInfo(AddCurrencyInfoInput input) {
		boolean goNext = true;
		AddCurrencyInfoOutput output = new AddCurrencyInfoOutput();

		if (!input.isValid()) {
			goNext = false;
			LogUtil.error(this.getClass(), "addCurrencyInfo input 驗證發生錯誤");
		}

		CurrencyNameMap newCurrencyNameInfo = null;
		if (goNext) {
			LogUtil.info(this.getClass(), "addCurrencyInfo 新增幣別中文名稱 start, 幣別名稱 : " + input.getEngName());
			try {
				CurrencyNameMap currencyNameInfo = new CurrencyNameMap();
				currencyNameInfo.setEnglishName(input.getEngName());
				currencyNameInfo.setChineseName(input.getChineseName());
				currencyNameInfo.setCreateBy(input.getCreateBy());
				currencyNameInfo.setCreateDate(new Date());
				currencyNameInfo.setRemark(input.getRemark());
				newCurrencyNameInfo = currencyNameMapRepository.save(currencyNameInfo);
				if (newCurrencyNameInfo == null) {
					goNext = false;
					LogUtil.error(this.getClass(), "addCurrencyInfo 新增幣別對應名稱失敗, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				LogUtil.error(this.getClass(), "addCurrencyInfo 新增幣別對應名稱發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "addCurrencyInfo 新增幣別中文名稱 end");
		}

		if (goNext) {
			output.setSuccess(true);
			CurrencyInfo currencyInfo = newCurrencyNameInfo != null ? this.transCurrencyInfo(newCurrencyNameInfo)
					: null;
			output.setCurrencyInfo(currencyInfo);
		} else {
			output.setSuccess(false);
		}

		return output;
	}

	// 更新對應幣別中文名稱對應資訊
	@Override
	public UpdateCurrencyInfoOutput updateCurrencyInfo(UpdateCurrencyInfoInput input) {
		boolean goNext = true;
		UpdateCurrencyInfoOutput output = new UpdateCurrencyInfoOutput();

		if (!input.isValid()) {
			goNext = false;
			LogUtil.error(this.getClass(), "updateCurrencyInfo input 驗證發生錯誤");
		}

		CurrencyNameMap newCurrencyNameInfo = null;
		if (goNext) {
			LogUtil.info(this.getClass(), "updateCurrencyInfo 更新幣別中文名稱 start, 幣別名稱 : " + input.getEngName());
			try {
				CurrencyNameMap oriCurNameMap = input.getOriCurNameMap();
				oriCurNameMap.setChineseName(input.getChineseName());
				oriCurNameMap.setUpdateBy(input.getUpdateBy());
				oriCurNameMap.setUpdateDate(new Date());
				oriCurNameMap.setRemark(input.getRemark());
				newCurrencyNameInfo = currencyNameMapRepository.save(oriCurNameMap);
				if (newCurrencyNameInfo == null) {
					goNext = false;
					LogUtil.error(this.getClass(), "updateCurrencyInfo 更新幣別對應名稱失敗, 幣別名稱 : " + input.getEngName());
				}
			} catch (Exception e) {
				goNext = false;
				LogUtil.error(this.getClass(), "updateCurrencyInfo 更新幣別對應名稱發生錯誤, 幣別名稱 : " + input.getEngName(), e);
			}
			LogUtil.info(this.getClass(), "updateCurrencyInfo 更新幣別中文名稱 end");
		}

		if (goNext) {
			output.setSuccess(true);
			CurrencyInfo currencyInfo = newCurrencyNameInfo != null ? this.transCurrencyInfo(newCurrencyNameInfo)
					: null;
			output.setCurrencyInfo(currencyInfo);
		} else {
			output.setSuccess(false);
		}

		return output;
	}

	// 刪除對應幣別中文名稱對應資訊
	@Override
	public DeleteCurrencyInfoOutput deleteCurrencyInfo(DeleteCurrencyInfoInput input) {
		DeleteCurrencyInfoOutput output = new DeleteCurrencyInfoOutput();
		boolean goNext = true;

		if (!input.isValid()) {
			goNext = false;
			LogUtil.error(this.getClass(), "deleteCurrencyInfo input 驗證發生錯誤");
		}

		if (goNext) {
			try {
				currencyNameMapRepository.delete(input.getOriCurNameMap());
			} catch (Exception e) {
				goNext = false;
				LogUtil.error(this.getClass(),
						"updateCurrencyInfo 刪除幣別資訊發生錯誤, 幣別名稱 : " + input.getOriCurNameMap().getEnglishName(), e);
			}
		}

		if (goNext) {
			output.setSuccess(true);
		} else {
			output.setSuccess(false);
		}

		return output;
	}

	// 呼叫 coindesk 取得最新匯率
	@Override
	public GetRealExRateInfoOutput getRealExRate(GetRealExRateInput input) {
		GetRealExRateInfoOutput output = new GetRealExRateInfoOutput();
		boolean goNext = true;

		GetExchangeRateOutput mwareOutput = null;
		LogUtil.info(this.getClass(), "getCurrencyInfo 取得即時匯率 start");
		try {
			GetExchangeRateInput mwareInput = new GetExchangeRateInput();
			mwareOutput = coindeskMware.getExchangeRate(mwareInput);
			if (!mwareOutput.isSuccess()) {
				goNext = false;
				LogUtil.error(this.getClass(), "getRealExRate 取得即時匯率失敗");
			}
		} catch (Exception e) {
			goNext = false;
			LogUtil.error(this.getClass(), "getRealExRate 取得即時匯率發生錯誤", e);
		}
		LogUtil.info(this.getClass(), "getCurrencyInfo 取得即時匯率 end");

		if (goNext) {
			output.setSuccess(true);
			output.setRealTimeExRate(mwareOutput.getExchangeRate());
		} else {
			output.setSuccess(false);
		}

		return output;
	}

	private CurrencyInfo transCurrencyInfo(CurrencyNameMap currencyNameInfo) {
		CurrencyInfo currencyInfo = new CurrencyInfo();
		currencyInfo.setEngName(currencyNameInfo.getEnglishName());
		currencyInfo.setChineseName(currencyNameInfo.getChineseName());
		currencyInfo.setCreateBy(currencyNameInfo.getCreateBy());
		currencyInfo.setCreatDate(DateUtil.format(currencyNameInfo.getCreateDate(), DateUtil.FORMAT_DateTime));
		currencyInfo.setUpdateBy(currencyNameInfo.getUpdateBy());
		currencyInfo.setUpdateDate(DateUtil.format(currencyNameInfo.getCreateDate(), DateUtil.FORMAT_DateTime));
		currencyInfo.setRemark(currencyNameInfo.getRemark());

		return currencyInfo;
	}

}
