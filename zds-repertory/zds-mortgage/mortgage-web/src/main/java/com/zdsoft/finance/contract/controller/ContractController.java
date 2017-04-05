package com.zdsoft.finance.contract.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.service.CommonService;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.entity.ConCaseContractSupplement;
import com.zdsoft.finance.contract.entity.ConCaseContractSupplement2;
import com.zdsoft.finance.contract.service.ConCaseContractService;
import com.zdsoft.finance.contract.service.ConCaseContractSupplement2Service;
import com.zdsoft.finance.contract.service.ConCaseContractSupplementService;
import com.zdsoft.finance.contract.vo.ConCaseContractSupplement2Vo;
import com.zdsoft.finance.contract.vo.ConCaseContractSupplementAllVo;
import com.zdsoft.finance.contract.vo.ConCaseContractSupplementVo;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
/**
 * 合同管理
 * @author huangdk
 * @date 2016-1-6
 */
@Controller
@RequestMapping("/contract")
public class ContractController extends BaseController {
	@Autowired
	private ConCaseContractService conCaseContractService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private CommonService _CommonService;
	
	@Autowired
	private CED ced;
	@Autowired
	private ConCaseContractSupplementService conCaseContractSupplementService;
	@Autowired
	private ConCaseContractSupplement2Service conCaseContractSupplement2Service;
	@Autowired
	private HousePropertyService housePropertyService;
	
	   /**
     * 机构合同报备入口
     * @return 机构合同报备列表
     * 
     * 2017.3.11 功能重复注释 dengyy
     */
//  @RequestMapping("/initContract")
//  @UriKey(key = "com.zdsoft.finance.contract.initContract")
//  @Menu(resource = "com.zdsoft.finance.contract.initContract", label = "机构合同报备", group = "contract", order = 1)
//  public ModelAndView initContract() {
//      return new ModelAndView("/contract/contract_list");
//  }
	
	
	/**
	 * 案件查询
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/contractList")
	@UriKey(key = "com.zdsoft.finance.contract.contractList")
	@ResponseBody
	public String contractList(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			Page<ContractModel> page = null;
			//查询数据模拟
			List<ContractModel> list = new ArrayList<>();
		
			for (int i = 0; i < 50; i++) {
				ContractModel temp = new ContractModel();
				temp.setid(i);
				
				temp.setapplyName(temp.getapplyName()+String.valueOf(i));
				list.add(temp);
			}
		
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("列表查询成功");
			msg.setRows(list);
			msg.setTotal(Long.valueOf(list.size()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询失败", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/**
	 * 申请
	 * @return
	 */
	@RequestMapping("/addContractApply")
	@UriKey(key = "com.zdsoft.finance.contract.addContractApply")
	@ResponseBody
	public ModelAndView addContractApply() {
		return new ModelAndView("/contract/contract_add");
	}
	
	
	/**
	 * 案件合同管理列表
	 * @return 案件合同管理列表
	 */
	@RequestMapping("/caseContractList")
	@UriKey(key = "com.zdsoft.finance.contract.caseContractList")
	@Menu(resource = "com.zdsoft.finance.contract.caseContractList", label = "案件合同管理", group = "contract", order = 4)
	public ModelAndView caseContractList() {
		return new ModelAndView("/contract/caseContract_List");
	}
	
