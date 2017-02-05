//package com.zdsoft.finance.demo.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
///**
// * Created by Administrator on 2016/9/19.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath*:spring-mvc.xml"})
//public class DClientControllerTest {
//    private static Logger logger= LoggerFactory.getLogger(DClientControllerTest.class);
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        this.mockMvc = webAppContextSetup(this.wac).build();
//    }
//
//    @Test
//    public void testFindAll() throws Exception {
//        ResultActions r=mockMvc.perform((get("/demo/findAll").param("page", "0").param("size", "1").param("name", "j")));
//        logger.info("========================<<<test findAll");
//       // r.andExpect(content().json("[{\"address\":\"中国重庆江北2\",\"age\":11,\"id\":\"4028a147573c30c001573c30c6620000\",\"idCard\":\"50502022220202\",\"isDeleted\":false,\"name\":\"jack\"}]"));
//        r.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
//        r.andExpect(status().isOk())
//                .andDo(print());
//        r.andExpect(jsonPath("$.[0].name",is("jack")));
//
//    }
//
//
//}
