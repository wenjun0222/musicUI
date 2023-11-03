package com.ning.utils.http;

import com.alibaba.fastjson.JSON;
import com.ning.common_component.NetworkErrorPanel;
import com.ning.entity.ResponseResult;
import com.ning.entity.query.UserQuery;
import com.ning.ui.LoginUI;
import com.ning.ui.main.RightDownPanel;
import com.ning.utils.UserUtil;
import com.ning.utils.WindowDispose;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  class HttpUtil extends Thread implements HttpResponseResult {
    private List<String> urlList=new ArrayList<>(Arrays.asList("user/login","user/sendCode","user/register","user/updatePwdByAccount"));
    private RightDownPanel rightDownPanel=RightDownPanel.getRightDownPanel();
    NetworkErrorPanel networkErrorPanel=new NetworkErrorPanel();
    private UserQuery userQuery= UserUtil.getUser();
    private final static String BASE_PATH="http://localhost:10000/";
    /**
     * post请求,发送实体类型
     * */
    protected void doPost(String url, Object entity){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        try{
            HttpPost httpPost=new HttpPost(BASE_PATH+url);
            for (String url1 : urlList) {
                if((!url.equals(url1))||(!url.contains(url1))){
                    httpPost.addHeader("token", userQuery.getToken());
                    httpPost.addHeader("id", userQuery.getId() + "");
                    break;
                }
            }
            //将实体类转化为特定的网络实体类，并放入post请求包中
            String json= JSON.toJSONString(entity);
            StringEntity stringEntity=new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            //开始发送post请求，并且将返回结果
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String s = EntityUtils.toString(httpEntity, "utf-8");
            ResponseResult responseResult = JSON.parseObject(s, ResponseResult.class);
            if(responseResult.code==404){
                LoginUI loginUI = LoginUI.getLoginUI();
                loginUI.loginUIInit();
                WindowDispose.mainUIDispose();
            }else {
                success(responseResult);
            }
        }catch (Exception e){
            error(e);
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * get请求，没有参数
     * */
    protected void doGet(String url){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        try{
            HttpGet httpGet=new HttpGet(BASE_PATH+url);
            for (String url1 : urlList) {
                if((!url.equals(url1))||(!url.contains(url1))){
                    httpGet.addHeader("token", userQuery.getToken());
                    httpGet.addHeader("id", userQuery.getId() + "");
                    break;
                }
            }
            //开始发送get请求，并且将返回结果
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String s = EntityUtils.toString(httpEntity, "utf-8");
            ResponseResult responseResult = JSON.parseObject(s, ResponseResult.class);
            if(responseResult.code==404){
                LoginUI loginUI = LoginUI.getLoginUI();
                loginUI.loginUIInit();
                WindowDispose.mainUIDispose();
            }else {
                success(responseResult);
            }
        }catch (Exception e){
            error(e);
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * post请求,发送file文件
     * */
    protected void doPost(String url, File file){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        try{
            HttpPost httpPost=new HttpPost(BASE_PATH+url);
            //将文件类转化为特定的网络文件类，并放入post请求包中
            MultipartEntityBuilder multipartEntityBuilder=MultipartEntityBuilder.create();
            multipartEntityBuilder.addBinaryBody("file",file);
            HttpEntity fileEntity=multipartEntityBuilder.build();
            httpPost.setEntity(fileEntity);
            //开始发送post请求，并且将返回结果
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String s = EntityUtils.toString(httpEntity, "utf-8");
            ResponseResult responseResult = JSON.parseObject(s, ResponseResult.class);
            success(responseResult);
        }catch (Exception e){
            error(e);
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void success(ResponseResult responseResult) {
    }
    @Override
    public void error(Exception e) {
        rightDownPanel.changePanel(networkErrorPanel);
    }
}
