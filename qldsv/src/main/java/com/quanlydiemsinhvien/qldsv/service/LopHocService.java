/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.quanlydiemsinhvien.qldsv.dto.LopHocDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;

/**
 *
 * @author Admin
 */
public interface LopHocService {
    Page<LopHocDTO> listLopHoc(Map<String,String> params, int page, int pageSize);
    LopHocDTO getLopById(int idLop);
    boolean addOrUpdateLopHoc(Lophoc lh);
    Boolean deleteById(int idLop);
    long countLopHoc();
}
