package cc.pogoda.backend.types;

import java.time.LocalDateTime;

public class GenericMeteoData {

	public long id;
	
	public LocalDateTime timestampEpoch;
	
	public float Temp;
	
	public float WindSpeed;
	
	public float WindGusts;
	
	public short WindDir;
	
	public int QNH;

	public short humidity;
}
