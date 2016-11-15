package service;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Notification;
import model.Request;
import model.User;
import model.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UserDaoImpl;
import utils.UserCommonUtil;
import utils.MD5Util;

@Controller  
@RequestMapping(value ="/user")
public class UserService {  
	
	public void check_token(int u_id,String u_token,HttpServletResponse response) throws IOException,SQLException{
		UserDaoImpl impl = new UserDaoImpl();
		if(!impl.token_auth(u_id, u_token)){
			response.setCharacterEncoding("UTF-8"); 
	        response.setContentType("text/json");
			PrintWriter out = response.getWriter();
			Response rs = new Response(1,"authorization failed",new Object());
			JSONObject json = JSONObject.fromObject( rs );
            out.print(json);
            out.close();
            //response.sendError(401);
		}
	}
	
	public String filter(String input){
		if(input == null){
			return "";
		}
		input = input.replaceAll("'","");
		input = input.replaceAll("\\\\","");
		input = input.replaceAll(";","");
		input = input.replaceAll("<","");
		input = input.replaceAll(">","");
		input = input.replaceAll("%","");
		input = input.replaceAll("#","");
		return input;
	}

    @RequestMapping(value = "/register", method = RequestMethod.POST)  
    @ResponseBody
    public void register(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException{
        response.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/json");
        String name = request.getParameter("u_name");
        String password = request.getParameter("u_pw_hash");
        String fullname = request.getParameter("u_fullname");
        String email = request.getParameter("u_email");
        String wechat = request.getParameter("u_wechat_id");
        User user = new User();
        //user.setId(UserCommonUtil.getcount()+1);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setFullname(fullname);
        user.setWechatid(wechat);
        user.setToken(MD5Util.MD5(name+password));
        UserDaoImpl impl = new UserDaoImpl();
        String status = impl.add(user);
        PrintWriter out = response.getWriter();
        
        if (status == "ok") {
        	user = impl.findByName(name);
        	Response rs = new Response(0,"",user);
        	JSONObject json = JSONObject.fromObject( rs );
            out.print(json);
		}else if (status == "error") {
			Response rs = new Response(5,"username repeated",new Object());
			JSONObject json = JSONObject.fromObject( rs );
	        out.print(json);
		}else{
			Response rs = new Response(6,"internal server error",new Object());
			JSONObject json = JSONObject.fromObject( rs );
	        out.print(json);
		}
        out.close();
       
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)  
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
    	response.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
    	String name = request.getParameter("u_name");
    	String password = request.getParameter("u_pw_hash");
    	UserDaoImpl impl = new UserDaoImpl();
    	int flag = impl.checklogin(name, password);
    	if(flag==0){
    		Response rs = new Response(5,"username or password is not correct",new Object());
    		JSONObject json = JSONObject.fromObject( rs );
    		out.print(json);
    		
    	}
    	else {
    		User user = impl.findById(flag);
    		Response rs = new Response(0,"",user);
    		JSONObject json = JSONObject.fromObject(rs);
    		out.print(json);
    	}
    	
    	out.close();
		return;
    }
    
    @RequestMapping(value = "/modify", method = RequestMethod.POST)  
    @ResponseBody
    public void modify(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException{
        response.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/json");
        int id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String wechat = request.getParameter("wechat");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setFullname(fullname);
        user.setWechatid(wechat);
        UserDaoImpl impl = new UserDaoImpl();
        impl.update(user);
    	PrintWriter out = response.getWriter();
        String message = "{'message':'success'}";  
        JSONObject json = JSONObject.fromObject( message );
        out.print(json);
        out.close();
    }
    
    //by zhuyifan 
    @RequestMapping(value = "/all_records", method = RequestMethod.GET)  
    @ResponseBody
    public void allRecords(HttpServletRequest request,HttpServletResponse response) throws IOException,SQLException{
    	response.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/json");
        int id = Integer.valueOf(request.getParameter("u_id"));
        String token = filter(request.getParameter("u_token"));
        check_token(id,token,response);
        UserDaoImpl impl = new UserDaoImpl();
        ArrayList<Request> requestList = impl.findRequests(id, "");
        
        PrintWriter out = response.getWriter();
        JSONArray result = JSONArray.fromObject(requestList);
        Response rs = new Response(0,"",result);
		JSONObject json = JSONObject.fromObject(rs);
		out.print(json);
    }
    
  //by zhuyifan 
    @RequestMapping(value = "/read_notification", method = RequestMethod.GET)  
    @ResponseBody
    public void readNotification(HttpServletRequest request,HttpServletResponse response) throws IOException,SQLException{
    	response.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/json");
        int id = Integer.valueOf(request.getParameter("u_id"));
        String token = filter(request.getParameter("u_token"));
        check_token(id,token,response);
        UserDaoImpl impl = new UserDaoImpl();
        ArrayList<Notification> notificationList = impl.findNotifications(id, "");
        
        PrintWriter out = response.getWriter();
        JSONArray result = JSONArray.fromObject(notificationList);
        Response rs = new Response(0,"",result);
		JSONObject json = JSONObject.fromObject(rs);
		out.print(json);
    }
    
    //by zhuyifan 
    @RequestMapping(value = "/seen_notification", method = RequestMethod.POST)  
    @ResponseBody
    public void seenNotification(HttpServletRequest request,HttpServletResponse response) throws IOException,SQLException{
    	response.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/json");
        int id = Integer.valueOf(request.getParameter("u_id"));
        String token = filter(request.getParameter("u_token"));
        int nId = Integer.valueOf(request.getParameter("n_id"));
        check_token(id,token,response);
        UserDaoImpl impl = new UserDaoImpl();
        impl.setNotifications(nId, "1");
        
        PrintWriter out = response.getWriter();
        Response rs = new Response(0,"","operation succeed");
		JSONObject json = JSONObject.fromObject(rs);
		out.print(json);
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)  
    @ResponseBody
    public void test(HttpServletResponse response) throws IOException{
    	 response.setCharacterEncoding("UTF-8"); 
         response.setContentType("text/json");
         PrintWriter out = response.getWriter();
         Response rs = new Response(0,"test ok",new Object());
         JSONObject json = JSONObject.fromObject( rs );
         out.print(json.toString());
         out.close();
    }
  
}  