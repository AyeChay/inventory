package com.repository;

import static com.repository.ConnectionClass.getConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.SupplierDTO;



public class SupplierRepository {
	public int insert (SupplierDTO req) {
		int i = 0;
		String insertQuery =  "insert into supplier (name,phone_number,contact_name, email, address, user_id) values (?,?,?,?,?,?)";
		try (var con = getConnection(); var ps = con.prepareStatement(insertQuery)) {
			ps.setString(1, req.getName());
			ps.setString(2, req.getPhone_number());
			ps.setString(3, req.getContact_name());
			ps.setString(4, req.getEmail());
			ps.setString(5, req.getAddress());
			ps.setInt(6, req.getUser_id());
			i = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println("Insert Supplier : " + e.getMessage());
			e.printStackTrace();
		}
		return i;
	}
	
	public int updateSupplierById(SupplierDTO req) {
		int i = 0;
		String updateQuery  = "update supplier set name = ?, phone_number = ?, contact_name = ?, email = ?, address = ?, user_id = ? where id = ?";
		try(var con = getConnection(); var ps = con.prepareStatement(updateQuery)) {
			ps.setString(1, req.getName());
			ps.setString(2, req.getPhone_number());
			ps.setString(3, req.getContact_name());
			ps.setString(4, req.getEmail());
			ps.setString(5, req.getAddress());
			ps.setInt(6, req.getUser_id());
			ps.setInt(7, req.getId());
			i = ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Update Supplier By Id : " + e.getMessage());
			e.printStackTrace();
		}
		
		return i;
	}
	
	public int deleteSupplierById(int id) {
		int i = 0;
		String deleteQuery = "delete from supplier where id = ?";
		try(var con = getConnection(); var ps = con.prepareStatement(deleteQuery)) {
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("delete Supplier By Id : " + e.getMessage());
			e.printStackTrace();
		}
		
		return i;
	}
	
	public SupplierDTO showSupplierById(int id) {
		SupplierDTO supplierDTO = null;
		String selectQuery = "select * from supplier where id = ?";
		try(var con = getConnection(); var ps = con.prepareStatement(selectQuery)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				supplierDTO = new SupplierDTO();
				supplierDTO.setId(rs.getInt("id"));
				supplierDTO.setName(rs.getString("name"));
				supplierDTO.setPhone_number(rs.getString("phone_number"));
				supplierDTO.setContact_name(rs.getString("contact_name"));
				supplierDTO.setAddress(rs.getString("address"));
				supplierDTO.setEmail(rs.getString("email"));
				supplierDTO.setUser_id(rs.getInt("user_id"));
			}
			
		} catch(SQLException e) {
			System.out.println("Show Supplier By Id : " + e.getMessage());
			e.printStackTrace();
		}
		return supplierDTO;
	}
	
	public List<SupplierDTO> showAllSupplier() {
		List<SupplierDTO> supplierList = new ArrayList<>();
		String selectQuery = "select * from supplier";
		try(var con = getConnection(); var ps = con.prepareStatement(selectQuery)) {
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO = new SupplierDTO();
				supplierDTO.setId(rs.getInt("id"));
				supplierDTO.setName(rs.getString("name"));
				supplierDTO.setPhone_number(rs.getString("phone_number"));
				supplierDTO.setContact_name(rs.getString("contact_name"));
				supplierDTO.setAddress(rs.getString("address"));
				supplierDTO.setEmail(rs.getString("email"));
				supplierDTO.setUser_id(rs.getInt("user_id"));
				supplierList.add(supplierDTO);
			}
			
			
		} catch(SQLException e) {
			System.out.println("Show All Supplier   : " + e.getMessage());
			e.printStackTrace();
		}
		return supplierList;
	}
}
