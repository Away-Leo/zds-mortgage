package com.zdsoft.finance.common.utils;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.apache.poi.hssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExportExcelUtil {

	private static Document getDoc(String fileContent, String serverPath) throws Exception {
		// 读取模板
		File myFile = new File(serverPath + "\\assets\\ExcelMould.html");
		// 将模板转换为jsoup文档
		Document doc = Jsoup.parse(myFile, "UTF-8", "");
		// 获得模板中的table
		Element element = doc.getElementById("content_table");
		// 将需要导出的数据写入
		element.append(fileContent);
		// 排除干扰标签
		doc.getElementById("datagrid-header-check").remove();
		Elements elements = doc.select("td[style=\"display:none\"]").remove();
		return doc;
	}

	/// 这个方法用于根据trs行数和sheet画出整个表格
	private static void mergeColRow(Elements trs, WritableSheet sheet) throws RowsExceededException, WriteException {
		int[][] rowhb = new int[300][50];
		for (int i = 0; i < trs.size(); i++) {
			Element tr = trs.get(i);
			Elements tds = tr.getElementsByTag("td");
			Elements ths = tr.getElementsByTag("th");

			int realColNum = 0;
			// 循环th
			for (int j = 0; j < ths.size(); j++) {
				Element th = ths.get(j);
				if (rowhb[i][realColNum] != 0) {
					realColNum = getRealColNum(rowhb, i, realColNum);
				}
				int rowspan = 1;
				int colspan = 1;
				if (th.attr("rowspan") != "") {
					rowspan = Integer.parseInt(th.attr("rowspan"));
				}
				if (th.attr("colspan") != "") {
					colspan = Integer.parseInt(th.attr("colspan"));
				}
				String text = th.text();
				drawMegerCell(rowspan, colspan, sheet, realColNum, i, text, rowhb);
				realColNum = realColNum + colspan;
			}
			// 循环td
			for (int j = 0; j < tds.size(); j++) {
				Element td = tds.get(j);
				if (rowhb[i][realColNum] != 0) {
					realColNum = getRealColNum(rowhb, i, realColNum);
				}
				int rowspan = 1;
				int colspan = 1;
				if (td.attr("rowspan") != "") {
					rowspan = Integer.parseInt(td.attr("rowspan"));
				}
				if (td.attr("colspan") != "") {
					colspan = Integer.parseInt(td.attr("colspan"));
				}
				String text = td.text();
				drawMegerCell(rowspan, colspan, sheet, realColNum, i, text, rowhb);
				realColNum = realColNum + colspan;
			}

		}
	}

	/**
	 * 根据样式画出单元格，并且根据rowpan和colspan合并单元格
	 * 
	 * @param rowspan
	 * @param colspan
	 * @param sheet
	 * @param realColNum
	 * @param realRowNum
	 * @param text
	 * @param rowhb
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private static void drawMegerCell(int rowspan, int colspan, WritableSheet sheet, int realColNum, int realRowNum,
			String text, int[][] rowhb) throws RowsExceededException, WriteException {

		for (int i = 0; i < rowspan; i++) {
			for (int j = 0; j < colspan; j++) {
				if (i != 0 || j != 0) {
					text = "";
				}
				Label label = new Label(realColNum + j, realRowNum + i, text);
				WritableFont countents = new WritableFont(WritableFont.TIMES, 10); // 设置单元格内容，字号12
				WritableCellFormat cellf = new WritableCellFormat(countents);
				cellf.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
				cellf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居
				label.setCellFormat(cellf);
				sheet.addCell(label);
				rowhb[realRowNum + i][realColNum + j] = 1;
			}
		}
		sheet.mergeCells(realColNum, realRowNum, realColNum + colspan - 1, realRowNum + rowspan - 1);
	}

	private static int getRealColNum(int[][] rowhb, int i, int realColNum) {
		while (rowhb[i][realColNum] != 0) {
			realColNum++;
		}
		return realColNum;
	}

	/// 根据colgroups设置表格的列宽
	private static void setColWidth(Elements colgroups, WritableSheet sheet) {
		if (colgroups.size() > 0) {
			Element colgroup = colgroups.get(0);
			Elements cols = colgroup.getElementsByTag("col");
			for (int i = 0; i < cols.size(); i++) {
				Element col = cols.get(i);
				String strwd = col.attr("width");
				if (col.attr("width") != "") {
					int wd = Integer.parseInt(strwd);
					sheet.setColumnView(i, wd / 8);
				}

			}

		}
	}

	/**
	 * 是根据html文件地址生成对应的xls
	 * 
	 * @param fileName
	 * @param htmlContent
	 * @param serverPath
	 * @return
	 * @throws Exception
	 */
	public static File toExcel(String fileName, String htmlContent, String serverPath) throws Exception {
		Document doc = getDoc(htmlContent, serverPath);
		String title = doc.title();
		/// 得到样式，以后可以根据正则表达式解析css，暂且没有找到cssparse
		Elements style = doc.getElementsByTag("style");
		/// 得到Table，demo只演示输入一个table，以后可以用循环遍历tables集合输入所有table
		Elements tables = doc.getElementsByTag("TABLE");
		if (tables.size() == 0) {
			return null;
		}
		Element table = tables.get(0);
		// 得到所有行
		Elements trs = table.getElementsByTag("tr");

		/// 得到列宽集合
		Elements colgroups = table.getElementsByTag("colgroup");
		// 文件保存到classpath目录下面
		String path = "D:/Upload/";
		path += fileName + ".xls";
		System.out.println("path==============================" + path);
		File file = new File(path);
		//如果文件夹不存在则创建文件夹
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		WritableWorkbook book = Workbook.createWorkbook(file);
		WritableSheet sheet = book.createSheet(fileName, 0);
		setColWidth(colgroups, sheet);
		mergeColRow(trs, sheet);
		book.write();
		book.close();
		return file;
	}

	/**
	 * 不根据html模板生成excel 另外一种导出excel模式 根据传进的参数用apache的poi生成excel
	 * 
	 * @param paras
	 *            需要生成的数据
	 * @param fileName
	 *            文件名称
	 * @return
	 * @throws Exception
	 */
	private static HSSFWorkbook buildExcel(List<Map<String, Object>> paras, String fileName) throws Exception {
		if (ObjectHelper.isNotEmpty(paras) && paras.size() > 0 && ObjectHelper.isNotEmpty(fileName)) {
			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 生成一个表格
			fileName = URLDecoder.decode(fileName, "UTF-8");
			HSSFSheet sheet = workbook.createSheet(fileName.substring(0, fileName.lastIndexOf(".")));
			// 生成一个样式
			HSSFCellStyle style2 = workbook.createCellStyle();
			// style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			// style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			// style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			// style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 生成另一个字体
			HSSFFont font2 = workbook.createFont();
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			// 把字体应用到当前的样式
			style2.setFont(font2);

			for (int i = 0; i < paras.size(); i++) {
				// 根据数据生成行
				HSSFRow row = sheet.createRow(i);
				// 得到一行的数据
				Map<String, Object> rowData = paras.get(i);
				// 得到一行数据的键集合
				Set<String> keys = rowData.keySet();
				int index = 0;
				if (ObjectHelper.isNotEmpty(keys)) {
					for (String temp : keys) {
						// 根据数据生成单元格
						HSSFCell cell = row.createCell(index);
						cell.setCellStyle(style2);
						HSSFRichTextString text = new HSSFRichTextString(
								ObjectHelper.isNotEmpty(rowData.get(temp)) ? rowData.get(temp) + "" : "");
						cell.setCellValue(text);
						index++;
					}
				} else {
					throw new BusinessException("10010004", "根据参数未找到相应值！");
				}
			}
			return workbook;
		} else {
			throw new BusinessException("10010002", "传入参数为空！");
		}
	}

	/**
	 * apache poi导出excel外部调用主方法
	 * 
	 * @param request
	 * @param response
	 * @param paras
	 * @param fileName
	 * @throws Exception
	 */
	public static void exportExcel(HttpServletRequest request, HttpServletResponse response,
			List<Map<String, Object>> paras, String fileName) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-download");
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		HSSFWorkbook workBook = ExportExcelUtil.buildExcel(paras, fileName);
		OutputStream out = response.getOutputStream();
		workBook.write(out);
		out.close();
	}

}