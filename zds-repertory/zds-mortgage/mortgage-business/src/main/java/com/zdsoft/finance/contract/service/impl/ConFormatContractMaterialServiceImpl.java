package com.zdsoft.finance.contract.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.contract.entity.ConFormatContractDetail;
import com.zdsoft.finance.contract.entity.ConFormatContractMaterial;
import com.zdsoft.finance.contract.repository.ConFormatContractMaterialRepository;
import com.zdsoft.finance.contract.service.ConFormatContractDetailService;
import com.zdsoft.finance.contract.service.ConFormatContractMaterialService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractMaterialServiceImpl.java 
 * @ClassName: ConFormatContractMaterialServiceImpl 
 * @Description: 格式化合同实物接口实现
 * @author zhongyong 
 * @date 2017年3月7日 下午5:10:44 
 * @version V1.0
 */
@Service("conFormatContractMaterialService")
public class ConFormatContractMaterialServiceImpl
		extends BaseServiceImpl<ConFormatContractMaterial, ConFormatContractMaterialRepository>
		implements ConFormatContractMaterialService {
	
	@Autowired
	private ConFormatContractDetailService conFormatContractDetailService;
	@Autowired
	private CED CED;
	
	@Override
	@Transactional
	public void createFormatContractMaterial(String applyId) throws Exception {
		if (ObjectHelper.isEmpty(applyId)) {
			throw new BusinessException("10010004", "传入参数为空");
		}
		// 获取清单列表
		List<ConFormatContractDetail> details = conFormatContractDetailService.findByFormatContractApplyId(applyId);

		// 是否使用同一个文件号，zy-后续调整为获取simpleCode取得开关
		boolean isSameNo = true;

		// 循环每个清单，生成合同实物
		for (ConFormatContractDetail detail : details) {
			String contractNo = CED.resolveExpression("YW000000000001", null);
			// 循环生成合同实物
			for (int i = 0; i < detail.getContractCopies(); i++) {
				ConFormatContractMaterial material = new ConFormatContractMaterial();
				material.setCreateBy(CED.getLoginUser().getEmpCd());
				material.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				// 判断是否生成相同的合同号
				if (isSameNo) {
					material.setFileNo(contractNo);
				} else {
					material.setFileNo(CED.resolveExpression("YW000000000001", null));
				}
				// 初始状态
				material.setFileStatus(ConFormatContractMaterial.FILE_STATUS_APPLY);
				material.setFormatContractDetailId(detail.getId());
				this.saveEntity(material);
			}
		}
	}

}
