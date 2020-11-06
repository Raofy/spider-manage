package com.jin10.spidermanage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.server.OperationBody;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.entity.Link;
import com.jin10.spidermanage.entity.Server;
import com.jin10.spidermanage.mapper.LabelMapper;
import com.jin10.spidermanage.mapper.LinkMapper;
import com.jin10.spidermanage.mapper.ServerMapper;
import com.jin10.spidermanage.service.LinkService;
import com.jin10.spidermanage.service.ServerService;
import com.jin10.spidermanage.vo.ServerFuzzyQueryVO;
import com.jin10.spidermanage.vo.ServerVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class ServerServiceImpl extends ServiceImpl<ServerMapper, Server> implements ServerService {

    @Autowired
    private LabelMapper labelMapper;

    @Override
    public BaseResponse addElement(OperationBody body) {
        String ip = body.getServerIp();
        QueryWrapper<Server> queryWrapper = new QueryWrapper<>();
        Integer serverIp = baseMapper.selectCount(queryWrapper.eq("server_ip", ip).or().eq("server_name", body.getServerName()));
        if (serverIp >= 1) {
            return BaseResponse.error(600, "服务器IP已经存在或服务器名相同");
        }
        return BaseResponse.ok(baseMapper.insert(new Server(body)));
    }

    @Override
    public List<ServerVO> getList() {
        return baseMapper.getAll();
    }

    @Override
    public BaseResponse getAll(Integer pageSize, Integer currentPage) {
        QueryWrapper<Server> queryWrapper = new QueryWrapper<>();
        IPage<Server> serverIPage = new Page<>(currentPage, pageSize);
        IPage<Server> result = baseMapper.selectPage(serverIPage, queryWrapper);
        return BaseResponse.ok(new ServerFuzzyQueryVO(result.getRecords(), result.getPages(), result.getSize(), result.getTotal()));
    }

    @Override
    @Transactional
    public Integer deleteById(Integer id) {
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        Label label = new Label();
        label.setServerId(0);
        labelMapper.update(label, queryWrapper.eq("server_id", id));
        return baseMapper.deleteById(id);
    }

    @Override
    public BaseResponse fuzzyQuery(String condition, Integer pageSize, Integer currentPage) {
        if (StringUtils.isNotBlank(condition)) {
            QueryWrapper<Server> queryWrapper = new QueryWrapper<>();

            queryWrapper.and(wrapper ->
                            wrapper.like("server_name", condition).or().like("server_ip", condition).or().like("port", condition)
            );
            IPage<Server> serverIPage = new Page<>(currentPage, pageSize);
            IPage<Server> result = baseMapper.selectPage(serverIPage, queryWrapper);

            return BaseResponse.ok(new ServerFuzzyQueryVO(result.getRecords(), result.getPages(), result.getSize(), result.getTotal()));
        }
        return BaseResponse.error(400, "查询条件不能为空");
    }
}
