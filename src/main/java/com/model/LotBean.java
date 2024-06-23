package spring.model;

import lombok.Data;

@Data
public class LotBean {

	private String id;
	private Integer quantity;
	private Double price;
	private String lot;
	private String expired;
	private String uom;
	
}
