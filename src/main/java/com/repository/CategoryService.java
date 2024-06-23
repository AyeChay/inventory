package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import spring.dto.CategoryDTO;

public class CategoryService {

	public List<CategoryDTO> getAll() {
		Connection con=ConnectionClass.getConnection();
		List<CategoryDTO> categories = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from category");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				CategoryDTO category = new CategoryDTO();
				category.setId(rs.getInt("id")+"");
				category.setName(rs.getString("name"));
				category.setDescription(rs.getString("description"));
				categories.add(category);
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Inserting Data : "+ e.getMessage());
		}
		return categories;
	}

	public int updateOne(CategoryDTO dto) {
		Connection con=ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("update category set name=?, description=? where id=?");
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getDescription());
			ps.setInt(3, Integer.valueOf(dto.getId()));
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Updating : "+ e.getMessage());
		}
		return result;
	}

	public int deleteOne(Integer id) {
		Connection con=ConnectionClass.getConnection();
		int result = 0;
		try {
//			PreparedStatement ps = con.prepareStatement("delete from category where id=?");
			PreparedStatement ps = con.prepareStatement("update category set deleted=1 where id=?");
			ps.setInt(1, id);
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Deleting : "+ e.getMessage());
		}
		return result;
	}

	public int insertOne(CategoryDTO dto) {
		Connection con=ConnectionClass.getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO category (name, description) VALUES (?, ?)");
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getDescription());
			result = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println("Insert Category : "+ e.getMessage());
		}
		return result;
	}

	public CategoryDTO getOne(Integer id) {
		Connection con=ConnectionClass.getConnection();
		CategoryDTO category =null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from category where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				category = new CategoryDTO();
				category.setId(rs.getInt("id")+"");
				category.setName(rs.getString("name"));
				category.setDescription(rs.getString("description"));
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Inserting Data : "+ e.getMessage());
		}
		
		return category;
	}

	
	
}