	/**
	 * 案件合同查询
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getCaseContractList")
	@UriKey(key = "com.zdsoft.finance.contract.getCaseContractList")
	@ResponseBody
	public String getCaseContractList(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		Page<Map<String, Object>> pms = null;
		
		try {
			pms = _CommonService.GetCaseContractPages(pageable,queryObjs);
			List<Map<String, Object>> records = pms.getRecords();
			//数据处理
			if(ObjectHelper.isNotEmpty(records)){
				for (Map<String, Object> map : records) {
					//贷款期限转换为月
					String applyDeadlineUnit = (String) map.get("APPLYTERMUNIT");
					
					int applyDeadline = 0;
					if(ObjectHelper.isNotEmpty(map.get("APPLYTERM"))){
						 applyDeadline = Integer.parseInt(map.get("APPLYTERM").toString());
					}
					//年转换为月
					if("0931001".equals(applyDeadlineUnit)){
						applyDeadline = applyDeadline*12;
					}else if("0931003".equals(applyDeadlineUnit)){//日转换为月
						applyDeadline = applyDeadline/30;
					}
					map.put("applyTermName", applyDeadline);
				}
			}
			
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("列表查询成功");
			msg.setRows(pms.getRecords());
			msg.setTotal(pms.getTotalRows());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("列表查询失败", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/**
	 * 案件合同管理列表
	 * @return 案件合同管理列表
	 */
	@RequestMapping("/caseContractPrint")
	@UriKey(key = "com.zdsoft.finance.contract.caseContractPrint")
	@ResponseBody
	public ModelAndView caseContractPrint(String caseApplyId, String operationType) {
		ModelAndView modelAndView =new ModelAndView("/contract/caseContract_print");
		Map<String,Object> caseContractModel = new HashMap<String,Object>(); 
		if("print".equals(operationType) && ObjectHelper.isNotEmpty(caseApplyId)){
			try {
		    List<Map<String, Object>> list=	conCaseContractService.getConCaseContractById(caseApplyId);
		    if(ObjectHelper.isNotEmpty(list)){
		    	caseContractModel = list.get(0);
		    	Object startDate=caseContractModel.get("CONTRACTSTARTDATE");
		    	Object endDate=caseContractModel.get("CONTRACTENDDATE");
		    	if(ObjectHelper.isNotEmpty(startDate) && ObjectHelper.isNotEmpty(endDate)){
		    		String sDate=startDate.toString();
		    		String eDate=endDate.toString();
		    		
		    		int yearDay1=Integer.parseInt(sDate.substring(0, 4));
		    		int yearDay2=Integer.parseInt(eDate.substring(0, 4));
		    		int monthDay1=Integer.parseInt(sDate.substring(4, 6));
		    		int monthDay2=Integer.parseInt(eDate.substring(4, 6));
		    		int dayDay1=Integer.parseInt(sDate.substring(6, 8));
		    		int dayDay2=Integer.parseInt(eDate.substring(6, 8));
		    		
		    		sDate=yearDay1+"-"+monthDay1+"-"+dayDay1;
		    		eDate=yearDay2+"-"+monthDay2+"-"+dayDay2;
			    	caseContractModel.put("CONTRACTSTARTDATE2", sDate);
			    	caseContractModel.put("CONTRACTENDDATE2", eDate);
			    	
			    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    	Date s=format.parse(sDate);
			    	Date e=format.parse(eDate);
			    	int days = (int) ((e.getTime() - s.getTime()) / (1000*3600*24));
			    	String address="";
			    	
			    	if(caseContractModel.get("PROVINCE")!=null){
			    		address+=ced.loadSimpleCodeNameByFullCode(caseContractModel.get("PROVINCE").toString());
			    	}
			    	if(caseContractModel.get("CITY")!=null){
			    		address+="/";
			    		address+=ced.loadSimpleCodeNameByFullCode(caseContractModel.get("CITY").toString());
			    	}
			    	if(caseContractModel.get("DISTRICT")!=null){
			    		address+="/";
			    		address+=ced.loadSimpleCodeNameByFullCode(caseContractModel.get("DISTRICT").toString());
			    	}
			    	if(caseContractModel.get("ADDRESS")!=null){
			    		address+="/";
			    		address+=ced.loadSimpleCodeNameByFullCode(caseContractModel.get("ADDRESS").toString());
			    	}
			    	caseContractModel.put("MAILINGADDRESS", address);
			    	caseContractModel.put("Date", days);
		    	}
		    	modelAndView.addObject("caseContractInfo", caseContractModel);
		    }
		    List<Map<String, Object>> caselist=conCaseContractService.getContractDetailByCaseContractId(caseApplyId);
		    if(!ObjectHelper.isNotEmpty(caselist)){
		    	caselist=new ArrayList<Map<String, Object>>();
		    }
		    modelAndView.addObject("caseInfo", caselist);
		    
		    
		    List<Map<String, Object>> printTplList=conCaseContractService.getContractPrintTplByCaseApplyId(caseApplyId);
		    if(!ObjectHelper.isNotEmpty(printTplList)){
		    	printTplList=new ArrayList<Map<String, Object>>();
		    }
		    modelAndView.addObject("printTplList", printTplList);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("查询失败", e);
			}
		}
		
		return modelAndView;
		//return new ModelAndView("/contract/caseContract_print",caseContractModel);
	}
	
	/**
	 * 合同套打预览
	 * @return 合同套打预览
	 */
	@RequestMapping("/caseContractView")
	@UriKey(key = "com.zdsoft.finance.contract.caseContractView")
	@ResponseBody
	public ModelAndView caseContractView() {
		return new ModelAndView("/contract/caseContract_view");
	}
	
