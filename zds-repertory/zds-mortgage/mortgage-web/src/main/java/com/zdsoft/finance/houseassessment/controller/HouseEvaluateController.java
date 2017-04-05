package com.zdsoft.finance.houseassessment.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.finance.common.base.impl.CustomCommon;
import com.zdsoft.finance.houseassessment.entity.HouseEvaluate;
import com.zdsoft.finance.houseassessment.entity.HouseEvaluateDetail;
import com.zdsoft.finance.houseassessment.service.HouseEvaluateDetailService;
import com.zdsoft.finance.houseassessment.service.HouseEvaluateService;
import com.zdsoft.finance.houseassessment.vo.HouseEvaluateDetailVo;
import com.zdsoft.finance.houseassessment.vo.HouseEvaluateQueryVo;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseEvaluateController.java 
 * @ClassName: HouseEvaluateController 
 * @Description: 房产评估Controller 
 * @author yangjia 
 * @date 2017年2月22日 下午5:07:54 
 * @version V1.0
 */
@Controller
@RequestMapping("/houseEvaluate")
public class HouseEvaluateController extends BaseController{
	
	
	@Autowired
	HouseEvaluateDetailService houseEvaluateDetailService;
	
	@Autowired
	HousePropertyService housePropertyService;
	
	@Autowired
	HouseEvaluateService houseEvaluateService;
	
	@Autowired
	private CED CED;
	/**
	 * 
	 * @Title: AfterCheckList 
	 * @Description: 房产评估注册入口
	 * @author yangjia 
	 * @return
	 */
	@RequestMapping("/houseAssessmentQuery")
	@UriKey(key = "com.zdsoft.finance.houseassessment.houseEvaluate.houseAssessmentQuery")
	@Menu(resource = "com.zdsoft.finance.houseassessment.houseEvaluate.houseAssessmentQuery", label = "房产估价", group = "helper", order = 2)
	public ModelAndView HouseAssessmentQuery(HttpServletRequest request) {
		ModelMap modelMap = new ModelMap();
		String url = ConstantParameter.getHouseAssessmentUrl();
		String bizId =  request.getParameter("bizId");
		String houseKey = request.getParameter("houseKey");
		String houseArea = request.getParameter("houseArea");
		String houseAddress = request.getParameter("houseAddress");	
		url = url + "?bizId="+(bizId != null ? bizId : "");
		url = url + "&houseKey="+(houseKey != null ? houseKey : "");
		url = url + "&houseArea="+(houseArea != null ? houseArea : "");
		url = url + "&houseAddress="+(houseAddress != null ? houseAddress : "");
		modelMap.put("houseAssessmentUrl", url);
		return new ModelAndView("houseassessment/house_assessment_query",modelMap);
	}
	
