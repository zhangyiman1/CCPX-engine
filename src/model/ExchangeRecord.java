package model;

public class ExchangeRecord {

	private int seller_A_Id;
	private int user_A_Id;
	private double point_A;
	private int seller_B_Id;
	private int user_B_Id;
	private double point_B;
	private String ex_time;
	
	public ExchangeRecord(int seller_A_Id,int user_A_Id,double point_A,int seller_B_Id,
			int user_B_Id,double point_B,String ex_time){
		this.seller_A_Id=seller_A_Id;
		this.user_A_Id=user_A_Id;
		this.point_A=point_A;
		this.seller_B_Id=seller_B_Id;
		this.user_B_Id=user_B_Id;
		this.point_B=point_B;
		this.ex_time=ex_time;
	}
	
	public ExchangeRecord(){
		
	}
	
	public int getSeller_A_Id() {
		return seller_A_Id;
	}
	public void setSeller_A_Id(int seller_A_Id) {
		this.seller_A_Id = seller_A_Id;
	}
	public int getUser_A_Id() {
		return user_A_Id;
	}
	public void setUser_A_Id(int user_A_Id) {
		this.user_A_Id = user_A_Id;
	}
	public double getPoint_A() {
		return point_A;
	}
	public void setPoint_A(double point_A) {
		this.point_A = point_A;
	}
	public int getSeller_B_Id() {
		return seller_B_Id;
	}
	public void setSeller_B_Id(int seller_B_Id) {
		this.seller_B_Id = seller_B_Id;
	}
	public int getUser_B_Id() {
		return user_B_Id;
	}
	public void setUser_B_Id(int user_B_Id) {
		this.user_B_Id = user_B_Id;
	}
	public double getPoint_B() {
		return point_B;
	}
	public void setPoint_B(double point_B) {
		this.point_B = point_B;
	}
	public String getEx_time() {
		return ex_time;
	}

	public void setEx_time(String ex_time) {
		this.ex_time = ex_time;
	}

	public String toString(){
		return this.seller_A_Id+" "+this.user_A_Id+" "+this.point_A+" "+
				this.seller_B_Id+" "+this.user_B_Id+" "+this.point_B+" "+this.ex_time;
	}
	
}
