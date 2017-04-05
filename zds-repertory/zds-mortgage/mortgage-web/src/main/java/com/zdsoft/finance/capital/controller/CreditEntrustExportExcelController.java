package com.zdsoft.finance.capital.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.capital.entity.CreditCostTracking;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.entity.CreditEntrustAttom;
import com.zdsoft.finance.capital.entity.CreditEntrustDebit;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;
import com.zdsoft.finance.capital.entity.LoanCapital;
import com.zdsoft.finance.capital.entity.SpareCapital;
import com.zdsoft.finance.capital.service.CreditCostTrackingService;
import com.zdsoft.finance.capital.service.CreditEntrustAttomService;
import com.zdsoft.finance.capital.service.CreditEntrustDebitService;
import com.zdsoft.finance.capital.service.CreditEntrustFeeItemService;
import com.zdsoft.finance.capital.service.CreditEntrustPrincipalService;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.service.CreditEntrustToolService;
import com.zdsoft.finance.capital.service.LoanCapitalService;
import com.zdsoft.finance.capital.service.SpareCapitalService;
import com.zdsoft.finance.capital.vo.CreditEntrustVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.ExcelUtil;
import com.zdsoft.finance.spi.common.dto.StatusNm;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustExportExcelController.java
 * @ClassName: CreditEntrustExportExcelController
 * @Description: 信托计划导出excelController
 * @author liuwei
 * @date 2017年2月6日 上午11:14:28
 * @version V1.0
 */
@Controller
@RequestMapping("/creditExportExcel")
public class CreditEntrustExportExcelController extends BaseController {

	@Autowired
	CreditEntrustService creditEntrustService;

	@Autowired
	CreditEntrustPrincipalService creditEntrustPrincipalService;

	@Autowired
	LoanCapitalService loanCapitalService;

	@Autowired
	CreditCostTrackingService creditCostTrackingService;

	@Autowired
	SpareCapitalService spareCapitalService;

	@Autowired
	CreditEntrustAttomService creditEntrustAttomService;

	@Autowired
	CreditEntrustDebitService creditEntrustDebitService;

	@Autowired
	CreditEntrustFeeItemService creditEntrustFeeItemService;

	@Autowired
	CreditEntrustToolService creditEntrustToolService;

	@Autowired
	CED CED;

	/**
	 * 信托计划资金情况报表路径
	 */
	public static final String CREDIT_ENTRUST_PATH = "/WEB-INF/modules/exportExcel/信托计划资金情况报表.xls";

	/**
	 * 本金投入明细情况报表路径
	 */
	public static final String PRINCIPAL_PATH = "/WEB-INF/modules/exportExcel/本金投入明细情况报表.xls";

	/**
	 * 非本金跟踪明细情况报表路径
	 */
	public static final String NON_PRINCIPAL_PATH = "/WEB-INF/modules/exportExcel/非本金跟踪明细情况报表.xls";

	/**
	 * 信托计划往来对象应付费用明细路径
	 */
	public static final String PAYMENT_PATH = "/WEB-INF/modules/exportExcel/信托计划往来对象应付费用明细.xls";

	/**
	 * 银联备付资金跟踪明细路径
	 */
	public static final String SPARE_CAPITAL_PATH = "/WEB-INF/modules/exportExcel/银联备付资金跟踪明细.xls";

	/**
	 * 信托计划转让明细路径
	 */
	public static final String ATTOM_PATH = "/WEB-INF/modules/exportExcel/信托计划转让明细.xls";

	/**
	 * 借方资金跟踪明细路径
	 */
	public static final String DEBIT_PATH = "/WEB-INF/modules/exportExcel/借方资金跟踪明细.xls";

