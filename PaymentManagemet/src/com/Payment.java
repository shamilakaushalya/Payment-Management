package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	
		
		//A common method to connect to the DB
	//A common method to connect to the DB
	private Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
			
		
		
		//Insert Project Details
		public String insertPayment(String paymentID, String userID, String cardname, String cardnumber,String expire_date, String cvv ){
			String output = "" ;
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for inserting."; 
				}
				
					
					// create a prepared statement
					String query = "INSERT INTO `payment1`(`paymentID`, `userID`, `cardname`, `cardnumber`,  `expire_date`, `cvv`) VALUES (?,?,?,?,?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, userID);
					 preparedStmt.setString(3, cardname);
					 preparedStmt.setString(4, cardnumber);
					 preparedStmt.setString(5, expire_date);					 
					 preparedStmt.setString(6, cvv); 
					
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newPayment = readPayment(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Payment.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String readPayment(){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
				// Prepare the html table to be displayed
				output = 
						"<table border='1' >"+ 
						"<tr >" +
						     	
							 "<th >User ID</th>" +
							 "<th>Card Name</th>" +
							 "<th>Card Number</th>" +							 
							 "<th>Expire Date</th>" +
							 "<th>CVV</th>" +
							 "<th>Update</th>" +
							 "<th>Remove</th>" +
						
						 "</tr>";
	
				String query = "select * from `payment1`";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 
					 //String researchID =  Integer.toString(rs.getInt("researchID"));
					 int paymentID = rs.getInt("paymentID");
					 String userID = rs.getString("userID");
					 String cardname = rs.getString("cardname");
					 String cardnumber = rs.getString("cardnumber");
					 String expire_date = rs.getString("expire_date");					 
					 String cvv = rs.getString("cvv");
					 
	
					 
					 // Add into the html table
					 
					 
					 output += "<tr><td>" + userID + "</td>";
					 output += "<td>" + cardname + "</td>";
					 output += "<td>" + cardnumber + "</td>";					 
					 output += "<td>" + expire_date + "</td>";
					 output += "<td>" + cvv + "</td>";
					
		
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + paymentID + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + paymentID + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the payments.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String updatePayment(String paymentID, String userID, String cardname, String cardnumber, String expire_date, String cvv){ 
			String output = ""; 
			try{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				String query = "UPDATE `payment1` SET `userID`=?,`cardname`=?,`cardnumber`=?,`expire_date`=?, `cvv`=? WHERE `paymentID`=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				  
				 preparedStmt.setString(1, userID);
				 preparedStmt.setString(2, cardname);
				 preparedStmt.setString(3, cardnumber);		
				 preparedStmt.setString(4, expire_date);	
				 preparedStmt.setString(5, cvv);
				 
				 preparedStmt.setString(6, paymentID);
				 
				// preparedStmt.setString(4, sector);
				
				 
 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newPayment = readPayment(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the research.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String deletePayment(String paymentID) { 
			String output = ""; 
			try{ 
				Connection con = connect();
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
				    String query ="DELETE FROM `payment1` WHERE paymentID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
					
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					  
					String newPayment = readPayment(); 
					output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}