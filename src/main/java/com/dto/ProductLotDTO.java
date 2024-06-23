package spring.dto;

import lombok.Data;

@Data
public class ProductLotDTO {

	private String name;
	private String category;
	private Integer quantity;
	private Double price;
	private String expired;
	private String uom;
	
}
