package profilo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/RemoveBan")
public class RemoveBan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveBan() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null) {
			response.sendRedirect("homepage.jsp");
		}
		DataSource ds=(DataSource)getServletContext().getAttribute("DataSource");
		String username=request.getParameter("username");
		UserModelDS user =new UserModelDS(ds);
		try {
			user.manageBan(username,new Date(System.currentTimeMillis()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String removeBanLink=response.encodeURL("/userBanned.jsp");
		request.setAttribute("username", username);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(removeBanLink);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
