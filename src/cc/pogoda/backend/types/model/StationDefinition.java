package cc.pogoda.backend.types.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_station_def")
public class StationDefinition {
	
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
	
	public String toString() {
		return "[StationDefinition][id = " + id + "][name = " + name + "][enabled = " + enabled +"][callsign = " + callsign +"][displayedName = " + displayedName + "][telemetryVersion = " + telemetryVersion + "]";
	}
}
