package com.zdsoft.finance.app.casemanage.handleMortgage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.app.casemanage.handleMortgage.vo.AppDetainVo;
import com.zdsoft.finance.app.casemanage.handleMortgage.vo.AppHousePropertyVo;
import com.zdsoft.finance.app.casemanage.handleMortgage.vo.AppInChargeVo;
import com.zdsoft.finance.app.casemanage.handleMortgage.vo.AppPledgeInfoVo;
import com.zdsoft.finance.app.casemanage.handleMortgage.vo.AppPropertyOwnerVo;
import com.zdsoft.finance.app.casemanage.handleMortgage.vo.AppSearchVo;
import com.zdsoft.finance.businesssetting.entity.MortOwner;
import com.zdsoft.finance.businesssetting.service.MortOwnerService;
import com.zdsoft.finance.casemanage.handleMortgage.entity.Detain;
import com.zdsoft.finance.casemanage.handleMortgage.entity.MaterialPromise;
import com.zdsoft.finance.casemanage.handleMortgage.entity.Notarize;
import com.zdsoft.finance.casemanage.handleMortgage.service.DetainService;
import com.zdsoft.finance.casemanage.handleMortgage.service.MaterialPromiseService;
import com.zdsoft.finance.casemanage.handleMortgage.service.NotarizeService;
import com.zdsoft.finance.casemanage.handleMortgage.vo.DetainVo;
import com.zdsoft.finance.casemanage.handleMortgage.vo.MaterialPromiseVo;
import com.zdsoft.finance.casemanage.handleMortgage.vo.NotarizeVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HandleMortgageAppController.java 
 * @ClassName: HandleMortgageAppController 
 * @Description: App接口办理抵押Controller
 * @author zhoushichao 
 * @date 2017年2月28日 下午8:44:19 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("server/casemanage/handleMortgage")
public class HandleMortgageAppController extends BaseController {

	@Autowired
    private CaseApplyService caseApplyService;
	@Autowired
	private NotarizeService notarizeService;
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private PledgeInfoService pledgeInfoService;
	@Autowired
	private DetainService detainService;
	@Autowired
	private MaterialPromiseService materialPromiseService;
	@Autowired
	private MortOwnerService mortOwnerService;
	@Autowired
	CED CED;
	