	/**
	 * 
	 * @Title: exportAllData
	 * @Description: 导出信托计划主表
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param pageable
	 *            分页信息
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/exportAllData")
	@UriKey(key = "com.zdsoft.finance.capital.exportAllData")
	@ResponseBody
	public ResponseMsg exportAllData(HttpServletRequest request, HttpServletResponse response, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		// 获取页面封装的查询参数
		String capitallist_id = request.getParameter("capitalistTempId");
		String creditEntrustName = request.getParameter("creditTempName");
		List<QueryObj> objs = new ArrayList<QueryObj>();
		if (ObjectHelper.isNotEmpty(capitallist_id)) {
			QueryObj query = new QueryObj();
			query.setObj("capitallist.id");
			query.setElement("String");
			query.setOperator("E");
			query.setValue(capitallist_id);
			objs.add(query);
		}
		if (ObjectHelper.isNotEmpty(creditEntrustName)) {
			QueryObj query = new QueryObj();
			query.setObj("creditEntrustName");
			query.setElement("String");
			query.setOperator("LK");
			query.setValue(creditEntrustName);
			objs.add(query);
		}
		// 分页查询
		Page<CreditEntrust> creditValue = creditEntrustService.findByHqlConditions(pageable, objs);

		// 组装Vo
		List<CreditEntrustVo> creditEntrustVos = new ArrayList<CreditEntrustVo>();
		if (ObjectHelper.isNotEmpty(creditValue) && ObjectHelper.isNotEmpty(creditValue.getRecords())) {
			for (int i = 0; i < creditValue.getRecords().size(); i++) {
				CreditEntrustVo creditEntrustVo = new CreditEntrustVo(creditValue.getRecords().get(i), new String[] {},
						new String[] { "capitalStatus" });

				// TODO ... liuwei 暂定为调用方法，如果效率低，则修改为存储过程
				Map<String, BigDecimal> sprecialFields = creditEntrustToolService
						.calculateSpecialFields(creditValue.getRecords().get(i), new Date());
				BigDecimal notEquippedPay = sprecialFields.get("notEquippedPay");
				BigDecimal bookBalance = sprecialFields.get("bookBalance");
				BigDecimal cumulativeRecoveryPrincipal = sprecialFields.get("cumulativeRecoveryPrincipal");
				BigDecimal cumulativeRecoveryInterest = sprecialFields.get("cumulativeRecoveryInterest");
				BigDecimal cumulativeRecoveryPenalty = sprecialFields.get("cumulativeRecoveryPenalty");
				BigDecimal cumulativeRecoveryLiqDamages = sprecialFields.get("cumulativeRecoveryLiqDamages");
				creditEntrustVo.setNotEquippedPay(notEquippedPay);
				creditEntrustVo.setBookBalance(bookBalance);
				creditEntrustVo.setCumulativeRecoveryPrincipal(cumulativeRecoveryPrincipal);
				creditEntrustVo.setCumulativeRecoveryInterest(cumulativeRecoveryInterest);
				creditEntrustVo.setCumulativeRecoveryPenalty(cumulativeRecoveryPenalty);
				creditEntrustVo.setCumulativeRecoveryLiqDamages(cumulativeRecoveryLiqDamages);
				creditEntrustVos.add(creditEntrustVo);
			}
		}

		try {

			String tempFilePath = request.getSession().getServletContext().getRealPath("/") + CREDIT_ENTRUST_PATH;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("信托计划资金情况报表".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT) + ".xls" + "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			Map<Integer, Object> totalData = new HashMap<Integer, Object>();

			// 设置数据占用格
			head.add("A4");
			head.add("B4");
			head.add("C4");
			head.add("D4");
			head.add("E4");
			head.add("F4");
			head.add("G4");
			head.add("H4");
			head.add("I4");
			head.add("J4");
			head.add("K4");
			head.add("L4");
			head.add("M4");
			head.add("N4");
			head.add("O4");
			head.add("P4");
			head.add("Q4");
			head.add("R4");
			head.add("S4");
			head.add("T4");
			head.add("U4");
			head.add("V4");
			head.add("W4");
			head.add("X4");
			head.add("Y4");
			head.add("Z4");
			head.add("AA4");
			head.add("AB4");
			head.add("AC4");
			head.add("AD4");
			head.add("AE4");
			head.add("AF4");
			head.add("AG4");
			head.add("AH4");
			head.add("AI4");
			head.add("AJ4");
			head.add("AK4");
			head.add("AL4");
			head.add("AM4");
			head.add("AN4");
			head.add("AO4");
			head.add("AP4");
			head.add("AQ4");
			head.add("AR4");
			head.add("AS4");
			head.add("AT4");
			for (int i = 0; i < creditEntrustVos.size(); i++) {
				Map<Integer, Object> data = new HashMap<Integer, Object>();
				data.put(1, creditEntrustVos.get(i).getCreditEntrustName());
				totalData.put(1, "总计：");

				data.put(2, creditEntrustVos.get(i).getPriorityPrincipal());
				totalData.put(2, changeValue(totalData.get(2), creditEntrustVos.get(i).getPriorityPrincipal()));

				data.put(3, creditEntrustVos.get(i).getCurrency());
				totalData.put(3, changeValue(totalData.get(3), creditEntrustVos.get(i).getCurrency()));

				data.put(4, creditEntrustVos.get(i).getBondTransfer());
				totalData.put(4, changeValue(totalData.get(4), creditEntrustVos.get(i).getBondTransfer()));

				data.put(5, creditEntrustVos.get(i).getRepaymentPrincipal());
				totalData.put(5, changeValue(totalData.get(5), creditEntrustVos.get(i).getRepaymentPrincipal()));

				data.put(6, creditEntrustVos.get(i).getRepaymentInterest());
				totalData.put(6, changeValue(totalData.get(6), creditEntrustVos.get(i).getRepaymentInterest()));

				data.put(7, creditEntrustVos.get(i).getCompensatoryPrincipal());
				totalData.put(7, changeValue(totalData.get(7), creditEntrustVos.get(i).getCompensatoryPrincipal()));

				data.put(8, creditEntrustVos.get(i).getCompensatoryInterest());
				totalData.put(8, changeValue(totalData.get(8), creditEntrustVos.get(i).getCompensatoryInterest()));

				data.put(9, creditEntrustVos.get(i).getBackPrincipal());
				totalData.put(9, changeValue(totalData.get(9), creditEntrustVos.get(i).getBackPrincipal()));

				data.put(10, creditEntrustVos.get(i).getBackInterest());
				totalData.put(10, changeValue(totalData.get(10), creditEntrustVos.get(i).getBackInterest()));

				data.put(11, creditEntrustVos.get(i).getOtherInterest());
				totalData.put(11, changeValue(totalData.get(11), creditEntrustVos.get(i).getOtherInterest()));

				data.put(12, creditEntrustVos.get(i).getPendingPayment());
				totalData.put(12, changeValue(totalData.get(12), creditEntrustVos.get(i).getPendingPayment()));

				data.put(13, creditEntrustVos.get(i).getTrustGuaranteeFund());
				totalData.put(13, changeValue(totalData.get(13), creditEntrustVos.get(i).getTrustGuaranteeFund()));

				data.put(14, creditEntrustVos.get(i).getInflowSubtotal());
				totalData.put(14, changeValue(totalData.get(14), creditEntrustVos.get(i).getInflowSubtotal()));

				data.put(15, creditEntrustVos.get(i).getRefund());
				totalData.put(15, changeValue(totalData.get(15), creditEntrustVos.get(i).getRefund()));

				data.put(16, creditEntrustVos.get(i).getFundsPriorityPrincipal());
				totalData.put(16, changeValue(totalData.get(16), creditEntrustVos.get(i).getFundsPriorityPrincipal()));

				data.put(17, creditEntrustVos.get(i).getFundsPriorityInterest());
				totalData.put(17, changeValue(totalData.get(17), creditEntrustVos.get(i).getFundsPriorityInterest()));

				data.put(18, creditEntrustVos.get(i).getBadPrincipal());
				totalData.put(18, changeValue(totalData.get(18), creditEntrustVos.get(i).getBadPrincipal()));

				data.put(19, creditEntrustVos.get(i).getInferiorPostYield());
				totalData.put(19, changeValue(totalData.get(19), creditEntrustVos.get(i).getInferiorPostYield()));

				data.put(20, creditEntrustVos.get(i).getTrustGuaranteeFundPercent());
				totalData.put(20,
						changeValue(totalData.get(20), creditEntrustVos.get(i).getTrustGuaranteeFundPercent()));

				data.put(21, creditEntrustVos.get(i).getTrustExpense());
				totalData.put(21, changeValue(totalData.get(21), creditEntrustVos.get(i).getTrustExpense()));

				data.put(22, creditEntrustVos.get(i).getBankCustodianFee());
				totalData.put(22, changeValue(totalData.get(22), creditEntrustVos.get(i).getBankCustodianFee()));

				data.put(23, creditEntrustVos.get(i).getTrustServiceFee());
				totalData.put(23, changeValue(totalData.get(23), creditEntrustVos.get(i).getTrustServiceFee()));

				data.put(24, creditEntrustVos.get(i).getExtensionServiceFee());
				totalData.put(24, changeValue(totalData.get(24), creditEntrustVos.get(i).getExtensionServiceFee()));

				data.put(25, creditEntrustVos.get(i).getStampDuty());
				totalData.put(25, changeValue(totalData.get(25), creditEntrustVos.get(i).getStampDuty()));

				data.put(26, creditEntrustVos.get(i).getSflManagementFee());
				totalData.put(26, changeValue(totalData.get(26), creditEntrustVos.get(i).getSflManagementFee()));

				data.put(27, creditEntrustVos.get(i).getCompensatory());
				totalData.put(27, changeValue(totalData.get(27), creditEntrustVos.get(i).getCompensatory()));

				data.put(28, creditEntrustVos.get(i).getBuyBack());
				totalData.put(28, changeValue(totalData.get(28), creditEntrustVos.get(i).getBuyBack()));

				data.put(29, creditEntrustVos.get(i).getCumulativeLoan());
				totalData.put(29, changeValue(totalData.get(29), creditEntrustVos.get(i).getCumulativeLoan()));

				data.put(30, creditEntrustVos.get(i).getOutflowSubtotal());
				totalData.put(30, changeValue(totalData.get(30), creditEntrustVos.get(i).getOutflowSubtotal()));

				data.put(31, creditEntrustVos.get(i).getRetain());
				totalData.put(31, changeValue(totalData.get(31), creditEntrustVos.get(i).getRetain()));

				data.put(32, creditEntrustVos.get(i).getResidualDistribution());
				totalData.put(32, changeValue(totalData.get(32), creditEntrustVos.get(i).getResidualDistribution()));

				data.put(33, creditEntrustVos.get(i).getAllocatedLoan());
				totalData.put(33, changeValue(totalData.get(33), creditEntrustVos.get(i).getAllocatedLoan()));

				data.put(34, creditEntrustVos.get(i).getNotEquippedPay());
				totalData.put(34, changeValue(totalData.get(34), creditEntrustVos.get(i).getNotEquippedPay()));

				data.put(35, creditEntrustVos.get(i).getBookBalance());
				totalData.put(35, changeValue(totalData.get(35), creditEntrustVos.get(i).getBookBalance()));

				data.put(36, creditEntrustVos.get(i).getCumulativeRecoveryPrincipal());
				totalData.put(36,
						changeValue(totalData.get(36), creditEntrustVos.get(i).getCumulativeRecoveryPrincipal()));

				data.put(37, creditEntrustVos.get(i).getCumulativeRecoveryInterest());
				totalData.put(37,
						changeValue(totalData.get(37), creditEntrustVos.get(i).getCumulativeRecoveryInterest()));

				data.put(38, creditEntrustVos.get(i).getCumulativeRecoveryPenalty());
				totalData.put(38,
						changeValue(totalData.get(38), creditEntrustVos.get(i).getCumulativeRecoveryPenalty()));

				data.put(39, creditEntrustVos.get(i).getCumulativeRecoveryLiqDamages());
				totalData.put(39,
						changeValue(totalData.get(39), creditEntrustVos.get(i).getCumulativeRecoveryLiqDamages()));

				data.put(40, creditEntrustVos.get(i).getCapitalStatusName());
				totalData.put(40, creditEntrustVos.get(i).getCapitalStatusName());

				data.put(41, creditEntrustVos.get(i).getTrustPrincipal());
				totalData.put(41, changeValue(totalData.get(41), creditEntrustVos.get(i).getTrustPrincipal()));

				data.put(42, creditEntrustVos.get(i).getTransferAmount());
				totalData.put(42, changeValue(totalData.get(42), creditEntrustVos.get(i).getTransferAmount()));

				data.put(43, creditEntrustVos.get(i).getTransferDate());
				totalData.put(43, creditEntrustVos.get(i).getTransferDate());

				data.put(44, creditEntrustVos.get(i).getMaturityDate());
				totalData.put(44, creditEntrustVos.get(i).getMaturityDate());

				data.put(45, creditEntrustVos.get(i).getCreateDate());
				totalData.put(45, creditEntrustVos.get(i).getCreateDate());

				data.put(46, creditEntrustVos.get(i).getId());
				totalData.put(46, null);

				datalist.add(data);
			}
			String[] heads = head.toArray(new String[head.size()]);// 必须为列表头部所有位置集合，
			// 输出
			// 数据单元格样式和头部单元格样式保持一致
			// 注释掉总计
			//datalist.add(totalData);

			excel.writeDateList(tempFilePath, heads, datalist, 0);

			// 写到输出流并移除资源
			excel.writeAndClose(tempFilePath, os);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导出信托计划资金情况报表失败", e);
		}
		return msg;
	}

	/**
	 * 
	 * @Title: exportPrincipalData
	 * @Description: 导出信托计划本金投入
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/exportPrincipalData")
	@UriKey(key = "com.zdsoft.finance.capital.exportPrincipalData")
	@ResponseBody
	public ResponseMsg exportPrincipalData(HttpServletRequest request, HttpServletResponse response) {

		ResponseMsg msg = new ResponseMsg();
		try {

			// 获取传入参数
			String capitallist_id = request.getParameter("capitalistTempId");
			String creditEntrustName = request.getParameter("creditTempName");

			Map<String, Object> conditions = new HashMap<String, Object>();
			if (ObjectHelper.isNotEmpty(capitallist_id)) {
				conditions.put("capitallist_id", capitallist_id);
			}
			if (ObjectHelper.isNotEmpty(creditEntrustName)) {
				conditions.put("creditEntrustName", creditEntrustName);
			}

			// 多条件查询
			List<CreditEntrustPrincipal> principals = creditEntrustPrincipalService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/") + PRINCIPAL_PATH;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("本金投入明细情况报表".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT) + ".xls" + "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			
			// 占用数据单元格
			head.add("A1");
			head.add("B1");
			head.add("C1");
			head.add("D1");
			head.add("E1");
			head.add("F1");
			head.add("G1");
			head.add("H1");
			head.add("I1");
			head.add("J1");
			head.add("K1");
			head.add("L1");
			for (int i = 0; i < principals.size(); i++) {
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, principals.get(i).getCreditEntrust().getCreditEntrustName());
					data.put(2, principals.get(i).getContribution());
					data.put(3, CED.loadSimpleCodeNameByFullCode(principals.get(i).getContributionType()));
					data.put(4, principals.get(i).getPrincipalAmount());
					data.put(5, principals.get(i).getAddDate());
					data.put(6, principals.get(i).getExpectDate());
					data.put(7, principals.get(i).getActualDate());
					data.put(8, principals.get(i).getMaturityDate());
					data.put(9,
							principals.get(i).getProfitRate() == null ? 0 : principals.get(i).getProfitRate() * 100);
					data.put(10, CED.loadSimpleCodeNameByFullCode(principals.get(i).getPayoutPeriod()));
					data.put(11, principals.get(i).getTermDay());
					data.put(12, principals.get(i).getRemark());
					datalist.add(data);
				} catch (Exception e) {

				}
			}

			String[] heads = head.toArray(new String[head.size()]);// 必须为列表头部所有位置集合，

			// 输出
			// 数据单元格样式和头部单元格样式保持一致
			excel.writeDateList(tempFilePath, heads, datalist, 0);
			// 写到输出流并移除资源
			excel.writeAndClose(tempFilePath, os);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("输出本金投入明细失败", e);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: exportLoanCapitalData
	 * @Description: 非本金跟踪明细导出
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/exportLoanCapitalData")
	@UriKey(key = "com.zdsoft.finance.capital.exportLoanCapitalData")
	@ResponseBody
	public ResponseMsg exportLoanCapitalData(HttpServletRequest request, HttpServletResponse response) {

		ResponseMsg msg = new ResponseMsg();
		try {

			// 获取传入参数
			String capitallist_id = request.getParameter("capitalistTempId");
			String creditEntrustName = request.getParameter("creditTempName");

			Map<String, Object> conditions = new HashMap<String, Object>();
			if (ObjectHelper.isNotEmpty(capitallist_id)) {
				conditions.put("capitallist_id", capitallist_id);
			}
			if (ObjectHelper.isNotEmpty(creditEntrustName)) {
				conditions.put("creditEntrustName", creditEntrustName);
			}
			// 多条件查询
			List<LoanCapital> loanCapitals = loanCapitalService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/") + NON_PRINCIPAL_PATH;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("非本金跟踪明细情况报表".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT) + ".xls" + "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			
			// 占用数据单元格
			head.add("A2");
			head.add("B2");
			head.add("C2");
			head.add("D2");
			head.add("E2");
			head.add("F2");
			head.add("G2");
			head.add("H2");
			head.add("I2");
			head.add("J2");
			head.add("K2");
			head.add("L2");
			head.add("M2");
			head.add("N2");
			head.add("O2");
			head.add("P2");
			head.add("Q2");
			for (int i = 0; i < loanCapitals.size(); i++) {
				List<CreditEntrustFeeItem> creditEntrustFeeItems = creditEntrustFeeItemService
						.findByBusinessId(loanCapitals.get(i).getId());
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, loanCapitals.get(i).getCreditEntrust().getCreditEntrustName());

					data.put(2, CED.loadSimpleCodeNameByFullCode(loanCapitals.get(i).getLenderType()));

					data.put(3, loanCapitals.get(i).getLenderName());

					data.put(4, loanCapitals.get(i).getHappenDate());

					data.put(5, StatusNm.getName(loanCapitals.get(i).getStatus()));

					data.put(6, CED.loadSimpleCodeNameByFullCode(loanCapitals.get(i).getCapitalState()));

					// 存放临时值
					data.put(7, 0);
					data.put(8, 0);
					data.put(9, 0);
					data.put(10, 0);
					data.put(11, 0);
					data.put(12, 0);
					data.put(13, 0);
					data.put(14, 0);
					data.put(15, 0);
					data.put(16, 0);

					// 重新填值
					for (int j = 0; j < creditEntrustFeeItems.size(); j++) {
						CreditEntrustFeeItem creditEntrustFeeItem = creditEntrustFeeItems.get(j);

						if ("capital".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(7, creditEntrustFeeItem.getFeeAmount());
						} else if ("interest".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(8, creditEntrustFeeItem.getFeeAmount());
						} else if ("penalty".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(9, creditEntrustFeeItem.getFeeAmount());
						} else if ("profit".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(10, creditEntrustFeeItem.getFeeAmount());
						} else if ("serviceCharge".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(11, creditEntrustFeeItem.getFeeAmount());
						} else if ("custodyFee".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(12, creditEntrustFeeItem.getFeeAmount());
						} else if ("managementExpense".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(13, creditEntrustFeeItem.getFeeAmount());
						} else if ("counterFee".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(14, creditEntrustFeeItem.getFeeAmount());
						} else if ("financialExpenses".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(15, creditEntrustFeeItem.getFeeAmount());
						} else if ("other".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(16, creditEntrustFeeItem.getFeeAmount());
						}
					}

					data.put(17, loanCapitals.get(i).getRemark());

					datalist.add(data);
				} catch (Exception e) {

				}
			}

			String[] heads = head.toArray(new String[head.size()]);// 必须为列表头部所有位置集合，

			// 输出
			// 数据单元格样式和头部单元格样式保持一致
			excel.writeDateList(tempFilePath, heads, datalist, 0);
			// 写到输出流并移除资源
			excel.writeAndClose(tempFilePath, os);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("输出本金投入明细失败", e);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: exportCostTracking
	 * @Description: 应付费用导出
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/exportCostTracking")
	@UriKey(key = "com.zdsoft.finance.capital.exportCostTracking")
	@ResponseBody
	public ResponseMsg exportCostTracking(HttpServletRequest request, HttpServletResponse response) {

		ResponseMsg msg = new ResponseMsg();
		try {

			// 获取传入参数
			String capitallist_id = request.getParameter("capitalistTempId");
			String creditEntrustName = request.getParameter("creditTempName");

			Map<String, Object> conditions = new HashMap<String, Object>();
			if (ObjectHelper.isNotEmpty(capitallist_id)) {
				conditions.put("capitallist_id", capitallist_id);
			}
			if (ObjectHelper.isNotEmpty(creditEntrustName)) {
				conditions.put("creditEntrustName", creditEntrustName);
			}

			// 多条件查询
			List<CreditCostTracking> creditCostTrackings = creditCostTrackingService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/") + PAYMENT_PATH;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("信托计划往来对象应付费用明细".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT) + ".xls" + "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			
			// 占用excel数据单元格
			head.add("A2");
			head.add("B2");
			head.add("C2");
			head.add("D2");
			head.add("E2");
			head.add("F2");
			head.add("G2");
			head.add("H2");
			head.add("I2");
			head.add("J2");
			head.add("K2");
			head.add("L2");
			head.add("M2");
			head.add("N2");
			head.add("O2");
			head.add("P2");
			for (int i = 0; i < creditCostTrackings.size(); i++) {
				List<CreditEntrustFeeItem> creditEntrustFeeItems = creditEntrustFeeItemService
						.findByBusinessId(creditCostTrackings.get(i).getId());
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, creditCostTrackings.get(i).getCreditEntrust().getCreditEntrustName());

					data.put(2, CED.loadSimpleCodeNameByFullCode(creditCostTrackings.get(i).getExpenditureType()));

					data.put(3, creditCostTrackings.get(i).getCostName());

					data.put(4, creditCostTrackings.get(i).getPayDate());

					data.put(5, StatusNm.getName(creditCostTrackings.get(i).getStatus()));

					// 存放临时值
					data.put(6, 0);
					data.put(7, 0);
					data.put(8, 0);
					data.put(9, 0);
					data.put(10, 0);
					data.put(11, 0);
					data.put(12, 0);
					data.put(13, 0);
					data.put(14, 0);
					data.put(15, 0);

					// 重新填值
					for (int j = 0; j < creditEntrustFeeItems.size(); j++) {
						CreditEntrustFeeItem creditEntrustFeeItem = creditEntrustFeeItems.get(j);
						if ("capital".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(6, creditEntrustFeeItem.getFeeAmount());
						} else if ("interest".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(7, creditEntrustFeeItem.getFeeAmount());
						} else if ("penalty".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(8, creditEntrustFeeItem.getFeeAmount());
						} else if ("profit".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(9, creditEntrustFeeItem.getFeeAmount());
						} else if ("serviceCharge".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(10, creditEntrustFeeItem.getFeeAmount());
						} else if ("custodyFee".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(11, creditEntrustFeeItem.getFeeAmount());
						} else if ("managementExpense".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(12, creditEntrustFeeItem.getFeeAmount());
						} else if ("counterFee".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(13, creditEntrustFeeItem.getFeeAmount());
						} else if ("financialExpenses".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(14, creditEntrustFeeItem.getFeeAmount());
						} else if ("other".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(15, creditEntrustFeeItem.getFeeAmount());
						}
					}

					data.put(16, creditCostTrackings.get(i).getRemark());

					datalist.add(data);
				} catch (Exception e) {

				}
			}

			String[] heads = head.toArray(new String[head.size()]);// 必须为列表头部所有位置集合，

			// 输出
			// 数据单元格样式和头部单元格样式保持一致
			excel.writeDateList(tempFilePath, heads, datalist, 0);
			// 写到输出流并移除资源
			excel.writeAndClose(tempFilePath, os);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("输出本金投入明细失败", e);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: exportSpareCapital
	 * @Description: 备付金导出
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/exportSpareCapital")
	@UriKey(key = "com.zdsoft.finance.capital.exportSpareCapital")
	@ResponseBody
	public ResponseMsg exportSpareCapital(HttpServletRequest request, HttpServletResponse response) {

		ResponseMsg msg = new ResponseMsg();
		try {

			// 获取查询参数
			String capitallist_id = request.getParameter("capitalistTempId");
			String creditEntrustName = request.getParameter("creditTempName");

			Map<String, Object> conditions = new HashMap<String, Object>();
			if (ObjectHelper.isNotEmpty(capitallist_id)) {
				conditions.put("capitallist_id", capitallist_id);
			}
			if (ObjectHelper.isNotEmpty(creditEntrustName)) {
				conditions.put("creditEntrustName", creditEntrustName);
			}

			// 多条件查询
			List<SpareCapital> spareCapitals = spareCapitalService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/") + SPARE_CAPITAL_PATH;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("银联备付资金跟踪明细".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT) + ".xls" + "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			
			// 占用excel数据单元格
			head.add("A1");
			head.add("B1");
			head.add("C1");
			head.add("D1");
			head.add("E1");
			head.add("F1");
			head.add("G1");
			head.add("H1");
			head.add("I1");
			head.add("J1");
			head.add("K1");
			head.add("L1");
			head.add("M1");
			for (int i = 0; i < spareCapitals.size(); i++) {
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, spareCapitals.get(i).getCreditEntrust().getCreditEntrustName());

					data.put(2, CED.loadSimpleCodeNameByFullCode(spareCapitals.get(i).getOperationType()));

					data.put(3, spareCapitals.get(i).getUseDate());

					data.put(4, spareCapitals.get(i).getApplyAmount());

					data.put(5, spareCapitals.get(i).getApplyRemark());

					data.put(6, spareCapitals.get(i).getActualArrivalDate());

					data.put(7, spareCapitals.get(i).getActualAmount());

					data.put(8, spareCapitals.get(i).getActualRemark());

					data.put(9, "");

					data.put(10, spareCapitals.get(i).getCompleteEmpName());

					data.put(11, spareCapitals.get(i).getCompleteDate());

					data.put(12, StatusNm.getName(spareCapitals.get(i).getStatus()));

					data.put(13, spareCapitals.get(i).getActualStatus());

					datalist.add(data);
				} catch (Exception e) {

				}
			}

			String[] heads = head.toArray(new String[head.size()]);// 必须为列表头部所有位置集合，

			// 输出
			// 数据单元格样式和头部单元格样式保持一致
			excel.writeDateList(tempFilePath, heads, datalist, 0);
			// 写到输出流并移除资源
			excel.writeAndClose(tempFilePath, os);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("输出本金投入明细失败", e);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: exportCreditAttom
	 * @Description: 信托计划转让明细导出
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/exportCreditAttom")
	@UriKey(key = "com.zdsoft.finance.capital.exportCreditAttom")
	@ResponseBody
	public ResponseMsg exportCreditAttom(HttpServletRequest request, HttpServletResponse response) {

		ResponseMsg msg = new ResponseMsg();
		try {

			// 获取查询参数
			String capitallist_id = request.getParameter("capitalistTempId");
			String creditEntrustName = request.getParameter("creditTempName");

			Map<String, Object> conditions = new HashMap<String, Object>();
			if (ObjectHelper.isNotEmpty(capitallist_id)) {
				conditions.put("capitallist_id", capitallist_id);
			}
			if (ObjectHelper.isNotEmpty(creditEntrustName)) {
				conditions.put("creditEntrustName", creditEntrustName);
			}

			// 多条件查询
			List<CreditEntrustAttom> attoms = creditEntrustAttomService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/") + ATTOM_PATH;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("信托计划转让明细".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT) + ".xls" + "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			
			// 占用excel数据单元格
			head.add("A2");
			head.add("B2");
			head.add("C2");
			head.add("D2");
			head.add("E2");
			head.add("F2");
			head.add("G2");
			head.add("H2");
			head.add("I2");
			head.add("J2");
			head.add("K2");
			head.add("L2");
			head.add("M2");
			head.add("N2");
			head.add("O2");
			head.add("P2");
			head.add("Q2");
			head.add("R2");
			head.add("S2");
			head.add("T2");
			head.add("U2");
			head.add("V2");
			for (int i = 0; i < attoms.size(); i++) {
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, attoms.get(i).getCreditEntrust().getCreditEntrustName());

					data.put(2, attoms.get(i).getAcceptName());

					data.put(3, attoms.get(i).getAcceptAmount());

					data.put(4, attoms.get(i).getAttomStatus());

					data.put(5, CED.loadSimpleCodeNameByFullCode(attoms.get(i).getAcceptType()));

					data.put(6, attoms.get(i).getOrgCd());

					data.put(7, attoms.get(i).getContactName());

					data.put(8, attoms.get(i).getCardNo());

					data.put(9, attoms.get(i).getAddress());

					data.put(10, attoms.get(i).getMobile());

					data.put(11, attoms.get(i).getPhone());

					data.put(12, (attoms.get(i).getContractProfitRate() * 100));

					data.put(13, attoms.get(i).getAttomContractNo());

					data.put(14, attoms.get(i).getAttomContractNo());

					data.put(15, attoms.get(i).getAttomEffect());

					data.put(16, attoms.get(i).getAttomEndDate());

					data.put(17, attoms.get(i).getAssigneeAccBank());

					data.put(18, attoms.get(i).getAssigneeAccName());

					data.put(19, attoms.get(i).getAssigneeAccNo());

					data.put(20, CED.loadSimpleCodeNameByFullCode(attoms.get(i).getPayoutPeriod()));

					data.put(21, attoms.get(i).getTermDay());

					data.put(22, attoms.get(i).getRemark());

					datalist.add(data);
				} catch (Exception e) {

				}
			}

			String[] heads = head.toArray(new String[head.size()]);// 必须为列表头部所有位置集合，

			// 输出
			// 数据单元格样式和头部单元格样式保持一致
			excel.writeDateList(tempFilePath, heads, datalist, 0);
			// 写到输出流并移除资源
			excel.writeAndClose(tempFilePath, os);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("输出本金投入明细失败", e);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: exportCreditDebit
	 * @Description: 信托计划借方资金跟踪明细导出
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return ResponseMsg处理消息
	 */
	@RequestMapping("/exportCreditDebit")
	@UriKey(key = "com.zdsoft.finance.capital.exportCreditDebit")
	@ResponseBody
	public ResponseMsg exportCreditDebit(HttpServletRequest request, HttpServletResponse response) {

		ResponseMsg msg = new ResponseMsg();
		try {
			String capitallist_id = request.getParameter("capitalistTempId");
			String creditEntrustName = request.getParameter("creditTempName");

			Map<String, Object> conditions = new HashMap<String, Object>();
			if (ObjectHelper.isNotEmpty(capitallist_id)) {
				conditions.put("capitallist_id", capitallist_id);
			}
			if (ObjectHelper.isNotEmpty(creditEntrustName)) {
				conditions.put("creditEntrustName", creditEntrustName);
			}

			List<CreditEntrustDebit> debits = creditEntrustDebitService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/") + DEBIT_PATH;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("借方资金跟踪明细".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT) + ".xls" + "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			
			// 占用excel数据单元格
			head.add("A2");
			head.add("B2");
			head.add("C2");
			head.add("D2");
			head.add("E2");
			head.add("F2");
			head.add("G2");
			head.add("H2");
			head.add("I2");
			head.add("J2");
			head.add("K2");
			head.add("L2");
			head.add("M2");
			head.add("N2");
			head.add("O2");
			head.add("P2");
			head.add("Q2");
			head.add("R2");
			head.add("S2");
			for (int i = 0; i < debits.size(); i++) {

				List<CreditEntrustFeeItem> creditEntrustFeeItems = creditEntrustFeeItemService
						.findByBusinessId(debits.get(i).getId());
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, debits.get(i).getCreditEntrust().getCreditEntrustName());

					data.put(2, CED.loadSimpleCodeNameByFullCode(debits.get(i).getDebitType()));

					data.put(3, CED.loadSimpleCodeNameByFullCode(debits.get(i).getDebtorType()));

					data.put(4, debits.get(i).getObjectName());

					data.put(5, debits.get(i).getExpenditureDate());

					data.put(6, debits.get(i).getActualOutDate());

					data.put(7, debits.get(i).getStatus());

					data.put(8, CED.loadSimpleCodeNameByFullCode(debits.get(i).getCapitalState()));

					// 存放临时值
					data.put(9, 0);
					data.put(10, 0);
					data.put(11, 0);
					data.put(12, 0);
					data.put(13, 0);
					data.put(14, 0);
					data.put(15, 0);
					data.put(16, 0);
					data.put(17, 0);
					data.put(18, 0);

					// 重新填值
					for (int j = 0; j < creditEntrustFeeItems.size(); j++) {
						CreditEntrustFeeItem creditEntrustFeeItem = creditEntrustFeeItems.get(j);

						if ("capital".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(9, creditEntrustFeeItem.getFeeAmount());
						} else if ("interest".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(10, creditEntrustFeeItem.getFeeAmount());
						} else if ("penalty".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(11, creditEntrustFeeItem.getFeeAmount());
						} else if ("profit".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(12, creditEntrustFeeItem.getFeeAmount());
						} else if ("serviceCharge".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(13, creditEntrustFeeItem.getFeeAmount());
						} else if ("custodyFee".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(14, creditEntrustFeeItem.getFeeAmount());
						} else if ("managementExpense".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(15, creditEntrustFeeItem.getFeeAmount());
						} else if ("counterFee".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(16, creditEntrustFeeItem.getFeeAmount());
						} else if ("financialExpenses".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(17, creditEntrustFeeItem.getFeeAmount());
						} else if ("other".equals(creditEntrustFeeItem.getFeeItemCd())) {
							data.put(18, creditEntrustFeeItem.getFeeAmount());
						}
					}

					data.put(19, debits.get(i).getRemark());

					datalist.add(data);
				} catch (Exception e) {

				}
			}

