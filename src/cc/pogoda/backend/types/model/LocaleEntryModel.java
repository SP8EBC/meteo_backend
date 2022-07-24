package cc.pogoda.backend.types.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "data_locale")
public class LocaleEntryModel {

	@Id
	@Basic
	public int id;
	
	@Basic
	public int localeId;
	
	@Basic
	public String language;
	
	@Basic
	public String text;
	
	/**
	 * This is a reference (join) to stations group definition stored in MySQL in table 'data_station_groups'. 
	 * Annotation parameter 'name = "localeId"' defines a field ***within*** 'data_locale' which value
	 * is used by parent entity to links 
	 * 
	 */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "localeList")
    //@JoinColumn(name = "localeId", nullable = false, insertable = false, updatable = false)
	List<StationsGroupModel> stationsGroupModel;
}
