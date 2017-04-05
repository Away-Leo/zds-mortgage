package com.zdsoft.finance.prcostitem.service.impl;

import com.zdsoft.bpm.dto.ProcessInstanceDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.PrCostItem;
import com.zdsoft.finance.prcostitem.entity.PrCostitemApply;
import com.zdsoft.finance.prcostitem.repository.PrCostItemRepository;
import com.zdsoft.finance.prcostitem.repository.PrCostitemApplyRepository;
import com.zdsoft.finance.prcostitem.service.PrCostItemService;
import com.zdsoft.finance.prcostitem.service.PrCostitemApplyService;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostitemApplyServiceImpl.java 
 * @ClassName: PrCostitemApplyServiceImpl 
 * @Description: 费用支拥申请
 * @author gufeng 
 * @date 2017年3月13日 下午5:04:10 
 * @version V1.0
 */
@Service
public class PrCostitemApplyServiceImpl extends BaseServiceImpl<PrCostitemApply, CustomRepository<PrCostitemApply, String>> 
	implements PrCostitemApplyService{
	
	@Autowired
	private PrCostitemApplyRepository prCostitemApplyRepository;
	
	@Autowired
	private PrCostItemRepository prCostItemRepository;
	
	@Autowired
	private PrCostItemService prCostItemService;
	
	@Autowired
	private BusiFormService busiFormService;
	
	@Autowired
	private BPM BPM;

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public PrCostitemApply saveOrUpdate(PrCostitemApply po)throws BusinessException {
		PrCostitemApply bean = null;
		if(ObjectHelper.isNotEmpty(po.getId())){
			bean = prCostitemApplyRepository.findOne(po.getId());
		}
		List<PrCostItem> items = new ArrayList<>();
		if(ObjectHelper.isNotEmpty(bean)){//修改
			bean.setUpdateBy(po.getUpdateBy());
			bean.setUpdateOrgCd(po.getUpdateOrgCd());
			bean.setUpdateTime(po.getUpdateTime());
			bean.setApplyOrgCd(po.getApplyOrgCd());
			bean.setApplyOrgNm(po.getApplyOrgNm());
			bean.setApplyEmpCd(po.getApplyEmpCd());
			bean.setApplyEmpNm(po.getApplyEmpNm());
			bean.setApplyDepCd(po.getApplyDepCd());
			bean.setApplyDepNm(po.getApplyDepNm());
			bean.setApplyTime(po.getApplyTime());
			bean.setStatus(po.getStatus());
			bean.setBusiformId(po.getBusiformId());
			List<PrCostItem> prCostitems = bean.getPrCostitems();
			for (PrCostItem item : prCostitems) {
				item.setPrCostitemApply(null);
				prCostItemRepository.updateEntity(item);
			}
		}else{//添加
			bean = prCostitemApplyRepository.saveEntity(po);
		}
		//更新关联关系
		if(ObjectHelper.isNotEmpty(po.getPrCostitem_ids())){
			for (String item_id : po.getPrCostitem_ids()) {
				PrCostItem item = prCostItemRepository.findOne(item_id);
				if(ObjectHelper.isNotEmpty(item)){
					item.setPrCostitemApply(bean);
					prCostItemRepository.updateEntity(item);
					items.add(item);
				}
			}
		}
		bean.setPrCostitems(items);
		bean = prCostitemApplyRepository.updateEntity(bean);
		return bean;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void applyLogicDelete(String id) throws BusinessException {
		if(ObjectHelper.isEmpty(id)){
			throw new BusinessException("1000000001","传入参数为空");
		}
		PrCostitemApply bean = prCostitemApplyRepository.findOne(id);
		if(ObjectHelper.isEmpty(bean)){
			throw new BusinessException("1000000002","未找到数据,id:" + id);
		}
		if(ObjectHelper.isNotEmpty(bean.getPrCostitems())){
			for (PrCostItem item : bean.getPrCostitems()) {
				prCostItemService.itemLogicDelete(item.getId());
			}
		}
		prCostitemApplyRepository.logicDelete(bean);
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public PrCostitemApply saveSend(PrCostitemApply po) throws BusinessException {
		PrCostitemApply bean = this.saveOrUpdate(po);
		bean = this.send(bean);
		return bean;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public PrCostitemApply send(PrCostitemApply po) throws BusinessException {
		if(ObjectHelper.isEmpty(po) || ObjectHelper.isEmpty(po.getId())){
			throw new BusinessException("100000001","费用支拥不支持流程需要！");
		}
		BusiForm busiForm = null;
		try {
			busiForm = saveBusiForm(po);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("审批单保存出错",e);
			throw new BusinessException("100000002",e.getMessage());
		}
		if(ObjectHelper.isEmpty(busiForm)){
			throw new BusinessException("100000003","审批单保存出错！");
		}
		
		//设置引擎参数
        Map<String, String> engineVarible = new HashMap<String, String>();
        //设置业务参数
        Map<String, String> businessVarible = new HashMap<String, String>();
        //流程实例
        ProcessInstanceDto instanceDto = null;
        try {
			instanceDto = BPM.startMainProcess("收费支佣", po.getId(), busiForm.getId(), "收费支佣", engineVarible, businessVarible);
		} catch (AppException e) {
			e.printStackTrace();
			logger.error("流程启动错误1",e);
			throw new BusinessException("10000004","流程启动错误1");
		}
        if(ObjectHelper.isEmpty(instanceDto)){
        	throw new BusinessException("10000005","流程启动错误2");
        }
      	//获取下一节点处理人
        po.setMsg(instanceDto.getCurrentCandidate());
        po.setStatus(BusiFormStatus.APPROVAL.value);
        po = prCostitemApplyRepository.updateEntity(po);
		return po;
	}
	
	/**
	 * 审批单保存
	 * @param po
	 * @return
	 * @throws Exception 
	 */
	private BusiForm saveBusiForm(PrCostitemApply po) throws Exception {
		BusiForm bf = new BusiForm();
		bf.setFormStatus(BusiFormStatus.APPROVAL.value);
		//申请人编号
		bf.setLaunchEmpCode(po.getApplyEmpCd());
        //申请人名称
		bf.setLaunchEmpName(po.getApplyEmpNm());
	    bf.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
	    bf.setBusinessEntityId(po.getId());
	    bf.setBusinessEntityName(po.getClass().getName());
//	    bf.setComponentsEntityId(po.getProjectCd());
//	    bf.setComponentsEntityNm(Project.class.getName());
	    return busiFormService.saveBusiForm(bf);
	}
	
	

}
