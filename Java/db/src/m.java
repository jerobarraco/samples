import java.sql.*;


public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "Select * from tabla";
		try{
			//Class.forName("org.gjt.mm.mysql.Driver");
			Class.forName("org.firebirdsql.jdbc.FBDriver"); 
			String url = "jdbc:firebirdsql:localhost/3050/cager";
			
			url = "jdbc:firebirdsql://192.168.89.44/3050/C:/java/financial.fdb";
			Connection con = DriverManager.getConnection(url, "SYSDBA", "masterkey");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println("Columna1: "+rs.getString(1));
				System.out.println("Columna2: "+rs.getInt("col2"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		/*
		Connection con;
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete from table1");
		stmt.executeQuery("Select * from table1");
		
		String SQL1 = "Select fld1 from tbl1 where id=?";
		
		String SQL2 = "? := Funccion1(?);";
		PreparedStatement pStmt = con.prepareStatement(SQL1);
		pStmt.setInt(1, 999);*/
	}

}
