package org.zuoyu.service.impl;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zuoyu.dao.UserMapper;
import org.zuoyu.exception.CustomException;
import org.zuoyu.manager.CriteriaManager;
import org.zuoyu.model.User;
import org.zuoyu.model.UserInfo;
import org.zuoyu.service.ICriteriaService;
import org.zuoyu.util.UUIDGenerated;
import org.zuoyu.util.UserUtil;

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

  public CriteriaServiceImpl(UserMapper userMapper, CriteriaManager criteriaManager) {
    this.userMapper = userMapper;
    this.criteriaManager = criteriaManager;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class,
      CustomException.class})
  public int applicationCriteria(UserInfo userInfo, MultipartFile multipartFile)
      throws IOException {
    User user = UserUtil.currentUser();
    String userInfoId = UUIDGenerated.obtain();
    user.setUserInfoId(userInfoId).setUserIsSubmitReview(true);
    int updateUser = userMapper.updateByPrimaryKeySelective(user);
    if (updateUser < 1) {
      throw new CustomException("修改账户审核状态失败");
    }
    userInfo.setUserInfoId(userInfoId);
    int insertUserInfo = criteriaManager.criteriaUser(userInfo, multipartFile);
    if (insertUserInfo < 1) {
      throw new CustomException("添加审核信息失败");
    }
    return 1;
  }
}
