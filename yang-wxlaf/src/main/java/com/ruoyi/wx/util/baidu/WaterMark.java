package com.ruoyi.wx.util.baidu;

import com.ruoyi.wx.util.baidu.domain.Unit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 图片处理类 支持局部打码
 */
public class WaterMark {

    /**
     * 给图片指定位置打马赛克
     *
     *  x 图片要打码区域左上角的横坐标
     *  y 图片要打码区域左上角的纵坐标
     *  width 图片要打码区域的宽度
     *  height 图片要打码区域的高度
     *  mosaicSize 马赛克尺寸，即每个矩形的长宽
     *  @param imagePath  图片位置
     *  @param units   打码单元列表
     */
    public static boolean sfzMosaic(String imagePath,List<Unit> units) throws IOException {

        int mosaicSize = 20;
        String path = imagePath;
        //读取该图片
        BufferedImage bi = ImageIO.read(new FileInputStream(path));
        //复制一份画布，在新画布上作画，因为要用到原来画布的颜色信息
        BufferedImage spinImage = new BufferedImage(bi.getWidth(),
                bi.getHeight(), BufferedImage.TYPE_INT_RGB);

        //3. 绘制马赛克(在指定范围内绘制矩形并填充颜色)
        Graphics gs = spinImage.getGraphics();
        //将老画布内容画到新画布上
        gs.drawImage(bi, 0, 0, null);
        //马赛克块大小 不能大于图片宽度和高度
        if (mosaicSize > bi.getWidth() || mosaicSize > bi.getHeight() || mosaicSize <= 0) {
            System.err.println("马赛克尺寸设置不正确");
            return false;
        }
        for (Unit item: units
             ) {
            int x = item.getLocation().getLeft();
            int y = item.getLocation().getTop();
            int width = item.getLocation().getWidth();
            int height = item.getLocation().getHeight();
            //2. 设置各方向绘制的马赛克块个数
            int xcount = width / mosaicSize; // x方向绘制个数
            int ycount = height / mosaicSize; // y方向绘制个数
            if ((width % mosaicSize) != 0) {
                xcount++;
            }
            if ((height % mosaicSize) != 0) {
                ycount++;
            }
            //3. 绘制马赛克(在指定范围内绘制矩形并填充颜色)
//            Graphics gs = spinImage.getGraphics();
            //将老画布内容画到新画布上
            //gs.drawImage(bi, 0, 0, null);
            int xTmp = x;
            int yTmp = y;
            for (int i = 0; i < xcount; i++) {
                for (int j = 0; j < ycount; j++) {
                    //马赛克矩形大小
                    int mwidth = mosaicSize;
                    int mheight = mosaicSize;
                    if (i == xcount - 1) {   //横向最后一个比较特殊，可能不够一个size
                        mwidth = width - xTmp;
                    }
                    if (j == ycount - 1) {  //同理
                        mheight = height - yTmp;
                    }
                    //矩形颜色取中心像素点RGB值
                    int centerX = xTmp;
                    int centerY = yTmp;
                    if (mwidth % 2 == 0) {
                        centerX += mwidth / 2;
                    } else {
                        centerX += (mwidth - 1) / 2;
                    }
                    if (mheight % 2 == 0) {
                        centerY += mheight / 2;
                    } else {
                        centerY += (mheight - 1) / 2;
                    }
                    Color color = new Color(bi.getRGB(centerX, centerY));//获取矩形颜色取中心像素点RGB值
                    gs.setColor(color);//设置绘制颜色
                    gs.fillRect(xTmp, yTmp, mwidth, mheight);//画着色块
                    yTmp += mosaicSize;// 计算下一个矩形的y坐标
                }
                yTmp = y;// 还原y坐标
                xTmp += mosaicSize;// 计算x坐标
            }
        }

        gs.dispose();
        ImageIO.write(spinImage, "jpg", new FileOutputStream(path)); // 保存图片
        return true;
    }

}
