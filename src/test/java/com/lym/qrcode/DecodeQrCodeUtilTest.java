package com.lym.qrcode;

import com.google.zxing.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 二维码解析测试类
 * Created by 刘彦民 on 2018/5/6.
 */
public class DecodeQrCodeUtilTest {
    private File logoFile;
    private String logoPath;
    private String directory;

    @Before
    public void init() {
        logoFile = new File("src/main/resources/image/qrcode.png");
        logoPath = logoFile.getAbsolutePath();
        directory = logoPath.substring(0, logoPath.lastIndexOf(File.separator) + 1);
    }


    @Test
    public void testDecode() throws IOException, NotFoundException {
        String content = DecodeQrCodeUtil.decode(logoFile);
        System.out.println(content);
    }

}
