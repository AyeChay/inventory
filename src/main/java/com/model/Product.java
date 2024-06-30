package com.model;

import java.sql.Date;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {
	private int id;
	private String productCode;
    private String productName;
    private String description;
    
    private boolean deleted;
}