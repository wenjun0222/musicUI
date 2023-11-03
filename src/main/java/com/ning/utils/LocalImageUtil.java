package com.ning.utils;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class LocalImageUtil {
    /**
     * 获取本地方形图片
     * */
    public  static ImageIcon getLocalSquareImage(String relativePath,int photoSize){
        BufferedImage image= getImage(relativePath, photoSize,photoSize, "");
        ImageIcon imageIcon=new ImageIcon(image);
        return imageIcon;
    }
    /**
     * 获取本地圆形图片
     * */
    public  static ImageIcon  getLocalOvalImage(String relativePath,int photoSize){
        BufferedImage image= getImage(relativePath, photoSize, photoSize,"oval");
        ImageIcon imageIcon=new ImageIcon(image);
        return imageIcon;
    }
    /**
     * 获取本地方形图片，倒圆角30
     * */
    public static ImageIcon getRectangleImage(String relativePath,int width,int height){
        BufferedImage image= getImage(relativePath, width,height, "rect");
        ImageIcon imageIcon=new ImageIcon(image);
        return imageIcon;
    }
    private static BufferedImage getImage(String relativePath,int width,int height,String shape){
        InputStream imageInputStream=null;
        BufferedImage copyImage=null;
        int x=0;
        int y=0;
        int arcSize=30;
        try {
            imageInputStream= LocalImageUtil.class.getClassLoader().getResourceAsStream(relativePath);
            assert imageInputStream!= null;
            BufferedImage srcImage=ImageIO.read(imageInputStream);
            if(width==0&& height==0){

            }else{
                srcImage =Thumbnails.of(srcImage).size(width, height).asBufferedImage();
            }
            // 创建一个继承于BufferedImage的对象，大小为width*height
            copyImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
            // 给继承自BufferedImage的对象一个透明的背景颜色
            Graphics2D g2 = copyImage.createGraphics();
            // 将原图以圆形遮罩的形式绘制到继承自BufferedImage的对象上
            if(shape.equals("oval")) {
                // 创建圆形遮罩
                Ellipse2D.Double circle = new Ellipse2D.Double(x, y, width, height);
                g2.setClip(circle);
            }
            else if(shape.equals("rect")){
                RoundRectangle2D.Double circle = new RoundRectangle2D.Double(x,y,width, height,arcSize,arcSize);
                g2.setClip(circle);
            }
            g2.drawImage(srcImage,x,y,width, height,null);
            g2.dispose();
            //在圆图外面再画一个圆
            //新创建一个graphics，这样画的圆不会有锯齿
            g2 = copyImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Stroke s = new BasicStroke(2F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2.setStroke(s);
            if(shape.equals("oval")){
                g2.drawOval(x,y,width, height);
            }else if(shape.equals("rect")){
                g2.drawRoundRect(x,y,width, height,arcSize,arcSize);
            }
            g2.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(imageInputStream!=null){
                try {
                    imageInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return copyImage;
    }

    public static void main(String[] args) {
        String path = LocalImageUtil.class.getClassLoader().getResource("").getPath();
        System.out.println(path);
    }
}
