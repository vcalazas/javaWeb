package com.vcalazas.pointstore.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Timer;

import com.vcalazas.pointstore.models.Test;

public class SqlConnector {

	private static Connection connect() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(  
					"jdbc:mysql://mysql.vcalazas.com.br/vcalazas","vcalazas","Vec404500");
			return con;
		}catch(Exception e){ 
			System.out.println(e);
			return null;
		}  
	}

	public static ResultSet search(String query) {
		try {
			final Connection con = SqlConnector.connect();
			if(con != null ) {
				Statement stmt = con.createStatement();  
				ResultSet rs = stmt.executeQuery(query);
				Timer timer = new Timer(100 , new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							con.close();
						} catch (SQLException e) { }
					}
				});
				timer.setRepeats(false);
				timer.start();

				return rs;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static ResultSet execute(String query) {
		try {
			final Connection con = SqlConnector.connect();
			if(con != null ) {
				Statement stmt = con.createStatement();  
				ResultSet rs = stmt.executeQuery(query);
				Timer timer = new Timer(100 , new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							con.close();
						} catch (SQLException e) { }
					}
				});
				timer.setRepeats(false);
				timer.start();

				return rs;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
