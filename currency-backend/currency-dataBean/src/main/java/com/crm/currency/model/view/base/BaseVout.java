package com.crm.currency.model.view.base;
import java.io.Serializable;

import com.crm.currency.model.common.ResultMessage;

public class BaseVout implements Serializable{
	public static final long serialVersionUID = 1L;
	
    protected ResultMessage resultMessage = new ResultMessage();

    public BaseVout() {

    }

    public ResultMessage getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(ResultMessage resultMessage) {
        this.resultMessage = resultMessage;
    }
}
