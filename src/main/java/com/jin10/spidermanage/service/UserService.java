package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.bean.label.Search;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.entity.User;
import com.jin10.spidermanage.vo.LabelVO;
import com.jin10.spidermanage.vo.UserVO;

import java.io.IOException;
import java.util.List;

public interface UserService extends IService<User> {

    BaseResponse addOrUpdate(int updaterId, String name, String iphone);

}
