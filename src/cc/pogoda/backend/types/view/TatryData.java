package cc.pogoda.backend.types.view;

public class TatryData {

	public int id;
	
	public long epoch;
	
	public String warsaw_local_date_time_excel_fmt;
	
	public String station;
	
	public float temperature_from_wx_frame;

	public int raw_measurement_from_telemetry_frame;
	
	public float calculated_measurement_from_telemetry_frame;
	
	public float voltage;
	
	public int max31865_status;

	public boolean lse_rdy;

	public boolean rtc_en;
	
	public boolean max31865_init_ok;
	
	public boolean sleep;
	
	public boolean spi_comm_error;
	
	public boolean spi_comm_ok;
	
	public String aprs_frame_type;
	
	public String aprs_frame_content;

	public String receiving_aprs_gate_callsign;
	
	public int receiving_aprs_gate_ssid;
		
	public String db_insert_warsaw_local_date_time_excel_fmt;
}
