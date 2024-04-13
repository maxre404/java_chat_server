package com.example.webscoket.demo;

import com.example.webscoket.demo.Utils.PictureUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main( String[] args ) {
        PictureUtil.picMap.put(Config.systemGroupId+"",PictureUtil.systemGroupUrl);
        SpringApplication.run ( DemoApplication.class , args );
    }
}
