package com.jin10.spidermanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin10.spidermanage.bean.label.InsertBody;
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
public class Link implements Serializable {

    @TableId(type = AUTO)
    private Integer id;

    /**
     * 链接
     */
    private String link;

    /**
     * 标签ID
     */
    private Integer labelId;


    public Link(int add, String link) {
        this.labelId = add;
        this.link = link;
    }

}
