package com.zdsoft.finance.casemanage.booking.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.booking.entity.Booking;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BookingService.java 
 * @ClassName: BookingService 
 * @Description: 案件预约信息服务接口
 * @author dengyy 
 * @date 2017年2月13日 下午7:41:38 
 * @version V1.0
 */
public interface BookingService extends BaseService<Booking>{

    /**
     * 
     * @Title: queryCaseCount 
     * @Description: 查询目前时间的案件预约情况
     * @author dengyy 
     * @param nowDate 当前时间
     * @return 预约统计信息
     */
	public List<Map<String, Object>> queryCaseCount(Long nowDate) throws BusinessException;
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 获取案件的预约信息
	 * @author dengyy 
	 * @param caseApplyId 案件id
	 * @return
	 * @throws BusinessException
	 */
	public Booking findByCaseApplyId(String caseApplyId) throws BusinessException ;

    /** 
     * @Title: saveOrUpdateBooking 
     * @Description: 保存修改预约信息
     * @author dengyy 
     * @param booking 预约信息
     * @return  
     * @throws Exception 
     */ 
    public Booking saveOrUpdateBooking(Booking booking) throws Exception;
    
    /**
     * 
     * @Title: getListLedgerBooking 
     * @Description: 获取预约台账列表数据
     * @author dengyy 
     * @param pageable 分页信息
     * @param param 查询信息
     * @return
     * @throws Exception 
     */
    public Page<Map<String,Object>> findListLedgerBooking(Pageable pageable, List<QueryObj> param,DataOperPermDto dtOperPermDto) throws  Exception ;

}