    /**
     * 
     * @Title: getPriceForCompany 
     * @Description: 评估查看
     * @author yangjia 
     * @update jingyh
     * @return
     */
	@RequestMapping("/getPriceForCompany")
	@UriKey(key="com.zdsoft.finance.houseassessment.houseEvaluate.getPriceForCompany")
	public ModelAndView getPriceForCompany(HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		String bizid = request.getParameter("bizid");
		//集团处理评估价申诉页面专用标识 added by caixiekang
		String groupHandlePage = request.getParameter("groupHandlePage");
		try {
			if (ObjectHelper.isEmpty(bizid)) {
				throw new BusinessException("", "传入数据为空！");
			}
			Map<String, Object> condition = new HashMap<String, Object>();
			HouseProperty houseProperty	= null;
			condition.put("businessId", bizid);
			List<Map<String,Object>> list = houseEvaluateDetailService.queryHouseEvaluateInfo(condition);
			if (ObjectHelper.isEmpty(list)) {
				//先通过押品ID查询，押品ID查不到再通过房产信息查询
				condition.put("businessId", null);
				// 为空，则根据明细模糊查询
				houseProperty = housePropertyService.findOne(bizid);
				condition.put("houseKey", houseProperty.getCommunityName());
				condition.put("houseArea", houseProperty.getArea());
				condition.put("provinceName", CED.loadSimpleCodeNameByFullCode(houseProperty.getProvince()));
				condition.put("cityName", CED.loadSimpleCodeNameByFullCode(houseProperty.getCity()));
				condition.put("districtName", CED.loadSimpleCodeNameByFullCode(houseProperty.getDistrict()));
				list = houseEvaluateDetailService.queryHouseEvaluateInfo(condition);
			}
			Map<String,HouseEvaluateQueryVo> resutMap = new HashMap<String,HouseEvaluateQueryVo>();	
			String houseAddress = null;
			BigDecimal sumPrice = null;
			String sumPriceStr = "";
			if (ObjectHelper.isNotEmpty(list)) {
				// 有数据
				List<Object> resultList = CustomCommon.convert(HouseEvaluateQueryVo.class, list);
				// 只取一次评估的记录数据
				String evaluateId = null;
				for (Object voObj : resultList) {
					HouseEvaluateQueryVo vo = (HouseEvaluateQueryVo)voObj;
					if (evaluateId == null) {
						evaluateId = vo.getEvaluateId();
					}
					if (vo.getEvaluateId().equals(evaluateId)) {
						// 只取一次评估的记录数据
						houseAddress = vo.getHouseAddress();
						sumPrice = vo.getSumPrice();
						sumPriceStr = DecimalFormat.getCurrencyInstance().format(sumPrice);
						vo.setEvaluatePriceStr(DecimalFormat.getCurrencyInstance().format(vo.getEvaluatePrice()));
						resutMap.put(vo.getEvaluateCompany(), vo);
					}
				}
			}
			if (ObjectHelper.isEmpty(houseAddress) && ObjectHelper.isNotEmpty(houseProperty)) {
				houseAddress = houseProperty.getCommunityName();
			}
			//设置隐藏地址与价格表头的标识
			if(ObjectHelper.isNotEmpty(groupHandlePage)){
				modelMap.put("showTitle", true);
			}
			
			modelMap.put("houseAddress", houseAddress);
			modelMap.put("sumPrice", sumPriceStr);
			modelMap.put("resutMap", resutMap);
		} catch (Exception e) {
			logger.error("查询估价失败",e);
			e.printStackTrace();
			modelMap.put("errorMsg", e.getMessage());
		}
		return new ModelAndView("houseassessment/house_assessment_info",modelMap);
	}
	
