package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.dto.CaseRelationCustomerDto;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.customer.vo.CreditVo;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.vo.FileStoreVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.json.JSONArray;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditInfomationController.java 
 * @ClassName: CreditInfomationController 
 * @Description: 资调-征信信息
 * @author liuhuan 
 * @date 2017年2月23日 上午9:53:38 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/casemanage/datasurvey/creditInfo")
public class CreditInfomationController extends BaseController{
	
	@Autowired
	private CreditService creditService;
	@Autowired
	private CaseApplyService caseApplyService;
	
	
	/** 
	 * @Title: loadCreditInfo 
	 * @Description: 征信页面-查看
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @return  
	 */ 
	@RequestMapping("/view")
	@UriKey(key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.view")
	public ModelAndView creditInfoView(String caseApplyId){
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/datasurvey/creditinformation_view",model);
	}
	/** 
	 * @Title: loadCreditInfo 
	 * @Description: 加载征信页面
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @return  
	 */ 
	@RequestMapping("/load")
	@UriKey(key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.edit")
	public ModelAndView loadCreditInfo(String caseApplyId){
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/datasurvey/creditinformation_list",model);
	}
	
    /** 
     * @Title: loadCreditList 
     * @Description: 加载征信信息列表
     * @author liuhuan 
     * @param jsoncallback
     * @param caseApplyId	案件id
     * @return  征信信息列表json
     */ 
    @RequestMapping("/loadCreditList")
    @UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.creditinfomation.loadCreditList")
    @ResponseBody
    public String loadCreditList(String jsoncallback, String caseApplyId) {
        ResponseMsg msg = new ResponseMsg();
        try {
            // 根据案件ID获取案件参与人列表
        	List<Credit> credits = creditService.findByCaseApplyIdAndCreditLinkCode(caseApplyId, Credit.LINK_CODE_SURVEY);
        	List<CreditVo> creditVOs = new ArrayList<CreditVo>();
        	List<CaseRelationCustomerDto> customers = creditService.findCreditListByCaseApplyId(caseApplyId);
        	if(ObjectHelper.isEmpty(customers)){
        		logger.error("error:案件异常-没有关联的客户！");
                msg.setResultStatus(ResponseMsg.APP_ERROR);
                msg.setMsg("案件相关客户征信列表查询失败");
                return ObjectHelper.objectToJson(msg, jsoncallback);
        	}
        	boolean iscustomerInCredit = false;//客户是否保存有征信信息
        	for (CaseRelationCustomerDto customerDto : customers) {
        		for(Credit credit : credits){
        			if(ObjectHelper.isEquals(customerDto.getCustomerId(), credit.getCustomerId())){
        				iscustomerInCredit = true;
        				CreditVo creditVo = new CreditVo(credit);
        				setCreditVo(customerDto, creditVo);
        				creditVOs.add(creditVo);
        				break;
        			}
        		}
        		if(!iscustomerInCredit){
        			Credit credit = creditService.saveCreditByCustomer(customerDto.getCustomerId(), caseApplyId);
        			CreditVo creditVo = new CreditVo(credit);
    				setCreditVo(customerDto, creditVo);
    				creditVOs.add(creditVo);
        		}
        		iscustomerInCredit = false;
        	}
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("案件相关客户征信列表查询成功");
            msg.setRows(creditVOs);
        } catch (Exception e) {
            logger.error("error:", e);
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("案件相关客户征信列表查询失败");
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }
	/** 
	 * @Title: setCreditVo 
	 * @Description: 设置征信vo值
	 * @author liuhuan 
	 * @param customerDto 客户对象
	 * @param creditVo 征信vo
	 */ 
	private void setCreditVo(CaseRelationCustomerDto customerDto, CreditVo creditVo) {
		creditVo.setCustomerId(customerDto.getCustomerId());
		creditVo.setCustomerName(customerDto.getCustomerName());
		creditVo.setCredentialType(customerDto.getCredentialType());
		creditVo.setCredentialTypeName(customerDto.getCredentialTypeName());
		creditVo.setCredentialNo(customerDto.getCredentialNo());
		creditVo.setJoinType(customerDto.getJoinType());
		creditVo.setJoinTypeName(customerDto.getJoinTypeName());
		creditVo.setActualUsePerson(customerDto.getActualUsePerson());
		creditVo.setActualUsePersonName(customerDto.getActualUsePersonName());
	}
	 
	/** 
	 * @Title: uploadedit 
	 * @Description: 上传征信附件的临时列表页面-跳转
	 * @author liuhuan 
	 * @param uploadCutomer 对应需上传的-客户-征信信息
	 * @return  
	 */ 
	@RequestMapping("/uploadCreditList")
	@UriKey(key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.uploadCreditList")
	public ModelAndView uploadCreditList(String uploadCutomer){
		ModelMap model = new ModelMap();
		if(ObjectHelper.isEmpty(uploadCutomer)){
			model.put("errorMsg", "数据异常,尝试关闭后重新点击上传.");
		}
		CreditVo creditVo = JSON.parseObject(uploadCutomer,CreditVo.class);
		
		CaseApply caseApply = null;
		try {
			caseApply = caseApplyService.findOne(creditVo.getCaseApplyId());
			model.put("productId", caseApply.getProductSubtypeId());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		model.put("uploadTitleName", "请上传: "+creditVo.getJoinTypeName()+'-'+creditVo.getCustomerName()+" 的征信附件！");
		model.put("joinTypeName", creditVo.getJoinTypeName());
		//资调环节
		String linkCode = CaseApply.CASE_STATUS_DATASURVEY.substring(CaseApply.CASE_STATUS_DATASURVEY.length()-2, CaseApply.CASE_STATUS_DATASURVEY.length());
		creditVo.setLinkStatusCode(linkCode);
		creditVo.setCreditLinkCode(Credit.LINK_CODE_SURVEY);
		String creditVoJson = JSON.toJSONString(creditVo);
		
		model.put("businessId", creditVo.getCustomerId());
		model.put("creditId", creditVo.getId());
		
		model.put("creditVoJson", creditVoJson);
		String sourceCode = CaseApplyStageEnumSimpleCodeEnum.getCaseApplyStageName(CaseApply.CASE_STATUS_DATASURVEY);
		model.put("sourceCode", sourceCode);
		return new ModelAndView("casemanage/datasurvey/creditinformation_add",model);
	}
	
	/** 
	 * @Title: saveCreditList 
	 * @Description: 保存-上传的征信附件
	 * @author liuhuan 
	 * @param attachmentList 附件集
	 * @param sourceCode 来源
	 * @param productId 产品id
	 * @param creditVoJson 征信必要信息
	 * @return  
	 */ 
	@RequestMapping("/saveCreditList")
	@UriKey(key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.saveCreditList")
	@ResponseBody
	public ResponseMsg saveCreditList(String attachmentList, String sourceCode, String productId, String creditVoJson){
		ResponseMsg msg = new ResponseMsg();
		try {
			if(ObjectHelper.isEmpty(attachmentList)){
				msg.setResultStatus(ResponseMsg.SYS_ERROR);
                msg.setMsg("请上传附件！");
                return msg;
			}
			if(ObjectHelper.isEmpty(sourceCode) || ObjectHelper.isEmpty(creditVoJson)){
				msg.setResultStatus(ResponseMsg.SYS_ERROR);
                msg.setMsg("系统异常！请联系管理员。");
                return msg;
			}
			JSONArray jsonArray = JSONArray.fromObject(attachmentList);
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> attachmentLists = jsonArray;
			
			CreditVo creditVo = JSON.parseObject(creditVoJson,CreditVo.class);
			
			Map<String,String> creditInfo = new HashMap<String, String>();
			//必传
			creditInfo.put("productId", productId);//产品id
			creditInfo.put("caseApplyId", creditVo.getCaseApplyId());//此处是案件id
			creditInfo.put("creditId", creditVo.getId());//征信id
			creditInfo.put("customerId", creditVo.getCustomerId());//客户id
			creditInfo.put("sourceCode", sourceCode);//来源-案件状态-名称
			creditInfo.put("linkCode", creditVo.getLinkStatusCode());//案件状态code后两位
			creditInfo.put("creditLinkCode", creditVo.getCreditLinkCode());//环节code(营销录入、资调录入、贷后录入)
			creditInfo.put("joinTypeName", creditVo.getJoinTypeName());//参与类型
			//授权书类型-必传
			creditInfo.put("credentialType", creditVo.getCredentialType());//证件类型
			creditInfo.put("credentialNo", creditVo.getCredentialNo());//证件号
			creditInfo.put("customerName", creditVo.getCustomerName());//客户姓名
			
			creditService.saveCreditAttachments(attachmentLists, creditInfo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上传附件失败!",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		} 
		return msg;
	}
	
	/** 
     * @Title: fileUploadTempData 
     * @Description: 上传征信附件的临时列表页面-加载
     * @author liuhuan 
     * @param uploadCutomer 对应需上传的-客户-征信
     * @param jsoncallback
     * @return  
     */ 
    @RequestMapping(value = "/fileUploadTempData",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.creditinfomation.fileUploadTempData")
    @ResponseBody
    public String fileUploadTempData(String businessId,String creditId,String jsoncallback){
    	ResponseMsg msg=new ResponseMsg();
        try{
        	List<FileStore> sourceData=this.creditService.findByCreditIdAndBusinessId(creditId, businessId);
        	
            List<FileStoreVo> returnList=new ArrayList<FileStoreVo>();
            if(ObjectHelper.isNotEmpty(sourceData)){
                for(FileStore temp:sourceData){
                	FileStoreVo vo=new FileStoreVo(temp);
                	String materiaCode = temp.getMateriaCode();
                	String materiaCodeName = isCreditAttement(materiaCode);
                	vo.setMateriaCodeName(materiaCodeName);
                    returnList.add(vo);
                }
            }
            msg.setRows(returnList);
        }catch (Exception e){
            msg.setMsg(e.getMessage());
            logger.error("查询上传临时数据出错",e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(msg,jsoncallback);
    }
    
    /** 
     * @Title: isCreditAttement 
     * @Description: 征信附件上传-判断征信附件类型
     * @author liuhuan 
     * @param materiaCode 资料Code
     * @return  
     */ 
    private String isCreditAttement(String materiaCode){
    	/* simpleCode数据固定 --若出现变动,此处代码必须添加或修改
    	 * 
    	 * YWDM001500405	借款人或买方征信
    	 * YWDM001500113	主借人征信身份证
    	 * YWDM001500114	主借人征信授权书
    	 * YWDM001500115	主借人配偶征信身份证
    	 * YWDM001500116	主借人配偶征信授权书
    	 * YWDM001500117	共同借款人征信身份证
    	 * YWDM001500118	共同借款人征信授权书
    	 * YWDM001500119	共同借款人配偶征信身份证
    	 * YWDM001500120	共同借款人配偶征信授权书
    	 * YWDM001500121	担保人征信身份证
    	 * YWDM001500122	担保人征信授权书
    	 * YWDM001500123	担保人配偶征信身份证
    	 * YWDM001500124	担保人配偶征信授权书
    	 */
    	if(ObjectHelper.isEquals(materiaCode, "YWDM001500405")){//借款人或买方征信
			return Credit.LINK_STATUS_CREDIT;//征信报告
		}
    	for(int i=13; i<23; i+=2){
	     	String temp = "YWDM0015001"+i;
	     	if(ObjectHelper.isEquals(materiaCode, temp)){
	     		return Credit.LINK_STATUS_CARD;//征信身份证
	     	}
        }
    	for(int i=14; i<25; i+=2){
    		String temp = "YWDM0015001"+i;
    		if(ObjectHelper.isEquals(materiaCode, temp)){
    			return Credit.LINK_STATUS_AUTHORIZATION;//征信授权书
    		}
    	}
    	return Credit.LINK_STATUS_UNSUCCESSFUL;
    }
    
}
