package com.kosmo.view.assessment;

import java.util.ArrayList;
import java.util.List;

import com.kosmo.mintchoco.assessment.AssessmentMDAO;
import com.kosmo.mintchoco.assessment.AssessmentMVO;

/*
 * 담당자 : 김정호, 천세문
 */

public class AssessmentControllerTemp 
{
	public static final int TAG_CNT_STANDARD = 5;			//평가글에 포함된 #태그가 몇번 이상이면 Mapping테이블에 올릴지 기준
	public static final int ASS_LIKE_CNT_STANDARD = 5;		//평가글에 베스트 순위를 뽑을때 최소 추천수 기준
	
	//평가글 등록시
	public void assInsertAfter(AssessmentMVO VO, AssessmentMDAO DAO)
	{
		List<String> tagList = getTagList(VO.getaContent());
		//평가글에 #태그가 하나라도 있다면
		for(int i = 0; i < tagList.size(); i++)
		{
			int iCount = DAO.getTagCount(VO.getMovieNum(), tagList.get(i));
			//게시물의 평가글 중 해당 #태그를 포함한 글이 기준 이상이면 
			if(TAG_CNT_STANDARD <= iCount)
			{
				//Tag테이블에 추가, 이미 있으면 Cnt를 증가
				int iResult = DAO.insertTag(tagList.get(i), VO.getMovieNum());
				if(-1 == iResult)
					DAO.updateTagUseCnt(tagList.get(i));
				
				//TagMapping테이블에 추가
				if(TAG_CNT_STANDARD == iCount)
					DAO.insertTagMapping(tagList.get(i), VO.getMovieNum());
			}
		}
	}
	
	//평가글 삭제시
	public void assDelAfter(AssessmentMVO VO, AssessmentMDAO DAO)
	{
		//영화의 게시물 중 1~3위의 글을 뽑는다.(추천수가 같을때는 날짜가 최신 순으로)
		List<AssessmentMVO> assRankList = DAO.getAssessRank(VO.getMovieNum(), 3);
		List<String> tagList = getTagList(VO.getaContent());
		
		//지운글이 3위랑 비교해서 추천수가 더 많거나, 추천수가 같고 날짜가 최신이면 -----> 지운글이 기존 3위안에 있었다면
		int compare = VO.getAssessIndate().compareTo(assRankList.get(2).getAssessIndate()); 
		if(assRankList.get(2).getLikes() < VO.getLikes() || (assRankList.get(2).getLikes() == VO.getLikes() && compare < 0)) 
		{
			//평가글에 #태그가 하나라도 있다면
			for(int i = 0; i < tagList.size(); i++)
			{
				int iCount = DAO.getTagCount(VO.getMovieNum(), tagList.get(i));
				boolean bCheck = false;
				for(AssessmentMVO rank : assRankList)
				{
					int iPos = rank.getaContent().indexOf(tagList.get(i));
					if(-1 != iPos)
					{
						bCheck = true;
						break;
					}	
				}
				
				//1~3위의 글에서 같은 태그가 포함되어 있지 않고 해당 #태그를 포함한 글이 기준 미만이면 삭제		
				if(false == bCheck && iCount < TAG_CNT_STANDARD)
					DAO.deleteTagMapping(tagList.get(i), VO.getMovieNum());
				
				//한번만 실행
				if(0 == i)
				{
					//3위의 글에 #태그가 있으면 맵핑 추가
					List<String> list = getTagList(assRankList.get(2).getaContent());
					for(String str : list)
						DAO.insertTagMapping(str, VO.getMovieNum());
				}
			}
		}
		else
		{
			//평가글에 #태그가 하나라도 있다면
			for(int i = 0; i < tagList.size(); i++)
			{
				//게시물의 평가글 중 해당 #태그를 포함한 글이 기준 미만이면
				int iCount = DAO.getTagCount(VO.getMovieNum(), tagList.get(i));
				if(iCount < TAG_CNT_STANDARD)
					DAO.deleteTagMapping(tagList.get(i), VO.getMovieNum());	
			}
		}
	}
	
