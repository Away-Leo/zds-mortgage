package com.zdsoft.finance.app.attachment.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zdsoft.essential.client.controller.UploaderController;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.app.attachment.service.AppAttachmentService;
import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.cra.client.service.CRA;
import com.zdsoft.framework.cra.dto.AccountDTO;
@Service("appAttachmentService")
public class AppAttachmentServiceImpl implements AppAttachmentService {
	private static final String defaultUploadFolder = "/mnt/upload/";
	private static Map<String, String> attachmentMeta = new HashMap<String, String>();
	@Log
	private Logger logger;
	@Autowired
	private CRA CRA;
	@Autowired
	private CED CED;
	/**
	 * 初始化附件文件类型
	 */
	static {
		attachmentMeta.put("doc", "Word");
		attachmentMeta.put("docx", "Word");
		attachmentMeta.put("xls", "Excel");
		attachmentMeta.put("xlsx", "Excel");
		attachmentMeta.put("csv", "Excel");
		attachmentMeta.put("ppt", "Power Point");
		attachmentMeta.put("txt", "text");
		attachmentMeta.put("jpg", "image");
		attachmentMeta.put("png", "image");
		attachmentMeta.put("gif", "image");
		attachmentMeta.put("bmp", "image");
		attachmentMeta.put("jpeg", "image");

	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public List<AttachmentDto> saveAttachment(CommonsMultipartFile[] files,String token) throws Exception {
		List<AttachmentDto> result = new ArrayList<AttachmentDto>();
	/*	List<MultipartFile> files = multipartRequest.getFiles("file");*/
		for (MultipartFile file:files) {
			/*String key = it.next();
			MultipartFile file = multipartRequest.getFile(key);*/
			if (file != null && !file.isEmpty()) {
				AttachmentDto dto = new AttachmentDto();
				dto.setFileLabel(file.getOriginalFilename());
				logger.info("fileName:", file.getOriginalFilename());
				dto.setFileNm( UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
				// 文件后辍名
				String file_ = FilenameUtils.getExtension(dto.getFileLabel());
				// 文件类型
				String fileType = "";
				// 判断文件是否存在后辍名
				if (ObjectHelper.isEmpty(file_)) {
					fileType = "其他";
				} else {
					// 将 后辍名转换成小写进行文件类型匹配
					fileType = attachmentMeta.get(file_.toLowerCase());
					if (ObjectHelper.isEmpty(fileType)) {
						fileType = "其他";
					}
				}
				dto.setFileType(fileType);
				AccountDTO account = CRA.getAccount(token);
				EmpDto loginUser = CED.getLoginUser(account.getId());
				//创建人
				dto.setOwner(loginUser.getEmpCd());
				String savePath = UploaderController.getUploadFolder();
				logger.debug("uploadFolder:[{}]", savePath);
				if (savePath == null) {
					UploaderController.setUploadFolder(defaultUploadFolder);
					logger.warn("Attention!!!!!!,uploadFolder is not be setted,reset with the default path:[{}]",
							defaultUploadFolder);
				}
				DateFormat format = new SimpleDateFormat("yyyy");
				savePath = FilenameUtils.concat(UploaderController.getUploadFolder()+File.separator+format.format(new Date()),
						dto.getFileNm());
				dto.setFilePath(savePath);
				logger.info("savePath:", savePath);
				// 上传默认为临时文件,需要在业务逻辑转为正式文件
				dto.setIsTemp(true);
				File outFile = new File(dto.getFilePath());
				FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
				dto = CED.saveAttachment(dto);
				dto.setFilePath(ConstantParameter.getAppDownloadUrl()+dto.getFilePath());
				result.add(dto);
			}
		}
		return result;
	}

}
