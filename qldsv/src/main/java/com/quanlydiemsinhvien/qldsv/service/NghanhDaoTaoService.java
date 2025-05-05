/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.quanlydiemsinhvien.qldsv.dto.NganhdaotaoDTO;

/**
 *
 * @author FPTSHOP
 */


public interface NghanhDaoTaoService {
    Page<NganhdaotaoDTO> getNganhdaotaoList(Map<String, String> params, int page, int pageSize);

}
