package com.jin10.spidermanage.bean.spider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ExecutorList {

    /**
     * recordsFiltered : 4
     * data : [{"id":3,"appname":"fuyi-executor","title":"爬虫调度","addressType":0,"addressList":null,"registryList":null},{"id":6,"appname":"local-executor","title":"本地执行器","addressType":0,"addressList":null,"registryList":null},{"id":7,"appname":"local-executor","title":"本地执行器","addressType":0,"addressList":null,"registryList":null},{"id":1,"appname":"xxl-job-executor-sample","title":"示例执行器","addressType":0,"addressList":null,"registryList":null}]
     * recordsTotal : 4
     */

    private int recordsFiltered;
    private int recordsTotal;

    private List<DataBean> data;

    @Data
    public static class DataBean {
        /**
         * id : 3
         * appname : fuyi-executor
         * title : 爬虫调度
         * addressType : 0
         * addressList : null
         * registryList : null
         */

        @JsonProperty("executor_id")
        private int id;

        private String appname;

        private String title;

        /**
         * 地址类型
         */
//        @JsonProperty("address_type")
//        private int addressType;
//
//        @JsonProperty("address_list")
//        private Object addressList;
//
//        @JsonProperty("registry_list")
//        private Object registryList;


    }
}
