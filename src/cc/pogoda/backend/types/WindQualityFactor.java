package cc.pogoda.backend.types;

public enum WindQualityFactor {
	FULL,
	DEGRADED,
	NOT_AVALIABLE,
	NO_DATA;
	
	public static WindQualityFactor fromBits(byte value, int version) {
		
		
		short t = (short) (value & 0xFF);
		
		// for differences between version please look at package-info.java for package cc.pogoda.backend.types
		if (version == 2 || version == 3) {
			t = (short) ((t & 0x6) >> 1);
			
			
			switch (t) {
				case 1: return WindQualityFactor.DEGRADED;
				case 2: return WindQualityFactor.FULL;
				default: return WindQualityFactor.NOT_AVALIABLE;
			}
		}
		else if (version == 1) {
			t = (short) ((t & 0x4) >> 2);
			
			
			switch (t) {
				case 1: return WindQualityFactor.DEGRADED;
				default: return WindQualityFactor.FULL;
			}			
		}
		else if (version == 0) {
			return WindQualityFactor.FULL;
		}
		else;
		
		return null;
	}
}
