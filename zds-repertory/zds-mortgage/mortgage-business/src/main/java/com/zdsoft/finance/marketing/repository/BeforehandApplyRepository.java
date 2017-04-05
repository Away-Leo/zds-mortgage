package com.zdsoft.finance.marketing.repository;

import java.util.HashMap;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.BeforehandApply;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforehandApplyRepository.java 
 * @ClassName: BeforehandApplyRepository 
 * @Description: 案件预申请Repository
 * @author zhoushichao 
 * @date 2017年3月14日 下午5:50:47 
 * @version V1.0
 */
public interface BeforehandApplyRepository extends CustomRepository<BeforehandApply, String>{
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 通过案件id查询欲案件申请
	 * @author zhoushichao 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public BeforehandApply findByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: checkLoanTerm 
	 * @Description: 检查贷款期限是否在产品期限范围内
	 * @author yangjia 
	 * @param productSubtypeId子产品id
	 * @param applyTerm贷款期限
	 * @return
	 */
	public default Long checkLoanTerm(String productSubtypeId,int applyTerm){
		 StringBuffer sql = new StringBuffer(" select id from prd_product_rate a ");
		    sql.append(  "  where a.product_id = :productSubtypeId ");
		    sql.append(  "  and decode(a.startdateunit, '0931001', a.startdate * 360, '0931002', a.startdate * 30, a.startdate) <= :applyTerm ");
		    sql.append(  "  and decode(a.enddateunit, '0931001',a.enddate * 360, '0931002', a.enddate * 30,a.enddate) >= :applyTerm ");
		    Map<String, Object> condition = new HashMap<String, Object>(); 
		    condition.put("productSubtypeId", productSubtypeId);
		    condition.put("applyTerm", applyTerm);
		return this.countAllSql(sql.toString(), condition);
	}

}
