package profilo;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;




public class UserModelDS {

	public UserModelDS(DataSource ds) {
		this.ds=ds;
	}


	public UserBean checkLogin(String name,String password)throws SQLException {
		if(name==null||name.equals("")||password==null||password.equals(""))
			throw new NullPointerException();
		Connection con=null;
		PreparedStatement ps=null;
		String sql="SELECT Username,Nome,Cognome,Img,Email,Pass,DataNascita,Coin,Ban,Denominazione,DipName, AES_DECRYPT(Pass,'despacito') as Password,Ruolo FROM Utente WHERE Email = ? OR Username=?";
		UserBean bean=new UserBean();
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, name);
			ResultSet rs=ps.executeQuery();

			if(rs.next()&&(rs.getString("Password").compareTo(password))==0) {
				System.out.println("Utente loggato");
				bean.setUsername(rs.getString("Username"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setImg(rs.getBlob("Img"));
				bean.setEmail(rs.getString("Email"));
				bean.setPass(rs.getString("Pass"));
				bean.setDataNascita(rs.getDate("DataNascita"));
				bean.setCoin(rs.getInt("Coin"));
				bean.setBan(rs.getDate("Ban"));
				bean.setDenominazione(rs.getString("Denominazione"));
				bean.setDipName(rs.getString("DipName"));
				bean.setRuolo(rs.getInt("Ruolo"));
			}
			else {
				System.out.println("Utente non loggato");
				return null;
			}
			if(rs!=null)
				rs.close();
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
		return bean;
	}

	public UserBean doRetrieveByUsername(String name)throws SQLException{
		if(name==null||name.equals(""))
			throw new NullPointerException();
		Connection con=null;
		PreparedStatement ps=null;
		String selectSQL="SELECT * FROM Utente WHERE Username = ?";
		UserBean bean = new UserBean();
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(selectSQL);
			ps.setString(1, name);	
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				bean.setUsername(rs.getString("Username"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setImg(rs.getBlob("Img"));
				bean.setEmail(rs.getString("Email"));
				bean.setPass(rs.getString("Pass"));
				bean.setDataNascita(rs.getDate("DataNascita"));
				bean.setCoin(rs.getInt("Coin"));
				bean.setBan(rs.getDate("Ban"));
				bean.setDenominazione(rs.getString("Denominazione"));
				bean.setDipName(rs.getString("DipName"));
				bean.setRuolo(rs.getInt("Ruolo"));
			}
			else
				return null;
			if(rs!=null)
				rs.close();
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
		return bean;
	}

	public UserBean doRetrieveByEmail(String Email)throws SQLException{
		if(Email==null||Email.equals(""))
			throw new NullPointerException();
		Connection con=null;
		PreparedStatement ps=null;
		String selectSQL="SELECT * FROM Utente WHERE Email = ?";
		UserBean bean = new UserBean();
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(selectSQL);
			ps.setString(1, Email);	
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				bean.setUsername(rs.getString("Username"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setImg(rs.getBlob("Img"));
				bean.setEmail(rs.getString("Email"));
				bean.setPass(rs.getString("Pass"));
				bean.setDataNascita(rs.getDate("DataNascita"));
				bean.setCoin(rs.getInt("Coin"));
				bean.setBan(rs.getDate("Ban"));
				bean.setDenominazione(rs.getString("Denominazione"));
				bean.setDipName(rs.getString("DipName"));
				bean.setRuolo(rs.getInt("Ruolo"));
			}
			else
				return null;
			if(rs!=null)
				rs.close();

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

		return bean;
	}


	public Collection<UserBean> doRetrieveUsers() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		String selectSQL="SELECT * FROM Utente WHERE Ruolo=0 ORDER BY Ban DESC;";
		Collection<UserBean> users=new LinkedList<UserBean>();
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(selectSQL);
			//Utility.print("doRetrieveAll:"+ps.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				UserBean bean=new UserBean();
				bean.setUsername(rs.getString("Username"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setImg(rs.getBlob("Img"));
				bean.setEmail(rs.getString("Email"));
				bean.setPass(rs.getString("Pass"));
				bean.setDataNascita(rs.getDate("DataNascita"));
				bean.setCoin(rs.getInt("Coin"));
				bean.setBan(rs.getDate("Ban"));
				bean.setDenominazione(rs.getString("Denominazione"));
				bean.setDipName(rs.getString("DipName"));
				bean.setRuolo(rs.getInt("Ruolo"));
				users.add(bean);
			}
			if(rs!=null)
				rs.close();
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
		return users;
	}

	public Collection<UserBean>  doRetrieveByParametersUser(String str,String ratingOrder, int rating) throws SQLException {
		if(str==null|| ratingOrder==null||ratingOrder.equals("")||rating<0)
			throw new NullPointerException();
		Connection con=null;
		PreparedStatement ps=null;
	




		String selectSQL="SELECT Utente.Username AS Username, ROUND(AVG(ValutazioneMedia)) AS feedback, Nome, Cognome, Denominazione, dipName, Img\n"
				+ "FROM Utente LEFT JOIN (SELECT Materiale.Username AS US, ROUND(AVG(Valutazione)) AS ValutazioneMedia\n"
				+ "FROM Materiale LEFT JOIN Feedback ON Materiale.CodiceMateriale = Feedback.CodiceMateriale \n"
				+ "WHERE Materiale.Hidden = 0\n"
				+ "GROUP BY Materiale.Username\n"
				+ "ORDER BY ValutazioneMedia) AS FeedbackMedio ON Utente.Username = FeedbackMedio.US\n"
				+ "WHERE Ruolo=0\n"
				+ "GROUP BY Utente.Username;";

	

			if ((ratingOrder.compareTo("DESC")==0)) {
				selectSQL="SELECT Utente.Username AS Username, ROUND(AVG(ValutazioneMedia)) AS feedback, Nome, Cognome, Denominazione, dipName, Img\n"
						+ "FROM Utente LEFT JOIN (SELECT Materiale.Username AS US, ROUND(AVG(Valutazione)) AS ValutazioneMedia\n"
						+ "FROM Materiale LEFT JOIN Feedback ON Materiale.CodiceMateriale = Feedback.CodiceMateriale \n"
						+ "WHERE Materiale.Hidden = 0\n"
						+ "GROUP BY Materiale.Username\n"
						+ "ORDER BY ValutazioneMedia) AS FeedbackMedio ON Utente.Username = FeedbackMedio.US\n"
						+ "WHERE (Username LIKE ? OR Nome LIKE ? OR Cognome LIKE ?)AND Ruolo=0\n"
						+ "GROUP BY Utente.Username\n"
						+ "ORDER BY feedback DESC;";
			}
			if ((ratingOrder.compareTo("ASC")==0)) {
				selectSQL="SELECT Utente.Username AS Username, ROUND(AVG(ValutazioneMedia)) AS feedback, Nome, Cognome, Denominazione, dipName, Img\n"
						+ "FROM Utente LEFT JOIN (SELECT Materiale.Username AS US, ROUND(AVG(Valutazione)) AS ValutazioneMedia\n"
						+ "FROM Materiale LEFT JOIN Feedback ON Materiale.CodiceMateriale = Feedback.CodiceMateriale \n"
						+ "WHERE Materiale.Hidden = 0\n"
						+ "GROUP BY Materiale.Username\n"
						+ "ORDER BY ValutazioneMedia) AS FeedbackMedio ON Utente.Username = FeedbackMedio.US\n"
						+ "WHERE (Username LIKE ? OR Nome LIKE ? OR Cognome LIKE ?)AND Ruolo=0\n"
						+ "GROUP BY Utente.Username\n"
						+ "ORDER BY feedback ASC";
			}
			if ((ratingOrder.compareTo("novalue")==0)) {
				selectSQL="SELECT Utente.Username AS Username, ROUND(AVG(ValutazioneMedia)) AS feedback, Nome, Cognome, Denominazione, dipName, Img\n"
						+ "FROM Utente LEFT JOIN (SELECT Materiale.Username AS US, ROUND(AVG(Valutazione)) AS ValutazioneMedia\n"
						+ "FROM Materiale LEFT JOIN Feedback ON Materiale.CodiceMateriale = Feedback.CodiceMateriale \n"
						+ "WHERE Materiale.Hidden = 0\n"
						+ "GROUP BY Materiale.Username\n"
						+ "ORDER BY ValutazioneMedia) AS FeedbackMedio ON Utente.Username = FeedbackMedio.US\n"
						+ "WHERE (Username LIKE ? OR Nome LIKE ? OR Cognome LIKE ?)AND Ruolo=0\n"
						+ "GROUP BY Utente.Username;";
			}


		


		

		Collection<UserBean> collectionBean=new LinkedList<UserBean>();
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(selectSQL);



		
				ps.setString(1, '%'+str+'%');
				ps.setString(2, '%'+str+'%');
				ps.setString(3, '%'+str+'%');
			


			ResultSet rs=ps.executeQuery();

			while(rs.next()) {
				
				
				if (rating!=0) {
					if (rs.getInt("feedback")==rating) {
						System.out.println("RATING: "+rs.getInt("feedback"));
						UserBean bean=new UserBean();
					bean.setUsername(rs.getString("Username"));
					bean.setNome(rs.getString("Nome"));
					bean.setCognome(rs.getString("Cognome"));
					bean.setImg(rs.getBlob("Img"));
					bean.setDenominazione(rs.getString("Denominazione"));
					bean.setDipName(rs.getString("dipName"));
					collectionBean.add(bean);
					}
				}else {

				UserBean bean=new UserBean();

				bean.setUsername(rs.getString("Username"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setImg(rs.getBlob("Img"));
				bean.setDenominazione(rs.getString("Denominazione"));
				bean.setDipName(rs.getString("dipName"));


				collectionBean.add(bean);
			}
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

		return collectionBean;

	}

	public void doSave(UserBean item) throws SQLException {
		if(item==null)
			throw new NullPointerException();
		Connection connection = null;
		PreparedStatement ps = null;

		String insertSQL = "INSERT INTO Utente (Username, Nome, Cognome, Email, Pass, DataNascita, Coin, Denominazione, DipName,Ban,Ruolo) VALUES (?, ?, ?, ?, AES_ENCRYPT(?,'despacito'), ?, ?, ?, ?,?,?)";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(insertSQL);
			ps.setString(1, item.getUsername());
			ps.setString(2, item.getNome());
			ps.setString(3, item.getCognome());
			ps.setString(4, item.getEmail());
			ps.setString(5, item.getPass());

			//	Date dataNascita = item.getDataNascita();

			ps.setDate(6, item.getDataNascita());
			ps.setInt(7, item.getCoin());
			ps.setString(8, item.getDenominazione());
			ps.setString(9, item.getDipName());
			ps.setDate(10, item.getBan());
			ps.setInt(11, item.getRuolo());

			ps.executeUpdate();
			System.out.println("Salvato nel Database");
		} finally {
			try {
				if(ps!=null)
					ps.close();
			}
			finally {
				if(connection!=null)
					connection.close();
			}
		}
	}

	public void manageBan(String username,Date ban) throws SQLException{
		if(username==null||username.equals("")||ban.before(new Date(System.currentTimeMillis())))
			throw new NullPointerException();
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "UPDATE Utente SET Ban = ? WHERE Username = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setDate(1, ban);
			ps.setString(2, username);
			ps.executeUpdate();
			System.out.println("Ban aggiornato");
		} finally {
			try {
				if(ps!=null)
					ps.close();
			}
			finally {
				if(con!=null)
					con.close();
			}
		}
	}

	public void doUpdateCoin(String username,int coin)throws SQLException {
		if(username==null||username.equals("")||coin<=0)
			throw new NullPointerException();
		Connection con = null;
		PreparedStatement ps = null;
		String sql="UPDATE Utente SET Coin=? WHERE Username=?";
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, coin);
			ps.setString(2, username);
			ps.executeUpdate();
			System.out.println("Coins aggiornati");
		}catch(Exception e) {
			e.printStackTrace();
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
	}

	public void doUpdatePassword(String username,String newPassword)throws SQLException {
		if(username==null||username.equals("")||newPassword==null||newPassword.equals(""))
			throw new NullPointerException();
		Connection connection = null;
		PreparedStatement ps = null;

		String sql = "UPDATE Utente SET Pass = AES_ENCRYPT(?,'despacito') WHERE Username = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setString(2, username);
			ps.executeUpdate();
			System.out.println("Password aggiornata");
		} finally {
			try {
				if(ps!=null)
					ps.close();
			}
			finally {
				if(connection!=null)
					connection.close();
			}
		}
	}

	public void doUpdateEmail(String username,String newMail)throws SQLException{
		if(username==null||username.equals("")||newMail==null||newMail.equals(""))
			throw new NullPointerException();
		Connection connection = null;
		PreparedStatement ps = null;

		String sql = "UPDATE Utente SET  Email= ? WHERE Username = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, newMail);
			ps.setString(2, username);
			ps.executeUpdate();
			System.out.println("Email aggiornata");
		} finally {
			try {
				if(ps!=null)
					ps.close();
			}
			finally {
				if(connection!=null)
					connection.close();
			}
		}

	}

	public void doUpdateDepartment(String username,String newDipName,String newUniversity) throws SQLException {
		if(username==null||username.equals("")||newDipName==null||newDipName.equals("")||newUniversity==null||newUniversity.equals(""))
			throw new NullPointerException();
		Connection connection = null;
		PreparedStatement ps = null;

		String sql = "UPDATE Utente SET  dipName= ?,Denominazione=? WHERE Username = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, newDipName);
			ps.setString(2, newUniversity);
			ps.setString(3, username);
			ps.executeUpdate();
			System.out.println("Dipartimento aggiornato");
		} finally {
			try {
				if(ps!=null)
					ps.close();
			}
			finally {
				if(connection!=null)
					connection.close();
			}
		}
	}

