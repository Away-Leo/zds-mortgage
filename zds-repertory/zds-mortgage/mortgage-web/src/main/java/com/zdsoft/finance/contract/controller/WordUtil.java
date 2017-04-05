package com.zdsoft.finance.contract.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.drools.compiler.lang.dsl.DSLMapParser.mapping_file_return;

import com.dyuproject.protostuff.StringSerializer.STRING;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.zdsoft.finance.contract.vo.CustomXWPFDocument;

public class WordUtil {

    static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。

    static final int wdFormatPDF = 17;// word转PDF 格式

    static final int ppSaveAsPDF = 32;// ppt 转PDF 格式

    public static CustomXWPFDocument generateWord(Map<String, Object> param, String template) {
        CustomXWPFDocument doc = null;
        XWPFTable table = null;
        List<XWPFTableRow> rows = null;
        List<XWPFTableCell> cells = null;
        List<XWPFParagraph> paragraphListTable = null;
        try {
            OPCPackage pack = POIXMLDocument.openPackage(template);
            doc = new CustomXWPFDocument(pack);
            if (param != null && param.size() > 0) {

                List<XWPFParagraph> paragraphList = doc.getParagraphs();
                processParagraphs(paragraphList, param, doc);

                Iterator<XWPFTable> it = doc.getTablesIterator();
                while (it.hasNext()) {
                    table = it.next();
                    rows = table.getRows();
                    for (XWPFTableRow row : rows) {
                        cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            paragraphListTable = cell.getParagraphs();
                            processParagraphs(paragraphListTable, param, doc);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    @SuppressWarnings("unchecked")
    public static void processParagraphs(List<XWPFParagraph> paragraphList, Map<String, Object> param, CustomXWPFDocument doc) {
        List<XWPFRun> runs = null;
        String text = null;
        boolean isSetText = false;
        String key = null;
        Object value = null;
        Map<String, Object> pic = null;
        int width = 0;
        int height = 0;
        int picType = 0;
        byte[] byteArray = null;
        ByteArrayInputStream byteInputStream = null;
        int ind = 0;
        if (paragraphList != null && paragraphList.size() > 0) {
            for (XWPFParagraph paragraph : paragraphList) {
                runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    isSetText = false;
                    text = run.getText(0);
                    if (text != null) {
                        for (Entry<String, Object> entry : param.entrySet()) {
                            key = entry.getKey();
                            if (text.indexOf(key) != -1) {
                                isSetText = true;
                                value = entry.getValue();
                                if (value instanceof String || value instanceof Character) {//
                                    text = text.replace(key, value.toString());
                                } else if (value instanceof Map) {//
                                    text = text.replace(key, "");
                                    pic = (Map<String, Object>) value;
                                    width = Integer.parseInt(pic.get("width").toString());
                                    height = Integer.parseInt(pic.get("height").toString());
                                    picType = getPictureType(pic.get("type").toString());
                                    byteArray = (byte[]) pic.get("content");
                                    byteInputStream = new ByteArrayInputStream(byteArray);
                                    try {
                                        // ind = doc.addPicture(byteInputStream, picType);
                                        // doc.createPicture(ind, width, height, paragraph);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        if (isSetText) {
                            run.setText(text, 0);
                        }
                    }
                }
            }
        }
    }

    private static int getPictureType(String picType) {
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if (picType != null) {
            if (picType.equalsIgnoreCase("png")) {
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            } else if (picType.equalsIgnoreCase("dib")) {
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            } else if (picType.equalsIgnoreCase("emf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            } else if (picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")) {
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            } else if (picType.equalsIgnoreCase("wmf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

    public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) {
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isClose) {
                try {
                    in.close();
                } catch (Exception e2) {
                    System.out.println("鍏抽棴娴佸け璐�");
                }
            }
        }
        return byteArray;
    }

    public static void word2pdf(String source, String target) {
        System.out.println("启动Word");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);

            Dispatch docs = app.getProperty("Documents").toDispatch();
            System.out.println("打开文档" + source);
            Dispatch doc = Dispatch.call(
                    docs, //
                    "Open", //
                    source, // FileName
                    false, // ConfirmConversions
                    true // ReadOnly
            ).toDispatch();

            System.out.println("转换文档到PDF " + target);
            File tofile = new File(target);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(
                    doc, //
                    "SaveAs", //
                    target, // FileName
                    wdFormatPDF);

            Dispatch.call(doc, "Close", false);
            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            if (app != null) app.invoke("Quit", wdDoNotSaveChanges);
        }
    }

    public static Map<String, Object> getKeyWord(String template) {
        Map<String, Object> hsLabel = new HashMap<String, Object>();
        CustomXWPFDocument doc = null;
        XWPFTable table = null;
        List<XWPFTableRow> rows = null;
        List<XWPFTableCell> cells = null;
        List<XWPFParagraph> paragraphListTable = null;

        try {
            OPCPackage pack = POIXMLDocument.openPackage(template);
            doc = new CustomXWPFDocument(pack);

            List<XWPFParagraph> paragraphList = doc.getParagraphs();
            processParagraphsKeyWord(paragraphList, doc, hsLabel);

            Iterator<XWPFTable> it = doc.getTablesIterator();
            while (it.hasNext()) {
                table = it.next();
                rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        paragraphListTable = cell.getParagraphs();
                        processParagraphsKeyWord(paragraphListTable, doc, hsLabel);
                    }
                }
            }
            pack.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hsLabel;
    }

    public static void processParagraphsKeyWord(List<XWPFParagraph> paragraphList, CustomXWPFDocument doc, Map<String, Object> hsLabel) {
        // Map<String, Object> hsLabel = new HashMap<String, Object>();

        List<XWPFRun> runs = null;
        String text = null;
        boolean isSetText = false;
        String key = null;
        Object value = null;
        Map<String, Object> pic = null;
        int width = 0;
        int height = 0;
        int picType = 0;
        byte[] byteArray = null;
        ByteArrayInputStream byteInputStream = null;
        int ind = 0;
        if (paragraphList != null && paragraphList.size() > 0) {
            for (XWPFParagraph paragraph : paragraphList) {

                text = paragraph.getParagraphText();

                List<String> listLabel = getTeacherList(text);
                for (String item : listLabel) {
                    hsLabel.put(item, "");
                }

            }
        }

    }

    public static List<String> getTeacherList(String managers) {
        List<String> ls = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\{([^\\}]+)\\}");
        Matcher matcher = pattern.matcher(managers);
        while (matcher.find())
            ls.add(matcher.group());
        return ls;
    }
}
