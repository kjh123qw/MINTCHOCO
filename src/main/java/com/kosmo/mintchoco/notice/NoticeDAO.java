package com.kosmo.mintchoco.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kosmo.mintchoco.assessment.AssessmentVO;
import com.kosmo.mintchoco.common.JDBCUtil;

/*
 * 담당자 : 박찬영, 김정호
 */

public class NoticeDAO {
	// 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	// 쿼리
	final private String SELECT_NOTICE_LIST = "SELECT * FROM NOTICE ORDER BY NOTICE_NUMBER DESC";
	final private String SELECT_NOTICE_ONE = "SELECT * FROM NOTICE WHERE NOTICE_NUMBER = ?";
	final private String UPDATE_NOTICE_CNT = "UPDATE NOTICE SET NOTICE_CNT = NOTICE_CNT + 1 WHERE NOTICE_NUMBER = ?";
	final private String INSERT_NOTICE = "INSERT INTO NOTICE VALUES(NOTICE_SEQ.NEXTVAL, 1, ?, ?, DEFAULT, DEFAULT)";
	final private String UPDATE_NOTICE = "UPDATE NOTICE SET NOTICE_TITLE = ?, NOTICE_CONTENT = ? WHERE NOTICE_NUMBER = ?";
	final private String DELETE_NOTICE = "DELETE FROM NOTICE WHERE NOTICE_NUMBER = ?";
	
	// 공지사항 목록
	public List<NoticeVO> selectNoticeList() {
		List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SELECT_NOTICE_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				NoticeVO noticeVO = new NoticeVO();
				noticeVO.setNoticeNumber(rs.getInt("notice_number"));
				noticeVO.setMemberNumber(rs.getInt("member_number"));
				noticeVO.setNoticeTitle(rs.getString("notice_title"));
				noticeVO.setNoticeContent(rs.getString("notice_content"));
				noticeVO.setNoticeCnt(rs.getInt("notice_cnt"));
				noticeVO.setNoticeRegdate(rs.getDate("notice_regdate"));
				noticeList.add(noticeVO);
			}
		} catch(Exception e) {
			System.out.println("Error - selectNoticeList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return noticeList;
	}
	
	// 공지사항 검색 목록
	public List<NoticeVO> selectSearchNoticeList(String noticeSearch) {
		String SEARCH_NOTICE = "SELECT * FROM NOTICE WHERE NOTICE_TITLE LIKE '%" + noticeSearch + "%' OR NOTICE_CONTENT LIKE '%" + noticeSearch + "%' ORDER BY NOTICE_NUMBER DESC";
		List<NoticeVO> noticeList = new ArrayList<NoticeVO>();

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SEARCH_NOTICE);
			rs = stmt.executeQuery();
			while(rs.next()) {
				NoticeVO noticeVO = new NoticeVO();
				noticeVO.setNoticeNumber(rs.getInt("notice_number"));
				noticeVO.setMemberNumber(rs.getInt("member_number"));
				noticeVO.setNoticeTitle(rs.getString("notice_title"));
				noticeVO.setNoticeContent(rs.getString("notice_content"));
				noticeVO.setNoticeCnt(rs.getInt("notice_cnt"));
				noticeVO.setNoticeRegdate(rs.getDate("notice_regdate"));
				noticeList.add(noticeVO);
			}
		} catch(Exception e) {
			System.out.println("Error - selectSearchNoticeList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return noticeList;
	}
	
	// 공지사항 디테일
	public NoticeVO selectNoticeOne(String noticeNumber) {
		NoticeVO noticeVO = new NoticeVO();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_NOTICE_CNT);
			stmt.setString(1, noticeNumber);
			stmt.executeUpdate();
			stmt = conn.prepareStatement(SELECT_NOTICE_ONE);
			stmt.setString(1, noticeNumber);
			rs = stmt.executeQuery();
			while(rs.next()) {
				noticeVO.setNoticeNumber(rs.getInt("notice_number"));
				noticeVO.setMemberNumber(rs.getInt("member_number"));
				noticeVO.setNoticeTitle(rs.getString("notice_title"));
				noticeVO.setNoticeContent(rs.getString("notice_content"));
				noticeVO.setNoticeCnt(rs.getInt("notice_cnt"));
				noticeVO.setNoticeRegdate(rs.getDate("notice_regdate"));
			}
		} catch(Exception e) {
			System.out.println("Error - selectNoticeOne()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return noticeVO;
	}
	
	// 공지사항 작성
	public void insertNotice(NoticeVO noticeVO) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(INSERT_NOTICE);
			stmt.setString(1, noticeVO.getNoticeTitle());
			stmt.setString(2, noticeVO.getNoticeContent());
			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error - insertNotice()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 공지사항 수정
	public void updateNotice(NoticeVO noticeVO) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_NOTICE);
			stmt.setString(1, noticeVO.getNoticeTitle());
			stmt.setString(2, noticeVO.getNoticeContent());
			stmt.setInt(3, noticeVO.getNoticeNumber());
			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error - updateNotice()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 공지사항 삭제
	public void deleteNotice(String noticeNumber) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DELETE_NOTICE);
			stmt.setString(1, noticeNumber);
			stmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error - deleteNotice()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}
