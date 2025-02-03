package cc.pogoda.backend.types.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cc.pogoda.backend.types.model.web.IconModel;

@Entity
@Table(name = "data_station_def")
public class StationDefinitionModel {
	
	@Id
	@Basic
	public int id;
	
	@Basic
	public String name;
	
	@Basic
	public boolean enabled;
	
	@Basic
	public String callsign;
	
	@Basic
	public byte ssid;
	
	@Basic
	public String displayedName;
	
	@Basic
	public String displayedLocation;
	
	@Basic
	public String sponsorUrl;

	@Basic
	public String backgroundJpg;
	
	@Basic
	public int backgroundJpgAlign;
	
	@Basic
	public int stationNameTextColour;

	@Basic
	public String moreInfo;
	
	@Basic
	public float lat;
	
	@Basic
	public float lon;
	
	@Basic
	public String timezone;
	
	@Basic
	public boolean hasWind;
	
	@Basic
	public boolean hasQnh;
	
	@Basic
	public boolean hasHumidity;
	
	@Basic
	public boolean hasRain;
	
	@Basic
	public byte telemetryVersion;
	
	/**
	 * This is a relation to all station groups this station belongs to. 'mappedBy = "stationDefinitionModel"'
	 * defines a field name inside {@link StationGroupsBondingsModel} class which points to the station 
	 * (an instance of this class) 
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stationDefinitionModel")
	@JsonIgnore
	public List<StationGroupsBondingsModel> groupsStationBelongsTo;
	
	public String toString() {
		return "[StationDefinition][id = " + id + "][name = " + name + "][enabled = " + enabled +"][callsign = " + callsign +"][displayedName = " + displayedName + "][telemetryVersion = " + telemetryVersion + "]";
	}
}
