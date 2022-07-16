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

import cc.pogoda.backend.types.StationsGroupCategory;

@Entity
@Table(name = "data_station_groups")
public class StationsGroupModel {

	@Id
	@Basic
	public int id;
	
	public StationsGroupCategory category;
	
	/**
	 * 
	 * The value of 'mappedBy' points to a field in 'target entity' which is bonded by the relation. It is required to let JPA now
	 * which parent entity uses 
	 * 
	 * https://www.baeldung.com/jpa-joincolumn-vs-mappedby
	 * Here, the value of mappedBy is the name of the association-mapping attribute on the owning side. With this, we have now established a bidirectional association between our Employee and Email entities.
	 * 
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "stationsGroupModel")
	public java.util.List<LocaleEntryModel> localeList;
}
