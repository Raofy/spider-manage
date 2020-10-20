package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.entity.Link;

import java.util.List;

public interface ImgUrlService extends IService<ImgUrl> {

    int delete(List<Integer> list);
}
