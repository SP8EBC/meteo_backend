package cc.pogoda.backend.types.model;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "data_telemetry")
public class TelemetryModel {

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
