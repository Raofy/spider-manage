package com.jin10.spidermanage.bean.label;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.entity.Link;
import lombok.Data;

import java.util.List;

@Data
public class InsertBody {



    @JsonProperty("category_id")
    private Integer categoryId;

    private Integer id;

    @JsonProperty("label_name")
    private String labelName;

    /**
     * 抓取时间描述
     */
    @JsonProperty("time_desc")
    private String timeDesc;

    /**
     * 需求描述
     */
    @JsonProperty("demand_desc")
    private String demandDesc;

    /**
     * 爬虫链接
     */
    @JsonProperty("spider_link")
    private List<Link> spiderLink;

    /**
     * 实例图片
     */
    private List<ImgUrl> img;

    /**
     * 爬虫ID
     */
    @JsonProperty("spider_id")
    private Integer spiderId;

    /**
     * 自动分配
     */
    @JsonProperty("auto_distribution")
    private Byte autoDistribution;

    /**
     * 参数
     */
    private Integer param;

    /**
     * 时间表达式
     */
    private String cron;
}
