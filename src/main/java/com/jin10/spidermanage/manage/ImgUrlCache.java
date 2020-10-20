package com.jin10.spidermanage.manage;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jin10.spidermanage.entity.ImgUrl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ImgUrlCache {
    private static Map<String, String> hmInstance = new HashMap<>();

    private ImgUrlCache() {
    }

    private static class Holder {
        static ImgUrlCache instance = new ImgUrlCache();
    }

    public static ImgUrlCache getInstance() {
        return ImgUrlCache.Holder.instance;
    }

    public synchronized Boolean addElement(String url) {
        hmInstance.put(url, url);
        return true;
    }

    public synchronized Boolean delElement(String url) {
        if (StringUtils.isNotBlank(url)) {
            hmInstance.remove(url);
            return true;
        }
        return false;
    }

    public synchronized Boolean updateElement(String url) {
        if (StringUtils.isNotBlank(url)) {
            this.addElement(url);
            return true;
        }
        return false;
    }


}
