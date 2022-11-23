package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.web.IconModel;

public interface IconRepo extends Repository<IconModel, Integer> {

	public static final int DEFAULT_GROUP_ICON	=	1999;
	
	List<IconModel> findAll();
}
