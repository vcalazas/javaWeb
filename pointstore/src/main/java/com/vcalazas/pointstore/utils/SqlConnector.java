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

	private Connection connect() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/marketplace","root","123456789");
			return con;
		}catch(Exception e){ 
			System.out.println(e);
			return null;
		}  
	}

	public ResultSet search(String query) {
		try {
			final Connection con = this.connect();
			if(con != null ) {
				Statement stmt = con.createStatement();  
				ResultSet rs = stmt.executeQuery(query);
//				Timer timer = new Timer(3000 , new ActionListener() {
//					public void actionPerformed(ActionEvent evt) {
//						try {
//							con.close();
//						} catch (SQLException e) { }
//					}
//				});
//				timer.setRepeats(false);
//				timer.start();

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

	public ResultSet execute(String query) {
		try {
			final Connection con = this.connect();
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
