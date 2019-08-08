package org.zuoyu.util;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON封装.
 *
 * @author zuoyu
 **/
public class Result extends HashMap<String, Object> {

  /**
   * 响应数据
   * @param data - 数据
   * @return - 结果
   */
  public static Result data(Object data) {
    return template(null, data);
  }

  /**
   * 响应信息
   * @param message - 信息
   * @return - 结果
   */
  public static Result message(String message) {
    return template(message, null);
  }

  /**
   * 响应信息和数据
   * @param message - 信息
   * @param data - 数据
   * @return - 结果
   */
  public static Result detail(String message, Object data){
    return template(message, data);
  }

  private static Result template(String message, Object data) {
    Result result = new Result();
    result.put("message", message);
    result.put("data", data);
    return result;
  }

  /**
   * 自定义响应
   * @param map - 自定义内容
   * @return - 结果
   */
  public static Result custom(Map<String, Object> map) {
    Result result = new Result();
    result.putAll(map);
    return result;
  }


  /**
   * 自定义
   * @return - 容器
   */
  public static Result custom() {
    return new Result();
  }

  /**
   * 自定义添加键值对
   * @param key - 键
   * @param value - 值
   * @return - 结果
   */
  @Override
  public Result put(String key, Object value) {
    super.put(key, value);
    return this;
  }
}
