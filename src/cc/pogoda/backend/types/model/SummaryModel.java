package cc.pogoda.backend.types.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_summary")
public class SummaryModel {

	@Id
	@Basic
	public int id;
	
	@Basic
	public long lasttimestamp;
	
	@Basic
	public int numberofmeasurements;
	
	@Basic
	public float temperature;
	
	@Basic
	public int qnh;
	
	@Basic 
	public int humidity;
	
	@Basic 
	public int direction;
	
	@Basic
	public float average;
	
	@Basic
	public float gusts;
	
	@Basic
	public float hourgusts;
	
	@Basic
	public float hourmaxavg;
	
	@Basic
	public float hourminavg;
	
	@Basic
	public String station;
}
