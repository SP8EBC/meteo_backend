package cc.pogoda.backend.controller;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneOffsetTransition;
import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.repository.TatryRepo;
import cc.pogoda.backend.types.model.TatryModel;
import cc.pogoda.backend.types.view.TatryData;

@RestController
public class TatryDataController {

	private @Autowired HttpServletRequest request;
	
    private static Logger logger = LogManager.getLogger();
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YY HH:mm");
    
    @Autowired
    TatryRepo tatryRepo;
    
	@RequestMapping(value = "/tatry/{stationName}/fivethousands", produces = "application/json;charset=UTF-8")
	public List<TatryData> getLongResultsForName(@PathVariable(required = true) String stationName) {
		logger.info("[getLongResultsForName][request.getRemoteAddr() = " + request.getRemoteAddr() + "][stationName = " + stationName +"]");
		
		List<TatryData> out = new LinkedList<TatryData>();
		
		List<TatryModel> m = tatryRepo.findFirst5000ByStationOrderByEpochDesc(stationName);
		
		for (TatryModel elem : m) {
			TatryData view = new TatryData();
			
			view.station = stationName;
			view.epoch = elem.epoch;
			
			ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(view.epoch), ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Europe/Warsaw"));
			view.warsaw_local_date_time_excel_fmt = formatter.format(dateTime);	// DD-MM-YY HH:MM
			
			view.id = elem.id;
			view.calculated_measurement_from_telemetry_frame = elem.rawmeasurementrecalc;
			view.raw_measurement_from_telemetry_frame = elem.rawmeasurement;
			view.temperature_from_wx_frame = elem.wxtemperature;
			view.max31865_status = elem.maxstatus;
			view.voltage = elem.voltage;
			
			view.lse_rdy = (elem.lserdy > 0) ? true : false;
			view.max31865_init_ok = (elem.maxok > 0) ? true : false;
			view.rtc_en = (elem.rtcen > 0) ? true : false;
			view.sleep = (elem.sleep > 0) ? true : false;
			view.spi_comm_error = (elem.spier > 0) ? true : false;
			view.spi_comm_ok = (elem.spiok > 0) ? true : false;
			
			view.aprs_frame_type = elem.source;
			view.aprs_frame_content = elem.frame;
			view.receiving_aprs_gate_callsign = elem.originatorcall;
			view.receiving_aprs_gate_ssid = elem.originatorssid;
			
			ZonedDateTime dbInsertDt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(elem.insertepoch), ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Europe/Warsaw"));
			view.db_insert_warsaw_local_date_time_excel_fmt = formatter.format(dbInsertDt);
			
			out.add(view);
		}
		
		logger.debug("[getLongResultsForName][stationName = " + stationName +"][out.size() = " + out.size() + "]");
		
		return out;

	}
	
	@RequestMapping(value = "/tatry/{stationName}/last", produces = "application/json;charset=UTF-8")
	public TatryData getLastResultsForName(@PathVariable(required = true) String stationName) {

		TatryModel model = tatryRepo.findFirstByStationOrderByEpochDesc(stationName);
		
		TatryData output = new TatryData();
		
		output.station = stationName;
		
		output.epoch = model.epoch;
		
		ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(output.epoch), ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Europe/Warsaw"));
		output.warsaw_local_date_time_excel_fmt = formatter.format(dateTime);	// DD-MM-YY HH:MM
		
		output.id = model.id;
		output.calculated_measurement_from_telemetry_frame = model.rawmeasurementrecalc;
		output.raw_measurement_from_telemetry_frame = model.rawmeasurement;
		output.temperature_from_wx_frame = model.wxtemperature;
		output.max31865_status = model.maxstatus;
		output.voltage = model.voltage;
		
		output.lse_rdy = (model.lserdy > 0) ? true : false;
		output.max31865_init_ok = (model.maxok > 0) ? true : false;
		output.rtc_en = (model.rtcen > 0) ? true : false;
		output.sleep = (model.sleep > 0) ? true : false;
		output.spi_comm_error = (model.spier > 0) ? true : false;
		output.spi_comm_ok = (model.spiok > 0) ? true : false;
		
		return output;
	}
	
}
