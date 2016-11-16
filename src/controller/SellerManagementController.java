package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.seller;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.SellerManagementService;
import utils.MD5Util;
import utils.VerifyCode;

@Controller
@RequestMapping(value ="/seller")
public class SellerManagementController {
	Calendar c = java.util.Calendar.getInstance(); // yyyy年MM月dd日hh时mm分ss秒
	SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy.MM.dd");

	static Logger log=Logger.getLogger(SellerManagementController.class);
	static{
		log.info("right");
	}
	 
	@Resource(name = "sellerManagementServiceImp")
	private SellerManagementService sellerManagementServiceImp;

	@RequestMapping("checkSeller")
	@ResponseBody
	public void checkSeller(HttpServletRequest req, HttpServletResponse res,
			String username, String password) {
			seller Seller = sellerManagementServiceImp.checkSeller(username, password);
			res.setCharacterEncoding("UTF-8"); 
	        res.setContentType("text/json");
	        PrintWriter out =null;
	        
			if (Seller != null) {
				//req.getSession().setAttribute("Seller", Seller);
		        String message = "{'message':'success'}";  
		        JSONObject json = JSONObject.fromObject(message);
		        System.out.print(json);
		        System.out.close();
		        
		        try{
		        out = res.getWriter();
		        out.write(json.toString());
		        out.flush();
		        out.close();
		        }catch (IOException e) {  
		            e.printStackTrace();  
		        } finally {  
		            if (out != null) {  
		                out.close();  
		            } 
		        } 

				
			} else {
		        String message = "{'message':'username or password is not correct'}";  
		        JSONObject json = JSONObject.fromObject(message);
		        System.out.print(json);
		        System.out.close();
		        try{
		        out = res.getWriter();
		        out.write(json.toString());
		        out.flush();
		        out.close();
		        }catch (IOException e) {  
		            e.printStackTrace();  
		        } finally {  
		            if (out != null) {  
		                out.close();  
		            } 
		        } 
			}

	}

	@RequestMapping("/exit")
	@ResponseBody
	public void exit(HttpServletRequest req) {
		//req.getSession().removeAttribute("user");
		String message = "{'exit':'exit success'}";  
        JSONObject json = JSONObject.fromObject(message);
        System.out.print(json);
        System.out.close();
	}
	
	@RequestMapping("/registSeller")
	public void registSeller(HttpServletRequest req, HttpServletResponse res, String Seller_Name,
			String Seller_Address, String Seller_Telephone, String Seller_Email, String Seller_Username,
			String Seller_Password, String IndustryType_id, String Seller_Logo,
			String Seller_Description) {
		
		seller Seller = new seller();
		Seller.setSeller_Name(Seller_Name);
		Seller.setSeller_Address(Seller_Address);
		Seller.setSeller_Telephone(Seller_Telephone);
		Seller.setSeller_Email(Seller_Email);
		Seller.setSeller_Username(Seller_Username);
		Seller.setSeller_Password(Seller_Password);
		Seller.setIndustryType_id(IndustryType_id);
		Seller.setSeller_Logo(Seller_Logo);
		Seller.setSeller_Description(Seller_Description);
		boolean b = sellerManagementServiceImp.regist(Seller);
		 PrintWriter out =null;
		if (b) {
			    String message = "{'message':'success'}";  
		        JSONObject json = JSONObject.fromObject(message);
		        System.out.print(json);
		        System.out.close();
		        try{
		        out = res.getWriter();
		        out.write(json.toString());
		        out.flush();
		        out.close();
		        }catch (IOException e) {  
		            e.printStackTrace();  
		        } finally {  
		            if (out != null) {  
		                out.close();  
		            } 
		        } 
		} else {
			 String message = "{'message':'false'}";  
		        JSONObject json = JSONObject.fromObject(message);
		        System.out.print(json);
		        System.out.close();
		        try{
		        out = res.getWriter();
		        out.write(json.toString());
		        out.flush();
		        out.close();
		        }catch (IOException e) {  
		            e.printStackTrace();  
		        } finally {  
		            if (out != null) {  
		                out.close();  
		            } 
		        } 
		}
	}

	@RequestMapping("/updateSellerinfo")
	public String updateUserinfo(HttpServletRequest req, seller seller) {

		seller s = (seller) req.getSession().getAttribute("seller");
		s.setSeller_Name(seller.getSeller_Name());
		s.setSeller_Address(seller.getSeller_Address());
		s.setSeller_Telephone(seller.getSeller_Telephone());
		s.setSeller_Email(seller.getSeller_Email());
		s.setSeller_Username(seller.getSeller_Username());
		s.setSeller_Password(seller.getSeller_Password());
		s.setSeller_Status(seller.getSeller_Status());
		s.setIndustryType_id(seller.getIndustryType_id());
		s.setSeller_Logo(seller.getSeller_Logo());
		s.setSeller_Description(seller.getSeller_Description());

		boolean b = sellerManagementServiceImp.updateSellerinfo(s);

		if (b) {
			req.getSession().removeAttribute("seller");

			return "login";
		} else {
			return "exit";
		}
	}

	@RequestMapping("/updateSellermail")
	public String updateSelleremail(HttpServletRequest req, String Seller_Email) {

		seller seller = (seller) req.getSession().getAttribute("seller");

		boolean b = sellerManagementServiceImp.updateEmail(seller.getSeller_id(), Seller_Email);

		if (b) {
			req.getSession().removeAttribute("seller");

			return "login";
		} else {
			return "exit";
		}
	}

	@RequestMapping("/updateSellerphone")
	public String updateSellerephone(HttpServletRequest req, String Seller_Telephone) {

		seller seller = (seller) req.getSession().getAttribute("seller");
		System.out.println("AAA Bakary：" + Seller_Telephone);
		boolean b = sellerManagementServiceImp.updatePhone(seller.getSeller_id(), Seller_Telephone);

		if (b) {
			req.getSession().removeAttribute("seller");

			return "login";
		} else {
			return "exit";
		}
	}

	@RequestMapping("/updatePassword")
	public String updatePassword(HttpServletRequest req, String Seller_Password) {

		seller seller = (seller) req.getSession().getAttribute("seller");

		boolean b = sellerManagementServiceImp.updatePassword(seller.getSeller_id(), MD5Util.MD5(Seller_Password));

		if (b) {
			req.getSession().removeAttribute("seller");
			return "login";
		} else {
			return "exit";
		}
	}


}
