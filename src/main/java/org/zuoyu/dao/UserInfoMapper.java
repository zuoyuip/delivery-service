package org.zuoyu.dao;

import org.springframework.stereotype.Repository;
import org.zuoyu.model.UserInfo;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zuoyu
 */
@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
}