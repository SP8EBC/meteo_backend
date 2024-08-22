package cc.pogoda.backend.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cc.pogoda.backend.types.model.parameteo.PmeteoEventModel;

public interface PMeteoEventCrudRepo extends CrudRepository<PmeteoEventModel, Integer> {

}
