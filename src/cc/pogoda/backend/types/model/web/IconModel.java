package cc.pogoda.backend.types.model.web;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cc.pogoda.backend.types.model.StationDefinitionModel;
import cc.pogoda.backend.types.model.StationsGroupModel;

@Entity
@Table(name = "data_icons")
public class IconModel {

	@Basic
	@Id
	public int id;
	
//	@Basic
//	public int stationgroupid;
	
	@Basic
	public String full;
	
	@Basic
	public String degraded;
	
	@Basic
	public String notavailable;
	
	@OneToOne
	@JoinColumn(name = "stationgroupid")
	public StationsGroupModel stationGroupModel;
	
	public IconModel() {
		id = 0;
		full = "";
		degraded = "";
		notavailable = "";
	}
	
}
