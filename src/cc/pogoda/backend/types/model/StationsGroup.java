package cc.pogoda.backend.types.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cc.pogoda.backend.types.StationsGroupCategory;

@Entity
@Table(name = "data_station_groups")
public class StationsGroup {

	@Id
	@Basic
	public int id;
	
	public StationsGroupCategory category;
}
