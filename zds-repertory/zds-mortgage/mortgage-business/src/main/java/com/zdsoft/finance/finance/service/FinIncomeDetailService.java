package com.zdsoft.finance.finance.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.finance.entity.FinIncomeDetail;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FinIncomeDetailService.java 
 * @ClassName: FinIncomeDetailService 
 * @Description: 收费明细接口
 * @author longwei 
 * @date 2017年2月16日 下午3:02:08 
 * @version V1.0
 */
public interface FinIncomeDetailService extends BaseService<FinIncomeDetail>{
	
	/**
	 * 
	 * @Title: deleteByFinIncomeId 
	 * @Description: 根据FinIncomeId删除收费数据
	 * @author xiangjx 
	 * @param finIncomeId
	 * @return
	 */
	public void deleteByFinIncomeId(String finIncomeId)throws Exception;
	/**
	 * 
	 * @Title: selectByExsistData 
	 * @Description: 把FinIncomeDetail中存在，FeeInfomation
	 * 不存在的数据 保存到FeeInfomation
	 * @author xiangjx 
	 * @param caseApplyId
	 * @param finIncomeId
	 * @return
	 * @throws Exception
	 */
	public List<FinIncomeDetail> selectByExsistData(String caseApplyId,String finIncomeId)  throws Exception;
	/**
	 * @Title: findByFinIncomeId 
	 * @Description: 通过收费id获取当次收费项
	 * @author jincheng 
	 * @param finIncomeId
	 * @return
	 */
	public List<FinIncomeDetail> findByFinIncomeId(String finIncomeId);
	
	
}
