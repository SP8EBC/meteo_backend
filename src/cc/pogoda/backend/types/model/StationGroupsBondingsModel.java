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
	
//	@Basic
//	public int stationId;
	
	/**
	 * This is a relation to a station bonded by this bonding.
	 * {@link StationGroupsBondingsModel.stationId} is a column / field
	 * inside {@link StationGroupsBondingsModel} and 'data_station_groups_bondings' 
	 * which defines the station identifier.
	 */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "stationId")
    StationDefinitionModel stationDefinitionModel;

	public StationDefinitionModel getStationDefinitionModel() {
		return stationDefinitionModel;
	}
}
