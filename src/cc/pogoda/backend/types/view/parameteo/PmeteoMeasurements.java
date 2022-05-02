package cc.pogoda.backend.types.view.parameteo;

public class PmeteoMeasurements {

	short temperature_dallas;
	
	short temperature_pt;
	
	short temperature_internal;
	
	short pressure;
	
	short humidity;
	
	short wind_direction;
	
	short wind_average;
	
	short wind_current_instantaneous;
	
	short wind_minimal;
	
	short wind_gust;
	
	public String toString() {
		return "[PmeteoMeasurements][temperature_dallas = " + temperature_dallas +"][pressure = " + pressure +"][wind_direction = " + wind_direction +"]";

	}
}
