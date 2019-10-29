package org.zuoyu.util;

import java.util.UUID;
import tk.mybatis.mapper.genid.GenId;

/**
 * UUID生成器.
 *
 * @author zuoyu
 **/
public class UUIDGenerated implements GenId<String> {


  @Override
  public String genId(String table, String column) {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /**
   * 手动获取UUID
   * @return UUID
   */
  public static String obtain(){
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
