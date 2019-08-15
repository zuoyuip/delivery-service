package org.zuoyu.service.impl;

import java.util.List;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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

  private final PasswordEncoder passwordEncoder = PasswordEncoderFactories
      .createDelegatingPasswordEncoder();

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public User getUserDetailsByUserPhone(String userPhone) {
    Example userExample = new Example(User.class);
    userExample.createCriteria().andEqualTo("userPhone", userPhone);
    return userMapper.selectOneByExample(userExample);
  }

  @Override
  public int insertUser(User user) {
    String passWord = user.getUserPassword();
    String encryptionPassWord = passwordEncoder.encode(passWord);
    user.setUserPassword(encryptionPassWord).setUserIsAccountNonExpired(true)
        .setUserIsAccountNonLocked(true).setUserIsCredentialsNonExpired(true).setUserIsEnabled(true)
        .setUserIsValid(true).setUserIsSubmitReview(false).setUserIsByReview(false);
    return userMapper.insert(user);
  }

  @Override
  public boolean isPresenceByUserPhone(String userPhone) {
    int count = userMapper.countByUserPhone(userPhone);
    return count > 0;
  }

  @Override
  public List<User> listUser() {
    return userMapper.selectAll();
  }
}
