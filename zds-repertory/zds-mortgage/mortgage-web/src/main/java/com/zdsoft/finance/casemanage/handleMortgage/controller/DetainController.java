package com.zdsoft.finance.casemanage.handleMortgage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterNameEnum;
import com.zdsoft.finance.casemanage.datasurvey.service.MatterModuleValidateService;
import com.zdsoft.finance.casemanage.handleMortgage.entity.Detain;
import com.zdsoft.finance.casemanage.handleMortgage.service.DetainService;
import com.zdsoft.finance.casemanage.handleMortgage.vo.DetainVo;
import com.zdsoft.finance.casemanage.handleMortgage.vo.HandleMortgageVo;
import com.zdsoft.finance.casemanage.vo.MatterModuleValidateVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforeAddressService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforeAddressVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.finance.marketing.service.SearchService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.finance.marketing.vo.PropertyOwnerVo;
import com.zdsoft.finance.marketing.vo.SearchVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DetainController.java 
 * @ClassName: DetainController 
 * @Description: 查册入押Controller
 * @author zhoushichao 
 * @date 2017年2月16日 下午6:09:12 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/casemanage/handleMortgage")
public class DetainController extends BaseController{
    
	 @Autowired
	 private CaseApplyService caseApplyService;
	 @Autowired
	 private CapitalistService capitalistService;
	 @Autowired
	 private CooperatorTerminalService cooperatorTerminalService;
	 @Autowired
	 private HousePropertyService housePropertyService;
	 @Autowired
	 private DetainService detainService;
	 @Autowired
	 private BeforePersonalCustomerService beforePersonalCustomerService;
	 @Autowired
	 private BeforeAddressService beforeAddressService;
	 @Autowired
	 private PropertyOwnerService propertyOwnerService;	
	 @Autowired
	 private PledgeInfoService pledgeInfoService;
	 @Autowired
	 private SearchService searchService;
	 @Autowired
	 private MatterModuleValidateService matterModuleValidateService;
	 
	/**
	 * 
	 * @Title: initDetain 
	 * @Description: 初始化查册入押页面
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @return
	 */
    @RequestMapping("/initDetain")
    @UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.initDetain")
    public ModelAndView initDetain(String caseApplyId){
        
        ModelMap model = new ModelMap();
        
        //案件基本信息
    	CaseApply caseApply;
		try {
			caseApply = caseApplyService.findOne(caseApplyId);
	    	CaseApplyVo caseApplyVo = new  CaseApplyVo(caseApply);
	    	//查询资金来源
	    	if (ObjectHelper.isNotEmpty(caseApply.getCapitalSource())) {
	    		Capitalist capitalist = capitalistService.findOne(caseApply.getCapitalSource());
	    		String cooperatorName = capitalist.getCapitalName();
	    		caseApplyVo.setCapitalSourceName(cooperatorName);
	    	}
	    	//查询终端
        	CooperatorTerminal cooperatorTerminal = cooperatorTerminalService.findOne(caseApply.getTerminalId());
        	caseApplyVo.setTerminalIdName(cooperatorTerminal.getTerminalFullName());
        	//资金
        	model.put("caseApplyVo", caseApplyVo);
	        
	        List<String> housePropertyIdList = new ArrayList<>();
	        
	        //查询房产Id
	        List<HouseProperty> houseProperties = housePropertyService.findByCaseApplyId(caseApplyId);
	        for (HouseProperty houseProperty : houseProperties) {
	            housePropertyIdList.add(houseProperty.getId());
	        }
	        model.put("housePropertyIdList",housePropertyIdList);
	        
	        //设置事项名称
			String matterName = MatterNameEnum.BOOK_ENTRY.value;
			List<MatterModuleValidate> validateList = matterModuleValidateService.findByBusinessKeyAndMatterName(caseApplyId, matterName);
			List<MatterModuleValidateVo> validateVoList = new ArrayList<MatterModuleValidateVo>();
			for (MatterModuleValidate matterModuleValidate : validateList) {
				MatterModuleValidateVo validateVo = new MatterModuleValidateVo();
				validateVo.setTabName(matterModuleValidate.getTabName());
				validateVo.setExecuteTag(matterModuleValidate.getExecuteTag());
				validateVoList.add(validateVo);
			}
			String validateJson = ObjectHelper.objectToJson(validateVoList);
			model.put("validateJson", validateJson);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("初始化查册入押页面失败:", e);
		}
        return new ModelAndView("/casemanage/handleMortgage/detain_entry_edit", model);
    }
    