	/**
	 * 
	 * @Title: initHouseEvaluatedList 
	 * @Description: 资调派单的房价评估信息初始化
	 * @author caixiekang 
	 * @param request
	 * @param housePropertyId房产id
	 * @return
	 */
	@RequestMapping("/initHouseEvaluatedList")
	@UriKey(key = "com.zdsoft.finance.houseassessment.houseEvaluate.initHouseEvaluatedList")
	public ModelAndView initHouseEvaluatedList(HttpServletRequest request,String housePropertyId) {
		logger.info("进入房产评估信息页面");
		logger.debug("房产Id：{}", housePropertyId);
		ModelMap model = new ModelMap();
		model.put("housePropertyId", housePropertyId);
		List<HouseEvaluateQueryVo> resultVoList = null;
		try {
			List<Map<String,Object>> infos = houseEvaluateService.queryHouseEvaluateInfos(housePropertyId);
			if (ObjectHelper.isNotEmpty(infos)) {
				resultVoList = new ArrayList<HouseEvaluateQueryVo>();
				// 有数据
				List<Object> resultList = CustomCommon.convert(HouseEvaluateQueryVo.class, infos);
				String evaluateId = null;
				for (Object voObj : resultList) {
					HouseEvaluateQueryVo vo = (HouseEvaluateQueryVo)voObj;
					if (evaluateId == null) {
						evaluateId = vo.getEvaluateId();
					}
					if (vo.getEvaluateId().equals(evaluateId)) {
						resultVoList.add(vo);
					}
				}
			}
			model.put("resultVoList", resultVoList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询房产评估信息错误！", e);
			model.put("errorMsg", e.getMessage());
		}
		return new ModelAndView("houseassessment/houseEvaluatedList",model);
	}
	
	/**
	 * 
	 * @Title: listHouseEvaluatedRecord 
	 * @Description: 返回房产评估信息列表
	 * @author caixiekang 
	 * @param req
	 * @param housePropertyId 房产id
	 * @return
	 */
	@RequestMapping("/listHouseEvaluatedRecord")
	@UriKey(key = "com.zdsoft.finance.houseassessment.houseEvaluate.listHouseEvaluatedRecord")
	@ResponseBody
	public ResponseMsg listHouseEvaluatedRecord(HttpServletRequest req, String housePropertyId){
		ResponseMsg msg = new ResponseMsg();
		try{
			//查找评估实体
			HouseEvaluate houseEvaluate = houseEvaluateService.findByHousePropertyId(housePropertyId);
			//查找评估信息
			List<HouseEvaluateDetail> houseEvaluateDeatils = 
					houseEvaluateDetailService.getHouseEvaluateDetailByHouseEvaluateId(houseEvaluate.getId());
			List<HouseEvaluateDetailVo> houseEvaluateDeatilVos = new ArrayList<>();
			for (HouseEvaluateDetail houseEvaluateDetail : houseEvaluateDeatils) {
				houseEvaluateDeatilVos.add(new HouseEvaluateDetailVo(houseEvaluateDetail));
			}
			msg.setRows(houseEvaluateDeatilVos);
			msg.setMsg("查询房产评估信息成功!");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("查询房产评估信息出现错误!");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: queryHouseEvaluateSumPrice 
	 * @Description: 异步查询房产综合评估价信息
	 * @author jingyh 
	 * @param req
	 * @return
	 */
	@RequestMapping("/queryHouseEvaluateSumPrice")
	@UriKey(key = "com.zdsoft.finance.houseassessment.houseEvaluate.queryHouseEvaluateSumPrice")
	@ResponseBody
	public ResponseMsg queryHouseEvaluateSumPrice(HttpServletRequest req) {
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			String address = req.getParameter("houseAddress");
			String businessId = req.getParameter("businessId");
			BigDecimal sumPrice = null;
			if (ObjectHelper.isNotEmpty(address) || ObjectHelper.isNotEmpty(businessId)) {
				condition.put("businessId", businessId);
				// 根据明细模糊查询
				condition.put("houseKey", req.getParameter("houseKey"));
				condition.put("houseArea", req.getParameter("houseArea"));
				String province = req.getParameter("province");
				if (ObjectHelper.isNotEmpty(province)) {
					condition.put("provinceName", CED.loadSimpleCodeNameByFullCode(province));
				}
				String city = req.getParameter("city");
				if (ObjectHelper.isNotEmpty(city)) {
					condition.put("cityName", CED.loadSimpleCodeNameByFullCode(city));
				}
				String district = req.getParameter("district");
				if (ObjectHelper.isNotEmpty(district)) {
					condition.put("districtName", CED.loadSimpleCodeNameByFullCode(district));
				}
				condition.put("houseAddress", address);
				List<Map<String, Object>> evaluateInfoList = houseEvaluateDetailService.queryHouseEvaluateInfo(condition);
				if (ObjectHelper.isNotEmpty(evaluateInfoList)) {
					// 有数据
					List<Object> resultList = CustomCommon.convert(HouseEvaluateQueryVo.class, evaluateInfoList);
					// 只取一次评估的记录数据
					String evaluateId = null;
					for (Object voObj : resultList) {
						HouseEvaluateQueryVo vo = (HouseEvaluateQueryVo)voObj;
						if (evaluateId == null) {
							evaluateId = vo.getEvaluateId();
						}
						if (vo.getEvaluateId().equals(evaluateId)) {
							// 只取一次评估的记录数据
							sumPrice = vo.getSumPrice();
						}
						if (ObjectHelper.isNotEmpty(sumPrice)) {
							// 跳出循环
							break;
						}
					}
				}
			}
			msg.optional("sumPrice", sumPrice);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询房产评估价发生错误!",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
}
