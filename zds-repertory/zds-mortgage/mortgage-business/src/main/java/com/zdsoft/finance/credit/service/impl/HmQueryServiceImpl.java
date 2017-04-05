package com.zdsoft.finance.credit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.dto.CreditReceiveDto;
import com.zdsoft.finance.credit.entity.HmQuery;
import com.zdsoft.finance.credit.repository.HmQueryRepository;
import com.zdsoft.finance.credit.service.HmQueryService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmQueryServiceImpl.java 
 * @ClassName: HmQueryServiceImpl 
 * @Description: 查询
 * @author gufeng 
 * @date 2017年2月23日 上午9:57:17 
 * @version V1.0
 */
@Service("hmQueryService")
public class HmQueryServiceImpl extends BaseServiceImpl<HmQuery, CustomRepository<HmQuery, String>>
implements HmQueryService{
	
	@Autowired
	private HmQueryRepository hmQueryRepository;
	
	@Override
	public List<HmQuery> findByQueryCredNum(String creditNo,String caseApplyId) {
		return hmQueryRepository.findByQueryCredNumAndObjectIdLike(creditNo,"%" + caseApplyId);
	}

	@Override
	@Transactional
	public HmQuery soloSave(CreditReceiveDto dto) {
		List<HmQuery> list = hmQueryRepository.findByQueryCredNumAndObjectId(dto.getQueryCredNum(),dto.getObjectId());
		HmQuery hq = null;
		if(ObjectHelper.isEmpty(list) || list.size() == 0){
			hq = new HmQuery();
			hq.setObjectId(dto.getObjectId());
			hq.setQueryName(dto.getQueryName());
			hq.setQueryCertType(dto.getQueryCertType());
			hq.setQueryCredNum(dto.getQueryCredNum());
			hq = hmQueryRepository.saveEntity(hq);
		}else{
			hq = list.get(0);
		}
		return hq;
	}

	@Override
	@Transactional
	public HmQuery saveQueryAttr(String id, String attrId) {
		if(ObjectHelper.isEmpty(id) || ObjectHelper.isEmpty(attrId)){
			return null;
		}
		HmQuery hq = hmQueryRepository.findOne(id);
		if(ObjectHelper.isNotEmpty(hq)){
			hq.setReportPdf(attrId);
		}
		hmQueryRepository.updateEntity(hq);
		return hq;
	}

	@Override
	public List<HmQuery> findByNewCardNum(String cardNum, String cardType) {
		return hmQueryRepository.findByQueryCredNumAndQueryCertTypeOrderByCreateTimeDesc(cardNum,cardType);
	}

	@Override
	public List<HmQuery> findByObjectIdLike(String objectId) {
		return hmQueryRepository.findByObjectIdLikeOrderByObjectIdDesc(objectId);
	}

}
