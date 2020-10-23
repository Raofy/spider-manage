package com.jin10.spidermanage.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.quartz.CronExpression;

import java.util.regex.Pattern;

/**
 *  校验合法性工具
 */
public class RegularUtil {

    /**
     * url正则表达式
     */
//    private static final String REGEX_URL = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
    private static final String REGEX_URL = "/(http|https):\\/\\/([\\w.]+\\/?)\\S*/";

    /**
     * cron 表达式校验
     *
     * @param cron
     * @return
     */
    public static Boolean cron(String cron) {
        return CronExpression.isValidExpression(cron);
    }


    /**
     * url 表达式校验
     *
     * @param url
     * @return
     */
    public static Boolean url(String url) {
        if (StringUtils.isNotBlank(url)) {
            String[] schemes = {"http","https"}; // DEFAULT schemes ="http","https","ftp"
            UrlValidator urlValidator = new UrlValidator(schemes);
            return urlValidator.isValid(url);
        }
        return false;
    }
}
