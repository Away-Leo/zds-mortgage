package com.zdsoft.finance.casemanage.limitApply.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitApply.service.CaseLimitApplyService;
import com.zdsoft.finance.casemanage.limitApply.vo.CaseLimitApplyVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.AllotAmountException;
import com.zdsoft.finance.common.exception.AmountLackBusiness;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.exception.NotOpenLimitException;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.finance.product.service.CreditEntrustPlanConfigService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseLimitApplyController.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.controller
 * @Description:案件额度申请控制器
 * @author: xj
 * @date:2017年1月7日 上午10:02:10
 * @version:v1.0
 */
@Controller
@RequestMapping("/limitApply")
public class CaseLimitApplyController extends BaseController {

	@Autowired
	private CaseApplyService caseApplySerivce;
	@Autowired
	private CaseLimitApplyService caseLimitApplyService;
	@Autowired
	private CreditEntrustService creditEntrustService;
	@Autowired
	private CapitalistService capitalistService;
	@Autowired
	private CreditEntrustPlanConfigService  creditEntrustPlanConfigService ;
	@Autowired
	private HousePropertyService  housePropertyService ;

	@Autowired
	private CED CED;

	/**
	 * 
	 * @Title: limitApplyList 
	 * @Description: 案件额度申请列表入口
	 * @author xj 
	 * @return
	 */
	@RequestMapping("/initCaseLimitApply")
	@UriKey(key = "com.cnfh.rms.limitApplyList.casemanage.limitApplyList")
	@Menu(resource = "com.cnfh.rms.casemanage.limitApplyList", label = "额度申请", group = "project", order = 6)
	@DataAuthResource(resource="com.cnfh.rms.casemanage.limitApplyList.dataAuth",label="额度申请",group="com.cnfh.rms.casemanage.limitApplyList")
	public ModelAndView limitApplyList() {
		ModelMap map = new ModelMap();
		try {
			EmpDto loginUser = CED.getLoginUser();
			String empCd = loginUser.getEmpCd();
			map.put("empCd", empCd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("limitApplyList", e);
		}
		return new ModelAndView("/casemanage/limitApply/limit_apply_list",map);
	}

	/**
	 * 
	 * @Title: caseLimitApplyList 
	 * @Description: 案件额度申请查询列表
	 * @author xj 
	 * @param request
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/caseLimitApplyList")
	@UriKey(key = "com.cnfh.rms.casemanage.caseLimitApplyList")
	@ResponseBody
	public String caseLimitApplyList(HttpServletRequest request, PageRequest pageable, String jsoncallback){
	    ResponseMsg msg = new ResponseMsg();
        try {
            // 获取查询参数
            List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[]{"cus","ma"});
            //数据权限
            DataOperPermDto dtOperPermDto=CED.findDataPerms(StoreHelper.getApplication(), "com.cnfh.rms.casemanage.limitApplyList.dataAuth");
            // 获取案件申请配置信息
            Page<Map<String, Object>> caseLimitApply = caseApplySerivce.findPageCaseLimitApply(pageable, queryObjs,dtOperPermDto);
            List<Map<String, Object>> records = caseLimitApply.getRecords();
            if(ObjectHelper.isNotEmpty(records)){
                for (Map<String,Object> map : caseLimitApply.getRecords()) {
                        try {
                            //额度状态
                            String actualLimitStatus = (String) map.get("actualLimitStatus");
                            if(ObjectHelper.isNotEmpty(actualLimitStatus)){
                                map.put("actualLimitStatusName", CED.loadSimpleCodeNameByFullCode(actualLimitStatus));
                            }
                            //查询抵押情况
                            List<HouseProperty> housePropertys = housePropertyService.findByCaseApplyId((String)map.get("id"));
                            if(ObjectHelper.isNotEmpty(housePropertys)&&housePropertys.size()>0){
                                String mortgageSituation = housePropertys.get(0).getMortgageSituation();
                                if(ObjectHelper.isNotEmpty(mortgageSituation)){  
                                    map.put("pledgeType", CED.loadSimpleCodeNameByFullCode(mortgageSituation));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error("caseLimitApplyList", e); 
                        }
                    
                }
            }
            
            msg.setMsg("列表查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(caseLimitApply.getTotalRows());
            msg.setRows(caseLimitApply.getRecords());
        } catch (AppException e1) {
        	logger.error("caseLimitApplyList", e1); 
            e1.printStackTrace();
        }
	
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: quotaApplication 
	 * @Description: 跳转到额度申请页面
	 * @author xj 
	 * @param id
	 * @param customerName
	 * @param pledgeType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/quotaApplication")
	@UriKey(key = "com.cnfh.rms.casemanage.quotaApplication")
	@ResponseBody
	public ModelAndView quotaApplication(String caseApplyId) throws Exception {
		ModelMap applyLimitModel = new ModelMap();
		CaseLimitApplyVo caseLimitApplyVo = new CaseLimitApplyVo();
		try {
			CaseApply caseApply = caseApplySerivce.findOne(caseApplyId);
			//资金来源
			String capitalSource = caseApply.getCapitalSource();
			if(ObjectHelper.isNotEmpty(capitalSource)){
				Capitalist capitalist = capitalistService.findOne(capitalSource);
				if(ObjectHelper.isNotEmpty(capitalist)){
					String cooperatorName = capitalist.getCapitalName();
					applyLimitModel.put("cooperatorName", cooperatorName);
				}
			}
			//查询资金计划
			CreditEntrustPlanConfig creditEntrustPlanConfig = creditEntrustPlanConfigService.findByProductIdAndCapitalistId(caseApply.getProductSubtypeId(), capitalSource, caseApply.getLoanNumber()); 
			//资金id
			String capitalPlanId = creditEntrustPlanConfig.getCapitalPlanId();
			
			//查询资金
			CreditEntrust creditEntrust = creditEntrustService.findOne(capitalPlanId);
			//资金计划id
			caseLimitApplyVo.setFundPlanId(capitalPlanId);
			//资金计划名称 
			caseLimitApplyVo.setFundPlanName(creditEntrust.getCreditEntrustName());
			//贷款发放账户机构名称 
			caseLimitApplyVo.setLoanOutAccountBranchName(creditEntrust.getWaitApprBank());
			//贷款发放账户账户名
			caseLimitApplyVo.setLoanOutAccountName(creditEntrust.getWaitApprName());
			//贷款发放账户
			caseLimitApplyVo.setLoanOutAccount(creditEntrust.getWaitApprNo());
			//贷款回款账户机构名称
			caseLimitApplyVo.setLoanBackAccountBranchName(creditEntrust.getCollectionAccountBank());
			//贷款回款账户账户名 
			caseLimitApplyVo.setLoanBackAccountName(creditEntrust.getCollectionAccountName());
			//贷款回款账户
			caseLimitApplyVo.setLoanBackAccount(creditEntrust.getCollectionAccountNo());
			//是否开放申请额度
			applyLimitModel.put("isOpenApply", creditEntrust.getIsOpenApply());
			applyLimitModel.put("vo", caseLimitApplyVo);
			
			EmpDto loginUser = CED.getLoginUser();
			String empNm = loginUser.getEmpNm();
			//当前登录人
			applyLimitModel.put("empNm", empNm);
			//当前日期
			applyLimitModel.put("currentDate", DateHelper.dateToString(new Date()));
			//申请金额
			applyLimitModel.put("applyAmount", caseApply.getApplyAmount());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("quotaApplication", e);
		}
		return new ModelAndView("/casemanage/limitApply/limit_apply_edit", applyLimitModel);
	}

	/**
	 * 
	 * @Title: caseLimitApplyView 
	 * @Description: 额度申请查看或者取消
	 * @author xj 
	 * @param caseLimitApplyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/caseLimitApplyView")
	@UriKey(key = "com.cnfh.rms.casemanage.caseLimitApplyView")
	@ResponseBody
	public ModelAndView caseLimitApplyView(String caseLimitApplyId,String caseApplyId)
			throws Exception {
		ModelMap modelMap = new ModelMap();
		try {
			CaseApply caseApply = caseApplySerivce.findOne(caseApplyId);
			//资金来源
			String capitalSource = caseApply.getCapitalSource();
			if(ObjectHelper.isNotEmpty(capitalSource)){
				Capitalist capitalist = capitalistService.findOne(capitalSource);
				if(ObjectHelper.isNotEmpty(capitalist)){
					String cooperatorName = capitalist.getCapitalName();
					modelMap.put("cooperatorName", cooperatorName);
				}
			}
			
			CaseLimitApply caseLimitApply = caseLimitApplyService.findOne(caseLimitApplyId);
			CaseLimitApplyVo vo = new CaseLimitApplyVo(caseLimitApply);
			modelMap.put("vo", vo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("caseLimitApplyView", e);
		}
		return new ModelAndView("/casemanage/limitApply/limit_apply_view", modelMap);
	}

	/**
	 * 
	 * @Title: saveCaseLimitApply 
	 * @Description: 额度申请
	 * @author xj 
	 * @param caseLimitPlanInfoVo
	 * @return
	 */
	@RequestMapping("/saveCaseLimitApply")
	@UriKey(key = "com.cnfh.rms.casemanage.saveCaseLimitApply")
	@ResponseBody
	public ResponseMsg saveCaseLimitApply(CaseLimitApplyVo caseLimitApplyVo) {
		ResponseMsg msg = new ResponseMsg();
		CaseLimitApply caseLimitApply = new CaseLimitApply();
		try {
			BeanUtils.copyProperties(caseLimitApplyVo, caseLimitApply);
			//业务需求：申请和额度分配不进行事物控制，申请成功后额度分配可以不成功
			//申请额度
			caseLimitApply = caseLimitApplyService.saveCaseLimitApply(caseLimitApply);
			//额度分配
			caseLimitApplyService.allotCaseLimitApply(caseLimitApply);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("额度申请成功");
		} catch(AmountLackBusiness e){
			logger.info("申请失败,资金余额不足", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("申请失败，资金余额不足!");
			e.printStackTrace();
		} catch(NotOpenLimitException e){
			logger.info("申请失败，未开放申请额度!",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("申请失败，未开放申请额度!");
			e.printStackTrace();
		} catch(BusinessException e){
			e.printStackTrace();
			msg.setMsg("系统内部错误，请查看日志!");
			if(ObjectHelper.isNotEmpty(e.getExceptionMessage())){
				msg.setMsg(e.getExceptionMessage());
			}
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error("caseLimitApply保存失败", e);
		} catch(AllotAmountException e){
			e.printStackTrace();
			logger.info("申请额度成功,分配额度失败", e);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("申请额度成功,分配额度失败!");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("caseLimitApply保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志!");
		}
		return msg;
	}

	/**
	 * 
	 * @Title: cancleCaseLimitApply 
	 * @Description: 取消额度申请
	 * @author xj 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/cancelCaseLimitApply")
	@UriKey(key = "com.cnfh.rms.casemanage.cancelCaseLimitApply")
	@ResponseBody
	public ResponseMsg cancleCaseLimitApply(String caseLimitApplyId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("取消额度成功");
			caseLimitApplyService.cancelCaseLimitApply(caseLimitApplyId);
		} catch(BusinessException e){
			e.printStackTrace();
			msg.setMsg("系统内部错误，请查看日志!");
			if(ObjectHelper.isNotEmpty(e.getExceptionMessage())){
				msg.setMsg(e.getExceptionMessage());
			}
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error("取消额度申请失败", e);
		} catch (Exception e) {
			logger.error("取消额度申请失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: hostoryCaseLimitApplyList 
	 * @Description: 通过案件id查询历史额度申请列表
	 * @author xj 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/hostoryCaseLimitApplyList")
	@UriKey(key = "com.cnfh.rms.casemanage.hostoryCaseLimitApplyList")
	@ResponseBody
	public ResponseMsg hostoryCaseLimitApplyList(String caseApplyId){
		ResponseMsg msg = new ResponseMsg();
		try {
			msg.setResultStatus(ResponseMsg.SUCCESS);
			List<CaseLimitApplyVo> caseLimitApplyVos = new ArrayList<CaseLimitApplyVo>();
			List<CaseLimitApply> caseLimitApplys = caseLimitApplyService.findByCaseApplyId(caseApplyId);
			if(ObjectHelper.isNotEmpty(caseLimitApplys)){
				for (CaseLimitApply caseLimitApply : caseLimitApplys) {
					CaseLimitApplyVo vo = new CaseLimitApplyVo();
					BeanUtils.copyProperties(caseLimitApply, vo);
					caseLimitApplyVos.add(vo);
				}
			}
			
			msg.setRows(caseLimitApplyVos);
			msg.setTotal(Long.valueOf(caseLimitApplyVos.size()));
		} catch (Exception e) {
			logger.error("取消额度申请失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
		
	}
}