	//추천 눌렀을시
	public void assPushLikesAfter(AssessmentMVO VO, AssessmentMDAO DAO)
	{
		List<String> tagList = getTagList(VO.getaContent());
		
		//영화의 게시물 중 1~4위의 글을 뽑는다.(추천수가 같을때는 날짜가 최신 순으로)
		List<AssessmentMVO> assRankList = DAO.getAssessRank(VO.getMovieNum(), 4);
		for(int i = 0; i < assRankList.size() - 1; i++)
		{
			//내가 3위 안에 포함되어 있다면
			if(VO.getAssessNum().equals(assRankList.get(i).getAssessNum()))
			{
				//#태그맵핑 추가
				for(String str : tagList)
					DAO.insertTagMapping(str, VO.getMovieNum());
				
				List<String> rank4TagList = getTagList(assRankList.get(3).getaContent());
				for(String strTag : rank4TagList)
				{
					//4위글에 포함된 태그의 사용횟수가 기준 이상이면 무시
					int iCount = DAO.getTagCount(VO.getMovieNum(), strTag);
					if(TAG_CNT_STANDARD <= iCount)
						continue;
							
					boolean bCheck = false;
					for(AssessmentMVO rank : assRankList)
					{
						int iPos = rank.getaContent().indexOf(strTag);
						if(-1 != iPos)
						{
							bCheck = true;
							break;
						}	
					}
					
					//1~3위의 글에서 같은 태그가 포함되어 있지 않고 해당 #태그를 포함한 글이 기준 미만이면 삭제		
					if(false == bCheck)
						DAO.deleteTagMapping(strTag, VO.getMovieNum());
				}
				
				break;
			}
		}
	}
	
	//추천 취소했을 시
	public void assCancelLikesAfter(AssessmentMVO VO, AssessmentMDAO DAO)
	{
		//영화의 게시물 중 1~4위의 글을 뽑는다.(추천수가 같을때는 날짜가 최신 순으로)
		List<AssessmentMVO> assRankList = DAO.getAssessRank(VO.getMovieNum(), 4);
		
		//내가 4위였었다면
		if(VO.getAssessNum().equals(assRankList.get(3).getAssessNum()))
		{
			//글이 3위랑 비교해서 추천수가 하나 작고 날짜가 최신이면 or 추천수가 같고 날짜가 최신이 아니면----->  기존 3위 였었다면
			int compare = VO.getAssessIndate().compareTo(assRankList.get(2).getAssessIndate()); 
			if((assRankList.get(2).getLikes() == VO.getLikes() - 1 && compare < 0) || (assRankList.get(2).getLikes() == VO.getLikes() &&  0 < compare))
			{
				//3위의 글에 포함된 태그맵핑추가 
				List<String> tag3List = getTagList(assRankList.get(2).getaContent());
				for(String str : tag3List)
					DAO.insertTagMapping(str, VO.getMovieNum());
				
				List<String> tagList = getTagList(VO.getaContent());
				for(String strTag : tagList)
				{
					//4위글에 포함된 태그의 사용횟수가 기준 이상이면 무시
					int iCount = DAO.getTagCount(VO.getMovieNum(), strTag);
					if(TAG_CNT_STANDARD <= iCount)
						continue;
							
					boolean bCheck = false;
					for(AssessmentMVO rank : assRankList)
					{
						int iPos = rank.getaContent().indexOf(strTag);
						if(-1 != iPos)
						{
							bCheck = true;
							break;
						}	
					}
					
					//1~3위의 글에서 같은 태그가 포함되어 있지 않고 해당 #태그를 포함한 글이 기준 미만이면 삭제		
					if(false == bCheck)
						DAO.deleteTagMapping(strTag, VO.getMovieNum());
				}
			}
		}
	}
	
	//평가글에서 @태그 뽑기
	public List<String> getTagList(String assContent)
	{
		List<String> tagList = new ArrayList<String>(); 
		int iPos, iPosEnd = 0;
		
		while(true)
		{
			iPos = assContent.indexOf(iPosEnd, '@');
			if(-1 != iPos)
			{
				iPosEnd = assContent.indexOf(iPos, ' ');
				String strTag = assContent.substring(iPos, iPosEnd); 
				
				boolean bCheck = true;
				for(int i = 0; i < tagList.size(); i++)
				{
					if(strTag.equals(tagList.get(i)))
					{
						bCheck = false;
						break;
					}
				}
				
				if(true == bCheck)
					tagList.add(strTag);
			}
			else
				break;
		}	
		
		return tagList; 
	}
}
