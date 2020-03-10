package com.kosmo.mintchoco.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kosmo.mintchoco.common.JDBCUtil;

/*
 * 담당자 : 장세진
 */

public class MemberMDAO {
	// JDBC 관련변수 선언
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// 필요SQL
	private final String GET_DETAIL = "SELECT * FROM MEMBER WHERE MEMBER_NUMBER=?";
	private final String ASSESSMENT_COUNT = "SELECT COUNT(*) FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=?";
	
	// 계정 정보
	public MemberVO getDetailInfo(String memberNum) {
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getDetailInfo()");
		MemberVO member = new MemberVO();
		
		try {		
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(GET_DETAIL);
			stmt.setString(1, memberNum);
			rs = stmt.executeQuery();
			if(rs.next()) {
				member.setNumber(rs.getInt("MEMBER_NUMBER"));
				member.setEmail(rs.getString("MEMBER_EMAIL"));
				member.setName(rs.getString("MEMBER_NAME"));
				member.setNickName(rs.getString("MEMBER_NICKNAME"));
				member.setAge(rs.getInt("MEMBER_AGE"));
				member.setGender(rs.getString("MEMBER_GENDER"));
				member.setIntroduce(rs.getString("MEMBER_INTRODUCE"));
				member.setIndate(rs.getDate("MEMBER_INDATE"));
				member.setLike_flag(rs.getString("MEMBER_FAVORITE_FLAG"));
				member.setAssessment_flag(rs.getString("MEMBER_ASSESSMENT_FLAG"));
				member.setInfo_flag(rs.getString("MEMBER_INFO_FLAG"));
			}
		} catch(Exception e) {
			System.out.println("getDetailInfo()" +e);
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
			return member;
	}
	
	// 몇 건 평가했는지
	public int getAssessmentCnt(String memberNum) {
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getAssessmentCnt()");
		int assess_cnt = 0;
		
		try {		
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ASSESSMENT_COUNT);
			stmt.setString(1, memberNum);
			rs = stmt.executeQuery();
			if(rs.next()) {
				assess_cnt = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getAssessmentCnt()" +e);
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
			return assess_cnt;
	}
	
	
}
