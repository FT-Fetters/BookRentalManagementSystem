package com.lyun.bookrentalmanagementsystem.utils;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class LangUtils {

    private static HashMap<String,String> langMap;

    public static boolean containsLang(String lang){
        if (langMap == null){
            readMap();
        }
        return langMap.containsKey(lang);
    }

    @SneakyThrows
    private static void readMap(){
        File langFile = PathTools.getLangMapFile();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(langFile)));
        String s;
        while ((s= br.readLine())!=null){
            String[] sp = s.split(":");
            langMap.put(sp[0],sp[1]);
        }
    }

}
