package cc.pogoda.backend.types.view.parameteo;

public class PmeteoStatus {
	
	public String main_config_data_basic_callsign;
	
	public byte main_config_data_basic_ssid;
	
	public long master_time;
	
	public byte main_cpu_load;
	
	public short rx10m;
	
	public short tx10m;
	
	public short digi10m;
	
	public short digidrop10m;
	
	public short kiss10m;
	
	public int rte_main_rx_total;
	
	public int rte_main_tx_total;
	
	public int rte_main_average_battery_voltage;
	
	public short rte_main_wakeup_count;
	
	public short rte_main_going_sleep_count;
	
	public short rte_main_last_sleep_master_time;
	
	@Override
	public String toString() {
		return "[PmeteoStatus][main_config_data_basic_callsign = " + main_config_data_basic_callsign +"][main_config_data_basic_ssid = " + main_config_data_basic_ssid +"][master_time = " + master_time +"]";
	}
}
