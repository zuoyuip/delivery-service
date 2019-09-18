package org.zuoyu.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zuoyu.dao.ReviewMapper;
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

  public CriteriaServiceImpl(UserMapper userMapper, CriteriaManager criteriaManager,
      ReviewMapper reviewMapper) {
    this.userMapper = userMapper;
    this.criteriaManager = criteriaManager;
    this.reviewMapper = reviewMapper;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class,
      CustomException.class})
  public int applicationCriteria(UserInfo userInfo, MultipartFile multipartFile)
      throws IOException {
    User user = UserUtil.currentUser();
    String userInfoId = UUIDGenerated.obtain();
    String reviewId = UUIDGenerated.obtain();
    user.setUserInfoId(userInfoId).setUserIsSubmitReview(true).setReviewId(reviewId);
    int updateUser = userMapper.updateByPrimaryKeySelective(user);
    if (updateUser < 1) {
      throw new CustomException("修改账户审核状态失败");
    }
    userInfo.setUserInfoId(userInfoId);
    int insertUserInfo = criteriaManager.criteriaUser(userInfo, multipartFile);
    if (insertUserInfo < 1) {
      throw new CustomException("添加用户信息失败");
    }
    Review review = new Review().setReviewIsBy(false).setReviewDate(new Date())
        .setUserId(user.getUserId()).setReviewId(reviewId);
    int insertReview = reviewMapper.insertSelective(review);
    if (insertReview < 1) {
      throw new CustomException("添加审核信息失败");
    }
    return 1;
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
    return 0;
  }

  @Override
  public int dealWithCriteria(String userId, boolean isPass) {
    User user = new User().setUserId(userId).setUserIsSubmitReview(isPass)
        .setUserIsByReview(isPass);
    return userMapper.updateByPrimaryKeySelective(user);
  }

}
