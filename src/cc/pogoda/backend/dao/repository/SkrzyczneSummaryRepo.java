package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.SkrzyczneMeteoDataModel;

public interface SkrzyczneSummaryRepo extends Repository<SkrzyczneMeteoDataModel, Integer> {
	
	List<SkrzyczneMeteoDataModel> findFirst50ByOrderByTimestampEpochDesc();
}
