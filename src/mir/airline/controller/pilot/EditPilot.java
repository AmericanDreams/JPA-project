package mir.airline.controller.pilot;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class EditPilot
 */
@WebServlet("/EditPilot")
public class EditPilot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PilotService pilotService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/Pilots");
		} else {
			try {
				Pilot pilot = pilotService.getPilotById(Integer.valueOf(id));
				if (pilot == null) {
					response.sendRedirect(request.getContextPath() + "/Pilots");
				} else {

					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					String date = format.format(pilot.getDob());

					request.setAttribute("pilotRanks", PilotRank.values());
					request.setAttribute("pilotRank", pilot.getRank());
					request.setAttribute("name", pilot.getName());
					request.setAttribute("surname", pilot.getSurname());
					request.setAttribute("dob", date);
					request.setAttribute("pilotId", pilot.getId());

					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher(View.pilot + "/editPilot.jsp");

					requestDispatcher.forward(request, response);
				}
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/Pilots");
			}
		}

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
		String pilotId = request.getParameter("pilotId");
		Date date = null;

		boolean hasError = false;
		
		if(pilotId == null || pilotId.isEmpty()){
			response.sendRedirect(request.getContextPath() + "/Pilots");
		}

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
			request.setAttribute("dobError", "Tarix secin");
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

		if (hasError) {

			request.setAttribute("pilotRanks", PilotRank.values());
			request.setAttribute("pilotRank", PilotRank.valueOf(pilotRank));
			request.setAttribute("name", name);
			request.setAttribute("surname", surname);
			request.setAttribute("dob", dob);

			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher(View.pilot + "/EditPilot.jsp");

			request.setAttribute("hasError", true);

			requestDispatcher.forward(request, response);

		} else {
			Pilot p = new Pilot(name, surname, date,
					PilotRank.valueOf(pilotRank));

			try {
				pilotService.editPilotById(Integer.valueOf(pilotId), p);
				response.sendRedirect(request.getContextPath() + "/Pilots?status=uptaded");
				

			} catch (Exception e) {

				request.setAttribute("pilotRanks", PilotRank.values());
				request.setAttribute("pilotRank", PilotRank.valueOf(pilotRank));
				request.setAttribute("name", name);
				request.setAttribute("surname", surname);
				request.setAttribute("dob", dob);

				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher(View.pilot + "/EditPilot.jsp");

				request.setAttribute("exception",
						"Pilot daxil edilməsi mümkün olmadı bir daha cəhd edin");

				requestDispatcher.forward(request, response);
			}

		}
	}

}
