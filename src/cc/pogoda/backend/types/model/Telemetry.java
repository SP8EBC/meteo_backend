package cc.pogoda.backend.types.model;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "telemetry")
public class Telemetry {

	@Id
	@Basic
	public int id;
	
	@Basic
	public String callsign;
	
	@Basic
	public String alias;
	
	@Column(name="TimestampEpoch")
	public LocalDateTime timestampEpoch;
	
	@Basic
	public short first;
	
	@Basic
	public short second;
	
	@Basic
	public short third;
	
	@Basic
	public short fourth;
	
	@Basic
	public short fifth;
	
	@Basic
	public short digital;
	
	@Basic
	public String frame;
	
	@Basic
	public byte type;
}
