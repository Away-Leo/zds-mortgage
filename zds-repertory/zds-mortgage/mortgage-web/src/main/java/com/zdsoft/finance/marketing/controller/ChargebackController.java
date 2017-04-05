package com.zdsoft.finance.marketing.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.Chargeback;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.ChargebackService;
import com.zdsoft.finance.marketing.vo.ChargebackVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.dto.ResultDto;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ChargebackController.java 
 * @ClassName: ChargebackController 
 * @Description: ChargebackController
 * @author caixiekang 
 * @date 2017年3月6日 下午2:26:29 
 * @version V1.0
 */
@Controller
@RequestMapping("/marketing/chargeback")
public class ChargebackController extends BaseController{

	@Autowired
	private ChargebackService chargebackService;
	@Autowired
	private CED CED;
	@Autowired
	private CaseApplyService caseApplyService;
	/**
	 * 
	 * @Title: initChargebackList 
	 * @Description: 退单管理列表初始化
	 * @author caixiekang 
	 * @param mv 视图
	 * @return
	 */
	@RequestMapping("/initChargebackList")
	@UriKey(key = "com.cnfh.rms.marketing.chargeback.initChargebackList")
	@Menu(resource = "com.zdsoft.finance.marketing.initChargebackList", label = "退单管理", group = "marketing", order = 12)
	@DataAuthResource(resource="com.zdsoft.finance.marketing.initChargebackList.dataAuth",label="退单管理",group="com.zdsoft.finance.marketing.initChargebackList")
	public ModelAndView initChargebackList(ModelAndView mv){
		mv.setViewName("marketing/chargeback/listChargeback");
		return mv;
	}
	
