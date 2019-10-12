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
import org.zuoyu.util.UUIDGenerated;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 包裹信息
 *
 * @author zuoyu
 */
@ApiModel(value = "Delivery", description = "包裹信息")
@Data
@Accessors(chain = true)
@Table(name = "`TB_DELIVERY`")
public class Delivery implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 包裹信息-唯一标识
   */
  @Id
  @KeySql(genId = UUIDGenerated.class)
  @Column(name = "`DELIVERY_ID`")
  @ApiModelProperty(value = "deliveryId包裹信息-唯一标识")
  private String deliveryId;

  /**
   * 包裹信息-唯一标识
   */
  @Column(name = "`DELIVERY_ORDER_CODE`")
  @ApiModelProperty(value = "deliveryOrderCode包裹信息-订单编号")
  private String deliveryOrderCode;

  /**
   * 包裹信息-快递名称
   */
  @Column(name = "`DELIVERY_NAME`")
  @ApiModelProperty(value = "deliveryName包裹信息-快递名称")
  private String deliveryName;

  /**
   * 包裹信息-快递取货号
   */
  @Column(name = "`DELIVERY_CODE`")
  @ApiModelProperty(value = "deliveryCode包裹信息-快递取货号")
  private String deliveryCode;

  /**
   * 包裹信息-包裹的收货人名字
   */
  @Column(name = "`DELIVERY_USER_NAME`")
  @ApiModelProperty(value = "deliveryUserName包裹信息-包裹的收货人名字")
  private String deliveryUserName;

  /**
   * 包裹信息-包裹的收货人名字
   */
  @Column(name = "`DELIVERY_USER_PHONE`")
  @ApiModelProperty(value = "deliveryUserPhone包裹信息-包裹的收货人手机号")
  private String deliveryUserPhone;

  /**
   * 包裹信息-包裹收货人性别
   */
  @Column(name = "`DELIVERY_USER_SEX`")
  @ApiModelProperty(value = "deliveryUserSex包裹信息-包裹收货人性别")
  private String deliveryUserSex;

  /**
   * 包裹信息-包裹所在地址
   */
  @Column(name = "`DELIVERY_ADDRESS`")
  @ApiModelProperty(value = "deliveryAddress包裹信息-包裹所在地址")
  private String deliveryAddress;

  /**
   * 包裹信息-包裹要送达的楼号
   */
  @Column(name = "`DELIVERY_GOAL_FLOOR`")
  @ApiModelProperty(value = "deliveryGoalFloor包裹信息-包裹要送达的楼号")
  private String deliveryGoalFloor;

  /**
   * 包裹信息-包裹要送达的地址
   */
  @Column(name = "`DELIVERY_GOAL_ADDRESS`")
  @ApiModelProperty(value = "deliveryGoalAddress包裹信息-包裹要送达的地址")
  private String deliveryGoalAddress;

  /**
   * 包裹信息-包裹重量
   */
  @Column(name = "`DELIVERY_WEIGHT`")
  @ApiModelProperty(value = "deliveryWeight包裹信息-包裹重量")
  private String deliveryWeight;

  /**
   * 包裹信息-包裹的赏金
   */
  @Column(name = "`DELIVERY_REWARD`")
  @ApiModelProperty(value = "deliveryReward包裹信息-包裹的赏金")
  private String deliveryReward;

  /**
   * 包裹信息-备注
   */
  @Column(name = "`DELIVERY_REMARK`")
  @ApiModelProperty(value = "deliveryRemark包裹信息-备注")
  private String deliveryRemark;

  /**
   * 包裹信息-日期
   */
  @Column(name = "`DELIVERY_DATE`")
  @ApiModelProperty(value = "deliveryDate包裹信息-日期")
  @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
  private Date deliveryDate;

  /**
   * 包裹信息-发布者的安全用户唯一标识
   */
  @Column(name = "`DELIVERY_USER_ID`")
  @ApiModelProperty(value = "deliveryUserId包裹信息-发布者的安全用户唯一标识")
  private String deliveryUserId;

  /**
   * 包裹信息-包裹工作者的安全用户唯一标识
   */
  @Column(name = "`DELIVERY_DELIVERY_USER_ID`")
  @ApiModelProperty(value = "deliveryDeliveryUserId包裹信息-包裹工作者的安全用户唯一标识")
  private String deliveryDeliveryUserId;

  /**
   * 包裹信息-包裹是否被接单
   */
  @Column(name = "`DELIVERY_STATUS`")
  @ApiModelProperty(value = "deliveryStatus包裹信息-包裹是否被接单")
  private Boolean deliveryStatus;

  public Delivery(String deliveryId, String deliveryName, String deliveryCode,
      String deliveryUserName, String deliveryUserSex, String deliveryAddress,
      String deliveryGoalAddress, String deliveryWeight, String deliveryReward,
      String deliveryRemark, Date deliveryDate, String deliveryUserId,
      String deliveryDeliveryUserId, Boolean deliveryStatus) {
    this.deliveryId = deliveryId;
    this.deliveryName = deliveryName;
    this.deliveryCode = deliveryCode;
    this.deliveryUserName = deliveryUserName;
    this.deliveryUserSex = deliveryUserSex;
    this.deliveryAddress = deliveryAddress;
    this.deliveryGoalAddress = deliveryGoalAddress;
    this.deliveryWeight = deliveryWeight;
    this.deliveryReward = deliveryReward;
    this.deliveryRemark = deliveryRemark;
    this.deliveryDate = deliveryDate;
    this.deliveryUserId = deliveryUserId;
    this.deliveryDeliveryUserId = deliveryDeliveryUserId;
    this.deliveryStatus = deliveryStatus;
  }

  public Delivery() {
    super();
  }
}