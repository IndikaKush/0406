package com.example.service;

import java.util.List;

import com.example.model.UserModel;

public interface UserService {
	public Object insert(UserModel userModel);
	public UserModel checkAccount(UserModel userModel);
	public List<UserModel> takeData(UserModel userModel);
	
	//delete
	public  List<UserModel>selectAllWhereId(UserModel userModel);
	public int deleteUser(UserModel userModel);
		   
	//update
	public UserModel update(UserModel userModel);
	public UserModel findById(Long id);
	public void save(UserModel user);
}

