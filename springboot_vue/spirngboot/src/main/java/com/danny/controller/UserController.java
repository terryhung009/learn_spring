package com.danny.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danny.common.Result;
import com.danny.entity.User;
import com.danny.mapper.UserMapper;
import com.sun.xml.internal.bind.v2.runtime.SwaRefAdapter;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping("/user")
    public Result save(@RequestBody User user){
        if(user.getPassword() == null){
            user.setPassword("123456") ;
        }
        userMapper.insert(user);
        System.out.println(Result.succ(user));
        return Result.succ(user);


    }

    @PutMapping("/user")
    public Result update(@RequestBody User user){

        userMapper.updateById(user);
        System.out.println(Result.succ(user));
        return Result.succ(user);


    }
    @DeleteMapping("/user/{id}")
    public Result delete(@PathVariable Long id){

        userMapper.deleteById(id);

        return Result.succ(id);


    }

    @GetMapping("/user")
    public Result findPage(@RequestParam(defaultValue = "1")  Integer pageNum,
                           @RequestParam(defaultValue = "10")  Integer pageSize,
                           @RequestParam(defaultValue = "")  String search){
//        Page<Object> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(User::getUsername, search);
        }

        IPage<User> userPage = userMapper.selectPage(
                new Page<>(pageNum, pageSize),wrapper);



        return Result.succ(userPage);

    }


}
