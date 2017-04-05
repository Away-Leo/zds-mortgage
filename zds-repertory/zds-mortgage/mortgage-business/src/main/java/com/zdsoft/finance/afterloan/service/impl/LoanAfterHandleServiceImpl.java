package com.zdsoft.finance.afterloan.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.afterloan.entity.EmergencyContact;
import com.zdsoft.finance.afterloan.entity.EmergencyContacts;
import com.zdsoft.finance.afterloan.entity.FollowInfo;
import com.zdsoft.finance.afterloan.repository.EmergencyContactRepository;
import com.zdsoft.finance.afterloan.repository.EmergencyContactsRepository;
import com.zdsoft.finance.afterloan.repository.FollowInfoRepository;
import com.zdsoft.finance.afterloan.service.AfterSuperviseService;
import com.zdsoft.finance.afterloan.service.FollowInfoService;
import com.zdsoft.finance.afterloan.service.LoanAfterHandleService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.AfterContact;
import com.zdsoft.finance.customer.entity.AfterCustomer;
import com.zdsoft.finance.customer.repository.AfterContactRepository;
import com.zdsoft.finance.customer.repository.AfterCustomerRepository;
import com.zdsoft.finance.customer.service.AfterContactService;
import com.zdsoft.finance.finance.entity.CustomerReceivable;
import com.zdsoft.finance.finance.service.CustomerReceivableService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.repository.CaseApplyAfterCustomerRepository;
import com.zdsoft.finance.marketing.repository.CaseApplyRepository;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import io.netty.util.internal.StringUtil;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanAfterHandleServiceImpl.java 
 * @ClassName: LoanAfterHandleServiceImpl 
 * @Description: 贷后处理ServiceImpl
 * @author huangwei 
 * @date 2017年3月10日 上午9:40:18 
 * @version V1.0
 */
