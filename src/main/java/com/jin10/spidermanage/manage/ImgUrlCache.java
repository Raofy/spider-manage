package com.jin10.spidermanage.manage;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jin10.spidermanage.entity.ImgUrl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ImgUrlCache {
    private static Map<Integer, List<ImgUrl>> hmInstance = new HashMap<>();

    private ImgUrlCache() {
    }

    public static Map<Integer, List<ImgUrl>> getInstance() {
        return hmInstance;
    }

    public static Boolean addElement(ImgUrl element) {
        if (!hmInstance.containsKey(element.getLabelId())) {
            List<ImgUrl> flag = new ArrayList<>();
            flag.add(element);
            hmInstance.put(element.getLabelId(), flag);
            return true;
        }else {
            hmInstance.get(element.getLabelId()).add(element);
            return true;
        }
    }

    public static Boolean delElement(ImgUrl element) {
        if (ObjectUtils.isNotNull(element)) {
            List<ImgUrl> imgUrls = hmInstance.get(element.getLabelId());
            for (ImgUrl i: imgUrls) {
                if (i.getUrl().equals(element.getUrl())) {
                    return imgUrls.remove(i);
                }
            }
            return false;
        }

        return false;
    }


}
