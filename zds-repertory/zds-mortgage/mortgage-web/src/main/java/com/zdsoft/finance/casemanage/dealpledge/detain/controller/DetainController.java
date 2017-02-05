package com.zdsoft.finance.casemanage.dealpledge.detain.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.dealpledge.detain.entity.Detain;
import com.zdsoft.finance.casemanage.dealpledge.detain.service.DetainService;
import com.zdsoft.finance.casemanage.dealpledge.detain.vo.DetainVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.SearchService;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.finance.marketing.vo.PropertyOwnerVo;
import com.zdsoft.finance.marketing.vo.SearchVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:DetainController.java
 * @Package:com.zdsoft.finance.casemanage.dealpledge.detain.controller
 * @Description:查册入押表Controller
 * @author: caixiekang
 * @date:2017年1月15日 下午2:28:01
 * @version:v1.0
 */

@Controller
@RequestMapping("/casemanage/dealpledge/detain")
public class DetainController extends BaseController{
    
    @Autowired
    private DetainService detainService;
   
    @Autowired
    private HousePropertyService housePropertyService;
    
    @Autowired
    private SearchService searchService;
    
    /**
     * 
     * 初始化查册入押页面
     *
     * @author caixiekang
     * @return
     */
    @RequestMapping("/initDetain")
    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.detain.initDetain")
    @Menu(resource = "com.zdsoft.finance.casemanage.dealpledge.detain.initDetain", label = "查册入押", group = "project", order = 10)
    public ModelAndView initDetain(String caseApplyId){
        
        caseApplyId = "402892c459b170f00159b1ad651e0022";
        ModelMap map = new ModelMap();
        List<String> housePropertyIdList = new ArrayList<>();
        
        List<HouseProperty> houseProperties = housePropertyService.findByCaseApplyId(caseApplyId);
        if(ObjectHelper.isEmpty(houseProperties)){
           // throw new BusinessException();
        }
        
        for (HouseProperty houseProperty : houseProperties) {
            housePropertyIdList.add(houseProperty.getId());
        }
        map.put("housePropertyIdList",housePropertyIdList);
        return new ModelAndView("/casemanage/dealpledge/detain/detain_view", map);
    }
    
    /**
     * 
     * 押品信息tab切换
     *
     * @author caixiekang
     * @return
     */
    @RequestMapping("/showDetainAllInfo")
    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.detain.showDetainAllInfo")
    public ModelAndView showDetainAllInfo(String housePropertyId, ModelMap map){
        try {
            HouseProperty houseProperty = housePropertyService.findOne(housePropertyId);
            
            HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
            map.put("housePropertyVo", housePropertyVo);
          //若有数据则回显查册入押信息
            Detain detain = detainService.findByHousePropertyId(housePropertyId);
            if(ObjectHelper.isNotEmpty(detain)){
                DetainVo detainVo = new DetainVo(detain);
                map.put("detainVo", detainVo);
            }
            //若有产权信息则回显数据
            Search search = searchService.findByHouseProperyId(housePropertyId);
            if(ObjectHelper.isNotEmpty(search)){
                SearchVo searchVo = new SearchVo(search);
                map.put("searchVo", searchVo);
            }
            //把housePropertyId传回页面
            map.put("housePropertyId", housePropertyId);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return new ModelAndView("/casemanage/dealpledge/detain/detain_detail_load", map);
    }
    /**
     * 
     * 保存入押信息
     *
     * @author caixiekang
     * @param detainVo
     * @param status
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/saveDetain")
    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.detain.saveDetain")
    @ResponseBody
    public ResponseMsg saveDetain(String pledgeList, String propertyList,DetainVo detainVo, SearchVo searchVo){
        ResponseMsg msg = new ResponseMsg();
        
        try {
            //抵押情况,转换抵押pledgeList为pledgeListVos对象,转换po
            JSONArray jsonPledgeList = JSONArray.fromObject(pledgeList);
            List<PledgeInfoVo> pledgeInfoVos = JSONArray.toList(jsonPledgeList, new PledgeInfoVo(), new JsonConfig());
            List<PledgeInfo> pledgeInfos = new ArrayList<PledgeInfo>();
            for (PledgeInfoVo pledgeInfoVo : pledgeInfoVos) {
                PledgeInfo pledgeInfo = pledgeInfoVo.toPO();
                pledgeInfos.add(pledgeInfo);
            }
            //产权人信息,转换propertyList为propertyList对象,转换po
            JSONArray jsonPropertyList = JSONArray.fromObject(propertyList);
            List<PropertyOwnerVo> propertyListVos = JSONArray.toList(jsonPropertyList, new PropertyOwnerVo(), new JsonConfig());
            List<PropertyOwner> propertyOwners = new ArrayList<PropertyOwner>();
            for (PropertyOwnerVo propertyOwnerVo : propertyListVos) {
                PropertyOwner propertyOwner = propertyOwnerVo.toPO();
                propertyOwners.add(propertyOwner);
            }
            
            // 产权状态信息
            Search search = searchVo.toPO();
            //获取查册入押信息VO
            Detain detain = detainVo.toPo();
            
            //调用service层保存
            detainService.saveDetainAllInfo(pledgeInfos,propertyOwners,search,detain);
            
            
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("查册入押所有信息保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查册入押所有信息保存失败", e);
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("系统内部错误，请查看日志");
        }
        return msg;
        
    }
    
    /**
     * 
     * 显示押品信息(单个)
     *
     * @author caixiekang
     * @param housePropertyId
     * @return
     */
    @RequestMapping("/listSingleHouse")
    @UriKey(key = "com.zdsoft.finance.casemanage.dealpledge.detain.listSingleHouse")
    @ResponseBody
    public ResponseMsg listSingleHouse(String housePropertyId){
        ResponseMsg msg = new ResponseMsg();
        try {
            HouseProperty houseProperty = housePropertyService.findOne(housePropertyId);
            HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
            List<HousePropertyVo> list = new ArrayList<HousePropertyVo>();
            list.add(housePropertyVo);
            msg.setRows(list);
            msg.setMsg("查找单个押品信息成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            msg.setMsg("查找单个押品信息失败");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        
        
        return msg;
    }
}
