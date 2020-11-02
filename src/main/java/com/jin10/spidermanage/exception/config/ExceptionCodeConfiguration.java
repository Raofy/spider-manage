package com.jin10.spidermanage.exception.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "lin")
@PropertySource(value = "classpath:config/exception-code.properties")
public class ExceptionCodeConfiguration {

    private Map<Integer, String> codes = new HashMap<>();

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }
    public Map<Integer, String> getCodes() {
        return codes;
    }

    public String getMessage(int status) {
        if (status > 0) {
            return codes.get(status);
        }
        return null;
    }
}
