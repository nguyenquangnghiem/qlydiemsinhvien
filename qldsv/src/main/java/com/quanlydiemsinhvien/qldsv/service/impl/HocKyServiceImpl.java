/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quanlydiemsinhvien.qldsv.converter.HocKyConverter;
import com.quanlydiemsinhvien.qldsv.dto.HocKyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Hocky;
import com.quanlydiemsinhvien.qldsv.pojo.Loaihocky;
import com.quanlydiemsinhvien.qldsv.repository.HocKyRepository;
import com.quanlydiemsinhvien.qldsv.repository.LoaihockyRepository;
import com.quanlydiemsinhvien.qldsv.service.HocKyService;

/**
 *
 * @author FPTSHOP
 */
@Service
@Transactional
public class HocKyServiceImpl implements HocKyService{
    
    @Autowired
    private HocKyRepository hocKyRepository;
    @Autowired
    private LoaihockyRepository loaihockyRepository;

    @Autowired
    private HocKyConverter hocKyConverter;

    @Override
    public Page<HocKyDTO> getHockyList(Map<String, String> params, Integer page, Integer pageSize) {
        try {
            String tenHKi = params.get("tenHK") == null? "" : params.get("tenHK");
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            if(tenHKi != null){
                Page<Hocky> hocKyList = hocKyRepository.findByTenHocKy_TenHocKyContaining(tenHKi, pageable);
                return hocKyList.map(it -> it != null ? hocKyConverter.hocKyToHocKyDTO(it) : null);
            }
            Page<Hocky> hocKyList = hocKyRepository.findAll(pageable);
            return hocKyList.map(it -> it != null ? hocKyConverter.hocKyToHocKyDTO(it) : null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addOrUpdateHocKy(Hocky hk) {
        try{
            hocKyRepository.save(hk);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Loaihocky> getLoaihockyList(Map<String, String> params) {
        try{
            return loaihockyRepository.findAll();
        } catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Hocky getHockyById(int id) {
        try {
            return this.hocKyRepository.findById(id).orElseThrow(() -> new RuntimeException("Học kỳ không tồn tại!"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(int idHK) {
        try{
            this.hocKyRepository.deleteById(idHK);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countHocKy() {
        return this.hocKyRepository.count();
    }
    
}
