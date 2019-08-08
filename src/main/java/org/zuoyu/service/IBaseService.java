package org.zuoyu.service;

import com.github.pagehelper.PageRowBounds;
import java.util.List;
import tk.mybatis.mapper.entity.Example;

/**
 * 公共资源整合接口.
 *
 * @author ZuoYu
 **/

public interface IBaseService<T> {

  /**
   * 根据唯一主键删除对应数据.
   *
   * @param o - 唯一主键
   * @return int - 变动数据的个数
   */
  int deleteByPrimaryKey(Object o);

  /**
   * 删除该数据.
   *
   * @param t - 要删除的数据
   * @return int - 变动数据的个数
   */
  int delete(T t);

  /**
   * 插入该数据.
   *
   * @param t - 要插入的数据
   * @return int - 变动数据的个数
   */
  int insert(T t);

  /**
   * 选择性保存该实体类.
   *
   * @param t - 实体类
   * @return int - 变动数据的个数
   */
  int insertSelective(T t);

  /**
   * 判断该主键是否已经存在.
   *
   * @param o - 要判断的主键
   * @return boolean - 结果
   */
  boolean existsWithPrimaryKey(Object o);

  /**
   * 返回所有数据.
   *
   * @return List - 所有数据
   */
  List<T> selectAll();

  /**
   * 根据唯一主键查询该数据.
   *
   * @param o - 唯一主键
   * @return T - 对应的数据
   */
  T selectByPrimaryKey(Object o);

  /**
   * 根据实体类不为null的字段查询总数.
   *
   * @param t - 实体类
   * @return int - 总数量
   */
  int selectCount(T t);

  /**
   * 根据实体类不为null的字段进行查询.
   *
   * @param t - 实体类
   * @return List - 符合条件的所有数据
   */
  List<T> select(T t);

  /**
   * 根据实体类不为null的字段进行查询一条数据.
   *
   * @param t - 实体类
   * @return T - 符合条件的数据
   */
  T selectOne(T t);

  /**
   * 根据主键进行更新数据.
   *
   * @param t - 要更改的数据
   * @return int - 变动数据的个数
   */
  int updateByPrimaryKey(T t);

  /**
   * 根据主键进行更新，只会更新不为null的数据.
   *
   * @param t - 要更改的数据
   * @return int - 变动数据的个数
   */
  int updateByPrimaryKeySelective(T t);

  /**
   * 根据模板删除相似数据.
   *
   * @param example - 模板
   * @return int - 变动数据的个数
   */
  int deleteByExample(Example example);

  /**
   * 根据模板查询相似数据.
   *
   * @param example - 模板
   * @return List - 相似的数据
   */
  List<T> selectByExample(Example example);

  /**
   * 根据模板查询相似数据的个数.
   *
   * @param example - 模板
   * @return int - 相似数据的个数
   */
  int selectCountByExample(Example example);

  /**
   * 根据模板查询一个相似的数据.
   *
   * @param example - 模板
   * @return T - 相似的数据
   */
  T selectOneByExample(Example example);

  /**
   * 根据模板修改相似的数据.
   *
   * @param t - 想要修改的结果
   * @param example - 模板
   * @return int - 变动数据的个数
   */
  int updateByExample(T t, Example example);

  /**
   * 根据模板修改相似的数据,只会更新不为null的数据.
   *
   * @param t - 想要修改的结果
   * @param example - 模板
   * @return int - 变动数据的个数
   */
  int updateByExampleSelective(T t, Example example);

  /**
   * 根据模板实现分页查询.
   *
   * @param example - 模板
   * @param rowBounds - 分页信息
   * @return List - 获取到的数据
   */
  List<T> selectByExampleAndRowBounds(Example example, PageRowBounds rowBounds);


  /**
   * 根据实体类实现分页查询.
   *
   * @param t - 实体类
   * @param rowBounds - 分页信息
   * @return List - 获取到的数据
   */
  List<T> selectByRowBounds(T t, PageRowBounds rowBounds);

}