	/**
	 * 
	 * @Title: findHandleMortgage 
	 * @Description: 办理抵押（主页面）
	 * @author zhoushichao 
	 * @param projectId   案件id
	 * @param processInstanceId  流程id
	 * @param businessKey  业务id
	 * @param taskInstanceId  任务实例id
	 * @return
	 */
	@RequestMapping("/findHandleMortgage")
	@ResponseBody
    public String findHandleMortgage(String projectId, String processInstanceId, String businessKey, String taskInstanceId){
		if (ObjectHelper.isEmpty(projectId)) {
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
        try {
            // 查询案件的基础信息
            Map<String, String> condition = caseApplyService.findCaseApplyByCondition(projectId);
            condition.put("processInstanceId", processInstanceId);
            condition.put("businessKey", businessKey);
            condition.put("taskInstanceId", taskInstanceId);
            return AppServerUtil.buildJsonObject(condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取案件基础信息失败！"+e);
            return AppServerUtil.buildError(AppStatus.SystemError);
        }
    }
	
	/**
	 * 
	 * @Title: findNotarizeList 
	 * @Description: 公证信息查询列表
	 * @author zhoushichao 
	 * @param caseApplyId  案件Id
	 * @return
	 */
	@RequestMapping("/findNotarizeList")
	@ResponseBody
	public String findNotarizeList(String caseApplyId) {
		
		if (ObjectHelper.isEmpty(caseApplyId)) {
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
		List<Notarize> notarizeList = null;
		List<NotarizeVo> notarizeVoList = new ArrayList<NotarizeVo>();
		try {
			notarizeList = notarizeService.findByCaseApplyId(caseApplyId);
			if (notarizeList.size() == 0) {
				//获取公证类型
				List<SimpleCodeDto> simpleCodeDtoList = CED.querySimpleCodeByCategoryId(Notarize.CATEGORY_ID);
				for (SimpleCodeDto simpleCodeDto : simpleCodeDtoList) {
					NotarizeVo vo = new NotarizeVo();
					vo.setCaseApplyId(caseApplyId);
					vo.setNotarizeType(simpleCodeDto.getFullCode());
					vo.setNotarizeTypeName(simpleCodeDto.getName());
					notarizeVoList.add(vo);
				}
			}else {
				for(Notarize notarize : notarizeList){
					NotarizeVo vo = new NotarizeVo(notarize);
					notarizeVoList.add(vo);
				}
			}
			
			//添加返回信息
			List<Map<String, String>> notarizeVoMap = new ArrayList<Map<String,String>>();
			for (NotarizeVo notarizeVo : notarizeVoList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", notarizeVo.getId());
				map.put("caseApplyId", notarizeVo.getCaseApplyId());
				map.put("notarizeType", notarizeVo.getNotarizeType());
				map.put("notarizeTypeName", notarizeVo.getNotarizeTypeName());
				map.put("notarizeOffice", notarizeVo.getNotarizeOffice());
				map.put("notarizeDate", String.valueOf(notarizeVo.getNotarizeDate()));
				map.put("notarizeProvideDate", String.valueOf(notarizeVo.getNotarizeProvideDate()));
				map.put("remark", notarizeVo.getRemark());
				notarizeVoMap.add(map);
			}
			return AppServerUtil.buildJsonList(notarizeVoMap);
		} catch (Exception e) {
			logger.error("获取公证信息失败！"+e);
			e.printStackTrace();
			return AppServerUtil.buildError(AppStatus.SystemError);
		}
	}
	
	/**
	 * 
	 * @Title: findDetainList 
	 * @Description: 查册入押列表（案件押品信息）
	 * @author zhoushichao 
	 * @param caseApplyId  案件Id
	 * @return
	 */
	@RequestMapping("/findDetainList")
	@ResponseBody
	public String findDetainList(String caseApplyId) {
		if (ObjectHelper.isEmpty(caseApplyId)) {
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
		// 分页抵押信息
		List<HouseProperty> housePropertyList = new ArrayList<HouseProperty>();
		try {
			housePropertyList = housePropertyService.findByCaseApplyId(caseApplyId);
			//添加返回信息
			List<Map<String, String>> housePropertyMap = new ArrayList<Map<String,String>>();
			for (HouseProperty houseProperty : housePropertyList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", houseProperty.getId());
				map.put("communityName", houseProperty.getCommunityName());
				map.put("area", houseProperty.getArea());
				//获取评估价抵押成数
				List<PledgeInfo> pledgeInfo = pledgeInfoService.findByHouseId(houseProperty.getId());
				if(ObjectHelper.isNotEmpty(pledgeInfo)){
                    if(ObjectHelper.isNotEmpty(pledgeInfo.get(0).getPercentage())){
                        map.put("percentage", pledgeInfo.get(0).getPercentage().toString());
                    }else{
                        map.put("percentage", "");
                    }
                }else{
                    map.put("percentage", "");
                }
				
				housePropertyMap.add(map);
			}
				
			return AppServerUtil.buildJsonList(housePropertyMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查找查册入押列表失败:"+e);
			return AppServerUtil.buildError(AppStatus.SystemError);
		}
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateNotarize 
	 * @Description: 批量保存公证信息
	 * @author zhoushichao 
	 * @param notarizeList Json公证信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveOrUpdateNotarize")
    @ResponseBody
    private String saveOrUpdateNotarize(String notarizeList) {
		List<Notarize> list = new ArrayList<Notarize>();
		if(ObjectHelper.isNotEmpty(notarizeList)){
			JSONArray notarizeListArray = JSONArray.fromObject(notarizeList);
			list = JSONArray.toList(notarizeListArray, new Notarize(),new JsonConfig());
		}else{
			 return AppServerUtil.buildError(AppStatus.ArgsError);
		}
        try {
        	//保存公证信息
        	notarizeService.saveOrUpdateNotarizeList(list);
            return AppServerUtil.buildJsonObject(AppStatus.Succeed);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存公证信息失败"+e);
            return AppServerUtil.buildError(AppStatus.SystemError);
        }
    }
	
	/**
	 * 
	 * @Title: editDetain 
	 * @Description: 查册入押--押品信息初始化
	 * @author zhoushichao 
	 * @param housePropertyId  房产Id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/editDetain")
	@ResponseBody
	public String editDetain(String housePropertyId) {
		if (ObjectHelper.isEmpty(housePropertyId)) {
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
		// 房产信息
		AppInChargeVo appInChargeVo = new AppInChargeVo();
		try {
			//查询房产信息
			Map<String, Object> map = detainService.findByAppInChargeHousePropertyId(housePropertyId);
			
			HouseProperty houseProperty = (HouseProperty) map.get("houseProperty");
			AppHousePropertyVo appHousePropertyVo = new AppHousePropertyVo();
			appHousePropertyVo.setId(houseProperty.getId());
			appHousePropertyVo.setCommunityName(houseProperty.getCommunityName());
			appInChargeVo.setAppHousePropertyVo(appHousePropertyVo);
			
			// 查询抵押信息
			List<AppPledgeInfoVo> appPledgeInfoVoList = new ArrayList<AppPledgeInfoVo>();
			if (ObjectHelper.isNotEmpty(housePropertyId)) {
				List<PledgeInfo> pledgeInfoList = (List<PledgeInfo>) map.get("pledgeInfoList");
				for(PledgeInfo info : pledgeInfoList){
					PledgeInfoVo pledgeInfo = new PledgeInfoVo(info);
					AppPledgeInfoVo vo = new AppPledgeInfoVo();
					vo.setId(info.getId());
					vo.setPledgeType(info.getPledgeType());
					vo.setPledgeTypeName(pledgeInfo.getPledgeTypeName());
					appPledgeInfoVoList.add(vo);
				}
			}
			appInChargeVo.setAppPledgeInfoVoList(appPledgeInfoVoList);
			
			
			 //查询产权状态信息
            Search search = (Search) map.get("search");
            if(ObjectHelper.isNotEmpty(search)){
            	AppSearchVo appSearchVo = new AppSearchVo();
            	appSearchVo.setId(search.getId());
            	appSearchVo.setSearchStatus(search.getSearchStatus());
                appInChargeVo.setAppsearchVo(appSearchVo);
            }
			
            // 查询产权人信息
    		List<AppPropertyOwnerVo> appPropertyOwnerVoList = new ArrayList<AppPropertyOwnerVo>();
			if (ObjectHelper.isNotEmpty(housePropertyId)) {
				List<PropertyOwner> propertyOwnerList = (List<PropertyOwner>) map.get("propertyOwnerList");
				for(PropertyOwner owner : propertyOwnerList){
					AppPropertyOwnerVo vo = new AppPropertyOwnerVo();
					vo.setId(owner.getId());
					vo.setOwnerName(owner.getOwnerName());
					appPropertyOwnerVoList.add(vo);
				}
			}
			appInChargeVo.setAppPropertyOwnerVoList(appPropertyOwnerVoList);
			
			 //查询查册入押信息
            Detain detain = (Detain) map.get("detain");
            if(ObjectHelper.isNotEmpty(detain)){
            	DetainVo detainVo = new DetainVo(detain);
            	AppDetainVo appDetainVo = new AppDetainVo();
            	appDetainVo.setId(detainVo.getId());
            	appDetainVo.setHousePropertyId(detainVo.getHousePropertyId());
            	appDetainVo.setPledgeType(detainVo.getPledgeType());
            	appDetainVo.setPledgeTypeName(detainVo.getPledgeTypeName());
            	appDetainVo.setPredictCertifiedDate(detainVo.getPredictCertifiedDate());
            	appDetainVo.setPresentId(detainVo.getPresentId());
            	if(ObjectHelper.isNotEmpty(detainVo.getPresentId())){
            		MortOwner mortOwner = mortOwnerService.findOne(detainVo.getPresentId());
            		appDetainVo.setPresentName(mortOwner.getOwnerName());
            	}
            	
            	appDetainVo.setPresentPledgeDate(detainVo.getPresentPledgeDate());
            	appInChargeVo.setAppDetainVo(appDetainVo);
            }
			
			return AppServerUtil.buildJsonObject(appInChargeVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查册入押--押品信息初始化失败"+e);
			return AppServerUtil.buildError(AppStatus.SystemError);
		}
	}
	
	/**
	 * 
	 * @Title: findMortgageHolder 
	 * @Description: 获取案件机构的抵押权证人Id和name
	 * @author dengyy 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/findMortgageHolder")
    @ResponseBody
	public String findMortgageHolder(String caseApplyId){
	    logger.info("获取案件机构的抵押权证人");
	    logger.debug("案件id："+caseApplyId);
	    List<Map<String, String>> map = new ArrayList<Map<String, String>>();
	    try {
	        //获取案件信息
            CaseApply caseApply= caseApplyService.findOne(caseApplyId);
            if(ObjectHelper.isNotEmpty(caseApply)){
                //案件对应公司的抵押权证人信息
                //TODO 后期修改为常量类中维护
                List<MortOwner> list = mortOwnerService.findByOrgCode(caseApply.getMechanismCode(),"YWDM0012202");
                //转换json数据
                for (MortOwner mortOwner : list) {
                    Map<String, String> maps = new HashMap<String, String>();
                    maps.put("fullcode", mortOwner.getId());
                    maps.put("name", mortOwner.getOwnerName());
                    map.add(maps);
                }
            }
            return AppServerUtil.buildJsonObject(map) ;
        } catch (BusinessException e) {
            logger.error("获取抵押权证人信息失败！"+e);
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError);
        }
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateDetain 
	 * @Description: 保存查册入押
	 * @author zhoushichao 
	 * @param request  请求参数
	 * @return
	 */
	@RequestMapping("/saveOrUpdateDetain")
	@ResponseBody
	public String saveOrUpdateDetain(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
        //获取参数
		String id = request.getParameter("id");
        String housePropertyId = request.getParameter("housePropertyId");
        String pledgeType = request.getParameter("pledgeType");
        String presentPledgeDate = request.getParameter("presentPledgeDate");
        String presentId = request.getParameter("presentId");
        String predictCertifiedDate = request.getParameter("predictCertifiedDate");
        if (ObjectHelper.isEmpty(housePropertyId) || ObjectHelper.isEmpty(pledgeType)
            || ObjectHelper.isEmpty(presentPledgeDate) || ObjectHelper.isEmpty(presentId) || ObjectHelper.isEmpty(predictCertifiedDate)) {
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
        Detain detain = new Detain();
        try {
	        if (ObjectHelper.isNotEmpty(id)) {
	        	detain = detainService.findOne(id);
			}
	        detain.setHousePropertyId(housePropertyId);
	        detain.setPledgeType(pledgeType);
	        detain.setPresentPledgeDate(new Long(presentPledgeDate));
	        detain.setPresentId(presentId);
	        detain.setPredictCertifiedDate(new Long(predictCertifiedDate));
	        	
        	//保存查册入押
        	detain = detainService.saveOrUpdateEntity(detain);
        	if(ObjectHelper.isNotEmpty(id)){
        		map.put("message", "修改查册入押成功！");
			}else {
				map.put("message", "保存查册入押成功！");
			}
        	map.put("id", detain.getId());
        	map.put("status", ResponseMsg.SUCCESS);
        	
			return ObjectHelper.objectToJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存查册入押失败"+e);
			return AppServerUtil.buildError(AppStatus.SystemError);
		}
	}
	
	/**
	 * 
	 * @Title: findMaterialpromiseList 
	 * @Description: 后补资料承诺查询列表
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @return
	 */
	@RequestMapping("/findMaterialpromiseList")
	@ResponseBody
	public String findMaterialpromiseList(String caseApplyId) {
		
		// 后补资料承诺
		List<MaterialPromise> naterialPromiseList = null;
		List<MaterialPromiseVo> naterialPromiseVoList = new ArrayList<MaterialPromiseVo>();
		//后补资料类型
		List<String> codes = new ArrayList<String>();
		//设置借款借据
		codes.add(MaterialPromise.BORROWER_IOU);
		//设置补充合同
		codes.add(MaterialPromise.SUPPLEMENTARY);
		//设置他项权证
		codes.add(MaterialPromise.HIS_WARRANT);
		//设置委托公证书
		codes.add(MaterialPromise.NOTARIZATION);
		try {
			naterialPromiseList = materialPromiseService.queryByCaseApplyIdAndMaterialTypeCode(caseApplyId, codes);
			if (naterialPromiseList.size() == 0) {
				//获取操作人
				EmpDto emp = CED.getLoginUser();
				//获取系统当前时间
				Long currentDate = new Long(TimeUtil.getCurrentDateInteger());
				//获取借款借据
				String borrowerIou = CED.loadSimpleCodeNameByFullCode(MaterialPromise.BORROWER_IOU);
				MaterialPromiseVo voA = new MaterialPromiseVo();
				voA.setCaseApplyId(caseApplyId);
				voA.setMaterialTypeCode(MaterialPromise.BORROWER_IOU);
				voA.setMaterialTypeName(borrowerIou);
				voA.setOperatorCode(emp.getEmpCd());
				voA.setOperatorName(emp.getEmpNm());
				voA.setPromiseDate(currentDate);
				//获取补充合同
				String supplementary = CED.loadSimpleCodeNameByFullCode(MaterialPromise.SUPPLEMENTARY);
				MaterialPromiseVo voB = new MaterialPromiseVo();
				voB.setCaseApplyId(caseApplyId);
				voB.setMaterialTypeCode(MaterialPromise.SUPPLEMENTARY);
				voB.setMaterialTypeName(supplementary);
				voB.setOperatorCode(emp.getEmpCd());
				voB.setOperatorName(emp.getEmpNm());
				voB.setPromiseDate(currentDate);
				//获取他项权证
				String hisWarrant = CED.loadSimpleCodeNameByFullCode(MaterialPromise.HIS_WARRANT);
				MaterialPromiseVo voC = new MaterialPromiseVo();
				voC.setCaseApplyId(caseApplyId);
				voC.setMaterialTypeCode(MaterialPromise.HIS_WARRANT);
				voC.setMaterialTypeName(hisWarrant);
				voC.setOperatorCode(emp.getEmpCd());
				voC.setOperatorName(emp.getEmpNm());
				voC.setPromiseDate(currentDate);
				//获取委托公证书
				String notarization = CED.loadSimpleCodeNameByFullCode(MaterialPromise.NOTARIZATION);
				MaterialPromiseVo voD = new MaterialPromiseVo();
				voD.setCaseApplyId(caseApplyId);
				voD.setMaterialTypeCode(MaterialPromise.NOTARIZATION);
				voD.setMaterialTypeName(notarization);
				voD.setOperatorCode(emp.getEmpCd());
				voD.setOperatorName(emp.getEmpNm());
				voD.setPromiseDate(currentDate);
				
				naterialPromiseVoList.add(voA);
				naterialPromiseVoList.add(voB);
				naterialPromiseVoList.add(voC);
				naterialPromiseVoList.add(voD);
			}else {
				for(MaterialPromise materialPromise : naterialPromiseList){
					MaterialPromiseVo vo = new MaterialPromiseVo(materialPromise);
					naterialPromiseVoList.add(vo);
				}
			}
			
			//添加返回信息
			List<Map<String, String>> materialPromiseMap = new ArrayList<Map<String,String>>();
			for (MaterialPromiseVo materialPromiseVo : naterialPromiseVoList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", materialPromiseVo.getId());
				map.put("caseApplyId", materialPromiseVo.getCaseApplyId());
				map.put("materialTypeCode", materialPromiseVo.getMaterialTypeCode());
				map.put("materialTypeName", materialPromiseVo.getMaterialTypeName());
				map.put("predictDate", String.valueOf(materialPromiseVo.getPredictDate()));
				map.put("operatorCode", materialPromiseVo.getOperatorCode());
				map.put("operatorName", materialPromiseVo.getOperatorName());
				map.put("promiseDate", String.valueOf(materialPromiseVo.getPromiseDate()));
				materialPromiseMap.add(map);
			}
			return AppServerUtil.buildJsonList(materialPromiseMap);
		} catch (Exception e) {
			logger.error("获取后补资料承诺失败！"+e);
			e.printStackTrace();
			return AppServerUtil.buildError(AppStatus.SystemError);
		}
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateMaterialpromise 
	 * @Description: 批量保存后补资料承诺
	 * @author zhoushichao 
	 * @param materialPromiseList Json后补资料承诺
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveOrUpdateMaterialpromise")
    @ResponseBody
    private String saveOrUpdateMaterialpromise(String materialPromiseList) {
		List<MaterialPromise> list = new ArrayList<MaterialPromise>();
		if(ObjectHelper.isNotEmpty(materialPromiseList)){
			JSONArray materialPromiseListListArray = JSONArray.fromObject(materialPromiseList);
			list = JSONArray.toList(materialPromiseListListArray, new MaterialPromise(),new JsonConfig());
		}else{
			 return AppServerUtil.buildError(AppStatus.ArgsError);
		}
        try {
        	//批量保存后补资料承诺
        	materialPromiseService.saveMaterialPromiseList(list);
            return AppServerUtil.buildJsonObject(AppStatus.Succeed);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("批量保存后补资料承诺失败"+e);
            return AppServerUtil.buildError(AppStatus.SystemError);
        }
    }
}