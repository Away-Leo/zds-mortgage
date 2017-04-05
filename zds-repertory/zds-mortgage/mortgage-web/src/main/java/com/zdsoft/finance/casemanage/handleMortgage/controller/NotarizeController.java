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
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.finance.casemanage.handleMortgage.entity.Notarize;
import com.zdsoft.finance.casemanage.handleMortgage.service.NotarizeService;
import com.zdsoft.finance.casemanage.handleMortgage.vo.HandleMortgageVo;
import com.zdsoft.finance.casemanage.handleMortgage.vo.NotarizeVo;
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

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: NotarizeController.java 
 * @ClassName: NotarizeController 
 * @Description: 公证Controller
 * @author zhoushichao 
 * @date 2017年2月16日 下午5:55:18 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/casemanage/handleMortgage")
public class NotarizeController extends BaseController {

	@Autowired
    private NotarizeService notarizeService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private CooperatorTerminalService cooperatorTerminalService;
	@Autowired
	private CapitalistService capitalistService;
	@Autowired
	CED CED;
    
    /**
     * 
     * @Title: editNotarize 
     * @Description: 流程中公证编辑页面
     * @author zhoushichao 
     * @param caseApplyId 案件Id
     * @return
     */
    @RequestMapping("/editNotarize")
    @UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.editNotarize")
    public ModelAndView editNotarize(String caseApplyId){
        ModelMap model = new ModelMap();
        try {
        	//案件基本信息
        	CaseApply caseApply = caseApplyService.findOne(caseApplyId);
        	CaseApplyVo caseApplyVo = new  CaseApplyVo(caseApply);
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
        
        return new ModelAndView("/casemanage/handleMortgage/notarize_edit", model);
    }
    
	/**
	 * 
	 * @Title: getNotarizeList 
	 * @Description: 公证信息查询列表
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getNotarizeList")
	@UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.getNotarizeList")
	@ResponseBody
	public String getNotarizeList(String caseApplyId, String jsoncallback) {
		
		ResponseMsg msg = new ResponseMsg();
		// 分页公证信息
		List<Notarize> notarizeList = null;
		List<NotarizeVo> notarizeVoList = new ArrayList<NotarizeVo>();
		try {
			notarizeList = notarizeService.findByCaseApplyId(caseApplyId);
			if (notarizeList.size() == 0) {
				//获取公证类型
				List<SimpleCodeDto> simpleCodeDtoList = CED.querySimpleCodeByCategoryId(Notarize.CATEGORY_ID);
				for (SimpleCodeDto simpleCodeDto : simpleCodeDtoList) {
					NotarizeVo vo = new NotarizeVo();
					vo.setCaseApplyId(caseApplyId);
					vo.setNotarizeType(simpleCodeDto.getFullCode());
					vo.setNotarizeTypeName(simpleCodeDto.getName());
					notarizeVoList.add(vo);
				}
			}else {
				for(Notarize notarize : notarizeList){
					NotarizeVo vo = new NotarizeVo(notarize);
					notarizeVoList.add(vo);
				}
			}
			
			msg.setTotal(new Long(notarizeVoList.size()));
			msg.setRows(notarizeVoList);
			msg.setMsg("公证信息列表查询成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("公证信息列表查询失败");
			e.printStackTrace();
			logger.error("公证信息列表查询失败:", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	 /**
	  * 
	  * @Title: saveNotarize 
	  * @Description: 保存或更新公证
	  * @author zhoushichao 
	  * @param handleMortgageVo  办理抵押基本信息
	  * @return
	  */
    @RequestMapping("/saveNotarize")
    @UriKey(key = "com.cnfh.rms.casemanage.handleMortgage.saveNotarize")
    @ResponseBody
    public ResponseMsg saveNotarize(HandleMortgageVo handleMortgageVo) {
        ResponseMsg msg = new ResponseMsg();
        
        //公证vo转换po
        List<Notarize> notarizeList = new ArrayList<Notarize>();
        if(ObjectHelper.isNotEmpty(handleMortgageVo.getNotarizeVoList())){
            for (NotarizeVo notarizeVo : handleMortgageVo.getNotarizeVoList()) {
            	Notarize notarize = notarizeVo.toPo();
                notarizeList.add(notarize);
            }
        }
        try {
        	//保存公证信息
        	notarizeService.saveOrUpdateNotarizeList(notarizeList);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("保存公证成功!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存公证失败!", e);
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("保存公证失败");
        }
        return msg;
    }
}