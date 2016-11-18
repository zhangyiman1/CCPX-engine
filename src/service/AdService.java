package service;

import model.advertisement;

public interface AdService {
	public void uploadAd(model.seller seller,String adtitle,String adimage);
	public advertisement checkAd(String adtitle);

}
