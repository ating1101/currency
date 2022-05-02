package com.crm.currency.facade;

import org.springframework.stereotype.Component;

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

public interface ICurrencyInfoFacade {
	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 查詢幣別對應中文名稱
	 */
	GetCurrencyOutput getCurrency(GetCurrencyInput input);

	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 新增幣別對應中文名稱
	 */
	AddCurrencyOutput addCurrency(AddCurrencyInput input);

	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 更新幣別對應中文名稱
	 */
	UpdateCurrencyOutput updateCurrency(UpdateCurrencyInput input);

	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 刪除幣別資訊
	 */
	DeleteCurrencyOutput deleteCurrency(DeleteCurrencyInput input);

	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 刪除幣別資訊
	 */
	RrefreshExRateOutput refreshExRate(RrefreshExRateInput input);
	/**
	 * @notes Created by Tim <br>
	 *        Created on 2022年05月01日
	 * @description 取指定幣別匯率、中文名稱
	 */
	GetCurExInfoOutput getCurExInfo(GetCurExInfoInput input);
}
