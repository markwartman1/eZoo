package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/feedingScheduleService")
public class FeedingScheduleService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isSuccess = false;
		//Get Parameters
		//Create an Feeding Schedule object from the parameters
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> fsList = dao.getAllFeeding_schedules();
		
		AnimalDAO adao = DAOUtilities.getAnimalDao();
		List<Animal> animalList = new ArrayList<>();
		
		for(FeedingSchedule fs : fsList) {
			animalList = adao.getAllAnimalsByFeeding_Schedule(fs.getSchedule_ID());
			for(Animal a : animalList) {
				fs.addToAnimalList(a);
			}
		}
		
				
		

		//try {
			//fsList = dao.getAllFeeding_schedules();
			//animalList = adao.getAllAnimals();
			System.out.println("Feeding Schedule obj's: " + fsList.size());
			//System.out.println("Animal obj's: " + animalList.size());
			
			if (fsList.size() > 0) {
				isSuccess = true;
			}

			/**
			 * if setting two objects in the session attribute, nest animal in feedingSchedule
			 */
			if (isSuccess) {
				//request.getSession().setAttribute("message", "Created Feeding Schedule");
				//request.getSession().setAttribute("messageClass", "alert-success");
				
				//request.setAttribute("animals", animalList);								// could be useful, jsp code already implemented
				request.setAttribute("feedingSchedules", fsList);
				request.getRequestDispatcher("feedingScheduleService.jsp").forward(request, response);
			}else {
				//change the message
				request.getSession().setAttribute("message", "No Feeding Schedule's to Show");
				request.getSession().setAttribute("messageClass", "alert-danger");
				
				request.getRequestDispatcher("feedingScheduleService.jsp").forward(request, response);
			}
			
//		}catch (Exception e){
//			e.printStackTrace();
//		}
		

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}
