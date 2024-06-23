package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.ProductDTO;
import com.dto.ProductLotDTO;



public class ProductService {
	
	public List<ProductDTO> getAll() {
		Connection con=ConnectionClass.getConnection();
		List<ProductDTO> products = new ArrayList<>();
		try {
			PreparedStatement ps=con.prepareStatement("select p.id, p.name, quantity, price, c.name category from product p inner join category c on p.category_id=c.id where p.deleted=0");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				ProductDTO product = new ProductDTO();
				product.setId(rs.getInt("id")+"");
				product.setName(rs.getString("name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setCategory(rs.getString("category"));
				products.add(product);
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Get All : "+ e.getMessage());
		}
		
		return products;
	}

	public ProductDTO getOne(Integer id) {
		Connection con=ConnectionClass.getConnection();
		ProductDTO product=null;
		try {
			PreparedStatement ps = con.prepareStatement("select p.id, p.name, quantity, price, c.name category from product p inner join category c on p.category_id=c.id where p.deleted=0 and p.id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				product=new ProductDTO();
				product.setId(rs.getInt("id")+"");
				product.setName(rs.getString("name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setCategory(rs.getString("category"));
			}
			
			con.close();
		} catch (SQLException e) {
			System.out.println("Get One : "+ e.getMessage());
		}
		
		return product;
	}

	public int updateOne(ProductDTO dto) {
		Connection con=ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("update product set name=?,quantity=?,price=?,category_id=(select id from category where name=?) where id=?");
			ps.setString(1,dto.getName());
			ps.setInt(2, dto.getQuantity());
			ps.setDouble(3, dto.getPrice());
			ps.setString(4, dto.getCategory());
			ps.setInt(5, Integer.valueOf(dto.getId()));
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Update One : "+ e.getMessage());
		}
		return result;
	}

	public int deleteOne(Integer id) {
		Connection con=ConnectionClass.getConnection();
		int result=0;
		try {
			PreparedStatement ps = con.prepareStatement("update product set deleted=1 where id=?");
			ps.setInt(1, id);
			result=ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Delete One : "+ e.getMessage());
		}
		
		return result;
	}

	public int insertOne(ProductLotDTO dto) {
		Connection con=ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO lot (lot_no, price, expired_date, quantity, UoM) VALUES (CONCAT('LOT', UUID()), ?, ?, ?, ?)");
			ps.setDouble(1, dto.getPrice());
			ps.setString(2, dto.getExpired());
			ps.setInt(3, dto.getQuantity());
			ps.setString(4, dto.getUom());
			ps.execute();
			PreparedStatement ps2 = con.prepareStatement("INSERT INTO product (name, category_id, lot_id) VALUES (?, (select id from category where name=?), ?)");
			ps2.setString(1,dto.getName());
			ps2.setString(2, dto.getCategory());
			
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Insert One : "+ e.getMessage());
		}
		return result;
	}

	private Object UUID() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
