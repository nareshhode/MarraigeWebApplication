package com.naresh.auth.model.profile;

import java.sql.Timestamp;

public class UserProfileHoroscope {

	public UserProfileHoroscope() {
		// TODO Auto-generated constructor stub
	}

	private long profileId;

	private String nationality;
	private String religion;
	private String community;
	private String caste;
	private String subCaste;
	private String nativeAncestralOrigin;
	private String city;
	private String state;
	private String nakshatra;
	private String gotra;
	private String rashi;
	private boolean isManglisk;
	private boolean isShani;
	private String maritalStatus;

	private boolean approved;
	private String approvedUserId;
	private Timestamp approvedDateTime;

	private boolean rejected;
	private String rejectedUserId;
	private Timestamp rejectedDateTime;

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getSubCaste() {
		return subCaste;
	}

	public void setSubCaste(String subCaste) {
		this.subCaste = subCaste;
	}

	public String getNativeAncestralOrigin() {
		return nativeAncestralOrigin;
	}

	public void setNativeAncestralOrigin(String nativeAncestralOrigin) {
		this.nativeAncestralOrigin = nativeAncestralOrigin;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNakshatra() {
		return nakshatra;
	}

	public void setNakshatra(String nakshatra) {
		this.nakshatra = nakshatra;
	}

	public String getGotra() {
		return gotra;
	}

	public void setGotra(String gotra) {
		this.gotra = gotra;
	}

	public String getRashi() {
		return rashi;
	}

	public void setRashi(String rashi) {
		this.rashi = rashi;
	}

	public boolean isManglisk() {
		return isManglisk;
	}

	public void setManglisk(boolean isManglisk) {
		this.isManglisk = isManglisk;
	}

	public boolean isShani() {
		return isShani;
	}

	public void setShani(boolean isShani) {
		this.isShani = isShani;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getApprovedUserId() {
		return approvedUserId;
	}

	public void setApprovedUserId(String approvedUserId) {
		this.approvedUserId = approvedUserId;
	}

	public Timestamp getApprovedDateTime() {
		return approvedDateTime;
	}

	public void setApprovedDateTime(Timestamp approvedDateTime) {
		this.approvedDateTime = approvedDateTime;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public String getRejectedUserId() {
		return rejectedUserId;
	}

	public void setRejectedUserId(String rejectedUserId) {
		this.rejectedUserId = rejectedUserId;
	}

	public Timestamp getRejectedDateTime() {
		return rejectedDateTime;
	}

	public void setRejectedDateTime(Timestamp rejectedDateTime) {
		this.rejectedDateTime = rejectedDateTime;
	}

}
