package com.jin10.spidermanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImlUrlDTO implements Serializable {

    private Integer id;

    private String url;
}
