package com.jin10.spidermanage.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jin10.spidermanage.entity.Server;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ServerFuzzyQueryVO implements Serializable {

    private List<Server> servers;

    @JsonProperty("page_num")
    private long pageNum;

    @JsonProperty("page_size")
    private long pageSize;

    private long total;

    public ServerFuzzyQueryVO(List<Server> servers, long pageNum, long pageSize, long total) {
        //this.servers = new ArrayList<>();
        this.servers = servers;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

}
