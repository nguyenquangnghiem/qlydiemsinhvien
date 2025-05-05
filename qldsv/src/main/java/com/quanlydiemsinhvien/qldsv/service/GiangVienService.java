/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.quanlydiemsinhvien.qldsv.dto.GiangVienDTO;
import com.quanlydiemsinhvien.qldsv.request.GiangvienCreateRequest;

/**
 *
 * @author FPTSHOP
 */
public interface GiangVienService {
    List<GiangVienDTO> getGiangvienList(Map<String, String> params);

    boolean addOrUpdateGiangVien(GiangvienCreateRequest gv, Principal principal);

    GiangVienDTO getGiangVienById(String idGiangVien);

    boolean deleteById(String idGiangVien);

    long countGiangVien();
}
