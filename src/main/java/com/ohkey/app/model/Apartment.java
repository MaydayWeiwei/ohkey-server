/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ohkey.app.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Wei JIANG
 */
@Entity
@Table(name = "apartment")
public class Apartment implements Serializable {

	private static final long serialVersionUID = 6335922249566440697L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private int id;

	@OneToOne(mappedBy = "apartment", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@JsonIgnore
	private KeyInfo keyInfo;

	@Column(name = "clientname", length = 255)
	private String clientName;

	@Column(name = "tel", length = 255)
	private String tel;

	@Column(name = "address", length = 255)
	private String address;

	@Column(name = "floor", length = 255)
	private String floor;

	@Column(name = "door", length = 255)
	private String door;

	@Column(name = "entercode", length = 255)
	private String enterCode;

	@Column(name = "capacity", nullable = false)
	private int capacity;

	@Column(name = "price")
	private double price;

	@Column(name = "status", nullable = false, length = 255)
	private String status;

	@Column(name = "comment1", length = 255)
	private String comment1;

	@Column(name = "comment2", length = 255)
	private String comment2;

	public Apartment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public KeyInfo getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(KeyInfo keyInfo) {
		this.keyInfo = keyInfo;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getEnterCode() {
		return enterCode;
	}

	public void setEnterCode(String enterCode) {
		this.enterCode = enterCode;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getComment2() {
		return comment2;
	}

	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}

}
