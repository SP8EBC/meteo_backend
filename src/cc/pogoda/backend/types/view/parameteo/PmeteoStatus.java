package cc.pogoda.backend.types.view.parameteo;

public class PmeteoStatus {
	
	public String sw_ver;
	
	public String sw_date;
	
	public String callsign;
	
	public byte ssid;
	
	public long master_time;
	
	public byte cpu_load;
	
	public short rx_10m;
	
	public short tx_10m;
	
	public short digi_10m;
	
	public short digi_drop_10m;
	
	public short kiss_10m;
	
	public int rx_total;
	
	public int tx_total;
	
	public int average_battery_voltage;
	
	/**
	 * Part of registers_counter (backup register 4)
	 */
	public short wakeup_count;
	
	/**
	 * Part of registers_counter (backup register 4)
	 */
	public short going_sleep_count;
	
	public short last_sleep_master_time;
	
	@Override
	public String toString() {
		return "[PmeteoStatus][main_config_data_basic_callsign = " + callsign +"][main_config_data_basic_ssid = " + ssid +"][master_time = " + master_time +"]";
	}
}
