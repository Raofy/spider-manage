package com.jin10.spidermanage.manage;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jin10.spidermanage.entity.ImgUrl;
import org.apache.commons.lang.StringUtils;
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

    public synchronized Boolean addElement(String url, String path) {
        hmInstance.put(url, path);
        return true;
    }

    public synchronized Boolean delElement(String url) {
        if (StringUtils.isNotBlank(url)) {
            hmInstance.remove(url);
            return true;
        }
        return false;
    }

    public synchronized Boolean isExit(String url) {
        if (StringUtils.isNotBlank(url)) {
            return hmInstance.containsKey(url);
        }
        return false;
    }

    public synchronized Boolean updateElement(String url, String path) {
        if (StringUtils.isNotBlank(url)) {
            this.addElement(url,path);
            return true;
        }
        return false;
    }

    public synchronized String getElement(String key) {
        if (StringUtils.isNotBlank(key)) {
            return hmInstance.get(key);
        }
        return null;
    }

    public void print() {
        for (Map.Entry<String, String> entry: hmInstance.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }

    public Boolean isEmpty() {
        return hmInstance.isEmpty();
    }
}
