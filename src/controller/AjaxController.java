package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.seller;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import service.SellerManagementService;
//dash
@Controller()
public class AjaxController {
	@Resource(name = "sellerManagementServiceImp")
	private SellerManagementService sellerManagementServiceImp;

		
}
