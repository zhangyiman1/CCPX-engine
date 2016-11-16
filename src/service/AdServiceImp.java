package service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.advertisement;
import dao.*;

@Service("AdServiceImp")
public class AdServiceImp implements AdService {
	@Resource(name = "AdDaoImp")
	private AdDao addao= new AdDaoImp();
	public void uploadAd(model.seller seller,String adtitle,String adimage)
	{
		addao.addAd(seller,adtitle,adimage);
	}
	public advertisement checkAd(String adtitle)
	{
		return addao.checkAd(adtitle);
	}

}
