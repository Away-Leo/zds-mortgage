package com.zdsoft.finance.archive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdsoft.finance.archive.service.ArchiveFileService;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 档案清单
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-21
 */
@Controller
@RequestMapping("archiveFile")
public class ArchiveFileController extends BaseController{

	@Autowired
	private ArchiveFileService archiveFileService;
	
}
