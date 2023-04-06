package com.example.service;

import java.util.List;

import com.example.model.NewUserModel;

public interface NewUserService {

 public List<NewUserModel>checkall (NewUserModel newUserModel);
 public int update(NewUserModel newUserModel);
 public List<NewUserModel>selectupdate(NewUserModel newUserModel);

}