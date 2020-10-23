package com.jin10.spidermanage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.bean.label.InsertBodyTest;
import com.jin10.spidermanage.dto.LabelDTO;
import com.jin10.spidermanage.entity.Category;
import com.jin10.spidermanage.entity.Label;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface LabelMapper extends BaseMapper<Label> {

    List<LabelDTO> getAll();

    LabelDTO getById(Integer id);

    int addElement(@Param("body") InsertBody body);

    int addEle(@Param("body") InsertBodyTest body);
}
