package com.zdsoft.finance.casemanage.promotion.otherproperty.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsDevice;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDeviceRepository.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.repository
 * @Description:其他资产之设备信息实现
 * @author: xiongpan
 * @date:2017年2月22日 下午2:31:02
 * @version:v1.0
 */
public interface AssetsDeviceRepository extends CustomRepository<AssetsDevice,String>{

	/**
	 * 
	 * @Title: findPageAssetsDevice 
	 * @Description: 根据案件id获取其他资产之设备的所有情况的分页信息
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 对应的案件id
	 * @return
	 * @throws Exception
	 */
	
	public default Page<Map<String, Object>> findPageAssetsDevice(PageRequest pageable, String caseApplyId) throws Exception{
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append(  "  c.deviceName as deviceName, ");
        sql.append(  "  c.invoiceDate as invoiceDate, " );
        sql.append(  "  c.ownerName as ownerName, " );
        sql.append(  "  c.isPledge as isPledge, " );
        sql.append(  "  c.pledgeAmount as pledgeAmount, " );
        sql.append(  "  c.evaluationAmount as evaluationAmount, " );
        sql.append(  "  c.deviceProvince as deviceProvince, " );
        sql.append(  "  c.deviceCity as deviceCity, " );
        sql.append(  "  c.deviceDistrict as deviceDistrict, " );
        sql.append(  "  c.detailAddress as detailAddress, " );
        sql.append(  "  c.caseApplyId as caseApplyId " );
        
        sql.append(   " from case_other_assets_device c " );
        sql.append(  " where c.isDeleted='F' and c.caseApplyId='"+caseApplyId+"'");
        StringBuffer extendSql = new StringBuffer(" order by c.createTime desc ");
        
        List<QueryObj> params = new ArrayList<QueryObj>();
        return this.getListObjectBySql(pageable, params, sql, extendSql);
		
	};
	
	
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id获取其他资产之设备的所有情况的列表信息
	 * @author xiongpan
	 * @param caseApplyId 对应的案件id
	 * @return
	 */
	@Query(" FROM AssetsDevice a where a.caseApplyId=:caseApplyId and a.isDeleted ='F' order by a.updateTime desc ")
	List<AssetsDevice> findByCaseApplyId(@Param(value="caseApplyId")String caseApplyId);


}
