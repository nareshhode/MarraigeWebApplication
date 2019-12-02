package com.naresh.auth.model.profile;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.naresh.auth.model.User;

@Entity
@Table(name = "userProfile")
public class UserProfile {

	public UserProfile() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "PROFILE_ID", unique = true, nullable = false)
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = {
			@Parameter(name = "property", value = "user") })
	private Long profileId;

	private boolean fullyApproved;
	private boolean inProgress;
	private boolean rejected;

	private String rejectedComments;

	private Timestamp createdDateTime;
	private Timestamp updatedDateTime;

	@Version
	private int version;

	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public boolean isFullyApproved() {
		return fullyApproved;
	}

	public void setFullyApproved(boolean fullyApproved) {
		this.fullyApproved = fullyApproved;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Timestamp getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Timestamp updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public String getRejectedComments() {
		return rejectedComments;
	}

	public void setRejectedComments(String rejectedComments) {
		this.rejectedComments = rejectedComments;
	}

}
