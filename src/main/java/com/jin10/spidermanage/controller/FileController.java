package com.jin10.spidermanage.controller;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.manage.ImgUrlCache;
import com.jin10.spidermanage.util.UploadFile;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.util.StringUtils;
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

    //单个文件的上传
    @PostMapping("/upload")
    public BaseResponse handleFileUpload(MultipartFile file, HttpServletRequest request) {
        String filePath = UploadFile.saveImageFromByte(file, request);
        if(ImgUrlCache.getInstance().addElement(filePath)) {
            return BaseResponse.ok(UploadFile.saveImageFromByte(file, request));
        }
        return BaseResponse.error("上传失败！");

    }

    @GetMapping("/del")
    public BaseResponse handleFileDelete(@PathParam("url") String url) {
        if (!StringUtils.isEmpty(url)) {
            if (ImgUrlCache.getInstance().delElement(url) && UploadFile.deleteFile(url)) {
                return BaseResponse.ok(true);
            }
            return BaseResponse.error(false);
        }
        return BaseResponse.error(false);
    }
}
