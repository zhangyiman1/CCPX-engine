package model;

import java.util.List;

public class ExchangePointVO {

	private int errno;
	private String err;
	private double totalPoint;
	private List exchangePoints;
	
	public ExchangePointVO(){}
	public ExchangePointVO(int errno, String err, List exchangePoints){
		this.errno=errno;
		this.err=err;
		this.exchangePoints=exchangePoints;
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
	public double getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(double totalPoint) {
		this.totalPoint = totalPoint;
	}
	public List getExchangePoints() {
		return exchangePoints;
	}
	public void setExchangePoints(List exchangePoints) {
		this.exchangePoints = exchangePoints;
	}
}
