package com.ning.utils.http;

import java.io.File;

public  class DoPostAndFileThread extends HttpUtil{
    private String url;
    private File file;
    public DoPostAndFileThread(String url, File file) {
        this.url = url;
        this.file = file;

    }
    @Override
    public void run() {
        doPost(url,file);
    }
}
