package com.kosmo.mintchoco.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kosmo.mintchoco.common.JDBCUtil;
import com.kosmo.mintchoco.movie.MovieVO;

/*
 * 담당자 : 유지상, 장세진
 */

public class MemberDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String MEMBER_INSERT = "INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL,?,?,?,?,?,?,DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT);";
	private final String MEMBER_CHECKEMAIL = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_EMAIL = ?";
	private final String MEMBER_CHECKNICKNAME = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_NICKNAME = ?";
	private final String MEMBER_CHECKNAME = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_NAME = ?";
	
	private final String MEMBER_FINDCHECK = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_EMAIL = ? AND MEMBER_NAME = ?";
	private final String MEMBER_CHANGEPWD = "UPDATE MEMBER SET MEMBER_PWD = ? WHERE MEMBER_EMAIL = ? AND MEMBER_NAME = ?";
	private final String MEMBER_LOGINCHECK = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_EMAIL = ? AND MEMBER_PWD = ?";
	private final String MEMBER_INFO = "SELECT * FROM MEMBER WHERE MEMBER_EMAIL = ? AND MEMBER_PWD = ?";
	
	private final String MOVIE_INFO = "SELECT * FROM (SELECT * FROM MOVIE ORDER BY MOVIE_NUMBER DESC) WHERE ROWNUM <=12";
	
	public void joinMember(MemberVO vo) { //회원가입
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_INSERT);
			stmt.setString(1, vo.getEmail());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getNickName());
			stmt.setInt(5, vo.getAge());
			stmt.setString(6, vo.getGender());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

	public int checkEmail(String email) {
		
		int result = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_CHECKEMAIL);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		
		return result;
	}

	public int checkNickname(String nickname) {
		
		int result = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_CHECKNICKNAME);
			stmt.setString(1, nickname);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return result;
	}

	public int checkName(String name) {
		
		int result = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_CHECKNAME);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return result;
	}

	public int checkfind(String email, String name) {
		
		int result = 0;
		
try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_FINDCHECK);
			stmt.setString(1, email);
			stmt.setString(2, name);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return result;
	}

	public void changePwd(String email, String name, String securityPwd) {

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_CHANGEPWD);
			stmt.setString(1, securityPwd);
			stmt.setString(2, email);
			stmt.setString(3, name);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

	public int loginCheck(String email, String newPwd) {
		
int result = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_LOGINCHECK);
			stmt.setString(1, email);
			stmt.setString(2, newPwd);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return result;
		
	}

	public List<MemberVO> loginMember(String email, String newPwd) {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_INFO);	
			stmt.setString(1, email);
			stmt.setString(2, newPwd);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				
				vo.setNumber(rs.getInt("MEMBER_NUMBER"));
				vo.setEmail(rs.getString("MEMBER_EMAIL"));
				//비번생략
				vo.setName(rs.getString("MEMBER_NAME"));
				vo.setNickName(rs.getString("MEMBER_NICKNAME"));
				vo.setAge(rs.getInt("MEMBER_AGE"));
				vo.setGender(rs.getString("MEMBER_GENDER"));
				vo.setIntroduce(rs.getString("MEMBER_INTRODUCE"));
				vo.setIndate(rs.getDate("MEMBER_INDATE"));
				vo.setLike_flag(rs.getString("MEMBER_FAVORITE_FLAG"));
				vo.setAssessment_flag(rs.getString("MEMBER_ASSESSMENT_FLAG"));
				vo.setInfo_flag(rs.getString("MEMBER_INFO_FLAG"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return list;
	}

	public List<MovieVO> movieList() {
		
		List<MovieVO> list = new ArrayList<MovieVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MOVIE_INFO);			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				MovieVO vo = new MovieVO();
				
				vo.setMovieNumber(rs.getInt("MOVIE_NUMBER"));
				vo.setMoviePoster(rs.getString("MOVIE_POSTER"));
				vo.setMovieTeaser(rs.getString("MOVIE_TEASER"));
				vo.setMovieTitle(rs.getString("MOVIE_TITLE"));
				vo.setMovieKind(rs.getString("MOVIE_KIND"));
				vo.setMovieDirector(rs.getString("MOVIE_DIRECTOR"));
				vo.setMovieActor(rs.getString("MOVIE_ACTOR"));
				vo.setMovieGrade(rs.getString("MOVIE_GRADE"));
				vo.setMovieTime(rs.getString("MOVIE_TIME"));
				vo.setMovieDate(rs.getString("MOVIE_DATE"));
				vo.setMovieYoutubeUrl(rs.getString("MOVIE_YOUTUBE_URL"));
				vo.setMovieNaverUrl(rs.getString("MOVIE_NAVER_URL"));
				vo.setMovieIndate(rs.getString("MOVIE_INDATE"));
				vo.setMovieContent(rs.getString("MOVIE_CONTENT"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return list;
	}

}
