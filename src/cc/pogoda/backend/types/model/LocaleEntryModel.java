package cc.pogoda.backend.types.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "data_locale")
public class LocaleEntryModel {

	@Id
	@Basic
	public int id;
	
	@Basic
	public int stationGroupId;
	
	@Basic
	public String language;
	
	@Basic
	public String text;
	
	/**
	 * This is a reference (join) to stations group definition stored in MySQL in table 'data_station_groups'. 
	 * Annotation parameter 'name = "stationGroupId"' defines a field ***within*** 'data_locale' which value
	 * is used by parent entity (StationGroupModel) to links this locale to certain station group
	 * 
	 */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "stationGroupId", nullable = false, insertable = false, updatable = false)
	StationsGroupModel stationsGroupModel;
}
