package com.jin10.spidermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.entity.Link;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImgUrlMapper extends BaseMapper<ImgUrl> {

    int deleteByColumns(@Param("list") List<Integer> list);
}
