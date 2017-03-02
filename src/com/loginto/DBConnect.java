package com.loginto;

import java.sql.*;
import java.util.Arrays;

public class DBConnect {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private String driverName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/uzytkownicy";
	private String username = "root"; 
	private String password = ""; 
	
	public DBConnect(){
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("Brak klasy sterownika");
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			System.out.println("Nieudane po³¹czenie z " + url);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public boolean checkLoginAndPassword(String login, char[] password){
		if(login.equals("")) return false;
		String query = "SELECT password FROM users WHERE login = ?";
		boolean isCorrect = false;
		try {
			PreparedStatement stat = connection.prepareStatement(query);
			stat.setString(1,  login);
			resultSet = stat.executeQuery();
			if(resultSet.next()){
				String passwordFromDB = resultSet.getString(1);
				char[] correctPassword = passwordFromDB.toCharArray();
				if(password.length != correctPassword.length){
					isCorrect = false;
				} else{
					isCorrect = Arrays.equals(correctPassword, password);
					isCorrect = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Arrays.fill(password, '0');
		return isCorrect;
	}
	
	public boolean addNewAccount(String login, char[] password){
		boolean added = false;
		if(login.length() < 3 || password.length <3) return false;
		String query = "SELECT count(login) FROM users WHERE login = ?";
		try {
			PreparedStatement stat = connection.prepareStatement(query);
			stat.setString(1,  login);
			resultSet = stat.executeQuery();
			if(resultSet.next()){
				int czyJest = resultSet.getInt(1);
				if(czyJest == 1) added = false;
				else if(czyJest == 0){
					String query1 = "INSERT INTO users VALUES(NULL, ?, ?)";
					PreparedStatement insertStat = connection.prepareStatement(query1);
					insertStat.setString(1,  login);
					String tempPassword = new String(password);
					insertStat.setString(2,  tempPassword);
					insertStat.executeUpdate();
					added = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return added;
	}
}
