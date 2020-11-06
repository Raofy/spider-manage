package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.server.OperationBody;
import com.jin10.spidermanage.entity.Link;
import com.jin10.spidermanage.entity.Server;
import com.jin10.spidermanage.vo.ServerVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ServerService extends IService<Server> {

    BaseResponse addElement(OperationBody body);

    List<ServerVO> getList();

    BaseResponse getAll(Integer pageSize, Integer currentPage);

    Integer deleteById(Integer id);

    BaseResponse fuzzyQuery(String condition, Integer pageSize, Integer currentPage);

}
