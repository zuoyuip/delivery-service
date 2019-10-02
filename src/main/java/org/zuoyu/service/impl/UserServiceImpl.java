package org.zuoyu.service.impl;

import java.util.List;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zuoyu.dao.UserMapper;
import org.zuoyu.exception.CustomException;
import org.zuoyu.model.User;
import org.zuoyu.service.IUserService;
import org.zuoyu.util.UserUtil;
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
    if (userPhone == null || "".equals(userPhone.trim()) || userPhone.trim().isEmpty()) {
      return null;
    }
    Example userExample = new Example(User.class);
    userExample.createCriteria().andEqualTo("userPhone", userPhone);
    return userMapper.selectOneByExample(userExample);
  }

  @Override
  public int insertUser(User user) {
    if (user == null) {
      return 0;
    }
    String passWord = user.getUserPassword();
    String encryptionPassWord = passwordEncoder.encode(passWord);
    user.setUserPassword(encryptionPassWord).setUserIsAccountNonExpired(true)
        .setUserIsAccountNonLocked(true).setUserIsCredentialsNonExpired(true).setUserIsEnabled(true)
        .setUserIsValid(true).setUserIsSubmitReview(false).setUserIsByReview(false);
    return userMapper.insert(user);
  }

  @Override
  public boolean isPresenceByUserPhone(String userPhone) {
    if (userPhone == null || "".equals(userPhone.trim()) || userPhone.trim().isEmpty()) {
      return false;
    }
    int count = userMapper.countByUserPhone(userPhone);
    return count > 0;
  }

  @Override
  public List<User> listUser() {
    return userMapper.selectAll();
  }

  @Override
  public User getUserById(String userId) {
    if (userId == null || "".equals(userId.trim()) || userId.trim().isEmpty()) {
      return null;
    }
    return userMapper.selectByPrimaryKey(userId);
  }

  @Override
  public int updateUserById(User user, boolean isPasswordEncoder) {
    if (user == null) {
      return 0;
    }
    String userId = user.getUserId();
    if (userId == null || "".equals(userId.trim()) || userId.trim().isEmpty()) {
      return 0;
    }
    if (isPasswordEncoder) {
      String passWord = user.getUserPassword();
      if (passWord == null || "".equals(passWord.trim()) || passWord.trim().isEmpty()) {
        return 0;
      }
      String encryptionPassWord = passwordEncoder.encode(passWord);
      user.setUserPassword(encryptionPassWord);
    }
    return userMapper.updateByPrimaryKeySelective(user);
  }

  @Override
  public boolean verifyUser(String passWord) {
    if (passWord == null || "".equals(passWord.trim()) || passWord.trim().isEmpty()) {
      return false;
    }
    if (!UserUtil.isAuthenticated()){
      throw new CustomException("当前用户未登录");
    }
    User user = UserUtil.currentUser();
    return passwordEncoder.matches(passWord, user.getPassword());
  }
}
