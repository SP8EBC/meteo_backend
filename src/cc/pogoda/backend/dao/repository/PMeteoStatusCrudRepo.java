package cc.pogoda.backend.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cc.pogoda.backend.types.model.parameteo.PmeteoStatusModel;

public interface PMeteoStatusCrudRepo extends CrudRepository<PmeteoStatusModel, Integer> {

	PmeteoStatusModel findByStation(String station);
}
