package com.jin10.spidermanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin10.spidermanage.bean.label.InsertBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;
import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Label implements Serializable {

    @TableId(type = AUTO)
    private Integer id;

    /**
     * 组别ID
     */
    @JsonProperty("category_id")
    private Integer categoryId;

    /**
     * 标签名
     */
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
     * 实例图片
     */
    private String example;

    /**
     * 爬虫ID
     */
    @JsonProperty("spider_id")
    private Integer spiderId;

    /**
     * 参数
     */
    private Integer param;

    /**
     * 时间表达式
     */
    private String cron;

    /**
     * 自动分配
     */
    @JsonProperty("auto_distribution")
    private Byte autoDistribution;

    /**
     * 创建时间
     */
    @JsonProperty("create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonProperty("update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date updateTime;

    public Label(InsertBody insertBody) {
        this.id = insertBody.getId();
        this.categoryId = insertBody.getCategoryId();
        this.labelName = insertBody.getLabelName();
        this.timeDesc = insertBody.getTimeDesc();
        this.demandDesc = insertBody.getDemandDesc();
        this.spiderId = insertBody.getSpiderId();
        this.param = insertBody.getParam();
        this.cron = insertBody.getCron();
        this.autoDistribution = insertBody.getAutoDistribution();
    }
}