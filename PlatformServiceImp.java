package service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.net.URLConnection; 

import com.alibaba.fastjson.JSONObject;

import dao.PlatformDao;
import model.Notification;
import model.Offer;
import model.Request;

@Service("PlatformServiceImp")
public class PlatformServiceImp implements PlatformService {

	@Resource(name = "PlatformDaoImp")
	private PlatformDao PlatformDaoImp;

	@Override
	public Boolean removeExchange(Integer request_id, Integer user_from) {
		Boolean flag = PlatformDaoImp.removeExchange(request_id, user_from);
		System.out.println("flag: " + flag);
		return flag;
	}

	@Override
	public Boolean declineExchange(Integer request_id, Integer user_to) {
		Boolean flag = PlatformDaoImp.declineExchange(request_id, user_to);
		System.out.println("flag: " + flag);
		return flag;
	}

	@Override
	public Boolean removeOffer(Integer offer_id, Integer user_id) {
		Boolean flag = PlatformDaoImp.removeOffer(offer_id, user_id);
		System.out.println("flag: " + flag);
		return flag;
	}

	@Override
	public List<Request> showLatestTransaction(Integer sellerFrom, Integer sellerTo) {
		List<Request> requests = PlatformDaoImp.showLatestTransaction(sellerFrom, sellerTo);
		return requests;
	}

	@Override
	public List<Offer> searchExcahnge(Integer sellerFrom, Integer sellerTo, Integer pointsFrom, Integer pointsToMin) {
		List<Offer> offers = PlatformDaoImp.searchExcahnge(sellerFrom, sellerTo, pointsFrom, pointsToMin);
		return offers;
	}

	@Override
	public List<Offer> showRecommendationList(Integer sellerFrom, Integer sellerTo, Integer pointsFrom,
			Integer pointsToMin) {
		List<Offer> offers = PlatformDaoImp.showRecommendationList(sellerFrom, sellerTo, pointsFrom, pointsToMin);
		return offers;
	}

	@Override
	public Boolean updateOfferStatus(Integer offer_id, Integer user_from) {
		Boolean flag = PlatformDaoImp.updateOfferStatus(offer_id, user_from);
		System.out.println("flag: " + flag);
		return flag;
	}

	@Override
	public Boolean updateRequestStatus(Integer request_id, Integer user_from) {
		Boolean flag = PlatformDaoImp.updateRequestStatus(request_id, user_from);
		System.out.println("flag: " + flag);
		return flag;

	}

	@Override
	public Boolean acceptRequest(Integer request_id) {
		Request request = new Request();
		Integer UserFrom = request.getUserFrom();
		Integer UserTo = request.getUserTo();
		Integer PointsFrom = request.getPointsFrom();
		Integer PointsTo = request.getPointsTo();
		Integer SellerFrom = request.getSellerFrom();
		Integer SellerTo = request.getSellerTo();
		Integer OfferFrom = request.getOfferFrom();
		Integer OfferTo = request.getOfferTo();
		request = PlatformDaoImp.requestData(request_id);
		// Boolean success = PlatformDaoImp.sendExchangeToBC(Integer request_id,
		// Integer UserFrom, Integer UserTo,
		// Integer PointsFrom, Integer PointsTo, Integer SellerFrom, Integer
		// SellerTo);
		Boolean success = true;
		// call notification notifi(OfferFrom) notifi(OfferTo) notifi(UserFrom)
		// notifi(UserTo)
		Boolean acc = PlatformDaoImp.acceptRequest(request_id, OfferFrom, OfferTo);
		List<Integer> requestList = PlatformDaoImp.listOfRequest(OfferFrom, OfferTo);
		requestList.remove(request_id);

		return null;

	}
	
	public boolean sendExchangeToBlockChain(Integer Request_id,
			Integer userFrom, Integer userTo, Integer sellerFrom,
			Integer sellerTo, Integer pointFrom, Integer pointTo) {
		// TODO Auto-generated method stub		
		//String sr=HttpRequest.sendPost("http://ccpx-blockchain.mybluemix.net/getTxInfo","Request_id=Request_id&user_A=userFrom&user_B=userTo&seller_A=sellerFrom&seller_B=sellerTo&point_A=pointFrom&point_B=pointTo&time=time");
        try{  
            // Configure and open a connection to the site you will send the request  
            URL url = new URL("http://ccpx-blockchain.mybluemix.net/getTxInfo");  
            URLConnection urlConnection = url.openConnection();  
            // set attribute of doOutput as true,表示将使用此urlConnection写入数据  
            urlConnection.setDoOutput(true);  
            // 定义待写入数据的内容类型，这里设置为application/x-www-form-urlencoded类型  
            urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");  
            // 得到请求的输出流对象  
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());  
            // 把数据写入Blockchain  
            out.write("Request_id=Request_id&user_A=userFrom&user_B=userTo&seller_A=sellerFrom&seller_B=sellerTo&point_A=pointFrom&point_B=pointTo");  
            out.flush();  
            out.close();  
              
            // 从blockchain读取响应  
            InputStream inputStream = urlConnection.getInputStream();  
            String encoding = urlConnection.getContentEncoding();  
            String respond = IOUtils.toString(inputStream, encoding);  
            System.out.println(respond);  
            
            JSONObject jsonObject=new JSONObject();		
    		String response=jsonObject.getString("respond");		
    		//System.out.println("RESPOND_CODE:"+response);	
    		if(response.equals('1')){
    		return true;
    		}else{
    		return false;
    		}
        }catch(IOException e){ 
        	e.printStackTrace();
        }
		return true; 								

	}

	@Override
	public Boolean createNotification(Integer userId, Integer status, Integer eR_ID) {
		if(PlatformDaoImp.createNotification(userId, status, eR_ID)) return true;
		else return false;
	}
	
	//NOTIFACTION TESTPAGE
	@Override
	public List<Notification> NotifListsByUserId(Integer userId) {
		return PlatformDaoImp.NotifListsByUserId(userId);
	}
	@Override
	public List<Notification> getNotifUnread(Integer userId) {
		return PlatformDaoImp.getNotifUnRead(userId);
	}


}
