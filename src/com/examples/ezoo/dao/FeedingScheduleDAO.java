package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.*;

public interface FeedingScheduleDAO {
	
	// add
	public boolean addFeeding_schedule(FeedingSchedule fs);
	
	/**
	 * First update table animals set all animal feeding schedules with 
	 * that schedule to null, before deleting the feeding_schedule record
	 * @param fs
	 * @return
	 */
	public boolean deleteFeeding_schedule(String feeding_scheduleID);
	
	/**
	 * Select * from feeding_schedules;
	 * @return the database of feeding_schedules
	 */
	public List<FeedingSchedule> getAllFeeding_schedules();
	
	/**
	 * retrieve a single feeding schedule on an 
	 * Animals' Foreign Key: feeding_schedule, or 
	 * Feeding_Schedules' Primary Key: schedule_ID 
	 * @param The id of Animals FK or Feeding_Schedules PK
	 * @return
	 */
	public FeedingSchedule getFeeding_scheduleByID(String id);
	
	/**
	 * method to retrieve a single feeding schedule from the database for a given animal
	 * @param animal
	 * @param fs
	 * @return
	 */
	public boolean subscribeToFeeding_schedule(Animal animal, FeedingSchedule fs);
	public boolean subscribeToFeeding_schedule(String animal_id, String fs_id);
	
	/**
	 * Update statement for the animal table, set feeding_schedule to null
	 * @param animal
	 * @return a boolean that represents database success of failure to update
	 * 
	 * @param could have been long animalID, String feeding_schedule
	 */
	public boolean unsubscribeToFeeding_schedule(Animal animal);
	public boolean unsubscribeToFeeding_schedule(String animalID);
	
	public boolean update(FeedingSchedule fs);
}
