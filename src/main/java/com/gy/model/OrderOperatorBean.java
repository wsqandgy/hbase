package com.gy.model;

/**
 * Created by gongyan on 16/12/14.
 */
public class OrderOperatorBean {

    private String orderId;
    private String orderState;
    private String userId;
    private String sellerId;
    private String orderTableName;
    private String optType;
    private String optContent;
    private String optTime;
    private String createTime;
    private String rowKey;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getOrderTableName() {
        return orderTableName;
    }

    public void setOrderTableName(String orderTableName) {
        this.orderTableName = orderTableName;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOptContent() {
        return optContent;
    }

    public void setOptContent(String optContent) {
        this.optContent = optContent;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderOperatorBean{" +
                "orderId='" + orderId + '\'' +
                ", orderState='" + orderState + '\'' +
                ", userId='" + userId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", orderTableName='" + orderTableName + '\'' +
                ", optType='" + optType + '\'' +
                ", optContent='" + optContent + '\'' +
                ", optTime='" + optTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
