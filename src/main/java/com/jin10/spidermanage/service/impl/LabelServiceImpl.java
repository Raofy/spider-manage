package com.jin10.spidermanage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.dto.LabelDTO;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.entity.Label;

import com.jin10.spidermanage.entity.Link;
import com.jin10.spidermanage.mapper.LabelMapper;
import com.jin10.spidermanage.service.ImgUrlService;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Resource
    private LabelMapper labelMapper;

    @Autowired
    private LinkService linkService;

    @Autowired
    private ImgUrlService imgUrlService;

    @Override
    @Transactional
    public Boolean add(InsertBody body) {
        labelMapper.addElement(body);
        ArrayList<Link> linkList = new ArrayList<>();
        ArrayList<ImgUrl> imgList = new ArrayList<>();
        boolean link = true;
        boolean img = true;
        if (body.getSpiderLink().size() > 0) {
            for (int i = 0; i < body.getSpiderLink().size(); i++) {
                linkList.add(new Link(body.getLid(), body.getSpiderLink().get(i)));
                link = linkService.saveOrUpdateBatch(linkList);
            }
        }
        if (body.getImg().size() > 0) {
            for (int i = 0; i < body.getImg().size(); i++) {
                imgList.add(new ImgUrl(body.getLid(), body.getImg().get(i)));
                img = imgUrlService.saveOrUpdateBatch(imgList);
            }
        }
        return link && img;
    }

    @Override
    public List<LabelDTO> getAll() {
        return labelMapper.getAll();
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        labelMapper.deleteById(id);
        linkService.remove(new QueryWrapper<Link>().eq("label_id", id));
        imgUrlService.remove(new QueryWrapper<ImgUrl>().eq("label_id", id));
        return true;
    }

    @Override
    @Transactional
    public Boolean updateLabel(InsertBody body) {
        Label label = new Label(body);
        labelMapper.updateById(label);
        linkService.remove(new QueryWrapper<Link>().eq("label_id", body.getLid()));
        imgUrlService.remove(new QueryWrapper<ImgUrl>().eq("label_id", body.getLid()));
        ArrayList<Link> linkList = new ArrayList<>();
        ArrayList<ImgUrl> imgList = new ArrayList<>();
        boolean link = true;
        boolean img = true;
        if (body.getSpiderLink().size() > 0) {
            for (int i = 0; i < body.getSpiderLink().size(); i++) {
                linkList.add(new Link(body.getLid(), body.getSpiderLink().get(i)));
                link = linkService.saveOrUpdateBatch(linkList);
            }
        }
        if (body.getImg().size() > 0) {
            for (int i = 0; i < body.getImg().size(); i++) {
                imgList.add(new ImgUrl(body.getLid(), body.getImg().get(i)));
                img = imgUrlService.saveOrUpdateBatch(imgList);
            }
        }
        return link && img;
    }


    @Override
    public LabelDTO getById(Integer id) {
        return labelMapper.getById(id);
    }
}
