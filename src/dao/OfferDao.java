package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Offer;

public interface OfferDao {
	public ArrayList<Offer> getExchangeOffers(int seller_from,int seller_to,int points_from,int points_to_min)  throws SQLException;
	public ArrayList<Offer> getExchangeOffers(int seller_from,int seller_to)  throws SQLException;
	public String making_an_offer(Offer offer) throws SQLException;
	public String cancelOffers(int offer_id, int user_id) throws SQLException;
}