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
}
