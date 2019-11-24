package com.csz.assertor;

import cn.hutool.core.util.RandomUtil;
import com.csz.assertor.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpLoggingFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(AssertorApplication.class);
    private static final boolean INCLUDE_RESPONSE_PAYLOAD = true;
    private static final int MAX_PAYLOAD_LENGTH = 1000;

    private String getContentAsString(byte[] buf, int maxLength, String charsetName) {
        if (buf == null || buf.length == 0) return "";
        int length = Math.min(buf.length, maxLength);
        try {
            return new String(buf, 0, length, charsetName);
        } catch (UnsupportedEncodingException ex) {
            return "Unsupported Encoding";
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        String id = RandomUtil.randomString(10);

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(wrappedRequest, wrappedResponse);
        long duration = System.currentTimeMillis() - startTime;

        LOGGER.warn("[{}]=> HTTP_STATUS: {}", id, response.getStatus());
        LOGGER.warn("[{}]=> PATH: {}", id, request.getRequestURL());
        LOGGER.warn("[{}]=> METHOD: {}", id, request.getMethod());
        LOGGER.warn("[{}]=> CLIENT_IP: {}", id, IpUtil.getIp(request));
        LOGGER.warn("[{}]=> DURATION: {}", id, duration);

        String requestBody = this.getContentAsString(wrappedRequest.getContentAsByteArray(), MAX_PAYLOAD_LENGTH, request.getCharacterEncoding());
        if (requestBody.length() > 0) {
            LOGGER.warn("[{}]=> REQUEST: {}", id, requestBody);
        }

        if (INCLUDE_RESPONSE_PAYLOAD) {
            byte[] buf = wrappedResponse.getContentAsByteArray();
            String responseBody = getContentAsString(buf, MAX_PAYLOAD_LENGTH, response.getCharacterEncoding());
            LOGGER.warn("[{}]=> RESPONSE: {}", id, responseBody);
        }
        wrappedResponse.copyBodyToResponse();
    }
}
