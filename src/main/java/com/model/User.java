package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private int id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private boolean status;
	private int roleId;
}
