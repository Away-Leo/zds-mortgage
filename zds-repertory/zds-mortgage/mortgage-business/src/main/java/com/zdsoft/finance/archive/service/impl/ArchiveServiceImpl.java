package com.zdsoft.finance.archive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.archive.entity.Archive;
import com.zdsoft.finance.archive.repository.ArchiveRepository;
import com.zdsoft.finance.archive.service.ArchiveService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ArchiveServiceImpl.java 
 * @ClassName: ArchiveServiceImpl 
 * @Description: 档案清单
 * @author gufeng 
 * @date 2017年3月13日 下午4:48:50 
 * @version V1.0
 */
@Service
public class ArchiveServiceImpl extends BaseServiceImpl<Archive, CustomRepository<Archive, String>> 
	implements ArchiveService {

	@SuppressWarnings("unused")
	@Autowired
	private ArchiveRepository archiveRepository;
	
}
