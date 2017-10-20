package mir.airline.controller.pilot;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mir.airline.service.PilotService;
import mir.airline.util.PilotRank;
import mir.airline.util.View;

/**
 * Servlet implementation class Pilots
 */
@WebServlet("/Pilots")
public class Pilots extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    PilotService pilotService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(View.pilot + "/pilotView.jsp");

		request.setAttribute("pilots", pilotService.getAllPilots());
		String status = request.getParameter("status");
		if(status!= null && status.equals("deleted")){
			request.setAttribute("succsess", "Silindi");
		}
		else if(status!= null && status.equals("uptaded")){
			request.setAttribute("succsess", "Deyishdirildi");
		}
		else if(status!= null && status.equals("inserted")){
			request.setAttribute("succsess", "Elave edildi");
		}
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
