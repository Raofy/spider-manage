package com.jin10.spidermanage.bean.label;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InsertBodyTest {

    /**
     * 组别id
     */
    private Integer gid;

    /**
     * 标签ID
     */
    private Integer lid;

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
    private List<String> spiderLink;

    /**
     * 实例图片
     */
    private List<String> img;

    /**
     * 爬虫ID
     */
    @JsonProperty("spider_id")
    private Integer spiderId;

    /**
     * 自动分配
     */
    @JsonProperty("auto_distribution")
    private Integer autoDistribution;

    /**
     * 参数
     */
    private List<KV> param;

    /**
     * 时间表达式
     */
    private String cron;
}
