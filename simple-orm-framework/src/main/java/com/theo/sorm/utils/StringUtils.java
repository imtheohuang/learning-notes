package com.theo.sorm.utils;

/**
 * 封装常用字符串操作
 * @author huangsuixin
 * @date 2019/10/21 22:03
 */
public class StringUtils {

    /**
     * 目标字符串首字母转为大写
     * @param string
     * @return
     */
    public static String firstCharUpperCase(String string) {
        return string.toUpperCase().substring(0, 1) + string.substring(1);
    }
}
