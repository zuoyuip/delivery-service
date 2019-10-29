package org.zuoyu.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 审核用户模型.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-10-29 15:15
 **/
@ApiModel(value = "CriteriaModel", description = "审核包装")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaModel implements Serializable {

  /**
   * 用户ID
   */
  private String userId;

  /**
   * 用户详情ID
   */
  private String userInfoId;

  /**
   * 用户审核ID
   */
  private String reviewId;

  /**
   * 审核姓名
   */
  private String userName;

  /**
   * 手机号
   */
  private String userPhone;

  /**
   * 审核提交日期
   */
  @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
  private Date criteriaTime;

  /**
   * 性别
   */
  private String sex;

  /**
   * 学院
   */
  private String college;

  /**
   * 专业
   */
  private String subject;

  /**
   * 班级
   */
  private String userClass;

  /**
   * 学号
   */
  private String studentNumber;

  /**
   * 图片地址
   */
  private String photoUrl;

  /**
   * 缩略图
   */
  private String thumbPhotoUrl;
}
