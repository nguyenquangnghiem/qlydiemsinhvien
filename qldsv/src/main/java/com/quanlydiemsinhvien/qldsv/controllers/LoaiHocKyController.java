package com.quanlydiemsinhvien.qldsv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quanlydiemsinhvien.qldsv.dto.LoaihockyDTO;
import com.quanlydiemsinhvien.qldsv.service.LoaiHocKyService;

@RestController
public class LoaiHocKyController {
    
    @Autowired
    private LoaiHocKyService loaiHocKyService;


    @GetMapping("/giaovu/loaihocky")
    public List<LoaihockyDTO> list() {
        return loaiHocKyService.getLoaiHocKyList();
    }
}