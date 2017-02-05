package com.zdsoft.finance.projectFolder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.projectFolder.service.ProjectFolderService;
import com.zdsoft.finance.projectfolder.entity.ProjectInfo;
import com.zdsoft.finance.projectfolder.entity.ProjectInfoType;
import com.zdsoft.finance.projectfolder.repository.ProjectInfoRepository;
import com.zdsoft.finance.projectfolder.repository.ProjectInfoTypeRepository;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;

@Service("projectFolderService")
public class ProjectFolderServiceImpl implements ProjectFolderService{
	/**
	 * 日志
	 */
	Logger logger = LoggerFactory.getLogger(ProjectFolderServiceImpl.class);
	
	@Autowired
	private ProjectInfoTypeRepository projectInfoTypeRepository;
	@Autowired
	private ProjectInfoRepository projectInfoRepository;

	@Transactional
	@Override
	public List<ProjectInfoType> showProjectFolderGroup(String projectCd) {
		return projectInfoTypeRepository.showProjectFolderGroup(projectCd,StoreHelper.getApplication());
	}
	@Transactional
	@Override
	public List<ProjectInfoType> showFixProjectFolderGroup(String groupNm) {
		return projectInfoTypeRepository.showFixProjectFolderGroup(groupNm,StoreHelper.getApplication());
	}

	@Transactional
	@Override
	public List<ProjectInfo> showProjectInfos(String projectCd,
			String groupNm) {
		List<ProjectInfo> returnData=new ArrayList<ProjectInfo>();//根据groupNm取得数据，如果为合同申请单 则选择最新的一条数据
		List<ProjectInfo> sourceData=projectInfoRepository.showProjectInfos(projectCd,StoreHelper.getApplication(), groupNm);
		//return projectInfoDao.showProjectInfos(projectCd,groupNm);
		if (ObjectHelper.isNotEmpty(groupNm)&&groupNm.trim().equalsIgnoreCase("HTSQD")&&ObjectHelper.isNotEmpty(sourceData))
		{
			returnData.add(sourceData.get(0));
		}
		else {
			returnData=sourceData;
		}
		return returnData;
	}
	@Transactional
	@Override
	public ProjectInfo findProjectInfoById(String id) {
		return projectInfoRepository.findOne(id);
	}

    @Override
    public ProjectInfo findProjectInfoByBusinessId(String businessId) {
        return projectInfoRepository.findByBusinessId(businessId);
    }

    @Override
	@Transactional
	public ProjectInfoType findProjectInfoTypeByGroupNm(String groupNm) {
		return projectInfoTypeRepository.findProjectInfoTypeByGroupNm(groupNm,StoreHelper.getApplication());
	}
	@Override
	@Transactional
	public void createProjectInfo(String classNm, String projectCd,
			String businessId) throws BusinessException {

		// 根据资料实体类名取得项目资料类别
		ProjectInfoType projectInfoType =  
				projectInfoTypeRepository
				.findByClassNmAndAppCd(classNm,StoreHelper.getApplication());

			
		if (ObjectHelper.isEmpty(projectInfoType)) {
			throw new BusinessException("projectInfoType","项目资料类别为空");
		}		
						
		ProjectInfo proR = new ProjectInfo();		
		proR.setBusinessId(businessId);
		proR.setProjectCd(projectCd);
		proR.setProjectInfoType(projectInfoType);
		this.projectInfoRepository.saveEntity(proR);
		logger.info("添加项目资料信息成功:id="+proR.getId()+",resId="+businessId + ",resProjectCd="+projectCd);
		
	}
	
	
}
