package com.zdsoft.finance.casemanage.evaluated.controller;

import java.math.BigDecimal;
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
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.casemanage.evaluated.entity.EvaluatedAppeal;
import com.zdsoft.finance.casemanage.evaluated.service.EvaluatedAppealService;
import com.zdsoft.finance.casemanage.evaluated.vo.EvaluatedAppealVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
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
 * @Title: EvaluatedController.java 
 * @ClassName: EvaluatedController 
 * @Description: 评估价申诉
 * @author caixiekang 
 * @date 2017年2月17日 下午3:12:44 
 * @version V1.0
 */
@Controller
@RequestMapping("/casemanage/evaluated")
public class EvaluatedAppealController extends BaseController{

	@Autowired 
	private EvaluatedAppealService appealService;
	@Autowired
	private CED ced;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	/**
	 * 
	 * @Title: initAppealList 
	 * @Description: 初始化评估价申诉页面
	 * @author caixiekang 
	 * @return
	 */
	@RequestMapping("/initAppealList")
    @UriKey(key = "com.zdsoft.finance.casemanage.evaluated.initAppealList")
    @Menu(resource = "com.zdsoft.finance.casemanage.evaluated.initAppealList", label = "评估价申诉", group = "project", order = 16)
    public ModelAndView initAppealList(){
        return new ModelAndView("/casemanage/evaluated/case_house_list");
        
    }
	
