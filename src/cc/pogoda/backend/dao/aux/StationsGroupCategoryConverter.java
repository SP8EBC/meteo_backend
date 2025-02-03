package cc.pogoda.backend.dao.aux;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cc.pogoda.backend.types.StationsGroupCategory;

@Converter(autoApply = true)
public class StationsGroupCategoryConverter implements AttributeConverter<StationsGroupCategory, Integer>{

    private static Logger logger = LogManager.getLogger();
	
	@Override
	public Integer convertToDatabaseColumn(StationsGroupCategory attribute) {
		logger.debug("[StationsGroupCategoryConverter][convertToDatabaseColumn][attribute = " + attribute +"]");
		return attribute.getVal();
	}

	@Override
	public StationsGroupCategory convertToEntityAttribute(Integer dbData) {
		logger.debug("[StationsGroupCategoryConverter][convertToDatabaseColumn][dbData = " + dbData +"]");
		return StationsGroupCategory.valueOf(dbData);
	}

}
