package com.apartogether.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SuperDao {	
	protected Connection conn = null ;
	
	public Connection getConnection() { // step02. 접속 객체 구하기
		String url = "jdbc:oracle:thin:@116.122.37.100:1521:xe" ;
		String id = "DBICTteam001" ;
		String password = "ICT1q2w3e4r" ;
		
		try {
			this.conn = DriverManager.getConnection(url, id, password) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn ;
	}
	
	public SuperDao() { // step01. 드라이버 로딩
		String driver = "oracle.jdbc.driver.OracleDriver" ;
		try {
			Class.forName(driver) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
