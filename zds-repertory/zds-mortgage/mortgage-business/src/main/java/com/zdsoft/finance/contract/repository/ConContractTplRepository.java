package com.zdsoft.finance.contract.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConContractTpl;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConContractTplRepository.java 
 * @ClassName: ConContractTplRepository 
 * @Description: 标准合同模板Repository
 * @author zhongyong 
 * @date 2017年3月6日 上午9:59:28 
 * @version V1.0
 */
public interface ConContractTplRepository extends CustomRepository<ConContractTpl,String>{
	
	/**
	 * @Title: getConContractTplList 
	 * @Description: 根据申请id查询标准合同
	 * @author zhongyong 
	 * @param tplApplyId 申请id
	 * @return
	 */
	@Query(" from ConContractTpl where isDeleted = false and  (tplApplyId = :tplApplyId)")
	public List<ConContractTpl> getConContractTplList(@Param("tplApplyId") String tplApplyId);
	
	/**
	 * @Title: findPageContract 
	 * @Description: 分页查询标准合同
	 * @author zhongyong 
	 * @param pageable 分页信息
	 * @param queryObjs 查询参数
	 * @param type 1标准合同 2：机构合同报备下的合同
	 * @return
	 */
	public default Page<Map<String, Object>> findPageContract(PageRequest pageable, List<QueryObj> queryObjs, int type){
		StringBuffer sql = new StringBuffer("");
        sql.append(" select tpl.id as id,");
        sql.append(" tpl.capitalisttype as capitalisttype,");
        sql.append(" tpl.contractname as contractname,");
        sql.append(" tpl.attachmentid as attachmentid,");
        sql.append(" tpl.contracttype as contracttype,");
        sql.append(" tpl.contracttplstate as contracttplstate,");
        sql.append(" cc.capitalname as capitalNm");
        sql.append(" from con_contract_tpl tpl");
        sql.append(" left join cpt_capitalist cc on cc.id=tpl.capitalid ");
        sql.append(" where tpl.isDeleted='F'");
		if (type == 1) {
			sql.append(" and tpl.orgcantractapplyid is null");
		}
        StringBuffer extendSql = new StringBuffer(" order by tpl.updateTime desc ");
        // 查询数据
        return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}
	
	/**
	 * @Title: findByOrgCantractApplyIdAndIsDeletedFalse 
	 * @Description: 根据机构合同报备id获取标准合同模板
	 * @author zhongyong 
	 * @param orgCantractApplyId 机构合同报备id
	 * @return
	 */
	public List<ConContractTpl> findByOrgCantractApplyIdAndIsDeletedFalse(String orgCantractApplyId);
	
	/**
	 * @Title: findContractTplListForFormatContract 
	 * @Description: 格式化合同清单明细获取标准合同
	 * @author zhongyong 
	 * @param capitalId 资方id
	 * @param contractType 合同类型
	 * @return
	 */
	@Query(" from ConContractTpl where isDeleted = false and contractTplState='Enable' and capitalId = :capitalId and contractType = :contractType")
	public List<ConContractTpl> findContractTplListForFormatContract(@Param("capitalId") String capitalId,
			@Param("contractType") String contractType);
}
