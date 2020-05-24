package com.kosmo.mintchoco.assessment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
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
					assess.setStars(rs.getFloat("STARS"));
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
					assess.setStars(rs.getFloat("STARS"));
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
					assess.setStars(rs.getFloat("STARS"));
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
					assess.setStars(rs.getFloat("STARS"));
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
		
		// 평가목록에서 제거
		public void deleteAssessment(List<String> chkbox) {
			String qr="";
			for (int i=0; i<chkbox.size(); i++) {
				qr += "?,";
			}
			qr = qr.substring(0, qr.length()-1);
			
			String ASSESSMENT_DELETE = "DELETE FROM ASSESSMENT WHERE ASSESS_ID in (" + qr + ")";
			
			System.out.println("===> JDBC로 deleteAssessment()");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ASSESSMENT_DELETE);			
				for (int i=0; i<chkbox.size(); i++) {
					stmt.setString(i+1, chkbox.get(i));
				}
				stmt.executeUpdate();
			} catch(Exception e) {
				System.out.println("deleteAssessment() + e");
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
		
	//게시물의 랭킹 1~3위 까지 조회(추천수가 기준 이상이면서)
	public List<AssessmentMVO> getAssessRank(int movieNum, int count) 
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getAssessRank()");
		List<AssessmentMVO> assessList = new ArrayList<AssessmentMVO>();		
		try 
		{		
			final String sql = "select * from (select * from ASSESSMENT_VIEW where MOVIE_NUMBER = ? and LIKES >= ? order by LIKES desc, ASSESS_REGDATE asc) where rownum <= ?";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, movieNum);
			stmt.setInt(2, 0);
			stmt.setInt(3, count);
			rs = stmt.executeQuery();
		
			while(rs.next()) 
			{
				AssessmentMVO assess = new AssessmentMVO();
				assess.setAssessNum(rs.getString("ASSESS_ID"));
				assess.setMovieNum(rs.getInt("MOVIE_NUMBER"));
				assess.setAssessIndate(rs.getDate("ASSESS_REGDATE"));
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
