package com.jin10.spidermanage.bean.label;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin10.spidermanage.annotation.valid.Cron;
import com.jin10.spidermanage.annotation.valid.URL;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.entity.Link;
import com.sun.javafx.logging.PulseLogger;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.security.PublicKey;
import java.util.List;

@Data
@NoArgsConstructor
public class InsertBody {

    /**
     * 组别id
     */
    private Integer gid;

    /**
     * 标签ID
     */
    private Integer lid;

    @JsonProperty("label_name")
    @NotBlank(message = "标签名不能为空")
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
    @NotBlank(message = "参数不能为空")
    @URL
    private String param;

    /**
     * 时间表达式
     */
    @Cron
    private String cron;

    /**
     * 是否开启调度
     * @param bodyTest
     */
    private int open;

    /**
     * 执行器id
     */
    @JsonProperty("executor_id")
    private int executorId;

    /**
     * 任务号ID
     */
    @JsonProperty("task_id")
    private Integer taskId;




    public InsertBody(InsertBodyTest bodyTest) {
        this.gid = bodyTest.getGid();
        this.lid = bodyTest.getLid();
        this.labelName = bodyTest.getLabelName();
        this.timeDesc = bodyTest.getTimeDesc();
        this.demandDesc = bodyTest.getDemandDesc();
        this.spiderLink = bodyTest.getSpiderLink();
        this.img = bodyTest.getImg();
        this.spiderId = bodyTest.getSpiderId();
        this.autoDistribution = bodyTest.getAutoDistribution();
        this.cron = bodyTest.getCron();

    }
}
