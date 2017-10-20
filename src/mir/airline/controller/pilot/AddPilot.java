package mir.airline.controller.pilot;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mir.airline.model.Pilot;
import mir.airline.service.PilotService;
import mir.airline.util.PilotRank;
import mir.airline.util.View;

/**
 * Servlet implementation class AddPilot
 */
@WebServlet("/AddPilot")
public class AddPilot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
	PilotService pilotService;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(View.pilot + "/pilotForm.jsp");

		request.setAttribute("pilotRanks", PilotRank.values());

		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String dob = request.getParameter("dob");
		String pilotRank = request.getParameter("pilotRank");
		Date date = null;

		boolean hasError = false;

		if (name == null || name.length() < 6) {
			hasError = true;
			request.setAttribute("nameError", "6 simvoldandan az olmamalidir");
		}

		if (surname == null || surname.length() < 6) {
			hasError = true;
			request.setAttribute("surnameError",
					"6 simvoldandan az olmamalidir");
		}

		if (dob == null || dob.isEmpty()) {
			hasError = true;
			request.setAttribute("dobError",
					"Tarix secin");
		} else {
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				date = format.parse(dob);
			} catch (ParseException e) {
				hasError = true;
				request.setAttribute("dobError",
						"Format Duzgun deyil dd-MM-yyyy olmalidir");
			}
		}

		if (pilotRank == null || pilotRank.isEmpty()) {
			hasError = true;
			request.setAttribute("pilotRankError", "Pilot Rank secilmelidir");
		}
		
		if(hasError){
			
			request.setAttribute("pilotRanks", PilotRank.values());
			request.setAttribute("name", name);
			request.setAttribute("surname", surname);
			request.setAttribute("dob", dob);
			
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher(View.pilot + "/pilotForm.jsp");

			request.setAttribute("hasError", true);

			requestDispatcher.forward(request, response);
			
		}else{
			Pilot p = new Pilot(name, surname, date, PilotRank.valueOf(pilotRank));
			
			try{
				pilotService.addPilot(p);		
				response.sendRedirect(request.getContextPath()+"/Pilots?status=inserted");

			}catch(Exception e){
				
				request.setAttribute("pilotRanks", PilotRank.values());
				request.setAttribute("name", name);
				request.setAttribute("surname", surname);
				request.setAttribute("dob", dob);
				
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher(View.pilot + "/pilotForm.jsp");

				request.setAttribute("exception", "Pilot daxil edilməsi mümkün olmadı bir daha cəhd edin");

				requestDispatcher.forward(request, response);
			}
			
			
		}

	}

}
