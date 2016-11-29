package service;

import model.ExchangeRateVO;

public interface ViewExchangeRateService {

	public ExchangeRateVO getExchangeRates(int sellerId, String startTime, String endTime);
	
}
