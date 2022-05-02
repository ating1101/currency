package com.crm.currency.service;

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

public interface ICurrencyService {
	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 查詢幣別對應中文名稱
	 */
	GetCurrencyInfoOutput getCurrencyInfo(GetCurrencyInfoInput input);

	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 新增幣別對應中文名稱
	 */
	AddCurrencyInfoOutput addCurrencyInfo(AddCurrencyInfoInput input);

	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 更新幣別對應中文名稱
	 */
	UpdateCurrencyInfoOutput updateCurrencyInfo(UpdateCurrencyInfoInput input);

	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 刪除幣別資訊
	 */
	DeleteCurrencyInfoOutput deleteCurrencyInfo(DeleteCurrencyInfoInput input);

	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 取得即時匯率
	 */
	GetRealExRateInfoOutput getRealExRate(GetRealExRateInput input);
}
