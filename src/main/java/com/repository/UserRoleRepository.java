package com.repository;

import static com.repository.ConnectionClass.getConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.UserRoleDTO;



public class UserRoleRepository {
	public int insert(UserRoleDTO roleDto) {
		int i = 0;
		String insertQuery = "insert into role(role) values (?)";
		try (var con = getConnection(); var ps = con.prepareStatement(insertQuery)) {
			ps.setString(1, roleDto.getName());
			i = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println("Insert UserRole : " + e.getMessage());
			e.printStackTrace();
		}
		return i;
	}
	
	public int updateUserRoleById(UserRoleDTO req) {
		int i = 0;
		String updateQuery  = "update role set role = ? where id = ?";
		try(var con = getConnection(); var ps = con.prepareStatement(updateQuery)) {
			ps.setString(1, req.getName());
			ps.setInt(2, req.getId());
			i = ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Update User Role By Id : " + e.getMessage());
			e.printStackTrace();
		}
		
		return i;
	}
	
	public UserRoleDTO showUserRoleById(int id) {
		String selectQuery = "select * from role where id = ?";
		UserRoleDTO userRole = null;
		try(var con = getConnection(); var ps = con.prepareStatement(selectQuery)) {
			ps.setInt(1, id);
			var rs = ps.executeQuery();
			while(rs.next()) {
				userRole = new UserRoleDTO();
				userRole.setId(rs.getInt("id"));
				userRole.setName(rs.getString("role"));
			}
		}catch(Exception e) {
			System.out.println("Show User Role DTo By Id : " + e.getMessage());
			e.printStackTrace();
		}
		return userRole;
	}
	
	public int deleteUseRoleById(int id) {
		int i = 0;
		
		String deleteQuery = "delete from role where id = ?";
		try(var con = getConnection(); var ps = con.prepareStatement(deleteQuery)) {
			ps.setInt(1, id);
			i = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Delete User Role By Id : "+ e.getMessage());
			e.printStackTrace();
		}
		return i;
	}
	
	public List<UserRoleDTO> showAllUserRole() {
		String selectQuery = "select * from role";
		List<UserRoleDTO> roleDtoList = new ArrayList<>();
		try(var con = getConnection(); var ps = con.prepareStatement(selectQuery)) {
			var rs = ps.executeQuery();
			while(rs.next()) {
				UserRoleDTO roleDto = new UserRoleDTO();
				roleDto.setId(rs.getInt("id"));
				roleDto.setName(rs.getString("role"));
				roleDtoList.add(roleDto);
			}
		}catch(Exception e) {
			System.out.println("Show All User Role  : " + e.getMessage());
			e.printStackTrace();
		}
		return roleDtoList;
	}
	
	 
}
