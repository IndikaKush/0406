package com.example.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.mapper.NewUserMapper;
import com.example.model.NewUserModel;
import com.example.service.NewUserService;



@Service
public class NewUserImpl implements NewUserService {
  @Resource
  private NewUserMapper mapper;
 
 public int update(NewUserModel newUserModel) {
   
   return mapper.update(newUserModel);
  
 }
    public List<NewUserModel>  checkall(NewUserModel newUserModel) {
  
  return mapper.checkall(newUserModel);
 }
    public List<NewUserModel> selectupdate(NewUserModel newUserModel){
     
  return mapper.selectupdate(newUserModel);
 }



 }