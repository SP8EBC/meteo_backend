package cc.pogoda.backend.types.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import cc.pogoda.backend.types.StationsGroupCategory;

@Entity
@Table(name = "data_station_groups")
public class StationsGroupModel {

	@Id
	@Basic
	public int id;
	
	public StationsGroupCategory category;
	
	public boolean enabled;
	
	/**
	 * 
	 * The value of 'mappedBy' points to a field in 'target entity' which is bonded by the relation. It is required to let JPA now
	 * which parent entity uses 'target entity'
	 * 
	 * https://www.baeldung.com/jpa-joincolumn-vs-mappedby
	 * Here, the value of mappedBy is the name of the association-mapping attribute on the owning side. With this, we have now established a bidirectional association between our Employee and Email entities.
	 * 
	 * 
	 * OneToMany relation means that single station group can have more than one locale (LocaleEntryModel)
	 * bounded, but single locale (LocaleEntryModel) can be connected with only one station group
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stationsGroupModel")
	public java.util.Set<LocaleEntryModel> localeList;
	
//	@OneToMany(mappedBy = "stationsGroupModel")
//	public java.util.Set<StationGroupsBondingsModel> bondingList;
}
