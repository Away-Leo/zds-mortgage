package com.zdsoft.finance.casemanage.evaluated.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.casemanage.evaluated.entity.EvaluatedAppeal;
import com.zdsoft.finance.casemanage.evaluated.service.EvaluatedAppealService;
import com.zdsoft.finance.casemanage.evaluated.vo.EvaluatedAppealVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: GroupHandleAppealController.java 
 * @ClassName: GroupHandleAppealController 
 * @Description: 集团查看评估价申诉Controller
 * @author caixiekang 
 * @date 2017年2月21日 上午9:33:18 
 * @version V1.0
 */
@Controller
@RequestMapping("/casemanage/evaluated/group")
public class GroupHandleAppealController extends BaseController{

	@Autowired 
	private EvaluatedAppealService appealService;
	@Autowired
	private CED ced;
	@Autowired
	private HousePropertyService housePropertyService;
	
	/**
	 * 
	 * @Title: initGroupHandleList 
	 * @Description: 集团处理评估价列表初始化
	 * @author caixiekang 
	 * @return
	 */
	@RequestMapping("/initGroupHandleList")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.group.initGroupHandleList")
    @Menu(resource = "com.zdsoft.finance.casemanage.evaluated.group.initGroupHandleList", label = "集团查看申诉", group = "project", order = 17)
	public ModelAndView initGroupHandleList(){
		
		return new ModelAndView("/casemanage/evaluated/group_handle_appeal_list");
	}
	
