package com.ning.utils.http;

public  class DoGetThread extends HttpUtil{
    private String url;
    public DoGetThread(String url){
        this.url=url;
    }
    @Override
    public void run() {
        doGet(url);
    }
}
