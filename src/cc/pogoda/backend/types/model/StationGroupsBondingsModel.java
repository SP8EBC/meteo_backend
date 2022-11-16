package cc.pogoda.backend.types.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "data_station_groups_bondings")
public class StationGroupsBondingsModel {

	@Id
	@Basic
	public int id;
	
	@Basic
	public int groupId;
	
	@Basic
	public int stationId;
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "stationId", nullable = false, insertable = false, updatable = false)
    StationDefinitionModel stationDefinitionModel;
}
