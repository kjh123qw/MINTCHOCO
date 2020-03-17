package com.kosmo.mintchoco.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import com.kosmo.mintchoco.common.JDBCUtil;
import com.kosmo.mintchoco.tag.TagVO;

/*
 * 담당자 : 천세문, 김정호
 */

public class MovieDAO {
	// 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// 쿼리
	// 입력
	final private String INSERT_MOVIE = "INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, ?)";
	
	// 선택
	final private String SELECT_MOVIE_LIST = "SELECT * FROM MOVIE";
	final private String SELECT_MOVIE_ONE = "SELECT * FROM MOVIE WHERE MOVIE_NUMBER = ?";

	// 수정
	final private String UPDATE_MOVIE = "UPDATE MOVIE SET MOVIE_TEASER = ?, MOVIE_TITLE = ?, MOVIE_KIND = ?, MOVIE_DIRECTOR = ?, MOVIE_ACTOR = ?, MOVIE_GRADE = ?, MOVIE_TIME = ?, MOVIE_DATE = ?, MOVIE_YOUTUBE_URL = ?, MOVIE_NAVER_URL = ?, MOVIE_CONTENT = ? WHERE MOVIE_NUMBER = ?";
	
	// 삭제
	final private String DELETE_MOVIE_NUM = "DELETE FROM MOVIE WHERE MOVIE_NUMBER = ?";
	
	// 영화 정보 입력
	public void insertMovie(MovieVO movieVO) {
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(INSERT_MOVIE);
			stmt.setString(1, movieVO.getMovieTeaser());
			stmt.setString(2, movieVO.getMovieTitle());
			stmt.setString(3, movieVO.getMovieKind());
			stmt.setString(4, movieVO.getMovieDirector());
			stmt.setString(5, movieVO.getMovieActor());
			stmt.setString(6, movieVO.getMovieGrade());
			stmt.setInt(7, Integer.parseInt(movieVO.getMovieTime()));
			stmt.setString(8, movieVO.getMovieDate());
			stmt.setString(9, movieVO.getMovieYoutubeUrl());
			stmt.setString(10, movieVO.getMovieNaverUrl());
			stmt.setString(11, movieVO.getMovieContent());
			
			stmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
	}
	
	// 전체 영화 목록
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
	
	// 영화 내용 상세
	public MovieVO selectOneMovie(String movieNumber) {
		
		MovieVO movieVO = new MovieVO();
		
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SELECT_MOVIE_ONE);
			stmt.setString(1, movieNumber);
			rs = stmt.executeQuery();
			if(rs.next()) {
				
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
				
			}
			
		} catch(Exception e) {
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return movieVO;
	}
	
	// 영화 내용 수정
	public void updateMovie(MovieVO movieVO) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_MOVIE);
			
			stmt.setString(1, movieVO.getMovieTeaser());
			stmt.setString(2, movieVO.getMovieTitle());
			stmt.setString(3, movieVO.getMovieKind());
			stmt.setString(4, movieVO.getMovieDirector());
			stmt.setString(5, movieVO.getMovieActor());
			stmt.setString(6, movieVO.getMovieGrade());
			stmt.setString(7, movieVO.getMovieTime());
			stmt.setString(8, movieVO.getMovieDate());
			stmt.setString(9, movieVO.getMovieYoutubeUrl());
			stmt.setString(10, movieVO.getMovieNaverUrl());
			stmt.setString(11, movieVO.getMovieContent());
			stmt.setInt(12, movieVO.getMovieNumber());
			stmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

	}
	
	// 기존 영화 삭제
	public void deleteMovie(String movieNumber) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DELETE_MOVIE_NUM);
			stmt.setString(1, movieNumber);
			stmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

	}

}
