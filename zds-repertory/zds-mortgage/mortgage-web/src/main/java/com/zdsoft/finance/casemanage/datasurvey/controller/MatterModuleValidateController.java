package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.casemanage.datasurvey.service.MatterModuleValidateService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MatterModuleValidateController.java 
 * @ClassName: MatterModuleValidateController 
 * @Description:  事项模块验证Controller 
 * @author zhoushichao 
 * @date 2017年3月3日 下午3:13:49 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/casemanage/datasurvey")
public class MatterModuleValidateController extends BaseController {

	@Autowired
	private MatterModuleValidateService matterModuleValidateService;
	
	/**
	 * 
	 * @Title: saveMatterModuleValidate 
	 * @Description: 保存事项模块验证
	 * @author zhoushichao 
	 * @param matterModuleValidate
	 * @return
	 */
	@RequestMapping("/saveMatterModuleValidate")
	@UriKey(key="com.zdsoft.finance.casemanage.datasurvey.saveMatterModuleValidate")
	@ResponseBody
	public ResponseMsg saveMatterModuleValidate(MatterModuleValidate matterModuleValidate){
		ResponseMsg msg = new ResponseMsg();
		MatterModuleValidate validate = new MatterModuleValidate();
		try {
			
			List<MatterModuleValidate> list = matterModuleValidateService.findByMatterNameAndTabNameAndExecuteTag(matterModuleValidate.getBusinessKey(),matterModuleValidate.getMatterName(), matterModuleValidate.getTabName(), matterModuleValidate.getExecuteTag());
			
			//判断是否执行过
			if (list.size()==0) {
				//设置数据
				validate.setBusinessKey(matterModuleValidate.getBusinessKey());
				validate.setMatterName(matterModuleValidate.getMatterName());
				validate.setTabName(matterModuleValidate.getTabName());
				validate.setExecuteTag(matterModuleValidate.getExecuteTag());
				
				//保存资调调查状态
				matterModuleValidateService.saveOrUpdateEntity(validate);
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			logger.error("保存事项模块验证失败！");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("保存事项模块验证失败！");
			e.printStackTrace();
		}
		return msg;
	}
}
