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

import com.quanlydiemsinhvien.qldsv.converter.NganhDaoTaoConverter;
import com.quanlydiemsinhvien.qldsv.dto.NganhdaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Nganhdaotao;
import com.quanlydiemsinhvien.qldsv.repository.NghanhDaoTaoRepository;
import com.quanlydiemsinhvien.qldsv.service.NghanhDaoTaoService;

/**
 *
 * @author FPTSHOP
 */
@Service
@Transactional
public class NghanhDaoTaoServiceImpl implements NghanhDaoTaoService{
    
    @Autowired
    private NghanhDaoTaoRepository ndtRepository;

    @Autowired
    private NganhDaoTaoConverter nganhDaoTaoConverter;

    @Override
    public Page<NganhdaotaoDTO> getNganhdaotaoList(Map<String, String> params, int page, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            Page<Nganhdaotao> nganhDaoTaoList = ndtRepository.findAll(pageable);
            return nganhDaoTaoList.map(it -> it != null ? nganhDaoTaoConverter.nganhdaotaoToNganhdaotaoDTO(it) : null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   
       
}
