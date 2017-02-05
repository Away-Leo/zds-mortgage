
package com.zdsoft.finance.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.projectfolder.entity.ProjectInfo;
import com.zdsoft.finance.projectfolder.repository.ProjectInfoRepository;
import com.zdsoft.framework.core.common.util.ObjectHelper;


@Service
public class PorjInfoAdvisorService {

	@Autowired
	ProjectInfoRepository projectInfoRepository;
	
	
	/**
	 * 注册事项
	 * @param pro
	 */
	@Transactional
	public ProjectInfo saveProjectInfo(ProjectInfo pro){		
		ProjectInfo projectInfoRecord = projectInfoRepository
				.findByBusiIdAndProTypeId(pro.getBusinessId(),pro.getProjectInfoType().getId());
		
		if (ObjectHelper.isNotEmpty(projectInfoRecord))
			return null;			
		else
			return projectInfoRepository.saveEntity(pro);
	}
	
	/**
	 * 删 除事项
	 * @param businessId
	 */
	@Transactional
	public void deleteProjectInfo(String businessId){
		if(ObjectHelper.isNotEmpty(businessId)){
			ProjectInfo projectInfo = projectInfoRepository.findByBusinessId(businessId);
			if(projectInfo!=null){
				projectInfoRepository.delete(projectInfo);
			}
		}
	}


}
