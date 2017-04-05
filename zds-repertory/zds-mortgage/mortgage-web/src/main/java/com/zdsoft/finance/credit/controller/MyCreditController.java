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
import com.zdsoft.finance.credit.entity.MyCredit;
import com.zdsoft.finance.credit.service.MyCreditService;
import com.zdsoft.finance.credit.vo.MyCreditVo;
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
 * @Title: MyCreditController.java
 * @ClassName: MyCreditController
 * @Description: 本人征信
 * @author zhongyong
 * @date 2017年2月23日 下午5:01:56
 * @version V1.0
 */
@Controller
@RequestMapping("myCredit")
public class MyCreditController extends BaseController {

	@Autowired
	private MyCreditService myCreditService;
	
	@Autowired
	private CED CED ;

	/**
	 * @Title: getCapitalistContact 
	 * @Description: 征信分页数据查询
	 * @author zhongyong 
	 * @param request
	 * @param jsoncallback
	 * @param pageable 分页信息
	 * @return
	 */
	@RequestMapping("/getMyCredit")
	@UriKey(key = "com.zdsoft.finance.credit.myCredit.getMyCredit")
	@ResponseBody
	public String getCapitalistContact(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页查询本人征信
			Page<MyCredit> page = myCreditService.findByHqlConditions(pageable, queryObjs);
			List<MyCredit> list = page.getRecords();
			List<MyCreditVo> listVo = new ArrayList<MyCreditVo>();
			for (MyCredit info : list) {
				MyCreditVo vo = new MyCreditVo(info);
				listVo.add(vo);
			}
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(page.getTotalRows());
			msg.setRows(listVo);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("本人征信查询异常：", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存征信数据
	 * @author zhongyong
	 * @param myCreditVo 本人征信vo
	 * @return
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.credit.myCredit.save")
	@ResponseBody
	public String save(MyCreditVo myCreditVo) {
		ResponseMsg msg = new ResponseMsg();
		try {
		    EmpDto empDto = CED.getLoginUser();
			if (!ObjectHelper.isEmpty(myCreditVo)) {
				if (ObjectHelper.isEmpty(myCreditVo.getId())) {
					MyCredit myCredit = myCreditVo.toPO();
					myCredit.setCreateBy(empDto.getEmpCd());
					myCredit.setCreateOrgCd(empDto.getOrgCd());
					myCredit= myCreditService.saveEntity(myCredit);
					msg.setMsg("保存成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				} else {
					MyCredit myCreditNew = myCreditVo.toPO();
					MyCredit myCredit = myCreditService.findOne(myCreditVo.getId());
					myCredit = (MyCredit) ObjectProperUtil.compareAndValue(myCreditNew, myCredit, false,null);
					myCredit.setUpdateBy(empDto.getEmpCd());
					myCredit.setUpdateOrgCd(empDto.getOrgCd());
					myCreditService.updateEntity(myCredit);
					msg.setMsg("更新成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				}
			} else {
				msg.setMsg("数据为空");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("保存本人征信失败！");
            logger.error("保存本人征信失败！", e);
            e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * @Title: del 
	 * @Description: 删除本人征信数据
	 * @author zhongyong 
	 * @param jsoncallback
	 * @param id 本人征信id
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.credit.myCredit.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
		ResponseMsg msg = new ResponseMsg();
		try {
			myCreditService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("删除本人征信异常：", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}

}
