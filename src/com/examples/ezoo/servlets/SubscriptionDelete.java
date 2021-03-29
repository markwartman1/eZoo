package com.examples.ezoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.dao.FeedingScheduleDaoImpl;
import com.examples.ezoo.model.Animal;

@WebServlet("/subscriptionDelete")
public class SubscriptionDelete extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String anmialid = request.getParameter("animalID");
		String fsid = request.getParameter("feedingSchedule_ID");
		
		FeedingScheduleDAO fsdao = DAOUtilities.getFeedingScheduleDao();
		
		if (fsdao.unsubscribeToFeeding_schedule(anmialid)) {
			//action="viewSubscriptions?_ID=${fs.schedule_ID}" method="get"
			//request.setCharacterEncoding(fsid);
			//request.getRequestDispatcher("viewSubscriptions?_ID=").forward(request, response);
			request.getSession().setAttribute("message", "Animal Unsubscribed");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("viewSubscriptions?schedule_ID=" + fsid);
		}
		else {
			request.getSession().setAttribute("message", "Failure to Unsubscribe");
			request.getSession().setAttribute("messageClass", "alert-danger");
		}
	}

}
