package com.zdsoft.finance.projectfolder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.projectfolder.entity.ProjectInfo;
import com.zdsoft.finance.projectfolder.entity.ProjectInfoType;

public interface ProjectInfoTypeRepository extends CustomRepository<ProjectInfo, String>{

	@Query("select p from ProjectInfoType p where p.isDeleted = false and  p.classNm=:classNm and p.appCd=:appCd ")
	public ProjectInfoType findByClassNmAndAppCd(@Param("classNm")String classNm,@Param("appCd")String appCd);
	
	public List<ProjectInfoType> showProjectFolderGroup(@Param("projectCd")String projectCd,@Param("appCd")String appCd);
	
	public List<ProjectInfoType> showFixProjectFolderGroup(@Param("groupNm")String groupNm,@Param("appCd")String appCd);
	
	@Query("from ProjectInfoType p where  p.isDeleted = false and  p.groupNm=:groupNm and p.appCd=:appCd")
	public ProjectInfoType findProjectInfoTypeByGroupNm(@Param("groupNm")String groupNm,@Param("appCd")String appCd) ;
	
	public ProjectInfoType findProjectinfoTypeByProjectinfo(String businessId,String projectCd) throws Exception;
	
	public ProjectInfo findProjectinfoBybusinessIdAndProjectCd(String businessId,String projectCd) throws Exception;
}
