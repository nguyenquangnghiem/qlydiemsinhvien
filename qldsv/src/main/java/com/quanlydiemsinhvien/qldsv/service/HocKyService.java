/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.quanlydiemsinhvien.qldsv.dto.HocKyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Hocky;
import com.quanlydiemsinhvien.qldsv.pojo.Loaihocky;

/**
 *
 * @author FPTSHOP
 */
public interface HocKyService {
    Page<HocKyDTO> getHockyList(Map<String, String> params, Integer page, Integer pageSize);
    boolean addOrUpdateHocKy(Hocky hk);
    List<Loaihocky> getLoaihockyList(Map<String, String> params);
    Hocky getHockyById(int id);
    boolean deleteById(int idHK);
    long countHocKy();
}
