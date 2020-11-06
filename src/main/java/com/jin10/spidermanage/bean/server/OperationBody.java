package com.jin10.spidermanage.bean.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin10.spidermanage.entity.Server;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class OperationBody implements Serializable {

    private Integer id;

    @JsonProperty("server_name")
    private String serverName;

    @NotBlank(message = "ip不能为空")
    @JsonProperty("server_ip")
    private String serverIp;

    @NotBlank(message = "端口号不能为空")
    private String port;

}
