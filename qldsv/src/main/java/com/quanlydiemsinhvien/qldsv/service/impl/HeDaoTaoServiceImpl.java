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

import com.quanlydiemsinhvien.qldsv.converter.HeDaoTaoConverter;
import com.quanlydiemsinhvien.qldsv.dto.HedaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Hedaotao;
import com.quanlydiemsinhvien.qldsv.repository.HeDaoTaoRepository;
import com.quanlydiemsinhvien.qldsv.service.HeDaoTaoService;

/**
 *
 * @author FPTSHOP
 */
@Service
@Transactional
public class HeDaoTaoServiceImpl implements HeDaoTaoService{
    
    @Autowired
    private HeDaoTaoRepository heDaoTaoRepository;

    @Autowired
    private HeDaoTaoConverter heDaoTaoConverter;

    @Override
    public Page<HedaotaoDTO> getHedaotaoList(Map<String, String> params, int page, int pageSize) {
        try{
            String ten = params.get("tenHDT");
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            if(ten != null){
                Page<Hedaotao> heDaoTaoList = heDaoTaoRepository.findByTenHeDaoTaoContaining(ten, pageable);
                return heDaoTaoList.map(it -> it != null ? heDaoTaoConverter.hedaotaoToHedaotaoDTO(it) : null);
            }
            Page<Hedaotao> heDaoTaoList = heDaoTaoRepository.findAll(pageable);
            return heDaoTaoList.map(it -> it != null ? heDaoTaoConverter.hedaotaoToHedaotaoDTO(it) : null);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addOrUpdateHeDT(Hedaotao hdt) {
        try{
            heDaoTaoRepository.save(hdt);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Hedaotao getHedaotaoById(int id) {
        try {
            return this.heDaoTaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Hệ đào tạo này không tồn tại!"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try{
            this.heDaoTaoRepository.deleteById(id);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countHeDaoTao() {
        return this.heDaoTaoRepository.count();
   }
    
}
