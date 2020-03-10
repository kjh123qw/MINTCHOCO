package com.kosmo.mintchoco.assessment;

import java.sql.Date;

/*
 * 담당자 : 김정호, 천세문
 */

public class AssessmentMVO {
	
	private String assessNum; // 평가목록 ID
	private int movieNum; // 영화 번호	
	private String poster; // 영화 포스터
	private String movieTitle; // 영화 제목
	private String kind; // 영화 장르
	private String grade; // 영화 등급
	private int playTime; // 상영시간
	private String release; // 개봉 년
	private String aContent; // 평가 내용
	private int astars; // 매긴 점수
	private int stars; // 영화 점수
	private Date assessIndate; // 평가한 날짜
	private int likes; // 평가에 대한 좋아요
	private int hates; // 평가에 대한 싫어요
	
	public AssessmentMVO() {
		super();
	}
	
	public AssessmentMVO(String assessNum, int movieNum, String poster, String movieTitle, String kind, String grade, int playTime, String release, String aContent, int astars, int stars, Date assessIndate, int likes, int hates) {
		this.assessNum = assessNum;
		this.movieNum = movieNum;
		this.poster = poster;
		this.movieTitle = movieTitle;
		this.kind = kind;
		this.grade = grade;
		this.playTime = playTime;
		this.release = release;
		this.aContent = aContent;
		this.astars = astars;
		this.stars = stars;
		this.assessIndate = assessIndate;
		this.likes = likes;
		this.hates = hates;
	}

	public String getAssessNum() {
		return assessNum;
	}

	public void setAssessNum(String assessNum) {
		this.assessNum = assessNum;
	}

	public int getMovieNum() {
		return movieNum;
	}

	public void setMovieNum(int movieNum) {
		this.movieNum = movieNum;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getPlayTime() {
		return playTime;
	}

	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}	
	
	public String getaContent() {
		return aContent;
	}

	public void setaContent(String aContent) {
		this.aContent = aContent;
	}

	public int getAstars() {
		return astars;
	}

	public void setAstars(int astars) {
		this.astars = astars;
	}
	
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Date getAssessIndate() {
		return assessIndate;
	}

	public void setAssessIndate(Date assessIndate) {
		this.assessIndate = assessIndate;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getHates() {
		return hates;
	}

	public void setHates(int hates) {
		this.hates = hates;
	}

	@Override
	public String toString() {
		return "AssessmentMVO [assessNum=" + assessNum + ", movieNum=" + movieNum + ", poster=" + poster
				+ ", movieTitle=" + movieTitle + ", kind=" + kind + ", grade=" + grade + ", playTime=" + playTime
				+ ", release=" + release + ", aContent=" + aContent + ", stars=" + stars + ", assessIndate=" + assessIndate + ", likes=" + likes
				+ ", hates=" + hates + ", toString()=" + super.toString() + "]";
	}
}
