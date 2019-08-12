package org.zuoyu.dao;

import org.springframework.stereotype.Repository;
import org.zuoyu.model.Suggest;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zuoyu
 */
@Repository
public interface SuggestMapper extends Mapper<Suggest> {
}