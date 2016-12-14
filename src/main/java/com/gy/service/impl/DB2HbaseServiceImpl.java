package com.gy.service.impl;

import com.gy.dao.IBaseDao;
import com.gy.dao.IDBDao;
import com.gy.model.OrderOperatorBean;
import com.gy.model.pojo.TbOrderOperator;
import com.gy.service.DB2HbaseService;
import com.gy.utils.CommonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongyan on 16/12/14.
 */
@Service
public class DB2HbaseServiceImpl implements DB2HbaseService{

    private static final Logger logger = LoggerFactory.getLogger(DB2HbaseServiceImpl.class);

    @Resource
    private IDBDao dbDaoImpl;

    @Resource
    private IBaseDao<OrderOperatorBean> hbaseDaoImpl;

    @Override
    public void transDB2Hbase() {
        long startTime = System.currentTimeMillis();
        logger.info("开始导出数据从数据库到Hbase >>>>> ");

        int count = dbDaoImpl.countOperationNum();
        logger.info("共需要导入{}条数据",count);
        int times = count / 200 + 1;
        for (int i = 0; i < times; i++) {
            logger.info("开始导入第{}次",i);
            long innerStartTime = System.currentTimeMillis();
            int startIndex = i * 200;
            List<TbOrderOperator> tbOrderOperators = dbDaoImpl.batchGetOrderOperatorInfo(startIndex);
            if(CollectionUtils.isNotEmpty(tbOrderOperators)){
                List<OrderOperatorBean> orderOperatorBeanList = new ArrayList<>();
                for(TbOrderOperator tbOrderOperator : tbOrderOperators){
                    OrderOperatorBean orderOperatorBean = CommonUtils.parseDB2Hbase(tbOrderOperator);
                    orderOperatorBeanList.add(orderOperatorBean);
                }
                hbaseDaoImpl.batchAdd(orderOperatorBeanList);
            }
            logger.info("导入第{}次完成!耗时:{}ms",i,(System.currentTimeMillis() - innerStartTime));
        }
        logger.info("导出数据完成! 耗时:{}",(System.currentTimeMillis() - startTime));
    }
}
