package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.SkrzyczneMeteoData;

public interface SkrzyczneSummaryRepo extends Repository<SkrzyczneMeteoData, Integer> {
	
	List<SkrzyczneMeteoData> findFirst50ByOrderByTimestampEpochDesc();
}
