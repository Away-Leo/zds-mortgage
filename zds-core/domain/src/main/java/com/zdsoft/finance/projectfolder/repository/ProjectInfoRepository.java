package com.zdsoft.finance.projectfolder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.projectfolder.entity.ProjectInfo;

public interface ProjectInfoRepository extends CustomRepository<ProjectInfo, String>{

	@Query("select t from ProjectInfo t where t.isDeleted=false and t.businessId=:businessId and t.projectInfoType.id=:typeId")		
	public ProjectInfo findByBusiIdAndProTypeId(@Param("businessId")String businessId,@Param("typeId")String typeId);

	@Query("select t from ProjectInfo t where t.isDeleted=false and t.businessId=:businessId")		
	public ProjectInfo findByBusinessId(@Param("businessId")String businessId);
	
	@Query("select distinct info from ProjectInfo info where  info.isDeleted = false and info.projectCd = :projectCd and info.projectInfoType.appCd = :appCd and info.projectInfoType.groupNm = :groupNm order by info.createTime desc")		
	public List<ProjectInfo> showProjectInfos(@Param("projectCd")String projectCd,@Param("appCd")String appCd,@Param("groupNm")String groupNm);

	
}
