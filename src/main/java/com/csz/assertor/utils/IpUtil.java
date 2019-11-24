package com.csz.assertor.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Assertor
 * @Description
 * @Date：2019/11/24
 */
public class IpUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(IpUtil.class);
    /**
     * @Author Assertor
     * @Date 2019/11/24
     * @Description 返回请求用户的真实IP
     * @return
     */

    public static String getIp(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            int index = ip.indexOf(",");
            if (StringUtils.isNotBlank(ip) && index > 0) {
                ip = ip.substring(0, index);
            }
        } catch (Exception e) {
            LOGGER.error("failed to get the client IP");
        }
        return ip;
    }
}
