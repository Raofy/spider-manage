package com.jin10.spidermanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.spider.Executor;
import com.jin10.spidermanage.bean.spider.XxlJobResponse;
import com.jin10.spidermanage.service.XxlService;
import com.jin10.spidermanage.util.Resp;
import com.jin10.spidermanage.util.XxlJobUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/xxl-job")
//@CrossOrigin(origins = {"*","null"},allowCredentials="true")
public class XxlJobController {

    private static final Logger LOGGER = LoggerFactory.getLogger(XxlJobController.class);
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.executor.appname}")
    private String executorAppname;

    @Autowired
    private XxlService xxlService;

    /**
     * 删除任务
     *
     * @param id
     * @return
     * @throws
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Resp delete(Long id) {
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
     * @throws
     */
//    @RequestMapping(value = "/start", method = RequestMethod.GET)
//    public Resp start(int id) {
//        try {
//            JSONObject response = XxlJobUtil.startJob(adminAddresses, id);
//            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
//                return Resp.getInstantiationSuccess("成功", null, null);
//            } else {
//                throw new Exception("调用xxl-job-admin-start接口失败！");
//            }
//        } catch (Exception e) {
//            return Resp.getInstantiationError("失败" + e.getMessage(), null, null);
//        }
//
//    }

    /**
     * 挂起任务
     *
     * @param id
     * @return
     * @throws
     */
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public Resp stop(int id) {
        try {
            XxlJobResponse response = XxlJobUtil.stopJob(adminAddresses, id);
            if (response.getCode() == 200) {
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
     * @throws
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

    @PostMapping("/executor/save")
    public BaseResponse saveExecutor(@Validated @RequestBody Executor executor, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            log.error("参数校验异常！ ==> {}", Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return BaseResponse.error(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        return this.xxlService.saveExecutor(adminAddresses, executor);
    }

    @PostMapping("/executor/update")
    public BaseResponse updateExecutor(@Validated @RequestBody Executor executor, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            log.error("参数校验异常！ ==> {}", Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return BaseResponse.error(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        return this.xxlService.updateExecutor(adminAddresses, executor);
    }

    @GetMapping("/executor/remove/{executorId}")
    public BaseResponse deleteExecutor(@Positive(message = "ID必须为正数") @PathVariable Long executorId) throws IOException {
        return xxlService.deleteExecutor(adminAddresses, executorId);
    }
}



