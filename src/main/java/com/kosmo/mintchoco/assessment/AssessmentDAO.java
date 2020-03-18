package com.kosmo.mintchoco.assessment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kosmo.mintchoco.common.JDBCUtil;
import com.kosmo.mintchoco.search.SearchVO;
import com.kosmo.view.assessment.AssessmentControllerTemp;

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
	
	
	//게시물의 랭킹 1~3위 까지 조회(추천수가 기준 이상이면서)
	public List<AssessmentVO> getAssessRank(int movieNum, int count) 
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getAssessRank()");
		List<AssessmentVO> assessList = new ArrayList<AssessmentVO>();		
		try 
		{		
			final String sql = "select * from (select * from ASSESSMENT_VIEW where MOVIE_NUMBER = ? and LIKES >= ? order by LIKES desc, ASSESS_REGDATE asc) where rownum <= ?";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, movieNum);
			stmt.setInt(2, AssessmentControllerTemp.ASS_LIKE_CNT_STANDARD);
			stmt.setInt(3, count);
			rs = stmt.executeQuery();
		
			while(rs.next()) 
			{
				AssessmentVO assess = new AssessmentVO();
				assess.setAssessId(rs.getString("ASSESS_ID"));
				assess.setMovieNumber(rs.getInt("MOVIE_NUMBER"));
				assess.setAssessRegdate(rs.getDate("ASSESS_REGDATE"));
				assess.setLikes(rs.getInt("LIKES"));
				assess.setHates(rs.getInt("HATES"));					
				assessList.add(assess);
			}
		} 
		catch(Exception e) 
		{
			System.out.println("getAssessRank()" + e);
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return assessList;
	}
	
	//게시물의 해당 태그에 대한 갯수 조회
	public int getTagCount(int movieNum, String tag) 
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getTagCount()");
		int iCount = 0;
		
		try 
		{		
			final String sql = "select count(*) from ASSESSMENT where MOVIE_NUMBER = ? and upper(ASSESS_CONTENT) like upper('%?%')";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, movieNum);
			stmt.setString(2, tag);
			rs = stmt.executeQuery();
		
			if(rs.next()) 
				iCount = rs.getInt(1);
		} 
		catch(Exception e) 
		{
			System.out.println("getTagCount()" + e);
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return iCount;
	}
	
	
	public int insertTag(String tag)
	{
		return insertTag(tag, 0);
	}
	
	//태크 테이블에 태그 추가
	public int insertTag(String tag, int movieNum)
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 insertTag()");
		int iCount = 0;
		
		try 
		{		
			final String sql = "insert into TAG values(?, ?)";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			
			if(0 == movieNum)
				stmt.setNull(2, Types.INTEGER);
			else
				stmt.setInt(2, movieNum);
			
			iCount = stmt.executeUpdate();
		} 
		catch(Exception e) 
		{
			System.out.println("insertTag()" + e);
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return iCount;
	}
	
	//태그맵핑 추가
	public int insertTagMapping(String tag, int movieNum)
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 inertTagMapping()");
		int iCount = 0;
		
		try 
		{		
			final String sql = "insert into TAG_MAPPING values(?, ?)";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			stmt.setInt(2, movieNum);
			
			iCount = stmt.executeUpdate();
		} 
		catch(Exception e) 
		{
			System.out.println("inertTagMapping()" + e);
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return iCount;
	}
	
	//태크맵팅 삭제
	public void deleteTagMapping(String tag, int movieNumber)
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 deleteTagMapping()");
		
		try 
		{		
			final String sql = "delete from TAG_MAPPING where MOVIE_NUMBER = ? and upper(TAG_CONTENT) = upper('?')";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, movieNumber);
			stmt.setString(2, tag);
			
			stmt.executeUpdate();
		} 
		catch(Exception e) 
		{
			System.out.println("deleteTagMapping()" + e);
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
	}
	
	//태그테이블의  해당 태그의 cnt + 1
	public void updateTagUseCnt(String tag)
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 updateTagUseCnt()");
		
		try 
		{		
			final String sql = "update TAG set cnt = cnt + 1 where upper(TAG_CONTENT) = upper(?)";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			stmt.executeUpdate();
		} 
		catch(Exception e) 
		{
			System.out.println("updateTagUseCnt()" + e);
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
	}
}
