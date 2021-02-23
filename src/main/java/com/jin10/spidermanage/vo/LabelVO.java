package com.jin10.spidermanage.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelVO implements Serializable {

    private Integer lid;

    @JsonProperty("label_name")
    private String labelName;

    private Integer gid;

    /**
     * 所有父级目录id
     */
    private String rid;

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
     * 实例图片路径
     */
    private List<ImlUrl> img;

    /**
     * 爬虫ID
     */
    @JsonProperty("spider_id")
    private Integer spiderId;

    /**
     * 参数
     */
    private String param;

    /**
     * 时间表达式
     */
    private String cron;

    /**
     * 自动分配
     */
    @JsonProperty("auto_distribution")
    private Integer autoDistribution;

    /**
     * 是否已经开启调度
     */
    private Integer open;

    /**
     * 任务号id
     */
    @JsonProperty("task_id")
    private int taskId;

    @JsonProperty("executor_id")
    private int executorId;

    /**
     * 服务器信息
     */
    private ServerVO server;

    /**
     * URL 路径
     */
    private String path;

    /**
     * 创建者
     */
    private String creator;


    /**
     * 更新者
     */
    private String updater;

//    /**
//     * 手机号
//     */
//    private String iPhone;

    /**
     * 创建者id
     *
     */
    @JsonProperty("creator_id")
    private long creatorId;

    /**
     * 更新者id
     *
     */
    @JsonProperty("updater_id")
    private long updaterId;

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




}
