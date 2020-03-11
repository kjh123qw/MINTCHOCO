package com.kosmo.mintchoco.assessment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kosmo.mintchoco.common.JDBCUtil;

/*
 * 담당자 : 김정호, 천세문
 */

public class AssessmentMDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// 필요SQL
	private final String ASSESSMENT_GET_LIST = "SELECT * FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=?";
	private final String ASSESSMENT_BY_TITLE = "SELECT * FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=? ORDER BY MOVIE_TITLE DESC";
	private final String ASSESSMENT_BY_INDATE = "SELECT * FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=? ORDER BY ASSESS_ID DESC";
	private final String ASSESSMENT_BY_STARS = "SELECT * FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=? ORDER BY ASSESS_STARS DESC, STARS DESC";
	private final String ASSESSMENT_DELETE = "DELETE FROM ASSESSMENT WHERE ASSESS_ID in (?)";
	// 평가목록 가져오기
		public List<AssessmentMVO> getAssessmentList(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getAssessmentList()");
			List<AssessmentMVO> assessList = new ArrayList<AssessmentMVO>();		
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ASSESSMENT_GET_LIST);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
			
				while(rs.next()) {
					AssessmentMVO assess = new AssessmentMVO();
					assess.setAssessNum(rs.getString("ASSESS_ID"));
					assess.setMovieNum(rs.getInt("MOVIE_NUMBER"));
					assess.setPoster(rs.getString("MOVIE_POSTER"));										
					assess.setMovieTitle(rs.getString("MOVIE_TITLE"));
					assess.setKind(rs.getString("MOVIE_KIND"));
					assess.setGrade(rs.getString("MOVIE_GRADE"));
					assess.setPlayTime(rs.getInt("MOVIE_TIME"));
					assess.setRelease(rs.getString("MOVIE_DATE"));
					assess.setaContent(rs.getString("ASSESS_CONTENT"));
					assess.setAstars(rs.getInt("ASSESS_STARS"));
					assess.setStars(rs.getInt("STARS"));
					assess.setAssessIndate(rs.getDate("ASSESS_REGDATE"));
					assess.setLikes(rs.getInt("LIKES"));
					assess.setHates(rs.getInt("HATES"));					
					assessList.add(assess);
				}
			} catch(Exception e) {
				System.out.println("getAssessmentList()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return assessList;
			}
		
		public List<AssessmentMVO> getAssessSortbyTitle(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getAssessSortbyTitle()");
			List<AssessmentMVO> assessList = new ArrayList<AssessmentMVO>();		
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ASSESSMENT_BY_TITLE);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
			
				while(rs.next()) {
					AssessmentMVO assess = new AssessmentMVO();
					assess.setAssessNum(rs.getString("ASSESS_ID"));
					assess.setMovieNum(rs.getInt("MOVIE_NUMBER"));
					assess.setPoster(rs.getString("MOVIE_POSTER"));										
					assess.setMovieTitle(rs.getString("MOVIE_TITLE"));
					assess.setKind(rs.getString("MOVIE_KIND"));
					assess.setGrade(rs.getString("MOVIE_GRADE"));
					assess.setPlayTime(rs.getInt("MOVIE_TIME"));
					assess.setRelease(rs.getString("MOVIE_DATE"));
					assess.setaContent(rs.getString("ASSESS_CONTENT"));
					assess.setAstars(rs.getInt("ASSESS_STARS"));
					assess.setStars(rs.getInt("STARS"));
					assess.setAssessIndate(rs.getDate("ASSESS_REGDATE"));
					assess.setLikes(rs.getInt("LIKES"));
					assess.setHates(rs.getInt("HATES"));					
					assessList.add(assess);
				}
			} catch(Exception e) {
				System.out.println("getAssessSortbyTitle()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return assessList;
			}
		
		public List<AssessmentMVO> getAssessSortbyIndate(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getAssessSortbyIndate()");
			List<AssessmentMVO> assessList = new ArrayList<AssessmentMVO>();		
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ASSESSMENT_BY_INDATE);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
			
				while(rs.next()) {
					AssessmentMVO assess = new AssessmentMVO();
					assess.setAssessNum(rs.getString("ASSESS_ID"));
					assess.setMovieNum(rs.getInt("MOVIE_NUMBER"));
					assess.setPoster(rs.getString("MOVIE_POSTER"));										
					assess.setMovieTitle(rs.getString("MOVIE_TITLE"));
					assess.setKind(rs.getString("MOVIE_KIND"));
					assess.setGrade(rs.getString("MOVIE_GRADE"));
					assess.setPlayTime(rs.getInt("MOVIE_TIME"));
					assess.setRelease(rs.getString("MOVIE_DATE"));
					assess.setaContent(rs.getString("ASSESS_CONTENT"));
					assess.setAstars(rs.getInt("ASSESS_STARS"));
					assess.setStars(rs.getInt("STARS"));
					assess.setAssessIndate(rs.getDate("ASSESS_REGDATE"));
					assess.setLikes(rs.getInt("LIKES"));
					assess.setHates(rs.getInt("HATES"));					
					assessList.add(assess);
				}
			} catch(Exception e) {
				System.out.println("getAssessSortbyIndate()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return assessList;
			}
		
		public List<AssessmentMVO> getAssessSortbyStars(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getAssessSortbyStars()");
			List<AssessmentMVO> assessList = new ArrayList<AssessmentMVO>();		
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ASSESSMENT_BY_STARS);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
			
				while(rs.next()) {
					AssessmentMVO assess = new AssessmentMVO();
					assess.setAssessNum(rs.getString("ASSESS_ID"));
					assess.setMovieNum(rs.getInt("MOVIE_NUMBER"));
					assess.setPoster(rs.getString("MOVIE_POSTER"));										
					assess.setMovieTitle(rs.getString("MOVIE_TITLE"));
					assess.setKind(rs.getString("MOVIE_KIND"));
					assess.setGrade(rs.getString("MOVIE_GRADE"));
					assess.setPlayTime(rs.getInt("MOVIE_TIME"));
					assess.setRelease(rs.getString("MOVIE_DATE"));
					assess.setaContent(rs.getString("ASSESS_CONTENT"));
					assess.setAstars(rs.getInt("ASSESS_STARS"));
					assess.setStars(rs.getInt("STARS"));
					assess.setAssessIndate(rs.getDate("ASSESS_REGDATE"));
					assess.setLikes(rs.getInt("LIKES"));
					assess.setHates(rs.getInt("HATES"));					
					assessList.add(assess);
				}
			} catch(Exception e) {
				System.out.println("getAssessSortbyStars()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return assessList;
			}
		
		// 찜목록에서 제거
		public void deleteAssessment(String chkbox) {
			System.out.println("===> JDBC로 deleteAssessment()");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ASSESSMENT_DELETE);			
				stmt.setString(1, chkbox);
				stmt.executeUpdate();
			} catch(Exception e) {
				System.out.println("deleteAssessment() + e");
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
	
}
