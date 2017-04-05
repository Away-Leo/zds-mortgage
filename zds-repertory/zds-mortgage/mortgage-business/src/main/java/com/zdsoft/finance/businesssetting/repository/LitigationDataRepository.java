package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.LitigationData;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title LitigationDataRepository.java
 * @className LitigationDataRepository
 * @description 诉讼资料配置自定义操作库
 * @author LiaoGuoWei
 * @create 2017/2/14 15:56
 * @version V1.0
 **/
public interface LitigationDataRepository extends CustomRepository<LitigationData,String>{

    /**
     * @Title: findLitigationDataByCondition
     * @Description: 按照条件查询
     * @author liaoguowei
     * @param pageable 分页参数
     * @param litigationData 查询条件
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.LitigationData>
     * @throws
     */
    public default Page<LitigationData> findLitigationDataByCondition(Pageable pageable,LitigationData litigationData){
        Map<String,Object> qryCondition=new HashMap<String,Object>();
        StringBuffer hql=new StringBuffer(" from LitigationData l where l.isDeleted = false ");
        //类型
        if(ObjectHelper.isNotEmpty(litigationData.getFileTypeCode())){
            hql.append(" and l.fileTypeCode = :fileTypeCode ");
            qryCondition.put("fileTypeCode",litigationData.getFileTypeCode());
        }
        //资料备注
        if(ObjectHelper.isNotEmpty(litigationData.getRemark())){
            hql.append(" and l.remark like :remark escape'/' ");
            qryCondition.put("remark","%"+litigationData.getRemark().replaceAll("%","/%").replaceAll("_","/_")+"%");
        }
        //文件名称
        if(ObjectHelper.isNotEmpty(litigationData.getFileName())){
            hql.append(" and l.fileName like :fileName escape'/' ");
            qryCondition.put("fileName","%"+litigationData.getFileName().replaceAll("%","/%").replaceAll("_","/_")+"%");
        }
        hql.append(" order by l.createTime desc,l.updateTime desc ");
        return this.findByHqlPage(pageable,hql.toString(),qryCondition);
    }
}
