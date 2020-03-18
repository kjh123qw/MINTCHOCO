package com.kosmo.mintchoco.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kosmo.mintchoco.common.JDBCUtil;
import com.kosmo.view.search.SearchController;

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

	// 전체 태그 목록
	public List<String> selectTagList()
	{
		List<String> strList = new ArrayList<String>(); 
		String sql = "SELECT * FROM TAG";
		try 
		{
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) 
			{
				String str = rs.getString("tag_content");
				strList.add(str);
			}
		}
		catch(Exception e) 
		{
			System.out.println("Error - selectTagList()\n");
			e.printStackTrace();
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return strList;
	}
	
	//태그 맵핑 Map
	public Map<String, ArrayList<Integer>> selectTagbyMovie(List<String> strTagList)
	{
		Map<String, ArrayList<Integer>> mapTagMappingList = new HashMap<String, ArrayList<Integer>>();
		String sql = "SELECT movie_number FROM tag_mapping WHERE upper(tag_content) = upper('?')";
		try
		{
			conn = JDBCUtil.getConnection();
			for(String str : strTagList)
			{
				ArrayList<Integer> movieNumberList = new ArrayList<Integer>(); 
				mapTagMappingList.put(str, movieNumberList);
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, str);
				
				rs = stmt.executeQuery();
				while(rs.next()) 
				{
					int number = rs.getInt("movie_number");
					mapTagMappingList.get(str).add(number);
				}
			}
		}
		catch(Exception e) 
		{
			System.out.println("Error - selectMovieList()\n");
			e.printStackTrace();
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return mapTagMappingList;
	}
	
	// 태그 검색 메소드
	public List<SearchVO> searchTagMovieList(ArrayList<Integer> movie_Number)
	{
		List<SearchVO> movieList = null;
		Map<Integer, SearchVO> mapSearchData = new HashMap<Integer, SearchVO>();
		
		//평점, 날짜, 최근 접근여부를 뽑는 쿼리
		String arrSql[] = new String[3];
		arrSql[0] = "SELECT * FROM (SELECT * FROM search_view ? ORDER BY stars DESC) WHERE rownum <= ?";
		arrSql[1] = "SELECT * FROM (SELECT * FROM search_view ? ORDER BY movie_date DESC) WHERE rownum <= ?";
		arrSql[2] = "SELECT * FROM (SELECT view.* FROM search_view view JOIN (SELECT movie_number, COUNT(*) AS cnt FROM ASSESSMENT WHERE SYSDATE - ? <= assess_regdate ? GROUP BY movie_number) movie WHERE view.movie_number = movie.movie_number ORDER BY movie.cnt DESC) WHERE ROWNUM <= ?";

		String strWhere = "";
		String strWhere2[] = {"where", "where", "and ("};
		ArrayList<ArrayList<SearchVO>> resultList = new ArrayList<ArrayList<SearchVO>>(3);
		
		for(int i = 0; i < movie_Number.size(); i++)
		{
			if(0 != i)
				strWhere += " or";
			
			strWhere += " movie_number = " + Integer.toString(i);
		}
		
		try 
		{
			conn = JDBCUtil.getConnection();
			for(int i = 0; i < arrSql.length; i++)
			{
				strWhere2[i] += strWhere;
				stmt = conn.prepareStatement(arrSql[i]);
				
				if(0 == i || 1 == i)
				{
					stmt.setString(1, strWhere2[i]);
					stmt.setInt(2, SearchController.TAG_SEARCH_VIEW_COUNT);	
				}
				else if(2 == i)
				{
					strWhere2[i] += ")";
					
					stmt.setInt(1, SearchController.TAG_SEARCH_DATE_STANDARD);
					stmt.setString(2, strWhere2[i]);
					stmt.setInt(3, SearchController.TAG_SEARCH_VIEW_COUNT);
				}
				
				rs = stmt.executeQuery();
				while(rs.next())
				{
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
					resultList.get(i).add(searchVO);
					
					int number = rs.getInt("movie_number");
					mapSearchData.put(number, searchVO);
				}
				
				Collections.reverse(resultList);
			}
			
			//Map에 3개의 sql결과를 종합하여 점수를 매긴다(낮을 수록 우선순위 높음)
			Map<Integer, Integer> mapSumResult = new HashMap<Integer, Integer>();
			for(int i = 0; i < resultList.size(); i++)
			{
				for(int j = 0; j < resultList.get(i).size(); j++)
				{
					int iMovieNumber = resultList.get(i).get(j).getMovieNumber();
					if(mapSumResult.containsKey(iMovieNumber))
						mapSumResult.put(iMovieNumber, mapSumResult.get(iMovieNumber) + j);
					else
						mapSumResult.put(iMovieNumber, j);
				}
			}
			
			List<Integer> list = sortByValue(mapSumResult); 
			if(0 < list.size())
			{
				movieList = new ArrayList<SearchVO>(); 
				for(int i = 0; i < list.size(); i++)
					movieList.add(mapSearchData.get(list.get(i)));
			}
		}
		catch(Exception e) 
		{
			System.out.println("Error - searchTagMovieList()\n");
			e.printStackTrace();
		}
		finally 
		{
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return movieList;
	}
	
	//맵 정렬 함수
	public List sortByValue(final Map map)
	{
		List<Integer> list = new ArrayList();
		list.addAll(map.keySet());
		
		Collections.sort(list, new Comparator()
		{
			public int compare(Object o1, Object o2)
			{
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
				return ((Comparable) v2).compareTo(v1);
			}
		});

		Collections.reverse(list);	//주석시 오름차순
		return list;
	}
	
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
		String movieTitle2 = " (REPLACE(MOVIE_TITLE, ' ', '') LIKE '%" + searchKeyWord.replace(" ", "") + "%')";
		String movieKind = "";
		String movieDirector = "";
		String movieActor = "";
		String movieContent = "";
		String orExpr = "";
		boolean firstTitleCond = true;
		String test = " (REPLACE(MOVIE_TITLE, ' ', '')) LIKE '%";
		for(char titleLetter : searchTitleArr) {
			if(titleLetter != ' ') {
				if('0' <= titleLetter && titleLetter <= '9') {
					movieTitle += (firstTitleCond?" (MOVIE_TITLE LIKE '":" AND MOVIE_TITLE LIKE '%") + titleLetter + "%'";
				} else {
					movieTitle += (firstTitleCond?" (MOVIE_TITLE LIKE '":" AND MOVIE_TITLE LIKE '%") + titleLetter + "%'";
//					movieTitle2 += (firstTitleCond?" (MOVIE_TITLE LIKE '":" AND MOVIE_TITLE LIKE '%") + titleLetter + "%'";
				}
				if(firstTitleCond)
					firstTitleCond = false;
			}
		}
		movieTitle += ")";
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
