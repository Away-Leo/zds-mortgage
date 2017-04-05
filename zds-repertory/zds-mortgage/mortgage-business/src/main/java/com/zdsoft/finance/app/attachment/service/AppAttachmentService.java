package com.zdsoft.finance.app.attachment.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zdsoft.essential.dto.basedata.AttachmentDto;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppAttachmentService.java 
 * @ClassName: AppAttachmentService 
 * @Description: app附件操作
 * @author xj 
 * @date 2017年3月13日 上午9:16:47 
 * @version V1.0
 */
public interface AppAttachmentService {
	
	/**
	 * 
	 * @Title: saveAttachment 
	 * @Description: 附件批量上传
	 * @author xj 
	 * @param files 文件
	 * @param token 登录token
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentDto> saveAttachment(CommonsMultipartFile[] files, String token) throws Exception;
}
