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

@WebServlet("/SetBan")
public class SetBan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SetBan() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null) {
			response.sendRedirect("homepage.jsp");
		}
		String date=request.getParameter("durataBan");
		Date ban=Date.valueOf(date);
		DataSource ds=(DataSource)getServletContext().getAttribute("DataSource");
		String username=request.getParameter("username");
		UserModelDS user =new UserModelDS(ds);
		ReportModelDS report=new ReportModelDS(ds);
		
		try {
			user.manageBan(username,ban);
			report.removeReport(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String editUserLink=response.encodeURL("/userBanned.jsp");
		request.setAttribute("username", username);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(editUserLink);
		dispatcher.forward(request, response);
	}

}
