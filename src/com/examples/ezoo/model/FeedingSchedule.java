package com.examples.ezoo.model;

import java.util.ArrayList;
import java.util.List;

public class FeedingSchedule {
	
	private int schedule_ID;
	private String feeding_time;
	private String recurrence;
	private String food;
	private String notes;
	private List<Animal> animalList = new ArrayList<>();
	
	// default no args
	public FeedingSchedule() {
	}

	public FeedingSchedule(int schedule_ID, String feeding_time, String recurrence, String food, String notes) {
		this.schedule_ID = schedule_ID;
		this.feeding_time = feeding_time;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
	}

	public int getSchedule_ID() {
		return schedule_ID;
	}

	public void setSchedule_ID(int schedule_ID) {
		this.schedule_ID = schedule_ID;
	}

	public String getFeeding_time() {
		return feeding_time;
	}

	public void setFeeding_time(String feeding_time) {
		this.feeding_time = feeding_time;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public void addToAnimalList(Animal a) {
		this.animalList.add(a);
	}
	
	public void deleteFromAnimalList(Animal a) {
		this.animalList.remove(a);
	}
	
	public List<Animal> getAnimalList() {
		return this.animalList;
	}
	
}
