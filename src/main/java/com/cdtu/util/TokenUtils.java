package com.cdtu.util;

import java.util.UUID;

public class TokenUtils {
    static public String getToken() {
        return UUID.randomUUID().toString();//随机获取一个不重复的id值
    }
}
