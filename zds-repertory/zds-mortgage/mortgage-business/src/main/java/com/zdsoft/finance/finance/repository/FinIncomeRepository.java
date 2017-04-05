package com.zdsoft.finance.finance.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.FinIncome;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FinIncomeRepository.java 
 * @ClassName: FinIncomeRepository 
 * @Description: 费用收费管理操作仓库
 * @author longwei 
 * @date 2017年2月15日 上午9:48:13 
 * @version V1.0
 */
public interface FinIncomeRepository extends CustomRepository<FinIncome, String>{

	
	public default List<Map<String,Object>> findAllSimpCode() throws Exception{
		StringBuffer sql = new StringBuffer();
		Map<String,Object> map = new HashMap<>();
		sql.append(
				"select a.companyName as \"name\", a.id as \"id\"\n" +
						"  from zf_evaluation_org_view a\n" + 
						"\n" + 
						"union all\n" + 
						"\n" + 
						"select b.companyname as \"name\", b.id as \"id\"\n" + 
						"  from cpt_other_cooperater b\n" + 
						"\n" + 
						"union all\n" + 
						"\n" + 
						"select c.terminalfullname as \"name\", c.id as \"id\" from cpt_terminal c");
		return this.findListMapByCondition(sql.toString(),map);
	}

}
