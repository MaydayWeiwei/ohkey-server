package com.ohkey.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Wei JIANG
 */
@Entity
@Table(name = "keyinfo")
public class KeyInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer id;

	@JoinColumn(name = "aptid", referencedColumnName = "id")
	@OneToOne(optional = false)
	private Apartment apartment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "keyInfo")
	private List<Code> codeList;

	@JoinColumn(name = "barid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Bar bar;

	@Column(name = "externalkey")
	private double externalKey;
	
	public KeyInfo () {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public List<Code> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<Code> codeList) {
		this.codeList = codeList;
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public double getExternalKey() {
		return externalKey;
	}

	public void setExternalKey(double externalKey) {
		this.externalKey = externalKey;
	}

}
