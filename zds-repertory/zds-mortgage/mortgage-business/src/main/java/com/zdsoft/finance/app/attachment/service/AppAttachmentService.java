package com.zdsoft.finance.app.attachment.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zdsoft.essential.dto.basedata.AttachmentDto;

/**
 * app附件操作
 * @author xj
 *
 */
public interface AppAttachmentService {
	/**
	 * 附件批量上传
	 * @param token 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<AttachmentDto> saveAttachment(CommonsMultipartFile[] files, String token) throws Exception;
}
