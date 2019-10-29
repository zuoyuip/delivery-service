package org.zuoyu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.zuoyu.util.UUIDGenerated;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 安全用户
 *
 * @author zuoyu
 */
@ApiModel(value = "User", parent = UserDetails.class, description = "安全账户")
@Data
@Accessors(chain = true)
@Table(name = "`TB_USER`")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;
  /**
   * 安全用户唯一标识
   */
  @Id
  @KeySql(genId = UUIDGenerated.class)
  @Column(name = "`USER_ID`")
  @ApiModelProperty(value = "userId安全用户唯一标识")
  private String userId;
  /**
   * 安全用户帐号（手机号）
   */
  @Column(name = "`USER_PHONE`")
  @ApiModelProperty(value = "userPhone安全用户帐号（手机号）")
  private String userPhone;
  /**
   * 安全用户的密码
   */
  @Column(name = "`USER_PASSWORD`")
  @ApiModelProperty(value = "userPassword安全用户的密码")
  private String userPassword;

  /**
   * 该安全用户帐号是否已注册
   */
  @Column(name = "`USER_IS_VALID`")
  @ApiModelProperty(value = "userIsValid该安全用户帐号是否已注册")
  private Boolean userIsValid;
  /**
   * 该安全用户帐号是否启用
   */
  @Column(name = "`USER_IS_ENABLED`")
  @ApiModelProperty(value = "userIsEnabled该安全用户帐号是否启用")
  private Boolean userIsEnabled;
  /**
   * 该安全用户帐号是否未过期
   */
  @Column(name = "`USER_IS_ACCOUNT_NON_EXPIRED`")
  @ApiModelProperty(value = "userIsAccountNonExpired该安全用户帐号是否未过期")
  private Boolean userIsAccountNonExpired;
  /**
   * 该安全用户帐号凭证是否未过期
   */
  @Column(name = "`USER_IS_CREDENTIALS_NON_EXPIRED`")
  @ApiModelProperty(value = "userIsCredentialsNonExpired该安全用户帐号凭证是否未过期")
  private Boolean userIsCredentialsNonExpired;
  /**
   * 该安全用户帐号是否未锁定
   */
  @Column(name = "`USER_IS_ACCOUNT_NON_LOCKED`")
  @ApiModelProperty(value = "userIsAccountNonLocked该安全用户帐号是否未锁定")
  private Boolean userIsAccountNonLocked;
  /**
   * 该安全用户帐号是否已通过审核
   */
  @Column(name = "`USER_IS_BY_REVIEW`")
  @ApiModelProperty(value = "userIsByReview该安全用户帐号是否已通过审核")
  private Boolean userIsByReview;
  /**
   * 该安全用户账户是否已提交用户审核
   */
  @Column(name = "`USER_IS_SUBMIT_REVIEW`")
  @ApiModelProperty(value = "userIsSubmitReview该安全用户账户是否已提交用户审核")
  private Boolean userIsSubmitReview;
  /**
   * 该帐号对应用户信息的唯一标识
   */
  @Column(name = "`USER_INFO_ID`")
  @ApiModelProperty(value = "userInfoId该帐号对应用户信息的唯一标识")
  private String userInfoId;
  /**
   * 该帐号对应的审核信息唯一标识
   */
  @Column(name = "`REVIEW_ID`")
  @ApiModelProperty(value = "reviewId该帐号对应的审核信息唯一标识")
  private String reviewId;


  @JsonIgnore
  @Override
  @ApiModelProperty(hidden = true)
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public String getPassword() {
    return this.userPassword;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public String getUsername() {
    return this.userPhone;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public boolean isAccountNonExpired() {
    return this.userIsAccountNonExpired;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public boolean isAccountNonLocked() {
    return this.userIsAccountNonLocked;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public boolean isCredentialsNonExpired() {
    return this.userIsCredentialsNonExpired;
  }

  @Override
  @ApiModelProperty(hidden = true)
  public boolean isEnabled() {
    return this.userIsEnabled;
  }
}