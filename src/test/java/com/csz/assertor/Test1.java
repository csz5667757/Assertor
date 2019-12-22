package com.csz.assertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Assertor
 * @Description
 * @Date：2019/11/26
 */
public class Test1 {

    public static void main(String[] args) throws ParseException {

        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String parse = format.format(date);
        Date parse1 = format.parse(parse);
        System.out.println(parse);
//        String a = "ddsa,ewqeqw,dasdaslewq,ewqeqw";
//        String[] split = a.split(",");
//        for (int i = 0; i <split.length; i++ ){
//            System.out.println(split[i]);
//        }
//        System.out.println(RandomStringUtils.random(8));
    }
}
