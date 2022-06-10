package cc.pogoda.backend.dao.repository;

import org.springframework.data.repository.CrudRepository;

import cc.pogoda.backend.types.model.parameteo.PmeteoMeasurementsModel;

public interface PMeteoMeasurementCrudRepo extends CrudRepository<PmeteoMeasurementsModel, Integer>{

}
