package com.zdsoft.finance.casemanage.material.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAttaLog;
import com.zdsoft.finance.casemanage.material.repository.CaseMaterialListAttaLogRepository;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListAttaLogService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListAttaLogServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.material.service.impl
 * @Description:案件资料清单附件日志服务接口实现
 * @author: gonght
 * @date:2017年1月15日 下午2:52:21
 * @version:v1.0
 */
@Service("caseMaterialListAttaLogService")
public class CaseMaterialListAttaLogServiceImpl extends BaseServiceImpl<CaseMaterialListAttaLog, CaseMaterialListAttaLogRepository>
        implements CaseMaterialListAttaLogService {
    
    @Log
    private Logger logger;
    
    @Autowired
    private CED CED;

    @Override
    public Page<Map<String, Object>> findCaseMaterialAttaLogList(PageRequest pageRequest, List<QueryObj> li) throws Exception {
        //调试SQL语句
        /*SELECT cml.id cmlid,cml.materiaTypeName,cmla.id,cmla.attachmentId,cmla.attachmentName,cmla.operatorNm,cmla.operatorTime,cmla.position,cmla.source,
        cmlal.downOper,cmlal.downCount,cmlal.downLastTime 
        FROM case_material_list cml 
        LEFT JOIN case_material_list_atta cmla ON cml.id = cmla.caseMaterialListId 
        LEFT JOIN (
        SELECT t.caseMaterialListAttaId,t.operatorCd,t.operatorNm downOper,COUNT(1) downCount,MAX(t.operatorTime) downLastTime 
         FROM case_material_list_atta_log t GROUP BY t.caseMaterialListAttaId,t.operatorCd,t.operatorNm
        ) cmlal ON cmla.id = cmlal.caseMaterialListAttaId
        WHERE 1=1*/
        StringBuffer _sql = new StringBuffer();
        _sql.append("SELECT cml.id cmlid,cml.materiaTypeName,cmla.id,cmla.attachmentId,cmla.attachmentName,cmla.operatorNm,cmla.operatorTime,cmla.position,cmla.source,")
        .append("cmlal.downOper,cmlal.downCount,cmlal.downLastTime ")
        .append("FROM case_material_list cml ")
        .append("LEFT JOIN case_material_list_atta cmla ON cml.id = cmla.caseMaterialListId ")
        .append("LEFT JOIN ( ")
        .append(" SELECT t.caseMaterialListAttaId,t.operatorCd,t.operatorNm downOper,COUNT(1) downCount,MAX(t.operatorTime) downLastTime ")
        .append(" FROM case_material_list_atta_log t GROUP BY t.caseMaterialListAttaId,t.operatorCd,t.operatorNm ")
        .append(") cmlal ON cmla.id = cmlal.caseMaterialListAttaId ")
        .append(" WHERE 1=1 ");
        StringBuffer _extendSql = new StringBuffer(" order by cml.id,cmla.createTime desc ");
        return this.customReposity.getListObjectBySql(pageRequest, li, _sql, _extendSql);
    }

}
