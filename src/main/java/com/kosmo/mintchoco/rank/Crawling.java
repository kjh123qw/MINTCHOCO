package com.kosmo.mintchoco.rank;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kosmo.mintchoco.common.ApiCall;
import com.kosmo.mintchoco.common.Unicode;

/*
 * 담당자 : 최원준
 */

public class Crawling 
{
	public static HashMap<Integer, String[]> CGVMovieRank()
	{
		String strUrl = "http://www.cgv.co.kr/movies/"; 
		Document doc = null;

		try 
		{
			doc = Jsoup.connect(strUrl).get();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
		Elements element = doc.select("div.sect-movie-chart");    

		//Iterator을 사용하여 하나씩 값 가져오기
		Iterator<Element> ie1 = element.select("strong.rank").iterator();
		Iterator<Element> ie2 = element.select("strong.title").iterator();
		Iterator<Element> ie3 = element.select("span.thumb-image > img").iterator();
		
		HashMap<Integer, String[]> mapMovieRank = new HashMap<Integer, String[]>();
		while (ie1.hasNext())
		{
			String[] arrStr = {ie2.next().text(), ie3.next().attr("src")};
			
			String strRank = ie1.next().text();
			Integer iRank = Integer.parseInt(strRank.substring(strRank.indexOf('.') + 1));
			mapMovieRank.put(iRank, arrStr);
		}
		
		return mapMovieRank;
	}
	
	public static HashMap<Integer, String[]> MegaBoxMovieRank()
	{
		HashMap<Integer, String[]> mapMovieRank = new HashMap<Integer, String[]>();
		
		String strUrl = "https://www.megabox.co.kr/on/oh/oha/Movie/selectMovieList.do";
		String strResponse = ApiCall.post(strUrl);
		strResponse = Unicode.decode(strResponse);
		
		try 
		{
            JSONParser jsonParser = new JSONParser(); //parser
            JSONObject jsonObj = (JSONObject) jsonParser.parse(strResponse);
            String imgSvrUrl = (String)jsonObj.get("imgSvrUrl");
            
            JSONArray movieArray = (JSONArray) jsonObj.get("movieList");
            for(int i = 0; i < movieArray.size(); i++)
            {
                JSONObject tempObj = (JSONObject)movieArray.get(i);
                Integer iRank = (int)(long)tempObj.get("boxoRank");
                String strName = (String)tempObj.get("movieNm");
                String strPath = imgSvrUrl + (String)tempObj.get("imgPathNm");
                
                String arrStr[] = {strName, strPath};
                
                mapMovieRank.put(iRank, arrStr);
            }
        }
		catch (ParseException e) 
		{
            e.printStackTrace();
        }
		
		return mapMovieRank;
	}
}
