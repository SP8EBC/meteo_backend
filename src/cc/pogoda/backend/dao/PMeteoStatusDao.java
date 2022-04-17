package cc.pogoda.backend.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cc.pogoda.backend.dao.repository.PMeteoStatusCrudRepo;
import cc.pogoda.backend.types.view.parameteo.PmeteoStatus;

@Repository
public class PMeteoStatusDao {

	@Autowired
	PMeteoStatusCrudRepo statusRepo;
	
	@PersistenceContext
	EntityManager em;
	
	public void updateOrInsertStatus(PmeteoStatus view, String station) {
		cc.pogoda.backend.types.model.parameteo.PmeteoStatus model;
		
		model = statusRepo.findByStation(station);
		
		if (model == null) {
			model = new cc.pogoda.backend.types.model.parameteo.PmeteoStatus();
			model.station = station;
		}
		
		model.timestamp = ZonedDateTime.now().toEpochSecond();
		model.mainConfigDataBasicCallsign = view.main_config_data_basic_callsign;
		model.mainConfigDataBasicSsid = view.main_config_data_basic_ssid;
		model.masterTime = view.master_time;
		model.mainCpuLoad = view.main_cpu_load;
		model.rx10m = view.rx10m;
		model.tx10m = view.tx10m;
		model.digi10m = view.digi10m;
		model.digidrop10m = view.digidrop10m;
		model.kiss10m = view.kiss10m;
		model.rteMainAverageBatteryVoltage = view.rte_main_average_battery_voltage;
		model.rteMainWakeupCount = view.rte_main_wakeup_count;
		model.rteMainGoingSleepCount = view.rte_main_going_sleep_count;
		model.rteMainLastSleepMasterTime = view.rte_main_last_sleep_master_time;
		
		statusRepo.save(model);
	}
	
	
}
