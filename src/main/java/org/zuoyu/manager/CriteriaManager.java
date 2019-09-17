package org.zuoyu.manager;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.zuoyu.dao.UserInfoMapper;
import org.zuoyu.exception.CustomException;
import org.zuoyu.model.UserInfo;

/**
 * 审核通用业务.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-17 10:34
 **/
@Component
public class CriteriaManager {

  /**
   * 解析路径
   */
  private static final String SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR = "/";
  private final UserInfoMapper userInfoMapper;
  private final FastFileStorageClient fastFileStorageClient;
  private final ThumbImageConfig thumbImageConfig;

  public CriteriaManager(UserInfoMapper userInfoMapper, FastFileStorageClient fastFileStorageClient,
      ThumbImageConfig thumbImageConfig) {
    this.userInfoMapper = userInfoMapper;
    this.fastFileStorageClient = fastFileStorageClient;
    this.thumbImageConfig = thumbImageConfig;
  }

  /**
   * 上传图片并添加用户信息
   *
   * @param userInfo - 用户信息
   * @param multipartFile - 图片
   * @return - 添加个数
   */
  public int criteriaUser(UserInfo userInfo, MultipartFile multipartFile) throws IOException {
    if (userInfo == null){
      throw new CustomException("userInfo is null");
    }
    StorePath storePath = fastFileStorageClient
        .uploadImageAndCrtThumbImage(multipartFile.getInputStream(), multipartFile.getSize(),
            FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);
    if (storePath.getPath().isEmpty()){
      throw new CustomException("上传图片失败");
    }
    String group = storePath.getGroup();
    String thumbImagePath = thumbImageConfig.getThumbImagePath(storePath.getPath());
    userInfo.setUserInfoPhotoUrl(storePath.getFullPath())
        .setUserInfoThumbPhotoUrl(group + SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR + thumbImagePath);
    return userInfoMapper.insertSelective(userInfo);
  }
}
