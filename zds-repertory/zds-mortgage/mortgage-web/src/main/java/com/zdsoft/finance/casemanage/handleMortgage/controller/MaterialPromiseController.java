package com.zdsoft.finance.casemanage.handleMortgage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.casemanage.handleMortgage.entity.MaterialPromise;
import com.zdsoft.finance.casemanage.handleMortgage.service.MaterialPromiseService;
import com.zdsoft.finance.casemanage.handleMortgage.vo.MaterialPromiseVo;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialPromiseController.java 
 * @ClassName: MaterialPromiseController 
 * @Description: 后补资料承诺Controller 
 * @author zhoushichao 
 * @date 2017年2月18日 下午2:20:14 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/casemanage/handleMortgage")
public class MaterialPromiseController extends BaseController{

	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private CapitalistService capitalistService;
	@Autowired
	private CooperatorTerminalService cooperatorTerminalService;
	@Autowired
	private MaterialPromiseService materialPromiseService;
	@Autowired
	CED CED;
	
	/**
	 * 
	 * @Title: editMaterialPromise 
	 * @Description: 初始化后补资料承诺页面
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @return
	 */
    @RequestMapping("/editMaterialPromise")
    @UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.editMaterialPromise")
    public ModelAndView editMaterialPromise(String caseApplyId){
        ModelMap model = new ModelMap();
        try {
        	//案件基本信息
        	CaseApply caseApply = caseApplyService.findOne(caseApplyId);
        	CaseApplyVo caseApplyVo = new  CaseApplyVo(caseApply);
        	//查询资金来源
        	if (ObjectHelper.isNotEmpty(caseApply.getCapitalSource())) {
	    		Capitalist capitalist = capitalistService.findOne(caseApply.getCapitalSource());
	    		String cooperatorName = capitalist.getCapitalName();
	    		caseApplyVo.setCapitalSourceName(cooperatorName);
	    	}
        	//查询终端
        	CooperatorTerminal cooperatorTerminal = cooperatorTerminalService.findOne(caseApply.getTerminalId());
        	caseApplyVo.setTerminalIdName(cooperatorTerminal.getTerminalFullName());
        	model.put("caseApplyVo", caseApplyVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询数据失败",e.getMessage());
        }
        
        return new ModelAndView("/casemanage/handleMortgage/material_promise_edit", model);
    }
    
    /**
	 * 
	 * @Title: getMaterialPromiseList 
	 * @Description: 后补资料承诺查询列表
	 * @author zhoushichao 
	 * @param caseApplyId  案件Id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getMaterialPromiseList")
	@UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.getMaterialPromiseList")
	@ResponseBody
	public String getMaterialPromiseList(String caseApplyId, String jsoncallback) {
		
		ResponseMsg msg = new ResponseMsg();
		//后补资料承诺
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
			
			msg.setTotal(new Long(naterialPromiseVoList.size()));
			msg.setRows(naterialPromiseVoList);
			msg.setMsg("后补资料承诺列表查询成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("后补资料承诺列表查询失败");
			e.printStackTrace();
			logger.error("后补资料承诺列表查询失败:", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	  * 
	  * @Title: saveMaterialPromiseList 
	  * @Description: 批量保存后补资料承诺
	  * @author zhoushichao 
	  * @param materialPromises
	  * @return
	  */
   @SuppressWarnings("unchecked")
	@RequestMapping("/saveMaterialPromiseList")
   @UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.saveMaterialPromiseList")
   @ResponseBody
   public ResponseMsg saveMaterialPromiseList(String materialPromises){
       ResponseMsg msg = new ResponseMsg();
       
       try {
           //JSON格式转换成List
           JSONArray jsonMaterialPromiseList = JSONArray.fromObject(materialPromises);
           List<MaterialPromise> materialPromiseList = JSONArray.toList(jsonMaterialPromiseList, new MaterialPromise(), new JsonConfig());
           
           //保存
           materialPromiseService.saveMaterialPromiseList(materialPromiseList);
           
           msg.setResultStatus(ResponseMsg.SUCCESS);
           msg.setMsg("后补资料承诺保存成功");
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("后补资料承诺保存失败", e);
           msg.setResultStatus(ResponseMsg.APP_ERROR);
           msg.setMsg("系统内部错误，请查看日志");
       }
       return msg;
   }
	
}