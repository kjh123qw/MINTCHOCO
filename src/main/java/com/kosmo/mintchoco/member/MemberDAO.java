package com.kosmo.mintchoco.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kosmo.mintchoco.common.JDBCUtil;
import com.kosmo.mintchoco.common.SecurityUtil;
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
	
	// 장세진 SQL
	private final String MEMBER_BY_NICKNAME = "SELECT * FROM MEMBER WHERE MEMBER_NICKNAME=?";
	private final String MEMBER_CHECKNN = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_NICKNAME=?";
	private final String GET_DETAIL = "SELECT * FROM MEMBER WHERE MEMBER_NUMBER=?";
	private final String ASSESSMENT_COUNT = "SELECT COUNT(*) FROM ASSESSMENT_VIEW WHERE MEMBER_NUMBER=?";
	private final String UPDATE_MEMBER = "UPDATE MEMBER SET MEMBER_NICKNAME=?, MEMBER_AGE=?, MEMBER_GENDER=?, MEMBER_INTRODUCE=? WHERE MEMBER_NUMBER=?";
	private final String UPDATE_MEMBERPW = "UPDATE MEMBER SET MEMBER_PWD=? WHERE MEMBER_NUMBER=?";
	private final String UPDATE_MEMBEROPE = "UPDATE MEMBER SET MEMBER_FAVORITE_FLAG=?, MEMBER_ASSESSMENT_FLAG=?, MEMBER_INFO_FLAG=? WHERE MEMBER_NUMBER=?";
	private final String MEMBER_INFO_BYNUM = "SELECT * FROM MEMBER WHERE MEMBER_NUMBER=?";
	private final String MEMBER_DELETE = "DELETE FROM MEMBER WHERE MEMBER_NUMBER=?";
	private final String MEMBER_CHECKPW = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_PWD=? AND MEMBER_NUMBER=?";
	
	
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

	public MemberVO loginMember(String email, String newPwd) {
		

		MemberVO memberVO = new MemberVO();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_INFO);	
			stmt.setString(1, email);
			stmt.setString(2, newPwd);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				memberVO.setNumber(rs.getInt("MEMBER_NUMBER"));
				//비번생략
				memberVO.setName(rs.getString("MEMBER_NAME"));
				memberVO.setNickName(rs.getString("MEMBER_NICKNAME"));
				memberVO.setAge(rs.getInt("MEMBER_AGE"));
				memberVO.setGender(rs.getString("MEMBER_GENDER"));
				memberVO.setIntroduce(rs.getString("MEMBER_INTRODUCE"));
				memberVO.setIndate(rs.getDate("MEMBER_INDATE"));
				memberVO.setLike_flag(rs.getString("MEMBER_FAVORITE_FLAG"));
				memberVO.setAssessment_flag(rs.getString("MEMBER_ASSESSMENT_FLAG"));
				memberVO.setInfo_flag(rs.getString("MEMBER_INFO_FLAG"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return memberVO;
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
	
		// 이하 by 장세진
		SecurityUtil security = new SecurityUtil();
		// 회원 번호 받기
		public String getMemberNumber(String nickname) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getMemberNumber()");
			int num = 0;
			try {					
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(MEMBER_BY_NICKNAME);
				stmt.setString(1, nickname);
				rs = stmt.executeQuery();
				if(rs.next()) {
					num = rs.getInt("MEMBER_NUMBER");
				}
			} catch(Exception e) {
				System.out.println("getMemberNumber()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				String number = Integer.toString(num);
				return number;
		}
		
		// 닉네임 확인
		public int checkUsingNickname(String nickName) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 checkNickname()");		
			int result = 0;

			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(MEMBER_CHECKNN);
				stmt.setString(1, nickName);
				rs = stmt.executeQuery();			
				while(rs.next()) {
					result = rs.getInt("COUNT(*)");
				}			
			} catch (Exception e) {
				System.out.println("checkNickname()" + e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}		
			return result;
		}	
		
		// 계정 정보
		public MemberVO getDetailInfo(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getDetailInfo()");
			MemberVO member = new MemberVO();
			
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(GET_DETAIL);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
				if(rs.next()) {
					member.setNumber(rs.getInt("MEMBER_NUMBER"));
					member.setEmail(rs.getString("MEMBER_EMAIL"));
					member.setName(rs.getString("MEMBER_NAME"));
					member.setNickName(rs.getString("MEMBER_NICKNAME"));
					member.setAge(rs.getInt("MEMBER_AGE"));
					member.setGender(rs.getString("MEMBER_GENDER"));
					member.setIntroduce(rs.getString("MEMBER_INTRODUCE"));
					member.setIndate(rs.getDate("MEMBER_INDATE"));
					member.setLike_flag(rs.getString("MEMBER_FAVORITE_FLAG"));
					member.setAssessment_flag(rs.getString("MEMBER_ASSESSMENT_FLAG"));
					member.setInfo_flag(rs.getString("MEMBER_INFO_FLAG"));
				}
			} catch(Exception e) {
				System.out.println("getDetailInfo()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return member;
		}
		
		// 계정정보(세션에 저장)
		public List<MemberVO> loginMemberbyN(String memberNum) {
			
			List<MemberVO> list = new ArrayList<MemberVO>();
			
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(MEMBER_INFO_BYNUM);	
				stmt.setString(1, memberNum);
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
		
		// 몇 건 평가했는지
		public int getAssessmentCnt(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 getAssessmentCnt()");
			int assess_cnt = 0;
			
			try {		
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ASSESSMENT_COUNT);
				stmt.setString(1, memberNum);
				rs = stmt.executeQuery();
				if(rs.next()) {
					assess_cnt = rs.getInt(1);
				}
			} catch(Exception e) {
				System.out.println("getAssessmentCnt()" +e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
				return assess_cnt;
		}
		
		// 개인정보 바꾸기
		public void updateMember(String memberNum, String nickName, String gender, String age, String introduce) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 updateMember()");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(UPDATE_MEMBER);			
				stmt.setString(1, nickName);
				stmt.setString(2, age);
				stmt.setString(3, gender);
				stmt.setString(4, introduce);
				stmt.setString(5, memberNum);
				stmt.executeUpdate();
			} catch(Exception e) {
				System.out.println("updateMember()" + e);
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}

		// 비번 바꾸기
		public void updateMemberPW(String memberNum, String password) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 updateMemberPW()");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(UPDATE_MEMBERPW);			
				stmt.setString(1, security.encryptSHA256(password));
				stmt.setString(2, memberNum);
				stmt.executeUpdate();
			} catch(Exception e) {
				System.out.println("updateMemberPW()" + e);
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}

		// 공개 여부 설정
		public void updateMemberOPE(String memberNum, String assessFlag, String favFlag, String infoFlag) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 updateMemberOPE()");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(UPDATE_MEMBEROPE);	
				stmt.setString(1, favFlag);
				stmt.setString(2, assessFlag);
				stmt.setString(3, infoFlag);
				stmt.setString(4, memberNum);
				stmt.executeUpdate();
			} catch(Exception e) {
				System.out.println("updateMemberOPE()" + e);
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
		// 회원 탈퇴
		public void deleteMember(String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 deleteMember()");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(MEMBER_DELETE);
				stmt.setString(1, memberNum);
				stmt.executeUpdate();
			} catch(Exception e) {
				System.out.println("deleteMember()" + e);
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
		// 비밀번호 확인
		public int checkPassword(String password, String memberNum) {
			// TODO Auto-generated method stub
			System.out.println("===> JDBC로 checkPassword()");		
			int result = 0;

			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(MEMBER_CHECKPW);
				stmt.setString(1, security.encryptSHA256(password));
				stmt.setString(2, memberNum);
				rs = stmt.executeQuery();			
				while(rs.next()) {
					result = rs.getInt("COUNT(*)");
				}			
			} catch (Exception e) {
				System.out.println("checkPassword()" + e);
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}		
			return result;
		}	
	

}
