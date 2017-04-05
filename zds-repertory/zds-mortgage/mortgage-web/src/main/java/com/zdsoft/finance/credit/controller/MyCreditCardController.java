package com.zdsoft.finance.credit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.finance.credit.entity.MyCreditCard;
import com.zdsoft.finance.credit.service.MyCreditCardService;
import com.zdsoft.finance.credit.vo.MyCreditCardVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: MyCreditCardController.java
 * @ClassName: MyCreditCardController
 * @Description: 本人信用卡
 * @author zhongyong
 * @date 2017年2月23日 下午5:24:13
 * @version V1.0
 */
@Controller
@RequestMapping("myCreditCard")
public class MyCreditCardController extends BaseController{

	@Autowired
	private MyCreditCardService myCreditCardService;
	
	@Autowired
	private CED CED;

	/***
	 * @Title: getMyCreditCard 
	 * @Description: 信用卡分页数据查询
	 * @author zhongyong 
	 * @param request
	 * @param jsoncallback
	 * @param pageable 分页信息
	 * @return
	 */
	@RequestMapping("/getMyCreditCard")
	@UriKey(key = "com.zdsoft.finance.credit.myCreditCard.getMyCreditCard")
	@ResponseBody
	public String getMyCreditCard(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();;
		try {
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页查询本人信用卡
			Page<MyCreditCard> page = myCreditCardService.findByHqlConditions(pageable, queryObjs);
			List<MyCreditCard> list = page.getRecords();
			List<MyCreditCardVo> listVo = new ArrayList<MyCreditCardVo>();
			for (MyCreditCard info : list) {
				MyCreditCardVo vo = new MyCreditCardVo(info);
				listVo.add(vo);
			}
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(page.getTotalRows());
			msg.setRows(listVo);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("信用卡查询异常：", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存信用卡数据
	 * @author zhongyong
	 * @param myCreditCardVo 信用卡信息vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.credit.myCreditCard.save")
	@ResponseBody
	public String save(MyCreditCardVo myCreditCardVo) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
		    EmpDto empDto = CED.getLoginUser();
			if (!ObjectHelper.isEmpty(myCreditCardVo)) {
				if (ObjectHelper.isEmpty(myCreditCardVo.getId())) {
					MyCreditCard myCreditCard = myCreditCardVo.toPO();
					myCreditCard.setCreateBy(empDto.getEmpCd());
					myCreditCard.setCreateOrgCd(empDto.getOrgCd());
					myCreditCardService.saveEntity(myCreditCard);
					msg.setMsg("保存成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				} else {
					MyCreditCard myCreditCardNew = myCreditCardVo.toPO();
					MyCreditCard myCreditCard = myCreditCardService.findOne(myCreditCardVo.getId());
					myCreditCard = (MyCreditCard) ObjectProperUtil.compareAndValue(myCreditCardNew, myCreditCard, false,null);
					myCreditCard.setUpdateBy(empDto.getEmpCd());
					myCreditCard.setUpdateOrgCd(empDto.getOrgCd());
					myCreditCardService.updateEntity(myCreditCard);
					msg.setMsg("更新成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				}
			} else {
				msg.setMsg("数据为空");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("保存信用卡失败！");
            logger.error("保存信用卡失败！", e);
            e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * @Title: del 
	 * @Description: 删除信用卡数据
	 * @author zhongyong 
	 * @param jsoncallback
	 * @param id 信用卡id
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.credit.myCreditCard.del")
	@ResponseBody
	public String del(String jsoncallback, String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			myCreditCardService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("删除信用卡异常：", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}
}
