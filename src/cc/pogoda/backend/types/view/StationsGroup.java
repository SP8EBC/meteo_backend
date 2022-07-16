package cc.pogoda.backend.types.view;

import cc.pogoda.backend.types.StationsGroupCategory;
import cc.pogoda.backend.types.model.LocaleEntryModel;

public class StationsGroup {

	public int id;
	
	public StationsGroupCategory category;
	
	public java.util.List<LocaleEntry> locale;
	
	public boolean isEmpty;
	
	public boolean isBuiltin;
}
