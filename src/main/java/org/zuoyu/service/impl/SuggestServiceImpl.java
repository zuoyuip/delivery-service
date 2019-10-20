package org.zuoyu.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.zuoyu.dao.SuggestMapper;
import org.zuoyu.model.Suggest;
import org.zuoyu.model.User;
import org.zuoyu.service.ISuggestService;
import org.zuoyu.util.UserUtil;

/**
 * 建议反馈的服务实现.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-17 10:06
 **/
@Service
class SuggestServiceImpl implements ISuggestService {

  private final SuggestMapper suggestMapper;

  SuggestServiceImpl(SuggestMapper suggestMapper) {
    this.suggestMapper = suggestMapper;
  }

  @Override
  public List<Suggest> selectAll() {
    List<Suggest> suggests = suggestMapper.selectAll();
    if (suggests == null || suggests.isEmpty()){
      return null;
    }
    suggests.sort(Comparator.comparing(Suggest::getSuggestDate));
    return suggests;
  }

  @Override
  public int addSuggest(Suggest suggest) {
    if (UserUtil.isAuthenticated()) {
      User user = UserUtil.currentUser();
      suggest.setSuggestUserId(user.getUserId());
    }
    return suggestMapper.insert(suggest.setSuggestDate(new Date()));
  }
}
