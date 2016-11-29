package dao;

import java.util.List;

import model.ExchangeRecord;

public interface IntelligentAnalysisDao {
	public List<ExchangeRecord> getIAExRec(int sellerId, String startTime, String endTime);
	public String getIndustryTypeBySellerId(int sellerId);
	public String getSellerNameBySellerId(int sellerId);
}
