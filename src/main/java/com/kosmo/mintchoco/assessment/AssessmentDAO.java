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
	final private String INSERT_ASSESSMENT = "INSERT INTO ASSESSMENT VALUES(?, ?, ?, ?, ?, default)";
	final private String DELETE_ASSESSMENT = "DELETE FROM ASSESSMENT WHERE ASSESS_ID = ?";
	
		// by 장세진
	private final String ASSESSMENT_BY_TITLE = "SELECT * FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=? ORDER BY MOVIE_TITLE ASC";
	private final String ASSESSMENT_BY_INDATE = "SELECT * FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=? ORDER BY ASSESS_ID ASC";
	private final String ASSESSMENT_BY_STARS = "SELECT * FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=? ORDER BY ASSESS_STARS DESC, STARS DESC";
	private final String SELECT_ASSESSMENT_VIEW_BY_MEMBER = "SELECT * FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=?";
	
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
		String sql = "INSERT INTO ASSESSMENT_EST VALUES(ASSESS_EST_SEQ.NEXTVAL, ?, ?, ?, default)";
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, Integer.toString(memberNumber) + assessId);
//			stmt.setInt(2, memberNumber);
//			stmt.setString(3, assessId);
//			stmt.setString(4, assessEst);
			stmt.setInt(1, memberNumber);
			stmt.setString(2, assessId);
			stmt.setString(3, assessEst);
			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error - updateAssessmentLike()\n");
			returnValue = 0;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return returnValue;
	}
	
	public boolean insertAssessment(int memberNumber, int movieNumber, String assessContent, int assessStars) {
		boolean bResult = false;
		String sql = "INSERT INTO ASSESSMENT VALUES(ASSESS_SEQ.NEXTVAL, ?, ?, ?, ?, default)";
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql); 	// INSERT_ASSESSMENT
//			stmt.setString(1, Integer.toString(memberNumber) + Integer.toString(movieNumber));
//			stmt.setInt(2, memberNumber);
//			stmt.setInt(3, movieNumber);
//			stmt.setString(4, assessContent);
//			stmt.setInt(5, assessStars);
			stmt.setInt(1, memberNumber);
			stmt.setInt(2, movieNumber);
			stmt.setString(3, assessContent);
			stmt.setInt(4, assessStars);
			int count = stmt.executeUpdate();
			if(0 < count)
				bResult = true;
			
		} catch(Exception e) {
			System.out.println("Error - insertAssessment()\n");
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
		return bResult;
	}
	
	public boolean deleteAssessment(String assessId) {
		boolean bResult = false;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DELETE_ASSESSMENT);
			stmt.setString(1, assessId);
			
			int count = stmt.executeUpdate();
			if(0 < count)
				bResult = true;
			
		} catch(Exception e) {
			System.out.println("Error - deleteAssessment()\n");
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
		return bResult;
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
				assessList.add(assessmentVO);
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
		tag = "#" + tag;
		int iCount = 0;
		String sql = "select count(*) from ASSESSMENT where MOVIE_NUMBER = ? and upper(ASSESS_CONTENT) like upper('%" + tag + "%')";
		
		try 
		{		
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, movieNum);
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
	
	public boolean insertTag(String tag)
	{
		return insertTag(tag, 0);
	}
	
	//태크 테이블에 태그 추가
	public boolean insertTag(String tag, int movieNum)
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 insertTag()");
		boolean bResult = false;
		
		try 
		{		
			final String sql = "insert into TAG(tag_content, movie_number) values(?, ?)";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			
			if(0 == movieNum)
				stmt.setNull(2, Types.INTEGER);
			else
				stmt.setInt(2, movieNum);
			
			int count = stmt.executeUpdate();
			if(0 < count)
				bResult = true;
		} 
		catch(Exception e) 
		{
			System.out.println("insertTag()" + e);
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return bResult;
	}
	
	//태그맵핑 추가
	public boolean insertTagMapping(String tag, int movieNum)
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 inertTagMapping()");
		boolean bResult = false;
		
		try 
		{		
			final String sql = "insert into TAG_MAPPING values(?, ?)";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			stmt.setInt(2, movieNum);
			
			int count = stmt.executeUpdate();
			if(0 < count)
				bResult = true;
		} 
		catch(Exception e) 
		{
			System.out.println("inertTagMapping()" + e);
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return bResult;
	}
	
	//태크맵팅 삭제
	public boolean deleteTagMapping(String tag, int movieNumber)
	{
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 deleteTagMapping()");
		boolean bResult = false;
		
		try 
		{		
			String sql = "delete from TAG_MAPPING where MOVIE_NUMBER = ? and upper(TAG_CONTENT) = upper('" + tag + "')";
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, movieNumber);
			
			int count = stmt.executeUpdate();
			if(0 < count)
				bResult = true;
		} 
		catch(Exception e) 
		{
			System.out.println("deleteTagMapping()" + e);
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return bResult;
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
	
	
	// 이하 by 장세진
	// 평가목록 가져오기(멤버 번호로)
	public List<AssessmentVO> getAssessmentList(String memberNum) {
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getAssessmentList()");
		List<AssessmentVO> asseList = new ArrayList<AssessmentVO>();		
		try {		
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SELECT_ASSESSMENT_VIEW_BY_MEMBER);
			stmt.setString(1, memberNum);
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
			System.out.println("getAssessmentList()" +e);
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
			return asseList;
		}
	
	// 제목 정렬
	public List<AssessmentVO> getAssessSortbyTitle(String memberNum) {
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getAssessSortbyTitle()");
		List<AssessmentVO> asseList = new ArrayList<AssessmentVO>();		
		try {		
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ASSESSMENT_BY_TITLE);
			stmt.setString(1, memberNum);
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
			System.out.println("getAssessSortbyTitle()" +e);
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
			return asseList;
		}
	
	// 등록 순 정렬
	public List<AssessmentVO> getAssessSortbyIndate(String memberNum) {
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getAssessSortbyIndate()");
		List<AssessmentVO> asseList = new ArrayList<AssessmentVO>();		
		try {		
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ASSESSMENT_BY_INDATE);
			stmt.setString(1, memberNum);
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
			System.out.println("getAssessSortbyIndate()" +e);
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
			return asseList;
		}
	
	// 평점순 정렬
	public List<AssessmentVO> getAssessSortbyStars(String memberNum) {
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getAssessSortbyStars()");
		List<AssessmentVO> asseList = new ArrayList<AssessmentVO>();		
		try {		
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ASSESSMENT_BY_STARS);
			stmt.setString(1, memberNum);
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
			System.out.println("getAssessSortbyStars()" +e);
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
			return asseList;
		}
	
		// 평가목록에서 제거(선택된 체크박스로)
		public void deleteAssessmentByChkbox(List<String> chkbox) {
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
}
