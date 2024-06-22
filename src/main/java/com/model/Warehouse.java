package com.model;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class Warehouse {
	private int id;
    private String productCode;
    private String productName;
    private String description;
    private int quantity;
    private Date date;
    private Date expireDate;
    private int locationId;
    private String locationName;
    private boolean deleted;
}
