package com.lyun.bookrentalmanagementsystem.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Lease {
    private int id;
    private int userId;
    private int bookId;
    private Date date;
}
