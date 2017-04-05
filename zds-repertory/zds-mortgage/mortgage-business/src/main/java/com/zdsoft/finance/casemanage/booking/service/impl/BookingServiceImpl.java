package com.zdsoft.finance.casemanage.booking.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.booking.entity.Booking;
import com.zdsoft.finance.casemanage.booking.repository.BookingRepository;
import com.zdsoft.finance.casemanage.booking.service.BookingService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.message.client.service.CMS;
import com.zdsoft.message.dto.MessageInfoDto;
import com.zdsoft.message.dto.SendType;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: BookingServiceImpl.java
 * @ClassName: BookingServiceImpl
 * @Description: 案件预约信息服务接口实现
 * @author dengyy
 * @date 2017年2月13日 下午7:43:26
 * @version V1.0
 */
@Service("bookingService")
public class BookingServiceImpl extends BaseServiceImpl<Booking, BookingRepository> implements BookingService {

    @Resource
    private CaseApplyService caseApplySerivce;

    @Resource
    private CED CED;

    @Resource
    private CMS CMS;

    @Override
    public List<Map<String, Object>> queryCaseCount(Long nowDate) throws BusinessException {
        List<Map<String, Object>> queryCaseCount = null;
        try {
            queryCaseCount = this.customReposity.queryCaseCount(nowDate);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("10010002", "统计当前时间后的预约信息失败！");
        }
        return queryCaseCount;
    }

    @Override
    public Booking findByCaseApplyId(String caseApplyId) throws BusinessException {
        if (ObjectHelper.isEmpty(caseApplyId)) {
            throw new BusinessException("10010004", "案件id不能为空！");
        }
        return this.customReposity.findByCaseApplyId(caseApplyId);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Booking saveOrUpdateBooking(Booking booking) throws Exception {
        // 获取案件信息
        CaseApply caseApply = caseApplySerivce.findOne(booking.getCaseApplyId());
        EmpDto empDto = CED.getLoginUser();
        Booking entity = null;
        if (ObjectHelper.isNotEmpty(booking.getId())) {
            entity = this.findOne(booking.getId());
            // 转换数据
            BeanUtils.copyProperties(booking, entity, new String[] { "id", "createBy", "createOrgCd", "createTime" });
            // 修改信息
            entity.setUpdateBy(empDto.getEmpCd());
            entity.setUpdateOrgCd(empDto.getOrgCd());
            // 修改预约信息
            entity = this.updateEntity(booking);
        } else {
            entity = new Booking();
            BeanUtils.copyProperties(booking, entity);
            entity.setBookingType(Booking.BOOKINGTYPE_TWO);
            entity.setCreateBy(empDto.getEmpCd());
            entity.setCreateOrgCd(empDto.getOrgCd());
            // 保存预约信息
            entity = this.saveEntity(booking);
            //修改案件状态
            caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.INVESTIGATION.value);
        }
        // 设置案件的预约信息
        caseApply.setBooking(entity);
        caseApply.setBookingType(Booking.BOOKINGTYPE_TWO);
        // 修改案件信息
        caseApplySerivce.updateEntity(caseApply);
        //短信
        if(Booking.REMINDWAY_ONE.equals(entity.getRemindWay())){
            // 发送短信
            MessageInfoDto messageInfoDto = new MessageInfoDto();
            EmpDto receiver = new EmpDto();
            // 接收人电话
            receiver.setEmpMobile(entity.getPhoneNumber());
            // 接收人姓名
            receiver.setEmpNm(entity.getRecipients());
            messageInfoDto.setReceiver(receiver);
            // 发送信息
            messageInfoDto.setContent(entity.getSendtContent());
            // 发送人
            messageInfoDto.setSender(empDto);
            // 发送类型 立即发送
            messageInfoDto.setSendType(SendType.TXI);
            // 发送短信
            CMS.sendSMS(messageInfoDto);
        }
        return entity;
    }

    @Override
    public Page<Map<String, Object>> findListLedgerBooking(Pageable pageable, List<QueryObj> param,DataOperPermDto dtOperPermDto) throws Exception {
        StringBuffer dataAuth = this.developmentManagerDataAuth(dtOperPermDto, "caseApply");
        Page<Map<String, Object>> page = this.customReposity.findListLedgerBooking(pageable, param,dataAuth);
        List<Map<String, Object>> list = page.getRecords();
        if (ObjectHelper.isNotEmpty(list)) {
            for (Map<String, Object> map : list) {
                // 获取simplecode 对应的名称
                String nameByFullCode = CED.loadSimpleCodeNameByFullCode(map.get("INTERVIEWAMORPM").toString());
                map.put("INTERVIEWAMORPM", nameByFullCode);
                // 转换数据 把null的数据转换 为空字符串
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (ObjectHelper.isEmpty(entry.getValue())) {
                        entry.setValue("");
                    }
                }
            }
        }
        return page;
    }

}
