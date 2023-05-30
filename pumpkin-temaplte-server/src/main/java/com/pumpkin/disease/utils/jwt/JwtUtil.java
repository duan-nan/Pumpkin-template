package com.pumpkin.disease.utils.jwt;

import com.pumpkin.disease.config.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Pumpkin
 * @description: Jwt令牌工具类
 * @since : 2022/12/14
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;

    private final String CLAIM_KEY_USERNAME = "jwtKey";

    private final String CLAIM_CREATE_TIME = "createTime";


    /**
     * 创建一个Jwt令牌
     *
     * @param username 用户名
     * @return token令牌
     */
    public String createJwt(String username) {
        Map<String, Object> map = new HashMap<>(2);
        map.put(CLAIM_KEY_USERNAME, username);
        map.put(CLAIM_CREATE_TIME, new Date());
        return generatorToken(map);
    }

    /**
     * 荷载生成token信息
     *
     * @param map map集合
     * @return token令牌
     */
    private String generatorToken(Map<String, Object> map) {
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(generatorExpiration())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getJwt().getSecret())
                .compact();
    }

    /**
     * 生成超时时间
     *
     * @return 超时时间
     */
    private Date generatorExpiration() {
        return new Date(System.currentTimeMillis() + jwtProperties.getJwt().getExpireTime() * 1000);
    }

    /**
     * 从token中获取用户名
     *
     * @param token token令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = String.valueOf(claims.get(CLAIM_KEY_USERNAME));
        } catch (Exception e) {
            log.error("获取用户信息失败==>{}", e.getMessage());
        }
        return username;
    }

    /**
     * 根据token获取荷载
     *
     * @param token token令牌
     * @return {@link Claims} 荷载
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getJwt().getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 检测token是否还有效
     *
     * @param token    token令牌
     * @param username 用户名
     * @return 是否有效
     */
    public boolean validateToken(String token, String username) {
        return getUsernameFromToken(token).equals(username) && isTokenExpired(token);
    }

    /**
     * token是否失效
     *
     * @param token token令牌
     * @return token是否失效
     */
    private boolean isTokenExpired(String token) {
        return new Date().before(getClaimsFromToken(token).getExpiration());
    }

}
