package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class onPrem {

	public String url = "jdbc:postgresql://124.70.165.138:5432/:5432/scantist";
	public String user = "scantist";
	public String password = "=4?9! [3&Z)0TY9X%($A?&x\\B2\\]]);e";
	
	
	/**
	 * This method is used to delete policy from Scantist compliance policy
	 * @return Connection
	 */

	public Connection connect() {
		System.out.println(password);
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Delete from scantist_compliance_policy where policy_name='testPolicy';");
			while (rs.next()) {
				int id = rs.getInt("id");
				String policyName = rs.getString("policy_name");

				System.out.println("ID = " + id);
				System.out.println("policyName = " + policyName);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}
	
	/**
	 * This method is used to update account email address
	 * @return Connection
	 */
	public Connection confirmEmailAddress(String email) {
		
		Connection conn1 = null;
		try {
			conn1 = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");

			Statement stmt = conn1.createStatement();
			ResultSet rs = stmt
					.executeQuery("update account_emailaddress set verified='true' where email='" + email + "';");

			while (rs.next()) {
				boolean flag = rs.getBoolean("verified");

				System.out.println("flag = " + flag);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn1;
	}
	
	/**
	 * This method is used to delete uploaded file from code upload table
	 * @return Connection
	 */
	public Connection deleteUploadFile() {
		
		Connection conn2 = null;
		try {
			conn2 = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");

			Statement stmt = conn2.createStatement();

			String sql = "Delete from scantist_codeupload where filename='jfinal-master.zip' AND project_id=13;";
			stmt.executeQuery(sql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn2;
	}
	
	
}

