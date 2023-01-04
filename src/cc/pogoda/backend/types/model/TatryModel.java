package cc.pogoda.backend.types.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_tatry")
public class TatryModel {

	@Id
	@Basic
	public int id;
	
	@Basic
	public long epoch;
	
	@Basic
	public String station;
	
	@Basic
	public float wxtemperature;
	
	@Basic
	public int rawmeasurement;
	
	@Basic
	public float rawmeasurementrecalc;
	
	@Basic
	public float voltage;
	
	@Basic
	public int maxstatus;
	
	@Basic
	public byte lserdy;
	
	@Basic
	public byte rtcen;
	
	@Basic
	public byte maxok;
	
	@Basic
	public byte sleep;
	
	@Basic
	public byte spier;
	
	@Basic
	public byte spiok;
}
