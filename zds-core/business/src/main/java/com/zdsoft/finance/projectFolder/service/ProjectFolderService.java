package com.zdsoft.finance.projectFolder.service;

import java.util.List;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.projectfolder.entity.ProjectInfo;
import com.zdsoft.finance.projectfolder.entity.ProjectInfoType;

public interface ProjectFolderService {

	/**
	 * 通过项目编号获取有项目资料类别(可包含固定项目资料类别)
	 * @param projectCd
	 * @return
	 */
	public List<ProjectInfoType> showProjectFolderGroup(String projectCd);
	/**
	 * 通过项目编号获取固定项目资料类别
	 * @param groupNm 
	 * @param projectCd
	 * @return
	 */
	public List<ProjectInfoType> showFixProjectFolderGroup(String groupNm);
	/**
	 * 通过项目编号获取项目资料
	 * @param projectCd
	 * @param groupNm
	 * @return
	 */
	public List<ProjectInfo> showProjectInfos(String projectCd, String groupNm);
	/**
	 * 通过项目资料ID获取项目资料
	 * @param id
	 * @return
	 */
	public ProjectInfo findProjectInfoById(String id);

    public ProjectInfo findProjectInfoByBusinessId(String businessId);
	/**
	 * 通过文件夹编号获取项目文件夹
	 * @param groupNm
	 * @return
	 */
	public ProjectInfoType findProjectInfoTypeByGroupNm(String groupNm);
	/**
	 * 新增项目文件信息接口
	 * @param classNm
	 * @param projectCd
	 * @param businessId
	 * @throws BusinessException
	 */
	public void createProjectInfo(String classNm, String projectCd,
			String businessId) throws BusinessException;
}
