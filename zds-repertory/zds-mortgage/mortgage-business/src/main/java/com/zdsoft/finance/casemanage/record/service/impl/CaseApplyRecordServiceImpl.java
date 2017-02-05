package com.zdsoft.finance.casemanage.record.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.record.entity.CaseApplyRecord;
import com.zdsoft.finance.casemanage.record.repository.CaseApplyRecordRepository;
import com.zdsoft.finance.casemanage.record.service.CaseApplyRecordService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

@Service
public class CaseApplyRecordServiceImpl extends
        BaseServiceImpl<CaseApplyRecord, CustomRepository<CaseApplyRecord, String>> implements CaseApplyRecordService {

    @Autowired
    private CaseApplyRecordRepository caseApplyRecordRepository;

    /**
     * 案件信息录入
     * 
     * @return
     */
    @Override
    public List<CaseApplyRecord> queryList(Map<String, Object> params) {
        String hql = "from CaseApplyRecord";
        return caseApplyRecordRepository.findByHql(hql, params);
    }

    @Override
    public Page<CaseApplyRecord> queryList(Pageable pageable, Map<String, Object> condition) {
        String hql = "from CaseApplyRecord";
        return caseApplyRecordRepository.findByHqlPage(pageable, hql, condition);
    }

}
