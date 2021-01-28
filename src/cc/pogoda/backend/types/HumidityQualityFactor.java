package cc.pogoda.backend.types;

public enum HumidityQualityFactor {

	FULL,
	DEGRADED,
	NOT_AVALIABLE,
	NO_DATA;
	
	public static HumidityQualityFactor fromBits(byte value, int version) {
		
		if (version == 0) {
			return HumidityQualityFactor.FULL;
		}
		else {
			
			short t = (short) (value & 0xFF);
			
			t = (short) ((t & 0x8) >> 3);
			
			switch (t) {
				case 1: return HumidityQualityFactor.NOT_AVALIABLE;
			}
			
			return HumidityQualityFactor.FULL;
		}
	}
}
