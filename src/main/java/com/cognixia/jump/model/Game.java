package com.cognixia.jump.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false)
	private String platform;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "bought")
	private List<User> boughtBy;
	
	public Game() {
		this(-1, "N/A", 0, 0, "No Platform");
	}

	
	public Game(Integer id, String title, double price, int quantity, String platform) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.platform = platform;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public List<User> getBoughtBy(){
		return boughtBy;
	}
	
	
	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", price=" + price + ", quantity=" + quantity + ", platform="
				+ platform + "]";
	}
	
	
	

	
}
