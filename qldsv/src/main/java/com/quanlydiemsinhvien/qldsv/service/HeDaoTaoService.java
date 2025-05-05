/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.quanlydiemsinhvien.qldsv.dto.HedaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Hedaotao;

/**
 *
 * @author FPTSHOP
 */
public interface HeDaoTaoService {
    Page<HedaotaoDTO> getHedaotaoList(Map<String, String> params, int page, int pageSize);
    boolean addOrUpdateHeDT(Hedaotao hdt);
    Hedaotao getHedaotaoById(int id);
    boolean deleteById(int id);
    long countHeDaoTao();
}
