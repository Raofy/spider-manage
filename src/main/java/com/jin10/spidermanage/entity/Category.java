package com.jin10.spidermanage.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Category implements Serializable {

    @TableId(type = AUTO)
    private Integer id;

    @JsonProperty("category_name")
    private String categoryName;

    private long parentId;


    @JsonProperty("create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date createTime;


    @JsonProperty("update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss",timezone = "GMT+8")
    private Date updateTime;

}
