package com.kosmo.mintchoco.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kosmo.mintchoco.common.JDBCUtil;

/*
 * 담당자 : 천세문, 김정호
 */

public class MovieDAO {
	// 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// 쿼리
	final private String SELECT_MOVIE_LIST = "SELECT * FROM MOVIE";
	
	// 메소드
	public List<MovieVO> selectMovieList() {
		List<MovieVO> movieList = new ArrayList<MovieVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SELECT_MOVIE_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				MovieVO movieVO = new MovieVO();
				movieVO.setMovieNumber(rs.getInt("movie_number"));
				movieVO.setMoviePoster(rs.getString("movie_poster"));
				movieVO.setMovieTeaser(rs.getString("movie_teaser"));
				movieVO.setMovieTitle(rs.getString("movie_title"));
				movieVO.setMovieKind(rs.getString("movie_kind"));
				movieVO.setMovieDirector(rs.getString("movie_director"));
				movieVO.setMovieActor(rs.getString("movie_actor"));
				movieVO.setMovieGrade(rs.getString("movie_grade"));
				movieVO.setMovieTime(rs.getString("movie_time"));
				movieVO.setMovieDate(rs.getString("movie_date"));
				movieVO.setMovieYoutubeUrl(rs.getString("movie_youtube_url"));
				movieVO.setMovieNaverUrl(rs.getString("movie_naver_url"));
				movieVO.setMovieIndate(rs.getString("movie_indate"));
				movieVO.setMovieContent(rs.getString("movie_content"));
				movieList.add(movieVO);
			}
			
		} catch(Exception e) {
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return movieList;
	}
}
