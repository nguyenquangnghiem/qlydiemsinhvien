/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.security.Principal;

import com.quanlydiemsinhvien.qldsv.dto.GiaoVuDTO;

/**
 *
 * @author FPTSHOP
 */
public interface GiaoVuService {
    GiaoVuDTO getGiaoVu(Principal auth);
}