	public void doUpdateImage(String username,InputStream image) throws SQLException{
		if(username==null||username.equals("")||image==null)
			throw new NullPointerException();
		Connection connection = null;
		PreparedStatement ps = null;

		String sql = "UPDATE Utente SET  Img=? WHERE Username = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setBlob(1, image);
			ps.setString(2, username);
			ps.executeUpdate();
			System.out.println("Immagine di profilo aggiornata");
		} finally {
			try {
				if(ps!=null)
					ps.close();
			}
			finally {
				if(connection!=null)
					connection.close();
			}
		}
	}

	public float getValutazione(String username)throws SQLException {
		if(username==null||username.equals(""))
			throw new NullPointerException();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		float media=-1.0F;
		String sql="SELECT avg(Valutazione) as Media FROM Materiale,Feedback WHERE Materiale.CodiceMateriale=Feedback.CodiceMateriale and Feedback.CodiceMateriale in (select Materiale.CodiceMateriale from Materiale where Username=?) GROUP BY Materiale.Username;";
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()&&rs!=null)
				media=rs.getFloat("Media");
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
			}
			finally {
				if(con!=null)
					con.close();
			}
		}
		return media;
	}


	public int getRole(String username) throws SQLException{
		if(username==null||username.equals(""))
			throw new NullPointerException();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT Ruolo FROM Utente WHERE Username=?;";
		int ruolo=-1;
		try {
			con=ds.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next())
				ruolo=rs.getInt("Ruolo");
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
			}
			finally {
				if(con!=null)
					con.close();
			}
		}
		return ruolo;
	}


	private DataSource ds;
}
