package com.jin10.spidermanage.controller;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
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
import org.springframework.util.CollectionUtils;
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
            List<User> userList = userService.list(new QueryWrapper<User>().lambda().eq(User::getId, body.getId()).or().eq(User::getName, "孙锦华"));
            if (! CollectionUtils.isEmpty(userList)) {
                List<String> members = new ArrayList();
                userList.forEach(item -> members.add(item.getIphone()));
                return BaseResponse.ok(DingTalk.ding((body.getMsg()), members));
            }
             else {
                return BaseResponse.error("没有该用户信息");
            }

        } else {
            User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getName, "孙锦华"));
            if (null != user) {
                List<String> list = new ArrayList<>();
                list.add(user.getIphone());
                return BaseResponse.ok(DingTalk.ding((body.getMsg()), list));
            }
            return BaseResponse.error("没有该用户信息");
        }
    }
}
