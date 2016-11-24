package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import sun.org.mozilla.javascript.internal.ObjArray;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.AmountPoint;
import model.SellerStatusInfo;
import model.Seller_transferInfoBean;
import model.TransferRecord;
import model.industry_type;
@Service("SellerTranserServiceImp")
public class SellerTranserServiceImp implements SellerTranserService {

	private final static String URL="http://tangmocd.cn/seller/v1.0.0/index.php?s=/home/index/points/";
	
	// 声明sessionFactory
			@Resource(name = "sessionFactory")
			private SessionFactory sessionFactory;
			private Session session;
			// 获取当前session的方法
			private Session getSession() {

				if (session == null) {
					session = sessionFactory.openSession();
				} else {
					session = sessionFactory.getCurrentSession();
				}
				return session;
			}
	/*
	 * 获取积分交易记录列表
	 *
	 * */
	public List<Object> querypointrecord(int sellerid,String time1,String time2,int timelen) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
		
		String hql="select  Pointtransfer_Time as time ,POintTransfer_Points as points,PointTransfer_Type as type,user.u_name as username  "
				+" from  pointTransfer "
				+" join user on user.u_id=pointTransfer.User_id "
				+" where seller_id= "+sellerid;
		
		if(("".equals(time1)||time1==null)||("".equals(time2)||time2==null)){
			//只要有一个为空，则不进行时间戳的拦截
			hql=hql+";";
		}
		else{
			time1=time1+" 00:00:00";
			time2=time2+" 00:00:00";
			Date time1data=df.parse(time1);		
			Date time2date=df.parse(time2);
			Timestamp timstamp1=new Timestamp(time1data.getTime());
			Timestamp timstamp2=new Timestamp(time2date.getTime());
			hql=hql+" and date(PointTransfer_Time) between \'"+time1+"\' and \'"+time2+"\' ;";
		}
						
		return getSession().createSQLQuery(hql).list();
	}
	/*
	 * 交换积分
	 * */
	public SellerStatusInfo point(Seller_transferInfoBean transferbean) {
		//String name,String password,String points,String tradetype,String seller_id
		   String str=connect(transferbean.getName(),transferbean.getPassword(),transferbean.getPoints(), transferbean.getTrade_type(), transferbean.getSeller_id());
		   SellerStatusInfo statusinfo=JSON.parseObject(str,SellerStatusInfo.class);	
		  
		   //插入记录
		   if(statusinfo.getStatus()==0&&("0".equalsIgnoreCase(transferbean.getTrade_type())||"1".equalsIgnoreCase(transferbean.getTrade_type()))){
			   //转移积分成功,这样才记录
		     Session session = getSession();
		     Transaction tr = null;
		     Connection con = null; 
		     PreparedStatement ps = null; 
		     
			   String sql="insert into pointTransfer ( "
                            +" PointTransfer_Time,PointTransfer_Points,PointTransfer_Type,User_id,Seller_id,SellerPointSystem_Username "
				   			+" ) "
					   		+" values "
					   		+" ( "
					   		+"	'"+transferbean.getCreatetime()+"',"+transferbean.getPoints()+","+transferbean.getTrade_type()+","+transferbean.getUserid()+","+transferbean.getSeller_id()+",'"+transferbean.getName()+"' "
					   		+"	);";
			   
	            Query customerinsert=session.createSQLQuery(sql);
	            customerinsert.executeUpdate();
		   
		   }
		   
		   return  statusinfo;
	}
	
	
	
	
	

	
	@Override
	public AmountPoint questamountPoint(int sellerid,String time1,String time2) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
		String hql =null;
		
		if((time1==null||"".equals(time1))||(time2==null||"".equals(time2))){
			//只要有一个为空，则不进行时间戳的拦截
			hql = "select a.income,b.outcome from "
					+" (select sum(POintTransfer_Points) as income from pointTransfer "
							+" where PointTransfer_Type=1 and seller_id="+sellerid+" ) a, "
							+" (select sum(POintTransfer_Points) as outcome from pointTransfer "
							+" where PointTransfer_Type=0 and seller_id="+sellerid+") b "
							+" ;";
		}
		else{
			time1=time1+" 00:00:00";
			time2=time2+" 00:00:00";
			Date time1data=df.parse(time1);		
			Date time2date=df.parse(time2);
			Timestamp timstamp1=new Timestamp(time1data.getTime());
			Timestamp timstamp2=new Timestamp(time2date.getTime());
			
			hql = "select a.income,b.outcome from "
					+" (select sum(POintTransfer_Points) as income from pointTransfer "
							+" where PointTransfer_Type=1 and seller_id="+sellerid+" and date(PointTransfer_Time) between \'"+time1+"\' and \'"+time2+"\' ) a, "
							+" (select sum(POintTransfer_Points) as outcome from pointTransfer "
							+" where PointTransfer_Type=0 and seller_id="+sellerid+" and date(PointTransfer_Time) between \'"+time1+"\' and \'"+time2+"\') b "
							+" ;";

		}
		
		List<Object> obj=getSession().createSQLQuery(hql).list();
		if(obj!=null&&obj.size()>0){
			Object[] result=(Object[]) obj.get(0);
			AmountPoint point=new AmountPoint();
			point.setIncome((BigDecimal)result[0]);
			point.setOutcome((BigDecimal)result[1]);
			
			return point;
		}else{
			return null;
		}
		
		
		
	}
	private static String getResponse(String requsetUrl, String content) {
        try {
            URL url = new URL(requsetUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true); // 使用 URL 连接进行输出
            httpConn.setDoInput(true); // 使用 URL 连接进行输入
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setUseCaches(false); // 忽略缓存
            httpConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpConn.setRequestMethod("POST"); // 设置URL请求方法
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(content.getBytes("UTF-8"));
            outputStream.close();
            BufferedReader responseReader = new BufferedReader(
                    new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String readLine;
            StringBuffer responseSb = new StringBuffer();
            while ((readLine = responseReader.readLine()) != null) {
                responseSb.append(readLine);
            }
            return responseSb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }

    }
	
    private static String connect(String name,String password,int points,String tradetype,String seller_id){
	        StringBuilder param=new StringBuilder();
	        param.append("name="+name+"&");
	        param.append("password="+password+"&");
	        param.append("trade_type="+tradetype+"&");
	        param.append("seller_id="+seller_id+"&");
	        param.append("points="+points);
	        
	      
	        return getResponse(URL,param.toString());
	    }
	
	
}
