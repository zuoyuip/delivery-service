package org.zuoyu.manager;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zuoyu.model.UserInfo;

/**
 * 短信的通用业务层.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-10-09 16:43
 **/
@Slf4j
@Component
public class SendSmsManager {

  private final IAcsClient acsClient;

  public SendSmsManager(IAcsClient acsClient) {
    this.acsClient = acsClient;
  }

  /**
   * 注册验证码发送
   *
   * @param phoneNumbers - 手机号
   * @param code - 验证码
   * @return 状态
   */
  public String registerCode(String phoneNumbers, String code) {
    return verificationCodeTemplate("SMS_175120712", phoneNumbers, code);
  }

  /**
   * 修改帐号验证码发送
   *
   * @param phoneNumbers - 手机号
   * @param code - 验证码
   * @return 状态
   */
  public String modifyUser(String phoneNumbers, String code) {
    return verificationCodeTemplate("SMS_175125620", phoneNumbers, code);
  }

  /**
   * 重置密码验证码发送
   *
   * @param phoneNumbers - 手机号
   * @param code - 验证码
   * @return 状态
   */
  public String forgetUser(String phoneNumbers, String code) {
    return verificationCodeTemplate("SMS_175120714", phoneNumbers, code);
  }

  /**
   * 订单对接
   *
   * @param phoneNumbers - 手机号码
   * @param userInfo - 用户信息
   * @param userPhone - 用户手机号码
   * @return - 状态
   * @throws ClientException -
   */
  public String transaction(String phoneNumbers, UserInfo userInfo, String userPhone)
      throws ClientException {
    if (userInfo == null) {
      return null;
    }
    String templateParam = "{ \"college\": \"" + userInfo.getUserInfoCollege()
        + "\",\"subject\": \"" + userInfo.getUserInfoSubject()
        + "\", \"class\": \"" + userInfo.getUserInfoClass()
        + "\",\"name\": \"" + userInfo.getUserInfoName()
        + "\",\"phone\": \"" + userPhone + "\"}";
    return smsTemplate(phoneNumbers, "SMS_175120731", templateParam);
  }

  private String verificationCodeTemplate(String templateCode, String phoneNumbers, String code) {
    String templateParam = "{\"code\":\"" + code + "\"}";
    String result = null;
    try {
      result = smsTemplate(phoneNumbers, templateCode, templateParam);
    } catch (ClientException e) {
      e.printStackTrace();
    }
    return result;
  }


  private String smsTemplate(String phoneNumbers, String templateCode, String templateParam)
      throws ClientException {
    CommonRequest commonRequest = new CommonRequest();
    commonRequest.setMethod(MethodType.POST);
    commonRequest.setDomain("dysmsapi.aliyuncs.com");
    commonRequest.setVersion("2017-05-25");
    commonRequest.setAction("SendSms");
    commonRequest.setRegionId("cn-hangzhou");
    commonRequest.putQueryParameter("PhoneNumbers", phoneNumbers);
    commonRequest.putQueryParameter("SignName", "顺便取");
    commonRequest.putQueryParameter("TemplateCode", templateCode);
    commonRequest.putQueryParameter("TemplateParam", templateParam);
    CommonResponse commonResponse = acsClient.getCommonResponse(commonRequest);
    String response = commonResponse.getData();
    log.info("phone:" + phoneNumbers + "\t" + response);
    return response;
  }

}
