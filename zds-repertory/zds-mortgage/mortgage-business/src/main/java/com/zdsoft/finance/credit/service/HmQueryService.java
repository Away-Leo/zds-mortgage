package com.zdsoft.finance.credit.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.credit.dto.CreditReceiveDto;
import com.zdsoft.finance.credit.entity.HmQuery;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmQueryService.java 
 * @ClassName: HmQueryService 
 * @Description: 查询
 * @author gufeng 
 * @date 2017年2月23日 上午9:55:58 
 * @version V1.0
 */
public interface HmQueryService extends BaseService<HmQuery>{

	/**
	 * @Title: findByQueryCredNum 
	 * @Description: 证件号查询
	 * @author gufeng 
	 * @param creditNo 证件号
	 * @param caseApplyId 案件id
	 * @return 查询结果集
	 */
	public List<HmQuery> findByQueryCredNum(String creditNo,String caseApplyId);

	/**
	 * @Title: soloSave 
	 * @Description: 唯一性保存
	 * @author gufeng 
	 * @param dto 传入dto
	 * @return 保存后的查询
	 */
	public HmQuery soloSave(CreditReceiveDto dto);

	/**
	 * @Title: saveQueryAttr 
	 * @Description: 保存附件id
	 * @author gufeng
	 * @param id 查询主键
	 * @param attrId 附件id
	 * @return 查询对象
	 */
	public HmQuery saveQueryAttr(String id, String attrId);

	/**
	 * @Title: findByNewCardNum 
	 * @Description: 身份证查询
	 * @author gufeng 
	 * @param cardNum 证件号
	 * @param cardType 证件类型
	 * @return 查询数据
	 */
	public List<HmQuery> findByNewCardNum(String cardNum, String cardType);
	
	/**
	 * 
	 * @Title: findByObjectIdLike 
	 * @Description: 案件号查询
	 * @author gufeng 
	 * @param caseApplyId 案件好
	 * @return 查询
	 */
	public List<HmQuery> findByObjectIdLike(String objectId);

}
