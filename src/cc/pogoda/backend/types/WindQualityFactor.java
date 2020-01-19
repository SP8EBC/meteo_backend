package cc.pogoda.backend.types;

public enum WindQualityFactor {
	FULL,
	DEGRADED,
	NOT_AVALIABLE;
	
	public static WindQualityFactor fromBits(byte value, int version) {
		
		//byte t = (byte) ((byte) (value & 0x6) >> 1);
		short t = (short) (value & 0xFF);
		
		t = (short) ((t & 0x6) >> 1);
		
		
		switch (t) {
			case 1: return WindQualityFactor.NOT_AVALIABLE;
			case 2: return WindQualityFactor.FULL;
		}
		
		return null;
	}
}
