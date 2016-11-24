package model;

import java.util.List;

public class HistogramData {

	private String yAxisTitleText;
	private List seriesData;
	public String getyAxisTitleText() {
		return yAxisTitleText;
	}
	public void setyAxisTitleText(String yAxisTitleText) {
		this.yAxisTitleText = yAxisTitleText;
	}
	public List getSeriesData() {
		return seriesData;
	}
	public void setSeriesData(List seriesData) {
		this.seriesData = seriesData;
	}
	
}
