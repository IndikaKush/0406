package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.model.NewUserModel;

@Repository
@Mapper
public interface NewUserMapper {
 int insert(NewUserModel newUser);
 int search(NewUserModel newUser);
 int delete(NewUserModel newUser);
 List<NewUserModel> checkall(NewUserModel newUserModel);
 List<NewUserModel> selectupdate(NewUserModel newUserModel);
 int update(NewUserModel newUser);

}