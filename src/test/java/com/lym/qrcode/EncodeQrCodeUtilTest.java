package com.lym.qrcode;

import com.google.zxing.WriterException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 二维码生成测试类
 * Created by 刘彦民 on 2018/5/6.
 */
public class EncodeQrCodeUtilTest {
    private File logoFile;
    private String logoPath;
    /**
     * logo所在的目录
     */
    private String directory;

    @Before
    public void init() {
        logoFile = new File("src/main/resources/image/logo_small.png");
        logoPath = logoFile.getAbsolutePath();
        directory = logoPath.substring(0, logoPath.lastIndexOf(File.separator) + 1);
    }

    @Test
    public void testEncode() throws IOException, WriterException {
        String content = "https://github.com/liuyanmin";
        String destPath = directory + "qrcode.png";
        EncodeQrCodeUtil.encode(content, 300, 300, logoPath, destPath, true);
    }

}
