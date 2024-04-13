package com.example.webscoket.demo.controller;

import com.example.webscoket.demo.Config;
import com.example.webscoket.demo.Utils.PictureUtil;
import com.example.webscoket.demo.Utils.SnowflakeIdGenerator;
import com.example.webscoket.demo.entity.SocketEntity;
import com.example.webscoket.demo.entity.httprequest.MessageRequest;
import com.example.webscoket.demo.entity.httpresponse.UserLogin;
import com.example.webscoket.demo.entity.httprequest.LoginRequest;
import com.example.webscoket.demo.webScoket.MyWebSocket;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 */
@RestController
public class Index {
    SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1);

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    // POST请求示例
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("/login")
    public UserLogin login(@RequestBody LoginRequest request) {
        long id = snowflakeIdGenerator.nextId();
        String imgUrl = PictureUtil.getPicture();
        PictureUtil.picMap.put(String.valueOf(id), imgUrl);
        System.out.println("userName:" + request.getUserName() + " 正在使用http登录 生成的id:" + id + "图片地址:" + imgUrl);
        return new UserLogin(request.getUserName(), String.valueOf(id), imgUrl);
    }

    // POST请求聊天历史数据示例
    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("/messageList")
    public List<SocketEntity> messageList(@RequestBody MessageRequest request) {
        int type = request.getType();
        if (type == 0) {//系统群聊
            System.out.println("请求获取群聊消息 请求数据:"+request.toString());
            return MyWebSocket.messageList.stream().filter(socketEntity -> socketEntity.getType() == 0).collect(Collectors.toList());
        } else {
            return MyWebSocket.messageList.stream().filter(socketEntity -> (socketEntity.getFromUser().equals(request.getFromUser()) && socketEntity.getToUser().equals(request.getToUser())
                    || socketEntity.getFromUser().equals(request.getToUser()) && socketEntity.getToUser().equals(request.getFromUser())
            )).collect(Collectors.toList());
        }
    }
}
