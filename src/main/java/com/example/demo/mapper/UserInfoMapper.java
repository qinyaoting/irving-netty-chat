package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInfoMapper {
    List<UserInfo> queryUserInfoList();

    UserInfo queryUserInfoById(Integer id);

    int addUserInfo(UserInfo userInfo);

    int updateUserInfo(UserInfo userInfo);

    int deleteUserInfo(Integer id);
}
