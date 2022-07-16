package cc.pogoda.backend.types.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cc.pogoda.backend.types.GenericMeteoData;
import cc.pogoda.backend.types.MeteoData;

@Entity
@Table(name = "data_station")
public class StationDataModel extends MeteoData {

	@Id
	@Basic
	public int id;
	
	@Basic
	public long epoch;
	
//	@Basic
//	public LocalDateTime datetime;
	
	@Basic
	public String station;
	
	@Basic
	public float temperature;
	
	@Basic
	public short humidity;
	
	@Basic
	public float pressure;
	
	@Basic
	public short winddir;
	
	@Basic
	public float windspeed;
	
	@Basic
	public float windgusts;
	
	@Basic
	public String tsource;
	
	@Basic
	public String wsource;
	
	@Basic
	public String psource;
	
	@Basic
	public String hsource;
	
	@Basic
	public String rsource;

	@Override
	public GenericMeteoData convertToGeneric() {
		GenericMeteoData out = new GenericMeteoData();
		
		out.id = this.id;
//		out.timestampEpoch = this.datetime;
		out.timestampEpoch = LocalDateTime.ofEpochSecond(this.epoch, 0, ZoneOffset.ofTotalSeconds(OffsetDateTime.now().getOffset().getTotalSeconds()));
		out.Temp = this.temperature;
		out.WindSpeed = this.windspeed;
		out.WindGusts = this.windgusts;
		out.WindDir = this.winddir;
		out.QNH = (int)this.pressure;
		out.humidity = this.humidity;
		
		return out;
	}
	
	public static StationDataModel averageFromList(List<StationDataModel> data, boolean doesntRound) {
		StationDataModel out = new StationDataModel();

		// rouding to four significant figures for pressure > 1000hPa
		MathContext mathContextFive = new MathContext(5, RoundingMode.HALF_EVEN);
		
		// rouding to four significant figures for pressure
		MathContext mathContextFour = new MathContext(4, RoundingMode.HALF_EVEN);
		
		// rouding to two significant figures for values bigger than 10.0
		MathContext mathContextThree = new MathContext(3, RoundingMode.HALF_EVEN);
		
		// rouding to two significant figures for values bigger than 1.0
		MathContext mathContextTwo = new MathContext(2, RoundingMode.HALF_EVEN);
		
		// rouding to one significant figures for values from 0.0 to 1.0
		MathContext mathContextOne = new MathContext(1, RoundingMode.HALF_EVEN);
		
		// used to round double to cerain decimal digits
		BigDecimal rounded;
		
		if (data != null && data.size() > 1) {
			
			int dataPoints = 0;
			
			// used for calculating angle mean value
	        double x = 0.0;
	        double y = 0.0;
	        double angleRadians = 0.0;
			
			// the returned value of wind gusts is the maximum found in the input set
			float gusts = 0.0f;
			
			for (StationDataModel d : data) {
				out.temperature += d.temperature;
				out.pressure += d.pressure;
				out.humidity += d.humidity;
				out.windspeed += d.windspeed;
				
	            angleRadians = Math.toRadians(d.winddir);
	            x += Math.cos(angleRadians);
	            y += Math.sin(angleRadians);
				
				dataPoints++;
				
				// look fo the maximum value of wind gusts 
				if (d.windgusts > gusts) 
					gusts = d.windgusts;
			}
			
			out.temperature /= dataPoints;
			out.pressure /= dataPoints;
			out.humidity /= dataPoints;
			out.windspeed /= dataPoints;
			
			out.windgusts = gusts;
			
			if (!doesntRound) {
				// rouding temperature
				if (out.temperature > 10.0 || out.temperature < -10.0) {
					rounded = new BigDecimal(out.temperature, mathContextThree);
				}
				else if (out.temperature > 1.0 || out.temperature < -1.0) {
					rounded = new BigDecimal(out.temperature, mathContextTwo);
				}
				else {
					rounded = new BigDecimal(out.temperature, mathContextOne);
				}
				out.temperature = rounded.floatValue();
				
				// rouding average wind speed
				if (out.windspeed > 10.0) {
					rounded = new BigDecimal(out.windspeed, mathContextThree);
				}
				else if (out.windspeed > 1.0) {
					rounded = new BigDecimal(out.windspeed, mathContextTwo);
				}
				else {
					rounded = new BigDecimal(out.windspeed, mathContextOne);
				}
				out.windspeed = rounded.floatValue();
				
				// rounding pressure
				if (out.pressure >= 1000.0f) {
					rounded = new BigDecimal(out.pressure, mathContextFive);
				}
				else {
					rounded = new BigDecimal(out.pressure, mathContextFour);
				}
				out.pressure = rounded.floatValue();
			}
			
			out.winddir = (short)Math.toDegrees(Math.atan2(y / dataPoints, x / dataPoints));
			
			if (out.winddir < 0) 
				out.winddir += 360;
		}
		
		return out;
	}
}
