package tech.ychen.blog.utils;

/**
 * @author leon
 * @date 2019-04-05 15:16
 */
public class AuthCodeUtils {

    public static int makeAuthCode(){

        int authCode = 0;

        authCode = (int)Math.round(Math.random()*(9999-1000) + 1000);


        return authCode;

    }
}
