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
	final private String SELECT_ASSESSMENT_VIEW = "SELECT * FROM ASSESSMENT_VIEW WHERE MOVIE_NUMBER = ?";

	// 전체 영화 목록
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
				assessmentVO.setMovieStars(rs.getFloat("movie_stars"));
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
}
