package cc.pogoda.backend.types.view.parameteo;

public class PmeteoMeasurements {

	public short temperature_dallas;
	
	public short temperature_pt;
	
	public short temperature_internal;
	
	public short temperature_modbus;
	
	public short pressure;
	
	public short humidity;
	
	public short wind_direction;
	
	public short wind_average;
	
	public short wind_gust;
	
	public short wind_current;
	
	public short wind_minimal;
	
	public short pm2_5;
	
	public short pm10;
		
	public int master_time;
		
	public String api_mac;
	
	public int dallas_qf_curr;
	
	public int dallas_qf_err;
	
	public int rtu_io_errors;
	
	public String toString() {
		return "[PmeteoMeasurements][temperature_dallas = " + temperature_dallas +"][pressure = " + pressure +"][wind_direction = " + wind_direction +"]";

	}
	/*
	 * {
"temperature_dallas":248,
"temperature_pt":0,
"temperature_internal":292,
"temperature_modbus":269,
"pressure":9402,
"humidity":56,
"wind_direction":261,
"wind_average":0,
"wind_gust":0,
"wind_current":0,
"wind_minimal":0,
"pm2_5":21,
"pm10":5,
"ssid":1,
"master_time":232250,
"callsign":"ABAKUS",
"api_mac":"00000000000000000000000000000000"} 
	 * 
	 * 
	 */
}

