package cc.pogoda.backend.types;

public enum HumidityQualityFactor {

	FULL,
	DEGRADED,
	NOT_AVALIABLE;
	
	public static HumidityQualityFactor fromBits(byte value, int version) {
		
		//byte t = (byte) ((byte) (value & 0x8) >> 3);
		short t = (short) (value & 0xFF);
		
		t = (short) ((t & 0x8) >> 3);
		
		switch (t) {
			case 1: return HumidityQualityFactor.NOT_AVALIABLE;
		}
		
		return HumidityQualityFactor.FULL;
	}
}
