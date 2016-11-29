package model;

import java.util.List;

public class ExchangeRateVO {

	private int errno;
	private String err;
	private List exchangeRates;
	
	public ExchangeRateVO(){}
	public ExchangeRateVO(int errno, String err, List exchangeRates){
		this.errno=errno;
		this.err=err;
		this.exchangeRates=exchangeRates;
	}
	
	
	public int getErrno() {
		return errno;
	}
	public void setErrno(int errno) {
		this.errno = errno;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public List getExchangeRates() {
		return exchangeRates;
	}
	public void setExchangeRates(List exchangeRates) {
		this.exchangeRates = exchangeRates;
	}
	
}
