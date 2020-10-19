package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.dto.LabelDTO;
import com.jin10.spidermanage.entity.Label;

import java.util.List;

public interface LabelService extends IService<Label> {

    int add(InsertBody body);

    List<LabelDTO> getAll();

    int delete(Integer id);

    int update(Integer id, String newName);

    LabelDTO getById(Integer id);


}
