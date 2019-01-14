package com.mycurdpro.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.Ret;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;

/**
 * jwt token 工具，需要根据集体业务做修改
 * @author zhangchuang
 */
public class JwtUtils {
    private final static Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

    //签发者
    private static final String ISSUER = "iss";
    //签发时间
    private static final String ISSUED_AT = "iat";
    //过期时间
    private static final String EXPIRATION_TIME = "exp";
    private static final Long EXPIRATION_TIME_VALUE = 1000 * 60L * 60 * 24 * 30;   // 30 天有效期
    //JWT ID
    private static final String JWT_ID = "jti";
    //密钥
    private static final String SECRET = "AAAABBBCCC";


    /**
     * 创建 jwt token, 使用默认过期时间
     * @param userId
     * @param username
     * @param name
     * @return
     */
    public static String createToken(String userId, String username, String name){
        return createToken(userId,username,name,EXPIRATION_TIME_VALUE);
    }

    /**
     * 构造Token
     *
     * @param userId   用户ID
     * @param username 用户名称
     * @param name     手机号
     * @param expValue      多久后签名过期，单位为 毫秒
     * @return
     */
    public static String createToken(String userId, String username, String name,Long expValue) {
        //采用HS256签名算法对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前系统时间
        long nowMillis = System.currentTimeMillis();
        //采用密钥对JWT加密签名
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //构造payload
        JSONObject payload = new JSONObject();
        payload.put(ISSUER, "ESBP");
        payload.put(ISSUED_AT, nowMillis / 1000);
        payload.put(JWT_ID, userId);
        payload.put("username", username);
        payload.put("name", name);
        //设置过期时间
        long expMillis = nowMillis + expValue;
        payload.put(EXPIRATION_TIME, expMillis / 1000);

        //设置JWT参数
        JwtBuilder builder = Jwts.builder()
                .setPayload(payload.toJSONString())
                .signWith(signatureAlgorithm, signingKey);

        //构造token字符串
        return builder.compact();
    }



    /**
     * JWT解析
     *
     * @param jwt
     * @return
     */
    public static Ret parseJWT(String jwt) {
        Ret ret = Ret.create();
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    //.setAllowedClockSkewSeconds(100) // 过期后 后 延后100s 内仍能解析
                    .parseClaimsJws(jwt).getBody();

            UserClaim userClaim = new UserClaim();
            userClaim.setUsername((String) claims.get("username"));
            userClaim.setName((String) claims.get("name"));
            userClaim.setJti(claims.getId());
            userClaim.setIss(claims.getIssuer());
            userClaim.setIat(claims.getIssuedAt());
            userClaim.setExp(claims.getExpiration());

            ret.setOk();
            ret.set("userClaim",userClaim);

        }catch (ExpiredJwtException e){
            LOG.info(e.getMessage());
            ret.setFail();
            ret.set("msg","expire");  // 过期
        }catch ( MalformedJwtException | SignatureException e1){
            LOG.error(e1.getMessage(),e1);
            ret.setFail();
            ret.set("msg","errorToken"); // 错误的 token
        }
        return ret;
    }
}

class UserClaim implements Serializable {
    private static final long serialVersionUID = -600345604328767722L;
    private String username;  // 用户名
    private String name;      // 用户姓名
    private String jti;       // 用户id
    private String iss;       // 签发人
    private Date iat;         // 签发时间
    private Date exp;         // 过期时间

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public Date getIat() {
        return iat;
    }

    public void setIat(Date iat) {
        this.iat = iat;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }
}
