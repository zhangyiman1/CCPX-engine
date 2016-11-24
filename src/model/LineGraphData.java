package model;

import java.util.ArrayList;

public class LineGraphData {

	private ArrayList<String> xAxisCategories;
	private String yAxisTitleText;
	private ArrayList<Double> seriesData;
	
	public ArrayList<String> getxAxisCategories() {
		return xAxisCategories;
	}
	public void setxAxisCategories(ArrayList<String> xAxisCategories) {
		this.xAxisCategories = xAxisCategories;
	}
	public String getyAxisTitleText() {
		return yAxisTitleText;
	}
	public void setyAxisTitleText(String yAxisTitleText) {
		this.yAxisTitleText = yAxisTitleText;
	}
	public ArrayList<Double> getSeriesData() {
		return seriesData;
	}
	public void setSeriesData(ArrayList<Double> seriesData) {
		this.seriesData = seriesData;
	}
	
}
