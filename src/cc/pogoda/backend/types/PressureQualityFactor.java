package cc.pogoda.backend.types;

public enum PressureQualityFactor {
	
	FULL,
	DEGRADED,
	NOT_AVALIABLE,
	NO_DATA;
	
	public static PressureQualityFactor fromBits(byte value, int version) {
		
		if (version == 0) {
			return PressureQualityFactor.FULL;
		}
		else {
		
			//byte t = (byte) ((byte) (value & 0x10) >> 4);
			short t = (short) (value & 0xFF);
			
			t = (short) ((t & 0x10) >> 4);
			
			switch (t) {
				case 1: return PressureQualityFactor.NOT_AVALIABLE;
			}
			
			return PressureQualityFactor.FULL;
		}
		
	}
}
