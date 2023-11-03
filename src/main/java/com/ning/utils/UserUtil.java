package com.ning.utils;

import com.alibaba.fastjson.JSON;
import com.ning.entity.query.UserQuery;

import java.io.*;

public class UserUtil {
    private final static String userFilePath="C:\\Users\\ning\\Desktop\\";
    public static void insertUser(UserQuery userQuery) {
        BufferedWriter bufferedWriter=null;
        try {
            String userStr=JSON.toJSONString(userQuery);
            System.out.println(userStr);
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(userFilePath+"User.txt")),"GBK"));
            bufferedWriter.write(userStr,0,userStr.length());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedWriter!=null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static UserQuery getUser(){
        BufferedReader bufferedReader=null;
        UserQuery userQuery=null;
        try{
            bufferedReader=new BufferedReader(new InputStreamReader
                    (new FileInputStream(new File(userFilePath+"User.txt")),"GBK"));
            String userStr=bufferedReader.readLine();
            userQuery=JSON.parseObject(userStr,UserQuery.class);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader!=null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userQuery;
    }

    public static void main(String[] args) {
        UserQuery user = getUser();
        System.out.println(user);
    }
}
