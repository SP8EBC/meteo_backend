package cc.pogoda.backend.dao.repository;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.SummaryModel;

public interface SummaryRepo extends Repository<SummaryModel, Integer> {

	SummaryModel findFirstByStation(String station);
}
