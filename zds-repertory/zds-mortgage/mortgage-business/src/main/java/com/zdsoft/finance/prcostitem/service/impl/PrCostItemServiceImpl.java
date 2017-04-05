package com.zdsoft.finance.prcostitem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.PrCostItem;
import com.zdsoft.finance.prcostitem.entity.PrCostItemDetail;
import com.zdsoft.finance.prcostitem.repository.PrCostItemDetailRepository;
import com.zdsoft.finance.prcostitem.repository.PrCostItemRepository;
import com.zdsoft.finance.prcostitem.service.PrCostItemService;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostItemServiceImpl.java 
 * @ClassName: PrCostItemServiceImpl 
 * @Description: 机构产品费用
 * @author gufeng 
 * @date 2017年3月13日 下午5:05:08 
 * @version V1.0
 */
@Service
public class PrCostItemServiceImpl extends BaseServiceImpl<PrCostItem, CustomRepository<PrCostItem, String>> 
	implements PrCostItemService{

	@Autowired
	private PrCostItemRepository prCostItemRepository;
	
	@Autowired
	private PrCostItemDetailRepository prCostItemDetailRepository;
	
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public PrCostItem saveOrUpdate(PrCostItem po) throws BusinessException {
		PrCostItem bean = null;
		if(ObjectHelper.isNotEmpty(po.getId())){
			bean = prCostItemRepository.findOne(po.getId());
		}
		if(ObjectHelper.isEmpty(bean)){
			//新增
			bean = prCostItemRepository.saveEntity(po);
			//保存明细
			List<PrCostItemDetail> list = new ArrayList<>();
			for (int i = 0; i < po.getDetailCode().length; i++) {
				PrCostItemDetail d = new PrCostItemDetail();
				d.setCreateBy(po.getCreateBy());
				d.setCreateOrgCd(po.getCreateOrgCd());
				d.setCreateTime(po.getCreateTime());
				d.setUpdateBy(po.getUpdateBy());
				d.setUpdateOrgCd(po.getUpdateOrgCd());
				d.setUpdateTime(po.getUpdateTime());
				d.setCode(po.getDetailCode()[i]);
				d.setName(po.getDetailName()[i]);
				d.setCollectionType(po.getDetailCollectionType()[i]);
				d.setStock(po.getDetailStock()[i]);
				d.setMonth(po.getDetailMonth()[i]);
				d.setCostMonth(po.getDetailCostMonth()[i]);
				d.setReceipt(po.getDetailReceipt()[i]);
				d.setPrCostItem(bean);
				PrCostItemDetail detailBean = prCostItemDetailRepository.saveEntity(d);
				list.add(detailBean);
			}
			bean = prCostItemRepository.updateEntity(bean);
		}else{
			//修改
			bean.setUpdateBy(po.getUpdateBy());
			bean.setUpdateOrgCd(po.getUpdateOrgCd());
			bean.setUpdateTime(po.getUpdateTime());
			bean.setProductId(po.getProductId());
			bean.setProductParentId(po.getProductParentId());
			bean.setPeriodStart(po.getPeriodStart());
			bean.setPeriodStartUnit(po.getPeriodStartUnit());
			bean.setPeriodEnd(po.getPeriodEnd());
			bean.setPeriodEndUnit(po.getPeriodEndUnit());
			bean.setAmountStart(po.getAmountStart());
			bean.setAmountEnd(po.getAmountEnd());
			bean.setPledgePickStart(po.getPledgePickStart());
			bean.setPledgePickEnd(po.getPledgePickEnd());
			bean.setOverdueFee(po.getOverdueFee());
			bean.setRenewalFee(po.getRenewalFee());
			bean.setTerminal(po.getTerminal());
			bean.setRepaymentType(po.getRepaymentType());
			bean.setPrepayment(po.getPrepayment());
			List<PrCostItemDetail> list = bean.getPrCostItemDetails();
			for (PrCostItemDetail detail : list) {
				for (int i = 0; i < po.getDetailCode().length; i++) {
					if(detail.getCode().equals(po.getDetailCode()[i])){
						detail.setUpdateBy(po.getUpdateBy());
						detail.setUpdateOrgCd(po.getUpdateOrgCd());
						detail.setUpdateTime(po.getUpdateTime());
						detail.setCollectionType(po.getDetailCollectionType()[i]);
						detail.setStock(po.getDetailStock()[i]);
						detail.setMonth(po.getDetailMonth()[i]);
						detail.setCostMonth(po.getDetailCostMonth()[i]);
						detail.setReceipt(po.getDetailReceipt()[i]);
						detail.setPrCostItem(bean);
						prCostItemDetailRepository.saveEntity(detail);
					}
				}
			}
			bean = prCostItemRepository.updateEntity(bean);
		}
		return bean;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void itemLogicDelete(String id) throws BusinessException {
		if(ObjectHelper.isEmpty(id)){
			throw new BusinessException("1000000001","传入参数为空");
		}
		PrCostItem item = prCostItemRepository.findOne(id);
		if(ObjectHelper.isEmpty(item)){
			throw new BusinessException("1000000002","未找到数据,id:" + id);
		}
		List<PrCostItemDetail> list = item.getPrCostItemDetails();
		for (PrCostItemDetail detail : list) {
			prCostItemDetailRepository.logicDelete(detail);
		}
		prCostItemRepository.logicDelete(item);
	}

	
}
