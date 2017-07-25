package org.com.cay.entity;

import java.io.Serializable;

//Ã»ÓÐÖ÷¼üid
public class Pay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int monthPay;
	private int yearPay;
	private int vocationWithPay;
	public int getMonthPay() {
		return monthPay;
	}
	public void setMonthPay(int monthPay) {
		this.monthPay = monthPay;
	}
	public int getYearPay() {
		return yearPay;
	}
	public void setYearPay(int yearPay) {
		this.yearPay = yearPay;
	}
	public int getVocationWithPay() {
		return vocationWithPay;
	}
	public void setVocationWithPay(int vocationWithPay) {
		this.vocationWithPay = vocationWithPay;
	}
	
}
