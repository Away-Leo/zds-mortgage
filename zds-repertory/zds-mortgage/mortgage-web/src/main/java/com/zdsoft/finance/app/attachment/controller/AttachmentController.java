package com.zdsoft.finance.app.attachment.controller;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.app.attachment.service.AppAttachmentService;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StringHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;
/**
 * app附件上传（支持批量上传）
 * @author xj
 *
 */
@Controller
@RequestMapping("/server/attachment")
public class AttachmentController extends BaseController {
	@Autowired
	private AppAttachmentService appAttachmentService;
	@Autowired
	private CED attachmentService;
	/**
	 * 批量上传文件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String upload(@RequestParam("fileDatas") CommonsMultipartFile[] files,String token){
		try {
			//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<AttachmentDto> attachmentDtos = null;
			if(ObjectHelper.isEmpty(files) || files.length==0 || ObjectHelper.isEmpty(token)){
				logger.error("app参数错误或不完整");
				return AppServerUtil.buildJsonMessage(AppStatus.ArgsError,"参数错误或不完整！"); 
			}else{
				attachmentDtos = appAttachmentService.saveAttachment(files,token);
				return AppServerUtil.buildJsonObject(AppStatus.Succeed,attachmentDtos);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("app上传附件:", e);
			return AppServerUtil.buildJsonMessage(AppStatus.ArgsError,"上传附件异常！"); 
		}
	}
	/**
	 * 下载
	 * @param id
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/download")
	public void download(@RequestParam("attachmentId") String id, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		AttachmentDto attachment = attachmentService.findAttachmentById(id);
		File file = new File(attachment.getFilePath());
		InputStream input = FileUtils.openInputStream(file);
		byte[] data = IOUtils.toByteArray(input);
		String agent = request.getHeader("USER-AGENT");
		String fileName = "";// StringHelper.decodeFromUtfToCode(attachment.getFileLabel());//URLEncoder.encode(file.getName(),
								// "UTF-8");
		// 判断浏览器是ie 还是firefox
		if (null != agent && -1 != agent.indexOf("MSIE")) {
			fileName = URLEncoder.encode(attachment.getFileLabel(), "UTF-8");
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
			fileName = StringHelper.decodeFromUtfToCode(attachment.getFileLabel());
		} else {
			fileName = attachment.getFileLabel();
		}
		response.reset();
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf-8"), "utf-8")); // 转码之后下载的文件不会出现中文乱码
		// response.setHeader("Content-Disposition", "attachment;filename=\"" +
		// fileName + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data, response.getOutputStream());
		IOUtils.closeQuietly(input);
	}

}
