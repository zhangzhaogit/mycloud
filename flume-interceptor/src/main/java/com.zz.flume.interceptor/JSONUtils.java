package com.zz.flume.interceptor;/**
 * @Author zhangzhao
 * @Date 2022/6/30 21:20
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2022-06-30 21:20 
 **/
public class JSONUtils {
    public static boolean isJSONValidate(String log){
        try {
            JSON.parse(log);
            return true;
        }catch (JSONException e){
            return false;
        }
    }
}