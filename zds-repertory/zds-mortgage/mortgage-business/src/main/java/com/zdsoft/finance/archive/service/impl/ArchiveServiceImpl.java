package com.zdsoft.finance.archive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.archive.entity.Archive;
import com.zdsoft.finance.archive.repository.ArchiveRepository;
import com.zdsoft.finance.archive.service.ArchiveService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 档案管理
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-21
 */
@Service
public class ArchiveServiceImpl extends BaseServiceImpl<Archive, CustomRepository<Archive, String>> 
	implements ArchiveService {

	@Autowired
	private ArchiveRepository archiveRepository;
	
}
