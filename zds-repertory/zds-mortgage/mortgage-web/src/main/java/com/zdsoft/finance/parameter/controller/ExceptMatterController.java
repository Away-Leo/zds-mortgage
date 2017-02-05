package com.zdsoft.finance.parameter.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.parameter.entity.ExceptMatter;
import com.zdsoft.finance.parameter.entity.ExceptMatter;
import com.zdsoft.finance.parameter.service.ExceptMatterService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

import io.netty.util.internal.StringUtil;
@RequestMapping("/paramter")
@Controller
public class ExceptMatterController extends BaseController{
	@Autowired
	private ExceptMatterService exceptMatterService;
	@Autowired
	private CED ced;
	/**
	 * 特批事项管理初始化
	 * 
	 * @return 特批事项管理页面
	 */
	@RequestMapping("/initExceptMatter")
	@UriKey(key = "com.zdsoft.finance.parameter.initExceptMatter")
	@Menu(resource = "com.zdsoft.finance.parameter.initExceptMatter", label = "特批事项管理", group = "businessSetting", order = 1)
	public ModelAndView initExceptMatter() {
		return new ModelAndView("/parameter/exceptmatter_list");
	}
	
	/**
	 * 特批事项列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/exceptMatter")
	@UriKey(key = "com.zdsoft.finance.parameter.getExceptMatter")
	@ResponseBody
	public String getExceptMatter(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try
		{
			// 获取页面封装的查询参数
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			QueryObj obj = new QueryObj();
			obj.setObj("updateTime");
			obj.setElement("OB");
			obj.setOperator("OB");
			obj.setValue("DESC");
			queryObjs.add(obj);
			// 分页特批事项
			Page<ExceptMatter> exceptMatterPage = exceptMatterService.findByHqlConditions(pageable, queryObjs);
			List<ExceptMatter> costItemList=exceptMatterPage.getRecords();
			//如果有特批事项,则添加操作人
			if(costItemList.size()>0)
			{
				//存储操作人id的数组
				String[] emps=new String[costItemList.size()];
	 			for(int i=0;i<costItemList.size();i++)
				{
	 				emps[i]=costItemList.get(i).getUpdateBy();
				}
	 			//获取操作人对象列表
	 			List<EmpDto> empsDto=new ArrayList<EmpDto>();
				for(int i=0;i<emps.length;i++)
				{
					if(emps[i].equals("0"))
					{
						EmpDto d=new EmpDto();
						d.setEmpNm("系统管理员");
						empsDto.add(d);
					}
					else
					{
						empsDto.add(ced.findEmployeesByCodes(new String[]{emps[i]}).get(0));

					}
				}
				//添加将操作人名称
				for(int i=0;i<costItemList.size();i++)
				{
					costItemList.get(i).setEmpName(empsDto.get(i).getEmpNm());
				}
			}
			//msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(exceptMatterPage.getTotalRows());
			msg.setRows(exceptMatterPage.getRecords());
		}
		catch(Exception e)
		{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 更新或添加特批事项
	 * @param label
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/updateExceptMatter")
	@UriKey(key = "com.zdsoft.finance.parameter.updateExceptMatter")
	@ResponseBody
	public String updateExceptMatter(ExceptMatter exceptMatter, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			exceptMatter.setUpdateBy(ced.getLoginUser().getEmpCd());
			exceptMatter.setUpdateTime(new Timestamp(new Date().getTime()));
//			//如果是添加信息，则新建变编码
//			if(StringUtil.isNullOrEmpty(exceptMatter.getCode()))
//			{
//				exceptMatter.setCode("TP"+new Date().getTime());
//			}
			exceptMatterService.saveOrUpdateEntity(exceptMatter);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 获取一个特批事项的基本信息
	 * @param label
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getBasicMessage")
	@UriKey(key = "com.zdsoft.finance.parameter.getBasicMessage")
	@ResponseBody
	public String getBasicMessage(HttpServletRequest request, String jsoncallback, PageRequest pageable)
	{
		ResponseMsg msg = new ResponseMsg();
		try {
			Map<String,Object> result=new HashMap<String,Object>();
			result.put("code", "TP"+new Date().getTime());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 删除特批事项
	 * @param person
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/deleteExceptMatter")
	@UriKey(key = "com.zdsoft.finance.parameter.deleteExceptMatter")
	@ResponseBody
	public String deleteExceptMatter(ExceptMatter exceptMatter, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			exceptMatterService.deleteExceptMatter(exceptMatter.getId());
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

}
