package com.kosmo.mintchoco.tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kosmo.mintchoco.common.JDBCUtil;

public class TagDAO {


	// 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	// 쿼리
	final private String SELECT_TAG_LIST = "SELECT * FROM TAG WHERE MOVIE_NUMBER IS NULL";
	final private String SELECT_MORE_TAG_LIST = "SELECT * FROM (SELECT * FROM TAG WHERE MOVIE_NUMBER IS NOT NULL ORDER BY CNT DESC) WHERE ROWNUM <= 10 AND MOVIE_NUMBER IS NOT NULL ORDER BY CNT DESC";

	// 전체 영화 목록
	public List<TagVO> selectTagList(int type) {
		List<TagVO> tagList = new ArrayList<TagVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement((type == 1) ? SELECT_TAG_LIST : SELECT_MORE_TAG_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				TagVO tagVO = new TagVO();
				tagVO.setTagContent(rs.getString("tag_content"));
				tagVO.setMovieNumber(rs.getInt("movie_number"));
				tagVO.setCnt(rs.getInt("cnt"));
				tagList.add(tagVO);
			}
			
		} catch(Exception e) {
			System.out.println("Error - selectTagList()\n");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return tagList;
	}
	
}
