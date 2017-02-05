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
import com.zdsoft.finance.capital.service.LoanCapitalService;
import com.zdsoft.finance.capital.service.SpareCapitalService;
import com.zdsoft.finance.spi.common.dto.StatusNm;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 信托计划导出excel
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
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
	CED CED;

	/**
	 * 导出信托计划主表
	 * 
	 * @param request
	 * @param response
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/exportAllData")
	@UriKey(key = "com.zdsoft.finance.capital.exportAllData")
	@ResponseBody
	public ResponseMsg exportAllData(HttpServletRequest request, HttpServletResponse response, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();

		String capitallist_id = request.getParameter("capitallist_id");
		String creditEntrustName = request.getParameter("creditEntrustName");
		String state = request.getParameter("state");

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("capitallist_id", capitallist_id);
		conditions.put("creditEntrustName", creditEntrustName);
		conditions.put("state", state);

		Page<Map<String, Object>> creditValue = creditEntrustService.reportSql(pageable, conditions);
		try {
			// String tempFilePath =
			// ExcelUtil.class.getResource("demo.xlsx").getPath();
			String tempFilePath = request.getSession().getServletContext().getRealPath("/")
					+ "/WEB-INF/modules/exportExcel/信托计划资金情况报表.xls";

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("信托计划资金情况报表".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE) + ".xls"
							+ "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			Map<Integer, Object> totalData = new HashMap<Integer, Object>();
			for (int i = 0; i < creditValue.getRecords().size(); i++) {
				Map<Integer, Object> data = new HashMap<Integer, Object>();
				data.put(1, creditValue.getRecords().get(i).get("NAME"));
				totalData.put(1, "总计：");
				head.add("A" + (i + 4));
				data.put(2, creditValue.getRecords().get(i).get("amount1"));
				totalData.put(2, changeValue(totalData.get(2), creditValue.getRecords().get(i).get("amount1")));
				head.add("B" + (i + 4));
				data.put(3, creditValue.getRecords().get(i).get("amount2"));
				totalData.put(3, changeValue(totalData.get(3), creditValue.getRecords().get(i).get("amount2")));
				head.add("C" + (i + 4));
				data.put(4, creditValue.getRecords().get(i).get("amount3"));
				totalData.put(4, changeValue(totalData.get(4), creditValue.getRecords().get(i).get("amount3")));
				head.add("D" + (i + 4));
				data.put(5, creditValue.getRecords().get(i).get("amount4"));
				totalData.put(5, changeValue(totalData.get(5), creditValue.getRecords().get(i).get("amount4")));
				head.add("E" + (i + 4));
				data.put(6, creditValue.getRecords().get(i).get("amount5"));
				totalData.put(6, changeValue(totalData.get(6), creditValue.getRecords().get(i).get("amount5")));
				head.add("F" + (i + 4));
				data.put(7, creditValue.getRecords().get(i).get("amount6"));
				totalData.put(7, changeValue(totalData.get(7), creditValue.getRecords().get(i).get("amount6")));
				head.add("G" + (i + 4));
				data.put(8, creditValue.getRecords().get(i).get("amount7"));
				totalData.put(8, changeValue(totalData.get(8), creditValue.getRecords().get(i).get("amount7")));
				head.add("H" + (i + 4));
				data.put(9, creditValue.getRecords().get(i).get("amount8"));
				totalData.put(9, changeValue(totalData.get(9), creditValue.getRecords().get(i).get("amount8")));
				head.add("I" + (i + 4));
				data.put(10, creditValue.getRecords().get(i).get("amount9"));
				totalData.put(10, changeValue(totalData.get(10), creditValue.getRecords().get(i).get("amount9")));
				head.add("J" + (i + 4));
				data.put(11, creditValue.getRecords().get(i).get("amount10"));
				totalData.put(11, changeValue(totalData.get(11), creditValue.getRecords().get(i).get("amount10")));
				head.add("K" + (i + 4));
				data.put(12, creditValue.getRecords().get(i).get("amount11"));
				totalData.put(12, changeValue(totalData.get(12), creditValue.getRecords().get(i).get("amount11")));
				head.add("L" + (i + 4));
				data.put(13, creditValue.getRecords().get(i).get("amount12"));
				totalData.put(13, changeValue(totalData.get(13), creditValue.getRecords().get(i).get("amount12")));
				head.add("M" + (i + 4));
				data.put(14, creditValue.getRecords().get(i).get("amount13"));
				totalData.put(14, changeValue(totalData.get(14), creditValue.getRecords().get(i).get("amount13")));
				head.add("N" + (i + 4));
				data.put(15, creditValue.getRecords().get(i).get("amount14"));
				totalData.put(15, changeValue(totalData.get(15), creditValue.getRecords().get(i).get("amount14")));
				head.add("O" + (i + 4));
				data.put(16, creditValue.getRecords().get(i).get("amount15"));
				totalData.put(16, changeValue(totalData.get(16), creditValue.getRecords().get(i).get("amount15")));
				head.add("P" + (i + 4));
				data.put(17, creditValue.getRecords().get(i).get("amount16"));
				totalData.put(17, changeValue(totalData.get(17), creditValue.getRecords().get(i).get("amount16")));
				head.add("Q" + (i + 4));
				data.put(18, creditValue.getRecords().get(i).get("amount17"));
				totalData.put(18, changeValue(totalData.get(18), creditValue.getRecords().get(i).get("amount17")));
				head.add("R" + (i + 4));
				data.put(19, creditValue.getRecords().get(i).get("amount18"));
				totalData.put(19, changeValue(totalData.get(19), creditValue.getRecords().get(i).get("amount18")));
				head.add("S" + (i + 4));
				data.put(20, creditValue.getRecords().get(i).get("amount19"));
				totalData.put(20, changeValue(totalData.get(20), creditValue.getRecords().get(i).get("amount19")));
				head.add("T" + (i + 4));
				data.put(21, creditValue.getRecords().get(i).get("amount20"));
				totalData.put(21, changeValue(totalData.get(21), creditValue.getRecords().get(i).get("amount20")));
				head.add("U" + (i + 4));
				data.put(22, creditValue.getRecords().get(i).get("amount21"));
				totalData.put(22, changeValue(totalData.get(22), creditValue.getRecords().get(i).get("amount21")));
				head.add("V" + (i + 4));
				data.put(23, creditValue.getRecords().get(i).get("amount22"));
				totalData.put(23, changeValue(totalData.get(23), creditValue.getRecords().get(i).get("amount22")));
				head.add("W" + (i + 4));
				data.put(24, creditValue.getRecords().get(i).get("amount23"));
				totalData.put(24, changeValue(totalData.get(24), creditValue.getRecords().get(i).get("amount23")));
				head.add("X" + (i + 4));
				data.put(25, creditValue.getRecords().get(i).get("amount24"));
				totalData.put(25, changeValue(totalData.get(25), creditValue.getRecords().get(i).get("amount24")));
				head.add("Y" + (i + 4));
				data.put(26, creditValue.getRecords().get(i).get("amount25"));
				totalData.put(26, changeValue(totalData.get(26), creditValue.getRecords().get(i).get("amount25")));
				head.add("Z" + (i + 4));
				data.put(27, creditValue.getRecords().get(i).get("amount26"));
				totalData.put(27, changeValue(totalData.get(27), creditValue.getRecords().get(i).get("amount26")));
				head.add("AA" + (i + 4));
				data.put(28, creditValue.getRecords().get(i).get("amount27"));
				totalData.put(28, changeValue(totalData.get(28), creditValue.getRecords().get(i).get("amount27")));
				head.add("AB" + (i + 4));
				data.put(29, creditValue.getRecords().get(i).get("amount28"));
				totalData.put(29, changeValue(totalData.get(29), creditValue.getRecords().get(i).get("amount28")));
				head.add("AC" + (i + 4));
				data.put(30, creditValue.getRecords().get(i).get("amount29"));
				totalData.put(30, changeValue(totalData.get(30), creditValue.getRecords().get(i).get("amount29")));
				head.add("AD" + (i + 4));
				data.put(31, creditValue.getRecords().get(i).get("amount30"));
				totalData.put(31, changeValue(totalData.get(31), creditValue.getRecords().get(i).get("amount30")));
				head.add("AE" + (i + 4));
				data.put(32, creditValue.getRecords().get(i).get("amount31"));
				totalData.put(32, changeValue(totalData.get(32), creditValue.getRecords().get(i).get("amount31")));
				head.add("AF" + (i + 4));
				data.put(33, creditValue.getRecords().get(i).get("amount32"));
				totalData.put(33, changeValue(totalData.get(33), creditValue.getRecords().get(i).get("amount32")));
				head.add("AG" + (i + 4));
				data.put(34, creditValue.getRecords().get(i).get("amount33"));
				totalData.put(34, changeValue(totalData.get(34), creditValue.getRecords().get(i).get("amount33")));
				head.add("AH" + (i + 4));
				data.put(35, creditValue.getRecords().get(i).get("amount34"));
				totalData.put(35, changeValue(totalData.get(35), creditValue.getRecords().get(i).get("amount34")));
				head.add("AI" + (i + 4));
				data.put(36, creditValue.getRecords().get(i).get("amount35"));
				totalData.put(36, changeValue(totalData.get(36), creditValue.getRecords().get(i).get("amount35")));
				head.add("AJ" + (i + 4));
				data.put(37, creditValue.getRecords().get(i).get("amount36"));
				totalData.put(37, changeValue(totalData.get(37), creditValue.getRecords().get(i).get("amount36")));
				head.add("AK" + (i + 4));
				data.put(38, creditValue.getRecords().get(i).get("amount37"));
				totalData.put(38, changeValue(totalData.get(38), creditValue.getRecords().get(i).get("amount37")));
				head.add("AL" + (i + 4));
				data.put(39, creditValue.getRecords().get(i).get("amount38"));
				totalData.put(39, changeValue(totalData.get(39), creditValue.getRecords().get(i).get("amount38")));
				head.add("AM" + (i + 4));
				data.put(40, creditValue.getRecords().get(i).get("amount39"));
				totalData.put(40, changeValue(totalData.get(40), creditValue.getRecords().get(i).get("amount39")));
				head.add("AN" + (i + 4));
				data.put(41, creditValue.getRecords().get(i).get("amount40"));
				totalData.put(41, changeValue(totalData.get(41), creditValue.getRecords().get(i).get("amount40")));
				head.add("AO" + (i + 4));
				data.put(42, creditValue.getRecords().get(i).get("amount41"));
				totalData.put(42, changeValue(totalData.get(42), creditValue.getRecords().get(i).get("amount41")));
				head.add("AP" + (i + 4));
				data.put(43, creditValue.getRecords().get(i).get("amount42"));
				totalData.put(43, changeValue(totalData.get(43), creditValue.getRecords().get(i).get("amount42")));
				head.add("AQ" + (i + 4));

				data.put(44, creditValue.getRecords().get(i).get("amount43"));
				totalData.put(44, changeValue(totalData.get(44), creditValue.getRecords().get(i).get("amount43")));
				head.add("AR" + (i + 4));

				data.put(45, creditValue.getRecords().get(i).get("amount44"));
				totalData.put(45, creditValue.getRecords().get(i).get("amount44"));
				head.add("AS" + (i + 4));

				data.put(46, creditValue.getRecords().get(i).get("id"));
				totalData.put(46, creditValue.getRecords().get(i).get("id"));
				head.add("AT" + (i + 4));

				// 流入小计
				BigDecimal inflowsubtotal = new BigDecimal(creditValue.getRecords().get(i).get("amount1") == null ? "0"
						: creditValue.getRecords().get(i).get("amount1").toString())
								.add(creditValue.getRecords().get(i).get("amount2") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount2").toString()))
								.add(creditValue.getRecords().get(i).get("amount3") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount3").toString()))
								.add(creditValue.getRecords().get(i).get("amount4") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount4").toString()))
								.add(creditValue.getRecords().get(i).get("amount5") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount5").toString()))
								.add(creditValue.getRecords().get(i).get("amount6") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount7").toString()))
								.add(creditValue.getRecords().get(i).get("amount7") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount7").toString()))
								.add(creditValue.getRecords().get(i).get("amount8") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount8").toString()))
								.add(creditValue.getRecords().get(i).get("amount9") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount9").toString()))
								.add(creditValue.getRecords().get(i).get("amount10") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount10").toString()))
								.add(creditValue.getRecords().get(i).get("amount11") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount11").toString()))
								.add(creditValue.getRecords().get(i).get("amount12") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount12").toString()));
				data.put(14, inflowsubtotal);
				totalData.put(14, changeValue(totalData.get(14), inflowsubtotal));

				// 流出小计
				BigDecimal outflowsubtotal = new BigDecimal(creditValue.getRecords().get(i).get("amount14") == null
						? "0" : creditValue.getRecords().get(i).get("amount14").toString())
								.add(creditValue.getRecords().get(i).get("amount15") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount15").toString()))
								.add(creditValue.getRecords().get(i).get("amount16") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount16").toString()))
								.add(creditValue.getRecords().get(i).get("amount17") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount17").toString()))
								.add(creditValue.getRecords().get(i).get("amount18") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount18").toString()))
								.add(creditValue.getRecords().get(i).get("amount19") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount19").toString()))
								.add(creditValue.getRecords().get(i).get("amount20") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount20").toString()))
								.add(creditValue.getRecords().get(i).get("amount21") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount21").toString()))
								.add(creditValue.getRecords().get(i).get("amount22") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount22").toString()))
								.add(creditValue.getRecords().get(i).get("amount23") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount23").toString()))
								.add(creditValue.getRecords().get(i).get("amount24") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount24").toString()))
								.add(creditValue.getRecords().get(i).get("amount25") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount25").toString()))
								.add(creditValue.getRecords().get(i).get("amount26") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount26").toString()))
								.add(creditValue.getRecords().get(i).get("amount27") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount27").toString()))
								.add(creditValue.getRecords().get(i).get("amount28") == null ? BigDecimal.ZERO
										: new BigDecimal(creditValue.getRecords().get(i).get("amount28").toString()));

				data.put(30, outflowsubtotal);
				totalData.put(30, changeValue(totalData.get(30), outflowsubtotal));

				// 剩余可分配
				BigDecimal residualDistribution = inflowsubtotal.subtract(outflowsubtotal)
						.subtract(new BigDecimal(creditValue.getRecords().get(i).get("amount31").toString()));

				data.put(32, residualDistribution);
				totalData.put(32, changeValue(totalData.get(32), residualDistribution));

				datalist.add(data);
			}

			Integer finalSize = (creditValue.getRecords().size() + 3);
			head.add("A" + (finalSize + 1));
			head.add("B" + (finalSize + 1));
			head.add("C" + (finalSize + 1));
			head.add("D" + (finalSize + 1));
			head.add("E" + (finalSize + 1));
			head.add("F" + (finalSize + 1));
			head.add("G" + (finalSize + 1));
			head.add("H" + (finalSize + 1));
			head.add("I" + (finalSize + 1));
			head.add("J" + (finalSize + 1));
			head.add("K" + (finalSize + 1));
			head.add("L" + (finalSize + 1));
			head.add("M" + (finalSize + 1));
			head.add("N" + (finalSize + 1));
			head.add("O" + (finalSize + 1));
			head.add("P" + (finalSize + 1));
			head.add("Q" + (finalSize + 1));
			head.add("R" + (finalSize + 1));
			head.add("S" + (finalSize + 1));
			head.add("T" + (finalSize + 1));
			head.add("U" + (finalSize + 1));
			head.add("V" + (finalSize + 1));
			head.add("W" + (finalSize + 1));
			head.add("X" + (finalSize + 1));
			head.add("Y" + (finalSize + 1));
			head.add("Z" + (finalSize + 1));
			head.add("AA" + (finalSize + 1));
			head.add("AB" + (finalSize + 1));
			head.add("AC" + (finalSize + 1));
			head.add("AD" + (finalSize + 1));
			head.add("AE" + (finalSize + 1));
			head.add("AF" + (finalSize + 1));
			head.add("AG" + (finalSize + 1));
			head.add("AH" + (finalSize + 1));
			head.add("AI" + (finalSize + 1));
			head.add("AJ" + (finalSize + 1));
			head.add("AK" + (finalSize + 1));
			head.add("AL" + (finalSize + 1));
			head.add("AM" + (finalSize + 1));
			head.add("AN" + (finalSize + 1));
			head.add("AO" + (finalSize + 1));
			head.add("AP" + (finalSize + 1));
			head.add("AQ" + (finalSize + 1));
			head.add("AR" + (finalSize + 1));
			head.add("AS" + (finalSize + 1));
			head.add("AT" + (finalSize + 1));

			String[] heads = head.toArray(new String[head.size()]);// 必须为列表头部所有位置集合，
			// 输出
			// 数据单元格样式和头部单元格样式保持一致
			datalist.add(totalData);

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
	 * 导出信托计划本金投入
	 * 
	 * @param request
	 * @param response
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/exportPrincipalData")
	@UriKey(key = "com.zdsoft.finance.capital.exportPrincipalData")
	@ResponseBody
	public ResponseMsg exportPrincipalData(HttpServletRequest request, HttpServletResponse response) {

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
			List<CreditEntrustPrincipal> principals = creditEntrustPrincipalService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/")
					+ "/WEB-INF/modules/exportExcel/本金投入明细情况报表.xls";

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("本金投入明细情况报表".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE) + ".xls"
							+ "\"");
			
			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			for (int i = 0; i < principals.size(); i++) {
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, principals.get(i).getCreditEntrust().getCreditEntrustName());
					head.add("A" + (i + 1));

					data.put(2, principals.get(i).getContribution());
					head.add("B" + (i + 1));

					data.put(3, CED.loadSimpleCodeNameByFullCode(principals.get(i).getContributionType()));
					head.add("C" + (i + 1));

					data.put(4, principals.get(i).getPrincipalAmount());
					head.add("D" + (i + 1));

					data.put(5, principals.get(i).getAddDate());
					head.add("E" + (i + 1));

					data.put(6, principals.get(i).getExpectDate());
					head.add("F" + (i + 1));

					data.put(7, principals.get(i).getActualDate());
					head.add("G" + (i + 1));

					data.put(8, principals.get(i).getMaturityDate());
					head.add("H" + (i + 1));

					data.put(9,
							principals.get(i).getProfitRate() == null ? 0 : principals.get(i).getProfitRate() * 100);
					head.add("I" + (i + 1));

					data.put(10, CED.loadSimpleCodeNameByFullCode(principals.get(i).getPayoutPeriod()));
					head.add("J" + (i + 1));

					data.put(11, principals.get(i).getTermDay());
					head.add("K" + (i + 1));

					data.put(12, principals.get(i).getRemark());
					head.add("L" + (i + 1));

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
	 * 非本金跟踪明细导出
	 * 
	 * @param request
	 * @param response
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/exportLoanCapitalData")
	@UriKey(key = "com.zdsoft.finance.capital.exportLoanCapitalData")
	@ResponseBody
	public ResponseMsg exportLoanCapitalData(HttpServletRequest request, HttpServletResponse response) {

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

			List<LoanCapital> loanCapitals = loanCapitalService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/")
					+ "/WEB-INF/modules/exportExcel/非本金跟踪明细情况报表.xls";

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("非本金跟踪明细情况报表".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE) + ".xls"
							+ "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			for (int i = 0; i < loanCapitals.size(); i++) {
				List<CreditEntrustFeeItem> creditEntrustFeeItems = creditEntrustFeeItemService
						.findByBusinessId(loanCapitals.get(i).getId());
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, loanCapitals.get(i).getCreditEntrust().getCreditEntrustName());
					head.add("A" + (i + 2));

					data.put(2, CED.loadSimpleCodeNameByFullCode(loanCapitals.get(i).getLenderType()));
					head.add("B" + (i + 2));

					data.put(3, loanCapitals.get(i).getLenderName());
					head.add("C" + (i + 2));

					data.put(4, loanCapitals.get(i).getHappenDate());
					head.add("D" + (i + 2));

					data.put(5, StatusNm.getName(loanCapitals.get(i).getStatus()));
					head.add("E" + (i + 2));

					data.put(6, CED.loadSimpleCodeNameByFullCode(loanCapitals.get(i).getCapitalState()));
					head.add("F" + (i + 2));

					// 存放临时值
					data.put(7, 0);
					head.add("G" + (i + 2));
					data.put(8, 0);
					head.add("H" + (i + 2));
					data.put(9, 0);
					head.add("I" + (i + 2));
					data.put(10, 0);
					head.add("J" + (i + 2));
					data.put(11, 0);
					head.add("K" + (i + 2));
					data.put(12, 0);
					head.add("L" + (i + 2));
					data.put(13, 0);
					head.add("M" + (i + 2));
					data.put(14, 0);
					head.add("N" + (i + 2));
					data.put(15, 0);
					head.add("O" + (i + 2));
					data.put(16, 0);
					head.add("P" + (i + 2));

					// 重新填值
					for (CreditEntrustFeeItem creditEntrustFeeItem : creditEntrustFeeItems) {
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
					head.add("Q" + (i + 2));

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
	 * 应付费用导出
	 * 
	 * @param request
	 * @param response
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/exportCostTracking")
	@UriKey(key = "com.zdsoft.finance.capital.exportCostTracking")
	@ResponseBody
	public ResponseMsg exportCostTracking(HttpServletRequest request, HttpServletResponse response) {

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

			List<CreditCostTracking> creditCostTrackings = creditCostTrackingService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/")
					+ "/WEB-INF/modules/exportExcel/信托计划往来对象应付费用明细.xls";

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("信托计划往来对象应付费用明细".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE) + ".xls"
							+ "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			for (int i = 0; i < creditCostTrackings.size(); i++) {
				List<CreditEntrustFeeItem> creditEntrustFeeItems = creditEntrustFeeItemService
						.findByBusinessId(creditCostTrackings.get(i).getId());
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, creditCostTrackings.get(i).getCreditEntrust().getCreditEntrustName());
					head.add("A" + (i + 2));

					data.put(2, CED.loadSimpleCodeNameByFullCode(creditCostTrackings.get(i).getExpenditureType()));
					head.add("B" + (i + 2));

					data.put(3, creditCostTrackings.get(i).getCostName());
					head.add("C" + (i + 2));

					data.put(4, creditCostTrackings.get(i).getPayDate());
					head.add("D" + (i + 2));

					data.put(5, StatusNm.getName(creditCostTrackings.get(i).getStatus()));
					head.add("E" + (i + 2));

					// 存放临时值
					data.put(6, 0);
					head.add("F" + (i + 2));
					data.put(7, 0);
					head.add("G" + (i + 2));
					data.put(8, 0);
					head.add("H" + (i + 2));
					data.put(9, 0);
					head.add("I" + (i + 2));
					data.put(10, 0);
					head.add("J" + (i + 2));
					data.put(11, 0);
					head.add("K" + (i + 2));
					data.put(12, 0);
					head.add("L" + (i + 2));
					data.put(13, 0);
					head.add("M" + (i + 2));
					data.put(14, 0);
					head.add("N" + (i + 2));
					data.put(15, 0);
					head.add("O" + (i + 2));

					// 重新填值
					for (CreditEntrustFeeItem creditEntrustFeeItem : creditEntrustFeeItems) {
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
					head.add("P" + (i + 2));

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
	 * 应付费用导出
	 * 
	 * @param request
	 * @param response
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/exportSpareCapital")
	@UriKey(key = "com.zdsoft.finance.capital.exportSpareCapital")
	@ResponseBody
	public ResponseMsg exportSpareCapital(HttpServletRequest request, HttpServletResponse response) {

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

			List<SpareCapital> spareCapitals = spareCapitalService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/")
					+ "/WEB-INF/modules/exportExcel/银联备付资金跟踪明细.xls";

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + new String("银联备付资金跟踪明细".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE) + ".xls"
							+ "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			for (int i = 0; i < spareCapitals.size(); i++) {
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, spareCapitals.get(i).getCreditEntrust().getCreditEntrustName());
					head.add("A" + (i + 1));

					data.put(2, CED.loadSimpleCodeNameByFullCode(spareCapitals.get(i).getOperationType()));
					head.add("B" + (i + 1));

					data.put(3, spareCapitals.get(i).getUseDate());
					head.add("C" + (i + 1));

					data.put(4, spareCapitals.get(i).getApplyAmount());
					head.add("D" + (i + 1));

					data.put(5, spareCapitals.get(i).getApplyRemark());
					head.add("E" + (i + 1));

					data.put(6, spareCapitals.get(i).getActualArrivalDate());
					head.add("F" + (i + 1));

					data.put(7, spareCapitals.get(i).getActualAmount());
					head.add("G" + (i + 1));

					data.put(8, spareCapitals.get(i).getActualRemark());
					head.add("H" + (i + 1));

					data.put(9, "");
					head.add("I" + (i + 1));

					data.put(10, spareCapitals.get(i).getCompleteEmpName());
					head.add("J" + (i + 1));

					data.put(11, spareCapitals.get(i).getCompleteDate());
					head.add("K" + (i + 1));

					data.put(12, StatusNm.getName(spareCapitals.get(i).getStatus()));
					head.add("L" + (i + 1));

					data.put(13, spareCapitals.get(i).getActualStatus());
					head.add("M" + (i + 1));

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
	 * 信托计划转让明细导出
	 * 
	 * @param request
	 * @param response
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/exportCreditAttom")
	@UriKey(key = "com.zdsoft.finance.capital.exportCreditAttom")
	@ResponseBody
	public ResponseMsg exportCreditAttom(HttpServletRequest request, HttpServletResponse response) {

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

			List<CreditEntrustAttom> attoms = creditEntrustAttomService.findByConditions(conditions);

			String tempFilePath = request.getSession().getServletContext().getRealPath("/")
					+ "/WEB-INF/modules/exportExcel/信托计划转让明细.xls";

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + new String("信托计划转让明细".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE) + ".xls"
							+ "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			for (int i = 0; i < attoms.size(); i++) {
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, attoms.get(i).getCreditEntrust().getCreditEntrustName());
					head.add("A" + (i + 2));

					data.put(2, attoms.get(i).getAcceptName());
					head.add("B" + (i + 2));

					data.put(3, attoms.get(i).getAcceptAmount());
					head.add("C" + (i + 2));

					data.put(4, attoms.get(i).getAttomStatus());
					head.add("D" + (i + 2));

					data.put(5, CED.loadSimpleCodeNameByFullCode(attoms.get(i).getAcceptType()));
					head.add("E" + (i + 2));

					data.put(6, attoms.get(i).getOrgCd());
					head.add("F" + (i + 2));

					data.put(7, attoms.get(i).getContactName());
					head.add("G" + (i + 2));

					data.put(8, attoms.get(i).getCardNo());
					head.add("H" + (i + 2));

					data.put(9, attoms.get(i).getAddress());
					head.add("I" + (i + 2));

					data.put(10, attoms.get(i).getMobile());
					head.add("J" + (i + 2));

					data.put(11, attoms.get(i).getPhone());
					head.add("K" + (i + 2));

					data.put(12, (attoms.get(i).getContractProfitRate() * 100));
					head.add("L" + (i + 2));

					data.put(13, attoms.get(i).getAttomContractNo());
					head.add("M" + (i + 2));

					data.put(14, attoms.get(i).getAttomContractNo());
					head.add("N" + (i + 2));

					data.put(15, attoms.get(i).getAttomEffect());
					head.add("O" + (i + 2));

					data.put(16, attoms.get(i).getAttomEndDate());
					head.add("P" + (i + 2));

					data.put(17, attoms.get(i).getAssigneeAccBank());
					head.add("Q" + (i + 2));

					data.put(18, attoms.get(i).getAssigneeAccName());
					head.add("R" + (i + 2));

					data.put(19, attoms.get(i).getAssigneeAccNo());
					head.add("S" + (i + 2));

					data.put(20, CED.loadSimpleCodeNameByFullCode(attoms.get(i).getPayoutPeriod()));
					head.add("T" + (i + 2));

					data.put(21, attoms.get(i).getTermDay());
					head.add("U" + (i + 2));

					data.put(22, attoms.get(i).getRemark());
					head.add("V" + (i + 2));

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
	 * 信托计划借方资金跟踪明细导出
	 * 
	 * @param request
	 * @param response
	 * @param pageable
	 * @return
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

			String tempFilePath = request.getSession().getServletContext().getRealPath("/")
					+ "/WEB-INF/modules/exportExcel/借方资金跟踪明细.xls";

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + new String("借方资金跟踪明细".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE) + ".xls"
							+ "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			for (int i = 0; i < debits.size(); i++) {

				List<CreditEntrustFeeItem> creditEntrustFeeItems = creditEntrustFeeItemService
						.findByBusinessId(debits.get(i).getId());
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, debits.get(i).getCreditEntrust().getCreditEntrustName());
					head.add("A" + (i + 2));

					data.put(2, CED.loadSimpleCodeNameByFullCode(debits.get(i).getDebitType()));
					head.add("B" + (i + 2));

					data.put(3, CED.loadSimpleCodeNameByFullCode(debits.get(i).getDebtorType()));
					head.add("C" + (i + 2));

					data.put(4, debits.get(i).getObjectName());
					head.add("D" + (i + 2));

					data.put(5, debits.get(i).getExpenditureDate());
					head.add("E" + (i + 2));

					data.put(6, debits.get(i).getActualOutDate());
					head.add("F" + (i + 2));

					data.put(7, debits.get(i).getStatus());
					head.add("G" + (i + 2));

					data.put(8, CED.loadSimpleCodeNameByFullCode(debits.get(i).getCapitalState()));
					head.add("H" + (i + 2));

					// 存放临时值
					data.put(9, 0);
					head.add("I" + (i + 2));
					data.put(10, 0);
					head.add("J" + (i + 2));
					data.put(11, 0);
					head.add("K" + (i + 2));
					data.put(12, 0);
					head.add("L" + (i + 2));
					data.put(13, 0);
					head.add("M" + (i + 2));
					data.put(14, 0);
					head.add("N" + (i + 2));
					data.put(15, 0);
					head.add("O" + (i + 2));
					data.put(16, 0);
					head.add("P" + (i + 2));
					data.put(17, 0);
					head.add("Q" + (i + 2));
					data.put(18, 0);
					head.add("R" + (i + 2));

					// 重新填值
					for (CreditEntrustFeeItem creditEntrustFeeItem : creditEntrustFeeItems) {
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
					head.add("S" + (i + 2));

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
