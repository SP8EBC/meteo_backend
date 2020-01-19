package cc.pogoda.backend.types;

public enum TemperatureQualityFactor {

	FULL,
	DEGRADED,
	NOT_AVALIABLE;
	
	public static TemperatureQualityFactor fromBits(byte value, int version) {	
		
		short t = (short) (value & 0xFF);
		
		t = (short) ((t & 0xE0) >> 5);
		
		switch (t) {
			case 1: return TemperatureQualityFactor.NOT_AVALIABLE;
			case 2: return TemperatureQualityFactor.DEGRADED;
			case 4: return TemperatureQualityFactor.FULL;
		}
		
		return null;
	}
	
}
