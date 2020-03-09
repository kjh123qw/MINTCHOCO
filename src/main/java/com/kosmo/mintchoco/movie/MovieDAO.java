package com.kosmo.mintchoco.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

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
	final private String SELECT_MOVIE_ONE = "SELECT * FROM MOVIE WHERE MOVIE_NUMBER = ?";
	
	// 영화 번호, 포스터 주소, 	티저 링크, 영화 제목, 영화 분류, 영화 감독, 영화 배우, 상영 등급, 상영 시간, 개봉 일자, 유튜브 링크, 네이버 링크, 	게시일, 	줄거리
	final private String INSERT_MOVIE = "INSERT INTO MOVIE VALUES(MOVIE_SEQ.NEXTVAL, concat(concat('mov_poster_', MOVIE_SEQ.CURRVAL), '.jpg'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, ?)";
	final private String DELETE_MOVIE_NUM = "DELETE FROM MOVIE WHERE MOVIE_NUMBER = ?";
	final private String DELETE_MOVIE_TITLE = "DELETE FROM MOVIE WHERE MOVIE_TITLE = ?";
	final private String DELETE_MOVIE_DIRECTOR = "DELETE FROM MOVIE WHERE MOVIE_DIRECTOR = ?";
	final private String DELETE_MOVIE_ACTOR = "DELETE FROM MOVIE WHERE MOVIE_ACTOR = ?";
	
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
			stmt.setString(7, movieVO.getMovieTime());
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
	public MovieVO selectOneMovie(String movieNuber) {
		
		MovieVO movieVO = new MovieVO();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SELECT_MOVIE_ONE);
			stmt.setString(1, movieNuber);
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
	
	// 기존 영화 삭제
	// 영화 번호
	public void deleteMovie1(String movieNumber) {
		
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
	
	// 영화 제목
	public void deleteMovie2(String movieTitle) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DELETE_MOVIE_TITLE);
			stmt.setString(1, movieTitle);
			stmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

	}
	
	// 감독 이름
	public void deleteMovie3(String movieDirector) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DELETE_MOVIE_DIRECTOR);
			stmt.setString(1, movieDirector);
			stmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

	}
	
	// 주연 배우
	public void deleteMovie4(String movieActor) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DELETE_MOVIE_ACTOR);
			stmt.setString(1, movieActor);
			stmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

	}

}
