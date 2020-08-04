/*
 * There are 7 steps to build a Database Connection
 * 
 * 1- Import package 	===> java.sql.*
 * 2- Load and Register the Driver ===> class.forName("some driver  name") ---> "com.mysql.jdbc.Driver"
 * 3- Establish Connection ===> "jdbc:mysql://localhost:3306/"
 * 4- Create Statement ===> Statement, PreparedStatement, CollableStatement
 * 5- Execute Query
 * 6- Process Result
 * 		ResultSet result = statement.executeQuery("SQL Sentence");
 * 		All data from sql statement is fetched into result set and with help of result.next() method can
 * 		be loop every row in the fetched data.
 * 
 * 		result.getInt("column index") and result.getString("column index / column name"), these two methods get
 * 		data in the related row.
 * 
 * 7- Close connection ===> statement.close() and connection.close()
 * 
 * Example-1 :
 * 
 * String sql = "INSERT INTO Customer (firstname, lastname) VALUES (?, ?)";
 * PreparedStatement statement = conn.prepareStatement(sql);
 * statement.setString(1, firstname);
 * statement.setString(2, lastname);
 * statement.executeUpdate();
 * statement.close();
 * conn.close();
 * 
 * Example-2 :
 * String sql = "SELECT * FROM Customer";
 * Statement statement = conn.createStatement();
 * ResultSet result  = statement.executeQuery(sql);
 * 
 * while(result.next()){
 * 	String firstname = result.getString(1); // column index or column name
 * 	String lastname = result.getString("lastname");
 * 	System.out.println(firstname + " " + lastname)
 * }
 * statement.close();
 * conn.close();
 * 
 */
package connection;

import java.sql.*;

import javax.swing.JOptionPane;

public class DbConnection {
	
	Connection connection = null;
	
	public Connection get_Connection(String driver, String db_url, String db_username, String db_password) {
		
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(db_url, db_username, db_password);
			// System.out.println("Connection succeed");
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Did not find any connector!\n" + e.toString(), "Connection Problem", JOptionPane.INFORMATION_MESSAGE);
			// System.out.println("Did not find any connector");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection Failed!\n" + e.toString(), "Connection Problem ", JOptionPane.INFORMATION_MESSAGE);
			// System.out.println("Connection failed");
		}
		
		return connection;
	}	

}
