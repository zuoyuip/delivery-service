package org.zuoyu.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import org.zuoyu.model.User;
import org.zuoyu.security.constants.JwtConstants;

/**
 * JWT_Token的工具类.
 *
 * @author zuoyu
 * @program jwt
 * @create 2019-10-16 09:38
 **/
public class JwtTokenUtils {

  private final static byte[] API_KEY_SECRET_BYTES = DatatypeConverter
      .parseBase64Binary(JwtConstants.JWT_SECRET_KEY);

  private final static SecretKey SECRET_KEY = Keys.hmacShaKeyFor(API_KEY_SECRET_BYTES);


  private JwtTokenUtils() {
  }


  /**
   * 根据账户构建token
   *
   * @param user - 账户
   * @return -
   */
  public static String createToken(User user) {
    return createJwt(JsonUtil.beanToJsonString(user));
  }

  /**
   * 获取用户
   *
   * @param token - token
   * @return - User
   */
  public static User getUserByToken(String token) {
    String subject = parseJwt(token).getSubject();
    return JsonUtil.jsonStringToBean(subject, User.class);
  }


  /**
   * 是否已过期
   *
   * @param token - token
   * @return boolean
   */
  public static boolean isTokenExpired(String token) {
    return parseJwt(token).getExpiration().before(new Date());
  }

  /**
   * 构建JWT
   *
   * @param subject - 实体
   * @return - token
   */
  private static String createJwt(String subject) {
    long nowMillis = System.currentTimeMillis();
    return Jwts.builder()
        .setId(JwtConstants.createTokenId())
        .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
        .setIssuer(JwtConstants.JWT_ISSUER)
        .setSubject(subject)
        .setIssuedAt(new Date(nowMillis))
        .setNotBefore(new Date(nowMillis))
        .setExpiration(new Date(nowMillis + 189216000000L))
        .compact();
  }

  /**
   * 解析token
   *
   * @param token -
   * @return - Claims
   */
  private static Claims parseJwt(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
  }
}
