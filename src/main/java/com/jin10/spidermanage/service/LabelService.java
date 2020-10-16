package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.entity.Label;

import java.util.List;

public interface LabelService extends IService<Label> {

    int add(Integer cid, String name);

    List<Label> getAll();

    int delete(Integer id);

    int update(Integer id, String newName);

    Label getById(Integer id);


}
