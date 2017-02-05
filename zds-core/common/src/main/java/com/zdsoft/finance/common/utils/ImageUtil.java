package com.zdsoft.finance.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 图片工具类
 *
 * @author LiaoGuoWei
 * @create 2016-12-29 17:22
 **/
public class ImageUtil {

    /**
     * 生成带字体的图片
     * @param str
     * @param outFilePath
     * @throws Exception
     */
    public static void createStrImage(String str, String outFilePath) throws Exception {
        File fileOut=new File(outFilePath);
        //判断路径是否存在 如果文件夹不存在则强制生成
        if(!fileOut.getParentFile().exists()) {
            fileOut.getParentFile().mkdirs();
        }
        //设置字体
        Font font = new Font("Courier", Font.PLAIN, 16);

        Rectangle2D r = font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));
        int unitHeight = (int) Math.floor(r.getHeight());//获取单个字符的高度
        //获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int width = (int) Math.round(r.getWidth()) + 1;
        int height = unitHeight + 3;//把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
        //创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);//先用白色填充整张图片,也就是背景
        g.setColor(Color.black);//在换成黑色
        g.setFont(font);//设置画笔字体
        g.drawString(str, 0, font.getSize());//画出字符串
        g.dispose();
        ImageIO.write(image, "png", fileOut);//输出png图片
    }

}
