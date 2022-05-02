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
		model.mainConfigDataBasicCallsign = view.callsign;
		model.mainConfigDataBasicSsid = view.ssid;
		model.masterTime = view.master_time;
		model.mainCpuLoad = view.cpu_load;
		model.rx10m = view.rx_10m;
		model.tx10m = view.tx_10m;
		model.digi10m = view.digi_10m;
		model.digidrop10m = view.digi_drop_10m;
		model.kiss10m = view.kiss_10m;
		model.rteMainAverageBatteryVoltage = view.average_battery_voltage;
		model.rteMainWakeupCount = view.wakeup_count;
		model.rteMainGoingSleepCount = view.going_sleep_count;
		model.rteMainLastSleepMasterTime = view.last_sleep_master_time;
		
		statusRepo.save(model);
	}
	
	
}
