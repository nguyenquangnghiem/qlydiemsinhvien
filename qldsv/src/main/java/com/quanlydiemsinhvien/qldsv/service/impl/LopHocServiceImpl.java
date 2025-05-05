/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quanlydiemsinhvien.qldsv.converter.LopHocConverter;
import com.quanlydiemsinhvien.qldsv.dto.LopHocDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;
import com.quanlydiemsinhvien.qldsv.repository.LopHocRepository;
import com.quanlydiemsinhvien.qldsv.service.LopHocService;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class LopHocServiceImpl implements LopHocService{

    @Autowired
    private LopHocRepository lopHocRepository;

    @Autowired
    private LopHocConverter lopHocConverter;
    
    @Override
    public Page<LopHocDTO> listLopHoc(Map<String,String> params, int page, int pageSize) {
        try {
            String ten = params.get("tenLH");
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            if(ten != null){
                Page<Lophoc> lopHocList = lopHocRepository.findByTenLopHocContaining(ten, pageable);
                return lopHocList.map(it -> it != null ? lopHocConverter.lopHocToLopHocDTO(it) : null);
            }
            Page<Lophoc> lopHocList = lopHocRepository.findAll(pageable);
            return lopHocList.map(it -> it != null ? lopHocConverter.lopHocToLopHocDTO(it) : null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LopHocDTO getLopById(int idLop) {
        try {
            return lopHocConverter.lopHocToLopHocDTO(lopHocRepository.findById(idLop).orElseThrow(() -> new RuntimeException("Lóp học không tồn tại!")));
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean addOrUpdateLopHoc(Lophoc lh) {
        try{
            lopHocRepository.save(lh);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteById(int idLop) {
        try{
            lopHocRepository.deleteById(idLop);
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public long countLopHoc() {
        return this.lopHocRepository.count();
    }
    
}
