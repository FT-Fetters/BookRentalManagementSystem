package com.lyun.bookrentalmanagementsystem.service;

import com.lyun.bookrentalmanagementsystem.entity.Lease;

import java.util.List;

public interface LeaseService {
    List<Lease> findAll();
    List<Lease> getByUser(String username);
}
