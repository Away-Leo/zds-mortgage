package com.zdsoft.finance.cooperator.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.entity.TerminalHistory;
import com.zdsoft.finance.cooperator.repository.CooperatorTerminalRepository;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.service.TerminalHistoryService;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CooperatorTerminalServiceImpl.java
 * @ClassName: CooperatorTerminalServiceImpl
 * @Description: 终端信息ServiceImpl
 * @author liuwei
 * @date 2017年3月1日 下午4:16:02
 * @version V1.0
 */
@Service("cooperatorTerminalService")
public class CooperatorTerminalServiceImpl
		extends BaseServiceImpl<CooperatorTerminal, CustomRepository<CooperatorTerminal, String>>
		implements CooperatorTerminalService {

	@Autowired
	CooperatorTerminalRepository cooperatorTerminalRepository;

	@Autowired
	TerminalHistoryService terminalHistoryService;

	@Autowired
	CED CED;

	@Override
	public List<CooperatorTerminal> getTerminalStatusAndOrgList(String createOrg, String status) {
		return cooperatorTerminalRepository.findByTerminalStatus(createOrg, status);
	}

	@Override
	public List<CooperatorTerminal> findByBelongRelevanceCodeAndTerminalStatus(String belongRelevanceCode,
			String status) {
		return cooperatorTerminalRepository.findByBelongRelevanceCodeAndTerminalStatus(belongRelevanceCode, status);
	}

	@Override
	@Transactional
	public CooperatorTerminal saveOrUpdateTerminal(CooperatorTerminal cooperatorTerminal) throws Exception {

		// 判断id是否存在
		if (ObjectHelper.isNotEmpty(cooperatorTerminal.getId())) { // id存在，修改
			// 查询原有终端信息
			CooperatorTerminal oldTerminal = cooperatorTerminalRepository.findOne(cooperatorTerminal.getId());

			Boolean isRecord = false;
			// 判断是否需要记录终端维护记录
			if (ObjectHelper.isNotEmpty(oldTerminal.getTerminalFullName())) {
				isRecord = true;
			}

			String changeValue = contrastObj(oldTerminal, cooperatorTerminal);

			// 修改属性
			oldTerminal.setTerminalCode(cooperatorTerminal.getTerminalCode());

			oldTerminal.setTerminalFullName(cooperatorTerminal.getTerminalFullName());
			oldTerminal.setTerminalType(cooperatorTerminal.getTerminalType());
			oldTerminal.setDealNumber(cooperatorTerminal.getDealNumber());
			oldTerminal.setCompanyTel(cooperatorTerminal.getCompanyTel());
			oldTerminal.setMonthOutAvgPredict(cooperatorTerminal.getMonthOutAvgPredict());
			oldTerminal.setGrade(cooperatorTerminal.getGrade());
			oldTerminal.setBelongType(cooperatorTerminal.getBelongType());
			oldTerminal.setBelongRelevanceCode(cooperatorTerminal.getBelongRelevanceCode());
			oldTerminal.setBelongRelevanceName(cooperatorTerminal.getBelongRelevanceName());
			if (ObjectHelper.isNotEmpty(cooperatorTerminal.getLinkman())) {
				oldTerminal.setLinkman(cooperatorTerminal.getLinkman());
			}
			oldTerminal.setTerminalStatus(cooperatorTerminal.getTerminalStatus());
			oldTerminal.setRebateType(cooperatorTerminal.getRebateType());
			oldTerminal.setReturnRate(cooperatorTerminal.getReturnRate());
			oldTerminal.setIsAllowPhoneMsg(cooperatorTerminal.getIsAllowPhoneMsg());
			oldTerminal.setIsBelongOrg(cooperatorTerminal.getIsBelongOrg());
			oldTerminal.setAcceptRebateType(cooperatorTerminal.getAcceptRebateType());
			oldTerminal.setAddProvince(cooperatorTerminal.getAddProvince());
			oldTerminal.setAddCounty(cooperatorTerminal.getAddCounty());
			oldTerminal.setAddCity(cooperatorTerminal.getAddCity());
			oldTerminal.setAddress(cooperatorTerminal.getAddress());
			oldTerminal.setBusinessScope(cooperatorTerminal.getBusinessScope());
			oldTerminal.setCooperateSuggest(cooperatorTerminal.getCooperateSuggest());
			oldTerminal.setOnContinueReason(cooperatorTerminal.getOnContinueReason());
			oldTerminal.setSpecialInstruction(cooperatorTerminal.getSpecialInstruction());

			// 经营信息
			oldTerminal.setFoundDate(cooperatorTerminal.getFoundDate());
			oldTerminal.setMainShareholder(cooperatorTerminal.getMainShareholder());
			oldTerminal.setStaffNumber(cooperatorTerminal.getStaffNumber());
			oldTerminal.setStaffTurnover(cooperatorTerminal.getStaffTurnover());
			oldTerminal.setIsGiveDeductMoney(cooperatorTerminal.getIsGiveDeductMoney());
			oldTerminal.setWageday(cooperatorTerminal.getWageday());
			oldTerminal.setMonthManageCost(cooperatorTerminal.getMonthManageCost());
			oldTerminal.setRegisteredCapital(cooperatorTerminal.getRegisteredCapital());
			oldTerminal.setWorkArea(cooperatorTerminal.getWorkArea());

			// 修改信息
			oldTerminal.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldTerminal.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
			oldTerminal.setUpdateTime(new Date());
			oldTerminal.setIsDeleted(false);

			cooperatorTerminal = cooperatorTerminalRepository.updateEntity(oldTerminal);

			if (isRecord && ObjectHelper.isNotEmpty(changeValue)) {
				TerminalHistory terminalHistory = new TerminalHistory();

				// 设置基础信息
				terminalHistory.setCreateBy(CED.getLoginUser().getEmpCd());
				terminalHistory.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				terminalHistory.setCreateTime(new Date());

				// 设置详细信息
				terminalHistory.setMaintainerCd(CED.getLoginUser().getEmpCd());
				terminalHistory.setMaintainerName(CED.getLoginUser().getEmpNm());
				terminalHistory.setCooperatorTerminal(cooperatorTerminal);
				terminalHistory.setPlanMaintainThing(changeValue);

				terminalHistoryService.saveEntity(terminalHistory);

				// 设置维护次数
				cooperatorTerminal.setMaintenanceTimes((cooperatorTerminal.getMaintenanceTimes() == null ? 0
						: cooperatorTerminal.getMaintenanceTimes()) + 1);
				cooperatorTerminalRepository.updateEntity(cooperatorTerminal);
			}

		} else { // id不存在，新增
			// 将默认删除修改为未删除
			cooperatorTerminal.setIsDeleted(false);

			// 保存新增信息
			cooperatorTerminal.setCreateBy(CED.getLoginUser().getEmpCd());
			cooperatorTerminal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			cooperatorTerminal.setCreateTime(new Date());
			cooperatorTerminal
					.setCreateDateLong(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));

			// 保存终端信息
			cooperatorTerminal = cooperatorTerminalRepository.saveEntity(cooperatorTerminal);
		}

		// 设置公用机构code
		cooperatorTerminal.setShareOrgCode(CED.getLoginUser().getCompanyCd());
		cooperatorTerminal = cooperatorTerminalRepository.updateEntity(cooperatorTerminal);
		return cooperatorTerminal;
	}

	@SuppressWarnings("rawtypes")
	private String contrastObj(CooperatorTerminal oldBean, CooperatorTerminal newBean) {
		String str = "";
		// if (oldBean instanceof SysConfServer && newBean instanceof
		// SysConfServer) {
		CooperatorTerminal pojo1 = oldBean;
		CooperatorTerminal pojo2 = newBean;
		try {
			Class clazz = pojo1.getClass();
			Field[] fields = pojo1.getClass().getDeclaredFields();
			int i = 1;
			for (Field field : fields) {
				if ("serialVersionUID".equals(field.getName()) || "createDateLong".equals(field.getName())
						|| "shareOrgCode".equals(field.getName()) || "terminalCode".equals(field.getName())
						|| "linkman".equals(field.getName()) || "maintenanceTimes".equals(field.getName())) {
					continue;
				}
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				Method getMethod = pd.getReadMethod();
				Object o1 = getMethod.invoke(pojo1);
				Object o2 = getMethod.invoke(pojo2);

				if (ObjectHelper.isEmpty(o1)) {
					o1 = "";
				}
				if (ObjectHelper.isEmpty(o2)) {
					o2 = "";
				}

				String fieldName = "";
				String finalValue1 = "";
				String finalValue2 = "";
				if ("terminalFullName".equals(field.getName())) {
					fieldName = "终端全称";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("terminalType".equals(field.getName())) {
					fieldName = "终端类别";
					finalValue1 = ObjectHelper.isNotEmpty(o1.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o1.toString()) : "";
					finalValue2 = ObjectHelper.isNotEmpty(o2.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o2.toString()) : "";
				} else if ("dealNumber".equals(field.getName())) {
					fieldName = "协议编号";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("companyTel".equals(field.getName())) {
					fieldName = "公司电话";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("monthOutAvgPredict".equals(field.getName())) {
					fieldName = "月均产能量预测";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("grade".equals(field.getName())) {
					fieldName = "终端等级";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("belongType".equals(field.getName())) {
					fieldName = "终端归属类型";
					finalValue1 = ObjectHelper.isNotEmpty(o1.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o1.toString()) : "";
					finalValue2 = ObjectHelper.isNotEmpty(o2.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o2.toString()) : "";
				} else if ("belongRelevanceCode".equals(field.getName())) {
					fieldName = "终端归属code";
					finalValue1 = CED.loadSimpleCodeNameByFullCode(o1.toString());
					finalValue2 = CED.loadSimpleCodeNameByFullCode(o2.toString());
				} else if ("belongRelevanceName".equals(field.getName())) {
					fieldName = "终端归属";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("terminalStatus".equals(field.getName())) {
					fieldName = "终端状态";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("rebateType".equals(field.getName())) {
					fieldName = "返佣类型";
					finalValue1 = ObjectHelper.isNotEmpty(o1.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o1.toString()) : "";
					finalValue2 = ObjectHelper.isNotEmpty(o2.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o2.toString()) : "";
				} else if ("returnRate".equals(field.getName())) {
					fieldName = "返佣具体数据";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("isAllowPhoneMsg".equals(field.getName())) {
					fieldName = "是否允许发送手机短信";
					finalValue1 = ObjectHelper.isNotEmpty(o1.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o1.toString()) : "";
					finalValue2 = ObjectHelper.isNotEmpty(o2.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o2.toString()) : "";
				} else if ("isBelongOrg".equals(field.getName())) {
					fieldName = "是否机构共用";
					finalValue1 = ObjectHelper.isNotEmpty(o1.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o1.toString()) : "";
					finalValue2 = ObjectHelper.isNotEmpty(o2.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o2.toString()) : "";
				} else if ("acceptRebateType".equals(field.getName())) {
					fieldName = "可接受返佣方式";
					finalValue1 = ObjectHelper.isNotEmpty(o1.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o1.toString()) : "";
					finalValue2 = ObjectHelper.isNotEmpty(o2.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o2.toString()) : "";
				} else if ("address".equals(field.getName())) {
					fieldName = "终端地址";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("businessScope".equals(field.getName())) {
					fieldName = "涉及业务";
					finalValue1 = ObjectHelper.isNotEmpty(o1.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o1.toString()) : "";
					finalValue2 = ObjectHelper.isNotEmpty(o2.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o2.toString()) : "";
				} else if ("cooperateSuggest".equals(field.getName())) {
					fieldName = "合作要求和建议";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("onContinueReason".equals(field.getName())) {
					fieldName = "没有继续合作原因";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("specialInstruction".equals(field.getName())) {
					fieldName = "该终端的特殊说明";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("foundDate".equals(field.getName())) {
					fieldName = "成立时间";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("mainShareholder".equals(field.getName())) {
					fieldName = "主要出资人及股东";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("staffNumber".equals(field.getName())) {
					fieldName = "员工数";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("staffTurnover".equals(field.getName())) {
					fieldName = "员工流动性";
					finalValue1 = ObjectHelper.isNotEmpty(o1.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o1.toString()) : "";
					finalValue2 = ObjectHelper.isNotEmpty(o2.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o2.toString()) : "";
				} else if ("isGiveDeductMoney".equals(field.getName())) {
					fieldName = "返佣是否给员工集体提成";
					finalValue1 = ObjectHelper.isNotEmpty(o1.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o1.toString()) : "";
					finalValue2 = ObjectHelper.isNotEmpty(o2.toString()) == true
							? CED.loadSimpleCodeNameByFullCode(o2.toString()) : "";
				} else if ("wageday".equals(field.getName())) {
					fieldName = "发放工资日期";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("monthManageCost".equals(field.getName())) {
					fieldName = "月均经营成本";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("registeredCapital".equals(field.getName())) {
					fieldName = "注册资本";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				} else if ("workArea".equals(field.getName())) {
					fieldName = "办公场所面积";
					finalValue1 = o1.toString();
					finalValue2 = o2.toString();
				}

				if (!o1.toString().equals(o2.toString())) {
					str += i + ".字段名称" + fieldName + ",旧值:" + finalValue1 + ",新值:" + finalValue2 + "。 ";
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
		return str;
	}

	@Override
	@Transactional
	public CooperatorTerminal saveTempTerminal(CooperatorTerminal cooperatorTerminal) throws Exception {
		cooperatorTerminal = cooperatorTerminalRepository.saveEntity(cooperatorTerminal);

		// 保存新增信息
		cooperatorTerminal.setCreateBy(CED.getLoginUser().getEmpCd());
		cooperatorTerminal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
		cooperatorTerminal.setCreateTime(new Date());
		cooperatorTerminal.setCreateDateLong(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));

		cooperatorTerminal = cooperatorTerminalRepository.logicDelete(cooperatorTerminal);
		return cooperatorTerminal;
	}

	@Override
	@Transactional
	public void updateTerminalStatusByIds(String id, String status) {
		String[] ids = null;
		if (StringUtils.isNotEmpty(id)) {
			id = id.substring(0, id.length() - 1);
			ids = id.split(",");
			for (String str : ids) {
				CooperatorTerminal terminal = cooperatorTerminalRepository.findOne(str);
				terminal.setTerminalStatus(status);
				cooperatorTerminalRepository.updateEntity(terminal);
			}
		}
	}

	@Override
	public List<CooperatorTerminal> findByShareOrgCdAndTerminalStatus(String shareOrgCd, String status) {
		return cooperatorTerminalRepository.findByShareOrgCdAndTerminalStatus(shareOrgCd, status);
	}

}
