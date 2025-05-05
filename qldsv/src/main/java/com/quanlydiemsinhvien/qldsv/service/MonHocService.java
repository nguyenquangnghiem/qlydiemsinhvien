/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.quanlydiemsinhvien.qldsv.dto.MonhocDTO;
import com.quanlydiemsinhvien.qldsv.dto.MonhocHockyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Monhoc;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.pojo.Monhocdangky;

/**
 *
 * @author FPTSHOP
 */
public interface MonHocService {
    Page<MonhocDTO> getMonHocList(Map<String, String> params, int page, int pageSize);
    boolean addOrUpdateMonHoc(Monhoc mh);
    MonhocDTO getMonHocById(int id);
    boolean deleteMonHoc(int id);
    public Monhoc getMonhocByIdMonhocHocKy(Integer idMonHocHocKy);

    List<MonhocHockyDTO> getMonHocByGiangVien(Map<String, String> params);
    
    List<MonhocHockyDTO> getMonHocByGiangVienChuaDay(Map<String, String> params);
    List<MonhocHockyDTO> getMonHocByGiangVienDaDay(Map<String, String> params);

    Long countMonHoc();

    List<Monhoc> getMonHocByIdSinhVien(String idSinhvien);
    List<Monhoc> getMonHocByIdSinhVienDangHoc(String idSinhvien);
    
    List<MonhocHocky> getMonHocHocKy(Map<String, String> params, Principal principal);
     List<Monhocdangky> getMonHocSinhVienDangKy(Map<String, String> params);
     
     boolean thanhToanHocPhi(Map<String, String> params);

     List<MonhocHockyDTO> getMonhocByHocKy(Integer idHocKy);
}
