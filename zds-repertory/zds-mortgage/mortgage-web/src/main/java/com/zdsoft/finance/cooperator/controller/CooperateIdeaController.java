package com.zdsoft.finance.cooperator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperateIdea;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperateIdeaService;
import com.zdsoft.finance.cooperator.vo.AttachmentVo;
import com.zdsoft.finance.cooperator.vo.CooperateIdeaVo;
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
 * @Title: CooperateIdeaController.java
 * @ClassName: CooperateIdeaController
 * @Description: 资方合作协议Controller
 * @author liuwei
 * @date 2017年3月8日 上午10:15:21
 * @version V1.0
 */
@Controller
@RequestMapping("/cooperateIdea")
public class CooperateIdeaController extends BaseController {

	@Autowired
	CooperateIdeaService cooperateIdeaService;

	@Autowired
	CED CED;
	@Autowired
	CapitalistService capitalistService;

	/**
	 * 
	 * @Title: initCooperateIdea
	 * @Description: 合作协议列表
	 * @author liuwei
	 * @param capitalistId
	 *            资方id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/initCooperateIdea")
	@UriKey(key = "com.zdsoft.finance.cooperator.idea.initCooperateIdea")
	public ModelAndView initCooperateIdea(String capitalistId, String operationType) {
		ModelAndView model = new ModelAndView("/cooperator/cooperateIdea_list");
		model.addObject("capitalistId", capitalistId);
		model.addObject("operationType", operationType);
		return model;
	}

	/**
	 * 
	 * @Title: dialog
	 * @Description: 合作协议dialog
	 * @author liuwei
	 * @param capitalistId
	 *            资方id
	 * @param id
	 *            合作协议id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/dialog")
	@UriKey(key = "com.zdsoft.finance.cooperator.idea.dialog")
	public ModelAndView dialog(String capitalistId, String id, String operationType) {
		ModelAndView modelAndView = new ModelAndView("/cooperator/cooperator_idea_dialog");

		try {
			if ("mod".equals(operationType)) {
				CooperateIdea idea = cooperateIdeaService.findOne(id);
				CooperateIdeaVo vo = new CooperateIdeaVo(idea);
				List<AttachmentVo> attrs = new ArrayList<>();
				List<AttachmentDto> dto = new ArrayList<>();
				if (vo != null && StringUtils.isNotEmpty(vo.getAttachmentId()) && vo.getAttachmentId().contains(",")) {
					String[] att = vo.getAttachmentId().split(",");
					dto = getListAttr(att);

				} else {
					if (!StringUtils.isEmpty(vo.getAttachmentId())) {
						AttachmentDto attr = CED.findAttachmentById(vo.getAttachmentId());
						dto.add(attr);
					}
				}
				for (AttachmentDto adto : dto) {
					AttachmentVo attrvo = new AttachmentVo();
					attrvo.setId(adto.getId());
					attrvo.setFileLabel(adto.getFileLabel());
					attrvo.setFileNm(adto.getFileNm());
					attrvo.setFilePath(adto.getFilePath());
					attrvo.setFileSize(adto.getFileSize());
					attrvo.setFileType(adto.getFileType());
					attrs.add(attrvo);
				}
				modelAndView.addObject("infoVo", vo);
				modelAndView.addObject("attrs", attrs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询合作协议信息失败", e);
		}
		modelAndView.addObject("capitalistId", capitalistId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}

	/**
	 * 
	 * @Title: getCooperateIdea
	 * @Description: 列表数据展示
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 处理消息msg json
	 */
	@RequestMapping("/getCooperateIdea")
	@UriKey(key = "com.zdsoft.finance.cooperator.idea.getCooperateIdea")
	@ResponseBody
	public String getCooperateIdea(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询会议
		Page<CooperateIdea> infos = cooperateIdeaService.findByHqlConditions(pageable, queryObjs);
		List<CooperateIdea> list = infos.getRecords();
		List<CooperateIdeaVo> listVo = new ArrayList<CooperateIdeaVo>();
		ResponseMsg msg = new ResponseMsg();
		try {
			for (CooperateIdea info : list) {
				CooperateIdeaVo vo = new CooperateIdeaVo(info);
				if (info != null && org.apache.commons.lang3.StringUtils.isNotEmpty(info.getAttachmentId())
						&& info.getAttachmentId().contains(",")) {
					String[] att = info.getAttachmentId().split(",");
					String str = getAttr(att);
					vo.setAttachName(str);
				} else {
					if (!StringUtils.isEmpty(info.getAttachmentId())) {
						AttachmentDto dto = CED.findAttachmentById(info.getAttachmentId());
						vo.setAttachName(dto.getFileLabel());
					}
				}
				listVo.add(vo);
			}

			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(infos.getTotalRows());
			msg.setRows(listVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取附件信息失败", e);
			msg.setMsg("列表查询失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: getListAttr
	 * @Description: 附件文件操作
	 * @author liuwei
	 * @param args
	 *            附件ids
	 * @return 附件集合
	 * @throws Exception
	 */
	public List<AttachmentDto> getListAttr(String[] args) throws Exception {
		List<String> list = new ArrayList<>();
		for (String str : args) {
			list.add(str);
		}
		List<AttachmentDto> dto = CED.findAttachmentByIds(list);
		return dto;
	}

	/**
	 * 
	 * @Title: getAttr
	 * @Description: 附件显示操作
	 * @author liuwei
	 * @param args
	 *            附件ids
	 * @return 附件名称
	 * @throws Exception
	 */
	public String getAttr(String[] args) throws Exception {
		List<String> list = new ArrayList<>();
		for (String str : args) {
			list.add(str);
		}
		List<AttachmentDto> dto = CED.findAttachmentByIds(list);
		String str = "";
		for (AttachmentDto attachmentDto : dto) {
			str += attachmentDto.getFileLabel() + ",";
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 更新合作协议
	 * @author liuwei
	 * @param infoVo
	 *            合作协议信息
	 * @return 处理消息Json
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.idea.save")
	@ResponseBody
	public String save(CooperateIdeaVo infoVo) {
		ResponseMsg msg = new ResponseMsg();

		try {
			// 判断合作协议是否存在
			if (!ObjectHelper.isEmpty(infoVo)) {
				// 判断合作协议id是否传入
				if (ObjectHelper.isEmpty(infoVo.getId())) {

					// 没有则新增
					Capitalist ter = capitalistService.findOne(infoVo.getCapitalistId());
					CooperateIdea info = infoVo.toPO();
					info.setCapitalist(ter);
					cooperateIdeaService.saveEntity(info);
					msg.setMsg("保存成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				} else {
					// 存在则执行修改
					CooperateIdea info = cooperateIdeaService.findOne(infoVo.getId());
					info.setAgreementName(infoVo.getAgreementName());
					info.setAttachmentId(infoVo.getAttachmentId());// 附件
					Capitalist ter = capitalistService.findOne(infoVo.getCapitalistId());
					info.setCapitalist(ter);
					cooperateIdeaService.updateEntity(info);
					msg.setMsg("更新成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				}

			} else {
				msg.setMsg("数据为空");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存合作协议失败", e);
			msg.setMsg("保存合作协议失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 逻辑删除合作协议
	 * @author liuwei
	 * @param jsoncallback
	 * @param id
	 *            合作协议id
	 * @return 处理消息msg json
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.idea.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
		ResponseMsg msg = new ResponseMsg();
		try {

			// 逻辑删除合作协议
			cooperateIdeaService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
}