@Service
public class LoanAfterHandleServiceImpl  extends BaseServiceImpl<CaseApply, CustomRepository<CaseApply, String>>
implements LoanAfterHandleService {
	@Autowired
	private CaseApplyRepository caseApplyRepository;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private CaseApplyAfterCustomerRepository caseApplyAfterCustomerRepository;
	@Autowired
	private AfterContactRepository afterContactRepository;
	@Autowired
	private AfterContactService afterContactService;
	@Autowired
	private EmergencyContactRepository emergencyContactRepository;
	@Autowired
	private AfterSuperviseService afterSuperviseService;
	@Autowired
	private EmergencyContactsRepository emergencyContactsRepository;
	@Autowired
	private FollowInfoRepository followInfoRepository;
	@Autowired
	private FollowInfoService followInfoService;
	@Autowired
	private BusiFormService busiFormService;
	@Autowired
	private CED CED;
	@Autowired
	private AfterCustomerRepository afterCustomerRepository;
	@Autowired
	private CustomerReceivableService customerReceivableService;
	/**
	 * @Title: getLoanAfterMessage 
	 * @Description: 获取相关案件信息
	 * @author huangwei 
	 * @param caseApplyId   案件id
	 */
	public ModelMap getCaseApplyDetail(String caseApplyId) throws Exception{
		ModelMap modelMap = new ModelMap();
		//案件对象
		CaseApply caseApply=caseApplyRepository.findOne(caseApplyId);
		//贷后贷款人对象
		AfterCustomer postLoanCustomer=null;
		List<CaseApplyAfterCustomer> caseApplyAfterCustomerList=caseApplyAfterCustomerRepository.findByCaseApplyIdAndJoinType(caseApplyId,CaseApplyAfterCustomer.MAIN_BORROW);
		 for(CaseApplyAfterCustomer cabc : caseApplyAfterCustomerList)
		 {
			postLoanCustomer=cabc.getAfterCustomer();
		 }
		 //案件详细信息
		 Map<String,Object> caseDetail=new HashMap<String,Object>();
		 caseDetail.put("caseApplyId", caseApply.getId());
		 caseDetail.put("caseApplyCode", caseApply.getCaseApplyCode());
		 caseDetail.put("orgName", caseApply.getMechanismName());
		 if(postLoanCustomer!=null)
		 {
			 caseDetail.put("customerName", postLoanCustomer.getCustomerName());
		 }
		 caseDetail.put("loanAmount", caseApply.getLoanApplyAnount());
		 //生成贷款期限
		 caseDetail.put("loanTerm",caseApply.getApplyTerm()+CED.loadSimpleCodeNameByFullCode(caseApply.getApplyTermUnit()));
		 caseDetail.put("balance", caseApply.getCaseApplyBalance());
		 modelMap.put("caseApply", caseDetail);
		return modelMap;
	}
	/**
	 * @Title: getContactsList 
	 * @Description: 获取联系人列表
	 * @author huangwei 
	 * @param caseApplyId   案件id
	 * @return
	 */
	public Page<Map<String, Object>> getContactsList(String caseApplyId,Pageable pageable)throws Exception{
		Page<Map<String, Object>> page = new PageImpl<>(pageable);
		List<Map<String, Object>> queryAllJoinCustomer = afterSuperviseService.queryAllJoinCustomer(caseApplyId);
		for(Map<String, Object> m : queryAllJoinCustomer)
		{
			m.put("need", "1");
		}
		page.setRecords(queryAllJoinCustomer);
		page.setTotalRows(Long.parseLong(queryAllJoinCustomer.size()+""));
//		return page;
		return page;

	}
	
	/**
	 * Title: getCustomerContacts  
	 * Description:    获取紧急联系人联系人联系方式
	 * @param customerId 用户id
	 * @param isEm  是否紧急联系人
	 * @return
	 * @throws Exception   
	 * @see com.zdsoft.finance.afterloan.service.LoanAfterHandleService#getCustomerContacts(java.lang.String, java.lang.String)
	 */
	public List<Map<String,Object>> getCustomerContacts(String customerId,String isEm)throws Exception{
		List<Map<String,Object>> contastData=new ArrayList<Map<String,Object>>();
		//如果为紧急联系人
		if(!StringUtil.isNullOrEmpty(isEm))
		{
			//紧急联系人列表
			List<EmergencyContact> contactList=emergencyContactRepository.findByCustomerId(customerId);
			for(EmergencyContact contact : contactList)
			{
				Map<String,Object> contactMap=new HashMap<String,Object>();
				contactMap.put("id", contact.getId());
				contactMap.put("contactType", contact.getContactType());
				contactMap.put("phoneNumber", contact.getPhoneNumber());
				contactMap.put("isDeleted", contact.getIsDeleted());
				contastData.add(contactMap);
			}
		}
		//如果不为紧急联系人
		else
		{
			//贷后联系人列表
			List<AfterContact> contactList=afterContactRepository.findByCustomerId(customerId);
			for(AfterContact contact : contactList)
			{
				Map<String,Object> contactMap=new HashMap<String,Object>();
				contactMap.put("id", contact.getId());
				contactMap.put("contactType", contact.getContactType());
				contactMap.put("phoneNumber", contact.getPhoneNumber());
				contactMap.put("isDeleted", contact.getIsDeleted());
				contastData.add(contactMap);
			}
		}
		return contastData;
	}
	/**
	 *Title: updateContactInformation  
	 * Description:   修改联系方式
	 * @param content    对象内容
	 * @param isEmergency  是否紧急联系人
	 * @param customerId   用户id
	 * @throws Exception   
	 * @see com.zdsoft.finance.afterloan.service.LoanAfterHandleService#updateContactInformation(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public void updateContactInformation(String content,String isEmergency,String customerId)throws Exception{
		//判断修改的联系人是否为紧急联系人
		if(!StringUtil.isNullOrEmpty(isEmergency))
		{
			List<EmergencyContact> contactList=new Gson().fromJson(content, new TypeToken<ArrayList<EmergencyContact>>() {}.getType());
			if(contactList!=null)
			{
				for(EmergencyContact contact : contactList)
				{
					contact.setCustomerId(customerId);
					if(StringUtil.isNullOrEmpty(contact.getId()))
					{
						emergencyContactRepository.save(contact);
					}
					else
					{
						EmergencyContact oldT = emergencyContactRepository.findOne(contact.getId());
						if (ObjectHelper.isEmpty(oldT)) {
							throw new BusinessException("10010002", "根据相应参数未找到相应的数据");
						}
						BeanUtils.copyProperties(contact, oldT);
						emergencyContactRepository.updateEntity(oldT);
					}
				}
			}
		}
		else
		{
			List<AfterContact> contactList=new Gson().fromJson(content, new TypeToken<ArrayList<AfterContact>>() {}.getType());
			if(contactList!=null)
			{
				for(AfterContact contact : contactList)
				{
					contact.setCustomerId(customerId);
					afterContactService.saveOrUpdateEntity(contact);
				}
			}
		}
	}
	
	/**
	 * @Title: addEmCustomerContacts 
	 * @Description: 添加紧急联系人
	 * @author huangwei 
	 * @param content     联系人对象
	 * @param contacts    紧急联系人对象
	 * @throws Exception
	 */
	@Transactional
	public void addEmCustomerContacts(String content,EmergencyContacts contacts)throws Exception{
		//保存紧急联系人
		contacts=emergencyContactsRepository.save(contacts);
		//保存联系方式
		List<EmergencyContact> contactList=new Gson().fromJson(content, new TypeToken<ArrayList<EmergencyContact>>() {}.getType());
		if(contactList!=null)
		{
			for(EmergencyContact contact:  contactList)
			{
				contact.setCustomerId(contacts.getId());
				emergencyContactRepository.save(contact);
			}
		}
	}
	/**
	 * @Title: initFollowInfoMessage 
	 * @Description: 初始化跟催信息
	 * @author huangwei 
	 * @param customerId  用户Id
	 * @param caseApplyId  案件id
	 * @return
	 * @throws Exception
	 */
	public ModelMap initFollowInfoMessage(String customerId,String caseApplyId)throws Exception{
		ModelMap modelMap=new ModelMap();
		modelMap.put("customerId", customerId);
		modelMap.put("caseApplyId", caseApplyId);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1=new SimpleDateFormat("yyyyMMdd");
		//跟催日期
		Date followDate=new Date();
		modelMap.put("followDate",format.format(followDate));
		//跟催人姓名
		modelMap.put("employeeName", CED.getLoginUser().getEmpNm());
		//跟催人code
		modelMap.put("employeeCode", CED.getLoginUser().getEmpCd());
		//跟催部门名称departmentName
		modelMap.put("departmentName", CED.getLoginUser().getDepartmentName());
		//跟催部门code
		modelMap.put("departmentCode", CED.getLoginUser().getDepartmentCd());
		//应还对象
		CustomerReceivable customerReceivable= customerReceivableService.findByCaseApplyIdAndIsEffect(caseApplyId, true);
		if(ObjectHelper.isNotEmpty(customerReceivable)){
			modelMap.put("days",customerReceivable.getDays());
			//预计还款日期
			Long loanDate=customerReceivable.getPlanRepayDate();
			//逾期天数
			//预计还款日期-跟催日期
			int deff=daysOfTwo(format1.parse(loanDate+""),followDate);
			if(deff>=4){
				Calendar ca = Calendar.getInstance();
				ca.setTime(followDate);
				ca.add(Calendar.DATE, 3);// num为增加的天数，可以改变的
				String enddate = format.format(ca.getTime());
				modelMap.put("pretNextFlUpDate", enddate);
			}else{
				Calendar ca = Calendar.getInstance();
				ca.setTime(followDate);
				ca.add(Calendar.DATE, 1);// num为增加的天数，可以改变的
				String enddate = format.format(ca.getTime());
				modelMap.put("pretNextFlUpDate", enddate);
			}
		}
		return modelMap;
	}
	/**
	  * @Title: daysOfTwo 
	  * @Description: 计算2个日期之间的天数
	  * @author huangwei 
	  * @param fDate   日期1
	  * @param oDate  日期2
	  * @return
	  */
	 private int daysOfTwo(Date fDate, Date oDate) {

		 if (null == fDate || null == oDate) {

	           return -1;

	       }

	       long intervalMilli = fDate.getTime()-oDate.getTime();

	       return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	    }
	/**
	 * Title: submitFollowInfo  
	 * Description: 提交跟催 
	 * @param followInfo   跟催对象
	 * @param afterSupervise   督办对象
	 * @param operate  操作类型
	 * @throws Exception   
	 * @see com.zdsoft.finance.afterloan.service.LoanAfterHandleService#submitFollowInfo(com.zdsoft.finance.afterloan.entity.FollowInfo, com.zdsoft.finance.afterloan.entity.AfterSupervise, java.lang.String)
	 */
	@Transactional
	public String submitFollowInfo(FollowInfo followInfo,AfterSupervise afterSupervise,String operate)throws Exception{
		//跟催客户姓名
		followInfo.setCustomerName(afterCustomerRepository.findOne(followInfo.getCustomerId()).getCustomerName());
		//创建人公司code
		followInfo.setCompanyCode(CED.getLoginUser().getCompanyCd());
		//保存跟催信息
		followInfo=followInfoService.saveOrUpdateEntity(followInfo);
		CaseApply caseApply = caseApplyService.findOne(followInfo.getCaseApplyId());
		followInfoService.saveEntity(followInfo);
		//保存督办信息
		if(followInfo.getSupervisd().equals("1"))
		{
			afterSupervise.setFollowInfoId(followInfo.getId());
			afterSuperviseService.saveOrUpdateEntity(afterSupervise);
		}
		//如果提交并要发起督办，则发起督办流程
		if(followInfo.getSupervisd().equals("1")&&operate.equals("submit")){
			afterSupervise.setFollowInfoId(followInfo.getId());
			BusiForm busiForm = afterSupervise.getBusiForm();
			if(ObjectHelper.isEmpty(busiForm)){
				busiForm = new BusiForm();
				//状态
				busiForm.setFormStatus(BusiFormStatus.APPROVAL.value);
				//还款计划：标题 案件号+产品      业务编号：案件号
		        busiForm.setApplyTitle("跟催督办-"+caseApply.getCaseApplyCode());
		        busiForm.setBusinessCode(caseApply.getCaseApplyCode());
				//关联业务表单id
				busiForm.setBusinessEntityId(afterSupervise.getId());
				//关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
				busiForm.setBusinessEntityName(AfterSupervise.class.getSimpleName());
				//关联组件数据ID 例如 项目表ID 合同ID
				busiForm.setComponentsEntityId(afterSupervise.getCaseApplyId());
				//关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
				busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
				//所属模块
				busiForm.setModelType(ApplyModelTypeEnum.AFTLOAN_SUPERVISE.value);
		        afterSupervise.setBusiForm(busiForm);
		        afterSuperviseService.saveOrUpdateEntity(afterSupervise);
		        busiForm.setFormStatus(BusiFormStatus.APPROVAL.value);
				 //功能代码
		        busiForm.setFunctionCode(AfterSupervise.PROCEESS_CODE);
	            //产品子类型id
		        busiForm.setProductId(caseApply.getProductSubtypeId());
	            //启动流程
		        busiForm = busiFormService.startProcess(busiForm, null, null);
			}
		}
		return followInfo.getId()+"+"+afterSupervise.getId();
	}
	/**
	 * @Title: getFollowInfoForm 
	 * @Description: 根据跟催id查找跟催详细信息
	 * @author huangwei 
	 * @param followInfoId   跟催对象
	 * @return
	 */
	public ModelMap getFollowInfoForm(String followInfoId) throws Exception{
		ModelMap modelMap=new ModelMap();
		modelMap.put("follow", getFollowInfoMessage(followInfoId));
		return modelMap;
	}
	
	/**
	 * @Title: getFollowInfoMessage 
	 * @Description: 获取跟催详信息
	 * @author huangwei 
	 * @param followInfoId  跟催id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object>  getFollowInfoMessage(String followInfoId) throws Exception{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		FollowInfo followInfo=followInfoRepository.findOne(followInfoId);
		Gson gson= new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {  
            @Override  
            public boolean shouldSkipField(FieldAttributes f) {  
                //过滤掉字段名包含"id","address"的字段  
                return f.getName().contains("afterSupervise")|f.getName().contains("busiForm");  
            }  
  
            @Override  
            public boolean shouldSkipClass(Class<?> clazz) {  
                //过滤掉 类名包含 Bean的类  
                return false;  
            }  
        }).create();  
		Map<String,Object> followInfoMap=gson.fromJson(gson.toJson(followInfo), Map.class);
		//跟催方式
		followInfoMap.put("followType", CED.loadSimpleCodeNameByFullCode(followInfoMap.get("followType").toString()));
		//跟催状态
		followInfoMap.put("followStatus", followInfoMap.get("followStatus").toString().equals("1") ? "有效":"无效");
		//处置预案
		if(followInfoMap.get("handlePlan")!=null)
		{
			followInfoMap.put("handlePlan", CED.loadSimpleCodeNameByFullCode(followInfoMap.get("handlePlan").toString()));
		}
		//子目录
		if(followInfoMap.get("childHandlePlan")!=null)
		{
			if(followInfoMap.get("handlePlan")!=null&&!followInfoMap.get("handlePlan").equals("其他"))
			{
				followInfoMap.put("childHandlePlan", CED.loadSimpleCodeNameByFullCode(followInfoMap.get("childHandlePlan").toString()));
			}
		}
		//预计还款日期
		followInfoMap.put("predictRepayDate",format.format(new Date(followInfo.getPredictRepayDate())));
		//预计下次跟进日期
		if(followInfoMap.get("pretNextFlUpDate")!=null)
		{
			followInfoMap.put("pretNextFlUpDate",format.format(new Date(followInfo.getPretNextFlUpDate())));
		}
		//跟催日期
		if(followInfoMap.get("followDate")!=null)
		{
			followInfoMap.put("followDate",format.format(new Date(followInfo.getFollowDate())));
		}
		//是否发起督办
		followInfoMap.put("supervisd", followInfoMap.get("supervisd").toString().equals("1") ? "是":"否");
		return followInfoMap;
	}
	

}