			String[] heads = head.toArray(new String[head.size()]);// 必须为列表头部所有位置集合，

			// 输出
			// 数据单元格样式和头部单元格样式保持一致
			excel.writeDateList(tempFilePath, heads, datalist, 0);
			// 写到输出流并移除资源
			excel.writeAndClose(tempFilePath, os);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("输出本金投入明细失败", e);
		}

		return msg;
	}

	/**
	 * 
	 * @Title: changeValue
	 * @Description: 转换值
	 * @author liuwei
	 * @param val
	 *            旧值
	 * @param val2
	 *            新值
	 * @return BigDecimal转换后的值
	 */
	private BigDecimal changeValue(Object val, Object val2) {
		BigDecimal returnVal = BigDecimal.ZERO;
		if (ObjectHelper.isNotEmpty(val)) {
			if (val instanceof BigInteger) {
				returnVal = (new BigDecimal((BigInteger) val));
			} else if (val instanceof BigDecimal) {
				returnVal = (BigDecimal) val;
			} else {
				returnVal = new BigDecimal(val.toString());
			}

		}
		if (ObjectHelper.isNotEmpty(val2)) {
			if (val2 instanceof BigInteger) {
				returnVal = returnVal.add(new BigDecimal((BigInteger) val2));
			} else if (val2 instanceof BigDecimal) {
				returnVal = returnVal.add((BigDecimal) val2);
			} else {
				returnVal = returnVal.add(new BigDecimal(val2.toString()));
			}
		}

		return returnVal;
	}

}
