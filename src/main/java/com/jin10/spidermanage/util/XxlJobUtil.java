package com.jin10.spidermanage.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.bean.spider.ExecutorList;
import com.jin10.spidermanage.bean.spider.XxlJobResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.util.*;

@Component
public class XxlJobUtil {

    public static Logger logger = LoggerFactory.getLogger(XxlJobUtil.class);

    private static String cookie = "";

    /**
     * 添加调度执行器
     *
     * @return
     */
    public static XxlJobResponse addExecutor(String url, Map<String, String> mapData) throws IOException {
        String path = "/jobgroup/save";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Cookie", cookie);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (mapData.size() != 0) {
            Set keySet = mapData.keySet();
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String k = it.next().toString();// key
                Object v = mapData.get(k);      // value
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
     * 更新调度执行器
     *
     * @return
     */
    public static XxlJobResponse updateExecutor(String url, Map<String, String> mapData) throws IOException {
        String path = "/jobgroup/update";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Cookie", cookie);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (mapData.size() != 0) {
            Set keySet = mapData.keySet();
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String k = it.next().toString();// key
                Object v = mapData.get(k);      // value
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
     * 删除调度器
     *
     * @param url
     * @param id
     * @return
     * @throws IOException
     */
    public static XxlJobResponse deleteExecutor(String url, Long id) throws IOException {
        String path = "/jobgroup/save";
        return doGetRequest(url, path);

    }

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
        httpPost.addHeader("Cookie", cookie);
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
     * 更新任务
     *
     * @param url
     * @param mapdata
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static XxlJobResponse updateJob(String url, Map<String, String> mapdata) throws HttpException, IOException {
        String path = "/jobinfo/update";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Cookie", cookie);
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
    public static JSONObject deleteJob(String url, Long id) throws HttpException, IOException {
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
    public static XxlJobResponse startJob(String url, int id) throws HttpException, IOException {
        String path = "/jobinfo/start?id=" + id;
        XxlJobResponse response = doGetRequest(url, path);
        logger.info("开启任务" + response.toString());
        return response;
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
    public static XxlJobResponse stopJob(String url, int id) throws HttpException, IOException {
        String path = "/jobinfo/stop?id=" + id;
        XxlJobResponse response = doGetRequest(url, path);
        logger.info("停止任务" + response.toString());
        return response;
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

    /**
     * 触发执行一次调度任务
     *
     * @param url
     * @param mapdata
     * @return
     * @throws IOException
     */
    public static XxlJobResponse triggerJob(String url, Map<String, String> mapdata) throws IOException {
        String path = "/jobinfo/trigger";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Cookie", cookie);
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
        httpPost.addHeader("Cookie", cookie);
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 响应的结果
            String content = EntityUtils.toString(entity, "UTF-8");
            logger.info("添加执行器返回码：" + content);
        }

    }

    /**
     * Get请求
     *
     * @param url
     * @param path
     * @return
     * @throws HttpException
     * @throws IOException
     */
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

    public static XxlJobResponse doGetRequest(String url, String path) throws HttpException, IOException {
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        HttpMethod get = new GetMethod(targetUrl);
        get.setRequestHeader("cookie", cookie);
        httpClient.executeMethod(get);
        InputStream inputStream = get.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        XxlJobResponse response = new XxlJobResponse();
        String str = "";
        while ((str = br.readLine()) != null) {
            stringBuffer.append(str);
        }
        if (get.getStatusCode() == 200) {
            response = JSONObject.parseObject(stringBuffer.toString(), XxlJobResponse.class);
        } else {
            try {
                response = JSONObject.parseObject(stringBuffer.toString(), XxlJobResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
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
            result = JSONObject.parseObject(stringBuffer.toString());
        } else {
            try {
                result = JSONObject.parseObject(stringBuffer.toString());
            } catch (Exception e) {
                result.put("error", stringBuffer.toString());
            }
        }
        return result;
    }

    /**
     * 登录xxl-job后台管理界面
     *
     * @param url
     * @param userName
     * @param password
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static String login(String url, String userName, String password) throws HttpException, IOException {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("userName", userName);
        requestBody.put("password", password);
        HttpResponse admin = HttpRequest.post(url+"/login").form(requestBody).execute();
        StringBuffer tmpCookies = new StringBuffer();
        admin.getCookies().forEach(item -> tmpCookies.append(item.getName() + "=" + item.getValue() + ";"));
        cookie = tmpCookies.toString();
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
        httpPost.addHeader("Cookie", cookie);
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

    /**
     * 获取已注册执行器列表
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static ExecutorList executorList(String url) throws IOException {
        String path = "/jobgroup/pageList";
        String targetUrl = url + path;
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Cookie", cookie);
        response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String content = EntityUtils.toString(entity, "UTF-8");
            logger.info("初始执行器列表"+content);
            ExecutorList executorList = JSONObject.parseObject(content, ExecutorList.class);
            return executorList;
        }
        return null;
    }

    /**
     * 清理xxl-job产生的调度日志文件
     *
     * @param url
     * @param jobGroup
     * @param jobId
     * @param type
     * @throws IOException
     */
    public static void clearXxlJobLog(String url, int jobGroup, int jobId, int type) throws IOException {
        String path = "/joblog/clearLog";
        String targetUrl = url + path;
        HashMap<String, Integer> mapdata = new HashMap<>();
        mapdata.put("jobGroup", jobGroup);
        mapdata.put("jobId", jobId);
        mapdata.put("type", type);
        logger.info("请求地址：" + targetUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(targetUrl);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Cookie", cookie);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (mapdata.size() != 0) {
            // 将mapdata中的key存在set集合中，通过迭代器取出所有的key，再获取每一个键对应的值
            Set keySet = mapdata.keySet();
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String k = it.next().toString();// key
                String v = mapdata.get(k).toString();// value
                nameValuePairs.add(new BasicNameValuePair(k,  v));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        httpClient.execute(httpPost);
    }


    /**
     * 检测xxl-job是否意外停止调度
     */
    public static void testXxlJobResponse(String url) {
        try {
            Map map = new HashMap<String, Integer>();
            map.put("id", 163);
            HttpResponse execute = HttpRequest.post(url + "/jobinfo/start").cookie(cookie).timeout(10000).form(map).execute();
            logger.info("检测xxl-job是否没有响应" + execute.body());
        } catch (Exception e) {
            logger.info(e.getMessage());
            if ("Read timed out".equals(e.getMessage())) {
                List<String> phone = Arrays.asList("17865313385", "15986941493");
                DingTalk.ding("xxl-job请求没响应", phone);
            }
        }

    }
}
