package com.example.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 咲蛍
 * @date 2021/6/11
 */
public class JWTUtil {
    /**
     * token 过期时间
     */
    private static final long TOKEN_EXPIRED_TIME = 2 * 60 * 60 * 1000;

    /**
     * 私钥
     */
    private static final String JWT_SECRET = "479767fd9a280be30e103c9caf9190a8";

    /**
     * 创建JWT
     */
    public static String createJWT(User user) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + TOKEN_EXPIRED_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "jwt");
            header.put("alg", "hs256");

            return JWT.create()
                    .withHeader(header)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id", String.valueOf(user.getUserId()))
                    .withClaim("name", String.valueOf(user.getUserName()))
                    .withClaim("status", String.valueOf(user.getUserStatus()))
                    .withClaim("nickname", String.valueOf(user.getUserNickname()))
                    .withClaim("position", String.valueOf(user.getUserPositionId()))
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 检验token是否正确
     *
     * @param token token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取过期时间
     *
     * @param token
     * @return
     */
    public static Date getExpiresAt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm).build().verify(token).getExpiresAt();
        }catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAt(String token) {
       try{
           Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
           return JWT.require(algorithm).build().verify(token).getIssuedAt();
       }catch (Exception e) {
           return null;
       }
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public static boolean isExpired(String token) {
        try {
            final Date expiration = getExpiresAt(token);
            return expiration.before(new Date());
        } catch (TokenExpiredException e) {
            return true;
        }

    }

    /**
     *获取用户自定义Claim集合
     * @param token
     * @return
     */
    public static Map<String, Claim> getClaims(String token){
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        Map<String, Claim> jwt = verifier.verify(token).getClaims();
        return jwt;
    }

    /**
     * 检验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token){
        DecodedJWT jwt=null;
        try {
            JWTVerifier verifier=JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
            jwt=verifier.verify(token);
        }catch (Exception e){

        }

        return jwt.getClaims();
    }


}
