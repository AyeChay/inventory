package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.warehouseDTO;

public class WarehouseRepository {
	
	public int insertWarehouse(warehouseDTO dto) {
        Connection con = connectionClass.getConnection();
        int result = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO warehouse (product_code, product_name, description, quantity, date, expired_date, location_id, deleted) values (?,?,?,?,?,?,?,?)");
            ps.setString(1, dto.getProductCode());
            ps.setString(2, dto.getProductName());
            ps.setString(3, dto.getDescription());
            ps.setInt(4, dto.getQuantity());
            ps.setDate(5, new java.sql.Date(dto.getDate().getTime()));
            ps.setDate(6, new java.sql.Date(dto.getExpireDate().getTime()));
            ps.setInt(7, dto.getLocationId());
            ps.setBoolean(8, false);

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert Warehouse: " + e.getMessage());
        }
        return result;
    }


	public List<warehouseDTO> getAllWarehouses() {
	    Connection con = connectionClass.getConnection();
	    List<warehouseDTO> lists = new ArrayList<>();
	    try {
	        PreparedStatement ps = con.prepareStatement(
	            "SELECT w.*, l.* FROM warehouse w INNER JOIN location l ON w.location_id = l.location_id where w.deleted= FALSE "
	        );
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            warehouseDTO dto = new warehouseDTO();
	            dto.setId(rs.getInt("id"));
	            dto.setProductCode(rs.getString("product_code"));
	            dto.setProductName(rs.getString("product_name"));
	            dto.setDescription(rs.getString("description"));
	            dto.setQuantity(rs.getInt("quantity"));
	            dto.setDate(rs.getDate("date"));
	            dto.setExpireDate(rs.getDate("expired_date"));
	            dto.setLocationId(rs.getInt("location_id"));
	            dto.setLocationName(rs.getString("location_name"));
	            dto.setDeleted(rs.getBoolean("deleted"));
	            
	            lists.add(dto);
	        }
	    } catch (SQLException e) {
	        System.out.println("Get All Warehouses: " + e.getMessage());
	    }
	    return lists;
	}



	
	public warehouseDTO getWarehouseById(int id) {
	    warehouseDTO warehouseDTO = null;
	    Connection con = connectionClass.getConnection();
	    try {
	        PreparedStatement ps = con.prepareStatement(
	            "SELECT w.*, l.* FROM warehouse w INNER JOIN location l ON w.location_id = l.location_id WHERE w.id = ? and w.deleted = FALSE"
	        );
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            warehouseDTO = new warehouseDTO();
	            warehouseDTO.setId(rs.getInt("id"));
	            warehouseDTO.setProductCode(rs.getString("product_code"));
	            warehouseDTO.setProductName(rs.getString("product_name"));
	            warehouseDTO.setDescription(rs.getString("description"));
	            warehouseDTO.setQuantity(rs.getInt("quantity"));
	            warehouseDTO.setDate(rs.getDate("date"));
	            warehouseDTO.setExpireDate(rs.getDate("expired_date"));
	            warehouseDTO.setLocationId(rs.getInt("location_id"));
	            warehouseDTO.setLocationName(rs.getString("location_name"));
	            warehouseDTO.setDeleted(rs.getBoolean("deleted"));
	        }
	    } catch (SQLException e) {
	        System.out.println("Get Warehouse By Id: " + e.getMessage());
	    }
	    return warehouseDTO;
	}


    public int updateWarehouse(warehouseDTO dto) {
        Connection con = connectionClass.getConnection();
        int result = 0;
        try {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE warehouse SET product_code = ?, product_name = ?, description = ?, quantity = ?, date = ?, expired_date = ?, location_id = ? WHERE id = ? "
            );
            ps.setString(1, dto.getProductCode());
            ps.setString(2, dto.getProductName());
            ps.setString(3, dto.getDescription());
            ps.setInt(4, dto.getQuantity());
            ps.setDate(5, new java.sql.Date(dto.getDate().getTime()));
            ps.setDate(6, new java.sql.Date(dto .getExpireDate().getTime()));
            ps.setInt(7, dto.getLocationId());
            ps.setInt(8, dto.getId());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update Warehouse: " + e.getMessage());
        }
        return result;
    }
    
    public int softDeleteWarehouse(int id) {
        Connection con = connectionClass.getConnection();
        int result = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE warehouse SET deleted = TRUE WHERE id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Soft Delete Warehouse: " + e.getMessage());
        }
        return result;
    }
}
