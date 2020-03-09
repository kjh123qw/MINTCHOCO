package com.kosmo.mintchoco.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kosmo.mintchoco.common.JDBCUtil;

/*
 * 담당자 : 유지상, 장세진
 */

public class MemberDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String MEMBER_INSERT = "INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL,?,?,?,?,?,?,DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT);";
	private final String MEMBER_CHECKEMAIL = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_EMAIL = ?";
	private final String MEMBER_CHECKNICKNAME = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_NICKNAME = ?";
	private final String MEMBER_CHECKNAME = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_NAME = ?";
	
	private final String MEMBER_FINDCHECK = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_EMAIL = ? AND MEMBER_NAME = ?";
	private final String MEMBER_CHANGEPWD = "UPDATE MEMBER SET MEMBER_PWD = ? WHERE MEMBER_EMAIL = ? AND MEMBER_NAME = ?";
	
	public void joinMember(MemberVO vo) { //회원가입
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_INSERT);
			stmt.setString(1, vo.getEmail());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getNickName());
			stmt.setInt(5, vo.getAge());
			stmt.setString(6, vo.getGender());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

	public int checkEmail(String email) {
		
		int result = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_CHECKEMAIL);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		
		return result;
	}

	public int checkNickname(String nickname) {
		
		int result = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_CHECKNICKNAME);
			stmt.setString(1, nickname);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return result;
	}

	public int checkName(String name) {
		
		int result = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_CHECKNAME);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return result;
	}

	public int checkfind(String email, String name) {
		
		int result = 0;
		
try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_FINDCHECK);
			stmt.setString(1, email);
			stmt.setString(2, name);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return result;
	}

	public void changePwd(String email, String name, String securityPwd) {

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_CHANGEPWD);
			stmt.setString(1, securityPwd);
			stmt.setString(2, email);
			stmt.setString(3, name);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

}
