package org.zuoyu.service.impl;

import org.springframework.stereotype.Service;
import org.zuoyu.dao.UserMapper;
import org.zuoyu.model.User;
import org.zuoyu.service.IUserService;
import tk.mybatis.mapper.entity.Example;

/**
 * 安全用户服务实现.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-10 18:00
 **/
@Service
public class UserServiceImpl implements IUserService {

  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public User getUserDetailsByUserName(String userName) {
    Example userExample = new Example(User.class);
    userExample.createCriteria().andEqualTo("userPhone", userName);
    return userMapper.selectOneByExample(userExample);
  }
}
