package com.zdsoft.finance.contract.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConContractTpl;
import com.zdsoft.finance.contract.repository.ConContractTplRepository;
import com.zdsoft.finance.contract.service.ConContractTplService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConContractTplServiceImpl.java 
 * @ClassName: ConContractTplServiceImpl 
 * @Description: 标准合同接口实现
 * @author zhongyong 
 * @date 2017年3月1日 上午9:27:49 
 * @version V1.0
 */
@Service("conContractTplService")
public class ConContractTplServiceImpl extends BaseServiceImpl<ConContractTpl, ConContractTplRepository>
		implements ConContractTplService {
	
	@Autowired
	private CED CED;

	@Override
	public Page<Map<String, Object>> findPageContract(PageRequest pageable, List<QueryObj> queryObjs, int type) throws Exception {
		Page<Map<String, Object>> page = this.customReposity.findPageContract(pageable, queryObjs, type);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (ObjectHelper.isNotEmpty(page.getRecords())) {
			List<Map<String, Object>> records = page.getRecords();
			for (Map<String, Object> map : records) {
				Map<String, Object> mapRtn = new HashMap<String, Object>();
				mapRtn.put("id", map.get("ID"));
				// 资方名称
				if (ObjectHelper.isNotEmpty(map.get("CAPITALNM"))) {
					mapRtn.put("capitalNm", map.get("CAPITALNM").toString());
				} else {
					mapRtn.put("capitalNm", "");
				}

				// 资方类别
				if (ObjectHelper.isNotEmpty(map.get("CAPITALISTTYPE"))) {
					mapRtn.put("capitalistTypeNm",
							CED.loadSimpleCodeNameByFullCode(map.get("CAPITALISTTYPE").toString()));
				} else {
					mapRtn.put("capitalistTypeNm", "");
				}

				// 合同名称
				if (ObjectHelper.isNotEmpty(map.get("CONTRACTNAME"))) {
					mapRtn.put("contractName", map.get("CONTRACTNAME").toString());
				} else {
					mapRtn.put("contractName", "");
				}

				// 附件名称
				if (ObjectHelper.isNotEmpty(map.get("ATTACHMENTID"))) {
					AttachmentDto dto = CED.findAttachmentById(map.get("ATTACHMENTID").toString());
					mapRtn.put("attachName", dto.getFileLabel());
					mapRtn.put("attachType", dto.getFileType());
					mapRtn.put("attachmentId", dto.getId());
				} else {
					mapRtn.put("attachName", "");
					mapRtn.put("attachType", "");
					mapRtn.put("attachmentId", "");
				}

				// 合同类型
				if (ObjectHelper.isNotEmpty(map.get("CONTRACTTYPE"))) {
					mapRtn.put("contractTypeNm", CED.loadSimpleCodeNameByFullCode(map.get("CONTRACTTYPE").toString()));
				} else {
					mapRtn.put("contractTypeNm", "");
				}

				// 合同状态
				if (ObjectHelper.isNotEmpty(map.get("CONTRACTTPLSTATE"))) {
					if ("Enable".equals(map.get("CONTRACTTPLSTATE").toString())) {
						mapRtn.put("contractTplState", "启用");
					} else {
						mapRtn.put("contractTplState", "停用");
					}
				} else {
					mapRtn.put("contractTplState", "");
				}
				list.add(mapRtn);
			}
		}
		page.setRecords(list);
		return page;
	}
	
	@Override
	public List<ConContractTpl> findByOrgCantractApplyId(String orgCantractApplyId) {
		if (ObjectHelper.isEmpty(orgCantractApplyId)) {
			throw new BusinessException("10010004", "机构合同报备id不能为空！");
		}
		return this.customReposity.findByOrgCantractApplyIdAndIsDeletedFalse(orgCantractApplyId);
	}
	
	@Override
	public List<ConContractTpl> findContractTplListForFormatContract(String capitalId, String contractType) {
		return this.customReposity.findContractTplListForFormatContract(capitalId, contractType);
	}

}
