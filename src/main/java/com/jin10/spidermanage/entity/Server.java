package com.jin10.spidermanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin10.spidermanage.bean.server.OperationBody;
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
public class Server implements Serializable {

    @TableId(type = AUTO)
    private Integer id;

    /**
     * 服务器中文名
     */
    @JsonProperty("server_name")
    private String serverName;

    /**
     * 服务器IP
     */
    @JsonProperty("server_ip")
    private String serverIp;

    private String port;


    public Server(OperationBody body) {
        this.id = body.getId();
        this.serverName = body.getServerName();
        this.serverIp = body.getServerIp();
        this.port = body.getPort();
    }
}
