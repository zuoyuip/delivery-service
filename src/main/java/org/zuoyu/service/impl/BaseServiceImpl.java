package org.zuoyu.service.impl;

import com.github.pagehelper.PageRowBounds;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.zuoyu.service.IBaseService;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 对公共资源整合借口的抽象实现.
 *
 * @author zuoyu
 **/

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

  /**
   * 对应的mapper
   */
  @Autowired
  protected Mapper<T> mapper;


  /**
   * 根据唯一主键删除对应数据.
   *
   * @param o - 唯一主键
   * @return int - 变动数据的个数
   */
  @Override
  public int deleteByPrimaryKey(Object o) {
    return mapper.deleteByPrimaryKey(o);
  }

  /**
   * 删除该数据.
   *
   * @param t - 要删除的数据
   * @return int - 变动数据的个数
   */
  @Override
  public int delete(T t) {
    return mapper.delete(t);
  }

  /**
   * 插入该数据.
   *
   * @param t - 要插入的数据
   * @return int - 变动数据的个数
   */
  @Override
  public int insert(T t) {
    return mapper.insert(t);
  }

  /**
   * 选择性保存该实体类.
   *
   * @param t - 实体类
   * @return int - 变动数据的个数
   */
  @Override
  public int insertSelective(T t) {
    return mapper.insertSelective(t);
  }

  /**
   * 判断该主键是否已经存在.
   *
   * @param o - 要判断的主键
   * @return boolean - 结果
   */
  @Override
  public boolean existsWithPrimaryKey(Object o) {
    return mapper.existsWithPrimaryKey(o);
  }

  /**
   * 返回所有数据.
   *
   * @return List - 所有数据
   */
  @Override
  public List<T> selectAll() {
    return mapper.selectAll();
  }

  /**
   * 根据唯一主键查询该数据.
   *
   * @param o - 唯一主键
   * @return T - 对应的数据
   */
  @Override
  public T selectByPrimaryKey(Object o) {
    return mapper.selectByPrimaryKey(o);
  }

  /**
   * 根据实体类不为null的字段查询总数.
   *
   * @param t - 实体类
   * @return int - 总数量
   */
  @Override
  public int selectCount(T t) {
    return mapper.selectCount(t);
  }

  /**
   * 根据实体类不为null的字段进行查询.
   *
   * @param t - 实体类
   * @return List - 符合条件的所有数据
   */
  @Override
  public List<T> select(T t) {
    return mapper.select(t);
  }

  /**
   * 根据实体类不为null的字段进行查询一条数据.
   *
   * @param t - 实体类
   * @return T - 符合条件的数据
   */
  @Override
  public T selectOne(T t) {
    return mapper.selectOne(t);
  }

  /**
   * 根据主键进行更新数据.
   *
   * @param t - 要更改的数据
   * @return int - 变动数据的个数
   */
  @Override
  public int updateByPrimaryKey(T t) {
    return mapper.updateByPrimaryKey(t);
  }

  /**
   * 根据主键进行更新，只会更新不为null的数据.
   *
   * @param t - 要更改的数据
   * @return int - 变动数据的个数
   */
  @Override
  public int updateByPrimaryKeySelective(T t) {
    return mapper.updateByPrimaryKeySelective(t);
  }

  /**
   * 根据模板删除相似数据.
   *
   * @param example - 模板
   * @return int - 变动数据的个数
   */
  @Override
  public int deleteByExample(Example example) {
    return mapper.deleteByExample(example);
  }

  /**
   * 根据模板查询相似数据.
   *
   * @param example - 模板
   * @return List - 相似的数据
   */
  @Override
  public List<T> selectByExample(Example example) {
    return mapper.selectByExample(example);
  }

  /**
   * 根据模板查询相似数据的个数.
   *
   * @param example - 模板
   * @return int - 相似数据的个数
   */
  @Override
  public int selectCountByExample(Example example) {
    return mapper.selectCountByExample(example);
  }

  /**
   * 根据模板查询一个相似的数据.
   *
   * @param example - 模板
   * @return T - 相似的数据
   */
  @Override
  public T selectOneByExample(Example example) {
    return mapper.selectOneByExample(example);
  }

  /**
   * 根据模板修改相似的数据.
   *
   * @param t - 想要修改的结果
   * @param example - 模板
   * @return int - 变动数据的个数
   */
  @Override
  public int updateByExample(T t, Example example) {
    return mapper.updateByExample(t, example);
  }

  /**
   * 根据模板修改相似的数据,只会更新不为null的数据.
   *
   * @param t - 想要修改的结果
   * @param example - 模板
   * @return int - 变动数据的个数
   */
  @Override
  public int updateByExampleSelective(T t, Example example) {
    return mapper.updateByExampleSelective(t, example);
  }

  /**
   * 根据模板实现分页查询.
   *
   * @param example - 模板
   * @param rowBounds - 分页信息
   * @return List - 获取到的数据
   */
  @Override
  public List<T> selectByExampleAndRowBounds(Example example, PageRowBounds rowBounds) {
    return mapper.selectByExampleAndRowBounds(example, rowBounds);
  }

  /**
   * 根据实体类实现分页查询.
   *
   * @param t - 实体类
   * @param rowBounds - 分页信息
   * @return List - 获取到的数据
   */
  @Override
  public List<T> selectByRowBounds(T t, PageRowBounds rowBounds) {
    return mapper.selectByRowBounds(t, rowBounds);
  }

}
