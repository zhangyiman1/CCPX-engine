package dao;

import java.util.List;

import model.ExchangeRecord;

public interface ViewExchangePointDao {
	public double getToExPo(int seller_id, String start_time,String end_time);
	public List<ExchangeRecord> getLatExRec(int seller_id, int record_num);
	public List<ExchangeRecord> getSeExRec(int seller_id, int user_id, 
			double min_point, double max_point, String start_time, String end_time);
	public String getUserNameByUserId(int userId);
}
