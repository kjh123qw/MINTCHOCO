package com.kosmo.mintchoco.favorite;

import java.sql.Date;

/*
 * 담당자 : 장세진
 */

public class FavoriteVO {
	private String favNum; // 찜목록 ID
	private int movieNum; // 영화 번호
	private Date favIndate; // 찜한 날짜
	private String movieTitle; // 영화 제목
	private String poster; // 영화 포스터
	private String kind; // 영화 장르
	private String grade; // 영화 등급
	private int playtime; // 상영시간
	
	
	public FavoriteVO() {
		super();
	}
	
	public FavoriteVO(String favNum, int movieNum, Date favIndate, String movieTitle, String poster, String kind, String grade, int playtime) {
		this.favNum = favNum;
		this.movieNum = movieNum;
		this.favIndate = favIndate;
		this.movieTitle = movieTitle;
		this.poster = poster;
		this.kind = kind;
		this.grade = grade;
		this.playtime = playtime;
	}
	
	public String getFavNum() {
		return favNum;
	}


	public void setFavNum(String favNum) {
		this.favNum = favNum;
	}

	public int getMovieNum() {
		return movieNum;
	}


	public void setMovieNum(int movieNum) {
		this.movieNum = movieNum;
	}


	public Date getFavIndate() {
		return favIndate;
	}


	public void setFavIndate(Date favIndate) {
		this.favIndate = favIndate;
	}


	public String getMovieTitle() {
		return movieTitle;
	}


	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}


	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
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


	public int getPlaytime() {
		return playtime;
	}


	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	@Override
	public String toString() {
		return "FavoriteVO [favNum=" + favNum + ", movieNum=" + movieNum + ", favIndate=" + favIndate + ", movieTitle=" + movieTitle
				+ ", poster=" + poster + ", kind=" + kind + ", grade=" + grade + ", playtime=" + playtime
				+ ", toString()=" + super.toString() + "]";
	}
}
