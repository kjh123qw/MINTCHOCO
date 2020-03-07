package com.kosmo.mintchoco.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kosmo.mintchoco.common.JDBCUtil;
import com.kosmo.mintchoco.movie.MovieVO;

/*
 * 담당자 : 천세문, 김정호
 */

public class SearchDAO {
	// 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// 검색용 쿼리(기본)
	private String SEARCH_MOVIE_LIST = "SELECT * FROM MOVIE WHERE";
	
	
	// 전체 리스트 부르는 메소드
	public List<MovieVO> searchMovieList(String searchKeyWord) {
		List<MovieVO> movieList = new ArrayList<MovieVO>();
		String[] searchKeyWordArr = searchKeyWord.split(" ");
		char[] searchTitleArr = searchKeyWord.toCharArray();
		String movieTitle = "";
		String movieKind = "";
		String movieDirector = ""; 
		String movieActor = "";
		String movieContent = "";
		String orExpr = "";
		boolean firstTitleCond = true;
		if(searchKeyWordArr.length == 0) {
			return null;
		} else {
			for(char titleLetter : searchTitleArr) {
				if(titleLetter != ' ') {
					System.out.println("titleLetter ==>" + titleLetter);
					if(firstTitleCond) {
						movieTitle = " MOVIE_TITLE LIKE '" + titleLetter + "%'";
						firstTitleCond = false;
					} else {
						movieTitle += " AND MOVIE_TITLE LIKE '%" + titleLetter + "%'";
					}
				}
			}
			for(String keyWord : searchKeyWordArr) {
				movieKind += orExpr + " MOVIE_KIND LIKE '%" + keyWord + "%'";
				movieDirector += orExpr + " MOVIE_DIRECTOR LIKE '%" + keyWord + "%'";
				movieActor += orExpr + " MOVIE_ACTOR LIKE '%" + keyWord + "%'";
				movieContent += orExpr + " MOVIE_CONTENT LIKE '%" + keyWord + "%'";
				orExpr = " OR";
			}
		}
		SEARCH_MOVIE_LIST += movieTitle + " OR" + movieKind + " OR" + movieDirector + " OR" + movieActor + " OR" + movieContent;

		System.out.println(":::::::::: SEARCH_MOVIE_LIST : " + SEARCH_MOVIE_LIST);
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SEARCH_MOVIE_LIST);
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
			System.out.println("Error - searchMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return movieList;
	}
}
