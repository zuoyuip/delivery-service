package org.zuoyu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuoyu.dao.SuggestMapper;
import org.zuoyu.model.Suggest;
import org.zuoyu.service.ISuggestService;

/**
 * 建议反馈的服务实现.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-17 10:06
 **/
@Service
public class SuggestServiceImpl implements ISuggestService {

  private final SuggestMapper suggestMapper;

  public SuggestServiceImpl(SuggestMapper suggestMapper) {
    this.suggestMapper = suggestMapper;
  }

  @Override
  public List<Suggest> selectAll() {
    return suggestMapper.selectAll();
  }

  @Override
  public int addSuggest(Suggest suggest) {
    return suggestMapper.insert(suggest);
  }
}
