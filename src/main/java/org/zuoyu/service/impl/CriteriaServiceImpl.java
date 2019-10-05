package org.zuoyu.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zuoyu.dao.ReviewMapper;
import org.zuoyu.dao.UserInfoMapper;
import org.zuoyu.dao.UserMapper;
import org.zuoyu.exception.CustomException;
import org.zuoyu.manager.CriteriaManager;
import org.zuoyu.model.Review;
import org.zuoyu.model.User;
import org.zuoyu.model.UserInfo;
import org.zuoyu.service.ICriteriaService;
import org.zuoyu.util.UUIDGenerated;
import org.zuoyu.util.UserUtil;
import tk.mybatis.mapper.entity.Example;

/**
 * 审核的服务实现.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-17 16:59
 **/
@Service
public class CriteriaServiceImpl implements ICriteriaService {


  private final UserMapper userMapper;
  private final CriteriaManager criteriaManager;
  private final ReviewMapper reviewMapper;
  private final UserInfoMapper userInfoMapper;

  public CriteriaServiceImpl(UserMapper userMapper, CriteriaManager criteriaManager,
      ReviewMapper reviewMapper, UserInfoMapper userInfoMapper) {
    this.userMapper = userMapper;
    this.criteriaManager = criteriaManager;
    this.reviewMapper = reviewMapper;
    this.userInfoMapper = userInfoMapper;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class,
      CustomException.class})
  public User applicationCriteria(UserInfo userInfo, MultipartFile multipartFile)
      throws IOException {
    User user = UserUtil.currentUser();
    String userInfoId = UUIDGenerated.obtain();
    String reviewId = UUIDGenerated.obtain();
    Review review = new Review().setReviewIsBy(false).setReviewDate(new Date())
        .setUserId(user.getUserId()).setReviewId(reviewId);
    int insertReview = reviewMapper.insertSelective(review);
    if (insertReview < 1) {
      throw new CustomException("添加审核信息失败");
    }
    userInfo.setUserInfoId(userInfoId);
    int insertUserInfo = criteriaManager.criteriaUser(userInfo, multipartFile);
    if (insertUserInfo < 1) {
      throw new CustomException("添加用户信息失败");
    }
    user.setUserInfoId(userInfoId).setUserIsSubmitReview(true).setReviewId(reviewId);
    int updateUser = userMapper.updateByPrimaryKeySelective(user);
    if (updateUser < 1) {
      throw new CustomException("修改账户审核状态失败");
    }
    return user;
  }

  @Override
  public List<User> waitCriteria() {
    Example example = new Example(User.class);
    example.createCriteria().andIsNotNull("userInfoId").andEqualTo("userIsByReview", false)
        .andEqualTo("userIsSubmitReview", true);
    return userMapper.selectByExample(example);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class,
      CustomException.class})
  public int dealWithCriteria(String userId, boolean isPass, String reviewMessage) {
    if (userId == null || "".equals(userId.trim()) || userId.trim().isEmpty()) {
      return 0;
    }
    User user = userMapper.selectByPrimaryKey(userId);
    if (user == null) {
      throw new CustomException("没有该用户");
    }
    Review review = new Review().setReviewId(user.getReviewId()).setReviewMessage(reviewMessage);
    int updateReview = reviewMapper.updateByPrimaryKeySelective(review);
    if (updateReview < 1) {
      throw new CustomException("更新审核信息失败");
    }
    int updateUser = dealWithCriteria(userId, isPass);
    if (updateUser < 1) {
      throw new CustomException("更新用户信息失败");
    }
    return 1;
  }

  @Override
  public int dealWithCriteria(String userId, boolean isPass) {
    if (userId == null || "".equals(userId.trim()) || userId.trim().isEmpty()) {
      return 0;
    }
    User user = new User().setUserId(userId).setUserIsSubmitReview(isPass)
        .setUserIsByReview(isPass);
    return userMapper.updateByPrimaryKeySelective(user);
  }

  @Override
  public Review findReviewById(String reviewId) {
    if (reviewId == null || "".equals(reviewId.trim()) || reviewId.trim().isEmpty()) {
      return null;
    }
    return reviewMapper.selectByPrimaryKey(reviewId);
  }

  @Override
  public UserInfo findUserInfoById(String userInfoId) {
    if (userInfoId == null || "".equals(userInfoId.trim()) || userInfoId.trim().isEmpty()) {
      return null;
    }
    return userInfoMapper.selectByPrimaryKey(userInfoId);
  }

}
