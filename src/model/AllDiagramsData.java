package model;

public class AllDiagramsData {

	private int errno;
	private String err;
	private HistogramData topSameIndustry;
	private HistogramData topDiffIndustry;
	private PieChartData industryRatio;
	private LineGraphData timeSequence;
	
	public int getErrno() {
		return errno;
	}
	public void setErrno(int errno) {
		this.errno = errno;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public HistogramData getTopSameIndustry() {
		return topSameIndustry;
	}
	public void setTopSameIndustry(HistogramData topSameIndustry) {
		this.topSameIndustry = topSameIndustry;
	}
	public HistogramData getTopDiffIndustry() {
		return topDiffIndustry;
	}
	public void setTopDiffIndustry(HistogramData topDiffIndustry) {
		this.topDiffIndustry = topDiffIndustry;
	}
	public PieChartData getIndustryRatio() {
		return industryRatio;
	}
	public void setIndustryRatio(PieChartData industryRatio) {
		this.industryRatio = industryRatio;
	}
	public LineGraphData getTimeSequence() {
		return timeSequence;
	}
	public void setTimeSequence(LineGraphData timeSequence) {
		this.timeSequence = timeSequence;
	}
	
}
