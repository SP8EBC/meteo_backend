package cc.pogoda.backend.types.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cc.pogoda.backend.types.GenericMeteoData;
import cc.pogoda.backend.types.MeteoData;

@Entity
@Table(name = "skrzyczne")
public class SkrzyczneMeteoDataModel extends MeteoData {
	
	@Id
	@Basic
	public long id;
	
	@Basic
	@Column(name="TimestampEpoch")
	public LocalDateTime timestampEpoch;
	
	@Basic
	public float Temp;
	
	@Basic
	public float WindSpeed;
	
	@Basic
	public float WindGusts;
	
	@Basic
	public short WindDir;
	
	@Basic
	public int QNH;
	
	@Basic
	public short humidity;

	@Override
	public GenericMeteoData convertToGeneric() {
		GenericMeteoData out = new GenericMeteoData();
		
		out.id = this.id;
		out.timestampEpoch = this.timestampEpoch;
		out.Temp = this.Temp;
		out.WindSpeed = this.WindSpeed;
		out.WindGusts = this.WindGusts;
		out.WindDir = this.WindDir;
		out.QNH = this.QNH;
		out.humidity = this.humidity;
		
		return out;
	}
}
