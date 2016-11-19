package dao;

import model.seller;
import model.seller;

public interface SellerManagementDao {

	public seller checkSeller(String username, String password);
	
	public seller validateUsername(String username);
	
	public boolean updateSellerinfo(seller seller);

	public boolean regist(seller seller);

	boolean updatePassword(int Seller_id, String Seller_Password);
	
	boolean updateEmail(int Seller_id, String Seller_Email);
	
	boolean updatePhone(int Seller_id, String Seller_Telephone);
	
	public seller getSellerById(int Seller_id);	
	
	public boolean checkActivationCode(String code, String sellerid);
	
	public boolean updateSellerStatus(String sellerid);
}
