package service;

import model.ExchangePointVO;

public interface ViewExchangePointService {

	public ExchangePointVO getExchangePoint(int sellerId, String startTime, String endTime);
	public ExchangePointVO getLatestExchangePoint(int sellerId);
}