	/**
	 * 
	 * @Title: getChargebackList 
	 * @Description: 获取退单管理列表
	 * @author caixiekang 
	 * @param request request请求
	 * @param pageable pagerequest请求
	 * @return
	 */
	@RequestMapping("/getChargebackList")
    @UriKey(key = "com.cnfh.rms.marketing.chargeback.getChargebackList")
    @ResponseBody
    public ResponseMsg getChargebackList(HttpServletRequest request,PageRequest pageable){
        ResponseMsg msg = new ResponseMsg();
        try {
        	List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[]{"ca","cu"});
        	//数据权限
            DataOperPermDto dtOperPermDto=CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.marketing.initChargebackList.dataAuth");
            //添加机构权限控制
        	QueryObj obj = new QueryObj();
    		obj.setObj("ca");
    		obj.setElement("mechanismCode");
    		obj.setOperator("=");
    		obj.setValue(CED.getLoginUser().getCompanyCd());
    		queryObjs.add(obj);
            Page<Map<String, Object>> chargebackPages = chargebackService.findChargebackPages(pageable, queryObjs,dtOperPermDto);
            List<Map<String,Object>> chargebackLists = chargebackPages.getRecords();
            //拼接退单与反退单的显示条件
            if(ObjectHelper.isNotEmpty(chargebackLists)){
            	for (Map<String, Object> map : chargebackLists) {
					Object stage = map.get("stage");
					//当案件状态为已退单,设置反退单操作为true
					if(CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_FINISH.value.equals(stage.toString())){
						map.put("oppositedChargeback", true);
						//当案件状态为其他状态,设置为false,可退单操作
					}else{
						map.put("oppositedChargeback", false);
					}
					
				}
            	
            }
            msg.setMsg("退单管理列表查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(chargebackPages.getTotalRows());
            msg.setRows(chargebackPages.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setMsg("退单管理列表查询失败");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return msg;
    }
	
	/**
	 * 
	 * @Title: editChargeBack 
	 * @Description: 进入退单申请的页面
	 * @author caixiekang 
	 * @param mv 视图
	 * @param caseApplyId 案件申请id
	 * @param businessKey 表单类id
	 * @param busiFormId busiFormId
	 * @return
	 */
	@RequestMapping("/editChargeBack")
	@UriKey(key = "com.cnfh.rms.marketing.chargeback.editChargeBack")
	public ModelAndView editChargeBack(ModelAndView mv, String caseApplyId,String businessKey,String busiFormId){
		logger.info("caseApplyId:{} ",caseApplyId);
		logger.info("businessKey:{} ",businessKey);
		logger.info("busiFormId:{}",busiFormId);
		//设置视图
		mv.setViewName("marketing/chargeback/chargeback_application_edit");
		mv.addObject("caseApplyId", caseApplyId);
		try{
			Chargeback chargeBack = null;
			//若是从我的工作台进入,则回显信息
			if(ObjectHelper.isNotEmpty(businessKey)){
				chargeBack = chargebackService.findOne(businessKey);
				
			}else{
				//初始化退单申请,若数据库没有空白数据,则创建空白的数据,返回id到页面,若有则直接返id回去页面
				chargeBack = chargebackService.findByCaseApplyIdAndChargebackCauseIsNull(caseApplyId);
				if(ObjectHelper.isEmpty(chargeBack)){
					chargeBack = new Chargeback();
					chargeBack.setCaseApplyId(caseApplyId);
					chargeBack = chargebackService.saveEntity(chargeBack);
				}
			}
			ChargebackVo chargebackVo = new ChargebackVo(chargeBack);
			mv.addObject("chargebackVo", chargebackVo);
			
			//拼接附件参数
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			String productId = caseApply.getProductSubtypeId();
			String linkCode = CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value
					.substring(CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value.length()-2, CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value.length());
			mv.addObject("productId", productId);
			mv.addObject("linkCode", linkCode);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("进入退单申请页面失败",exception);
		}
		
		return mv;
	}
	
	/**
	 * 
	 * @Title: saveOrSubmitChargeback 
	 * @Description: 保存或更新退款单申请
	 * @author caixiekang 
	 * @param chargebackVo
	 * @return
	 */
	@RequestMapping("/saveOrSubmitChargeBack")
	@UriKey(key = "com.cnfh.rms.marketing.chargeback.saveOrSubmitChargeBack")
	@ResponseBody
	public ResponseMsg saveOrSubmitChargeback(ChargebackVo chargebackVo) {
		ResponseMsg msg = new ResponseMsg();
		try{
			Chargeback chargeback=chargebackVo.toPo();
			//保存或提交退款申请,按条件启动流程
			chargeback=chargebackService.saveOrSubmitChargeback(chargeback); 
			msg.setId(chargeback.getId());
			msg.setMsg(chargeback.getMsg());
			msg.setResultStatus(ResultDto.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}
	/**
	 * 
	 * @Title: viewChargeback 
	 * @Description: 进入退款申请的我的申请查看页面
	 * @author caixiekang 
	 * @param projectId 即caseApplyId 案件Id
	 * @param processInstanceId 流程实例id
	 * @param businessKey 退款申请Id
	 * @param busiFormId 
	 * @return
	 */
	@RequestMapping(value = "/viewChargeback")
	@UriKey(key = "com.cnfh.rms.marketing.chargeback.viewChargeback")
	public ModelAndView viewChargeback(String projectId, String processInstanceId, String businessKey,String busiFormId) {
		logger.info("projectId: ",projectId);
		logger.info("businessKey: ",businessKey);
		logger.info("busiFormId: ",busiFormId);
		
		ModelMap model = new ModelMap();
		model.put("projectId", projectId);
		model.put("caseApplyId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		CaseApply caseApply = null;
		try {
			//拼接附件参数
			caseApply = caseApplyService.findOne(projectId);
			String productId = caseApply.getProductSubtypeId();
			String linkCode = CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value
					.substring(CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value.length()-2, CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value.length());
			model.put("productId", productId);
			model.put("linkCode", linkCode);
			//获取退款申请Vo对象
			Chargeback chargeBack=chargebackService.findOne(businessKey);
			ChargebackVo chargeBackVo=new ChargebackVo(chargeBack);
			model.put("chargeBackVo", chargeBackVo);
		} catch (Exception e) {
			logger.error("进入退单申请查看页面失败",e);
			e.printStackTrace();
		}
		return new ModelAndView("/marketing/chargeback/chargeback_application_view", model);
	}
	
	/**
	 * 
	 * @Title: viewChargebackProcess 
	 * @Description: 退单审批页面
	 * @author caixiekang 
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/viewChargebackProcess")
	@UriKey(key = "com.cnfh.rms.marketing.chargeback.viewChargebackProcess")
	@ManualJob(resource = "com.cnfh.rms.marketing.chargeback.viewChargebackProcess", label = "退单审批")
	@FinishJob(resource = "com.cnfh.rms.marketing.chargeback.viewChargebackProcess", businessId = "businessKey", projectId = "projectId")
	public ModelAndView viewChargebackProcess(String projectId, String processInstanceId, String businessKey) {
		logger.info("projectId: ",projectId);
		logger.info("businessKey: ",businessKey);
		logger.info("processInstanceId: ",processInstanceId);
		
		ModelMap model = new ModelMap();
		model.put("projectId", projectId);
		model.put("caseApplyId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		CaseApply caseApply = null;
		try {
			caseApply = caseApplyService.findOne(projectId);
			//拼接附件参数
			String productId = caseApply.getProductSubtypeId();
			String linkCode = CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value
					.substring(CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value.length()-2, CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value.length());
			model.put("productId", productId);
			model.put("linkCode", linkCode);
			//获取退款申请Vo对象
			Chargeback chargeBack=chargebackService.findOne(businessKey);
			ChargebackVo chargeBackVo=new ChargebackVo(chargeBack);
			model.put("chargeBackVo", chargeBackVo);
		} catch (Exception e) {
			logger.error("进入退单审批页面失败",e);
			e.printStackTrace();
		}
		return new ModelAndView("/marketing/chargeback/chargeback_application_view_process", model);
	}
	/**
	 * 
	 * @Title: opChargeBack 
	 * @Description: 反退单处理
	 * @author caixiekang 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/opChargeBack")
	@UriKey(key = "com.cnfh.rms.marketing.chargeback.opChargeBack")
	@ResponseBody
	public ResponseMsg opChargeBack(String caseApplyId) {
		ResponseMsg msg = new ResponseMsg();
		try{
			//修改案件状态
			chargebackService.opChargeBack(caseApplyId);
			//保存或提交退款申请,按条件启动流程
			msg.setMsg("反退单成功,案件状态已重置为\"营销登记\"");
			msg.setResultStatus(ResultDto.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}
}
