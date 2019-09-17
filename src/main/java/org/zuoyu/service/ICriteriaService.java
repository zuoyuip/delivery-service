package org.zuoyu.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.zuoyu.model.UserInfo;

/**
 * 审核服务.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-17 16:57
 **/
public interface ICriteriaService {

  /**
   * 申请审核
   * @param userInfo - 用户信息
   * @param multipartFile - 图片文件
   * @return - 申请成功1,申请失败0
   */
  int applicationCriteria(UserInfo userInfo, MultipartFile multipartFile) throws IOException;
}
