package com.jin10.spidermanage.controller;

import com.alibaba.fastjson.JSON;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.util.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/login")
//@CrossOrigin(origins = {"*","null"},allowCredentials="true")
public class LoginController {

    @GetMapping("/getUserInfo")
    public BaseResponse getUserInfo(@RequestParam(value = "appName") String appName, @RequestParam(value = "expand", required = false) Integer expand, HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        if (StringUtils.isNotBlank(appName)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                StringBuffer cookieStr = new StringBuffer();
                for (Cookie cookie : cookies) {
                    cookieStr.append(String.format("%s=%s;", cookie.getName(), cookie.getValue()));
                }
                HashMap<String, String> header = new HashMap<>();
                header.put("x-app-id", "Fg451yNoEKr3PHzA");
                header.put("x-version", "1.0.0");
                header.put("Cookie", cookieStr.toString());
                HashMap<String, String> params = new HashMap<>();
                params.put("appName", appName);
                params.put("expand", String.valueOf(expand));
                String s = HttpClientUtils.doGetRequest("http://bb946289468048559c892519b50a81b7.z3c.jin10.com/getUserinfo/front", header, params);
                return JSON.toJavaObject(JSON.parseObject(s), BaseResponse.class);
            }
        }
        return BaseResponse.error("重新登陆");
    }
}
