package dao;

import com.gy.dao.IBaseDao;
import com.gy.model.OrderOperatorBean;
import com.gy.utils.DateUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/stand-alone.xml")
public class DaoTestCase {

    @Autowired
    private IBaseDao<OrderOperatorBean> hbaseDaoImpl;

    @Test
    public void testInsertDB(){
        OrderOperatorBean orderOperatorBean = new OrderOperatorBean();
        orderOperatorBean.setOrderState("4");
        orderOperatorBean.setOrderId("3000000");
        orderOperatorBean.setCreateTime(new DateTime().toString(DateUtils.ss));
        orderOperatorBean.setOptContent("this is opt content");
        orderOperatorBean.setOptTime(new DateTime().toString(DateUtils.ss));
        orderOperatorBean.setOptType("3");
        orderOperatorBean.setSellerId("333333");
        orderOperatorBean.setUserId("44444");
        orderOperatorBean.setOrderTableName("tb_order_seller");
        hbaseDaoImpl.add(orderOperatorBean);
    }


    @Test
    public void testUpdateDB(){
        OrderOperatorBean orderOperatorBean = new OrderOperatorBean();
        orderOperatorBean.setOrderState("5");
        orderOperatorBean.setOrderId("3000000");
        orderOperatorBean.setCreateTime(new DateTime().toString(DateUtils.ss));
        orderOperatorBean.setOptContent("this is opt content is new");
        orderOperatorBean.setOptTime(new DateTime().toString(DateUtils.ss));
        orderOperatorBean.setOptType("3");
        orderOperatorBean.setSellerId("333333");
        orderOperatorBean.setUserId("44444");
        orderOperatorBean.setOrderTableName("tb_order_seller");
        orderOperatorBean.setRowKey("3000000:1481696636118");
        hbaseDaoImpl.update(orderOperatorBean);
    }


    @Test
    public void testQueryDB(){
        OrderOperatorBean orderOperatorBean = hbaseDaoImpl.queryById("3000000:1481696636118");
        System.out.println(orderOperatorBean);
    }

}
