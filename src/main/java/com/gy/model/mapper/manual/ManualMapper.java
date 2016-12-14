package com.gy.model.mapper.manual;

import com.gy.model.pojo.TbOrderOperator;

import java.util.List;
import java.util.Map;

public interface ManualMapper {

    List<TbOrderOperator> selectOperationList(Map<String,Object> paramMap);

    int countOperationNum();

}