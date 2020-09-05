package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.SortedMap;

@Slf4j
public class SignUtil {
    /**
     * @param params 所有的请求参数都会在这里进行排序加密
     * @return 验证签名结果
     */
    public static boolean verifySign(SortedMap<String, String> params) {

        String urlSign = params.get("sign");
        log.info("Url Sign : {}", urlSign);
        if (params == null || StringUtils.isEmpty(urlSign)) {
            return false;
        }
        //把参数加密
        String paramsSign = getParamsSign(params);
        log.info("Param Sign : {}", paramsSign);
        return !StringUtils.isEmpty(paramsSign) && urlSign.equals(paramsSign);
    }

    /**
     * @param params 所有的请求参数都会在这里进行排序加密
     * @return 得到签名
     */
    public static String getParamsSign(SortedMap<String, String> params) {
        params.remove("sign");
        String paramsJsonStr = JSONObject.toJSONString(params);
        paramsJsonStr += Constants.SALT;
        return DigestUtils.md5DigestAsHex(paramsJsonStr.getBytes()).toUpperCase();
    }

}
