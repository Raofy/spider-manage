package com.jin10.spidermanage.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin10.spidermanage.entity.Server;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerVO implements Serializable {

    @JsonProperty("server_id")
    private Integer serverId;

    @JsonProperty("server_name")
    private String serverName;


    public ServerVO (Server server) {
        this.serverId = server.getId();
        this.serverName = String.format("%s(%s)", server.getServerName(), server.getServerIp());
    }
}
