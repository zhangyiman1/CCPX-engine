package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.seller;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
				//Cookie cookie=new Cookie("sellerid",String.valueOf(Seller.getSeller_id()));
				//res.addCookie(cookie); {'message':'success','sellerid':'sellerid','sellername':'sellername'}
		        String sellerid = String.valueOf(Seller.getSeller_id());
		        String sellername = Seller.getSeller_Name();
		        String message = "{'message':'success','sellerid':'"+sellerid+"','sellername':'"+sellername+"'}";
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
	
	@RequestMapping("checkActivationCode")
	@ResponseBody
	public void checkActivationCode(HttpServletRequest req, HttpServletResponse res,
			String code, String sellerid) {
			boolean b = sellerManagementServiceImp.checkActivationCode(code,sellerid);
			res.setCharacterEncoding("UTF-8"); 
	        res.setContentType("text/json");
	        PrintWriter out =null;
			if (b) {
				//修改Seller Status
				boolean r = sellerManagementServiceImp.updateSellerStatus(sellerid);
				
				if(r){
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
				}else{
					String message = "{'message':'You are already a member'}";  
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
				
			}else {
		        String message = "{'message':'activation code is not correct'}";  
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

	@RequestMapping("/validateSeller_Username")
	@ResponseBody
	public void validateSeller_Username(HttpServletRequest req,
			HttpServletResponse res, String username){
		seller Seller = sellerManagementServiceImp.validateUsername(username);
		res.setCharacterEncoding("UTF-8"); 
        res.setContentType("text/json");
        PrintWriter out =null;
		if (Seller != null) {
	        String message = "{'message':'exist'}";  
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
	        String message = "{'message':'not exist'}";  
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
	public void registSeller(HttpServletRequest req, HttpServletResponse res, String seller_Name,
			String seller_Address, String seller_Telephone, String seller_Email, String seller_Username,
			String seller_Password, String industryType_id, MultipartFile Logo,
			String seller_Description){
		
		    seller Seller = new seller();
		    Seller.setSeller_Name(seller_Name);
		    Seller.setSeller_Address(seller_Address);
		    Seller.setSeller_Telephone(seller_Telephone);
		    Seller.setSeller_Email(seller_Email);
	    	Seller.setSeller_Username(seller_Username);
		    Seller.setSeller_Password(seller_Password);
	     	Seller.setIndustryType_id(industryType_id);
	    	Seller.setSeller_Description(seller_Description);
	    	Seller.setSeller_Status("0");
		    PrintWriter out =null;
	     	res.setCharacterEncoding("UTF-8"); 
            res.setContentType("text/json");
        
	        String path = null;
	        String type = null;
	        String fileName = Logo.getOriginalFilename();
	        type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
	        

	                if (Logo.getSize()<2000000) {
	                	 type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
	                	 if (type!=null) {
	                		 if ("PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {//图片格式正确，执行regist操作
	                			String realPath=req.getSession().getServletContext().getRealPath("/")+"images\\";
	     		                String trueFileName=seller_Username+"."+type;
	     		                path=realPath+trueFileName;
	     		                System.out.println("存放图片文件的路径:"+path);
	     		                try{
	     		                Logo.transferTo(new File(path));}
	     		                catch (IOException e) {  
	     	       		            e.printStackTrace();  
	     	       		        } finally {  
	     	       		        } 
	     		                Seller.setSeller_Logo("images\\"+trueFileName);
	     		                boolean b = sellerManagementServiceImp.regist(Seller);
	     		               if (b) {
	   	           			    String message = "success";
	   	           		        try{
	   	           		        out = res.getWriter();
	   	           		        out.write(message);
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
	   	           			    String message = "false_exception";  
	   	           		        try{
	   	           		        out = res.getWriter();
	   	           		        out.write(message);
	   	           		        out.flush();
	   	           		        out.close();
	   	           		        }catch (IOException e) {  
	   	           		            e.printStackTrace();  
	   	           		        } finally {  
	   	           		            if (out != null) {  
	   	           		                out.close();  
	   	           		            }//if 
	   	           		        } //finally
	   	           		}//else
	                		 }//图片格式正确，执行regist操作
	                		 else{//图片格式不正确
	                			    String message = "false_format_not_correct";  
		   	           		        try{
		   	           		        out = res.getWriter();
		   	           		        out.write(message);
		   	           		        out.flush();
		   	           		        out.close();
		   	           		        }catch (IOException e) {  
		   	           		            e.printStackTrace();  
		   	           		        } finally {  
		   	           		            if (out != null) {  
		   	           		                out.close();  
		   	           		            }//if 
		   	           		        } //finally
	                		 }//图片格式不正确
	                	 }//type!=null
	                	 else{//type==null
	                		 String message = "false_type_null";  
	   	           		        try{
	   	           		        out = res.getWriter();
	   	           		        out.write(message);
	   	           		        out.flush();
	   	           		        out.close();
	   	           		        }catch (IOException e) {  
	   	           		            e.printStackTrace();  
	   	           		        } finally {  
	   	           		            if (out != null) {  
	   	           		                out.close();  
	   	           		            }//if 
	   	           		        } //finally
	                	 }//type!==null
	                }
	                else{//Logo.getSize()>2000000
	                	String message = "false_size_too_big";  
	           		        try{
	           		        out = res.getWriter();
	           		        out.write(message);
	           		        out.flush();
	           		        out.close();
	           		        }catch (IOException e) {  
	           		            e.printStackTrace();  
	           		        } finally {  
	           		            if (out != null) {  
	           		                out.close();  
	           		            }//if 
	           		        } //finally
	                }//Logo.getSize()>2000000
		               
	                	
	                	
	                	
	                	
	                	
	                	
		
		
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
