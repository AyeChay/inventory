package com.repository;

import static com.repository.ConnectionClass.getConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.UserDTO;



public class UserRepository {
	public int insert (UserDTO req) {
		int i = 0;
		String insertQuery = "insert into user (username, password, email, phone_number, status, role_id) values (?,?,?,?,?,?)";
		try (var con = getConnection(); var ps = con.prepareStatement(insertQuery)) {
			ps.setString(1, req.getName());
			ps.setString(2, req.getPassword());
			ps.setString(3, req.getEmail());
			ps.setString(4, req.getPhone());
			ps.setBoolean(5, req.isStatus());
			ps.setInt(6, req.getRoleId());
			
			i = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println("Insert User : " + e.getMessage());
			e.printStackTrace();
		}
		return i;
	}
	
	public UserDTO showUserById(int i) {
		UserDTO user = null;
		String selectQuery = "select * from user where id = ?";
		try(var con = getConnection(); var ps = con.prepareStatement(selectQuery)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				 user = new UserDTO();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("username"));
				 user.setPassword(rs.getString("password"));
				 user.setEmail(rs.getString("email"));
				 user.setPhone(rs.getString("phone_number"));
				 user.setStatus(rs.getBoolean("status"));
				 user.setRoleId(rs.getInt("role_id"));
			}
			
		} catch(SQLException e) {
			System.out.println("Show User By Id : " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	
	public int deleteUserById(int id) {
		int i = 0;
		
		String deleteQuery = "delete from user where id = ?";
		try(var con = getConnection(); var ps = con.prepareStatement(deleteQuery)) {
			ps.setInt(1, id);
			i = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Delete User By Id : "+ e.getMessage());
			e.printStackTrace();
		}
		return i;
	}
	
	public int updateUserById(UserDTO req) {
		int i = 0;
		String updateQuery  = "update user set username = ? , password = ?, email = ?, phone_number = ?, status = ?, role_id = ? where id = ?";
		try(var con = getConnection(); var ps = con.prepareStatement(updateQuery)) {
			ps.setString(1, req.getName());
			ps.setString(2, req.getPassword());
			ps.setString(3, req.getEmail());
			ps.setString(4, req.getPhone());
			ps.setBoolean(5, req.isStatus());
			ps.setInt(6, req.getRoleId());
			ps.setInt(7, req.getId());
			i = ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Update User By Id : " + e.getMessage());
			e.printStackTrace();
		}
		
		return i;
	}
	
	public List<UserDTO> showAllUser() {
		 String selectQuery = "select * from user";
		 List<UserDTO> userList = new ArrayList<>();
		 try(var con = getConnection(); var ps = con.prepareStatement(selectQuery)) {
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()) {
				 UserDTO user = new UserDTO();
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("username"));
				 user.setPassword(rs.getString("password"));
				 user.setEmail(rs.getString("email"));
				 user.setPhone(rs.getString("phone_number"));
				 user.setStatus(rs.getBoolean("status"));
				 user.setRoleId(rs.getInt("role_id"));
				 userList.add(user);
			 }
		 }catch(Exception e) {
				System.out.println("Show All User : " + e.getMessage());
				e.printStackTrace();
		}
		 
		return userList;
	}
}
