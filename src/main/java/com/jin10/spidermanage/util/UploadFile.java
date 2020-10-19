package com.jin10.spidermanage.util;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UploadFile {

    public static String saveImageFromByte(MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                String originalFilename = file.getOriginalFilename();    //获取原始文件名
                if (StringUtils.isEmpty(originalFilename)) {
                    return "上传文件为空";
                }
                String baseDest = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
//                String baseDest = "src/main/resources/static";
                String fileDir = "/uploads/images/" + createNewDir();
                String fileServerPath = baseDest + fileDir;
                File dir = new File(fileServerPath);
                if (!dir.isDirectory()) {
                    dir.mkdirs();
                }
                File originalFile = new File(dir, originalFilename);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(originalFile));
                System.out.println(file.getName());
                bufferedOutputStream.write(file.getBytes());
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                String filePath = request.getScheme() + "://" +
                        request.getServerName() + ":"
                        + request.getServerPort()
                        + fileDir + "/" + originalFilename;

                return filePath;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    public static Object bulkSaveImageFromByte(MultipartFile[] file, HttpServletRequest request) {
        if (file.length <= 0) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                List<String> urls = new ArrayList<>();
                for (int i = 0; i < file.length; i++) {
                    String originalFilename = file[i].getOriginalFilename();    //获取原始文件名
                    if (StringUtils.isEmpty(originalFilename)) {
                        return "上传文件为空";
                    }
                    String baseDest = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
//
                    String fileDir = "/uploads/images/" + createNewDir();
                    String fileServerPath = baseDest + fileDir;
                    File dir = new File(fileServerPath);
                    if (!dir.isDirectory()) {
                        dir.mkdirs();
                    }
                    File originalFile = new File(dir, originalFilename);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(originalFile));
                    bufferedOutputStream.write(file[i].getBytes());
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    String filePath = request.getScheme() + "://" +
                            request.getServerName() + ":"
                            + request.getServerPort()
                            + fileDir + "/" + originalFilename;
                    urls.add(filePath);

                }
                return urls;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    /**
     * 为文件重新命名，命名规则为当前系统时间毫秒数
     *
     * @return string
     */
    private static String getFileNameNew() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fmt.format(new Date());
    }

    /**
     * 以当前日期为名，创建新文件夹
     *
     * @return
     */
    private static String createNewDir() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(new Date());
    }
}