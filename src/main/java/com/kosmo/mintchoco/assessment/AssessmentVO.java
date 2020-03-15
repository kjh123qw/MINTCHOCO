package com.kosmo.mintchoco.assessment;

import java.sql.Date;

/*
 * 담당자 : 김정호, 천세문
 */

public class AssessmentVO {
	
	private String assessId;
	private int memberNumber;
	private String memberNickname;
	private int movieNumber;
	private String moviePoster;
	private String movieTitle;
	private float movieStars;
	private String movieKind;
	private String movieGrade;
	private int movieTime;
	private String movieDate;
	private String assessContent;
	private int assessStars;
	private Date assessRegdate;
	private int likes;
	private int hates;
	
	public String getAssessId() {
		return assessId;
	}
	public void setAssessId(String assessId) {
		this.assessId = assessId;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public int getMovieNumber() {
		return movieNumber;
	}
	public void setMovieNumber(int movieNumber) {
		this.movieNumber = movieNumber;
	}
	public String getMoviePoster() {
		return moviePoster;
	}
	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public float getMovieStars() {
		return movieStars;
	}
	public void setMovieStars(float movieStars) {
		this.movieStars = movieStars;
	}
	public String getMovieKind() {
		return movieKind;
	}
	public void setMovieKind(String movieKind) {
		this.movieKind = movieKind;
	}
	public String getMovieGrade() {
		return movieGrade;
	}
	public void setMovieGrade(String movieGrade) {
		this.movieGrade = movieGrade;
	}
	public int getMovieTime() {
		return movieTime;
	}
	public void setMovieTime(int movieTime) {
		this.movieTime = movieTime;
	}
	public String getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(String movieDate) {
		this.movieDate = movieDate;
	}
	public String getAssessContent() {
		return assessContent;
	}
	public void setAssessContent(String assessContent) {
		this.assessContent = assessContent;
	}
	public int getAssessStars() {
		return assessStars;
	}
	public void setAssessStars(int assessStars) {
		this.assessStars = assessStars;
	}
	public Date getAssessRegdate() {
		return assessRegdate;
	}
	public void setAssessRegdate(Date assessRegdate) {
		this.assessRegdate = assessRegdate;
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
}
