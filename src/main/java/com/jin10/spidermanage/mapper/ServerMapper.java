package com.jin10.spidermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jin10.spidermanage.bean.server.OperationBody;
import com.jin10.spidermanage.entity.Link;
import com.jin10.spidermanage.entity.Server;
import com.jin10.spidermanage.vo.ServerVO;

import java.util.List;

public interface ServerMapper extends BaseMapper<Server> {

    List<ServerVO> getAll();
}
