package com.gy.dao.impl;

import com.gy.dao.IBaseDao;
import com.gy.model.OrderOperatorBean;
import com.gy.utils.CommonUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.NavigableMap;

/**
 * 操作Hbase的Dao
 */
@Repository
public class HbaseDaoImpl implements IBaseDao<OrderOperatorBean>{

    private final static Logger logger = LoggerFactory.getLogger(HbaseDaoImpl.class);

    public final static String TABLE_ORDEROPERATOR_NAME = "tb_order_operator";

    public static Configuration configuration;
    public static HTable hTable = null;
    static {
        configuration = new Configuration();
        try {
            hTable = new HTable(configuration,TABLE_ORDEROPERATOR_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加数据到Hbase中
     *
     * 参数
     *     订单操作记录
     *
     * 需要归还操作池中的数据
     */
    @Override
    public void add(OrderOperatorBean orderOperatorBean) {
        // 生成主键名称
        byte[] keyBytes = (orderOperatorBean.getOrderId() + ":" + System.currentTimeMillis()).getBytes();

        Put put = new Put(keyBytes);
        // 增加行键
        put.addColumn(Bytes.toBytes("info"),"orderId".getBytes(),orderOperatorBean.getOrderId().getBytes());
        put.addColumn(Bytes.toBytes("info"),"orderState".getBytes(),orderOperatorBean.getOrderState().getBytes());
        put.addColumn(Bytes.toBytes("info"),"userId".getBytes(),orderOperatorBean.getUserId().getBytes());
        put.addColumn(Bytes.toBytes("info"),"sellerId".getBytes(),orderOperatorBean.getSellerId().getBytes());
        put.addColumn(Bytes.toBytes("info"),"orderTableName".getBytes(),orderOperatorBean.getOrderTableName().getBytes());
        put.addColumn(Bytes.toBytes("info"),"optType".getBytes(),orderOperatorBean.getOptType().getBytes());
        put.addColumn(Bytes.toBytes("info"),"optContent".getBytes(),orderOperatorBean.getOptType().getBytes());
        put.addColumn(Bytes.toBytes("info"),"optTime".getBytes(),orderOperatorBean.getOptTime().getBytes());
        put.addColumn(Bytes.toBytes("info"),"createTime".getBytes(),orderOperatorBean.getCreateTime().getBytes());

        try {
            hTable.put(put);
        } catch (Exception e) {
            logger.error("插入数据库发生异常!{}", e);
        } finally {
        }
    }

    /**
     *
     * @param orderOperatorBean
     */
    @Override
    public void update(OrderOperatorBean orderOperatorBean) {
        // 生成主键名称
        byte[] keyBytes = (orderOperatorBean.getRowKey()).getBytes();
        Put put = new Put(keyBytes);
        // 增加行键
        put.addColumn(Bytes.toBytes("info"),"orderId".getBytes(),orderOperatorBean.getOrderId().getBytes());
        put.addColumn(Bytes.toBytes("info"),"orderState".getBytes(),orderOperatorBean.getOrderState().getBytes());
        put.addColumn(Bytes.toBytes("info"),"userId".getBytes(),orderOperatorBean.getUserId().getBytes());
        put.addColumn(Bytes.toBytes("info"),"sellerId".getBytes(),orderOperatorBean.getSellerId().getBytes());
        put.addColumn(Bytes.toBytes("info"),"orderTableName".getBytes(),orderOperatorBean.getOrderTableName().getBytes());
        put.addColumn(Bytes.toBytes("info"),"optType".getBytes(),orderOperatorBean.getOptType().getBytes());
        put.addColumn(Bytes.toBytes("info"),"optContent".getBytes(),orderOperatorBean.getOptContent().getBytes());
        put.addColumn(Bytes.toBytes("info"),"optTime".getBytes(),orderOperatorBean.getOptTime().getBytes());
        put.addColumn(Bytes.toBytes("info"),"createTime".getBytes(),orderOperatorBean.getCreateTime().getBytes());
        try {
            hTable.put(put);
        } catch (Exception e) {
            logger.error("更新数据库发生异常!{}", e);
        } finally {
        }
    }

    @Override
    public void delete(OrderOperatorBean orderOperatorBean) {

    }

    @Override
    public OrderOperatorBean queryById(String id) {
        OrderOperatorBean orderOperatorBean = new OrderOperatorBean();
        Get get = new Get(Bytes.toBytes(id));
        HTable table = hTable;
        Result result = null;
        try {
            result = table.get(get);
            NavigableMap<byte[], byte[]> navigableMap = result.getFamilyMap(Bytes.toBytes("info"));
            orderOperatorBean = CommonUtils.parseKV2OrderOperatorBean(navigableMap);
        } catch (IOException e) {
            logger.error("查询数据库发生异常!{}", e);
        } finally {
        }
        return orderOperatorBean;
    }
}
