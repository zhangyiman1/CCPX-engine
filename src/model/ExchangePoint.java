package model;

public class ExchangePoint {

	private String userName;
	private String gainPointSeller;
	private double gainPoint;
	private String losePointSeller;
	private double losePoint;
	private String timestamp;
	
	public ExchangePoint(){}
	public ExchangePoint(String userName, String gainPointSeller, double gainPoint,
			String losePointSeller, double losePoint, String timestamp){
		this.userName=userName;
		this.gainPointSeller=gainPointSeller;
		this.gainPoint=gainPoint;
		this.losePointSeller=losePointSeller;
		this.losePoint=losePoint;
		this.timestamp=timestamp;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGainPointSeller() {
		return gainPointSeller;
	}
	public void setGainPointSeller(String gainPointSeller) {
		this.gainPointSeller = gainPointSeller;
	}
	public double getGainPoint() {
		return gainPoint;
	}
	public void setGainPoint(double gainPoint) {
		this.gainPoint = gainPoint;
	}
	public String getLosePointSeller() {
		return losePointSeller;
	}
	public void setLosePointSeller(String losePointSeller) {
		this.losePointSeller = losePointSeller;
	}
	public double getLosePoint() {
		return losePoint;
	}
	public void setLosePoint(double losePoint) {
		this.losePoint = losePoint;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