	/**
	 * 
	 * @Title: listAppealList 
	 * @Description: 获取评估价申诉页面的列表
	 * @author caixiekang 
	 * @param request
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/listAppealList")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.listAppealList")
	@ResponseBody
	public ResponseMsg listAppealList(HttpServletRequest request, PageRequest pageable){
		ResponseMsg msg = new ResponseMsg();
		try {
			//获取搜索参数
            List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[]{"ca", "cu", "prc", "prp"});
            //获取评估价申诉的结果集
            Page<Map<String, Object>> appealPage = appealService.queryAppealListByCondiction(pageable, queryObjs);
            //获取LiSt,再拼接需要的Map对象
            List<Map<String, Object>> appealList = appealPage.getRecords();
            if(ObjectHelper.isNotEmpty(appealList)){
            	for (Map<String, Object> map : appealList) {
            		//判断可否显示申诉按钮
            		BigDecimal appealStatusB = (BigDecimal) map.get("appealStatus");
            		String appealStatus=appealStatusB.toString();
            		//判断是否有在审批中的申诉
            		if("1".equals(appealStatus)){
            			map.put("operatable", false);
            		}else{
            			map.put("operatable", true);
            		}
            		
            		//地址拼接
            		String housePropertyId = (String) map.get("housePropertyId");
            		HouseProperty houseProperty = housePropertyService.findOne(housePropertyId);
            		HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
            		map.put("detailAddress",housePropertyVo.getTotalMailingAddress());
            		
            	}
            }
            msg.setMsg("评估价申诉查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(appealPage.getTotalRows());
            msg.setRows(appealPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setMsg("案件跟踪列表查询失败");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
		return msg;
	}
	/**
	 * 
	 * @Title: eidtAppeal 
	 * @Description: 进入评估价申诉页面
	 * @author caixiekang
	 * @param caseApplyId 案件id
	 * @param housePropertyId 房产id
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/eidtAppeal")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.eidtAppeal")
	public ModelAndView eidtAppeal(String caseApplyId, String housePropertyId ) throws BusinessException{
		ModelAndView mv = new ModelAndView("/casemanage/evaluated/evluated_appeal_application_edit");
		
		try {
			//获取案件VO
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply); 
			//获取主借人名字
			List<BeforePersonalCustomer> bfs = beforePersonalCustomerService.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			caseApplyVo.setCustomerName(bfs.get(0).getCustomerName());
			//获取评估价申诉VO
			EvaluatedAppealVo evaluatedAppealVo = new EvaluatedAppealVo();
			List<EvaluatedAppeal> appeals =appealService.findDraftByHousePropertyId(housePropertyId);
			if(ObjectHelper.isNotEmpty(appeals)){
				evaluatedAppealVo = new EvaluatedAppealVo(appeals.get(0));
			}
			//获取当前操作人
			EmpDto employee = ced.getLoginUser();
			evaluatedAppealVo.setAppealName(employee.getEmpNm());
			evaluatedAppealVo.setAppealEmployeId(employee.getId());
			evaluatedAppealVo.setAppealDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			mv.addObject("caseApplyVo", caseApplyVo);
			mv.addObject("housePropertyId", housePropertyId);
			mv.addObject("evaluatedAppealVo", evaluatedAppealVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入评估价申诉编辑页面出错");
		}
		
		return mv;
	}
	
	/**
	 * 
	 * @Title: saveAppeal 
	 * @Description: 保存评估价
	 * @author caixiekang 
	 * @param evaluatedAppealVo 评估价申诉VO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/saveAppeal")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.saveAppeal")
	@ResponseBody
	public ResponseMsg saveAppeal(EvaluatedAppealVo evaluatedAppealVo) throws BusinessException{
		ResponseMsg msg = new ResponseMsg();
		try {
			if(ObjectHelper.isNotEmpty(evaluatedAppealVo)){
				EvaluatedAppeal evaluatedAppeal = evaluatedAppealVo.toPo();
				//判断是否保存
				if(EvaluatedAppealVo.SUBMITTED_NO.equals(evaluatedAppealVo.getSumitted())){
					evaluatedAppeal.setAppealStatus(EvaluatedAppeal.DRAFT);
				}
				if(EvaluatedAppealVo.SUBMITTED_YES.equals(evaluatedAppealVo.getSumitted())){
					evaluatedAppeal.setAppealStatus(EvaluatedAppeal.IN_PROCESS);
				}
				EvaluatedAppeal afterSaved = appealService.saveOrUpdateEntity(evaluatedAppeal);
				msg.setResultStatus(ResponseMsg.SUCCESS);
				msg.setMsg("评估价申诉提交成功");
				Map<String, Object> map = new HashMap<String, Object>();
				//返回id到页面
				map.put("evaluatedAppealVoId", afterSaved.getId());
				msg.setOptional(map);
			}else{
				msg.setResultStatus(ResponseMsg.SYS_ERROR);
				msg.setMsg("评估价申诉信息为空,请输入必填信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("评估价申诉信息提交失败,请联系管理员");
		}
		
		return msg;
	}
	/**
	 * 
	 * @Title: viewAppeal 
	 * @Description: 评估价申诉详情
	 * @author caixiekang 
	 * @param caseApplyId 案件id
	 * @param housePropertyId 房产id
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/viewAppeal")
	@UriKey(key = "com.zdsoft.finance.casemanage.evaluated.viewAppeal")
	public ModelAndView viewAppeal(String caseApplyId, String housePropertyId ) throws BusinessException{
		ModelAndView mv = new ModelAndView("/casemanage/evaluated/evluated_appeal_application_view");
		try {
			//获取评估价申诉详情VO
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply); 
			//获取主借人名字
			List<BeforePersonalCustomer> bfs = beforePersonalCustomerService.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			caseApplyVo.setCustomerName(bfs.get(0).getCustomerName());
			//查找在审批中的评估价申诉
			List<EvaluatedAppeal> appeals =appealService.findInProcessByHousePropertyId(housePropertyId);
			EvaluatedAppeal apeal = appeals.get(0);
			EvaluatedAppealVo evaluatedAppealVo = new EvaluatedAppealVo(apeal);
			mv.addObject("caseApplyVo", caseApplyVo);
			mv.addObject("housePropertyId", housePropertyId);
			mv.addObject("evaluatedAppealVo", evaluatedAppealVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
}



