package com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private int id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private boolean status;
	private int roleId;
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", status=" + status + ", roleId=" + roleId + "]";
	}
}
