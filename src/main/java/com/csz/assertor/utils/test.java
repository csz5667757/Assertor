package com.csz.assertor.utils;

import org.springframework.util.DigestUtils;

import static com.csz.assertor.utils.MD5Util.convertMD5;

/**
 * @author Assertor
 * @Description
 * @Date：2019/11/26
 */
public class test {

    public static final String password = "123456";

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
//        String a = MD5Util.string2MD5(password);
//        System.out.println(a);
//
//        String c =DigestUtils.md5DigestAsHex(password.getBytes());
//        System.out.println(c);
//
//        String b = convertMD5(convertMD5(a));
//        System.out.println(b);
        String a = "123";
        String b = "123";
        System.out.println(a.equals(b));
    }
}
