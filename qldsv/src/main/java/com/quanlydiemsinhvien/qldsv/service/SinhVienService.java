/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.util.List;
import java.util.Map;

import com.quanlydiemsinhvien.qldsv.dto.SinhVienDTO;
import com.quanlydiemsinhvien.qldsv.pojo.DiemMonHoc;
import com.quanlydiemsinhvien.qldsv.request.SinhVienCreateRequest;

/**
 *
 * @author Admin
 */
public interface SinhVienService {
    SinhVienDTO getSinhvien(String idTaiKhoan);

    List<SinhVienDTO> getSinhvienList(Map<String, String> params);

    boolean addOrUpdateSinhVien(SinhVienCreateRequest sv);

    SinhVienDTO getSinhVienById(String idSinhVien);

    boolean deleteById(String id);

    List<DiemMonHoc> getSinhvienByMonHoc(Map<String, String> params);

    Long countSinhVien();

    List<SinhVienDTO> getSinhVienByIdLop(int idLop);

    Object getSinhVienByIdAPI(String idSinhVien);
}
