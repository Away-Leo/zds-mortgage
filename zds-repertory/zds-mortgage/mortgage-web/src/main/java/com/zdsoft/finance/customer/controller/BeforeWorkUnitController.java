package com.zdsoft.finance.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
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
 * @Title:BeforeWorkUnitController.java
 * @Package:com.zdsoft.finance.customer.controller
 * @Description:贷前工作单位列表
 * @author: xj
 * @date:2017年1月12日 下午9:29:11
 * @version:v1.0
 */
@Controller
@RequestMapping("beforeWorkUnit")
public class BeforeWorkUnitController extends BaseController {
	@Autowired
	private BeforeWorkUnitService beforeWorkUnitService;
	@Autowired
	private CED CED;
	/**
	 * 
	 * 通过案件id查询贷前客户工作信息
	 *
	 * @author xj
	 * @param customerId
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
					this.transformFullcode(beforeWorkUnitVo);
					
				}
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(Long.parseLong(beforeWorkUnitVos.size()+""));
			msg.setRows(beforeWorkUnitVos);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("查询贷前工作地址异常：", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
	//转换simplecode
	private void transformFullcode(BeforeWorkUnitVo beforeWorkUnitVo) throws Exception {
		//行业类型
		String industry = beforeWorkUnitVo.getIndustry();
		if(ObjectHelper.isNotEmpty(industry)){
			String industryNm = CED.loadSimpleCodeNameByFullCode(industry);
			beforeWorkUnitVo.setIndustryNm(industryNm);
		}
		//行业
		String industryType = beforeWorkUnitVo.getIndustry();
		if(ObjectHelper.isNotEmpty(industryType)){
			String industryTypeNm = CED.loadSimpleCodeNameByFullCode(industryType);
			beforeWorkUnitVo.setIndustryTypeNm(industryTypeNm);
		}
		//单位性质
		String workUnitNature = beforeWorkUnitVo.getWorkUnitNature();
		if(ObjectHelper.isNotEmpty(workUnitNature)){
			String workUnitNatureNm = CED.loadSimpleCodeNameByFullCode(workUnitNature);
			beforeWorkUnitVo.setWorkUnitNatureNm(workUnitNatureNm);
		}
		//职务
		String position = beforeWorkUnitVo.getPosition();
		if(ObjectHelper.isNotEmpty(position)){
			String positionNm = CED.loadSimpleCodeNameByFullCode(position);
			beforeWorkUnitVo.setPositionNm(positionNm);
		}
		//拼接地址
		String province = beforeWorkUnitVo.getProvince();
		String city = beforeWorkUnitVo.getCity();
		String district = beforeWorkUnitVo.getDistrict();
		String workUnitAddressName ="";
		if(ObjectHelper.isNotEmpty(province)){
			workUnitAddressName =CED.loadSimpleCodeNameByFullCode(province);
		}
		if(ObjectHelper.isNotEmpty(city)){
			workUnitAddressName +="/"+CED.loadSimpleCodeNameByFullCode(city);
		}
		if(ObjectHelper.isNotEmpty(district)){
			workUnitAddressName +="/"+CED.loadSimpleCodeNameByFullCode(district);
		}
		beforeWorkUnitVo.setWorkUnitAddressName(workUnitAddressName);
	}
}
