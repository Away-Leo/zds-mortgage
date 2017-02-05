package com.zdsoft.finance.workbench.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.workbench.service.MyDraftService;
import com.zdsoft.finance.workbench.vo.MyDraftVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import com.zdsoft.framework.cra.client.service.CRA;
import com.zdsoft.framework.cra.dto.MenuDTO;

/**
 * 我的草稿箱控制器
 * @author longwei
 * @date 2017/01/13
 * @version 1.0
 */

@Controller
@RequestMapping("/myDraft")
public class MyDraftController extends BaseController {

	@Autowired
	private MyDraftService myDraftService;
	@Autowired
	private CRA CRA;
	
	/**
	 * 我的草稿入口
	 */
	@RequestMapping("/list")
	@UriKey(key = "com.zdsoft.finance.myDraft.list")
	@Menu(resource = "com.zdsoft.finance.myDraft.list", label = "我的草稿", group = "workbench", order = 5)
	public ModelAndView list() {
		return new ModelAndView("/workbench/my_draft_list");
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/getList")
	@UriKey(key = "com.zdsoft.finance.myDraft.getList")
	@ResponseBody
	public ResponseMsg getList(MyDraftVo myDraftVo,PageRequest pageable) {
		ResponseMsg msg=new ResponseMsg();
		MyDraft myDraft=myDraftVo.toPo();
		
		try {
			Page<MyDraft> page=myDraftService.findByPage(myDraft, pageable);
			List<MyDraftVo> myDraftVos=new ArrayList<MyDraftVo>();
			for(MyDraft queryMyDraft:page.getRecords()){
				myDraftVos.add(new MyDraftVo(queryMyDraft));
			}
			msg.setRows(myDraftVos);
			msg.setTotal(page.getTotalRows());
		} catch (BusinessException e) {
			logger.error("我的草稿，查询列表错误",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询列表错误");
		}
		return msg;
	}
	
	/**
	 * 修改
	 * @throws BusinessException 
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.myDraft.edit")
	public ModelAndView edit(String busiFormId) throws BusinessException {
		if(ObjectHelper.isEmpty(busiFormId)){
			logger.error("修改草稿id为空");
			throw new BusinessException("修改草稿id为空");
		}
		try {
			ProcessConfig processConfig = myDraftService.editBusiForm(busiFormId);
			//解析地址
			try {
				MenuDTO menuDTO = CRA.getMenu(StoreHelper.getApplication(), processConfig.getProcessKey());
				if(ObjectHelper.isEmpty(menuDTO)){
					logger.error("解析地址为空");
					throw new BusinessException("解析地址为空");
				}
				return new ModelAndView("redirect:"+menuDTO.getPath());
			} catch (Exception e) {
				logger.error("获取平台资源失败，不能解析地址",e);
				e.printStackTrace();
				throw new BusinessException("获取平台资源失败，不能解析地址");
			}

		} catch (BusinessException e) {
			logger.error("修改我的草稿错误",e);
			e.printStackTrace();
			throw new BusinessException("修改我的草稿错误");
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@UriKey(key = "com.zdsoft.finance.myDraft.delete")
	@ResponseBody
	public ResponseMsg delete(String busiFormId) {
		ResponseMsg msg=new ResponseMsg();
		if(ObjectHelper.isEmpty(busiFormId)){
			logger.error("删除草稿id为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("删除草稿id为空");
			return msg;
		}
		try {
			myDraftService.delete(busiFormId);
		} catch (BusinessException e) {
			logger.error("删除失败");
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("删除失败");
		}
		return msg;
	}
}
