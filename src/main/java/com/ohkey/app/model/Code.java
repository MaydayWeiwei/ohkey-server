package com.ohkey.app.model;

import java.io.Serializable;
import java.util.Date;

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
	private Date endDate;

	@JoinColumn(name = "keyid", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private KeyInfo keyInfo;

	@Column(name = "currentdate", nullable = false)
	private Date currentDate;

	@Column(name = "generatecode")
	private String generateCode;
	
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public KeyInfo getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(KeyInfo keyInfo) {
		this.keyInfo = keyInfo;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getGenerateCode() {
		return generateCode;
	}

	public void setGenerateCode(String generateCode) {
		this.generateCode = generateCode;
	}

}
