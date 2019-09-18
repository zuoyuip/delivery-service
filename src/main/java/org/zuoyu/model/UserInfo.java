package org.zuoyu.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author zuoyu
 */
@ApiModel(value = "UserInfo", description = "用户详细信息")
@Data
@Accessors(chain = true)
@Table(name = "`TB_USER_INFO`")
public class UserInfo implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 用户信息的唯一标识(该表不使用自动主键)
   */
  @Id
  @Column(name = "`USER_INFO_ID`")
  @ApiModelProperty(value = "userInfoId用户信息的唯一标识")
  private String userInfoId;
  /**
   * 用户信息—名字
   */
  @Column(name = "`USER_INFO_NAME`")
  @ApiModelProperty(value = "userInfoName用户信息—名字")
  private String userInfoName;
  /**
   * 用户信息-学院
   */
  @Column(name = "`USER_INFO_COLLEGE`")
  @ApiModelProperty(value = "userInfoCollege用户信息-学院")
  private String userInfoCollege;
  /**
   * 用户信息-专业
   */
  @Column(name = "`USER_INFO_SUBJECT`")
  @ApiModelProperty(value = "userInfoSubject用户信息-专业")
  private String userInfoSubject;
  /**
   * 用户信息-班级
   */
  @Column(name = "`USER_INFO_CLASS`")
  @ApiModelProperty(value = "userInfoClass用户信息-班级")
  private String userInfoClass;
  /**
   * 用户信息-学号
   */
  @Column(name = "`USER_INFO_STUDENT_NUMBER`")
  @ApiModelProperty(value = "userInfoStudentNumber用户信息-学号")
  private String userInfoStudentNumber;
  /**
   * 用户信息-审核图片
   */
  @Column(name = "`USER_INFO_PHOTO_URL`")
  @ApiModelProperty(value = "userInfoPhotoUrl用户信息-审核图片")
  private String userInfoPhotoUrl;
  /**
   * 用户信息-审核缩略图片
   */
  @Column(name = "`USER_INFO_THUMB_PHOTO_URL`")
  @ApiModelProperty(value = "userInfoThumbPhotoUrl用户信息-审核缩略图片")
  private String userInfoThumbPhotoUrl;

  public UserInfo(String userInfoId, String userInfoName, String userInfoCollege,
      String userInfoSubject, String userInfoClass, String userInfoStudentNumber,
      String userInfoPhotoUrl, String userInfoThumbPhotoUrl) {
    this.userInfoId = userInfoId;
    this.userInfoName = userInfoName;
    this.userInfoCollege = userInfoCollege;
    this.userInfoSubject = userInfoSubject;
    this.userInfoClass = userInfoClass;
    this.userInfoStudentNumber = userInfoStudentNumber;
    this.userInfoPhotoUrl = userInfoPhotoUrl;
    this.userInfoThumbPhotoUrl = userInfoThumbPhotoUrl;
  }

  public UserInfo() {
    super();
  }
}