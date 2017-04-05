package com.zdsoft.finance.marketing.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.dto.TaskInstanceQueryDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.repository.BusiFormRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.repository.CaseApplyRepository;
import com.zdsoft.finance.marketing.repository.HousePropertyRepository;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyServiceImpl.java 
 * @ClassName: CaseApplyServiceImpl 
 * @Description: 案件服务类
 * @author zhoushichao 
 * @date 2017年3月14日 下午8:01:40 
 * @version V1.0
 */
@Service("caseApplySerivce")
public class CaseApplyServiceImpl extends BaseServiceImpl<CaseApply, CaseApplyRepository>	implements CaseApplyService {

	@Autowired
	private CaseApplyRepository caseApplyRepository;
	
	@Autowired
	private HousePropertyRepository housePropertyRepository;
	
	@Autowired
	private CED CED;
	
	@Autowired
	private BPM BPM;
	
	@Autowired
	private BusiFormRepository busiFormRepository;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService ;
	
	@Override
	@Transactional
	public CaseApply saveOrUpdateCaseApply(CaseApply caseApply) throws Exception {
		if(ObjectHelper.isEmpty(caseApply.getCaseApplyCode())){
			try {
				 // 生成案件案号
				String caseApplyCode = CED.resolveExpression("YW000000000003", null);
				logger.debug("新生成的案件号为："+caseApplyCode);
				 caseApply.setCaseApplyCode(caseApplyCode);
			} catch (Exception e) {
				logger.error("平台异常，未解析表达式");
				e.printStackTrace();
			}
		}
		//初始案件状态设置为正常
 		if(ObjectHelper.isEmpty(caseApply.getCaseApplyStatus())){
 			caseApply.setCaseApplyStatus(CaseApply.NORMAL);
		}
		//初始保存案件审批状态设置为一审状态
 		if(ObjectHelper.isEmpty(caseApply.getExaminationStatus())){
 			caseApply.setExaminationStatus(CaseApply.FIRST_INSTANCE);
 		}
		//设置申请时间
		if(ObjectHelper.isEmpty(caseApply.getApplyDate())){
			caseApply.setApplyDate(new Long(TimeUtil.getCurrentDateInteger()));
		}
		//设置案件状态
		if(ObjectHelper.isEmpty(caseApply.getStage())){
			caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.TERMINALCASE.value);
		}
		return this.saveOrUpdateEntity(caseApply);
	}
	
	@Override
	public Page<Map<String, Object>> findPageBeforehandApply(Pageable pageable, List<QueryObj> queryObjs)
			throws Exception {
		EmpDto dto =  CED.getLoginUser();
		String userCode = dto.getEmpCd();
		String loginOrgCode = null;
		// TODO 判断是否是领导 START
		boolean isLeader = CED.checkSelfOrgManagerByEmpCd(userCode);
		if (isLeader) {
			loginOrgCode = dto.getOrgCd();
		}
		// TODO 判断是否是领导 END
		Page<Map<String, Object>> page = caseApplyRepository.findPageBeforehandApply(pageable, queryObjs,userCode,loginOrgCode);
		for(Map<String, Object> map : page.getRecords()){
			String houseProvince = String.valueOf(map.get("HOUSEPROVINCE"));
			String houseCity = String.valueOf(map.get("HOUSECITY"));
			String houseDistrict = String.valueOf(map.get("HOUSEDISTRICT"));
			String mailingAddress = String.valueOf(map.get("MAILINGADDRESS"));
			if(ObjectHelper.isNotEmpty(houseProvince)||ObjectHelper.isNotEmpty(houseCity)||ObjectHelper.isNotEmpty(houseDistrict)){
				mailingAddress = CED.loadSimpleCodeNameByFullCode(houseProvince)+"/"+CED.loadSimpleCodeNameByFullCode(houseCity)+"/"+CED.loadSimpleCodeNameByFullCode(houseDistrict)+"/"+mailingAddress;		
			map.put("MAILINGADDRESS", mailingAddress);
			}
		}
		
		return page;
	}

	@Override
	public Page<Map<String, Object>> findPageCaseApply(Pageable pageable, List<QueryObj> queryObjs,DataOperPermDto dtOperPermDto) throws Exception {
		StringBuffer dataAuth = this.developmentManagerDataAuth(dtOperPermDto, "c");
		Page<Map<String, Object>> page = caseApplyRepository.findPageCaseApply(pageable, queryObjs,dataAuth);
		for(Map<String, Object> map : page.getRecords()){
			
			String caseApplyId = String.valueOf(map.get("ID"));
			List<HouseProperty> housePropertyList = housePropertyRepository.findByCaseApplyIdOrderByCreateTime(caseApplyId);
			
			String stage = "";
			String evaluatingPrice = "";
			String mailingAddress = "";
			if(ObjectHelper.isNotEmpty(housePropertyList)){
				HouseProperty houseProperty = housePropertyList.get(0);
				if (ObjectHelper.isNotEmpty(houseProperty.getEvaluatingPrice())) {
					evaluatingPrice=houseProperty.getEvaluatingPrice().toString();
				}
				mailingAddress = CED.loadSimpleCodeNameByFullCode(houseProperty.getProvince())+"/"+CED.loadSimpleCodeNameByFullCode(houseProperty.getCity())+"/"+CED.loadSimpleCodeNameByFullCode(houseProperty.getDistrict())+"/"+houseProperty.getMailingAddress();		
			}
			if(ObjectHelper.isNotEmpty(map.get("STAGE"))){
				stage=CED.loadSimpleCodeNameByFullCode(String.valueOf(map.get("STAGE")));
			}
			map.put("STAGE",stage);
			map.put("EVALUATINGPRICE", evaluatingPrice);
			map.put("MAILINGADDRESS", mailingAddress);
			
			String id = String.valueOf(map.get("ID"));
			List<BusiForm> busiForms = busiFormRepository.findByComponentsEntityIdOrderByCreateTimeDesc(id);
			if(ObjectHelper.isNotEmpty(busiForms) && busiForms.size()>0){
				Set<String> processInstanceKey = new HashSet<String>();
				processInstanceKey.add(busiForms.get(0).getProcessInstanceKey());
				try {
					Map<String, List<TaskInstanceQueryDto>> findProInstanceByIds = BPM.findProInstanceByIds(processInstanceKey);
					if (ObjectHelper.isNotEmpty(findProInstanceByIds.get(busiForms.get(0).getProcessInstanceKey()))) {
					    List<TaskInstanceQueryDto> taskInstanceQueryDtos = findProInstanceByIds.get(busiForms.get(0).getProcessInstanceKey());
					    String currentDealEmpName = null;
					    String currentTaskName = null;
						for (TaskInstanceQueryDto taskDto : taskInstanceQueryDtos) {
							// 当前处理人
							if (currentDealEmpName == null) {
								currentDealEmpName = taskDto.getAssigneeNm();
							} else {
								currentDealEmpName += ";" +  taskDto.getAssigneeNm(); 
							}
							// 当前任务名
							if (currentTaskName == null) {
								currentTaskName = taskDto.getTaskName();
							} 
						}
						map.put("CURRENTDEALEMPNAME", currentDealEmpName);
						map.put("CURRENTTASKNAME", currentTaskName);
					} 
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("获取当前处理节点：caseapplyid:"+id+",processInstanceKey:"+processInstanceKey, e);
				}
			}
		}
		return page;
	}

	/**
	 * 	案件额度申请分页查询信息
	 *  @author xj
	 * @throws BusinessException 
	 */
	@Override
	public Page<Map<String, Object>> findPageCaseLimitApply(Pageable pageable, List<QueryObj> queryObjs, DataOperPermDto dtOperPermDto) throws BusinessException {
	    StringBuffer dataAuth = this.developmentManagerDataAuth(dtOperPermDto, "ma");
		Page<Map<String,Object>> page = caseApplyRepository.findPageCaseLimitApply(pageable,queryObjs,dataAuth);
		return page;
	}

	/**
	 * 案件预约分页查询信息
	 * @throws Exception 
	 */
	@Override
	public Page<Map<String, Object>> findPageAppointment(PageRequest pageable, List<QueryObj> queryObjs, DataOperPermDto dtOperPermDto) throws Exception {
		StringBuffer dataAuth = this.developmentManagerDataAuth(dtOperPermDto, "c");
	    Page<Map<String, Object>> page = caseApplyRepository.findPageAppointment(pageable,queryObjs,dataAuth);
		for(Map<String, Object> map : page.getRecords()){
			if(ObjectHelper.isNotEmpty(map.get("APPOINTMENTTYPE"))){
				String code = CED.loadSimpleCodeNameByFullCode(map.get("APPOINTMENTTYPE").toString());
				map.put("APPOINTMENTTYPE", code);
			}
			if(ObjectHelper.isEmpty(map.get("PHONENUMBER"))){
			    map.put("PHONENUMBER", "");
			}
		}
		return page;
	}

	/**
	 * 获取案件预约客户(即案件的主借人,所有担保人)
	 */
	@Override
	public List<Map<String, Object>> appointmentClient(String id) {
		List<Map<String, Object>> list = caseApplyRepository.appointmentClient(id);
		if(ObjectHelper.isNotEmpty(list)){
			for(Map<String, Object> map : list){
				if(ObjectHelper.isNotEmpty(map.get("JOINTYPE"))){
					try {
						String code = CED.loadSimpleCodeNameByFullCode(map.get("JOINTYPE").toString());
						map.put("JOINTYPE", code);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
                    if (ObjectHelper.isNotEmpty(map.get("SPOUSECUSTOMERID"))) {
                        try {
                            CaseApplyBeforeCustomer beforeCustomer = caseApplyBeforeCustomerService.findByBeforeCustomerId(map.get("SPOUSECUSTOMERID").toString());
                            map.put("JOINTYPE", CED.loadSimpleCodeNameByFullCode(beforeCustomer.getJoinType())+"配偶");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
				
				if(ObjectHelper.isNotEmpty(map.get("CONTACTTYPE"))){
					try {
						String code = CED.loadSimpleCodeNameByFullCode(map.get("CONTACTTYPE").toString());
						map.put("CONTACTTYPE", code);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
				    map.put("CONTACTTYPE", "");
				}
				if(ObjectHelper.isEmpty(map.get("PHONENUMBER"))){
				    map.put("PHONENUMBER", "");
				}
			}
		}
		return list;
	}

	/**
	 * 案件面签分页查询信息
	 * @throws Exception 
	 */
	@Override
	public Page<Map<String, Object>> findPageInterview(PageRequest pageable, List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto ) throws Exception {
		StringBuffer dataAuth = this.developmentManagerDataAuth(dataOperPermDto, "c");
	    Page<Map<String, Object>> page = caseApplyRepository.findPageInterview(pageable,queryObjs,dataAuth);
		for(Map<String, Object> map : page.getRecords()){
			if(ObjectHelper.isNotEmpty(map.get("INTERVIEWSTATUS"))){
				String code = CED.loadSimpleCodeNameByFullCode(map.get("INTERVIEWSTATUS").toString());
				map.put("INTERVIEWSTATUS", code);
			}
			if(ObjectHelper.isEmpty(map.get("PRODUCTTYPENAME"))){
				map.put("PRODUCTTYPENAME", "");
			}
			if(ObjectHelper.isEmpty(map.get("PRODUCTSUBTYPENAME"))){
				map.put("PRODUCTSUBTYPENAME", "");
			}
			if(ObjectHelper.isEmpty(map.get("PHONENUMBER"))){
                map.put("PHONENUMBER", "");
            }
		}
		return page;
	}

	/**
	 * 查询押品相关信息
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, Object>> queryHouseInfo(String id) throws Exception {
	    List<Map<String,Object>> list = caseApplyRepository.queryHouseInfo(id);
        for (Map<String, Object> map : list) {
            Object province = map.get("PROVINCE");
            Object city = map.get("CITY");
            Object district = map.get("DISTRICT");
            if(ObjectHelper.isNotEmpty(province)&&ObjectHelper.isNotEmpty(city)&&ObjectHelper.isNotEmpty(district)){
                List<String> fullCode = new ArrayList<String>();
                fullCode.add(province.toString());
                fullCode.add(city.toString());
                fullCode.add(district.toString());
                Map<String, String> fullCodes = CED.loadSimpleCodeNameByFullCodes(fullCode);
                map.put("MAILINGADDRESS", fullCodes.get(province)+"/"+fullCodes.get(city)+"/"+fullCodes.get(district)+"/"+map.get("MAILINGADDRESS"));
            }
             
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (ObjectHelper.isEmpty(entry.getValue())) {
                    entry.setValue("");   
                }
             }
        }
        return list;
	}

	/**
	 * 
	 * @Title: findPageTerminalCase 
	 * @Description: 终端进件分页查询
	 * @author xiongpan
	 */
	@Override
	public Page<Map<String, Object>> findPageTerminalCase(PageRequest pageable, List<QueryObj> queryObjs) throws Exception{
		Page<Map<String, Object>> page = caseApplyRepository.findPageTerminalCase(pageable,queryObjs);
		for(Map<String, Object> map : page.getRecords()){
			String province = String.valueOf(map.get("PROVINCE"));
			String city = String.valueOf(map.get("CITY"));
			String district = String.valueOf(map.get("DISTRICT"));
			String mailingAddress = String.valueOf(map.get("MAILINGADDRESS"));
			String terminalType = String.valueOf(map.get("TERMINALTYPE"));
			String stage = String.valueOf(map.get("STAGE"));
			
			if(ObjectHelper.isNotEmpty(province) && ObjectHelper.isNotEmpty(city) && ObjectHelper.isNotEmpty(district)){
				String housePropertyAddress = CED.loadSimpleCodeNameByFullCode(province)+"/"+CED.loadSimpleCodeNameByFullCode(city)+"/"+CED.loadSimpleCodeNameByFullCode(district)+"/"+mailingAddress;
				map.put("HOUSEPROPERTYADDRESS", housePropertyAddress);
			}
			
			if(ObjectHelper.isNotEmpty(terminalType)){
				String terminalTypeNm = CED.loadSimpleCodeNameByFullCode(terminalType);
				map.put("TERMINALTYPENM", terminalTypeNm);
			}
			
			if(ObjectHelper.isNotEmpty(stage)){
				String stageNm = CED.loadSimpleCodeNameByFullCode(stage);
				map.put("STAGENM", stageNm);
			}
			
		}
	
		return page;
	}
	
	@Override
	public Page<Map<String, Object>> findPageCaseSpecialApprove(Pageable pageable, List<QueryObj> queryObjs) throws Exception {
		QueryObj obj = new QueryObj();
		obj.setObj("ca");
		obj.setElement("developmentDepartmentCode");
		obj.setOperator("=");
		obj.setValue(CED.getLoginUser().getDepartmentCd());
		queryObjs.add(obj);
		Page<Map<String, Object>> page = caseApplyRepository.findPageCaseSpecialApprove(pageable, queryObjs);
		logger.info("caseapplyservice .....  特批列表查询成功");
		for (Map<String, Object> map : page.getRecords()) {
			String applyTermUnit = map.get("APPLYTERMUNIT")==null?"":map.get("APPLYTERMUNIT").toString();
			if (applyTermUnit.equals("0931001")) {//年
				map.put("APPLYTERM", Integer.parseInt(map.get("APPLYTERM").toString()) * 12);
			}else if(applyTermUnit.equals("0931003")){//天
				map.put("APPLYTERM", Integer.parseInt(map.get("APPLYTERM").toString()) / 30);
			}
		}
		return page;
	}

	@Override
	public Page<Map<String, Object>> findPageCaseApplyBasicInforList(PageRequest pageable, List<QueryObj> queryObjs,DataOperPermDto dtOperPermDto)	throws Exception {
	    StringBuffer dataAuth = this.developmentManagerDataAuth(dtOperPermDto, "ma");
		Page<Map<String, Object>> page = caseApplyRepository.findPageCaseApplyList(pageable, queryObjs,dataAuth);
		//数据封装
		if(ObjectHelper.isNotEmpty(page) && ObjectHelper.isNotEmpty(page.getRecords())){
			for (Map<String, Object> map : page.getRecords()) {
				//案件状态
				Object stage = map.get("stage");
				if(ObjectHelper.isNotEmpty(stage)){
					String stageName = CED.loadSimpleCodeNameByFullCode(stage.toString());
					map.put("stageName", stageName);
				}
				String id = (String) map.get("id");
				List<BusiForm> busiForms = busiFormRepository.findByComponentsEntityIdOrderByCreateTimeDesc(id);
				if(ObjectHelper.isNotEmpty(busiForms) && busiForms.size()>0){
					//当前节点
					map.put("currentNode", "");
					//当前处理人
					map.put("currentHandler","");
					Set<String> processInstanceKey = new HashSet<String>();
					processInstanceKey.add(busiForms.get(0).getProcessInstanceKey());
					try {
						Map<String, List<TaskInstanceQueryDto>> findProInstanceByIds = BPM.findProInstanceByIds(processInstanceKey);
						if (ObjectHelper.isNotEmpty(findProInstanceByIds.get(busiForms.get(0).getProcessInstanceKey()))) {
						    List<TaskInstanceQueryDto> taskInstanceQueryDtos = findProInstanceByIds.get(busiForms.get(0).getProcessInstanceKey());
							for (TaskInstanceQueryDto dto : taskInstanceQueryDtos) {
								String assigneeNm = dto.getAssigneeNm();
								String subject = dto.getSubject();
								map.put("currentHandler", assigneeNm);
								map.put("currentNode", map.get("currentNode") + " " + subject);
							}
						} 
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("获取当前处理人：caseapplyid:"+id+",processInstanceKey:"+processInstanceKey, e);
					}
				}
			}
		}
		return page;
	}

	@Override
	public Page<Map<String, Object>> findRelationCaseInfos(Pageable pageable, String customerId,
			List<String> exceptCaseIds) throws Exception {
		if (ObjectHelper.isNotEmpty(customerId)) {
			EmpDto loginUser = CED.getLoginUser();
			String loginOrgCode = null;
			// 判断是否是领导 START
			boolean isLeader = CED.checkSelfOrgManagerByEmpCd(loginUser.getEmpCd());
			if (isLeader) {
				loginOrgCode = loginUser.getOrgCd();
			}
			// 判断是否是领导 END
			String loginEmpCode = loginUser.getEmpCd();
			return this.customReposity.findRelationCaseInfos(pageable, customerId, exceptCaseIds,loginOrgCode,loginEmpCode);
		}
		return null;
	}

	@Override
	public CaseApply findByCaseApplyCode(String caseApplyCode) throws BusinessException {
		if(ObjectHelper.isNotEmpty(caseApplyCode)){
			CaseApply caseApply=this.caseApplyRepository.findByCaseApplyCode(caseApplyCode);
			if(ObjectHelper.isNotEmpty(caseApply)){
				return caseApply;
			}else{
				throw new BusinessException("10010002","根据参数未找到相应数据，根据案件号未找到案件");
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数，通过案件号查找案件出错");
		}
	}

    @Override
    public Map<String, String> findCaseApplyByCondition(String caseApplyId) throws Exception {
        if(ObjectHelper.isEmpty(caseApplyId)){
            throw new BusinessException("10010004", "案件id不能为空!");
        }
        List<Map<String,Object>> condition = this.caseApplyRepository.findCaseApplyByCondition(caseApplyId);
        Map<String,String> map = new HashMap<String,String>() ;
        if(ObjectHelper.isNotEmpty(condition)){
            Map<String, Object> map2 = condition.get(0);
            String province = String.valueOf(map2.get("PROVINCE"));
            String city = String.valueOf(map2.get("CITY"));
            String district = String.valueOf(map2.get("DISTRICT"));
            String applyTermUnit = String.valueOf(map2.get("APPLYTERMUNIT"));
            String capitalUseFor = String.valueOf(map2.get("CAPITALUSEFOR"));
            //simplecode数据转换
            List<String> arg0 = new ArrayList<String>();
            arg0.add(province);
            arg0.add(city);
            arg0.add(district);
            arg0.add(applyTermUnit);
            arg0.add(capitalUseFor);
            Map<String, String> fullCodes = CED.loadSimpleCodeNameByFullCodes(arg0);
            //抵押物地址
            map.put("mailingAddress", fullCodes.get(province)+fullCodes.get(city)+fullCodes.get(district)+ String.valueOf(map2.get("MAILINGADDRESS")));
            //期限
            map.put("applyTerm", map2.get("APPLYTERM")+"/"+fullCodes.get(applyTermUnit));
            //贷款用途
            map.put("capitalUseFor", fullCodes.get(capitalUseFor));
            //案件id
            map.put("caseApplyId", String.valueOf(map2.get("CASEAPPLYID")));
            //案件code
            map.put("caseApplyCode", String.valueOf(map2.get("CASEAPPLYCODE")));
            //产品
            map.put("productSubtypeName", String.valueOf(map2.get("PRODUCTSUBTYPENAME")));
            //申请金额
            map.put("applyAmount", String.valueOf(map2.get("APPLYAMOUNT")));
            //终端id
            map.put("terminalId",String.valueOf( map2.get("TERMINALID")));
            //终端名称
            map.put("terminalFullName", String.valueOf(map2.get("TERMINALFULLNAME")));
            // 主借人
            map.put("customerName", String.valueOf(map2.get("CUSTOMERNAME")));
            if(ObjectHelper.isNotEmpty(map2.get("CAPITALSOURCE"))){
                //资金来源id
                map.put("capitalSource", String.valueOf(map2.get("CAPITALSOURCE")));
                //资金来源名称
                map.put("cooperatorName", String.valueOf(map2.get("CAPITALNAME")));
            }else{
                map.put("capitalSource", "");
                map.put("cooperatorName", "");
            }
           
        }
        return map;
    }

	@Override
	public List<CaseApply> findByStage(String stage) {
		return this.customReposity.findByStage(stage); 
	}

	@Override
	public Page<Map<String, Object>> findPageCaseApplyAndCommission(Pageable pageable, List<QueryObj> queryObjs)
			throws Exception {
		return caseApplyRepository.findPageCaseApplyAndComission(pageable, queryObjs);
	}
	@Override
	public Page<Map<String, Object>> findPageCaseApplyAndDetail(Pageable pageable, List<QueryObj> queryObjs)
			throws Exception {
		return caseApplyRepository.findPageCaseApplyAndDetail(pageable, queryObjs);
	}
	
	@Override
	@Transactional
	public void discardApply(String caseApplyId){
		try{
		CaseApply caseApply = 	customReposity.findOne(caseApplyId);
		BusiForm form = caseApply.getBusiForm();
		form.setFormStatus(BusiFormStatus.SCRAPPED.value);
		caseApply.setStage("YWDM009235");
		caseApply.setBusiForm(form);
		customReposity.updateEntity(caseApply);
		}catch(Exception e){
			logger.error(e.getStackTrace()+"");
		}
	}

	@Override
	@Transactional
	public CaseApply changeCaseStage(String caseApplyId, String stage) throws Exception {
		CaseApply caseapply = this.findOne(caseApplyId);
		caseapply.setStage(stage);
		caseapply = this.updateEntity(caseapply);
		return caseapply;
	}

	@Override
	public Page<Map<String, Object>> findPageMonitorRecord(PageRequest pageable, List<QueryObj> queryObjs,
			String controlType) throws Exception {
		return this.customReposity.findPageMonitorRecord(pageable, queryObjs, controlType);
	}

	@Override
	public Map<String, Object> findMonitorRecordByCaseApplyId(String caseApplyId, String controlType) {
		return this.customReposity.findMonitorRecordByCaseApplyId(caseApplyId, controlType);
	}

	@Override
	public Page<Map<String, Object>> getCaseApplyCustomerReceivableList(PageRequest pageable,	List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto) throws BusinessException {
		StringBuffer data_auth=this.developmentManagerDataAuth(dataOperPermDto, "mca");
		StringBuffer _sql=new StringBuffer();
		_sql.append(" select  ");
		_sql.append("  mca.id,mca.capitalSource,mca.caseApplyCode,mca.productSubtypeName,mca.productTypeName, mca.applyAmount,mca.applyDate,mca.developmentManagerName, mca.developmentDepartmentName, ");
		_sql.append("  mca.loanApplyAnount,mca.caseApplyBalance,mca.applyTerm,mca.applyTermUnit, ");
		_sql.append("  pp.capitalistName,   ");//资金来源
		_sql.append("  cus.customerName,   ");//主借人
		_sql.append("  fcr.days, fcr.ovreDueTime,  ");
		_sql.append("  loan.actualDate,  ");
		_sql.append("  cla.fundPlanName,cla.id fundPlanId  ");//信托计划
		_sql.append("  from mkt_case_apply mca   ");
		_sql.append("  LEFT JOIN prd_product pp  on pp.id=mca.productSubtypeId   ");
		_sql.append("  LEFT JOIN fin_customer_receivable fcr  on fcr.caseApplyId=mca.ID and fcr.isEffect='T'   ");
		_sql.append("  LEFT JOIN case_before_customer cbc on cbc.caseApplyId=mca.id  and cbc.joinType='"+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		_sql.append("  LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		_sql.append("  LEFT JOIN case_limit_apply cla on cla.caseApplyId=mca.id ");
		_sql.append("  LEFT JOIN ( select la.caseApplyId,min(lr.actualDate) actualDate  from loan_apply la left join loan_record lr on la.id=lr.loanApplyId where la.status='3' group by la.caseApplyId)loan on loan.caseApplyId=mca.id  ");
		_sql.append("  where 1=1 ");
		
		_sql.append(data_auth);
		StringBuffer _extendSql=new StringBuffer();
		_extendSql.append(" order by mca.applyDate desc ");
		return this.customReposity.getListObjectBySql(pageable, queryObjs, _sql, _extendSql);
	}
}
