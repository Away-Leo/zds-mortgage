package com.zdsoft.finance.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.finance.customer.vo.BeforeWorkUnitVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeWorkUnitController.java 
 * @ClassName: BeforeWorkUnitController 
 * @Description: 贷前工作单位
 * @author xj 
 * @date 2017年3月13日 上午9:19:02 
 * @version V1.0
 */
@Controller
@RequestMapping("beforeWorkUnit")
public class BeforeWorkUnitController extends BaseController {
	@Autowired
	private BeforeWorkUnitService beforeWorkUnitService;
	
	/**
	 * 
	 * @Title: listWorkUnitByCustomerId 
	 * @Description: 通过案件id查询贷前客户工作信息
	 * @author xj 
	 * @param customerId 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/listWorkUnitByCustomerId")
	@UriKey(key = "com.zdsoft.finance.beforeWorkUnit.listWorkUnitByCustomerId")
	@ResponseBody
	public String listWorkUnitByCustomerId(String customerId,String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		try {
			List<BeforeWorkUnit> beforeWorkUnits = beforeWorkUnitService.queryByCustomerId(customerId);
			List<BeforeWorkUnitVo> beforeWorkUnitVos = new ArrayList<BeforeWorkUnitVo>();
			if(ObjectHelper.isNotEmpty(beforeWorkUnits)){
				for (BeforeWorkUnit beforeWorkUnit : beforeWorkUnits) {
					BeforeWorkUnitVo beforeWorkUnitVo = new BeforeWorkUnitVo(beforeWorkUnit);
					beforeWorkUnitVos.add(beforeWorkUnitVo);
				}
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(Long.parseLong(beforeWorkUnitVos.size()+""));
			msg.setRows(beforeWorkUnitVos);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("查询贷前工作地址异常：", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
}
