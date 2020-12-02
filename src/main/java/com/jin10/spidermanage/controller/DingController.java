package com.jin10.spidermanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.dingtalk.DingTalkWarn;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.entity.User;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.service.UserService;
import com.jin10.spidermanage.util.DingTalk;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dingTalk")
public class DingController {

    @Autowired
    private UserService userService;

    /**
     * 钉钉告警
     *
     * @param body
     * @return
     */
    @PostMapping("/warn")
    public BaseResponse dingTalkWarning(@RequestBody DingTalkWarn body) {
        if (body.getId() > 0 && StringUtils.isNotBlank(body.getMsg())) {
            User result = userService.getOne(new QueryWrapper<User>().eq("id", body.getId()));
            if (null != result) {
                String iphone = result.getIphone();
                List<String> members = new ArrayList();
                members.add(iphone);
                Object ding = DingTalk.ding(body.getMsg(), members);
                return BaseResponse.ok(ding);
            } else {
                return BaseResponse.error("没有该用户信息");
            }

        }
        return BaseResponse.error("参数为空，请校验参数");
    }
}
