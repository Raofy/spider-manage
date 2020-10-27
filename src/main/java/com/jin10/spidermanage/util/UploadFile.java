package com.jin10.spidermanage.util;

import com.jin10.spidermanage.manage.ImgUrlCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class UploadFile {

    private static String baseUrl = "/home/software/web/spider-manager/data";
    private static String nginxPort = "8095";

    public static String saveImageFromByte(MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                ImgUrlCache imgUrlCache = ImgUrlCache.getInstance();
                String originalFilename = file.getOriginalFilename();    //获取原始文件名
                if (StringUtils.isEmpty(originalFilename)) {
                    return "上传文件为空";
                }
                String fileDir = "/images/";
                String fileServerPath = baseUrl + fileDir;     //   /home/software/web/spider-manage/data/images/
                File dir = new File(fileServerPath);
                if (!dir.isDirectory()) {
                    dir.mkdirs();
                }
                String fileName = MD5Util.MD5(getFileNameNew()) + "." + StringUtils.substringAfter(originalFilename, ".");
                File originalFile = new File(dir, fileName);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(originalFile));
                bufferedOutputStream.write(file.getBytes());
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                String url = request.getScheme() + "://" +
                        request.getServerName() + ":" + nginxPort + File.separator + "images" + File.separator + fileName;
                imgUrlCache.addElement(url, originalFile.getAbsolutePath());
                return url;
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
                    String fileDir = "/uploads/images/" + createNewDir();
                    String fileServerPath = baseUrl + fileDir;
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
                            + fileDir + "/" + getFileNameNew();
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
     * 删除文件
     * @param path
     * @return
     */
    public static Boolean deleteFile(String path) {
        if (!StringUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.delete()) {
                return true;
            }
            return false;
        }
        return false;
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
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(new Date());
    }
}
