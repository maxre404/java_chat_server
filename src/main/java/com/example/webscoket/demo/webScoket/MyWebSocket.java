package com.example.webscoket.demo.webScoket;

import com.example.webscoket.demo.Command;
import com.example.webscoket.demo.Utils.PictureUtil;
import com.example.webscoket.demo.entity.SocketEntity;
import com.example.webscoket.demo.entity.SocketResponse;
import com.example.webscoket.demo.entity.SocketUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.BiConsumer;

/**
 * Created by 自由翱翔峰 on 2018/12/7 16:09
 */

/**
 * 虽然@component默认是单例模式的，但在spring boot 中还是会为每一个websocket连接初始化一个bean，所以这里使用一个静态的set保存spring boot创建的bean--MyWebscoket
 */
@ServerEndpoint ( value="/websocket/{userId}/{name}" )
@Component
public class MyWebSocket {
    //用来存储每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet <MyWebSocket> (  );
    //用来记录sessionId和session之间的绑定关系。
    private static Map<String,Session> map = new HashMap <String,Session> (  );
    private static Map<String,MyWebSocket> userMap = new ConcurrentHashMap<>();
    private Session session;//当前会话的session
    private String name;
    private String userId;
    private String imgUrl = "";
    private Gson gson = new Gson();
    /**
     * 成功建立连接调用的方法
     */
    @OnOpen
    public void onOpen( Session session, @PathParam  ( "userId" ) String userId,@PathParam  ( "name" ) String name){
        this.session = session;
        this.name = name;
        this.userId = userId;
        map.put ( session.getId (),session );
        webSocketSet.add ( this );//加入set中
        imgUrl = PictureUtil.picMap.get(userId);
        userMap.put(userId,this);
        System.out.println( name+"上线了"+"我的频道号是"+session.getId ()+",当前连接人数为："+ webSocketSet.size ());
        broadCastUserLogin(new SocketResponse(Command.cmd_user_info_99,new SocketUser(userId,name,session.getId(), imgUrl)));
//        this.session.getAsyncRemote ().sendText ( name+"上线了"+"我的频道号是"+session.getId ()+",当前连接人数为："+ webSocketSet.size () );
//        this.session.getAsyncRemote().sendText(gson.toJson(new SocketResponse(Command.cmd_user_info_99,new SocketUser(name,session.getId(), imgUrl))));
    }

    /**
     * 连接关闭调用的方法
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        System.out.println(this.name+"用户下线  id:"+userId);
        webSocketSet.remove ( this );//加入set中
        map.remove ( session.getId () );
        userMap.remove(userId);
    }
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace ();
    }

    /**
     * 收到客户消息后调用的方法
     * @param session
     */
    @OnMessage
    public void OnMessage(String message,Session session,  @PathParam  ( "userId" ) String userId,@PathParam  ( "name" ) String name) throws IOException {
        //message不是普通的string,而是我们定义的SocketEntity,json字符串
        SocketEntity socketEntity = new ObjectMapper (  ).readValue ( message,SocketEntity.class );
        int cmd = socketEntity.getCmd();
        System.out.println("收到cmd:"+cmd);
        switch (cmd){
            case Command.cmd_get_online_users_100:
                ArrayList<SocketUser> userList = new ArrayList<>();
                for (MyWebSocket myWebSocket: webSocketSet){
                    userList.add(new SocketUser(myWebSocket.userId,myWebSocket.name,myWebSocket.session.getId(),myWebSocket.imgUrl));
                }
                session.getAsyncRemote().sendText(gson.toJson(new SocketResponse(Command.cmd_get_online_users_100,userList)));
                break;
            case Command.cmd_chat_101:
                //        //单聊
                if (socketEntity.getType() == 1) {
                    //单聊：需要找到发送者和接收者即可
//                    socketEntity.setFromUser(userId);//发送者
//                    Session fromsession = map.get(socketEntity.getFromUser());
//                    Session tosession = map.get(socketEntity.getToUser());
                    Session fromsession = userMap.get(socketEntity.getFromUser()).session;
                    Session tosession = userMap.get(socketEntity.getToUser()).session;
                    if (tosession != null) {
                        fromsession.getAsyncRemote().sendText(gson.toJson(new SocketResponse(Command.cmd_chat_101,socketEntity)));//发送消息
                        tosession.getAsyncRemote().sendText(gson.toJson(new SocketResponse(Command.cmd_chat_101,socketEntity)));//发送消息
                    } else {
                        fromsession.getAsyncRemote().sendText("系统消息:对方不在线或者您输入的频道号有误");//发送消息
                    }
                } else {
                    //   广播群发给每一个客户端
                    broadcast(socketEntity, name);
                }
                break;
        }
    }

    /**
     * 群发消息
     * @param socketEntity
     */
    private void broadcast(SocketEntity socketEntity ,String name) {
        for (MyWebSocket myWebSocket: webSocketSet){
            //发送消息
            myWebSocket.session.getAsyncRemote ().sendText (name+":"+socketEntity.getMessage ());
        }
    }

    private void broadCastUserLogin(SocketResponse response){
        userMap.forEach((userId, myWebSocket) -> {
            myWebSocket.session.getAsyncRemote().sendText(gson.toJson(response));
        });
    }


}
