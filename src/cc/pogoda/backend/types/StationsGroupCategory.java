package cc.pogoda.backend.types;

/**
 * Defines station groups categories with a distinction between builtin groups and custom (aka external). Presence of builtin groups is 
 * hardcoded in 'Meteo System' application with an appropriate locale. The icon is always displayed to the user regardless if this group
 * is defined by the API and it consist any stations
 * @author mateusz
 *
 */
public enum StationsGroupCategory {

	PARAGLIDING(1),
	SAILING(2),
	SKIING(3),
	MOUNTAINS(4),
	CITIES(5),
	CUSTOM(99);
	
	private final int val;
	
	private StationsGroupCategory(int v) {
		val = v;
	}

	public int getVal() {
		return val;
	}
	
	public static StationsGroupCategory valueOf(int v) {
		StationsGroupCategory out = StationsGroupCategory.CUSTOM;
		
		switch (v) {
		case 1: out = PARAGLIDING; break;
		case 2: out = SAILING; break;
		case 3: out = SKIING; break;
		case 4: out = MOUNTAINS; break;
		case 5: out = CITIES; break;
		}
		
		return out;
	}
}
