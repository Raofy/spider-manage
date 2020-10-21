package com.jin10.spidermanage.controller;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.manage.ImgUrlCache;
import com.jin10.spidermanage.mapper.ImgUrlMapper;
import com.jin10.spidermanage.service.ImgUrlService;
import com.jin10.spidermanage.util.UploadFile;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private ImgUrlService imgUrlService;

    //单个文件的上传
    @PostMapping("/upload")
    public BaseResponse handleFileUpload(MultipartFile file, HttpServletRequest request) {
        ImgUrlCache imgCache = ImgUrlCache.getInstance();
        if (imgCache.isEmpty()) {
            Iterable<ImgUrl> imgUrls = imgUrlService.getBaseMapper().selectList(null);
            for (ImgUrl i : imgUrls) {
                imgCache.addElement(i.getUrl(), i.getPath());
            }
        }
        String filePath = UploadFile.saveImageFromByte(file, request);
        if (StringUtils.isNotBlank(filePath)) {
            return BaseResponse.ok(filePath);
        }
        return BaseResponse.error("上传失败！");
    }

    @GetMapping("/del")
    public BaseResponse handleFileDelete(@PathParam("url") String url) {
        if (!StringUtils.isEmpty(url)) {
            String path = ImgUrlCache.getInstance().getElement(url);
            if (ImgUrlCache.getInstance().delElement(url) && UploadFile.deleteFile(path)) {
                return BaseResponse.ok(true);
            }
            return BaseResponse.error(false);
        }
        return BaseResponse.error(false);
    }
}
