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
import com.zdsoft.finance.common.exception.BusinessException;
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
 * 资方合作协议
 * @author Hisa
 *
 */
@Controller
@RequestMapping("/cooperateIdea")
public class CooperateIdeaController  extends BaseController{
	
	@Autowired
	CooperateIdeaService cooperateIdeaService;
	
	@Autowired
	CED CED;
	@Autowired
	CapitalistService capitalistService;
	
	/**
	 * 合作方联系人信息列表
	 * 
	 * @return
	 */
	@RequestMapping("/initCooperateIdea")
	@UriKey(key = "com.zdsoft.finance.cooperator.idea.initCooperateIdea")
	public ModelAndView initCooperateIdea(String capitalistId,String operationType) {
		ModelAndView model = new ModelAndView("/cooperator/cooperateIdea_list");
		model.addObject("capitalistId", capitalistId);
		model.addObject("operationType", operationType);
		return model;
	}

	@RequestMapping("/dialog")
	@UriKey(key="com.zdsoft.finance.cooperator.idea.dialog")
	public ModelAndView dialog(String capitalistId,String id,String operationType) throws Exception{
		ModelAndView modelAndView=new ModelAndView("/cooperator/cooperator_idea_dialog");
		if("mod".equals(operationType)){
			CooperateIdea idea = cooperateIdeaService.findOne(id);
			CooperateIdeaVo vo = new CooperateIdeaVo(idea);
			List<AttachmentVo> attrs = new ArrayList<>();
			List<AttachmentDto> dto = new ArrayList<>();
			if(vo != null && StringUtils.isNotEmpty(vo.getAttachmentId()) &&  vo.getAttachmentId().contains(",")){
				String[] att = vo.getAttachmentId().split(",");
				dto = getListAttr(att);
				
			}else{
				if(!StringUtils.isEmpty(vo.getAttachmentId())){
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
		modelAndView.addObject("capitalistId", capitalistId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}
	/**
	 * 列表数据展示
	 * 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getCooperateIdea")
	@UriKey(key = "com.zdsoft.finance.cooperator.idea.getCooperateIdea")
	@ResponseBody
	public String getCooperateIdea(HttpServletRequest request, String jsoncallback, PageRequest pageable) throws Exception {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询会议
		Page<CooperateIdea> infos = cooperateIdeaService.findByHqlConditions(pageable, queryObjs);
		List<CooperateIdea> list = infos.getRecords();
		List<CooperateIdeaVo> listVo = new ArrayList<CooperateIdeaVo>();
		for (CooperateIdea info : list) {
			CooperateIdeaVo vo = new CooperateIdeaVo(info);
			if(info != null && org.apache.commons.lang3.StringUtils.isNotEmpty(info.getAttachmentId()) && info.getAttachmentId().contains(",")){
				String[] att = info.getAttachmentId().split(",");
				String str = getAttr(att);
				vo.setAttachName(str);
			}else{
				if(!StringUtils.isEmpty(info.getAttachmentId())){
					AttachmentDto dto = CED.findAttachmentById(info.getAttachmentId());
					vo.setAttachName(dto.getFileLabel());
				}
			}
			listVo.add(vo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(infos.getTotalRows());
		msg.setRows(listVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 附件文件操作
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentDto> getListAttr(String[] args) throws Exception{
		List<String> list = new ArrayList<>();
		for (String str : args) {
			list.add(str);
		}
		List<AttachmentDto> dto = CED.findAttachmentByIds(list);
		return dto;
	}
	/**
	 * 附件显示操作
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public String getAttr(String[] args) throws Exception{
		List<String> list = new ArrayList<>();
		for (String str : args) {
			list.add(str);
		}
		List<AttachmentDto> dto = CED.findAttachmentByIds(list);
		String str = "";
		for (AttachmentDto attachmentDto : dto) {
			str+=attachmentDto.getFileLabel()+",";
		}
		str = str.substring(0,str.length()-1);
		return str;
	}

	/**
	 * 更新联系人
	 * 
	 * @param jsoncallback
	 * @param infoVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.idea.save")
	@ResponseBody
	public String save(CooperateIdeaVo infoVo) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		if (!ObjectHelper.isEmpty(infoVo)) {
			if (ObjectHelper.isEmpty(infoVo.getId())) {
				Capitalist ter = capitalistService.findOne(infoVo.getCapitalistId());
				CooperateIdea info = infoVo.toPO();
				info.setCapitalist(ter);
				cooperateIdeaService.saveEntity(info);
				msg.setMsg("保存成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			} else {
				CooperateIdea info = cooperateIdeaService.findOne(infoVo.getId());
				info.setAgreementName(infoVo.getAgreementName());
				info.setAttachmentId(infoVo.getAttachmentId());//附件
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
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 删除
	 * 
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.idea.del")
	@ResponseBody
	public String del(String jsoncallback, String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
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
