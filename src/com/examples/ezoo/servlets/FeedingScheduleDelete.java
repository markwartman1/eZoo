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
import com.examples.ezoo.dao.FeedingScheduleDaoImpl;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

@WebServlet("/feedingScheduleDelete")
public class FeedingScheduleDelete extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String anmialid = request.getParameter("animalID");
		String fsid = request.getParameter("feedingSchedule_ID");
		
		FeedingScheduleDAO fsdao = DAOUtilities.getFeedingScheduleDao();
		
		
		if (fsdao.deleteFeeding_schedule(fsid)) {
			//action="viewSubscriptions?_ID=${fs.schedule_ID}" method="get"
			//request.setCharacterEncoding(fsid);
			//request.getRequestDispatcher("viewSubscriptions?_ID=").forward(request, response);
			request.getSession().setAttribute("message", "Feeding Schedule Removed");
			request.getSession().setAttribute("messageClass", "alert-success");
			
			/**
			 * if deletion was a success, send new feeding schedule data to the table
			 */
			List<FeedingSchedule> fsList = fsdao.getAllFeeding_schedules();
			AnimalDAO adao = DAOUtilities.getAnimalDao();
			List<Animal> animalList = new ArrayList<>();
			
			for(FeedingSchedule fs : fsList) {
				animalList = adao.getAllAnimalsByFeeding_Schedule(fs.getSchedule_ID());
				for(Animal a : animalList) {
					fs.addToAnimalList(a);
				}
			}
			
			request.setAttribute("feedingSchedules", fsList);
			request.getRequestDispatcher("feedingScheduleService.jsp").forward(request, response);
			//response.sendRedirect("animalCareHome.jsp");			
			//response.sendRedirect("feedingScheduleService.jsp");									// works too, same thing...
			//request.getRequestDispatcher("feedingScheduleService.jsp").forward(request, response);// navigates, but old data on table
		}
		else {
			request.getSession().setAttribute("message", "Failure to Delete");
			request.getSession().setAttribute("messageClass", "alert-danger");
		}
	}

}
