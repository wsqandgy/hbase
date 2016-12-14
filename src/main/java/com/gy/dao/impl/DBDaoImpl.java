package com.gy.dao.impl;

import com.gy.dao.IDBDao;
import com.gy.model.mapper.manual.ManualMapper;
import com.gy.model.pojo.TbOrderOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gongyan on 16/12/14.
 */
@Repository
public class DBDaoImpl implements IDBDao{

    private static final Logger logger = LoggerFactory.getLogger(DBDaoImpl.class);

    @Resource
    private ManualMapper manualMapper;

    @Override
    public List<TbOrderOperator> batchGetOrderOperatorInfo(Integer startIndex) {

        Map<String,Object> stringObjectMap = new HashMap<String,Object>();
        stringObjectMap.put("start",startIndex);
        return manualMapper.selectOperationList(stringObjectMap);

    }

    @Override
    public int countOperationNum() {
        return manualMapper.countOperationNum();
    }


}
