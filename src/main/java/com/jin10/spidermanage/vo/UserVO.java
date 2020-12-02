package com.jin10.spidermanage.vo;

import com.jin10.spidermanage.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserVO implements Serializable {

    /**
     * 更新者ID
     */
    private long updaterId;

    /**
     * 更新者名字
     */
    private String name;

    /**
     * 更新者电话
     */
    private String iphone;


    public UserVO(User user) {
        this.updaterId = user.getId();
        this.name = user.getName();
        this.iphone = user.getIphone();
    }
}
