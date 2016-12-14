package com.gy.dao.impl;

import com.gy.dao.IBaseDao;
import com.gy.model.OrderOperatorBean;
import com.gy.utils.CommonUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FuzzyRowFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Order;
import org.apache.hadoop.hbase.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * 根据操作对象的Rowkey更新对应Rowkey的操作对象的内容
     *
     * 参数:
     *    订单操作对象
     *
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

    /**
     * 删除指定RowKey的订单操作记录
     *
     * 参数
     *    rowkey
     *
     */
    @Override
    public void delete(OrderOperatorBean orderOperatorBean) {

    }

    /**
     * 查询指定RowKey的订单操作记录
     *
     * 参数
     *    rowkey
     *
     * @return 订单操作记录实体
     */
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

    /**
     * 批量添加给定的订单操作记录
     *
     * 参数
     *    订单操作记录的集合
     *
     */
    @Override
    public void batchAdd(List<OrderOperatorBean> lists) {
        List<Put> puts = new ArrayList<Put>();
        for(OrderOperatorBean orderOperatorBean : lists){
            // 生成主键名称
            byte[] keyBytes = (orderOperatorBean.getOrderId() + ":" + orderOperatorBean.getCreateTime()).getBytes();
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
            puts.add(put);
        }

        try {
            hTable.put(puts);
        } catch (IOException e) {
            logger.error("批量插入数据异常!{}",e);
        }
    }

    @Override
    public List<OrderOperatorBean> queryByPage(String key , int size) {
        Scan scan = new Scan();
        scan.setMaxVersions(1);
//        scan.setStartRow(key.getBytes());
        Filter filter = new PageFilter(size);
        scan.setFilter(filter);
        List<OrderOperatorBean> orderOperatorBeanList = new ArrayList<>();
        try {
            ResultScanner results = hTable.getScanner(scan);
            for(Result result : results){
                NavigableMap<byte[], byte[]> navigableMap = result.getFamilyMap(Bytes.toBytes("info"));
                OrderOperatorBean orderOperatorBean = CommonUtils.parseKV2OrderOperatorBean(navigableMap);
                orderOperatorBeanList.add(orderOperatorBean);
                String rowNumber = new String(result.getRow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderOperatorBeanList;
    }

    /**
     * 模糊查询rowKey,查询内容
     * @param key
     * @return
     */
    @Override
    public List<OrderOperatorBean> queryByFuzzy(String key) {
        Pair<byte[],byte[]> pair = new Pair<byte[],byte[]>();
        pair.setFirst(key.getBytes());
        pair.setSecond(new byte[]{1,1,1,1,1,1,1,1,1,1,1,1});
        List<Pair<byte[], byte[]>> pairs = new ArrayList<Pair<byte[], byte[]>>();
        pairs.add(pair);
        Filter filter = new FuzzyRowFilter(pairs);
        Scan scan = new Scan();
        scan.setStartRow("1000000016474:2015-01-28 15:52:15".getBytes()); // 设置起始的行键
        scan.setFilter(filter);
        List<OrderOperatorBean> orderOperatorBeanList = new ArrayList<>();
        try {
            ResultScanner results = hTable.getScanner(scan);
            for(Result result : results){
                NavigableMap<byte[], byte[]> navigableMap = result.getFamilyMap(Bytes.toBytes("info"));
                OrderOperatorBean orderOperatorBean = CommonUtils.parseKV2OrderOperatorBean(navigableMap);
                orderOperatorBeanList.add(orderOperatorBean);
                String rowNumber = new String(result.getRow());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orderOperatorBeanList;
    }
}
