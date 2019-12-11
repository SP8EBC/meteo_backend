package cc.pogoda.backend.dao.aux;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class MyNamingStrategy implements PhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalCatalogName(Identifier arg0, JdbcEnvironment arg1) {
		return arg0;
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier arg0, JdbcEnvironment arg1) {
		return arg0;
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier arg0, JdbcEnvironment arg1) {
		return arg0;
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier arg0, JdbcEnvironment arg1) {
		return arg0;
	}

	@Override
	public Identifier toPhysicalTableName(Identifier arg0, JdbcEnvironment arg1) {
		return arg0;
	}

}
