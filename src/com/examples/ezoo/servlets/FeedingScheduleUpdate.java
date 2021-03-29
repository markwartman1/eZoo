package com.examples.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

@WebServlet("/feedingScheduleUpdate")
public class FeedingScheduleUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("feedingScheduleAdd.jsp").forward(request, response);
//	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isSuccess = false;
		//Get Parameters
		int id = Integer.parseInt(request.getParameter("id"));
		String feeding_time = request.getParameter("feeding_time");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		
		//Create an Feeding Schedule object from the parameters
		FeedingSchedule fs = new FeedingSchedule(
				id, 
				feeding_time, 
				recurrence,
				food,
				notes);
		
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			
			if (dao.update(fs)) {

				isSuccess = true;
				//request.getRequestDispatcher("feedingScheduleAdd.jsp").forward(request, response);
			}

			if (isSuccess) {
				request.getSession().setAttribute("message", "Feeding Schedule Updated");
				request.getSession().setAttribute("messageClass", "alert-success");
				response.sendRedirect("feedingScheduleService");
				
			}else {
				//change the message
				request.getSession().setAttribute("message", "Failed to update Feeding Schedule");
				request.getSession().setAttribute("messageClass", "alert-danger");
				
			}

			

			
			
			
		}catch (Exception e){
			e.printStackTrace();
			


		}
	}

}
