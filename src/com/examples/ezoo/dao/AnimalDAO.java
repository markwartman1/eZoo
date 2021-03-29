package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;

/**
 * Main interface used to execute CRUD methods on Animal class
 * @author anon & mark
 *
 */
public interface AnimalDAO {

	/**
	 * Used to retrieve a list of all Animals 
	 * @return
	 */
	List<Animal> getAllAnimals();
	
	/**
	 * @param feeding_schedule is an Animals property feeding_schedule
	 * @return
	 */
	List<Animal> getAllAnimalsByFeeding_Schedule(int feeding_schedule);

	/**
	 * Used to persist the animal to the datastore
	 * @param animalToSave
	 */
	void saveAnimal(Animal animalToSave) throws Exception;

	
}
