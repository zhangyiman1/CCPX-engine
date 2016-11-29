package test;

import java.util.ArrayList;
import java.util.List;

import service.ViewExchangeRateServiceImpl;
import utils.HttpUtil;
import model.ExchangeRecord;

import com.alibaba.fastjson.JSON;

import dao.ViewExchangePointDaoImpl;
import dao.ViewExchangeRateDaoImpl;

public class TestInterfaceOfBlockChain {

	/**
	 * test method
	 * @param args
	 */
	public static void main(String[] args) {
//		String sb=getDataFromBC();
//		List<ExchangeRecord> recs=new ArrayList<>();
//		String a=JSON.parseObject(sb).get("content").toString();
//		recs=JSON.parseArray(a, ExchangeRecord.class);
//		for(int i=0;i<recs.size();i++){
//			System.out.println(recs.get(i).toString());
//		}
		
//		String sb=getToExPo();
//		String content=JSON.parseObject(sb).get("content").toString();
//		String tol=JSON.parseObject(content).getString("TO_EX_PO");
//		System.out.println(tol);
		
		String res=HttpUtil.sendPost("http://ccpx-blockchain.mybluemix.net/getLatExRec", "SELLER_ID=123&RECORD_NUM=10");
		System.out.println(res);
		
//		ViewExchangePointDaoImpl vl=new ViewExchangePointDaoImpl();
//		double tol=vl.getToExPo(0, null, null);
//		System.out.println(tol);
//		List<ExchangeRecord> la=vl.getLatExRec(0, 0);
//		for(int i=0;i<la.size();i++){
//			System.out.println(la.get(i).toString());
//		}
//		List<ExchangeRecord> se=vl.getSeExRec(0, 0, 0, 0, null, null);
//		for(int i=0;i<se.size();i++){
//			System.out.println(se.get(i).toString());
//		}
	}
	
	public static String getDataFromBC(){
		
		// send request to blockchain then get data
		String url="http://ccpx-blockchain.mybluemix.net/getIAExRec";
		String param="SELLER_ID=123&START_TIME=2016/11/22 00:00:00&END_TIME=2016/11/22 12:00:00";
		String result=HttpUtil.sendPost(url, param);		
		System.out.println(result);
		
		return result;
	}
	
	public static String getIAExRec(){
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\"respond\":100,");
		sb.append("\"content\":");
		sb.append("[");
		sb.append("{\"USER_A_ID\":\"5\",\"SELLER_A_ID\":\"5\",\"POINT_A\":10,"
				+ "\"USER_B_ID\":\"6\",\"SELLER_B_ID\":\"6\",\"POINT_B\":110,\"EX_TIME\":\"2016/11/07 00:10:00\"}");
		sb.append(",");
		sb.append("{\"USER_A_ID\":\"5\",\"SELLER_A_ID\":\"6\",\"POINT_A\":20,"
				+ "\"USER_B_ID\":\"6\",\"SELLER_B_ID\":\"5\",\"POINT_B\":220,\"EX_TIME\":\"2016/11/07 00:15:00\"}");
		sb.append(",");
		sb.append("{\"USER_A_ID\":\"5\",\"SELLER_A_ID\":\"5\",\"POINT_A\":30,"
				+ "\"USER_B_ID\":\"6\",\"SELLER_B_ID\":\"9\",\"POINT_B\":330,\"EX_TIME\":\"2016/11/07 00:20:00\"}");
		sb.append(",");
		sb.append("{\"USER_A_ID\":\"7\",\"SELLER_A_ID\":\"5\",\"POINT_A\":40,"
				+ "\"USER_B_ID\":\"6\",\"SELLER_B_ID\":\"23\",\"POINT_B\":440,\"EX_TIME\":\"2016/11/07 00:21:00\"}");
		sb.append(",");
		sb.append("{\"USER_A_ID\":\"8\",\"SELLER_A_ID\":\"5\",\"POINT_A\":50,"
				+ "\"USER_B_ID\":\"6\",\"SELLER_B_ID\":\"19\",\"POINT_B\":550,\"EX_TIME\":\"2016/11/07 00:26:00\"}");
		sb.append(",");
		sb.append("{\"USER_A_ID\":\"8\",\"SELLER_A_ID\":\"5\",\"POINT_A\":60,"
				+ "\"USER_B_ID\":\"6\",\"SELLER_B_ID\":\"13\",\"POINT_B\":660,\"EX_TIME\":\"2016/11/07 00:50:00\"}");
		sb.append(",");
		sb.append("{\"USER_A_ID\":\"8\",\"SELLER_A_ID\":\"5\",\"POINT_A\":70,"
				+ "\"USER_B_ID\":\"6\",\"SELLER_B_ID\":\"8\",\"POINT_B\":770,\"EX_TIME\":\"2016/11/07 00:00:00\"}");
		sb.append("]");
		sb.append("}");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static String getToExPo(){
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\"respond\":100,");
		sb.append("\"content\":");
		sb.append("{");
		sb.append("\"TO_EX_PO\":5000");
		sb.append("}");
		sb.append("}");
		System.out.println(sb.toString());
		return sb.toString();
	}
}
