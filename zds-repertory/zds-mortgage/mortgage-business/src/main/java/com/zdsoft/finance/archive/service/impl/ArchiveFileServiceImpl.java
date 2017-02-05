package com.zdsoft.finance.archive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.archive.entity.ArchiveFile;
import com.zdsoft.finance.archive.repository.ArchiveFileRepository;
import com.zdsoft.finance.archive.service.ArchiveFileService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;

@Service
public class ArchiveFileServiceImpl extends BaseServiceImpl<ArchiveFile, CustomRepository<ArchiveFile, String>> 
	implements ArchiveFileService{

	@Autowired
	private ArchiveFileRepository archiveFileRepository;
	
}
