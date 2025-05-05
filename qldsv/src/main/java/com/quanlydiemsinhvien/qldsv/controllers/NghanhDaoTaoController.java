/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanlydiemsinhvien.qldsv.dto.NganhdaotaoDTO;
import com.quanlydiemsinhvien.qldsv.service.NghanhDaoTaoService;

/**
 *
 * @author FPTSHOP
 */

 @RestController
public class NghanhDaoTaoController {
    
    @Autowired
    private NghanhDaoTaoService ndtService;
    
    @GetMapping("/giaovu/nganhdaotao")
    public Page<NganhdaotaoDTO> list(@RequestParam Map<String, String> params, @RequestParam(defaultValue="1" ,required = false) Integer page,
    @RequestParam(defaultValue = "100", required=false) int pageSize) {
        return ndtService.getNganhdaotaoList(params, page, pageSize);
    }
}
