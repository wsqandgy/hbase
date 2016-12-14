package com.gy.dao;

/**
 * 接口类
 */
public interface IBaseDao<T> {

    void add(T t);

    void update(T t);

    void delete(T t);

    T queryById(String id);
}
