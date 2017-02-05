package com.zdsoft.finance.prcostitem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.PrCostitemApply;
import com.zdsoft.finance.prcostitem.service.PrCostitemApplyService;
import com.zdsoft.finance.prcostitem.vo.PrCostitemApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 费用支拥申请
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2017-01-03
 */
@Controller
@RequestMapping("prCostitemApply")
public class PrCostitemApplyController extends BaseController{
	
	@Autowired
	private PrCostitemApplyService prCostitemApplyService;
	
	@Autowired
	private CED CED;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 入口
	 * @return
	 */
	@RequestMapping("/init")
    @UriKey(key = "com.zdsoft.finance.prCostitemApply.init")
    @Menu(resource = "com.zdsoft.finance.prCostItem.init", label = "收费支拥管理", group = "businessSetting", order = 1)
	public String init(){
		return "prcostitem/prCostitemApply_init";
	}
	
	/**
	 * 收费支拥申请查询
	 * @param pageable
	 * @param param 参数
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/listData")
	@UriKey(key = "com.zdsoft.finance.prCostitemApply.listData")
	@ResponseBody
	public String listData(PageRequest pageable,String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<PrCostitemApply> page = prCostitemApplyService.findByHqlConditions(pageable, queryObjs);
		List<PrCostitemApply> list = page.getRecords();
		List<PrCostitemApplyVo> listVo = new ArrayList<>();
		for (PrCostitemApply po : list) {
			PrCostitemApplyVo vo = new PrCostitemApplyVo(po);
			listVo.add(vo);
		}
		msg.setRows(listVo);
		msg.setTotal(page.getTotalRows());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	@RequestMapping("/add")
    @UriKey(key = "com.zdsoft.finance.prCostitemApply.add")
	public ModelAndView add(String id){
		ModelMap map = new ModelMap();
		PrCostitemApplyVo vo = null;
		if(ObjectHelper.isEmpty(id)){
			vo = new PrCostitemApplyVo();
			try {
				vo.setApplyOrgCd(CED.getLoginUser().getOrgCd());
				vo.setApplyOrgNm(CED.getLoginUser().getOrgNm());
				vo.setApplyEmpCd(CED.getLoginUser().getEmpCd());
				vo.setApplyEmpNm(CED.getLoginUser().getEmpNm());
				vo.setApplyDepCd(CED.getLoginUser().getDepartmentCd());
				vo.setApplyDepNm(CED.getLoginUser().getDepartmentName());
				vo.setApplyTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
				vo.setStatus(BusiFormStatus.DRAFT.value);
				vo.setStatusNm(BusiFormStatus.getName(BusiFormStatus.DRAFT.value));
			} catch (Exception e) {
				logger.error("CED错误",e.getMessage());
				e.printStackTrace();
			}
		}else{
			try {
				PrCostitemApply apply = prCostitemApplyService.findOne(id);
				vo = new PrCostitemApplyVo(apply);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询出错",e.getMessage());
			}
		}
		map.put("vo", vo);
		ModelAndView model = new ModelAndView("prcostitem/prCostitemApply_edit");
		model.addAllObjects(map);
		return model;
	}
	
	/**
	 * 保存
	 * @param vo
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/save")
    @UriKey(key = "com.zdsoft.finance.prCostitemApply.save")
	@ResponseBody
	public String save(PrCostitemApplyVo vo,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
        try {
        	vo.setCreateBy(CED.getLoginUser().getEmpCd());
        	vo.setCreateOrgCd(CED.getLoginUser().getOrgCd());
        	vo.setCreateTime(new Date());
        	vo.setUpdateBy(CED.getLoginUser().getEmpCd());
        	vo.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
        	vo.setUpdateTime(new Date());
        	PrCostitemApply apply = prCostitemApplyService.saveOrUpdate(vo.toPO());
            map.put("status", 1);
            map.put("msg", "保存成功！");
            map.put("id", apply.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存出错", e);
            map.put("status", 0);
            map.put("msg", "保存出错");
        }
        return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	
	/**
	 * 查看
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail")
    @UriKey(key = "com.zdsoft.finance.prCostitemApply.detail")
	public ModelAndView detail(String id){
		ModelMap map = new ModelMap();
		try {
			PrCostitemApply apply = prCostitemApplyService.findOne(id);
			PrCostitemApplyVo vo = new PrCostitemApplyVo(apply);
			map.put("vo", vo);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询出错",e.getMessage());
		}
		ModelAndView model = new ModelAndView("prcostitem/prCostitemApply_detail");
		model.addAllObjects(map);
		return model;
	}
	/**
	 * 删除
	 * @param id
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/del")
    @UriKey(key = "com.zdsoft.finance.prCostitemApply.del")
	@ResponseBody
	public String del(String id,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		try {
			prCostitemApplyService.applyLogicDelete(id);
			map.put("status", 1);
            map.put("msg", "删除成功！");
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("删除出错", e);
            map.put("status", 0);
            map.put("msg", "删除出错");
		}
		return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	
	
	//流程
	
	/**
	 * 送审
	 * @param vo
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/saveSend")
    @UriKey(key = "com.zdsoft.finance.prCostitemApply.saveSend")
	@ResponseBody
	public String saveSend(PrCostitemApplyVo vo,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
        try {
        	vo.setCreateBy(CED.getLoginUser().getEmpCd());
        	vo.setCreateOrgCd(CED.getLoginUser().getOrgCd());
        	vo.setCreateTime(new Date());
        	vo.setUpdateBy(CED.getLoginUser().getEmpCd());
        	vo.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
        	vo.setUpdateTime(new Date());
        	PrCostitemApply apply = prCostitemApplyService.saveSend(vo.toPO());
            map.put("status", 1);
            map.put("msg", apply.getMsg());
            map.put("id", apply.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("送审出错", e);
            map.put("status", 0);
            map.put("msg", "送审出错");
        }
        return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	
	/**
	 * 费用支拥编辑
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/editJob")
	@UriKey(key = "com.zdsoft.finance.prCostitemApply.editJob")
	@ManualJob(resource = "com.zdsoft.finance.prCostitemApply.editJob", label = "费用支拥编辑")
	public ModelAndView editJob(String projectId, String processInstanceId, String businessKey) {
		ModelMap model = new ModelMap();
		model.put("projectId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		PrCostitemApply bean = null;
		try {
			bean = prCostitemApplyService.findOne(projectId);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询数据错误",e.getMessage());
		}
		if(ObjectHelper.isNotEmpty(bean)){
			PrCostitemApplyVo vo = new PrCostitemApplyVo(bean);
			model.put("vo", vo);
		}
		return new ModelAndView("prcostitem/prCostitemApply_job_edit", model);
	}
	
	/**
	 * 费用支拥修改保存
	 * @param vo 数据
	 * @return 信息
	 */
	@RequestMapping(value = "/editJobSave")
	@UriKey(key = "com.zdsoft.finance.prCostitemApply.editJobSave")
	@ResponseBody
	@FinishJob(resource = "com.zdsoft.finance.prCostitemApply.editJob", businessId = "businessKey", projectId = "projectId")
	public ResponseMsg conditionJobEditSave(PrCostitemApplyVo vo,String businessKey,String projectId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			vo.setCreateBy(CED.getLoginUser().getEmpCd());
        	vo.setCreateOrgCd(CED.getLoginUser().getOrgCd());
        	vo.setCreateTime(new Date());
        	vo.setUpdateBy(CED.getLoginUser().getEmpCd());
        	vo.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
        	vo.setUpdateTime(new Date());
        	PrCostitemApply apply = prCostitemApplyService.saveOrUpdate(vo.toPO());
			msg.setId(apply.getId());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		}catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存失败");
			logger.error("保存失败",e);
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 费用支拥查看
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/viewJob")
	@UriKey(key = "com.zdsoft.finance.prCostitemApply.viewJob")
	@ManualJob(resource = "com.zdsoft.finance.prCostitemApply.viewJob", label = "费用支拥查看")
	@FinishJob(resource = "com.zdsoft.finance.prCostitemApply.viewJob", businessId = "businessKey", projectId = "projectId")
	public ModelAndView viewJob(String projectId, String processInstanceId, String businessKey) {
		ModelMap model = new ModelMap();
		model.put("projectId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		try {
			PrCostitemApply apply = prCostitemApplyService.findOne(projectId);
			PrCostitemApplyVo vo = new PrCostitemApplyVo(apply);
			model.put("vo", vo);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询出错",e.getMessage());
		}
		return new ModelAndView("prcostitem/prCostitemApply_job_detail", model);
	}
	
}
