package io.github.obscure1910;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.jdbc.ContainerDatabaseDriver;


public class TestContainerRunner {

	private static Logger LOGGER = LoggerFactory.getLogger(TestContainerRunner.class);
	
	private Connection connection;
	private String url;
	
	public void initialise() throws SQLException, IOException {
		LOGGER.info("Try to instatiate testcontainers");
		Properties props = System.getProperties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("munit.properties"));
		this.url = (String )props.get("db.url");

		LOGGER.info("Initialize testcontainers for jdbc url: " + this.url);
	
		ContainerDatabaseDriver driver = new ContainerDatabaseDriver();
        this.connection = driver.connect(this.url, new Properties());    
	}

	public void destroy() throws SQLException {
		LOGGER.info("Close connection for jdbc url: " + this.url);
		ContainerDatabaseDriver.killContainer(this.url);
		this.connection.close();
		LOGGER.info("Connection closed!");
	}
	
}
