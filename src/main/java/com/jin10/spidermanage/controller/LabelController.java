package com.jin10.spidermanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.entity.Link;
import com.jin10.spidermanage.service.ImgUrlService;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.service.LinkService;
import com.jin10.spidermanage.util.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private ImgUrlService imgUrlService;

    @GetMapping("/all")
    public BaseResponse getAll() {
        return BaseResponse.ok(labelService.getAll());
    }

    @GetMapping("/get/{id}")
    public BaseResponse getById(@PathVariable("id") Integer id) {
        return BaseResponse.ok(labelService.getById(id));
    }


    @PostMapping("/add")
    public BaseResponse add(@RequestBody InsertBody body) {
        int add = labelService.add(body);    //返回主键ID
        ArrayList<Link> linkList = new ArrayList<>();
        ArrayList<ImgUrl> imgList = new ArrayList<>();
        for (int i = 0; i < body.getSpiderLink().size(); i++) {
            body.getSpiderLink().get(i).setLabelId(body.getId());
        }
        for (int i = 0; i < body.getImg().size(); i++) {
            body.getImg().get(i).setLabelId(body.getId());
        }
        boolean link = linkService.saveOrUpdateBatch(linkList);
        boolean img = imgUrlService.saveOrUpdateBatch(imgList);
        return BaseResponse.ok(link && img);
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody InsertBody body) {
        Label label = new Label(body);
        labelService.updateById(label);
        linkService.updateBatchById(body.getSpiderLink());
        imgUrlService.updateBatchById(body.getImg());
        return BaseResponse.ok("更新完毕");
    }

    @GetMapping("/delete/{id}")
    public BaseResponse delete(@PathVariable("id") Integer id) {
        QueryWrapper<ImgUrl> imgUrlQueryWrapper = new QueryWrapper<>();
        boolean imgRemove = imgUrlService.remove(imgUrlQueryWrapper.eq("label_id", id));
        return BaseResponse.ok(labelService.delete(id));
    }

    //单个文件的上传
    @PostMapping("/uploads")
    public BaseResponse handleFileUpload(MultipartFile file, HttpServletRequest request) {
        return BaseResponse.ok(UploadFile.saveImageFromByte(file, request));
    }





}
