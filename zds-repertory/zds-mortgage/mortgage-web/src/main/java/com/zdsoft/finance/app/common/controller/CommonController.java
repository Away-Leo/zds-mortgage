package com.zdsoft.finance.app.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.CodeDto;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * CommonController
 * @Package:com.zdsoft.finance.app.common
 * @Description:app 公共信息获取  地址数据  下拉数据
 * @author: dengyy
 * @date:2017年1月13日 下午5:24:56
 * @version:v1.0
 */
@RequestMapping("/server")
@Controller
public class CommonController extends BaseController {
    
    @Autowired
    private CED CED ;
    
    /**
     * 
     * @Title: region 
     * @Description: 获取所有的地址信息 包含信息 省市区
     * @author dengyy 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/region",produces = "text/plain;charset=UTF-8")
    @UriKey(key="com.zdsoft.finance.app.common.region")
    @ResponseBody
    public String region(HttpServletRequest request,HttpServletResponse response){
        try {
            //获取省市区数据   根据categoryId：YWDM0016 查询 simplecode数据  
            List<CodeDto> list = CED.queryProvince();
            List<Map<String, Object>> rtList = new ArrayList<>();
            for (CodeDto simpleCodeDto : list) {
                //省
                Map<String, Object> map = new HashMap<String, Object>() ;
                map.put("regionCode", simpleCodeDto.getFullCode());
                map.put("regionName", simpleCodeDto.getName());
                List<Map<String, Object>> list1= new ArrayList<>() ;
                map.put("cityList", list1);
                //市
                List<CodeDto> condition = CED.queryCodeByParentId(simpleCodeDto.getId());
                if(ObjectHelper.isNotEmpty(condition)){
                    for (CodeDto simpleCodeDto2 : condition) {
                        Map<String, Object> map1 = new HashMap<String, Object>() ;
                        map1.put("regionCode", simpleCodeDto2.getFullCode());
                        map1.put("regionName", simpleCodeDto2.getName());
                        List<Map<String, Object>> list2= new ArrayList<>() ;
                        map1.put("districtList", list2);
                        //县
                        List<CodeDto> condition2 = CED.queryCodeByParentId(simpleCodeDto2.getId());
                        if(ObjectHelper.isNotEmpty(condition2)){
                            for (CodeDto simpleCodeDto3 : condition2) {
                                Map<String, Object> map2 = new HashMap<String, Object>() ;
                                map2.put("regionCode", simpleCodeDto3.getFullCode());
                                map2.put("regionName", simpleCodeDto3.getName());
                                list2.add(map2);
                            }
                        }
                        list1.add(map1);
                    }
                }
                rtList.add(map);
            }
            Map<String, Object> rtMap = new HashMap<>();
            rtMap.put("list", rtList);
            return AppServerUtil.buildJsonObject(AppStatus.Succeed, rtMap);
        } catch (Exception e) {
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError);
        }
    }
    
    /**
     * 
     * @Title: checkbox 
     * @Description: app获取下拉数据信息
     * @author dengyy 
     * @param request
     * @param response
     * @param checkboxName 下拉框 CategoryId
     * @return
     */
    @RequestMapping(value = "/checkbox",produces = "text/plain;charset=UTF-8")
    @UriKey(key="com.zdsoft.finance.app.common.checkbox")
    @ResponseBody
    public String checkbox(HttpServletRequest request,HttpServletResponse response,String checkboxName){
        try {
            //传入参数  参数不能为空   下拉框的CategoryId
            if(ObjectHelper.isEmpty(checkboxName)){
                return AppServerUtil.buildError(AppStatus.ArgsError);
            }
            String[] split = checkboxName.split(",");
            List<Map<String, Object>> listRt = new ArrayList<Map<String, Object>>();
            for (String  string: split) {
                List<SimpleCodeDto> codeByCategoryId = CED.querySimpleCodeByCategoryId(string);
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                //数据转换
                for (SimpleCodeDto simpleCodeDto : codeByCategoryId) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("optionCode", simpleCodeDto.getFullCode());
                    map.put("optionVal", simpleCodeDto.getName());
                    list.add(map);
                }
                Map<String,Object> hashMap = new HashMap<>();
                hashMap.put("code", list);
                listRt.add(hashMap);
            }
            Map<String, Object> mapRt = new HashMap<String, Object>();
            //数据封装
            mapRt.put("checkbox", listRt);
            //返回数据
            return  AppServerUtil.buildJsonObject(AppStatus.Succeed, mapRt);
        } catch (Exception e) {
            e.printStackTrace();
            return  AppServerUtil.buildError(AppStatus.LogicError);
        }
    }
    
    /**
     * 
     * @Title: comboTree 
     * @Description:  app获取下拉tree数据信息
     * @author dengyy 
     * @param request
     * @param response
     * @param comboTreeName 下拉框 CategoryId
     * @return
     */
    @RequestMapping(value = "/comboTree",produces = "text/plain;charset=UTF-8")
    @UriKey(key="com.zdsoft.finance.app.common.comboTree")
    @ResponseBody
    public String comboTree(HttpServletRequest request,HttpServletResponse response,String comboTreeName){
        try {
            //传入参数  参数不能为空   下拉框的CategoryId
            if(ObjectHelper.isEmpty(comboTreeName)){
                return AppServerUtil.buildError(AppStatus.ArgsError);
            }
            String[] split = comboTreeName.split(",");
            List<Map<String, Object>> listRt = new ArrayList<Map<String, Object>>();
            for (String  string: split) {
                List<SimpleCodeDto> codeByCategoryId = CED.querySimpleCodeByCategoryId(string);
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                if(ObjectHelper.isNotEmpty(codeByCategoryId)){
                	for (SimpleCodeDto simpleCodeDto : codeByCategoryId) {
						 if(ObjectHelper.isEmpty(simpleCodeDto.getPid())){
							 Map<String, String> map = new HashMap<>();
							 map.put("optionCode", simpleCodeDto.getFullCode());
				             map.put("optionVal", simpleCodeDto.getName());
				             list.add(map);
						 }
					}
                	Map<String,Object> hashMap = new HashMap<>();
                    hashMap.put("code", list);
                    listRt.add(hashMap);
                }
            }
            Map<String, Object> mapRt = new HashMap<String, Object>();
            //数据封装
            mapRt.put("checkbox", listRt);
            //返回数据
            return  AppServerUtil.buildJsonObject(AppStatus.Succeed, mapRt);
        } catch (Exception e) {
            e.printStackTrace();
            return  AppServerUtil.buildError(AppStatus.LogicError);
        }
    }
    
    /**
     * 
     * @Title: comboTreeChildren 
     * @Description: app获取下拉ztree 子节点信息
     * @author dengyy 
     * @param request
     * @param response
     * @param fullCode 下拉框 值
     * @return
     */
    @RequestMapping(value = "/comboTreeChildren",produces = "text/plain;charset=UTF-8")
    @UriKey(key="com.zdsoft.finance.app.common.comboTcomboTreeChildrenree")
    @ResponseBody
    public String comboTreeChildren(HttpServletRequest request,HttpServletResponse response,String fullCode){
        try {
            //传入参数  参数不能为空   下拉框的CategoryId
            if(ObjectHelper.isEmpty(fullCode)){
                return AppServerUtil.buildError(AppStatus.ArgsError);
            }
            String[] split = fullCode.split(",");
            List<Map<String, Object>> listRt = new ArrayList<Map<String, Object>>();
            for (String  string: split) {
                List<CodeDto> list = CED.queryCodeByParentId(string);
                List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
                for (CodeDto codeDto : list) {
                	 Map<String, String> map = new HashMap<>();
					 map.put("optionCode", codeDto.getFullCode());
		             map.put("optionVal", codeDto.getName());
		             list1.add(map);
				}
                Map<String,Object> hashMap = new HashMap<>();
                hashMap.put("code", list1);
                listRt.add(hashMap);
            }
            Map<String, Object> mapRt = new HashMap<String, Object>();
            //数据封装
            mapRt.put("checkbox", listRt);
            //返回数据
            return  AppServerUtil.buildJsonObject(AppStatus.Succeed, mapRt);
        } catch (Exception e) {
            e.printStackTrace();
            return  AppServerUtil.buildError(AppStatus.LogicError);
        }
    }
    
}