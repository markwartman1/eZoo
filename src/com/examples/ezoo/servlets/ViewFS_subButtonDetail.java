package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

@WebServlet("/viewSubscriptions")
public class ViewFS_subButtonDetail extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// this _ID reflects upon the database relationship between
		// FeedingSchedule PK & Animal FK
		int _ID = Integer.parseInt(request.getParameter("schedule_ID"));
		String id = request.getParameter("schedule_ID");
		System.out.println("buttonDetail: _ID param: " + _ID);
		
		FeedingScheduleDAO fsdao = DAOUtilities.getFeedingScheduleDao();
		FeedingSchedule feedingSchedule = fsdao.getFeeding_scheduleByID(id);	// Solid reliability this id exists
		
		AnimalDAO adao = DAOUtilities.getAnimalDao();
		List<Animal> animalList = adao.getAllAnimalsByFeeding_Schedule(_ID);
		
		List<Animal> allAnimals = adao.getAllAnimals();
		
		for(Animal a : animalList) {
			System.out.println("AnimalList name: " + a.getName());
		}
		for(Animal a : allAnimals) {
			System.out.println("allAnimals name: " + a.getName());
		}
		
		request.setAttribute("feedingSchedule", feedingSchedule);
		request.setAttribute("animalList", animalList);
		request.setAttribute("allAnimals", allAnimals);
		
		request.getRequestDispatcher("feedingsScheduleSubscriptions.jsp").forward(request, response);
	}

}
