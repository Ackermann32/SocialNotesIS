package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;


public class ChatModelDS{

	public ChatModelDS(DataSource ds) {
		this.ds=ds;
	}
	
	public ChatBean doRetrieveByKey(String code) throws SQLException {
		if(code==null||code.equals(""))
			throw new NullPointerException();
		Connection con=null;
		PreparedStatement ps=null;
		
		int codice = Integer.parseInt(code);
		String selectSQL="SELECT * FROM Chat WHERE ChatID = ?;";
        ChatBean bean = new ChatBean();
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(selectSQL);
			ps.setInt(1, codice);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				bean.setChatID(rs.getInt("ChatID"));
				bean.setTitolo(rs.getString("Titolo"));
				return bean;
			}
		}
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			finally {
				if(con!=null)
					con.close();
			}
		}
		return null;
	}
	
	public ChatBean doRetrieveLast() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
	
		String selectSQL="SELECT * FROM Chat ORDER BY ChatID;";
        ChatBean bean = new ChatBean();
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(selectSQL);
			ResultSet rs=ps.executeQuery();
			if(rs.last()) {
				bean.setChatID(rs.getInt("ChatID"));
				bean.setTitolo(rs.getString("Titolo"));
				return bean;
			}
		}
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			finally {
				if(con!=null)
					con.close();
			}
		}
		return null;
	}

	

	public void doSave(ChatBean item) throws SQLException {
		if(item==null)
			throw new NullPointerException();
		Connection con=null;
		PreparedStatement ps=null;
		String sql="INSERT INTO Chat (Titolo) VALUES (?)";
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, item.getTitolo());
			System.out.println(ps.executeUpdate());


		}finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (con != null) {
					con.close();
				}
			}
		}
		
	}
	
	public Collection<ChatBean> doRetrieveChatName(String username) throws SQLException {
		if(username==null||username.equals(""))
			throw new NullPointerException();
		Connection con=null;
		PreparedStatement ps=null;
		
		String selectSQL="SELECT Chat.ChatID, Titolo from Utente inner join Partecipazione on Utente.Username = Partecipazione.Username inner join Chat  on Partecipazione.ChatID = Chat.ChatID WHERE Utente.Username = ?;";
		Collection<ChatBean> chats=new LinkedList<ChatBean>();
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(selectSQL);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				ChatBean bean=new ChatBean();
				bean.setChatID(rs.getInt("ChatID"));
				bean.setTitolo(rs.getString("Titolo"));
				chats.add(bean);
				
			}
		}
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			finally {
				if(con!=null)
					con.close();
			}
		}
		return chats;
	}

	private DataSource ds;
}
