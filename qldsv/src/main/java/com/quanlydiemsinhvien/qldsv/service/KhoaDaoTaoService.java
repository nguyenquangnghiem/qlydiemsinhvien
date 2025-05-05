/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.quanlydiemsinhvien.qldsv.dto.KhoaDTO;
import com.quanlydiemsinhvien.qldsv.dto.KhoadaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Khoa;
import com.quanlydiemsinhvien.qldsv.pojo.Khoadaotao;
import com.quanlydiemsinhvien.qldsv.pojo.Phonghoc;

/**
 *
 * @author FPTSHOP
 */
public interface KhoaDaoTaoService {
    Page<KhoaDTO> getKhoaList(Map<String, String> params, int page, int pageSize);
    Page<KhoadaotaoDTO> getKhoadaotaoList (Map<String, String> params, Integer page, Integer pageSize);
    List<Phonghoc> getPhonghocList (Map<String, String> params);
    boolean addOrUpdateKhoaDT(Khoadaotao kdt);
    boolean addOrUpdateKhoa(Khoa k);
    boolean addOrUpdatePhongHoc(Phonghoc ph);
    Khoadaotao getKhoadaotaoById(int id);
    Khoa getKhoaById(int id);
    Phonghoc getPhonghocById(int id);
    boolean deleteById(int id);
    long countKhoa();
    long countKhoaDaoTao();
    long countPhongHoc();
}
