package com.ning.utils;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 *
 * */
public class OvalImageUtil extends Canvas {
    private String relativePath;//图片名称
    private int photoSize;//图片大小
    public OvalImageUtil( String relativePath,int photoSize) {
        this.relativePath=relativePath;
        this.photoSize=photoSize;
    }
    @Override
    public void paint(Graphics g) {
        InputStream imageInputStream=null;
        try {
            imageInputStream= Thread.currentThread().getContextClassLoader().getResourceAsStream(relativePath);
            assert imageInputStream != null;
            BufferedImage srcImage= ImageIO.read(imageInputStream);
            srcImage = Thumbnails.of(srcImage).size(photoSize, photoSize).asBufferedImage();
            BufferedImage image=new BufferedImage(photoSize,photoSize,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillOval(0,0,photoSize,photoSize);
            g2d.setComposite(AlphaComposite.SrcIn);
            g2d.drawImage(srcImage,0,0,photoSize,photoSize,null);
            g.drawImage(image,0,0,photoSize,photoSize,null);
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

    }

}
