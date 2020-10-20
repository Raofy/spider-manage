package com.jin10.spidermanage.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.entity.Link;
import com.jin10.spidermanage.mapper.ImgUrlMapper;
import com.jin10.spidermanage.mapper.LinkMapper;
import com.jin10.spidermanage.service.ImgUrlService;
import com.jin10.spidermanage.service.LinkService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ImgUrlServiceImpl extends ServiceImpl<ImgUrlMapper, ImgUrl> implements ImgUrlService {

    @Resource
    private ImgUrlMapper imgUrlMapper;

    @Override
    public int delete(List<Integer> list) {


        return imgUrlMapper.deleteByColumns(list);
    }
}
