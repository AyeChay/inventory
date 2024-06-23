package spring.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private String id;
	private String name;
	private Integer quantity;
	private Double price;
	private String category;
}
