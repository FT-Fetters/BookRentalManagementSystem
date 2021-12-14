package com.lyun.bookrentalmanagementsystem.entity;

import lombok.Data;

import java.time.Year;

@Data
public class Book {
    private int id;
    private String name;
    private String author;
    private String press;
    private String type;
    private Year pubdate;
    private String lang;
}
