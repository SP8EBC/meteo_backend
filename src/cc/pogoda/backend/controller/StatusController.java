package cc.pogoda.backend.controller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.config.Consts;
import cc.pogoda.backend.types.view.Status;

@RestController
public class StatusController {
	
	
    private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(value = "/status", produces = "application/json;charset=UTF-8")
	public Status status() {
		Status out = new Status();
		
		logger.debug("test");
		
		ZonedDateTime current = ZonedDateTime.now();
		
		out.backend_api_version_string = Consts.VERSION;
		out.backend_api_build_version_number = Consts.BUILD_VERSION_NUM;
		out.backend_api_main_version_number = Consts.MAIN_VERSION_NUM;
		out.backend_api_patch_version_number = Consts.PATCH_VERSION_NUM;
		
		out.status = 0;
		out.api_timestamp_epoch = current.toEpochSecond();
		out.api_timezone = current.getZone().toString();
		out.api_tz_offset_string = current.getOffset().toString();
		out.api_tz_offset_seconds = current.getOffset().getTotalSeconds();
		
		return out;
	}
}
