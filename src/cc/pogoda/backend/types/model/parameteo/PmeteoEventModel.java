package cc.pogoda.backend.types.model.parameteo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_parameteo_event")
public class PmeteoEventModel {

	
	@Id
	@Basic
	public int id;
	
	@Basic
	public String stationName;
	
	@Basic
	public long eventMasterTime;
	
	@Basic
	public int eventCounterId;

	@Basic
	public String severity;
	
	@Basic
	public String source;
	
	@Basic
	public String event;
		
	@Basic
	public int param;
	
	@Basic
	public int param2;
	
	@Basic
	public int wparam;
	
	@Basic
	public int wparam2;
	
	@Basic
	public int lparam;
	
	@Basic
	public int lparam2;
}
