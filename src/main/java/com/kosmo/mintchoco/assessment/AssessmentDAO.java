package com.kosmo.mintchoco.assessment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kosmo.mintchoco.common.JDBCUtil;
import com.kosmo.mintchoco.search.SearchVO;

/*
 * 담당자 : 김정호, 천세문
 */

public class AssessmentDAO {
	// 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	// 쿼리
	final private String SELECT_ASSESSMENT_VIEW = "SELECT * FROM ASSESSMENT_VIEW WHERE MOVIE_NUMBER = ? ORDER BY LIKES DESC, ASSESS_REGDATE DESC";
	final private String INSERT_ASSESSMENT_LIKE = "INSERT INTO ASSESSMENT_EST VALUES(?, ?, ?, ?, default)";
	final private String SELECT_ASSESSMENT_LIKE = "SELECT * FROM ASSESSMENT_VIEW WHERE ASSESS_ID = ?";
	final private String SELECT_ASSESSMENT = "INSERT INTO ASSESSMENT VALUES(?, ?, ?, ?, ?, default)";
	final private String DELETE_ASSESSMENT = "DELETE FROM ASSESSMENT WHERE ASSESS_ID = ?";

	// 영화 평점 목록
	public List<AssessmentVO> selectAssessmentList(int movieNumber) {
		List<AssessmentVO> asseList = new ArrayList<AssessmentVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SELECT_ASSESSMENT_VIEW);
			stmt.setInt(1, movieNumber);
			rs = stmt.executeQuery();
			while(rs.next()) {
				AssessmentVO assessmentVO = new AssessmentVO();
				assessmentVO.setAssessId(rs.getString("assess_id"));
				assessmentVO.setMemberNumber(rs.getInt("member_number"));
				assessmentVO.setMemberNickname(rs.getString("member_nickname"));
				assessmentVO.setMovieNumber(rs.getInt("movie_number"));
				assessmentVO.setMoviePoster(rs.getString("movie_poster"));
				assessmentVO.setMovieTitle(rs.getString("movie_title"));
				assessmentVO.setMovieStars(rs.getFloat("stars"));
				assessmentVO.setMovieKind(rs.getString("movie_kind"));
				assessmentVO.setMovieGrade(rs.getString("movie_grade"));
				assessmentVO.setMovieTime(rs.getInt("movie_time"));
				assessmentVO.setMovieDate(rs.getString("movie_date"));
				assessmentVO.setAssessContent(rs.getString("assess_content"));
				assessmentVO.setAssessStars(rs.getInt("assess_stars"));
				assessmentVO.setAssessRegdate(rs.getDate("assess_regdate"));
				assessmentVO.setLikes(rs.getInt("likes"));
				assessmentVO.setHates(rs.getInt("hates"));
				asseList.add(assessmentVO);
			}
		} catch(Exception e) {
			System.out.println("Error - selectAssessmentList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return asseList;
	}
	
	public AssessmentVO selectAssessmentLike(String assessId) {
		AssessmentVO assessmentVO = new AssessmentVO();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SELECT_ASSESSMENT_LIKE);
			stmt.setString(1, assessId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				assessmentVO.setAssessId(rs.getString("assess_id"));
				assessmentVO.setMemberNumber(rs.getInt("member_number"));
				assessmentVO.setMemberNickname(rs.getString("member_nickname"));
				assessmentVO.setMovieNumber(rs.getInt("movie_number"));
				assessmentVO.setMoviePoster(rs.getString("movie_poster"));
				assessmentVO.setMovieTitle(rs.getString("movie_title"));
				assessmentVO.setMovieStars(rs.getFloat("stars"));
				assessmentVO.setMovieKind(rs.getString("movie_kind"));
				assessmentVO.setMovieGrade(rs.getString("movie_grade"));
				assessmentVO.setMovieTime(rs.getInt("movie_time"));
				assessmentVO.setMovieDate(rs.getString("movie_date"));
				assessmentVO.setAssessContent(rs.getString("assess_content"));
				assessmentVO.setAssessStars(rs.getInt("assess_stars"));
				assessmentVO.setAssessRegdate(rs.getDate("assess_regdate"));
				assessmentVO.setLikes(rs.getInt("likes"));
				assessmentVO.setHates(rs.getInt("hates"));
			}
		} catch(Exception e) {
			System.out.println("Error - selectAssessmentLike()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return assessmentVO;
	}
	
	public int insertAssessmentLike(int memberNumber, String assessId, String assessEst) {
		int returnValue = 1;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(INSERT_ASSESSMENT_LIKE);
			stmt.setString(1, Integer.toString(memberNumber) + assessId);
			stmt.setInt(2, memberNumber);
			stmt.setString(3, assessId);
			stmt.setString(4, assessEst);
			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error - updateAssessmentLike()\n");
			returnValue = 0;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return returnValue;
	}
	
	public void insertAssessment(int memberNumber, int movieNumber, String assessContent, int assessStars) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SELECT_ASSESSMENT);
			stmt.setString(1, Integer.toString(memberNumber) + Integer.toString(movieNumber));
			stmt.setInt(2, memberNumber);
			stmt.setInt(3, movieNumber);
			stmt.setString(4, assessContent);
			stmt.setInt(5, assessStars);
			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error - insertAssessment()\n");
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void deleteAssessment(String assessId) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DELETE_ASSESSMENT);
			stmt.setString(1, assessId);
			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error - deleteAssessment()\n");
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}
