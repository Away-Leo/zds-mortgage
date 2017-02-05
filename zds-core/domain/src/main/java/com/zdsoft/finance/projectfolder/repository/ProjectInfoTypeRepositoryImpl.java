package com.zdsoft.finance.projectfolder.repository;


import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import com.zdsoft.finance.projectfolder.entity.ProjectInfo;
import com.zdsoft.finance.projectfolder.entity.ProjectInfoType;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.util.ObjectHelper;

public class ProjectInfoTypeRepositoryImpl {

	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public ProjectInfoType findProjectinfoTypeByProjectinfo(String businessId,String projectCd) throws Exception{
		if (ObjectHelper.isNotEmpty(businessId)&&ObjectHelper.isNotEmpty(projectCd)){
            StringBuffer sql=new StringBuffer(" select distinct t.* from zf_project_info_type t where  t.isDeleted=:isDeleted  AND t.id=(" +
                    " select y.projectInfoType_id FROM zf_project_info y" +
                    " where y.isDeleted=:isDeleted  and y.businessId=:businessId  and y.projectCd=:projectCd )");
            Query query=em.createNativeQuery(sql.toString(),ProjectInfoType.class);
            query.setParameter("isDeleted", !BaseEntity.DELETED);
            query.setParameter("businessId", businessId);
            query.setParameter("projectCd", projectCd);
            List<ProjectInfoType> sourceData=query.getResultList();
            if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()==1){
                return sourceData.get(0);
            }else {
                throw new Exception("此数据未进行详情配置");
            }
        }else {
            throw new Exception("项目文件夹跳转详情出错，参数为空");
        }
	}
	
	public ProjectInfo findProjectinfoBybusinessidAndProjectCd(String businessId,String projectCd) throws Exception{
		if (ObjectHelper.isNotEmpty(businessId)&&ObjectHelper.isNotEmpty(projectCd)){
            StringBuffer sql=new StringBuffer("  select distinct y.* FROM zf_project_info y" +
                    " where y.isDeleted=:isDeleted  and y.businessId=:businessId  and y.projectCd=:projectCd ");
            Query query=em.createNativeQuery(sql.toString(),ProjectInfo.class);
            query.setParameter("isDeleted",!BaseEntity.DELETED);
            query.setParameter("businessId",businessId);
            query.setParameter("projectCd",projectCd);
            ProjectInfo data=(ProjectInfo)query.getSingleResult();
            return  data;
        }else {
            throw new Exception("项目文件夹跳转详情出错，参数为空");
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectInfoType> showProjectFolderGroup(String projectCd,String appCd){
            
            StringBuffer sql=new StringBuffer("SELECT DISTINCT new com.zdsoft.finance.projectfolder.entity.ProjectInfoType(info.projectInfoType.groupNm,info.projectInfoType.infoLabel,info.projectInfoType.displayOrder) FROM ProjectInfo info WHERE  info.projectInfoType.isDeleted = :isDeleted  and  info.projectCd = :projectCd AND info.projectInfoType.appCd = :appCd AND info.projectInfoType.isFix = :isFix order by info.projectInfoType.displayOrder asc");
            Query query=em.createQuery(sql.toString());
            query.setParameter("isDeleted",!BaseEntity.DELETED);
            query.setParameter("projectCd",projectCd);
            query.setParameter("appCd",appCd);
            query.setParameter("isFix",false);
            List<ProjectInfoType> data=query.getResultList();
            if(ObjectHelper.isEmpty(data)){
            	return Collections.emptyList();
            }
            return  data;
	}

	@SuppressWarnings("unchecked")
	public List<ProjectInfoType> showFixProjectFolderGroup(String groupNm,String appCd){
            StringBuffer sb = new StringBuffer();
    		sb.append("SELECT DISTINCT infoType FROM ProjectInfoType infoType WHERE  infoType.isDeleted = :isDeleted and  infoType.isFix = :isFix AND infoType.appCd = :appCd ");
    		if(ObjectHelper.isNotEmpty(groupNm)){
    			sb.append("and infoType.groupNm = :groupNm ");
    		}
    		sb.append("order by infoType.displayOrder asc ");
    		Query query = em.createQuery(sb.toString());
    		query.setParameter("isDeleted",!BaseEntity.DELETED);
    		query.setParameter("appCd", appCd);
    		query.setParameter("isFix", new Boolean(true));
    		if(ObjectHelper.isNotEmpty(groupNm)){
    			query.setParameter("groupNm", groupNm);
    		}
    		List<ProjectInfoType> data=query.getResultList();
    		if(ObjectHelper.isEmpty(data)){
            	return Collections.emptyList();
            }
    		return data;
	}

}
