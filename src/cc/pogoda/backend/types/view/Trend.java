package cc.pogoda.backend.types.view;

import cc.pogoda.backend.types.TrendData;

public class Trend {

	public long currentTimestampUtc;
	
	public TrendData temperatureTrend;
	
	public TrendData humidityTrend;
	
	public TrendData pressureTrend;
	
	public TrendData averageWindspeedTrend;
	
	public TrendData maximumWindpseedTrend;
	
	public Trend() {
		temperatureTrend = new TrendData();
		humidityTrend = new TrendData();
		pressureTrend = new TrendData();
		averageWindspeedTrend = new TrendData();
		maximumWindpseedTrend = new TrendData();
	}
}
