package com.kosmo.mintchoco.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * 담당자 : 김정호
 */

public class JDBCUtil {

	
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
			return con;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(PreparedStatement stmt, Connection conn) {  // insert, update, delete �� ȣ��
		if(stmt != null) {
			try {
				if(!stmt.isClosed()) stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {  // select �� ȣ��
		if(rs != null) {
			try {
				if(!rs.isClosed()) rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if(stmt != null) {
			try {
				if(!stmt.isClosed()) stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
		
	}
	
}
