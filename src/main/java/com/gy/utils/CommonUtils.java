package com.gy.utils;

import com.gy.model.OrderOperatorBean;
import com.gy.model.pojo.TbOrderOperator;

import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by gongyan on 16/12/14.
 */
public class CommonUtils {
    /**
     * 将Hbase的结果集转换为订单操作记录的对象
     *
     * 参数
     *     Hbase的操作结果集
     *
     * @return 订单操作对象
     */
    public static OrderOperatorBean parseKV2OrderOperatorBean(NavigableMap<byte[], byte[]> navigableMap) {
        OrderOperatorBean orderOperatorBean = new OrderOperatorBean();
        for(Map.Entry<byte[], byte[]> entry : navigableMap.entrySet()){
            // 列族中的key值
            String key = new String(entry.getKey());
            // 列族中的value值
            String value = new String(entry.getValue());
            if ("orderId".equals(key)) {
                orderOperatorBean.setOrderId(value);
            }
            if ("orderState".equals(key)) {
                orderOperatorBean.setOrderState(value);
            }
            if ("userId".equals(key)) {
                orderOperatorBean.setUserId(value);
            }
            if ("sellerId".equals(key)) {
                orderOperatorBean.setSellerId(value);
            }
            if ("orderTableName".equals(key)) {
                orderOperatorBean.setOrderTableName(value);
            }
            if ("optType".equals(key)) {
                orderOperatorBean.setOptType(value);
            }
            if ("optContent".equals(key)) {
                orderOperatorBean.setOptContent(value);
            }
            if ("optTime".equals(key)) {
                orderOperatorBean.setOptTime(value);
            }
            if ("createTime".equals(key)) {
                orderOperatorBean.setCreateTime(value);
            }
        }
        return orderOperatorBean;
    }

    /**
     * 数据库的订单操作对象记录转换为Hbase的操作记录
     *
     * 参数
     *    数据库中订单操作记录
     *
     * @return 转换完成的Hbase订单操作记录
     */
    public static OrderOperatorBean parseDB2Hbase(TbOrderOperator tbOrderOperator) {

        OrderOperatorBean orderOperatorBean = new OrderOperatorBean();
        orderOperatorBean.setOrderTableName(tbOrderOperator.getOrderTableName());
        orderOperatorBean.setCreateTime(DateUtils.dateTime2StringWithss(tbOrderOperator.getCreateTime()));
        orderOperatorBean.setSellerId(tbOrderOperator.getSellerId() + "");
        orderOperatorBean.setUserId(tbOrderOperator.getUserId() + "");
        orderOperatorBean.setOptType(tbOrderOperator.getOptType());
        orderOperatorBean.setOptContent(tbOrderOperator.getOptContent());
        orderOperatorBean.setOptTime(DateUtils.dateTime2StringWithss(tbOrderOperator.getOptTime()));
        orderOperatorBean.setOrderId(tbOrderOperator.getOrderId());
        orderOperatorBean.setOrderState(tbOrderOperator.getOrderState() + "");

        return orderOperatorBean;
    }
}
