package com.gy.dao;

import com.gy.model.pojo.TbOrderOperator;

import java.util.List;

/**
 * Created by gongyan on 16/12/14.
 */
public interface IDBDao {

    List<TbOrderOperator> batchGetOrderOperatorInfo(Integer startIndex);

    int countOperationNum();
}
