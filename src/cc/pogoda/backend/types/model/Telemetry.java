package cc.pogoda.backend.types.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_telemetry")
public class Telemetry {

	@Id
	@Basic
	public int id;
	
	@Basic
	public long epoch;
	
	@Basic
	public LocalDateTime datetime;
	
	@Basic
	public String station;
	
	@Basic
	public String callsign;
	
	@Basic
	public short number;
	
	@Basic
	public float first;
	
	@Basic
	public float second;
	
	@Basic
	public float third;
	
	@Basic
	public float fourth;
	
	@Basic
	public float fifth;
	
	@Basic
	public short rawfirst;
	
	@Basic
	public short rawsecond;
	
	@Basic
	public short rawthird;
	
	@Basic
	public short rawfourth;
	
	@Basic
	public short rawfifth;
	
	@Basic
	public short digital;
	
	@Basic
	public String frame;
	
	@Basic
	public byte type;
}
