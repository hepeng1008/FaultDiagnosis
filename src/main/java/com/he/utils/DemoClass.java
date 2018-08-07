package com.he.utils;

public class DemoClass {

    public static void main(String[] args) {
        Comet comet = new Comet();
        comet.setUserId("1");//前端到session中的用户id

        comet.setMsgCount(String.valueOf("hello world!"));
        comet.setMsgData(null);

        new CometUtil().pushTo(comet);


    }
}
