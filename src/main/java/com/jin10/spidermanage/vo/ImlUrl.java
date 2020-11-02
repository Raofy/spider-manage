package com.jin10.spidermanage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImlUrl implements Serializable {

    private Integer id;

    private String url;
}
