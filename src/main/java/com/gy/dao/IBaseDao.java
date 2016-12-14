package com.gy.dao;

import java.util.List;

/**
 * 接口类
 */
public interface IBaseDao<T> {

    void add(T t);

    void update(T t);

    void delete(T t);

    T queryById(String id);

    void batchAdd(List<T> lists);

    List<T> queryByPage(String key ,int size);

    List<T> queryByFuzzy(String key);
}
