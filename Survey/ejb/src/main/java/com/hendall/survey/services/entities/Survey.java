package com.hendall.survey.services.entities;
// Generated Jan 25, 2016 2:51:30 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Survey generated by hbm2java
 */
@Entity
@Table(name = "survey")
public class Survey extends BaseEntity implements java.io.Serializable {

	private Integer surveyKey;
	private SurveyTypeLu surveyTypeLu;
	private Users users;
	private Date startDate;
	private Date nextDate;

	private Set<UserSurveyAccess> userSurveyAccesses = new HashSet<UserSurveyAccess>(0);

	public Survey() {
	}

	public Survey(SurveyTypeLu surveyTypeLu, Date startDate, Date createDate, int createUser) {
		this.surveyTypeLu = surveyTypeLu;
		this.startDate = startDate;

	}

	public Survey(SurveyTypeLu surveyTypeLu, Users users, Date startDate, Date nextDate, Date createDate,
			int createUser, Date modifyDate, Integer modifyUser, Set<UserSurveyAccess> userSurveyAccesses) {
		this.surveyTypeLu = surveyTypeLu;
		this.users = users;
		this.startDate = startDate;
		this.nextDate = nextDate;

		this.userSurveyAccesses = userSurveyAccesses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Survey_Key", unique = true, nullable = false)
	public Integer getSurveyKey() {
		return this.surveyKey;
	}

	public void setSurveyKey(Integer surveyKey) {
		this.surveyKey = surveyKey;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Survery_Type_Key", nullable = false)
	public SurveyTypeLu getSurveyTypeLu() {
		return this.surveyTypeLu;
	}

	public void setSurveyTypeLu(SurveyTypeLu surveyTypeLu) {
		this.surveyTypeLu = surveyTypeLu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Survey_Started_User")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Action_Date", nullable = false, length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Next_Date", length = 19)
	public Date getNextDate() {
		return this.nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "survey")
	public Set<UserSurveyAccess> getUserSurveyAccesses() {
		return this.userSurveyAccesses;
	}

	public void setUserSurveyAccesses(Set<UserSurveyAccess> userSurveyAccesses) {
		this.userSurveyAccesses = userSurveyAccesses;
	}

}
