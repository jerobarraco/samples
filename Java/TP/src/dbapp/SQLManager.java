package dbapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SQLManager {
	private static Connection con = null;
	
	private static SQLManager sm = null;
	
	public String host = "localhost";
	public Integer port = 3050;
	public String db = "c:\\Svn\\pysnipps\\Java\\lab4\\BD_guia1.FDB";
	private SQLManager() throws SQLException, ClassNotFoundException {
		Class.forName("org.firebirdsql.jdbc.FBDriver");
		String url = "jdbc:firebirdsql:"+ host + "/" + port.toString() + 
						":"+db;
		con = DriverManager.getConnection(url, "SYSDBA", "masterkey");
		con.setAutoCommit(true);
	}

	public static PreparedStatement getSt(String SQL) throws SQLException{
		if (sm == null){
			try {
				sm = new SQLManager();
			} catch (Exception ex) {
				Logger.getLogger(SQLManager.class.getName()).log(Level.SEVERE, null, ex);
				sm = null;
			}
		}
		return con.prepareStatement(SQL);
	}	
}
