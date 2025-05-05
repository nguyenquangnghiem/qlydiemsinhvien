package com.quanlydiemsinhvien.qldsv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quanlydiemsinhvien.qldsv.converter.LoaiHocKyConverter;
import com.quanlydiemsinhvien.qldsv.dto.LoaihockyDTO;
import com.quanlydiemsinhvien.qldsv.repository.LoaihockyRepository;
import com.quanlydiemsinhvien.qldsv.service.LoaiHocKyService;

@Service
@Transactional
public class LoaiHocKyServiceImpl implements LoaiHocKyService {

    @Autowired
    private LoaihockyRepository loaihockyRepository;

    @Autowired
    private LoaiHocKyConverter loaiHocKyConverter;

    @Override
    public List<LoaihockyDTO> getLoaiHocKyList() {
        try{
            return loaihockyRepository.findAll().stream().map(it -> loaiHocKyConverter.loaihockyToLoaihockyDTO(it)).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}