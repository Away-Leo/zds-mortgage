package com.zdsoft.finance.casemanage.handleMortgage.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.handleMortgage.entity.Notarize;
import com.zdsoft.finance.casemanage.handleMortgage.repository.NotarizeRepository;
import com.zdsoft.finance.casemanage.handleMortgage.service.NotarizeService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: NotarizeServiceImpl.java 
 * @ClassName: NotarizeServiceImpl 
 * @Description: 公证实现类
 * @author zhoushichao 
 * @date 2017年2月16日 下午6:38:53 
 * @version V1.0 
 */ 
@Service("notarizeService")
public class NotarizeServiceImpl extends BaseServiceImpl<Notarize, NotarizeRepository> 
		implements NotarizeService{
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdateNotarizeList(List<Notarize> notarizeList) throws Exception {
		for (Notarize notarize : notarizeList) {
			Notarize notarizes = new Notarize();
			if (ObjectHelper.isNotEmpty(notarizes.getId())) {
				notarizes = this.findOne(notarizes.getId());
			}
			BeanUtils.copyProperties(notarize, notarizes,new String[] {"id","createTime"});
			this.saveOrUpdateEntity(notarize);
		}
	}

	@Override
	public List<Notarize> findByCaseApplyId(String caseApplyId) throws Exception {
		return this.customReposity.findByCaseApplyId(caseApplyId);
	}
}
