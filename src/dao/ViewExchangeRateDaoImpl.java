package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import test.TestInterfaceOfBlockChain;
import utils.HttpUtil;

import com.alibaba.fastjson.JSON;

import model.ExchangeRecord;

@Repository("ExchangeRateDaoImpl")
public class ViewExchangeRateDaoImpl implements ViewExchangeRateDao{

	static Logger log=Logger.getLogger(IntelligentAnalysisDaoImpl.class);
	
	@Override
	public List<ExchangeRecord> getRaExRec(int seller_A_id, int seller_B_id,
			String start_time, String end_time) {

		List<ExchangeRecord> recs=new ArrayList<ExchangeRecord>();
		
		try{
			// send request to blockchain then get data
			String url="http://ccpx-blockchain.mybluemix.net/getRaExRec";
			String param="SELLER_A_ID="+seller_A_id+"&SELLER_B_ID="+seller_B_id+"&START_TIME="+start_time+"&END_TIME="+end_time;
			String result=HttpUtil.sendPost(url, param);
			
			//just for test
			//String result=TestInterfaceOfBlockChain.getIAExRec();
			
			recs=JSON.parseArray(result, ExchangeRecord.class);
		}catch(Exception e){
			log.error(e.toString());
		}
		
		return recs;
	}

}
