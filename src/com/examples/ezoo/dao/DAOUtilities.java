package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Class used to retrieve DAO Implementations. Serves as a factory.
 * 
 * @author anon
 *
 */
public class DAOUtilities {

	private static final String CONNECTION_USERNAME = "postgres";
	private static final String CONNECTION_PASSWORD = "passPostgresSQL";
	private static final String URL = "jdbc:postgresql://localhost:5432/eZoo";
	
	private static FeedingScheduleDAO feedingScheduleDaoImpl;
	private static AnimalDaoImpl animalDaoImpl;
	private static Connection connection;

	public static synchronized AnimalDAO getAnimalDao() {

		if (animalDaoImpl == null) {
			animalDaoImpl = new AnimalDaoImpl();
		}
		return animalDaoImpl;
	}
	
	public static synchronized FeedingScheduleDAO getFeedingScheduleDao() {
		
		if (feedingScheduleDaoImpl == null) {
			feedingScheduleDaoImpl = new FeedingScheduleDaoImpl();
		}
		return feedingScheduleDaoImpl;
	}

	static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("getting new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}

}
