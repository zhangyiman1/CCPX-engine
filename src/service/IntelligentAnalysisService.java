package service;

import model.AllDiagramsData;

public interface IntelligentAnalysisService {

	public AllDiagramsData getTradePointDiagramsData(int sellerId, String startTime, String endTime);
	public AllDiagramsData getTradeCountDiagramsData(int sellerId, String startTime, String endTime);
}
