package com.zdsoft.finance.common.utils;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ZUIPdfUtil.java
 * @ClassName: ZUIPdfUtil
 * @Description: 转换pdf工具  itext
 * @author dengyy
 * @date 2017年2月7日 下午5:00:40
 * @version V1.0
 */
public class ZUIPdfUtil {
    
    /**
     * 
     * @Title: newParagraph 
     * @Description: 创建文字
     * @author dengyy 
     * @param content 内容
     * @param align 对齐方式
     *            paragraph.setAlignment(Element.ALIGN_LEFT); 居左 
     *            paragraph.setAlignment(Element.ALIGN_CENTER); 居中
     *            paragraph.setAlignment(Element.ALIGN_RIGHT); 居右
     * @return
     */
    public static Paragraph newParagraph(String content, int align) {
        // 设置字体类型 支持中文显示 需要itext-asian架包
        BaseFont baseFont;
        Paragraph paragraph = null ;
        try {
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(baseFont);
            paragraph = new Paragraph(content, font);
            paragraph.setAlignment(align);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paragraph ;
    }

    /**
     * @Title: newBlankLine
     * @Description: 添加空行
     * @author dengyy
     * @param document
     * @throws DocumentException
     */
    public static void newBlankLine(Document document) throws DocumentException {
        document.add(Chunk.NEWLINE);
    }

    /**
     * 
     * @Title: newImage 
     * @Description: 创建图片
     * @author dengyy 
     * @param imgPath 图片路径
     * @param width 宽
     * @param height 高
     * @param align 对齐方式
     * @return
     */
    public static Image newImage(String imgPath, int width, int height, int align) {
        Image img = null;
        try {
            img = Image.getInstance(imgPath);
            img.scaleAbsolute(width, height);
            img.setAlignment(align);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 
     * @Title: newPdfPCellByRows 
     * @Description: 创建一个跨多行的单元格  只支持汉字
     * @author dengyy 
     * @param rows 所占行数
     * @param paragraph 单元格内容文字
     * @param align 对齐方式
     * @return
     */
    public static PdfPCell newPdfPCellByRows(int rows, Paragraph paragraph, int align) {
        //创建一个table 一行
        PdfPTable iTable = new PdfPTable(1);
        PdfPCell iCell = new PdfPCell();
        iCell.setFixedHeight(iCell.getFixedHeight() * rows);
        iTable.addCell(iCell);
        //设置文字
        iCell.addElement(paragraph);
        //对齐方式
        iCell.setHorizontalAlignment(align);
        PdfPCell cell = new PdfPCell(iTable);
        cell.setBorder(0);
        return cell;
    }

    /**
     * 
     * @Title: newPdfPCellByColspan 
     * @Description: 创建一个跨多列的单元格 支持图片 加图片描述
     * @author dengyy 
     * @param colspan  所占列数
     * @param image  单元格内容图片
     * @param align 对齐方式
     * @param content 表头显示内容 图片描述内容
     * @return
     */
    public static PdfPCell newPdfPCellByColspan(int colspan, Image image, int align,String content ) {
        PdfPTable iTable = new PdfPTable(1);
        iTable.getDefaultCell().setBorderWidth(0);//不显示边框
        //创建单元格
        PdfPCell iCell = new PdfPCell();
        //合并单元格 格数
        iCell.setColspan(colspan);
        //不显示边框
        iCell.setBorder(0); 
        //添加图片
        iCell.addElement(image);
        //对齐方式
        iCell.setHorizontalAlignment(align);
        iTable.addCell(iCell);
        //创建表头
        PdfPHeaderCell header = new PdfPHeaderCell();
        //不显示边框
        header.setBorder(0); 
        header.addElement(ZUIPdfUtil.newParagraph(content, Element.ALIGN_CENTER));
        iTable.addCell(header);
        PdfPCell cell = new PdfPCell(iTable);
        cell.setBorder(0); //不显示边框
        return cell;
    }
    
    /**
     * 
     * @Title: newPdfPCellByColspan 
     * @Description: 创建一个跨多列的单元格 支持图片 加图片描述
     * @author dengyy 
     * @param colspan  所占列数
     * @param paragraph  单元格内容
     * @param align 对齐方式
     * @param content 表头显示内容 图片描述内容
     * @return
     */
    public static PdfPCell newPdfPCellByColspan(int colspan, Paragraph paragraph, int align,String content ) {
        PdfPTable iTable = new PdfPTable(1);
        iTable.getDefaultCell().setBorderWidth(0);//不显示边框
        //创建单元格
        PdfPCell iCell = new PdfPCell();
        //合并单元格 格数
        iCell.setColspan(colspan);
        //不显示边框
        iCell.setBorder(0); 
        //添加图片
        iCell.addElement(paragraph);
        //对齐方式
        iCell.setHorizontalAlignment(align);
        iTable.addCell(iCell);
        //创建表头
        PdfPHeaderCell header = new PdfPHeaderCell();
        //不显示边框
        header.setBorder(0); 
        header.addElement(ZUIPdfUtil.newParagraph(content, Element.ALIGN_CENTER));
        iTable.addCell(header);
        PdfPCell cell = new PdfPCell(iTable);
        cell.setBorder(0); //不显示边框
        return cell;
    }

    /**
     * 
     * @Title: addBackGroundImg 
     * @Description: 添加背景图片
     * @author dengyy 
     * @param document 文档
     * @param imgPath  图片路径
     * @param width 图片宽度
     * @param height 图片高度
     * @param x 图片位置x值 (文档左下角为坐标原点)
     * @param y 图片位置y值
     */
    @SuppressWarnings("unused")
    private static void addBackGroundImg(Document document, String imgPath, float width, float height, int x, int y) {
        Image bakimage;
        try {
            //获取图片
            bakimage = Image.getInstance(imgPath);
            //图形作为文字的背景显示
            bakimage.setAlignment(Image.UNDERLYING);
            //设置显示图片的坐标
            bakimage.setAbsolutePosition(x, y);
            //对齐方式
            bakimage.setAlignment(Element.ALIGN_TOP);
            //设置图片大小
            bakimage.scaleAbsoluteWidth(width);
            bakimage.scaleAbsoluteHeight(height);
            document.add(bakimage);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (DocumentException e) {
            e.printStackTrace();
        }
      
    }

    /**
     * 
     * @Title: newPdfPCell 
     * @Description: 创建单元格(内容为图片)
     * @author dengyy 
     * @param border 边框
     * @param align 对齐方式
     * @param colspan 所占列数
     * @param image 内容(图片对象)
     * @return
     */
    public static PdfPCell newPdfPCell( int border, int align, int colspan, Image image) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(border);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.addElement(image);
        return cell;
    }

    /**
     * 
     * @Title: newPdfPCell 
     * @Description: 创建单元格(内容为文字)
     * @author dengyy 
     * @param border 边框
     * @param align 对齐方式
     * @param colspan 所占列数
     * @param paragraph 内容(文字对象)
     * @return
     */
    public static PdfPCell newPdfPCell(int border, int align, int colspan, Paragraph paragraph) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(border);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.addElement(paragraph);
        return cell;
    }

    /**
     * 
     * @Title: newFont 
     * @Description: 创建字体
     * @author dengyy 
     * @param size  大小
     * @param font 字体
     * @return
     */
    public static Font newFont(int size, int font) {
        BaseFont bfChinese = null;
        try {
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Font(bfChinese, size, font);
    }
    //测试
   /* public static void main(String[] args) throws DocumentException, IOException {
        // step 1: 创建文档对象
        Document document = new Document();
        try {
            // step 2:
            // 我们创建一个文档
            // 并针对PDF格式的流文件
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("c://inSequence.pdf"));
            writer.setStrictImageSequence(true);
            // step 3: 我们打开文档
            document.open();
            // step 4:
            //表格两列
            PdfPTable table1 = new PdfPTable(2); 
            //垂直居中
            table1.setHorizontalAlignment(Element.ALIGN_CENTER); 
            //表格的宽度为100%
            table1.setWidthPercentage(100);
            //两列宽度的比例
            float[] wid1 ={0.5f,0.5f}; 
            table1.setWidths(wid1); 
            table1.getDefaultCell().setBorderWidth(0); //不显示边框
            //图片
            Image jpg = Image.getInstance("c://bar.jpg");
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, new Paragraph(""), Element.ALIGN_CENTER, ""));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市1"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市2"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市3"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市1"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市2"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市3"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市1"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市2"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市3"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市1"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市2"));
            table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, "重庆市3"));
            document.add(table1);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }*/
}
