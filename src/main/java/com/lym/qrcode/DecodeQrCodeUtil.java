package com.lym.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用google开源的zxing 解析qrcode二维码工具类
 * Created by 刘彦民 on 2018/5/6.
 */
public class DecodeQrCodeUtil {
    /**
     * 二维码默认编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 二维码默认格式类型
     */
    private static final String FORMAT = "png";


    /**
     * 二维码解析
     * @param file
     * @throws IOException
     * @throws NotFoundException
     * @return 返回得到的二维码内容
     */
    public static String decode(File file) throws IOException, NotFoundException {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            return null;
        }

        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Map<DecodeHintType, Object> hints = new ConcurrentHashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, DEFAULT_CHARSET);

        Result result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();

        return resultStr;
    }


    /**
     * 二维码解析
     * @param path 二维码存储位置
     * @throws IOException
     * @throws NotFoundException
     * @return 返回二维码内容
     */
    public static String decode(String path) throws IOException, NotFoundException {
        return DecodeQrCodeUtil.decode(new File(path));
    }

}
