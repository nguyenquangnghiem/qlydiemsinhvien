/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.quanlydiemsinhvien.qldsv.dto.MonhocdangkyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.DiemMonHoc;
import com.quanlydiemsinhvien.qldsv.request.DiemMonHocDangKyRequest;

/**
 *
 * @author Admin
 */
public interface DiemService {

    double getDiemTrungBinh(Map<String, String> params);

    List<Object> getDiemTrungBinhHaiHe(Map<String, String> params);

    public void editDiem(List<DiemMonHocDangKyRequest> request);

    List<DiemMonHoc> getListDiemDangHoc(Map<String, String> params);

    List<DiemMonHoc> getListDiemDaHoc(Map<String, String> params);

    DiemMonHoc addDiem(Map<String, String> params);

    String setDiemByCSV(Map<String, String> params, MultipartFile file);

    DiemMonHoc getDiemByIdDiem(Map<String, String> params);

    List<MonhocdangkyDTO> getDiemByidGiangVien(Map<String, String> params);
//
    boolean khoaDiem(Map<String, String> params);

    Boolean dangKyMonHoc(Map<String, String> params);
    
    Boolean huyDangKy( Map<String, String> params);
    
    boolean addCotDiem(Map<String, String> params); 
    
    boolean deleteCotDiem(Map<String, String> params); 
    boolean setDiemTB(Map<String, String> params);

}
