package com.example.demo.controller;

import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("/queryAll")
    public List<UserInfo> queryUserList(){
        return userInfoMapper.queryUserInfoList();
    }

    @GetMapping("/queryById/{id}")
    public UserInfo queryUserById(@PathVariable("id") int id){
        return userInfoMapper.queryUserInfoById(id);
    }

    @GetMapping("/add")
    public String addUser(){
        userInfoMapper.addUserInfo(new UserInfo(4,"葫芦娃","888888"));
        return  "add Ok!";
    }

    @GetMapping("/update")
    public String updateUser(){
        userInfoMapper.updateUserInfo(new UserInfo(4,"孙悟空","123456"));
        return "update Ok!";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userInfoMapper.deleteUserInfo(id);
        return "delete Ok!";
    }
}
