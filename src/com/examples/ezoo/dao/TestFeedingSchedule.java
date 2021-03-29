package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.FeedingSchedule;

public class TestFeedingSchedule {

	public static void main(String[] args) {
		
		FeedingScheduleDAO dao = new FeedingScheduleDaoImpl();
		List<FeedingSchedule> list = dao.getAllFeeding_schedules();
		int count = 1;
		for(FeedingSchedule fs : list) {
			System.out.println(fs.getFood());
			System.out.println("Count: " + count);
		}

	}

}
