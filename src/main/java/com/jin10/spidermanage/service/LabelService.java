package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.bean.label.InsertBodyTest;
import com.jin10.spidermanage.bean.label.Search;
import com.jin10.spidermanage.vo.LabelVO;
import com.jin10.spidermanage.entity.Label;

import java.util.List;

public interface LabelService extends IService<Label> {

    BaseResponse add(InsertBody body);

    BaseResponse addKV(InsertBodyTest body);

    List<LabelVO> getAll();

    boolean delete(Integer id, Integer eid);

    BaseResponse updateLabel(InsertBody body);

    LabelVO getById(Integer id);


    List<Search> getLabelByCondition(String condition);




}
