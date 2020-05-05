package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	public Connection connect() {

		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paymentdb","root", "");
			// For testing
			System.out.println("Successfully connected---1");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String viewPayment() {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
		
			output = "<table border='1'><tr><th>payment Id</th>" + "<th>Patient Name</th><th>Patient Address</th>"+ "<th>Time</th>"
					+ "<th>Date</th>" +"<th>Amount</th>"+"<th>Hospital Name</th>"+ "<th>Update</th><th>Remove</th></tr>";


			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String payId = Integer.toString(rs.getInt("PId"));
				String pName = rs.getString("Pname");
				String pAddress = rs.getString("Paddress");
				String time = rs.getString("Ttime");
				String date = rs.getString("Pdate");		
				String amount = rs.getString("Pamount");
				String Hname = rs.getString("Hname");

				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate'" + "name='hidItemIDUpdate' type='hidden'" + "value='"
						+ payId + "'>" + payId + "</td>";
				
				output += "<td>" + pName + "</td>";
				output += "<td>" + pAddress + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + Hname + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button'" + "value='Update'"
						+ "class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button'"
						+ "value='Remove'" + "class='btnRemove btn btn-danger' data-itemid='" + payId+ "'>"
						+ "</td></tr>";
			}
			
			con.close();
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the payment Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String insertPayment(String id, String name, String address, String time, String date,String amount,String Hname) {

		String output = "";
		try {

			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " INSERT INTO payment (PId,  Pname,Paddress ,Ttime,Pdate,Pamount,Hname ) VALUES (?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, time);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, amount);
			preparedStmt.setString(7, Hname);
			
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = viewPayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
			System.out.println("Add "+output);

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updatePayment(String id, String name, String address, String time,String date,String amount, String Hname) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payment SET Pname=?,Paddress=?,Ttime=?,Pdate=?,Pamount=?, Hname=? WHERE PId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(7, id);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, address);
			preparedStmt.setString(3, time);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, amount);
			preparedStmt.setString(6, Hname);
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newPayment = viewPayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String pId) {
		String output = "";
		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where PId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, pId);

			// execute the statement
			preparedStmt.execute();
			con.close();
		
			String newPayment = viewPayment() ;
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
	

}
