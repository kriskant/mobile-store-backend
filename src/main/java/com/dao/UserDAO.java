package com.dao;

import com.modal.Users;

public interface UserDAO {
	public void saveUser(Users users);
	public Users getUserById(int user_id);
	public void removeUserById(int user_id);
	public Users get(String email);
}
