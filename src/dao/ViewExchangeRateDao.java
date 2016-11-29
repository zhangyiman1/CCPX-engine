package dao;

import java.util.List;

import model.ExchangeRecord;

public interface ViewExchangeRateDao {
	public List<ExchangeRecord> getRaExRec(int seller_A_id, int seller_B_id, String start_time,String end_time);
}
