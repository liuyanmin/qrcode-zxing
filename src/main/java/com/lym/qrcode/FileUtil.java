package com.lym.qrcode;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 文件操作工具类
 * Created by 刘彦民 on 2018/5/6.
 */
public class FileUtil {

    public static void mkdirs(String dir) {
        if (StringUtils.isEmpty(dir)) {
            return;
        }

        File file = new File(dir);
        if (file.isDirectory()) {
            return;
        } else {
            file.mkdirs();
        }
    }
}
