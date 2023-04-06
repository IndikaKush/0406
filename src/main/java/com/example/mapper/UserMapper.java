package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.model.UserModel;
@Repository
@Mapper
public interface UserMapper {

	int insert(UserModel userModel);

	UserModel checkAccount(UserModel userModel);

	List<UserModel> takeData(UserModel userModel);

	UserModel takeDay(UserModel userModel);

	List<UserModel> selectAllWhereId(UserModel userModel);

	int deleteUser(UserModel userModel);

	UserModel update(UserModel userModel);

	Object findById(Long id);

}