package com.jin10.spidermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.bean.label.Search;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.entity.User;
import com.jin10.spidermanage.vo.LabelVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    int addElement(@Param("user") User user);

    int updateElement(@Param("user") User user);
}
