package com.jin10.spidermanage.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.entity.Link;
import com.jin10.spidermanage.mapper.LabelMapper;
import com.jin10.spidermanage.mapper.LinkMapper;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

}
