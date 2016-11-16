package dao;
import model.advertisement;
import model.seller;
public interface AdDao {
	public void addAd(seller seller,String adtitle,String adimage);
	public advertisement checkAd(String adtitle);
}
