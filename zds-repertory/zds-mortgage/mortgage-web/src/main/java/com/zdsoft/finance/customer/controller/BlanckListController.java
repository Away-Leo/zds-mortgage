package com.zdsoft.finance.customer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BlanckList;
import com.zdsoft.finance.customer.service.BlanckListService;
import com.zdsoft.finance.customer.vo.BlanckListVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 黑名单
 * @author zhangchao
 *	2016/12/22
 */
@Controller
@RequestMapping("blanckList")
public class BlanckListController extends BaseController {

	@Autowired
	private BlanckListService blanckListService;
	
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 客户管理黑名单菜单注册
	 * @return
	 */
	@RequestMapping("/initBlanckList")
	@UriKey(key = "com.zdsoft.finance.blanckList.initBlanckList")
	@Menu(resource = "com.zdsoft.finance.blanckList.initBlanckList", label = "黑名单管理", group = "customer", order = 2)
	public ModelAndView initBlanckList() {
		String EmpCd = null;
		try {
			EmpCd = CED.getLoginUser().getEmpCd();
		} catch (Exception e) {
			logger.error("获取当前登录人失败", e);
			e.printStackTrace();
		}
		Map<String, Object> optional = new HashMap<String, Object>();
		optional.put("EmpCd", EmpCd);
		return new ModelAndView("/customer/blanck_list" , optional);
	}
	
