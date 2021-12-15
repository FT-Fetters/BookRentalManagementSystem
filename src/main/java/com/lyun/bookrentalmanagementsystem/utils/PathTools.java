package com.lyun.bookrentalmanagementsystem.utils;

import lombok.SneakyThrows;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.ResourceUtils;

import java.io.File;

public class PathTools {
    //linux和windows下通用
    private static String getJarFilePath() {
        ApplicationHome home = new ApplicationHome(PathTools.class);
        File jarFile = home.getSource();
        return jarFile.getParentFile().toString();
    }

    /**
     * 获取运行环境所在的目录
     * @return 运行目录
     */
    public static String getRunPath(){
        File jarFile = new File(getJarFilePath());
        return jarFile.getParent() + "/policelearing";
    }
    public static String getImagePath(){
        File jarFile = new File(getJarFilePath());
        return jarFile.getParent()+"\\src\\main\\resources\\image";
    }

    @SneakyThrows
    public static String getStaticPath(){
        return ResourceUtils.getFile("classpath:static").getAbsolutePath();
    }

    @SneakyThrows
    public static File getLangMapFile(){
        return ResourceUtils.getFile("classpath:static/langMap.txt");
    }
}
