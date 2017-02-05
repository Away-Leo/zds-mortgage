package com.zdsoft.finance.busiform.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.finance.busiform.repository.BusiFormRepository;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service("busiFormService")
public class BusiFormServiceImpl extends BaseServiceImpl<BusiForm, BusiFormRepository> implements BusiFormService {

	@Autowired
	BusiFormRepository busiFormRepository;

	@Autowired
	private CED CED;

	@Autowired
	private BPM BPM;

	@Override
	public BusiForm findByBusinessEntityIdAndBusinessEntityNm(String businessEntityId, String businessEntityNm) {
		return busiFormRepository.findByBusinessEntityIdAndBusinessEntityNm(businessEntityId, businessEntityNm);
	}

	@Override
	public String applyBusiFormNo() {
		return null;
	}

	@Override
	@Transactional
	public BusiForm saveBusiForm(BusiForm busiForm) throws Exception {
		if (ObjectHelper.isNotEmpty(busiForm)) {
			if (ObjectHelper.isEmpty(busiForm.getApplyTime())) {
				busiForm.setApplyTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			}
			if (ObjectHelper.isEmpty(busiForm.getProcessStartTime())) {
				busiForm.setProcessStartTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			}
			if (ObjectHelper.isEmpty(busiForm.getCreateTime())) {
				busiForm.setCreateTime(new Date());
			}
			if (ObjectHelper.isEmpty(busiForm.getProcessApplayEmpCd())) {
				busiForm.setProcessApplayEmpCd(CED.getLoginUser().getEmpCd());
			}
			if (ObjectHelper.isEmpty(busiForm.getProcessApplayEmpNm())) {
				busiForm.setProcessApplayEmpNm(CED.getLoginUser().getEmpNm());
			}
			if (ObjectHelper.isEmpty(busiForm.getApplyEmpCd())) {
				busiForm.setApplyEmpCd(CED.getLoginUser().getEmpCd());
			}
			if (ObjectHelper.isEmpty(busiForm.getApplyEmpNm())) {
				busiForm.setApplyEmpNm(CED.getLoginUser().getEmpNm());
			}
			if (ObjectHelper.isEmpty(busiForm.getCreateBy())) {
				busiForm.setCreateBy(CED.getLoginUser().getEmpCd());
			}
			if (ObjectHelper.isEmpty(busiForm.getCreateOrgCd())) {
				busiForm.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			}
			return busiFormRepository.saveEntity(busiForm);
		} else {
			throw new BusinessException("10010004", "保存busiform出错，传入参数为空！");
		}
	}

	@Override
	@Transactional
	public BusiForm updateBusiForm(BusiForm busiForm) throws BusinessException {
		if (ObjectHelper.isNotEmpty(busiForm)) {
			BusiForm target = busiFormRepository.findOne(busiForm.getId());
			target = copyPoperties(busiForm, target);
			return busiFormRepository.updateEntity(target);
		} else {
			throw new BusinessException("10010004", "传入参数为空！更新busiFrom出错");
		}
	}

	@Override
	@Transactional
	public BusiForm logicDelete(String businessEntityId, String businessEntityNm) {
		BusiForm busiForm = busiFormRepository.findByBusinessEntityIdAndBusinessEntityNm(businessEntityId,
				businessEntityNm);
		busiForm = busiFormRepository.logicDelete(busiForm);
		return busiForm;
	}

	@Override
	public BusiForm findById(String id) throws BusinessException {
		if (ObjectHelper.isNotEmpty(id)) {
			BusiForm busiForm = this.busiFormRepository.findOne(id);
			if (ObjectHelper.isNotEmpty(busiForm) && ObjectHelper.isNotEmpty(busiForm.getId())) {
				return busiForm;
			} else {
				throw new BusinessException("10010002", "根据相应参数未找到相应的数据");
			}
		} else {
			throw new BusinessException("1001001", "通过ID查找busiform出错！参数为空");
		}
	}

	@Override
	@Transactional
	public BusiForm updateBusiFormStatus(BusiForm busiform, Integer status) throws BusinessException {
		if (ObjectHelper.isNotEmpty(busiform) && ObjectHelper.isNotEmpty(status)) {
			BusiForm newBusiFrom = this.findById(busiform.getId());
			newBusiFrom = (BusiForm) ObjectProperUtil.compareAndValue(busiform, newBusiFrom, false);
			return this.busiFormRepository.updateEntity(newBusiFrom);
		} else {
			throw new BusinessException("10010004", "传入参数为空！更新busiFrom状态出错");
		}
	}

	@Override
	public List<BusiForm> findByCondition(Map<String, Object> condition) {
		return null;
	}

	@Override
	public BusiForm saveOrUpdateBusiForm(BusiForm busiForm) throws Exception {
		if (ObjectHelper.isNotEmpty(busiForm)) {
			if (ObjectHelper.isNotEmpty(busiForm.getId())) {
				return this.updateBusiForm(busiForm);
			} else {
				return this.saveBusiForm(busiForm);
			}
		} else {
			throw new BusinessException("10010004", "传入参数为空！更新或保存BusiForm出错");
		}
	}

	/**
	 * 手动copy字段属性值
	 *
	 * @param source
	 *            源数据
	 * @param target
	 *            目标数据
	 * @return copy后的对象
	 */
	private BusiForm copyPoperties(BusiForm source, BusiForm target) throws BusinessException {
		target = (BusiForm) ObjectProperUtil.compareAndValue(source, target, false);
		return target;
	}

	@Override
	public BusiForm findByBusinessEntityId(String businessEntityId) throws BusinessException {
		if (ObjectHelper.isEmpty(businessEntityId)) {
			throw new BusinessException("10010004", "传入businessEntityId为空");
		}
		BusiForm busiForm = busiFormRepository.findByBusinessEntityId(businessEntityId);
		if (ObjectHelper.isEmpty(busiForm)) {
			throw new BusinessException("10010002", "根据传入businessEntityId未找到审批单信息");
		}
		return busiForm;
	}

	@Override
	public Page<MyDraft> findByPage(MyDraft myDraft, Pageable pageable) throws BusinessException {
		return busiFormRepository.findByPage(myDraft, pageable);
	}
}
