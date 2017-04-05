package com.zdsoft.finance.exportexcel.controller;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ExportExcelUtil;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出excel测试
 *
 * @author LiaoGuoWei
 * @create 2016-12-22 11:26
 **/
@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    @RequestMapping("/exportTestPage")
    @UriKey(key = "com.zdsoft.finance.export.exportTestPage")
    public ModelAndView exportTestPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        modelAndView.setViewName("exportExcel/export_excel");


        return modelAndView;
    }


    @RequestMapping("/exportTestData")
    @UriKey(key = "com.zdsoft.finance.export.exportTestData")
    @ResponseBody
    public String exportTestData(String jsoncallback) {
        Map<String, Object> returnData = new HashMap<String, Object>();
        List<Map<String, Object>> sourceData = new ArrayList<Map<String, Object>>();
        Map<String, Object> data1 = new HashMap<String, Object>();
        data1.put("name", "karl");
        data1.put("age", "25");
        data1.put("address", "cq");
        data1.put("phone", "18623272525");
        data1.put("country", "china");
        data1.put("tall", "175");
        data1.put("weight", "60kg");
        data1.put("profession", "engineer");
        data1.put("company", "zds");
        sourceData.add(data1);

        Map<String, Object> data2 = new HashMap<String, Object>();
        data2.put("name", "karl");
        data2.put("age", "25");
        data2.put("address", "cq");
        data2.put("phone", "18623272525");
        data2.put("country", "china");
        data2.put("tall", "175");
        data2.put("weight", "60kg");
        data2.put("profession", "engineer");
        data2.put("company", "zds");
        sourceData.add(data2);
        Map<String, Object> data3 = new HashMap<String, Object>();
        data3.put("name", "karl");
        data3.put("age", "25");
        data3.put("address", "cq");
        data3.put("phone", "18623272525");
        data3.put("country", "china");
        data3.put("tall", "175");
        data3.put("weight", "60kg");
        data3.put("profession", "engineer");
        data3.put("company", "zds");
        sourceData.add(data3);
        Map<String, Object> data4 = new HashMap<String, Object>();
        data4.put("name", "karl");
        data4.put("age", "25");
        data4.put("address", "cq");
        data4.put("phone", "18623272525");
        data4.put("country", "china");
        data4.put("tall", "175");
        data4.put("weight", "60kg");
        data4.put("profession", "engineer");
        data4.put("company", "zds");
        sourceData.add(data4);
        Map<String, Object> data5 = new HashMap<String, Object>();
        data5.put("name", "karl");
        data5.put("age", "25");
        data5.put("address", "cq");
        data5.put("phone", "18623272525");
        data5.put("country", "china");
        data5.put("tall", "175");
        data5.put("weight", "60kg");
        data5.put("profession", "engineer");
        data5.put("company", "zds");
        sourceData.add(data5);
        Map<String, Object> data6 = new HashMap<String, Object>();
        data6.put("name", "karl");
        data6.put("age", "25");
        data6.put("address", "cq");
        data6.put("phone", "18623272525");
        data6.put("country", "china");
        data6.put("tall", "175");
        data6.put("weight", "60kg");
        data6.put("profession", "engineer");
        data6.put("company", "zds");
        sourceData.add(data6);
        Map<String, Object> data7 = new HashMap<String, Object>();
        data7.put("name", "karl");
        data7.put("age", "25");
        data7.put("address", "cq");
        data7.put("phone", "18623272525");
        data7.put("country", "china");
        data7.put("tall", "175");
        data7.put("weight", "60kg");
        data7.put("profession", "engineer");
        data7.put("company", "zds");
        sourceData.add(data7);
        Map<String, Object> data8 = new HashMap<String, Object>();
        data8.put("name", "karl");
        data8.put("age", "25");
        data8.put("address", "cq");
        data8.put("phone", "18623272525");
        data8.put("country", "china");
        data8.put("tall", "175");
        data8.put("weight", "60kg");
        data8.put("profession", "engineer");
        data8.put("company", "zds");
        sourceData.add(data8);
        Map<String, Object> data9 = new HashMap<String, Object>();
        data9.put("name", "karl");
        data9.put("age", "25");
        data9.put("address", "cq");
        data9.put("phone", "18623272525");
        data9.put("country", "china");
        data9.put("tall", "175");
        data9.put("weight", "60kg");
        data9.put("profession", "engineer");
        data9.put("company", "zds");
        sourceData.add(data9);
        Map<String, Object> data10 = new HashMap<String, Object>();
        data10.put("name", "karl");
        data10.put("age", "25");
        data10.put("address", "cq");
        data10.put("phone", "18623272525");
        data10.put("country", "china");
        data10.put("tall", "175");
        data10.put("weight", "60kg");
        data10.put("profession", "engineer");
        data10.put("company", "zds");
        sourceData.add(data10);
        Map<String, Object> data11 = new HashMap<String, Object>();
        data11.put("name", "karl");
        data11.put("age", "25");
        data11.put("address", "cq");
        data11.put("phone", "18623272525");
        data11.put("country", "china");
        data11.put("tall", "175");
        data11.put("weight", "60kg");
        data11.put("profession", "engineer");
        data11.put("company", "zds");
        sourceData.add(data11);


        returnData.put("rows", sourceData);
        returnData.put("total", sourceData.size());


        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }

    @RequestMapping(value = "/toExcel", produces = "text/html;charset=UTF-8")
    @UriKey(key = "com.zdsoft.finance.toExcel")
    public void toExcel(String fileName,@RequestParam(name = "htmlContent")String htmlContent, String jsoncallback, HttpServletRequest request, HttpServletResponse response) {
        ResponseMsg msg = new ResponseMsg();
        ServletOutputStream out=null;
        try {
            String path = request.getSession().getServletContext().getRealPath("/");
            logger.info("获得的服务器根路径为：》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》" + path);
            //导出Excel主方法
            File file = ExportExcelUtil.toExcel(fileName, htmlContent, path);
            //得到文件名
            fileName=file.getName();
            if (file == null || !file.exists()) {
                throw new BusinessException("10010002", "根据参数未找到相应数据");
            }
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            String userAgent = request.getHeader("User-Agent");
            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
            //非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
            }
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getOutputStream();

            FileInputStream inputStream = new FileInputStream(file);


            int b = 0;
            byte[] buffer = new byte[512];
            while ((b = inputStream.read(buffer)) != -1) {
                //4.写到输出流(out)中
                out.write(buffer, 0, b);
            }
            inputStream.close();
            out.close();
            out.flush();

        } catch (Exception e) {
            logger.error("导出Excel出错！", e);
            e.printStackTrace();
        }
    }
}
