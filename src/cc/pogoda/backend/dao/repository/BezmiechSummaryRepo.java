package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.BezmiechMeteoDataModel;
import cc.pogoda.backend.types.model.SkrzyczneMeteoDataModel;

public interface BezmiechSummaryRepo extends Repository<BezmiechMeteoDataModel, Integer> {
	
	List<BezmiechMeteoDataModel> findFirst50ByOrderByTimestampEpochDesc();
}
