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
import mir.airline.util.View;

/**
 * Servlet implementation class DeletePilot
 */
@WebServlet("/DeletePilot")
public class DeletePilot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    PilotService pilotService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/Pilots");
		} else {
			try{
				pilotService.deletePilotById(Integer.valueOf(id));
				response.sendRedirect(request.getContextPath() + "/Pilots?status=deleted");
			}catch(Exception e){
				request.setAttribute("exception","Silinme mumkun olmadi" );
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher(View.pilot + "/pilotView.jsp");

				requestDispatcher.forward(request, response);
			} 
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
