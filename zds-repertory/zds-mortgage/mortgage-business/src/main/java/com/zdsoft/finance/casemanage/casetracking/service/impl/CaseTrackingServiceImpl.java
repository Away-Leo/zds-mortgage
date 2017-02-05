package com.zdsoft.finance.casemanage.casetracking.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.casetracking.entity.ManualNodeRecord;
import com.zdsoft.finance.casemanage.casetracking.repository.CaseTrackingRepository;
import com.zdsoft.finance.casemanage.casetracking.service.CaseTrackingService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

@Service
public class CaseTrackingServiceImpl extends BaseServiceImpl<ManualNodeRecord, CustomRepository<ManualNodeRecord,String>>
    implements CaseTrackingService{
    
    @Autowired
    private CaseTrackingRepository caseTrackingRepository;
    
    @Override
    public Page<Map<String, Object>> queryAllCaseTracking(Pageable pageable, List<QueryObj> queryObjs) {
        
        @SuppressWarnings("static-access")
        Page<Map<String, Object>> page = caseTrackingRepository.getListObjectBySql(pageable, queryObjs, caseTrackingRepository.SQL, 
                caseTrackingRepository.EXTEND_SQL);
        return page;
    }

}
