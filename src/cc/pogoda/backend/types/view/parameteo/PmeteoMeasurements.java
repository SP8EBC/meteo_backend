package cc.pogoda.backend.types.view.parameteo;

public class PmeteoMeasurements {

	short temperature_dallas;
	
	short temperature_pt;
	
	short temperature_internal;
	
	short pressure;
	
	short humidity;
	
	short wind_direction;
	
	short wind_average;
	
	short wind_gust;
	
	short wind_current;
	
	short wind_minimal;
	
	int pwm_first;
	
	int pwm_second;
	
	public String toString() {
		return "[PmeteoMeasurements][temperature_dallas = " + temperature_dallas +"][pressure = " + pressure +"][wind_direction = " + wind_direction +"]";

	}
}
