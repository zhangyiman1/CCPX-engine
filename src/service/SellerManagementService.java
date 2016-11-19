package service;

import model.seller;

public interface SellerManagementService {

	public seller checkSeller(String username, String password);
	
	public seller validateUsername(String username);
	
	public boolean updateSellerinfo(seller seller);

	public boolean updatePassword(int Seller_id, String Seller_Password);
	
	boolean updateEmail(int Seller_id, String Seller_Email);
	
	boolean updatePhone(int Seller_id, String Seller_Telephone);

	public boolean regist(seller seller);
	
	public seller getSellerById(int Seller_id);
	
	public boolean checkActivationCode(String code, String sellerid);
	
	public boolean updateSellerStatus(String sellerid);
}
