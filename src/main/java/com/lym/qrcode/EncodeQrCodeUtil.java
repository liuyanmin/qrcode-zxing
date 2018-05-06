package com.lym.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用google开源的zxing 生成qrcode二维码工具类
 * Created by 刘彦民 on 2018/5/6.
 */
public class EncodeQrCodeUtil {
    /**
     * 二维码默认编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 二维码默认格式类型
     */
    private static final String FORMAT = "png";


    /**
     * 生成二维码（带logo）
     * @param content 二维码内容
     * @param width 二维码宽
     * @param height 二维码高
     * @param logoImgPath logo
     * @param logoWidth logo图片宽
     * @param logoHeight logo图片高
     * @param needCompress logo是否压缩
     * @return BufferedImage
     */
    public static BufferedImage createQrCode(String content, int width, int height, String logoImgPath, Integer logoWidth, Integer logoHeight, boolean needCompress) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new ConcurrentHashMap<>();
        // 二维码容错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 字符编码
        hints.put(EncodeHintType.CHARACTER_SET, DEFAULT_CHARSET);
        // 二维码外边框大小
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        width = bitMatrix.getWidth();
        height = bitMatrix.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        if (StringUtils.isEmpty(logoImgPath)) {
            return image;
        }

        // 插入logo图片
        EncodeQrCodeUtil.insertImage(image, logoImgPath, logoWidth, logoHeight, needCompress);

        return image;
    }


    /**
     * 插入LOGO
     * @param source 原二维码
     * @param logoImgPath logo
     * @param logoWidth logo宽
     * @param logoHeight logo高
     * @param needCompress 是否需要压缩logo
     * @throws IOException
     */
    private static void insertImage(BufferedImage source, String logoImgPath, Integer logoWidth, Integer logoHeight, boolean needCompress) throws IOException {
        File file = new File(logoImgPath);
        if (!file.exists()) {
            return;
        }

        Image src = ImageIO.read(file);
        int width = src.getWidth(null);
        int height = src.getHeight(null);

        // 使用指定的logo大小
        if (logoWidth != null && logoWidth > 0) {
            width = logoWidth;
        }
        if (logoHeight != null && logoHeight > 0) {
            height = logoHeight;
        }

        // 压缩logo
        if (needCompress) {
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
        }

        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (source.getWidth() - width) / 2;
        int y = (source.getHeight() - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y ,width, height, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();

    }


    /**
     * 生成带logo的二维码（指定logo宽高）
     * @param content
     * @param width
     * @param height
     * @param logoImgPath
     * @param destPath
     * @param needCompress
     * @throws WriterException
     * @throws IOException
     */
    public static void encode(String content, int width, int height, String logoImgPath, Integer logoWidth, Integer logoHeight, String destPath, boolean needCompress) throws WriterException, IOException {
        BufferedImage image = EncodeQrCodeUtil.createQrCode(content, width, height, logoImgPath, logoWidth, logoHeight, needCompress);
        FileUtil.mkdirs(destPath);
        ImageIO.write(image, FORMAT, new File(destPath));
    }


    /**
     * 生成带logo的二维码（不指定logo宽高，使用logo默认宽高）
     * @param content
     * @param width
     * @param height
     * @param logoImgPath
     * @param destPath
     * @param needCompress
     * @throws WriterException
     * @throws IOException
     */
    public static void encode(String content, int width, int height, String logoImgPath, String destPath, boolean needCompress) throws WriterException, IOException {
        BufferedImage image = EncodeQrCodeUtil.createQrCode(content, width, height, logoImgPath, null, null, needCompress);
        FileUtil.mkdirs(destPath);
        ImageIO.write(image, FORMAT, new File(destPath));
    }


    /**
     * 生成不带logo的二维码
     * @param content
     * @param width
     * @param height
     * @param destPath
     * @throws WriterException
     * @throws IOException
     */
    public static void encode(String content, int width, int height, String destPath) throws WriterException, IOException {
        EncodeQrCodeUtil.encode(content, width, height, null, destPath, false);
    }


    /**
     * 生成带logo的二维码，并输出到指定的输出流（指定logo宽高）
     * @param content
     * @param width
     * @param height
     * @param logoImgPath
     * @param output
     * @param needCompress
     * @throws WriterException
     * @throws IOException
     */
    public static void encode(String content, int width, int height, String logoImgPath, Integer logoWidth, Integer logoHeight, OutputStream output, boolean needCompress) throws WriterException, IOException {
        BufferedImage image = EncodeQrCodeUtil.createQrCode(content, width, height, logoImgPath, logoWidth, logoHeight, needCompress);
        ImageIO.write(image, FORMAT, output);
    }


    /**
     * 生成带logo的二维码，并输出到指定的输出流（不指定logo宽高）
     * @param content
     * @param width
     * @param height
     * @param logoImgPath
     * @param output
     * @param needCompress
     * @throws WriterException
     * @throws IOException
     */
    public static void encode(String content, int width, int height, String logoImgPath, OutputStream output, boolean needCompress) throws WriterException, IOException {
        BufferedImage image = EncodeQrCodeUtil.createQrCode(content, width, height, logoImgPath, null, null, needCompress);
        ImageIO.write(image, FORMAT, output);
    }


    /**
     * 生成不带参数的二维码输出到指定的输出流
     * @param content
     * @param width
     * @param height
     * @param output
     * @throws WriterException
     * @throws IOException
     */
    public static void encode(String content, int width, int height, OutputStream output) throws WriterException, IOException {
        EncodeQrCodeUtil.encode(content, width, height, null, output, false);
    }

}
