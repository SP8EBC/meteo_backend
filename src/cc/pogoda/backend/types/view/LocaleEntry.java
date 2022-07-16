package cc.pogoda.backend.types.view;

import java.util.LinkedList;
import java.util.List;

import cc.pogoda.backend.types.model.LocaleEntryModel;

public class LocaleEntry {

	public int id;
	
	public int localeId;
	
	public String language;
	
	public String text;
	
	public static LocaleEntry fromModel(cc.pogoda.backend.types.model.LocaleEntryModel model) {
		LocaleEntry out = new LocaleEntry();
		
		out.id = model.id;
		out.language = model.language;
		out.localeId = model.localeId;
		out.text = model.text;
		
		return out;
	}
	
	public static List<LocaleEntry> fromModelList(List<cc.pogoda.backend.types.model.LocaleEntryModel> list) {
		LinkedList<LocaleEntry> out = new LinkedList<LocaleEntry>();
		
		for (LocaleEntryModel entry : list) {
			out.add(LocaleEntry.fromModel(entry));
		}
		
		return out;
	}
}
