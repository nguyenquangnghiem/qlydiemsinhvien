/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service.impl;

import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.repository.MonhocHockyRepository;
import com.quanlydiemsinhvien.qldsv.service.PhieuMonHocService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FPTSHOP
 */
@Service
@Transactional
public class PhieuMonHocServiceImpl implements PhieuMonHocService {

    @Autowired
    private MonhocHockyRepository monhocHockyRepository;

    @Override
    public boolean addPhieuMHHK(MonhocHocky mh) {
        try {
            if (mh.getListSoLuong().length == 0) {
                return false;
            }
            for (int i = 0; i < mh.getListSoLuong().length; i++) {
                MonhocHocky mhmoi = new MonhocHocky();
                mhmoi.setIdHocky(mh.getIdHocky());
                mhmoi.setIdMonHoc(mh.getListMonhocs()[i]);
                mhmoi.setSoLuong(mh.getListSoLuong()[i]);
                mhmoi.setNgayBatDau(mh.getListDateBatDau()[i]);
                mhmoi.setNgayKetThuc(mh.getListDateKetThuc()[i]);
                mhmoi.setIdGiangVien(mh.getIdGiangVien());
                mhmoi.setPhongHoc(mh.getListPhonghocs()[i]);
                mhmoi.setSoLuongConLai(mh.getSoLuongConLai());
                monhocHockyRepository.save(mhmoi);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MonhocHocky> getMonhocByHockyList(int id) {
        try {
            return monhocHockyRepository.findByIdHocky_IdHocKy(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updatePhieuMHHK(MonhocHocky mh) {
        try{
            monhocHockyRepository.save(mh);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MonhocHocky getMonhocHocky(int id) {
        return monhocHockyRepository.findById(id).orElse(null);
    }
}
