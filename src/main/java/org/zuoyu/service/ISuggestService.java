package org.zuoyu.service;

import java.util.List;
import org.zuoyu.model.Suggest;

/**
 * 建议反馈服务.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-17 10:05
 **/
public interface ISuggestService {

  /**
   * 获取所有建议
   * @return - 建议集合
   */
  List<Suggest> selectAll();

  /**
   * 添加建议
   * @param suggest - 建议实例
   * @return - 添加的个数
   */
  int addSuggest(Suggest suggest);
}
