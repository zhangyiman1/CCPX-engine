package controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.advertisement;
import model.seller;


import service.*;

@Controller
public class AdController {
	@Resource(name = "AdServiceImp")
	private AdService adservice =  new AdServiceImp();
	 String path ;
	 
	 
	@RequestMapping("/upload.do")//???
	public String uploadAd(@RequestParam("adtitle") String adtitle,HttpServletRequest req,HttpServletResponse res,MultipartFile file, String seller_id) throws IllegalStateException, IOException
	{
		
		if (adtitle.length()>40||adtitle.equals("not filled"))
		{
			req.setAttribute("msg", "exceed length limit40 or adtitle=null");
		    return "uploadad";
		}
			
		advertisement ad=adservice.checkAd(adtitle);
		
		if(ad!=null)
		{
			req.setAttribute("msg", "repeated adtitle");
		    return "uploadad";
		}
		
		 if (file.getSize()<2000000) {// 判断上传的文件是否为空
	            String path=null;// 文件路径
	            String type=null;// 文件类型
	            String fileName=file.getOriginalFilename();// 文件原名称
	            System.out.println("上传的文件原名称:"+fileName);
	            // 判断文件类型
	            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
	            if (type!=null) {// 判断文件类型是否为空
	                if ("PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
	                    // 项目在容器中实际发布运行的根路径
	                    String realPath=req.getSession().getServletContext().getRealPath("/")+"images\\";
	                    // 自定义的文件名称
	                    String trueFileName=adtitle+"."+type;
	                    // 设置存放图片文件的路径
	                    path=realPath+/*System.getProperty("file.separator")+*/trueFileName;
	                    System.out.println("存放图片文件的路径:"+path);
	                    // 转存文件到指定的路径
	                    file.transferTo(new File(path));
	                    System.out.println("文件成功上传到指定目录下");
	                    System.out.println(file.getSize());
	                    this.path = path;
	                }else {
	                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
	                    req.setAttribute("msg", "not correct type(png,jpg)");
	                    return "uploadad";
	                }
	            }else {
	                System.out.println("文件类型为空");
	                req.setAttribute("msg", "type cant = null");
	                return "uploadad";
	            }
	        }else {
//	            System.out.println("没有找到相对应的文件");
//	            req.setAttribute("msg", "file=null");
	        	System.out.println("文件太大");
                req.setAttribute("msg", "file bigger than 2mb");
	            return "uploadad";}
		
			
			
		seller seller = new seller();
		seller.setSeller_id(Integer.parseInt(seller_id));
		System.out.println(seller.getSeller_id());
//    	seller.setSeller_id(1);
//		seller.setSeller_Name("apple");
		
		
		adservice.uploadAd(seller,adtitle,path);
		req.setAttribute("msg", "upload success");
	    return "uploadad";
		
	}

}
