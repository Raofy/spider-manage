package com.jin10.spidermanage.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.entity.*;
import com.jin10.spidermanage.mapper.UserMapper;
import com.jin10.spidermanage.service.*;
import com.jin10.spidermanage.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public BaseResponse addOrUpdate(int updaterId, String name, String iphone) {

        if (updaterId == 0) {         //插入
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("name", name);
            User exit = baseMapper.selectOne(queryWrapper.and(wapper -> wapper.eq("iphone", iphone)));
            if (ObjectUtil.isNull(exit)) {
                User user = new User(name, iphone);
                baseMapper.insert(user);
                return BaseResponse.ok(new UserVO(user));
            }
            return BaseResponse.ok("手机已经存在");
        } else {                                 //更新
            User user = new User(updaterId, name, iphone);
            baseMapper.updateById(user);
            return BaseResponse.ok(new UserVO(user));
        }
    }
}
