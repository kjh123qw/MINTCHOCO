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

}
