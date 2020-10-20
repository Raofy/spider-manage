package com.jin10.spidermanage.bean.label;

import lombok.Data;

@Data
public class SpiderTest {

    /**
     * title : 10月20日山东金诚石化丙烯最新报价
     * source : 甲醇网
     * category : 文章
     * channel : 首页
     * url : http://www.jiachunwang.com/quote/show/275763
     */

    private String title;
    private String source;
    private String category;
    private String channel;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
