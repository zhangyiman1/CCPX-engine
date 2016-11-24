package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import model.AmountPoint;
import model.SellerStatusInfo;
import model.Seller_transferInfoBean;
import model.TransferRecord;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.SellerTranserService;

@Controller
@RequestMapping("seller")
public class SellerTransferController extends SellerBaseController {

	@Resource(name="SellerTranserServiceImp")
	private SellerTranserService transferserviceImp;
	
	
	@RequestMapping(value="transfer",method=RequestMethod.POST)
	@ResponseBody
	public SellerStatusInfo transferpoint(Seller_transferInfoBean transferbean){
		
		SellerStatusInfo si = super.CreateStatus();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
		String nowtime=df.format(new Date());
		transferbean.setCreatetime(nowtime);
		if(transferbean.getUserid()==null||"".equalsIgnoreCase(transferbean.getUserid())){
			si.setMsg("invalid parameter ");
			si.setStatus(1);
			return si;
		}
		//设置时间
		
		si=transferserviceImp.point(transferbean);
		
		logger.warn(transferbean.toString());
		return si;
	}
	@RequestMapping(value="listrecord",method=RequestMethod.POST)
	@ResponseBody
	public SellerStatusInfo transrecord(int sellerid,String time1,String time2,int timelen) throws ParseException{
		SellerStatusInfo si = super.CreateStatus();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
		logger.warn(time2+" "+time1+" "+timelen);
		
		List<Object>  records=transferserviceImp.querypointrecord(sellerid,time1,time2,timelen);
		List<TransferRecord> recordresult=new ArrayList<TransferRecord>();
		
		
		
		TransferRecord record=null;
		for(int i=0;i<records.size();i++){
			Object[] obj=(Object[]) records.get(i);
			record=new TransferRecord();
			 record.setTime((String)df.format(obj[0]));
			 record.setPoints(Integer.parseInt(obj[1].toString()));
			 record.setType(Boolean.parseBoolean(obj[2].toString())?"income":"outcome");
			 record.setUsername((String)obj[3]);	
			 recordresult.add(record);		
		}
		si.setData(recordresult);
		return si;
	}
	@RequestMapping(value="amoutpoint",method=RequestMethod.POST)
	@ResponseBody
	public SellerStatusInfo amountpoint(int sellerid,String time1,String time2) throws ParseException{
		SellerStatusInfo si = super.CreateStatus();
		
		AmountPoint points=transferserviceImp.questamountPoint(sellerid,time1,time2);
		si.setData(points);
		return si;
	}
	
}
