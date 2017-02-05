//package com.zdsoft.finance.demo.service;
//
//import com.zdsoft.finance.demo.entity.DClient;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * demo客户业务单元测试
// * Created on 2016-09-18
// * @author Maple
// * @version 1.0
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath*:applicationContext.xml")
//public class DClientSeviceTest {
//    public static  Logger logger= LoggerFactory.getLogger(DClientSeviceTest.class);
//    @Autowired
//    private DClientSevice dClientSevice;
//
//    @Test
//    public void testQuery(){
//        PageRequest pageRequest=new PageRequest(0,10);
//        Page<DClient> pages=dClientSevice.queryAllByName("j",pageRequest);
//
//        for (DClient dClient:pages.getContent()) {
//            logger.info("==========="+dClient.toString());
//        }
//        Assert.assertTrue(pages.getTotalElements()>0);
//    }
//
//    @Test
//    public void testSave(){
//        DClient client=new DClient();
//        client.setAddress("中国重庆江北33333");
//        client.setName("maple");
//        client.setAge(11);
//        client.setIdCard("4581220202");
//        dClientSevice.save(client);
//        Assert.assertNotNull(client.getId());
//    }
//}
