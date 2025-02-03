package cc.pogoda.backend.types.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	
	@Basic
	public String source;
	
	@Basic
	public String frame;
	
	@Basic
	public String originatorcall;
	
	@Basic 
	public int originatorssid;
	
	@Basic
	public long insertepoch;
}
