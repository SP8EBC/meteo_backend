package cc.pogoda.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.SummaryDao;
import cc.pogoda.backend.dao.TelemetryDao;
import cc.pogoda.backend.types.HumidityQualityFactor;
import cc.pogoda.backend.types.PressureQualityFactor;
import cc.pogoda.backend.types.TemperatureQualityFactor;
import cc.pogoda.backend.types.WindQualityFactor;
import cc.pogoda.backend.types.model.Telemetry;
import cc.pogoda.backend.types.view.Summary;

@RestController
public class SummaryController {

	@Autowired
	SummaryDao dao;
	
	@Autowired
	TelemetryDao telemetry;
	
	@RequestMapping(value = "/summary", produces = "application/json;charset=UTF-8")
	public Summary summaryControler(@RequestParam(value="station")String station) {
		Summary s = dao.getSummaryPerStationName(station);
		
		Telemetry t = telemetry.getTelemetryFromStationName(station);
		
		WindQualityFactor wind_qf =  WindQualityFactor.fromBits((byte) t.digital, 1);
		TemperatureQualityFactor temperature_qf = TemperatureQualityFactor.fromBits((byte) t.digital, 1);
		PressureQualityFactor pressure_qf = PressureQualityFactor.fromBits((byte) t.digital, 1);
		HumidityQualityFactor humidity_qf = HumidityQualityFactor.fromBits((byte) t.digital, 1);
		
		s.qnh_qf = pressure_qf.toString();
		s.wind_qf = temperature_qf.toString();
		s.temperature_qf = wind_qf.toString();
		s.humidity_qf = humidity_qf.toString();
		
		return s;
		
	}
}
