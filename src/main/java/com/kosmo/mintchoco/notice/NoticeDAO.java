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
	final private String SELECT_NOTICE_LIST = "SELECT * FROM NOTICE ORDER BY NOTICE_REGDATE DESC";
	
	// 영화 평점 목록
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
	
}