    /**
     * 
     * @Title: editDetain 
     * @Description: 押品信息tab切换
     * @author zhoushichao 
     * @param housePropertyId 房产Id
     * @return
     */
    @RequestMapping("/editDetain")
    @UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.editDetain")
    public ModelAndView editDetain(String housePropertyId,String caseApplyId){
    	ModelMap map = new ModelMap();
        try {
        	//Modify by xj
        	//表示需要调用后台复制名称
        	map.put("isUrlName", true);
        	//页面复制地址
        	this.queryAddress(map,caseApplyId);
        	//Modify by xj end
        	HouseProperty houseProperty = housePropertyService.findOne(housePropertyId);
            
            HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
            map.put("housePropertyVo", housePropertyVo);
            //若有产权信息则回显数据
            Search search = houseProperty.getSearch();
            if(ObjectHelper.isNotEmpty(search)){
                SearchVo searchVo = new SearchVo(search);
                map.put("searchVo", searchVo);
            }
            //若有数据则回显查册入押信息
            Detain detain = detainService.findByHousePropertyId(housePropertyId);
            if(ObjectHelper.isNotEmpty(detain)){
            	DetainVo detainVo = new DetainVo(detain);
            	map.put("detainVo", detainVo);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error("押品信息tab切换失败:", e);
        }
        return new ModelAndView("/casemanage/handleMortgage/detain_mortgage_edit", map);
    }

    /**
     * 
     * @Title: queryAddress 
     * @Description: 查询主借人的地址 用于页面地址复制
     * @author xj 
     * @param map 传入页面map
     * @param caseApplyId 案件id
     */
	private void queryAddress(ModelMap map, String caseApplyId) {
		List<BeforePersonalCustomer> mainCustomer = beforePersonalCustomerService.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
        if(ObjectHelper.isNotEmpty(mainCustomer) && mainCustomer.size()>0){
        	//家庭地址
			BeforeAddress beforeAddress = beforeAddressService.loadAddresss(mainCustomer.get(0).getId(), BeforeAddress.HOME_ADDRESS);
			if(ObjectHelper.isNotEmpty(beforeAddress)){
				BeforeAddressVo homeAddressVo = new BeforeAddressVo(beforeAddress);
				map.put("homeAddressVo", homeAddressVo);
			}
			//户籍地址
			BeforeAddress registrationSddress = beforeAddressService.loadAddresss(mainCustomer.get(0).getId(), BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
			if(ObjectHelper.isNotEmpty(registrationSddress)){
				BeforeAddressVo beforeAddressVo = new BeforeAddressVo(registrationSddress);
				map.put("registrationAddressVo", beforeAddressVo);
			}
        }
	}
	
	/**
	 * 
	 * @Title: saveDetain 
	 * @Description: 保存查册入押信息
	 * @author zhoushichao 
	 * @param handleMortgageVo 办理抵押基本信息
	 * @return
	 */
	@RequestMapping("/saveDetain")
    @UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.saveDetain")
    @ResponseBody
    public ResponseMsg saveDetain(HandleMortgageVo handleMortgageVo){
        ResponseMsg msg = new ResponseMsg();
        // 将Vo转成Po
     	Map<String, Object> voTurnPoMap = this.voTurnPo(handleMortgageVo);
     	//获取数据
     	HouseProperty houseProperty = (HouseProperty) voTurnPoMap.get("houseProperty");
        
     	//查册入押信息vo转换po
     	Detain detain = handleMortgageVo.getDetainVo().toPo();
     	
        try {
        	//保存查册入押
        	Map<String, Object> map = detainService.saveDetainAllInfo(detain,houseProperty);
        	
            msg.setOptional(map);
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
     * @Title: voTurnPo 
     * @Description: 办理抵押vo转Po
     * @author zhoushichao 
     * @param handleMortgageVo 办理抵押信息
     * @return
     */
	public Map<String, Object> voTurnPo(HandleMortgageVo handleMortgageVo) {
		Map<String, Object> voTurnPoMap =new HashMap<String, Object>();
		HouseProperty houseProperty =null;
		try {
			//获取房产信息
			houseProperty = housePropertyService.findOne(handleMortgageVo.getDetainVo().getHousePropertyId());
			
			//抵押情况
			List<PledgeInfo> pledgeInfoList = new ArrayList<PledgeInfo>();
			List<PledgeInfoVo> pledgeInfoVoList = handleMortgageVo.getPledgeInfoVoList();
			if(ObjectHelper.isNotEmpty(pledgeInfoVoList)){
				for (PledgeInfoVo pledgeInfoVo : pledgeInfoVoList) {
					PledgeInfo pledgeInfo = pledgeInfoVo.toPo();
					PledgeInfo pledge = new PledgeInfo();
					if(ObjectHelper.isNotEmpty(pledgeInfoVo.getId())){
						try {
							pledge = pledgeInfoService.findOne(pledgeInfoVo.getId());
							pledge.setUpdateTime(new Date());
						} catch (BusinessException e) {
							e.printStackTrace();
						}
					}else{
						pledge.setCreateTime(new Date());
					}
					BeanUtils.copyProperties(pledgeInfo, pledge , new String[]{"id","isDeleted","createTime","updateTime"});
					pledgeInfoList.add(pledge);
				}
			}
			houseProperty.setPledgeInfoList(pledgeInfoList);
			
			//产权人信息
			List<PropertyOwner> propertyOwnerList = new ArrayList<PropertyOwner>();
			List<PropertyOwnerVo> propertyOwnerVoList = handleMortgageVo.getPropertyOwnerVoList();
			//产权人信息
			if(ObjectHelper.isNotEmpty(propertyOwnerVoList)){
				for (PropertyOwnerVo propertyOwnerVo : propertyOwnerVoList) {
					PropertyOwner propertyOwner = propertyOwnerVo.toPo();
					PropertyOwner property = new PropertyOwner();
					if(ObjectHelper.isNotEmpty(propertyOwnerVo.getId())){
						try {
							property = propertyOwnerService.findOne(propertyOwnerVo.getId());
							property.setUpdateTime(new Date());
						} catch (BusinessException e) {
							e.printStackTrace();
						}
					}else{
						property.setCreateTime(new Date());
					}
					BeanUtils.copyProperties(propertyOwner, property , new String[]{"id","isDeleted","createTime","updateTime"});
					propertyOwnerList.add(property);
				}
			}
			//房产设置产权人信息
			houseProperty.setPropertyOwnerList(propertyOwnerList);
			
			 // 产权状态信息vo转换po
	        Search search = handleMortgageVo.getSearchVo().toPo();
	        //设置产权状态
			if (ObjectHelper.isNotEmpty(search)) {
				Search searchPo = new Search();
				if (ObjectHelper.isNotEmpty(search.getId())) {
					try {
						searchPo = searchService.findOne(search.getId());
						searchPo.setUpdateTime(new Date());
					} catch (BusinessException e) {
						e.printStackTrace();
					}
				}else{
					searchPo.setCreateTime(new Date());
				}
				BeanUtils.copyProperties(search, searchPo , new String[]{"isDeleted","createTime","updateTime"});
				houseProperty.setSearch(searchPo);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("办理抵押vo转Po失败：", e);
		}
		voTurnPoMap.put("houseProperty", houseProperty);
		return voTurnPoMap;
	}
}
