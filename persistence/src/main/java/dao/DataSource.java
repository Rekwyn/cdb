package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

@Component
public class DataSource {

	@Autowired
	private HikariDataSource ds;

	private static Logger logger = LoggerFactory.getLogger(DataSource.class);

	private DataSource() {
	}

	/**
	 * Get a connection.
	 * 
	 * @return connection object based on datasource properties
	 */
	public Connection getConnection() {

		try {
			return ds.getConnection();
		} catch (SQLException e) {
			logger.error("Error while connecting to the DB.", e.getMessage());
		}

		return null;
	}
}
