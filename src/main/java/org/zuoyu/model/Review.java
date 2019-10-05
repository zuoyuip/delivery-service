package org.zuoyu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 审核信息
 *
 * @author zuoyu
 */
@ApiModel(value = "Review", description = "审核信息")
@Data
@Accessors(chain = true)
@Table(name = "`TB_REVIEW`")
public class Review implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 审核信息的唯一标识（不使用自动主键）
   */
  @Id
  @Column(name = "`REVIEW_ID`")
  @ApiModelProperty(value = "reviewId审核信息的唯一标识")
  private String reviewId;
  /**
   * 审核信息-审核日期
   */
  @Column(name = "`REVIEW_DATE`")
  @ApiModelProperty(value = "reviewDate审核信息-审核日期")
  @JsonFormat(pattern="yyyy年MM月dd日 HH:mm:ss",timezone="GMT+8")
  private Date reviewDate;
  /**
   * 审核信息-审核是否已通过
   */
  @Column(name = "`REVIEW_IS_BY`")
  @ApiModelProperty(value = "reviewIsBy审核信息-审核是否已通过")
  private Boolean reviewIsBy;
  /**
   * 审核信息-审核用户的唯一标识
   */
  @Column(name = "`USER_ID`")
  @ApiModelProperty(value = "userId审核信息-审核用户的唯一标识")
  private String userId;
  /**
   * 审核信息-审核说明
   */
  @Column(name = "`REVIEW_MESSAGE`")
  @ApiModelProperty(value = "reviewMessage审核信息-审核说明")
  private String reviewMessage;

  public Review(String reviewId, Date reviewDate, Boolean reviewIsBy, String userId,
      String reviewMessage) {
    this.reviewId = reviewId;
    this.reviewDate = reviewDate;
    this.reviewIsBy = reviewIsBy;
    this.userId = userId;
    this.reviewMessage = reviewMessage;
  }

  public Review() {
    super();
  }
}