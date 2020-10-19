package com.jin10.spidermanage.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.dto.LabelDTO;
import com.jin10.spidermanage.entity.Label;

import com.jin10.spidermanage.mapper.LabelMapper;
import com.jin10.spidermanage.service.LabelService;
import org.springframework.stereotype.Service;
;
import javax.annotation.Resource;
import java.util.List;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Resource
    private LabelMapper labelMapper;

    @Override
    public int add(InsertBody body) {
        return labelMapper.addElement(body);
    }

    @Override
    public List<LabelDTO> getAll() {
        return labelMapper.getAll();
    }

    @Override
    public int delete(Integer id) {
        return labelMapper.deleteById(id);
    }

    @Override
    public int update(Integer id, String newName) {
        return labelMapper.updateById(new Label().setId(id).setLabelName(newName));
    }

    @Override
    public LabelDTO getById(Integer id) {
        return labelMapper.getById(id);
    }
}
