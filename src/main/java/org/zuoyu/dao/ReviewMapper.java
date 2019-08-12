package org.zuoyu.dao;

import org.springframework.stereotype.Repository;
import org.zuoyu.model.Review;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zuoyu
 */
@Repository
public interface ReviewMapper extends Mapper<Review> {
}