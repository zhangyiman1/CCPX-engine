package dao;

import java.util.List;

import model.Offer;
import model.Request;
public interface PlatformDao {

	public Boolean removeExchange(Integer request_id, Integer user_from);
	public Boolean declineExchange(Integer request_id, Integer user_to);
	public Boolean removeOffer(Integer offer_id, Integer user_id);
	public List<Request> showLatestTransaction(Integer sellerFrom, Integer sellerto);  
	
 	public List<Offer> searchExcahnge(Integer sellerFrom, Integer sellerTo, Integer pointsFrom, Integer pointsToMin);
 	
 	public List<Offer> showRecommendationList(Integer sellerFrom, Integer sellerTo, Integer pointsFrom, Integer pointsToMin);
 	
 	public Boolean updateOfferStatus(Integer offer_id, Integer user_from);
 	public Boolean updateRequestStatus(Integer request_id, Integer user_from); 
 	
}
