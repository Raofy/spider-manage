package com.jin10.spidermanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.util.Resp;
import com.jin10.spidermanage.util.TimeUtil;
import com.jin10.spidermanage.util.XxlJobUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/xxl-job")
public class XxlJobController {

    private static final Logger LOGGER = LoggerFactory.getLogger(XxlJobController.class);
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.executor.appname}")
    private String executorAppname;

    //@ApiOperation(value = "添加jobInfo并启动", httpMethod = "GET")
    @GetMapping
    public Resp saveXxl() {
        //查询列表数据
        try {
            JSONObject requestInfo = new JSONObject();
            // 执行器主键ID
            requestInfo.put("jobGroup", 2);
            // 任务执行CRON表达式
            long etime1 = System.currentTimeMillis() + 1 * 60 * 1000;//延时函数，单位毫秒，这里是延时了1分钟
            String date = TimeUtil.getCron(new Date(etime1));
            System.out.println(date);
//        requestInfo.put("jobCron","0 0/1 * * * ?");
            requestInfo.put("jobCron", date);
            // 任务描述
            requestInfo.put("jobDesc", "xxxJob");

            // 负责人
            requestInfo.put("author", "admin");
            // 报警邮件
            requestInfo.put("alarmEmail", "xxx@satcloud.com.cn");

            // 执行器路由策略
            requestInfo.put("executorRouteStrategy", "FIRST");
            // 执行器，任务Handler名称
            requestInfo.put("executorHandler", "xxxJobHandler");
            // todo 执行器，任务参数
            requestInfo.put("executorParam", "测试202006300943");
            // 阻塞处理策略
            requestInfo.put("executorBlockStrategy", "SERIAL_EXECUTION");
            // 任务执行超时时间，单位秒
            requestInfo.put("executorTimeout", 0);
            // 失败重试次数
            requestInfo.put("executorFailRetryCount", 1);
            // GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
            requestInfo.put("glueType", "BEAN");
            // GLUE备注
            requestInfo.put("glueRemark", "GLUE代码初始化");

            // 调度状态：0-停止，1-运行
            requestInfo.put("triggerStatus", 0);
            // 上次调度时间
            requestInfo.put("triggerLastTime", 0);
            // 下次调度时间
            requestInfo.put("triggerNextTime", 0);
//        requestInfo.put("cronGen_display","0 0/1 * * * ?");
            JSONObject response = XxlJobUtil.addJob(adminAddresses, requestInfo);
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                //修改任务参数 把id放入
                // 执行器主键ID
                requestInfo.put("executorParam", "JobId=" + response.get("content") + ";测试202006300943");
                requestInfo.put("id", Integer.valueOf(response.get("content").toString()));
                JSONObject responseUpdate = XxlJobUtil.updateJob(adminAddresses, requestInfo);
                if (responseUpdate.containsKey("code") && 200 == (Integer) responseUpdate.get("code")) {
                    //加入任务成功之后直接启动
                    JSONObject responseStart = XxlJobUtil.startJob(adminAddresses, Integer.valueOf(response.get("content").toString()));
                    if (responseStart.containsKey("code") && 200 == (Integer) responseStart.get("code")) {
                        return Resp.getInstantiationSuccess("成功", null, null);
                    } else {
                        throw new Exception("调用xxl-job-admin-start接口失败！");
                    }
                } else {
                    throw new Exception("调用xxl-job-admin-update接口失败！");
                }
            } else {
                throw new Exception("调用xxl-job-admin-add接口失败！");
            }
        } catch (Exception e) {
            return Resp.getInstantiationError("失败" + e.getMessage(), null, null);
        }
    }

    /**
     * 删除任务
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Resp delete(int id) {
        try {
            JSONObject response = XxlJobUtil.deleteJob(adminAddresses, id);
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                return Resp.getInstantiationSuccess("成功", null, null);
            } else {
                throw new Exception("调用xxl-job-admin-delete接口失败！");
            }
        } catch (Exception e) {
            return Resp.getInstantiationError("失败" + e.getMessage(), null, null);
        }

    }

    /**
     * 开始任务
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public Resp start(int id) {
        try {
            JSONObject response = XxlJobUtil.startJob(adminAddresses, id);
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                return Resp.getInstantiationSuccess("成功", null, null);
            } else {
                throw new Exception("调用xxl-job-admin-start接口失败！");
            }
        } catch (Exception e) {
            return Resp.getInstantiationError("失败" + e.getMessage(), null, null);
        }

    }

    /**
     * 挂起任务
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public Resp stop(int id) {
        try {
            JSONObject response = XxlJobUtil.stopJob(adminAddresses, id);
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                return Resp.getInstantiationSuccess("成功", null, null);
            } else {
                throw new Exception("调用xxl-job-admin-stop接口失败！");
            }
        } catch (Exception e) {
            return Resp.getInstantiationError("失败" + e.getMessage(), null, null);
        }
    }

    /**
     * 登陆
     *
     * @param userName
     * @param password
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Resp login(String userName, String password) {
        try {
            String cookie = XxlJobUtil.login(adminAddresses, userName, password);
            if (StringUtils.isNotBlank(cookie)) {
                return Resp.getInstantiationSuccess("成功", null, null);
            } else {
                throw new Exception("调用xxl-job-admin-login接口失败！");
            }
        } catch (Exception e) {
            return Resp.getInstantiationError("失败" + e.getMessage(), null, null);
        }
    }

    /**
     * 根据xxl-appname获取对应id
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getAppNameIdByAppname", method = RequestMethod.GET)
    public Resp getAppNameIdByAppname() {
        try {
            JSONObject response = XxlJobUtil.getAppNameIdByAppname(adminAddresses, executorAppname);
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                return Resp.getInstantiationSuccess("成功", null, null);
            } else {
                throw new Exception("调用xxl-job-admin-getAppNameIdByAppname接口失败！");
            }
        } catch (Exception e) {
            return Resp.getInstantiationError("失败" + e.getMessage(), null, null);
        }
    }
}



