package org.zuoyu.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.zuoyu.model.Review;
import org.zuoyu.model.User;
import org.zuoyu.model.UserInfo;
import org.zuoyu.model.vo.CriteriaModel;

/**
 * 审核服务. 审核状态制定： userInfoId为null； userIsByReview为false； userIsSubmitReview为false ————该用户为从未提交审核。
 * userInfoId不为null； userIsByReview为false； userIsSubmitReview为true ————该用户为已提交审核，但管理员还未处理。
 * userInfoId不为null； userIsByReview为false； userIsSubmitReview为false ————该用户为已提交审核，但被管理员拒绝通过审核。
 * userInfoId不为null； userIsByReview为true； userIsSubmitReview为true ————该用户为已提交审核，并通过管理员的审核。
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-17 16:57
 **/
public interface ICriteriaService {


  /**
   * 申请审核
   *
   * @param userInfo - 用户信息
   * @param multipartFile - 图片文件
   * @return - 更改后的user
   * @throws IOException - 流解析异常
   */
  User applicationCriteria(UserInfo userInfo, MultipartFile multipartFile) throws IOException;


  /**
   * 获取所有待审核用户
   *
   * @return - 待审核用户
   */
  List<CriteriaModel> waitCriteria();


  /**
   * 对用户的申请进行处理
   *
   * @param userId - 帐户ID
   * @param isPass - 是否通过
   * @param reviewMessage - 审核信息
   * @return - 成功返回 1,失败返回 0
   */
  int dealWithCriteria(String userId, boolean isPass, String reviewMessage);

  /**
   * 对用户的申请进行处理
   * @param userId - 帐户ID
   * @param isPass - 是否通过
   * @return - 成功返回 1,失败返回 0
   */
  int dealWithCriteria(String userId, boolean isPass);

  /**
   * 根据唯一标识符获取审核申请
   * @param reviewId - 唯一标识符
   * @return - 审核申请
   */
  Review findReviewById(String reviewId);


  /**
   * 根据唯一标识符获取账户信息
   * @param userInfoId - 唯一标识符
   * @return - 账户信息
   */
  UserInfo findUserInfoById(String userInfoId);

}
