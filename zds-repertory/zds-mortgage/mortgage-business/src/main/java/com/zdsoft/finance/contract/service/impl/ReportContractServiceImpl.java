package com.zdsoft.finance.contract.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.CoactAgencyContractTplApply;
import com.zdsoft.finance.contract.repository.ReportContractRepository;
import com.zdsoft.finance.contract.service.ReportContractService;


@Service("reportContractService")
public class ReportContractServiceImpl extends BaseServiceImpl<CoactAgencyContractTplApply, CustomRepository<CoactAgencyContractTplApply, String>>
implements ReportContractService {

@Autowired
private ReportContractRepository reportContractRepository;

@Override
public List<CoactAgencyContractTplApply> findAll() {
List<CoactAgencyContractTplApply> CoactAgencyContractTplApplys = reportContractRepository.findAll();
return CoactAgencyContractTplApplys;

}
}