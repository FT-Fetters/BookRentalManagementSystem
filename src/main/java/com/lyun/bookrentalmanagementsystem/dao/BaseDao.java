package com.lyun.bookrentalmanagementsystem.dao;

import java.util.List;

public interface BaseDao<T> {
    List<T> findAll();
}
