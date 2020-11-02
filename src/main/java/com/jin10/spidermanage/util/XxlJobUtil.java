package com.jin10.spidermanage.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.spider.ExecutorList;
import com.jin10.spidermanage.bean.spider.XxlJobResponse;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class XxlJobUtil {

    public static Logger logger = LoggerFactory.getLogger(XxlJobUtil.class);

    private static String cookie = "";

    /**
     * 新增/编辑任务
     *
     * @param url
     * @param
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static XxlJobResponse addJob(String url, Map<String, String> mapdata) throws HttpException, IOException {
        String path = "/jobinfo/add";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (mapdata.size() != 0) {
            Set keySet = mapdata.keySet();
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String k = it.next().toString();// key
                Object v = mapdata.get(k);      // value
                nameValuePairs.add(new BasicNameValuePair(k, (String) v));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应的结果
            String content = EntityUtils.toString(entity, "UTF-8");
            return JSONObject.parseObject(content, XxlJobResponse.class);
        }
        return null;
    }

    /**
     * 新增/编辑任务
     *
     * @param requestInfo
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject addJob(Map<String, String> requestInfo) throws HttpException, IOException {
        String targetUrl = "http://127.0.0.1:9090/xxl-job-admin/jobinfo/add";
        logger.info("请求地址：" + targetUrl);
        HashMap<String, String> header = new HashMap<>();
        String result = HttpClientUtils.doPostRequest(targetUrl, header, null, new StringEntity(JSON.toJSONString(requestInfo), ContentType.APPLICATION_FORM_URLENCODED));
        System.out.println("result:" + result);
        return (JSONObject) JSON.toJSON(result);
    }

    /**
     * 新增/编辑任务
     *
     * @param url
     * @param requestInfo
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject addJob(String url, JSONObject requestInfo) throws HttpException, IOException {
        String path = "/jobinfo/add";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(targetUrl);
        RequestEntity requestEntity = new StringRequestEntity(requestInfo.toString(), "application/x-www-form-urlencoded", "utf-8");
        post.setRequestEntity(requestEntity);
        httpClient.executeMethod(post);
        JSONObject result = new JSONObject();
        result = getJsonObject(post, result);
        return result;
    }

    public static XxlJobResponse updateJob(String url, Map<String, String> mapdata) throws HttpException, IOException {
        String path = "/jobinfo/update";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (mapdata.size() != 0) {
            // 将mapdata中的key存在set集合中，通过迭代器取出所有的key，再获取每一个键对应的值
            Set keySet = mapdata.keySet();
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String k = it.next().toString();// key
                Object v = mapdata.get(k);// value
                nameValuePairs.add(new BasicNameValuePair(k, (String) v));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应的结果
            String content = EntityUtils.toString(entity, "UTF-8");
            return JSONObject.parseObject(content, XxlJobResponse.class);
        }
        return null;
    }

    /**
     * 删除任务
     *
     * @param url
     * @param id
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject deleteJob(String url, int id) throws HttpException, IOException {
        String path = "/jobinfo/remove?id=" + id;
        return doGet(url, path);
    }

    /**
     * 开始任务
     *
     * @param url
     * @param id
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject startJob(String url, int id) throws HttpException, IOException {
        String path = "/jobinfo/start?id=" + id;
        return doGet(url, path);
    }

    /**
     * 停止任务
     *
     * @param url
     * @param id
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject stopJob(String url, int id) throws HttpException, IOException {
        String path = "/jobinfo/stop?id=" + id;
        return doGet(url, path);
    }

    /**
     * 根据xxl-appname获取对应id
     *
     * @param url
     * @param appnameParam
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject getAppNameIdByAppname(String url, String appnameParam) throws HttpException, IOException {
        String path = "/jobgroup/getAppNameIdByAppname?appnameParam=" + appnameParam;
        return doGet(url, path);
    }


    public static XxlJobResponse triggerJob(String url, Map<String, String> mapdata) throws IOException {
        String path = "/jobinfo/trigger";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (mapdata.size() != 0) {
            // 将mapdata中的key存在set集合中，通过迭代器取出所有的key，再获取每一个键对应的值
            Set keySet = mapdata.keySet();
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String k = it.next().toString();
                Object v = mapdata.get(k);
                nameValuePairs.add(new BasicNameValuePair(k, (String) v));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        //callback(url);
        if (entity != null) {
            // 响应的结果
            String content = EntityUtils.toString(entity, "UTF-8");
            return JSONObject.parseObject(content, XxlJobResponse.class);
        }
        return null;
    }

    public static void callback(String url) throws IOException {
        String path = "/api/callback";
        String targetUrl = url + path;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应的结果
            String content = EntityUtils.toString(entity, "UTF-8");
            logger.info("添加执行器返回码：" + content);
        }

    }
    public static JSONObject doGet(String url, String path) throws HttpException, IOException {
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        HttpMethod get = new GetMethod(targetUrl);
        get.setRequestHeader("cookie", cookie);
        httpClient.executeMethod(get);
        JSONObject result = new JSONObject();
        result = getJsonObject(get, result);
        return result;
    }

    private static JSONObject getJsonObject(HttpMethod get, JSONObject result) throws IOException {
        InputStream inputStream = get.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String str = "";
        while ((str = br.readLine()) != null) {
            stringBuffer.append(str);
        }
        if (get.getStatusCode() == 200) {
            /**
             *  使用此方式会出现
             *  Going to buffer response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.
             *  异常
             *  String responseBodyAsString = get.getResponseBodyAsString();
             *  result = JSONObject.parseObject(responseBodyAsString);*/
            result = JSONObject.parseObject(stringBuffer.toString());
        } else {
            try {
//                result = JSONObject.parseObject(get.getResponseBodyAsString());
                result = JSONObject.parseObject(stringBuffer.toString());
            } catch (Exception e) {
                result.put("error", stringBuffer.toString());
            }
        }
        return result;
    }


    public static String login(String url, String userName, String password) throws HttpException, IOException {
        String path = "/jobinfo/login?userName=" + userName + "&password=" + password;
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        HttpMethod get = new GetMethod(targetUrl);
        httpClient.executeMethod(get);
        if (get.getStatusCode() == 200) {
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
            }
            cookie = tmpcookies.toString();
        } else {
            try {
                cookie = "";
            } catch (Exception e) {
                cookie = "";
            }
        }
        return cookie;
    }

    /**
     * 注册保存执行器
     *
     * @param url
     * @param mapdata
     * @throws IOException
     */
    public static void jobGroupSave(String url, Map<String, String> mapdata) throws IOException {
        String path = "/jobgroup/save";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (mapdata.size() != 0) {
            // 将mapdata中的key存在set集合中，通过迭代器取出所有的key，再获取每一个键对应的值
            Set keySet = mapdata.keySet();
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String k = it.next().toString();// key
                Object v = mapdata.get(k);// value
                nameValuePairs.add(new BasicNameValuePair(k, (String) v));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应的结果
            String content = EntityUtils.toString(entity, "UTF-8");
            logger.info("添加执行器返回码：" + content);
        }
    }

    public static ExecutorList executorList(String url) throws IOException {
        String path = "/jobgroup/pageList";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);

        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//        httpPost.addHeader("Cookie", cookie);
        response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应的结果
            String content = EntityUtils.toString(entity, "UTF-8");
            logger.info("初始执行器列表"+content);
            ExecutorList executorList = JSONObject.parseObject(content, ExecutorList.class);
            Iterator<ExecutorList.DataBean> iterator = executorList.getData().iterator();
            while (iterator.hasNext()) {
                ExecutorList.DataBean next = iterator.next();
                if (ObjectUtils.isNull(next.getAddressList())) {
                    iterator.remove();
                }
            }
            return executorList;
        }

        return null;
    }
}
