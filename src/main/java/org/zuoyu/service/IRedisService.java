package org.zuoyu.service;

/**
 * Redis操作.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-10-13 14:52
 **/
public interface IRedisService {

  /**
   * 检查给定 key 是否存在
   * @param key - key
   * @return false/true
   */
  boolean isExists(String key);


  /**
   * 删除给定的key
   * @param key - key
   * @return false/true
   */
  boolean deleteKey(String key);

  /**
   * 设置指定 key 的值
   * @param key - K
   * @param value - V
   */
  void setKeyValue(String key, Object value);

  /**
   * 设置指定key，过期时间5分钟
   * @param key - K
   * @param value - V
   */
  void setKeyValueTimeout(String key, Object value);

  /**
   * 根据Key获取指定值
   * @param key - K
   * @return value - V
   */
  Object getValueByKey(String key);

  /**
   * 清空所有缓存
   */
  void clearAll();
}
