package com.crm.currency.mware.bo;

public class ExchangeRate {
	
	private UpdateTime time;
	private String disclaimer;
	private String chartName;
	private Bpi bpi;
	public UpdateTime getTime() {
		return time;
	}
	public void setTime(UpdateTime time) {
		this.time = time;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public Bpi getBpi() {
		return bpi;
	}
	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}
	
}
