package cc.pogoda.backend.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cc.pogoda.backend.types.model.parameteo.PmeteoStatus;

public interface PMeteoStatusCrudRepo extends CrudRepository<PmeteoStatus, Integer> {

	PmeteoStatus findByStation(String station);
}
