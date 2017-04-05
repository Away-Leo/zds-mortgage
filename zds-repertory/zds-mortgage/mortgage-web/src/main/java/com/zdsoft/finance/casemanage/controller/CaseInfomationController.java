package com.zdsoft.finance.casemanage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.LoanAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableInfoVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperatorBank;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.vo.CooperatorBankVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseInfomationController.java
 * @Package:com.zdsoft.finance.casemanage.financialreview
 * @Description:案件信息
 * @author: laijun
 * @date:2017年1月14日 上午10:46:32
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/financialreview/caseinfomation")
public class CaseInfomationController {
	@Autowired
	public CaseApplyService caseApplyService;
	@Autowired
	public CapitalistService capitalistService;

	/**
	 * 
	 * 案件信息编辑
	 *
	 * @author laijun
	 * @date:2017年1月14日 上午10:46:42
	 * @return
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.casemanage.financialreview.caseinfomation.edit")
	public ModelAndView edit(String caseApplyId) {
		ModelMap model = new ModelMap();
		CaseApplyVo vo = new CaseApplyVo();
		ReceivableInfoVo infoVo = new ReceivableInfoVo();
		CooperatorBankVo cooperatorBankVo=new CooperatorBankVo();
		ReceivableAccountVo receivableAccountVo = new ReceivableAccountVo();
		LoanAccountVo loanAccountVo = new LoanAccountVo();
		List<BankAccount> bankAccountList = new ArrayList<>();
		try {
			// 获取案件信息
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			ReceivableInfo po = caseApply.getReceivableInfo();
			// 获取资方银行卡信息
			Capitalist capitalist = capitalistService.findOne(caseApply.getCapitalSource());
			CooperatorBank cooperatorBank = null;
			cooperatorBankVo=new CooperatorBankVo(cooperatorBank);
			if (ObjectHelper.isNotEmpty(po)) {
				infoVo = new ReceivableInfoVo(po);
			}
			vo = new CaseApplyVo(caseApply);
			// 银行卡信息
			bankAccountList = caseApply.getCaseBankAccount();
			if (ObjectHelper.isNotEmpty(bankAccountList) && bankAccountList.size() > 0) {
				for (BankAccount bankAccount : bankAccountList) {
					if (bankAccount.getAccountType() == 0) {
						receivableAccountVo = new ReceivableAccountVo(bankAccount);
					} else {
						loanAccountVo = new LoanAccountVo(bankAccount);
					}
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			model.put("errorMsg", e.getMessage());
		}
		model.put("caseApply", vo);
		model.put("receivableInfo", infoVo);
		model.put("receivableAccountVo", receivableAccountVo);
		model.put("loanAccountVo", loanAccountVo);
		model.put("cooperatorBankVo", cooperatorBankVo);
		return new ModelAndView("casemanage/financialreview/caseinfomation_edit", model);
	}
}
