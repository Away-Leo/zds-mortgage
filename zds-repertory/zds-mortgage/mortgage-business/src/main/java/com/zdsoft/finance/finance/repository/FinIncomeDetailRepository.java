package com.zdsoft.finance.finance.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.FinIncomeDetail;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FinIncomeDetailRepository.java 
 * @ClassName: FinIncomeDetailRepository 
 * @Description: 费用收费明细操作仓库
 * @author longwei 
 * @date 2017年2月16日 下午3:01:00 
 * @version V1.0
 */
public interface FinIncomeDetailRepository extends CustomRepository<FinIncomeDetail, String>{
	

	/**
	 * 
	 * @Title: selectByExsistData 
	 * @Description: 查询出正式库中不存在的数据
	 * @author xiangjx 
	 * @param caseApplyId
	 * @param finIncomeId
	 * @return
	 * @throws Exception
	 */
	public default List<FinIncomeDetail> selectByExsistData(String caseApplyId,String finIncomeId) throws Exception{
		
		Map<String,Object> qryCondition=new HashMap<String,Object>();
		StringBuffer sql=new StringBuffer();
		
		sql.append(" select c.feeitem AS \"feeItem\","+
				" c.paidamount AS \"paidAmount\"" +
				"  from (select b.feeitem , sum(b.paidamount) AS paidamount \n" + 
				"          from fin_income_detail b\n" + 
				"         where b.finincomeid = '"+finIncomeId+"'");
		//案件查询条件
		if(ObjectHelper.isNotEmpty(caseApplyId)){
            sql.append(" and B.FEEITEM  NOT IN \n" + 
		"         (select x.feeitem\n" + 
		"                  from case_fee_infomation x\n" + 
		"                 where x.caseapplyid = '"+caseApplyId+"' ) \n");
        }
		sql.append("group by b.feeitem ) c");
        return this.findBySql(sql.toString(),qryCondition,FinIncomeDetail.class);
	}
	
	/**
	 * @Title: findByFinIncomeId 
	 * @Description: 通过收费id,获取费用项
	 * @author jincheng 
	 * @param finIncomeId
	 * @return
	 */
	public List<FinIncomeDetail> findByFinIncomeId(String finIncomeId);
}
