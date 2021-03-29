package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDaoImpl implements FeedingScheduleDAO {
	
	Connection connection = null;
	PreparedStatement stmt = null;

	@Override
	public boolean addFeeding_schedule(FeedingSchedule fs) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO feeding_schedules VALUES (?, ?, ?, ?, ?)"; // note 5 '?''s
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' values
			stmt.setInt(1, fs.getSchedule_ID());
			stmt.setString(2, fs.getFeeding_time());
			stmt.setString(3, fs.getRecurrence());
			stmt.setString(4, fs.getFood());
			stmt.setString(5, fs.getNotes());
			
			// If we were able to add our FeedingSchedule to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean deleteFeeding_schedule(String feeding_scheduelID) {
		// Update Animals Set feeding_schedule=null Where feeding_schedule = schedule_ID
		// Delete feeding_schedules Where schedule_ID = ?(scheduel_ID)
		try {
			// QUERY #1
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule=null WHERE feeding_schedule=?;";
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' values
			// cast Animal Model Long to databases Integer requirement
			stmt.setInt(1, Integer.parseInt(feeding_scheduelID));	// update Animal that has this feeding_schedule
			System.out.println("Running UPDATE ...");
			
			if(stmt.executeUpdate() == 0) {
				System.out.println("UDATE QUERY: NO ROWS CHANGED");
			}else {
				System.out.println("UPDATE QUERY: ROWS WERE UPDATED: ANIMAL REMOVED FROM FS !!!");
			}
			
			// QUERY #2
			sql = "DELETE FROM feeding_schedules Where schedule_ID=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, Integer.parseInt(feeding_scheduelID));	// delete feeding_schedule with this ID
			
			
			// If we were able to add our FeedingSchedule to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0) {
				System.out.println("DELETE WAS MADE");
				return true;
			}
			else {
				System.out.println("DELETE: THERE WAS NO SCHEDULE DELETION");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
		
	}

	@Override
	public List<FeedingSchedule> getAllFeeding_schedules() {
		
		List<FeedingSchedule> feedingSchedules = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM feeding_schedules";
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database
			
			while(rs.next()) {
				FeedingSchedule fs = new FeedingSchedule(
						rs.getInt("schedule_ID"),
						rs.getString("feeding_time"),
						rs.getString("recurrence"),
						rs.getString("food"),
						rs.getString("notes"));
				
				feedingSchedules.add(fs);
			}
			
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return list of FeedingSchedule objects from the database
		return feedingSchedules;
	}

	@Override
	public FeedingSchedule getFeeding_scheduleByID(String id) {
		
		FeedingSchedule fs = new FeedingSchedule();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM feeding_schedules WHERE schedule_id=?";
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			System.out.println("inside fsDaoImpl: id param: " + id);
			stmt.setInt(1, Integer.parseInt(id));									// error resolution: used to be string
			
			ResultSet rs = stmt.executeQuery();			// Queries the database
			
			while(rs.next()) {
						fs.setSchedule_ID(rs.getInt("schedule_id"));				// error resolution change here from _ID to _id ???
						fs.setFeeding_time(rs.getString("feeding_time"));
						fs.setRecurrence(rs.getString("recurrence"));
						fs.setFood(rs.getString("food"));
						fs.setNotes(rs.getString("notes"));
			}
			
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return FeedingSchedule object from the database
		return fs;
	}
	
	/**
	 * Polymorphic option of above method
	 * This would query an Animals feeding_schedule if existed??
	 * @param animal
	 * @return
	 */
	public FeedingSchedule getFeeding_scheduleByID(Animal animal) {
		
		FeedingSchedule fs = new FeedingSchedule();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM feeding_schedules WHERE schedule_ID = (SELECT feeding_schedule FROM animals WHERE feeding_schedule=?)";
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			stmt.setString(1, animal.getFeedingSchedule());
			
			ResultSet rs = stmt.executeQuery();			// Queries the database
			
			while(rs.next()) {
						fs.setSchedule_ID(rs.getInt("schedule_ID"));
						fs.setFeeding_time(rs.getString("feeding_time"));
						fs.setRecurrence(rs.getString("recurrence"));
						fs.setFood(rs.getString("food"));
						fs.setNotes(rs.getString("notes"));
			}
			
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return FeedingSchedule object from the database
		return fs;
	}

	@Override
	public boolean subscribeToFeeding_schedule(Animal animal, FeedingSchedule fs) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule=? WHERE animalid=?";
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' values
			// cast Animal Model Long to databases Integer requirement
			stmt.setInt(2, (int) animal.getAnimalID());	// search for this animal
			stmt.setInt(1, fs.getSchedule_ID());		// SET feedingSchedule_ID to Animal feeding_schedule
			
			
			// If we were able to add our FeedingSchedule to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	@Override
	public boolean subscribeToFeeding_schedule(String animal_id, String fs_id) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule=? WHERE animalid=?";
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' values
			// cast String to database Integer
			stmt.setInt(2, Integer.parseInt(animal_id));	// search for this animal
			stmt.setInt(1, Integer.parseInt(fs_id));		// SET feedingSchedule_ID to Animal feeding_schedule
			
			
			// If we were able to add our FeedingSchedule to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean unsubscribeToFeeding_schedule(Animal animal) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule=null WHERE animalid=?";
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' values
			// cast database Integer to Animal Model Long
			stmt.setInt(1, (int) animal.getAnimalID());	// search for this animal			
			
			// If we were able to add our FeedingSchedule to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	@Override
	public boolean unsubscribeToFeeding_schedule(String animalID) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule=null WHERE animalid=?";
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' values
			// cast database Integer to Animal Model Long
			stmt.setInt(1, Integer.parseInt(animalID));	// search for this animal			
			
			// If we were able to add our FeedingSchedule to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	@Override
	public boolean update(FeedingSchedule fs) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE feeding_schedules "
					+ "SET feeding_time=? , recurrence=? , food=? , notes=?"
					+ "WHERE schedule_id = ?"; // note 5 '?''s
			stmt = connection.prepareStatement(sql);
			
			// Setting '?' values
			stmt.setInt(5, fs.getSchedule_ID());
			stmt.setString(1, fs.getFeeding_time());
			stmt.setString(2, fs.getRecurrence());
			stmt.setString(3, fs.getFood());
			stmt.setString(4, fs.getNotes());
			
			// If we were able to add our FeedingSchedule to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
				System.out.println("Closing down connection...");
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