	/**
	 * 查询合同信息补充
	 * @return 合同信息补充
	 */
	@RequestMapping("/caseContractSupplement")
	@UriKey(key = "com.zdsoft.finance.contract.caseContractSupplement")
	@ResponseBody
	public ModelAndView caseContractSupplement(String caseApplyId , String id, String capitalUseFor) {
		ModelAndView modelAndView= new ModelAndView("/contract/contractSupplement_edit");
		try 
		{
			ConCaseContractSupplementVo vo=new ConCaseContractSupplementVo();
			List<ConCaseContractSupplement> infos = conCaseContractSupplementService.getConCaseContractSupplementByCaseContractId(caseApplyId);
			if(ObjectHelper.isNotEmpty(infos)){
				ConCaseContractSupplement conCaseContractSupplement = infos.get(0);
				vo = new ConCaseContractSupplementVo(conCaseContractSupplement);
				modelAndView.addObject("info", vo);
				modelAndView.addObject("conCaseContractSupplementId", vo.getId());
			}
			else
			{
				//合同编号设置
				ConCaseContract conCaseContract=conCaseContractService.findOne(id);
				if(ObjectHelper.isNotEmpty(conCaseContract.getContractNo()))
				{
					vo.setBc2(conCaseContract.getContractNo());
				}
				else
				{
					vo.setBc2( "HT" + new Date().getTime());
				}
				//默认带出贷款申请中的贷款用途
				vo.setBc1(capitalUseFor);
				modelAndView.addObject("info", vo);
			}
			
			
			
			List<ConCaseContractSupplement2> infos2 = conCaseContractSupplement2Service.getConCaseContractSupplement2ByCaseContractId(caseApplyId);
			if(ObjectHelper.isNotEmpty(infos2)){
				ConCaseContractSupplement2 conCaseContractSupplement2 = infos2.get(0);
				ConCaseContractSupplement2Vo vo2 = new ConCaseContractSupplement2Vo(conCaseContractSupplement2);
				modelAndView.addObject("conCaseContractSupplement2Id", vo2.getId());
				modelAndView.addObject("info2", vo2);
			}
			modelAndView.addObject("caseApplyId", caseApplyId);
		} 
		catch (Exception e) {
			e.printStackTrace();
			logger.error("查询失败", e);
		}
		
		return modelAndView;
	}
	
	/**
	 * 保存合同信息补充
	 * @param conCaseContractSupplementAllVo
	 * @return
	 */
	@RequestMapping("/saveCaseContractSupplement")
	@UriKey(key = "com.zdsoft.finance.contract.saveCaseContractSupplement")
	@ResponseBody
	public ResponseMsg saveCaseContractSupplement(ConCaseContractSupplementAllVo conCaseContractSupplementAllVo) {
		
		ResponseMsg msg = new ResponseMsg();

		// 增加
		try {
			ConCaseContractSupplementVo conCaseContractSupplementVo = conCaseContractSupplementAllVo.getConCaseContractSupplementVo();
			ConCaseContractSupplement2Vo conCaseContractSupplement2Vo = conCaseContractSupplementAllVo.getConCaseContractSupplement2Vo();
			ConCaseContractSupplement conCaseContractSupplement = conCaseContractSupplementVo.toPo();
			ConCaseContractSupplement2 conCaseContractSupplement2 = conCaseContractSupplement2Vo.toPo();
			ConCaseContractSupplement saveConCaseContractSupplement=conCaseContractSupplementService.saveOrUpdateEntity(conCaseContractSupplement);
			ConCaseContractSupplement2 saveConCaseContractSupplement2=conCaseContractSupplement2Service.saveOrUpdateEntity(conCaseContractSupplement2);
			Map<String, Object> Map = new HashMap<String, Object>();
			Map.put("conCaseContractSupplementId", saveConCaseContractSupplement.getId());
			Map.put("conCaseContractSupplement2Id", saveConCaseContractSupplement2.getId());
			msg.setOptional(Map);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 查询房产表
	 *
	 * @author wangnengduo
	 * @param caseApplyId
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping(value = "/getMktGouseProperty")
	@UriKey(key = "com.zdsoft.finance.contract.getMktGouseProperty")
	@ResponseBody
	public String getMktGouseProperty( String jsoncallback, String caseApplyId) {
		List<HousePropertyVo> listVo = new ArrayList<HousePropertyVo>();
		ResponseMsg responseMsg = new ResponseMsg();
		try {
			List<HouseProperty> infos =housePropertyService.findByCaseApplyId(caseApplyId);
			if (ObjectHelper.isNotEmpty(infos)) {
				for (HouseProperty houseProperty : infos) {
					HousePropertyVo vo=new HousePropertyVo(houseProperty);
					listVo.add(vo);
				}
			}
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
			responseMsg.setRows(listVo);
			
		} catch (Exception e) {
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			responseMsg.setMsg("查询错误");
			e.printStackTrace();
			logger.error("查询失败", e);
		}
		return ObjectHelper.objectToJson(responseMsg, jsoncallback);
	}
	
	/**
	 * 修改保存房产表
	 * @param HousePropertyVo housePropertyVo
	 * @return
	 */
	@RequestMapping("/saveMktHouseProperty")
	@UriKey(key = "com.zdsoft.finance.contract.saveMktHouseProperty")
	@ResponseBody
	public ResponseMsg saveMktHouseProperty(HousePropertyVo housePropertyVo) {
		
		ResponseMsg msg = new ResponseMsg();

		// 修改
		try {
			HouseProperty houseProperty=housePropertyService.findOne(housePropertyVo.getId());
			houseProperty.setCreditorsAmount(housePropertyVo.getCreditorsAmount());
			houseProperty.setLandCertificateNo(housePropertyVo.getLandCertificateNo());
			houseProperty.setLandUseArea(housePropertyVo.getLandUseArea());
			houseProperty.setMortgageAmount1(housePropertyVo.getMortgageAmount1());
			houseProperty.setMortgageAmount2(housePropertyVo.getMortgageAmount2());
			housePropertyService.updateEntity(houseProperty);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
}
