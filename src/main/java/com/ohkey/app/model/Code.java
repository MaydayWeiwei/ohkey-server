package com.ohkey.app.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Wei JIANG
 */

@Entity
@Table(name = "code")
public class Code implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private int id;

	@Column(name = "startdate")
	private Date startDate;

	@Column(name = "enddate")
	private String endDate;

	@JoinColumn(name = "keyid", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private KeyInfo keyInfo;

	@Column(name = "currentdate", nullable = false)
	private Timestamp currentDate;

	@Column(name = "generatecode")
	private int generateCode;
	
	public Code () {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public KeyInfo getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(KeyInfo keyInfo) {
		this.keyInfo = keyInfo;
	}

	public Timestamp getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Timestamp currentDate) {
		this.currentDate = currentDate;
	}

	public int getGenerateCode() {
		return generateCode;
	}

	public void setGenerateCode(int generateCode) {
		this.generateCode = generateCode;
	}

}
