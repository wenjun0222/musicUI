package com.ning.entity;

import java.io.Serializable;

public class ResponseResult<Data> implements Serializable {
    public Data data;
    public  String message;
    public  int code;
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
