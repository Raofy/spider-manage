package com.jin10.spidermanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ImgUrl implements Serializable {

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
