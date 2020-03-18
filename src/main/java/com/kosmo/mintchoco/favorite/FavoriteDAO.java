package com.kosmo.mintchoco.favorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kosmo.mintchoco.common.JDBCUtil;
import com.kosmo.mintchoco.member.MemberVO;
import com.kosmo.mintchoco.movie.MovieVO;

/*
 * 담당자 : 장세진
 */

public class FavoriteDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// 필요SQL
	private final String FAVORITE_GET_LIST = "SELECT * FROM FAVORITE_VIEW WHERE MEMBER_NUMBER=?";
	private final String FAV_BY_TITLE = "SELECT * FROM FAVORITE_VIEW WHERE MEMBER_NUMBER=? ORDER BY MOVIE_TITLE";
	private final String FAV_BY_RELEASE = "SELECT * FROM FAVORITE_VIEW WHERE MEMBER_NUMBER=? ORDER BY MOVIE_TIME DESC";
	private final String FAV_BY_INDATE = "SELECT * FROM FAVORITE_VIEW WHERE MEMBER_NUMBER=? ORDER BY FAVORITE_ID ASC";
	private final String FAVORITE_INSERT = "INSERT INTO FAVORITE VALUES(?, ?, ?, DEFAULT)";
	private final String FAVORITE_DELETE = "DELETE FAVORITE WHERE MEMBER_NUMBER = ? and MOVIE_NUMBER = ?";
	private final String LATEST_FAV = "SELECT * FROM FAVORITE_VIEW WHERE MEMBER_NUMBER=? ORDER BY FAVORITE_REGDATE DESC LIMIT 5";
	private final String FAV_COUNT = "SELECT COUNT(*) FROM FAVORITE_VIEW WHERE MEMBER_NUMBER=?";
	
	// 찜목록 가져오기
	public List<FavoriteVO> getFavoriteList(String memberNum) {
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getFavoriteList()");
		List<FavoriteVO> favoriteList = new ArrayList<FavoriteVO>();		
		try {		
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(FAVORITE_GET_LIST);
			stmt.setString(1, memberNum);
			rs = stmt.executeQuery();
		
			while(rs.next()) {
				FavoriteVO favorite = new FavoriteVO();
				favorite.setFavNum(rs.getString("FAVORITE_ID"));
				favorite.setMovieNum(rs.getInt("MOVIE_NUMBER"));
				favorite.setFavIndate(rs.getDate("FAVORITE_REGDATE"));
				favorite.setPoster(rs.getString("MOVIE_POSTER"));
				favorite.setMovieTitle(rs.getString("MOVIE_TITLE"));
				favorite.setKind(rs.getString("MOVIE_KIND"));
				favorite.setGrade(rs.getString("MOVIE_GRADE"));
				favorite.setPlaytime(rs.getInt("MOVIE_TIME"));
				favoriteList.add(favorite);
			}
		} catch(Exception e) {
			System.out.println("getFavoriteList()" +e);
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
			return favoriteList;
	}
	
	// 찜목록 영화 제목으로 정렬
		public List<FavoriteVO> getFavSortbyTitle(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getFavSortbyTitle()");
			List<FavoriteVO> favoriteList = new ArrayList<FavoriteVO>();		
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(FAV_BY_TITLE);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
			
				while(rs.next()) {
					FavoriteVO favorite = new FavoriteVO();
					favorite.setFavNum(rs.getString("FAVORITE_ID"));
					favorite.setMovieNum(rs.getInt("MOVIE_NUMBER"));
					favorite.setFavIndate(rs.getDate("FAVORITE_REGDATE"));
					favorite.setPoster(rs.getString("MOVIE_POSTER"));
					favorite.setMovieTitle(rs.getString("MOVIE_TITLE"));
					favorite.setKind(rs.getString("MOVIE_KIND"));
					favorite.setGrade(rs.getString("MOVIE_GRADE"));
					favorite.setPlaytime(rs.getInt("MOVIE_TIME"));
					favoriteList.add(favorite);
				}
			} catch(Exception e) {
				System.out.println("getFavSortbyTitle()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return favoriteList;
		}
		
		// 찜목록 영화 개봉일로 정렬
		public List<FavoriteVO> getFavSortbyRelease(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getFavSortbyRelease()");
			List<FavoriteVO> favoriteList = new ArrayList<FavoriteVO>();		
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(FAV_BY_RELEASE);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
			
				while(rs.next()) {
					FavoriteVO favorite = new FavoriteVO();
					favorite.setFavNum(rs.getString("FAVORITE_ID"));
					favorite.setMovieNum(rs.getInt("MOVIE_NUMBER"));
					favorite.setFavIndate(rs.getDate("FAVORITE_REGDATE"));
					favorite.setPoster(rs.getString("MOVIE_POSTER"));
					favorite.setMovieTitle(rs.getString("MOVIE_TITLE"));
					favorite.setKind(rs.getString("MOVIE_KIND"));
					favorite.setGrade(rs.getString("MOVIE_GRADE"));
					favorite.setPlaytime(rs.getInt("MOVIE_TIME"));
					favoriteList.add(favorite);
				}
			} catch(Exception e) {
				System.out.println("getFavSortbyRelease()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return favoriteList;
		}
		
		// 찜목록 등록순으로 정렬
		public List<FavoriteVO> getFavSortbyIndate(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getFavSortbyIndate()");
			List<FavoriteVO> favoriteList = new ArrayList<FavoriteVO>();		
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(FAV_BY_INDATE);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
			
				while(rs.next()) {
					FavoriteVO favorite = new FavoriteVO();
					favorite.setFavNum(rs.getString("FAVORITE_ID"));
					favorite.setMovieNum(rs.getInt("MOVIE_NUMBER"));
					favorite.setFavIndate(rs.getDate("FAVORITE_REGDATE"));
					favorite.setPoster(rs.getString("MOVIE_POSTER"));
					favorite.setMovieTitle(rs.getString("MOVIE_TITLE"));
					favorite.setKind(rs.getString("MOVIE_KIND"));
					favorite.setGrade(rs.getString("MOVIE_GRADE"));
					favorite.setPlaytime(rs.getInt("MOVIE_TIME"));
					favoriteList.add(favorite);
				}
			} catch(Exception e) {
				System.out.println("getFavSortbyIndate()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return favoriteList;
		}
	
	// 마이페이지 메인에서 최근 5개
	public List<FavoriteVO> getFavInfo(String memberNum) {
		// TODO Auto-generated method stub
		System.out.println("===> JDBC로 getFavInfo()");
		List<FavoriteVO> favoriteList = new ArrayList<FavoriteVO>();		
		try {		
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(LATEST_FAV);
			stmt.setString(1, memberNum);
			rs = stmt.executeQuery();
		
			while(rs.next()) {
				FavoriteVO favorite = new FavoriteVO();
				favorite.setFavNum(rs.getString("FAVORITE_ID"));
				favorite.setMovieNum(rs.getInt("MOVIE_NUMBER"));
				favorite.setFavIndate(rs.getDate("FAVORITE_REGDATE"));
				favorite.setPoster(rs.getString("MOVIE_POSTER"));
				favorite.setMovieTitle(rs.getString("MOVIE_TITLE"));
				favorite.setKind(rs.getString("MOVIE_KIND"));
				favorite.setGrade(rs.getString("MOVIE_GRADE"));
				favorite.setPlaytime(rs.getInt("MOVIE_TIME"));
				favoriteList.add(favorite);
			}
		} catch(Exception e) {
			System.out.println("getFavInfo()" +e);
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
			return favoriteList;
	}
	
	// 찜목록에 추가
	public void insertFavorite(int memberNumber, String movieNumber) {

		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(FAVORITE_INSERT);		
			stmt.setString(1, memberNumber + movieNumber);
			stmt.setInt(2, memberNumber);
			stmt.setString(3, movieNumber);
			stmt.executeUpdate();		
			
		} catch(Exception e) {
		
			e.printStackTrace();
			
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 찜 목록 취소
	public void deleteFavorite(int memberNumber, String movieNumber) {			
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(FAVORITE_DELETE);
			stmt.setInt(1, memberNumber);
			stmt.setInt(2, Integer.parseInt(movieNumber));
			stmt.executeUpdate();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 몇 건 찜했는지
		public int getFavoriteCnt(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getFavoriteCnt()");
			int assess_cnt = 0;
			
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(FAV_COUNT);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
				if(rs.next()) {
					assess_cnt = rs.getInt(1);
				}
			} catch(Exception e) {
				System.out.println("getFavoriteCnt()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return assess_cnt;
		}

}
