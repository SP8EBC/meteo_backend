package cc.pogoda.backend.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationsGroupDao;
import cc.pogoda.backend.types.model.StationsGroup;
import cc.pogoda.backend.types.view.StationsGroupView;

@RestController
public class StationGroupsController {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private StationsGroupDao dao;
    
	@RequestMapping(value = "/groups/all", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public List<StationsGroupView> getAllGroups() {
    	return dao.getAll();
    }
}
