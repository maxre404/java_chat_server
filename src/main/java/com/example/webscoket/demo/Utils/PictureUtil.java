package com.example.webscoket.demo.Utils;

import java.util.ArrayList;
import java.util.Random;

public class PictureUtil {
    public static ArrayList<String> picList = new ArrayList<>();
    private static Random random = new Random();

    public static String getPicture() {
        if (picList.isEmpty()){
            picList.add("https://img95.699pic.com/xsj/0k/3e/ot.jpg!/fw/700/watermark/url/L3hzai93YXRlcl9kZXRhaWwyLnBuZw/align/southeast");
            picList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqPrueY10so3GZeZNvgb1VWHjYtHdhEBV3ujI7MsDPuQ&s");
            picList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPhHtvZR7tUTlFFnnECCb5wfCWXuAXSJI7X0Ft4Wg2Rg&s");
            picList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTw_oJC_v4QygTSCOS1XgAOL7eKQEpZ4PdW2naR8wMMSg&s");
            picList.add("https://p.qqan.com/up/2022-11/202211179640228.jpg");
            picList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS43-7RXqrdBAUM4yFkVqeTCLXoLO4cc9UupADiki0wZw&s");
            picList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmYWuprPxZabYmub9rDOh5l1qSHQBiPf2KTO4QhQ5b-g&s");
            picList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwleOOHiRHpJRfvuOSIZrKG4HuyWWLmfJSZcxBDtlgvg&s");
            picList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyz_CegGqVx8LnrYjXdDdW6a-IcYTGylC0ctj9T9UJfA&s");
        }
        int index = random.nextInt(picList.size());
        return picList.get(index);
    }
}
