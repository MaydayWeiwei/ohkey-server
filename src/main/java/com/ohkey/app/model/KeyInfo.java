
package com.ohkey.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonIgnore
	private Apartment apartment;

	@OneToMany(mappedBy = "keyInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<Code> codeList;

	@JoinColumn(name = "barid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonIgnore
	private Bar bar;

	@Column(name = "externalkey")
	private Integer externalKey;
	
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

	public Integer getExternalKey() {
		return externalKey;
	}

	public void setExternalKey(Integer externalKey) {
		this.externalKey = externalKey;
	}

}
