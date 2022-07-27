package cc.pogoda.backend.types.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cc.pogoda.backend.types.model.LocaleEntryModel;

public class LocaleEntry {

	public int id;
	
	public int stationGroupId;
	
	public String language;
	
	public String text;
	
	public static LocaleEntry fromModel(cc.pogoda.backend.types.model.LocaleEntryModel model) {
		LocaleEntry out = new LocaleEntry();
		
		out.id = model.id;
		out.language = model.language;
		out.stationGroupId = model.stationGroupId;
		out.text = model.text;
		
		return out;
	}
	
	public static List<LocaleEntry> fromModelList(Set<cc.pogoda.backend.types.model.LocaleEntryModel> list) {
		LinkedList<LocaleEntry> out = new LinkedList<LocaleEntry>();
		
		for (LocaleEntryModel entry : list) {
			out.add(LocaleEntry.fromModel(entry));
		}
		
		return out;
	}
}
