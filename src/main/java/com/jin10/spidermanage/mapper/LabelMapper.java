package com.jin10.spidermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.bean.label.InsertBodyTest;
import com.jin10.spidermanage.bean.label.Search;
import com.jin10.spidermanage.vo.Category;
import com.jin10.spidermanage.vo.LabelVO;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.vo.LabelVO1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelMapper extends BaseMapper<Label> {

    List<LabelVO> getAll();

    LabelVO getById(Integer id);

    int addElement(@Param("body") InsertBody body);

    List<Search> getByLabelLike(@Param("condition") String condition);

    List<LabelVO1> findLabelByCategoryId(@Param("id") int id);


    List<Category> findByCategoryId(@Param("id") int id);

}
