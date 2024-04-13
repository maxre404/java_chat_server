package com.example.webscoket.demo.controller;
import com.example.webscoket.demo.Utils.PictureUtil;
import com.example.webscoket.demo.Utils.SnowflakeIdGenerator;
import com.example.webscoket.demo.entity.httpresponse.UserLogin;
import com.example.webscoket.demo.entity.httprequest.LoginRequest;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 */
@RestController
public class Index {
    SnowflakeIdGenerator snowflakeIdGenerator =new SnowflakeIdGenerator(1);
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    // POST请求示例
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("/login")
    public UserLogin login(@RequestBody LoginRequest request) {
        long id = snowflakeIdGenerator.nextId();
        String imgUrl = PictureUtil.getPicture();
        PictureUtil.picMap.put(String.valueOf(id), imgUrl);
        System.out.println("userName:"+request.getUserName()+" 正在使用http登录 生成的id:"+id+"图片地址:"+imgUrl);
        return new UserLogin(request.getUserName(),String.valueOf(id),imgUrl);
    }
}
