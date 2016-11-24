package model;

import java.util.Date;

public class Record{
	int r_id;
	int u_id_from;
	int u_id_to;
	int seller_id_from;
	int seller_id_to;
	int points_from;
	int points_to;
	int offer_id;
	String status;
	Date update_time;
	
	public Record(){}

	public Record(int r_id, int u_id_from, int u_id_to, int seller_id_from, int seller_id_to, int points_from,
			int points_to, int offer_id, String status, Date update_time) {
		super();
		this.r_id = r_id;
		this.u_id_from = u_id_from;
		this.u_id_to = u_id_to;
		this.seller_id_from = seller_id_from;
		this.seller_id_to = seller_id_to;
		this.points_from = points_from;
		this.points_to = points_to;
		this.offer_id = offer_id;
		this.status = status;
		this.update_time = update_time;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public int getU_id_from() {
		return u_id_from;
	}

	public void setU_id_from(int u_id_from) {
		this.u_id_from = u_id_from;
	}

	public int getU_id_to() {
		return u_id_to;
	}

	public void setU_id_to(int u_id_to) {
		this.u_id_to = u_id_to;
	}

	public int getSeller_id_from() {
		return seller_id_from;
	}

	public void setSeller_id_from(int seller_id_from) {
		this.seller_id_from = seller_id_from;
	}

	public int getSeller_id_to() {
		return seller_id_to;
	}

	public void setSeller_id_to(int seller_id_to) {
		this.seller_id_to = seller_id_to;
	}

	public int getPoints_from() {
		return points_from;
	}

	public void setPoints_from(int points_from) {
		this.points_from = points_from;
	}

	public int getPoints_to() {
		return points_to;
	}

	public void setPoints_to(int points_to) {
		this.points_to = points_to;
	}

	public int getOffer_id() {
		return offer_id;
	}

	public void setOffer_id(int offer_id) {
		this.offer_id = offer_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	};
	
	
}