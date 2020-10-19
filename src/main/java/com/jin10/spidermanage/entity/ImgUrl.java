package com.jin10.spidermanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
public class ImgUrl {

    @TableId(type = AUTO)
    private Integer id;

    @JsonProperty("label_id")
    private Integer labelId;

    private String url;


    public ImgUrl(Integer id, String s) {
        this.labelId = id;
        this.url = s;
    }
}
