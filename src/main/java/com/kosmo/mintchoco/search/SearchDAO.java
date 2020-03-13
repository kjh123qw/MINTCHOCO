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
	// 쿼리
	final private String SELECT_MOVIE_LIST = "SELECT * FROM SEARCH_VIEW";

	// 전체 영화 목록
	public List<SearchVO> selectMovieList() {
		List<SearchVO> movieList = new ArrayList<SearchVO>();
		int movieIndex = 0;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SELECT_MOVIE_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SearchVO searchVO = new SearchVO();
				searchVO.setMovieNumber(rs.getInt("movie_number"));
				searchVO.setMoviePoster(rs.getString("movie_poster"));
				searchVO.setMovieTitle(rs.getString("movie_title"));
				searchVO.setMovieStars(rs.getFloat("stars"));
				searchVO.setMovieKind(rs.getString("movie_kind"));
				searchVO.setMovieDirector(rs.getString("movie_director"));
				searchVO.setMovieActor(rs.getString("movie_actor"));
				searchVO.setMovieGrade(rs.getString("movie_grade"));
				searchVO.setMovieTime(rs.getString("movie_time"));
				searchVO.setMovieDate(rs.getString("movie_date"));
				searchVO.setMovieIndex(movieIndex++);
				movieList.add(searchVO);
			}
			
		} catch(Exception e) {
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return movieList;
	}
	// 검색 메소드
	public List<SearchVO> searchMovieList(String searchKeyWord) {
		// 검색용 쿼리(기본)
		String SEARCH_MOVIE_MAIN_LIST = "SELECT * FROM SEARCH_VIEW WHERE";
		String SEARCH_MOVIE_SECOND_LIST = "SELECT * FROM SEARCH_VIEW WHERE";
		String SEARCH_MOVIE_THIRD_LIST = "SELECT * FROM SEARCH_VIEW WHERE";
		
		List<SearchVO> searchMovieList = new ArrayList<SearchVO>();
		int movieIndex = 0;
		String[] searchKeyWordArr = searchKeyWord.split(" ");
		char[] searchTitleArr = searchKeyWord.toCharArray();
		String movieTitle = "";
		String movieTitle2 = "";
		String movieKind = "";
		String movieDirector = "";
		String movieActor = "";
		String movieContent = "";
		String orExpr = "";
		boolean firstTitleCond = true;

		for(char titleLetter : searchTitleArr) {
			if(titleLetter != ' ') {
				if('0' <= titleLetter && titleLetter <= '9') {
					movieTitle += (firstTitleCond?" (MOVIE_TITLE LIKE '":" AND MOVIE_TITLE LIKE '%") + titleLetter + "%'";
				} else {
					movieTitle += (firstTitleCond?" (MOVIE_TITLE LIKE '":" AND MOVIE_TITLE LIKE '%") + titleLetter + "%'";
					movieTitle2 += (firstTitleCond?" (MOVIE_TITLE LIKE '%":" AND MOVIE_TITLE LIKE '%") + titleLetter + "%'";
				}
				if(firstTitleCond)
					firstTitleCond = false;
			}
		}
		movieTitle += ")";
		movieTitle2 += ")";
		for(String keyWord : searchKeyWordArr) {
			movieKind += orExpr + " MOVIE_KIND LIKE '%" + keyWord + "%'";
			movieDirector += orExpr + " MOVIE_DIRECTOR LIKE '%" + keyWord + "%'";
			movieActor += orExpr + " MOVIE_ACTOR LIKE '%" + keyWord + "%'";
			movieContent += orExpr + " MOVIE_CONTENT LIKE '%" + keyWord + "%'";
			orExpr = " OR";
		}

		SEARCH_MOVIE_MAIN_LIST += movieTitle;
		SEARCH_MOVIE_SECOND_LIST += " NOT" + movieTitle + " AND " + movieTitle2;
		SEARCH_MOVIE_THIRD_LIST += " NOT (" + movieTitle + " OR " + movieTitle2 + ") AND (" + movieKind + " OR" + movieDirector + " OR" + movieActor + " OR" + movieContent + ")";
//		System.out.println(":::::::::: SEARCH_MOVIE_MAIN_LIST : " + SEARCH_MOVIE_MAIN_LIST);
//		System.out.println(":::::::::: SEARCH_MOVIE_SECOND_LIST : " + SEARCH_MOVIE_SECOND_LIST);
//		System.out.println(":::::::::: SEARCH_MOVIE_THIRD_LIST : " + SEARCH_MOVIE_THIRD_LIST);
		try {
			conn = JDBCUtil.getConnection();
			// 첫 번째 검색
			stmt = conn.prepareStatement(SEARCH_MOVIE_MAIN_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SearchVO searchVO = new SearchVO();
				searchVO.setMovieNumber(rs.getInt("movie_number"));
				searchVO.setMoviePoster(rs.getString("movie_poster"));
				searchVO.setMovieTitle(rs.getString("movie_title"));
				searchVO.setMovieStars(rs.getFloat("stars"));
				searchVO.setMovieKind(rs.getString("movie_kind"));
				searchVO.setMovieDirector(rs.getString("movie_director"));
				searchVO.setMovieActor(rs.getString("movie_actor"));
				searchVO.setMovieGrade(rs.getString("movie_grade"));
				searchVO.setMovieTime(rs.getString("movie_time"));
				searchVO.setMovieDate(rs.getString("movie_date"));
				searchVO.setMovieIndex(movieIndex++);
				searchMovieList.add(searchVO);
			}
			// 두 번째 검색
			stmt = conn.prepareStatement(SEARCH_MOVIE_SECOND_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SearchVO searchVO = new SearchVO();
				searchVO.setMovieNumber(rs.getInt("movie_number"));
				searchVO.setMoviePoster(rs.getString("movie_poster"));
				searchVO.setMovieTitle(rs.getString("movie_title"));
				searchVO.setMovieStars(rs.getFloat("stars"));
				searchVO.setMovieKind(rs.getString("movie_kind"));
				searchVO.setMovieDirector(rs.getString("movie_director"));
				searchVO.setMovieActor(rs.getString("movie_actor"));
				searchVO.setMovieGrade(rs.getString("movie_grade"));
				searchVO.setMovieTime(rs.getString("movie_time"));
				searchVO.setMovieDate(rs.getString("movie_date"));
				searchVO.setMovieIndex(movieIndex++);
				searchMovieList.add(searchVO);
			}
			// 세 번째 검색
			stmt = conn.prepareStatement(SEARCH_MOVIE_THIRD_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SearchVO searchVO = new SearchVO();
				searchVO.setMovieNumber(rs.getInt("movie_number"));
				searchVO.setMoviePoster(rs.getString("movie_poster"));
				searchVO.setMovieTitle(rs.getString("movie_title"));
				searchVO.setMovieStars(rs.getFloat("stars"));
				searchVO.setMovieKind(rs.getString("movie_kind"));
				searchVO.setMovieDirector(rs.getString("movie_director"));
				searchVO.setMovieActor(rs.getString("movie_actor"));
				searchVO.setMovieGrade(rs.getString("movie_grade"));
				searchVO.setMovieTime(rs.getString("movie_time"));
				searchVO.setMovieDate(rs.getString("movie_date"));
				searchVO.setMovieIndex(movieIndex++);
				searchMovieList.add(searchVO);
			}
			
		} catch(Exception e) {
			System.out.println("Error - searchMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return searchMovieList;
	}
}
