package com.zdsoft.finance.product.controller;

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

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.product.entity.PartRepayment;
import com.zdsoft.finance.product.service.PartRepaymentService;
import com.zdsoft.finance.product.vo.PartRepaymentVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 分段还款
 * @createTime 2017年1月10日 下午2:51:37
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Controller
@RequestMapping("partRepayment")
public class PartRepaymentController extends BaseController {

	@Autowired
	private PartRepaymentService partRepaymentService;
	@Autowired
	private CED CED;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 档案清单入口
	 * @return 档案清单页面
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.partRepayment.init")
	public ModelAndView init(String productId) {
		ModelMap map = new ModelMap();
		//TODO 自测
//		if(ObjectHelper.isEmpty(productId)){
//			productId = "TODO1";
//		}
		map.put("productId", productId);
		return new ModelAndView("/product/part_repayment_list",map);
	}
	
	/**
	 * 档案清单查询
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/list")
	@UriKey(key = "com.zdsoft.finance.partRepayment.list")
	@ResponseBody
	public String list(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<PartRepayment> page = partRepaymentService.findByHqlConditions(pageable, queryObjs);
		List<PartRepayment> list = page.getRecords();
		List<PartRepaymentVo> listVo = new ArrayList<>();
		if(ObjectHelper.isNotEmpty(list) && list.size() > 0){
			for (PartRepayment bean : list) {
				PartRepaymentVo vo = new PartRepaymentVo(bean);
				listVo.add(vo);
			}
		}
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(listVo);
		msg.setTotal(page.getTotalRows());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保存
	 * @param vo
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/save")
    @UriKey(key = "com.zdsoft.finance.partRepayment.save")
	@ResponseBody
	public String save(PartRepaymentVo vo,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
        try {
        	if(ObjectHelper.isEmpty(vo.getId())){
        		vo.setCreateBy(CED.getLoginUser().getEmpCd());
        		vo.setCreateOrgCd(CED.getLoginUser().getOrgCd());
        		vo.setCreateTime(new Date());
        	}else{
        		vo.setUpdateBy(CED.getLoginUser().getEmpCd());
        		vo.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
        		vo.setUpdateTime(new Date());
        	}
        	PartRepayment po = partRepaymentService.saveOrUpdate(vo.toPO());
            map.put("status", 1);
            map.put("msg", "保存成功！");
            map.put("id", po.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存出错", e);
            map.put("status", 0);
            map.put("msg", "保存出错");
        }
        return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	
	/**
	 * 删除
	 * @param id
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/deleted")
	@UriKey(key = "com.zdsoft.finance.partRepayment.deleted")
	@ResponseBody
	public String deleted(String id,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
		try {
			partRepaymentService.logicDelete(id);
			map.put("status", 1);
			map.put("msg", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除出错", e);
			map.put("status", 0);
			map.put("msg", "删除出错！");
		}
		return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	
}
