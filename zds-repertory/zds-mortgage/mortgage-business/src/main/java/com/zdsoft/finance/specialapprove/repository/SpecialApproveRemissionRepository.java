package com.zdsoft.finance.specialapprove.repository;

import java.util.ArrayList;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveRemission;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveRemissionRepository.java 
 * @ClassName: SpecialApproveRemissionRepository 
 * @Description: 费用减免repository
 * @author wangrongwei
 * @date 2017年2月27日 下午4:05:09 
 * @version V1.0 
 */ 
public interface SpecialApproveRemissionRepository extends CustomRepository<SpecialApproveRemission, String> {
	
	/** 
	 * @Title: queryRemissionTypeByCaseApplyId 
	 * @Description: 通过案件ID查询可减免费用项
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @return  
	 */ 
	public default Page<Map<String, Object>> queryRemissionTypeByCaseApplyId(String caseApplyId) throws Exception{
		StringBuffer sql = new StringBuffer("select distinct(t.feetype),t.feetypename from case_fee_infomation t ");
		sql.append(" where t.caseApplyId='" + caseApplyId+"' ");
		//查询数据
		return this.getListObjectBySql(new PageRequest(), new ArrayList<>(), sql, new StringBuffer());	
	}
	
	/** 
	 * @Title: queryRemissionItemByType 
	 * @Description: 通过减免类型查询项目
	 * @author wangrongwei
	 * @param remissionType 减免项类型
	 * @return
	 * @throws Exception  
	 */ 
	public default Page<Map<String, Object>> queryRemissionItemByType(String remissionType) throws Exception{
		StringBuffer sql = new StringBuffer("select t.id,t.feeitemname from case_fee_infomation t ");
		sql.append(" where t.feetype ='" + remissionType+"' ");
		//查询数据
		return this.getListObjectBySql(new PageRequest(), new ArrayList<>(), sql, new StringBuffer());	
	}
	
}
