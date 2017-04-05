package com.zdsoft.finance.capital.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustTieOff;
import com.zdsoft.finance.casemanage.limitApply.service.CaseLimitApplyService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.DateHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustTieOffQuartz.java
 * @ClassName: CreditEntrustTieOffQuartz
 * @Description: 信托计划每晚12点自动扎帐(未分配备付金、账面余额、累计收回本金、累计收回利息、累计收回罚息、累计收回违约金)
 * @author liuwei
 * @date 2017年2月16日 下午8:55:16
 * @version V1.0
 */
@Component
public class CreditEntrustTieOffQuartz {

	@Autowired
	CreditEntrustTieOffService creditEntrustTieOffService;

	@Autowired
	CreditEntrustService creditEntrustService;

	@Autowired
	SpareCapitalService spareCapitalService;

	@Autowired
	CaseLimitApplyService caseLimitApplyService;

	@Autowired
	CreditEntrustToolService creditEntrustToolService;

	@Log
	Logger logger;

	/**
	 * 
	 * @Title: tieOffCreditEntrust 
	 * @Description: 12点对未分配备付金、账面余额、累计收回本金、累计收回利息、累计收回罚息、累计收回违约金这6个字段进行扎帐处理
	 * @author liuwei
	 */
	@Scheduled(cron = "0 0 0 * * ? ")
	@Transactional
	public void tieOffCreditEntrust() {
		// 未分配备付金 等于=上日备付金余额+本日请款到账额-本日放款额-本日退回金额-已分配额度(已匹配资金部分)
		// 账面余额 等于=上日备付金余额+本日请款到账额-本日放款额-本日退回金额 (1、首次备付金余额为0)
		// 累计收回本金 属于该资金计划下所有放款的案件的还款的金额(本金)
		// 累计收回利息 属于该资金计划下所有放款的案件的还款的金额(利息)
		// 累计收回罚息 属于该资金计划下所有放款的案件的还款的金额(罚息)
		// 累计收回违约金 属于该资金计划下所有放款的案件的还款的金额(违约金)

		// 查出所有的有效的信托计划
		List<CreditEntrust> creditEntrusts = creditEntrustService.findByConditions(null);
		if (creditEntrusts.size() != 0) {
			// 遍历信托计划
			for (int i = 0; i < creditEntrusts.size(); i++) {
				CreditEntrust creditEntrust = creditEntrusts.get(i);

				// 获取6个跑批字段扎帐值(前一天)
				Map<String, BigDecimal> specialFields = creditEntrustToolService.calculateSpecialFields(creditEntrust,
						DateHelper.getBeforeDay(new Date()));
				CreditEntrustTieOff creditEntrustTieOff = new CreditEntrustTieOff();
				creditEntrustTieOff.setNotEquippedPay(specialFields.get("notEquippedPay"));
				creditEntrustTieOff.setBookBalance(specialFields.get("bookBalance"));
				creditEntrustTieOff.setCumulativeRecoveryPrincipal(specialFields.get("cumulativeRecoveryPrincipal"));
				creditEntrustTieOff.setCumulativeRecoveryInterest(specialFields.get("cumulativeRecoveryInterest"));
				creditEntrustTieOff.setCumulativeRecoveryPenalty(specialFields.get("cumulativeRecoveryPenalty"));
				creditEntrustTieOff.setCumulativeRecoveryLiqDamages(specialFields.get("cumulativeRecoveryLiqDamages"));
				creditEntrustTieOff
						.setTieOffDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
				creditEntrustTieOff.setCreditEntrustId(creditEntrust.getId());

				try {
					creditEntrustTieOffService.saveEntity(creditEntrustTieOff);
				} catch (BusinessException e) {
					e.printStackTrace();
					logger.error("系统自动跑批失败", e);
				}

			}
		}

	}

}
