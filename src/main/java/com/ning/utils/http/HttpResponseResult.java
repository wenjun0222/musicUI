package com.ning.utils.http;

import com.ning.entity.ResponseResult;

public interface HttpResponseResult {
    /**
     * 请求成功时，可以在此方法里面对响应结果进行处理
     * */
    public  void success(ResponseResult responseResult);
    /**
     * 网络出现问题时，可以在此方法里面进行响应处理
     * */
    public  void error(Exception e);
}
