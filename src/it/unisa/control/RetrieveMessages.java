package it.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import it.unisa.model.MessageBean;
import it.unisa.model.MessageModelDS;

@WebServlet("/RetrieveMessages")
public class RetrieveMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RetrieveMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		DataSource ds=(DataSource)getServletContext().getAttribute("DataSource");
		String chat=request.getParameter("chatid");
		int chatID=Integer.parseInt(chat);
		MessageModelDS messageModel=new MessageModelDS(ds);
		try {
			Collection<MessageBean> messages=messageModel.doRetrieveByChatID(chatID);
			if(messages!=null&&messages.size()>0) {
				Gson gson=new Gson();
				String messaggi=gson.toJson(messages);
				System.out.println(messaggi);
				out.write(messaggi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
