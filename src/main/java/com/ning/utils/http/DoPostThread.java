package com.ning.utils.http;

public  class DoPostThread extends HttpUtil {
    private String url;
    private Object entity;
    public DoPostThread(String url, Object entity) {
        this.url = url;
        this.entity = entity;

    }
    @Override
    public void run() {
        doPost(url,entity);
    }
}

