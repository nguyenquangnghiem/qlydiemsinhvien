/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service;

import java.util.List;

import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;

/**
 *
 * @author FPTSHOP
 */
public interface PhieuMonHocService {
    boolean addPhieuMHHK(MonhocHocky mh);
    List<MonhocHocky> getMonhocByHockyList(int id);
    boolean updatePhieuMHHK(MonhocHocky mh);
    MonhocHocky getMonhocHocky(int id);
}
