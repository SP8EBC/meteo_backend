package cc.pogoda.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.SummaryDao;
import cc.pogoda.backend.types.view.Summary;

@RestController
public class SummaryController {

	@Autowired
	SummaryDao dao;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/summary", produces = "application/json;charset=UTF-8")
	public Summary summaryControler(@RequestParam(value="station")String station) {
		Summary s = dao.getSummaryPerStationName(station);
		
		return s;
		
	}
}
