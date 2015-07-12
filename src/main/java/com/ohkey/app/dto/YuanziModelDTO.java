package com.ohkey.app.dto;

import java.util.Date;
import java.util.List;

public class YuanziModelDTO {
	private String selfDescription;
	private String pictureDescription;
	private String email;
	private String gender;
	private Date onlineDate;
	private List<YuanziModelQuestionDTO> questionList;
	private List<YuanziModelInfoDTO> infoList;
	private String bigImgUrl;
	private String profileImgUrl;
	private String wechatImgUrl;
	public String getSelfDescription() {
		return selfDescription;
	}
	public void setSelfDescription(String selfDescription) {
		this.selfDescription = selfDescription;
	}
	public String getPictureDescription() {
		return pictureDescription;
	}
	public void setPictureDescription(String pictureDescription) {
		this.pictureDescription = pictureDescription;
	}
	public List<YuanziModelQuestionDTO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<YuanziModelQuestionDTO> questionList) {
		this.questionList = questionList;
	}
	public List<YuanziModelInfoDTO> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<YuanziModelInfoDTO> infoList) {
		this.infoList = infoList;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}
	public String getBigImgUrl() {
		return bigImgUrl;
	}
	public void setBigImgUrl(String bigImgUrl) {
		this.bigImgUrl = bigImgUrl;
	}
	public String getProfileImgUrl() {
		return profileImgUrl;
	}
	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}
	public String getWechatImgUrl() {
		return wechatImgUrl;
	}
	public void setWechatImgUrl(String wechatImgUrl) {
		this.wechatImgUrl = wechatImgUrl;
	}
}
