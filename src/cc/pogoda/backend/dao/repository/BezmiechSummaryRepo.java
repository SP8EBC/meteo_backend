package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.BezmiechMeteoData;
import cc.pogoda.backend.types.model.SkrzyczneMeteoData;

public interface BezmiechSummaryRepo extends Repository<BezmiechMeteoData, Integer> {
	
	List<BezmiechMeteoData> findFirst50ByOrderByTimestampEpochDesc();
}
