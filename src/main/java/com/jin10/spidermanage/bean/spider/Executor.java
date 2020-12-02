package com.jin10.spidermanage.bean.spider;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class Executor {

    private int id;

    @NotBlank(message = "appname不能为空")
    private String appname;

    @NotBlank(message = "中文名不能为空")
    private String title;
}