	/**
	 * 黑名单查询列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getBlanckList")
	@UriKey(key = "com.zdsoft.finance.blanckList.getBlanckList")
	@ResponseBody
	public String getBlanckList(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数 
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 分页黑名单
		Page<BlanckList> blanckListPage = blanckListService.findByHqlConditions(pageable, queryObjs);
		List<BlanckListVo> blanckListVos = new ArrayList<BlanckListVo>();
		for (BlanckList blanckList : blanckListPage.getRecords()) {
			BlanckListVo blanckListVo = new BlanckListVo(blanckList);
			blanckListVo = SimpleCodeToName(blanckListVo);
			blanckListVos.add(blanckListVo);
		}
		
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(blanckListPage.getTotalRows());
		msg.setRows(blanckListVos);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保存黑名单
	 * @param blanckListVo 黑名单对象
	 * @return
	 */
	@RequestMapping("/saveBlanckList")
	@UriKey(key = "com.zdsoft.finance.blanckList.saveBlanckList")
	@ResponseBody
	public ResponseMsg saveBlanckList(BlanckListVo blanckListVo) {
		BlanckList blanckList = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> blanckListMap = new HashMap<String, Object>();

		// 将Vo转成Po
		blanckList = blanckListVo.toPO();
		
		BlanckListVo newBlanckListVo = null;
		BlanckList newBlanckList = new BlanckList();

		// 执行保存
		try {
			if(ObjectHelper.isNotEmpty(blanckList.getId())){
				Date date = new Date();
				blanckList.setCreateTime(date);
				blanckList.setCreateBy(CED.getLoginUser().getEmpCd());
				blanckList.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newBlanckList = blanckListService.findOne(blanckList.getId());
				newBlanckList = blanckListService.updateEntity(blanckList);
			}else{
				Date date = new Date();
				blanckList.setCreateTime(date);
				blanckList.setCreateBy(CED.getLoginUser().getEmpCd());
				blanckList.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newBlanckList = blanckListService.saveEntity(blanckList);
			}
			newBlanckListVo = new BlanckListVo(newBlanckList);
			blanckListMap.put("newBlanckListVo", newBlanckListVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(blanckListMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("blanckList保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 删除黑名单信息
	 * @param id 黑名单id
	 * @return
	 */
	@RequestMapping("/delBlanckList")
	@UriKey(key = "com.zdsoft.finance.blanckList.delBlanckList")
	@ResponseBody
	public ResponseMsg delBlanckList(String id) {
		ResponseMsg msg = new ResponseMsg();

		// 删除
		try {
			BlanckList blanckList = blanckListService.findOne(id);
			blanckListService.logicDelete(blanckList);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("blanckList删除失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 黑名单数据导入
	 * @param multipartRequest 页面传入的数据
	 * @param request
	 * @param jsoncallback
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/intoBlanckList")
	@UriKey(key = "com.zdsoft.finance.blanckList.intoBlanckList")
	@ResponseBody
	public ResponseMsg intoBlanckList(MultipartHttpServletRequest multipartRequest,HttpServletRequest request, String jsoncallback) throws IOException{
		logger.debug("upload---------附件开始上传------------------------>start");
		Map<String, Object> result = new HashMap<String, Object>();
		ResponseMsg msg = new ResponseMsg();
		try {
			logger.error("附件开始上传-------" + StoreHelper.getUserToken());
			String zdsUserToken = request.getParameter("zdsUserToken");
			if (StoreHelper.getUserToken() == null && ObjectHelper.isNotEmpty(zdsUserToken)) {
				StoreHelper.setUserToken(zdsUserToken);
			}

			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("Filedata");
			if (null == file || file.isEmpty()) {
				result.put("success", false);
				result.put("msg", "上传文件不能为空!");
				logger.debug("上传附件失败，附件不能为空!");
			} else {
				if(ObjectHelper.isEmpty(file)){
					msg.setResultStatus(ResponseMsg.APP_ERROR);
					msg.setMsg("上传附件为空");
				}else{
					//根据指定的文件输入流导入Excel从而产生Workbook对象  
					Workbook wb0 = new HSSFWorkbook(file.getInputStream());
					//获取Excel文档中的第一个表单  
					Sheet sht0 = wb0.getSheetAt(0);
					//对Sheet中的每一行进行迭代  
					for (Row r : sht0) {
						//如果当前行的行号（从0开始）未达到2（第三行）则从新循环  
						if(r.getRowNum()<1){
							continue;
						}
						//创建实体类  
						BlanckListVo info=new BlanckListVo();
						//取出当前行第1个单元格数据，并封装在info实体stuName属性上  
						info.setBlackName(r.getCell(0).getStringCellValue());
						info.setCredentiaType(r.getCell(1).getStringCellValue());
						info.setCredentialNo(r.getCell(2).getStringCellValue());
						info.setReasonType(r.getCell(3).getStringCellValue());
						info.setSource(r.getCell(4).getStringCellValue());
						if(ObjectHelper.isNotEmpty(r.getCell(5).getDateCellValue())){
							info.setStartDate(DateHelper.stringDateToLong(DateHelper.dateToString(r.getCell(5).getDateCellValue(), "yyyy/MM/dd"), "yyyy/MM/dd", "yyyyMMdd"));
						}
						if(ObjectHelper.isNotEmpty(r.getCell(6).getDateCellValue())){
							info.setEndDate(DateHelper.stringDateToLong(DateHelper.dateToString(r.getCell(6).getDateCellValue(), "yyyy/MM/dd"), "yyyy/MM/dd", "yyyyMMdd"));
						}
						BlanckList blanckList = info.toPO();
						try {
							Date date = new Date();
							blanckList.setCreateTime(date);
							blanckList.setCreateBy(CED.getLoginUser().getEmpCd());
							blanckList.setCreateOrgCd(CED.getLoginUser().getOrgCd());
							blanckListService.saveEntity(blanckList);
							msg.setResultStatus(ResponseMsg.SUCCESS);
						} catch (BusinessException e) {
							e.printStackTrace();
							logger.error("blanckList导入失败", e);
							msg.setResultStatus(ResponseMsg.APP_ERROR);
						}
					}
					if(msg.getResultStatus() == ResponseMsg.SUCCESS){
						msg.setMsg("导入成功");
					}
					if(msg.getResultStatus() == ResponseMsg.APP_ERROR){
						msg.setMsg("导入失败");
					}
				}
			}
		} catch (Exception e) {
			logger.error("附件上传异常", e);
			result.put("success", false);
		}
		logger.debug("upload---------附件上传处理完成------------------------>end");
		return msg;
}
	
	/**
	 * SimpleCode值转换
	 * @param latestCustomerVo
	 * @return
	 */
	public BlanckListVo SimpleCodeToName(BlanckListVo blanckListVo){
		try {
			blanckListVo.setCredentiaType(CED.loadSimpleCodeNameByFullCode(blanckListVo.getCredentiaType()));
			blanckListVo.setReasonType(CED.loadSimpleCodeNameByFullCode(blanckListVo.getReasonType()));
			blanckListVo.setSource(CED.loadSimpleCodeNameByFullCode(blanckListVo.getSource()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blanckListVo;
	}
}
