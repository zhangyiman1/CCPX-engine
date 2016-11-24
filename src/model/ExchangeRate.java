package model;

public class ExchangeRate {

	private int sellerId;
	private String sellerName;
	private String industryType;
	private double rate;
	
	public ExchangeRate(){}
	public ExchangeRate(int sellerId, String sellerName, String industryType, double rate){
		this.sellerId=sellerId;
		this.sellerName=sellerName;
		this.industryType=industryType;
		this.rate=rate;
	}
	
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
}
