package com.ning.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class Url {
    public static URL getUrl(String url){
        URL url1=null;
        try {
            url1=new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url1;
    }
}