	/**
	 * 
	 * @Title: listGroupHandleList 
	 * @Description: 获取集团要处理的申诉列表
	 * @author caixiekang 
	 * @param request
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("/listGroupHandleList")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.group.listGroupHandleList")
	@ResponseBody
	public ResponseMsg listGroupHandleList(HttpServletRequest request, PageRequest pageRequest){
		ResponseMsg msg = new ResponseMsg();
		try{
			List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[]{"ca", "cu", "prc", "prp"});
			Page<Map<String, Object>> handlePage = appealService.queryGroupHandleList(queryObjs,pageRequest);
			//拼接地址
			List<Map<String, Object>> handleList = handlePage.getRecords();
			if(ObjectHelper.isNotEmpty(handleList)){
				for (Map<String, Object> map : handleList) {
					String housePropertyId = (String) map.get("housePropertyId");
					HouseProperty houseProperty = housePropertyService.findOne(housePropertyId);
            		HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
            		map.put("detailAddress",housePropertyVo.getTotalMailingAddress());
				}
			}
			
			msg.setMsg("集团处理评估价申诉列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(handlePage.getTotalRows());
			msg.setRows(handleList);
		}catch(Exception e){
			e.printStackTrace();
	        msg.setMsg("集团处理评估价申诉列表查询失败");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: editHandleAppeal 
	 * @Description: 集团处理申诉页面
	 * @author caixiekang 
	 * @param housePropertyId 房产id
	 * @return
	 */
	@RequestMapping("/editHandleAppeal")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.group.editHandleAppeal")
	public ModelAndView editHandleAppeal(String housePropertyId){
		ModelAndView mv = new ModelAndView("/casemanage/evaluated/group_handle_appeal_edit");
		try {
			//详细地址和申诉信息获取
			HouseProperty houseProperty = housePropertyService.findOne(housePropertyId);
    		HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
			List<EvaluatedAppeal> evaluatedAppeals = appealService.findInProcessByHousePropertyId(housePropertyId);
			EvaluatedAppeal evaluatedAppeal = evaluatedAppeals.get(0);
			EvaluatedAppealVo evaluatedAppealVo = new EvaluatedAppealVo(evaluatedAppeal);
			mv.addObject("fullAdress", housePropertyVo.getTotalMailingAddress());
			mv.addObject("evaluatedAppealVo", evaluatedAppealVo);
		} catch (Exception e) {
			logger.error("跳转集团处理评估价页面出现问题");
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateHandleAppeal 
	 * @Description: 集团处理申诉价保存
	 * @author caixiekang 
	 * @param id
	 * @param appealAprolAmount
	 * @param Submitted 页面上的flag 标识是否提交
	 * @return
	 */
	@RequestMapping("/saveOrUpdateHandleAppeal")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.group.saveOrUpdateHandleAppeal")
	@ResponseBody
	public ResponseMsg saveOrUpdateHandleAppeal(String id, String appealAprolAmount, String submitted){
		ResponseMsg msg = new ResponseMsg();
		try{
			EvaluatedAppeal evaluatedAppeal = appealService.findOne(id);
			if(ObjectHelper.isEmpty(evaluatedAppeal)){
				throw new BusinessException("错误评估价申诉为空");
			}
			if(ObjectHelper.isEmpty(appealAprolAmount)){
				throw new BusinessException("集团复议价为空");
			}
			//设置复议价的金额
			BigDecimal  appealAprolAmountBigDec = new BigDecimal(appealAprolAmount);
			evaluatedAppeal.setAppealAprolAmount(appealAprolAmountBigDec);
			//设置操作员工的信息
			EmpDto loginUser = ced.getLoginUser();
			evaluatedAppeal.setHandleDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			evaluatedAppeal.setHandleEmployeeId(loginUser.getId());
			evaluatedAppeal.setHandleEmployeeName(loginUser.getEmpNm());
			msg.setMsg("保存成功");
			//若点击提交则为变更完成
			HouseProperty houseProperty = null;
			if(EvaluatedAppealVo.SUBMITTED_YES.equals(submitted)){
				evaluatedAppeal.setAppealStatus(EvaluatedAppeal.FINISH);
				//房产信息更新复议价信息
				houseProperty = housePropertyService.findOne(evaluatedAppeal.getHousePropertyId());
				houseProperty.setControlReviewPrice(evaluatedAppeal.getAppealAprolAmount());
				msg.setMsg("提交成功");
			}
			appealService.saveOrUpdateEntity(evaluatedAppeal,houseProperty);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("保存集团复议价失败");
			msg.setMsg("保存集团复议价失败,请联系系统管理员处理");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		
		return msg;
		
	}
	/**
	 * 
	 * @Title: listHandleAppealRecord 
	 * @Description: 获取变更记录列表
	 * @author caixiekang 
	 * @param pageRequest
	 * @param housePropertyId 房产id
	 * @return
	 */
	@RequestMapping("/listHandleAppealRecord")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.group.listHandleAppealRecord")
	@ResponseBody
	public ResponseMsg listHandleAppealRecord(PageRequest pageRequest,String housePropertyId){
		ResponseMsg msg = new ResponseMsg();
		try{
			Page<EvaluatedAppeal> page = appealService.findFinishByHousePropertyId(housePropertyId, pageRequest);
			List<EvaluatedAppeal> list = page.getRecords();
			List<EvaluatedAppealVo> listVo = new ArrayList<>();
			if(ObjectHelper.isNotEmpty(list)){
				for (EvaluatedAppeal evaluatedAppeal : list) {
					listVo.add(new EvaluatedAppealVo(evaluatedAppeal));
				}
			}
			msg.setRows(listVo);
			msg.setTotal(page.getTotalRows());
			msg.setMsg("变更记录查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		}catch(Exception e){
			logger.error("变更记录查询失败");
			e.printStackTrace();
			msg.setMsg("变更记录查询失败");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: checkIsAppealInProcess 
	 * @Description: 查询评估价申诉是否在审批中
	 * @author caixiekang 
	 * @param housePropertyId
	 * @return
	 */
	@RequestMapping("/checkIsAppealInProcess")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.group.checkIsAppealInProcess")
	@ResponseBody
	public ResponseMsg checkIsAppealInProcess(String housePropertyId){
		List<EvaluatedAppeal> evaluatedAppeals = null;
		ResponseMsg msg = new ResponseMsg();
		try{
			evaluatedAppeals=appealService.findByHousePropertyIdAndStatus(housePropertyId,EvaluatedAppeal.IN_PROCESS);
			//如果有list不为null,则正在进行评估价申诉
			msg.setResultStatus(ResponseMsg.SUCCESS);
			if(ObjectHelper.isNotEmpty(evaluatedAppeals)){
				msg.setId("F");
				msg.setMsg("当前正在处理评估价申诉请求,请勿重复提交评估价申诉申请");
			}else{
				msg.setId("T");
			}
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("查询评估价是否在审批中失败",exception);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setId("F");
			msg.setMsg("查询失败,请联系管理员");
		}
		
		return msg;
	}
}
