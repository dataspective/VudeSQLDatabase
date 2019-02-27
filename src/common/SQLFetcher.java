package common;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;



public class SQLFetcher {
	

	
	public static StringBuilder query1(JTextField textField) {
		StringBuilder sb2=new StringBuilder();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		String txt = textField.getText();
		String _url = "jdbc:mysql://localhost/JdbcLab?autoReconnect=true&useSSL=false";

		try {

			// Step 1: Load the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Step 2: make a connection
			conn = DriverManager.getConnection(_url, "root", "root");

			// Step 3: Create a statement
			stmt = conn.createStatement();

			// Step 4: Make a query
			rs = stmt.executeQuery(txt);

			// Step 5: Use ResultSetMetaData to discover the size of the returned relation
			ResultSetMetaData metaData = rs.getMetaData();

			// Step 5.1: Get the column header info for report writing
			int numColumns = metaData.getColumnCount();
			
			for(int i = 1; i <= numColumns; i++)
			{
			   sb2.append(String.format(" %-20s",metaData.getColumnLabel(i)));
			   System.out.print(String.format(" %-20s",metaData.getColumnLabel(i)));
			}
			System.out.println("");
			sb2.append("\n");
			// Step 6: Print out the results
			 
			while (rs.next()) {
				for (int i = 1; i <= numColumns; i++) {
					Object obj = rs.getObject(i);
					if (obj != null) {
						sb2.append(String.format(" %-20s",rs.getObject(i).toString()));
					 System.out.print(String.format(" %-20s",rs.getObject(i).toString()));
					}else {
						
						sb2.append(String.format(" %-20s","NULL"));
						 System.out.print(String.format(" %-20s","NULL"));
					}
				}
				System.out.println("");
				sb2.append("\n");
			}
     
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally { // ALWAYS clean up your DB resources
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return sb2;
		
	}
	
}