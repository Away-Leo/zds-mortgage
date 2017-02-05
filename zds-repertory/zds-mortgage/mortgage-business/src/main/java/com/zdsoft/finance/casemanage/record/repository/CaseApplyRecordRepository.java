package com.zdsoft.finance.casemanage.record.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.zdsoft.finance.casemanage.record.entity.CaseApplyRecord;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CaseApplyRecordRepository.java
 * @Package com.zdsoft.finance.casemanage.record.repository
 * @Description: 案件信息录入
 * @author Liyb
 * @date 2017年1月16日 下午4:23:59
 * @version V1.0
 */
public interface CaseApplyRecordRepository extends CustomRepository<CaseApplyRecord, String> {

    /**案件信息录入列表
     * @return
     */
    @Query(" from CaseApplyRecord")
    public List<CaseApplyRecord> findList();

}
