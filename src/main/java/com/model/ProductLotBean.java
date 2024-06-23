package com.model;

import java.util.Date;

import lombok.Data;

@Data
public class ProductLotBean {

	private String name;
	private String category;
	private Integer quantity;
	private Double price;
	private String expired;
	private String uom;
	
}
