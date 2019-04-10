package tech.ychen.blog.utils;

/**
 * @author leon
 * @date 2019-04-05 16:32
 */

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import tech.ychen.blog.entiy.User;

import java.util.Date;

/**
 * @author leon
 * @date 2019-03-26 22:21
 * JWT工具类
 */
public class JwtUtils {

    public static final String SUBJECT = "leon";

    public static long EXPIRE = 1000*60*60*24*7; //过期时间ms

    public static final String APPSECRET = "leon666";


    /**
     * 生成 jwt
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){
        if(user == null  || user.getUserName() == null ){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("img",user.getUserHeadimg())
                .claim("name",user.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256,APPSECRET).compact();

        return token;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){

        try {

            final Claims claims =  Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){ }
            
        return null;

    }


}
