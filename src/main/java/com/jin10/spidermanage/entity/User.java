package com.jin10.spidermanage.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Setter
@Getter
@AllArgsConstructor
public class User implements Serializable {

    @TableId(type = AUTO)
    private long id;

    private String name;

    private String iphone;

    public User(String name, String iphone) {
        this.name = name;
        this.iphone = iphone;
    }
}
