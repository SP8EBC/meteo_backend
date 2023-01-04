package cc.pogoda.backend.config;

public class Consts {

	public static final int MAIN_VERSION_NUM = 0;
	
	public static final int PATCH_VERSION_NUM = 0;
	
	public static final int BUILD_VERSION_NUM = 31;
	
	public static final String VERSION = "meteo_backend_" + MAIN_VERSION_NUM + "-" + PATCH_VERSION_NUM + "-" + BUILD_VERSION_NUM;
	
	/**
	 * Default icons if station is not associated to any group
	 */
	public static final String DEFAULT_AVAILABLE_ICON = "http://pogoda.cc/icons/default_full.gif";
	public static final String DEFAULT_DEGR_ICON = "http://pogoda.cc/icons/default_degraded.gif";
	public static final String DEFAULT_NAVBLE_ICON = "http://pogoda.cc/icons/default_notavailable.gif";
}
