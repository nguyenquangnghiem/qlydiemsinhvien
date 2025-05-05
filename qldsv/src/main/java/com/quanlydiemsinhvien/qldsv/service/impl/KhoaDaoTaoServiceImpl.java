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

import com.quanlydiemsinhvien.qldsv.converter.KhoaConverter;
import com.quanlydiemsinhvien.qldsv.converter.KhoaDaoTaoConverter;
import com.quanlydiemsinhvien.qldsv.dto.KhoaDTO;
import com.quanlydiemsinhvien.qldsv.dto.KhoadaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Khoa;
import com.quanlydiemsinhvien.qldsv.pojo.Khoadaotao;
import com.quanlydiemsinhvien.qldsv.pojo.Phonghoc;
import com.quanlydiemsinhvien.qldsv.repository.KhoaDaoTaoRepository;
import com.quanlydiemsinhvien.qldsv.repository.KhoaRepository;
import com.quanlydiemsinhvien.qldsv.repository.PhonghocRepository;
import com.quanlydiemsinhvien.qldsv.service.KhoaDaoTaoService;

/**
 *
 * @author FPTSHOP
 */
@Service
@Transactional
public class KhoaDaoTaoServiceImpl implements KhoaDaoTaoService{
    
    @Autowired
    private KhoaDaoTaoRepository khoaDaoTaoRepository;
    @Autowired
    private KhoaRepository khoaRepository;
    @Autowired
    private PhonghocRepository phonghocRepository;
    @Autowired
    private KhoaConverter khoaConverter;
    @Autowired
    private KhoaDaoTaoConverter khoaDaoTaoConverter;

    @Override
    public Page<KhoaDTO> getKhoaList(Map<String, String> params, int page, int pageSize) {
        try{
            String ten = params.get("tenKH");
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            if(ten != null){
                Page<Khoa> khoaHocList = khoaRepository.findByTenKhoaContaining(ten, pageable);
                return khoaHocList.map(it -> it != null ? khoaConverter.khoaToKhoaDTO(it) : null);
            }
            Page<Khoa> khoaHocList = khoaRepository.findAll(pageable);
            return khoaHocList.map(it -> it != null ? khoaConverter.khoaToKhoaDTO(it) : null);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<KhoadaotaoDTO> getKhoadaotaoList(Map<String, String> params, Integer page, Integer pageSize) {
        try{
            String ten = params.get("tenKDT");
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            if(ten != null){
                Page<Khoadaotao> khoaDaoTaoList = khoaDaoTaoRepository.findByTenKhoaDaoTaoContaining(ten, pageable);
                return khoaDaoTaoList.map(it -> it != null ? khoaDaoTaoConverter.khoadaotaoToKhoadaotaoDTO(it) : null);
            }
            Page<Khoadaotao> khoaDaoTaoList = khoaDaoTaoRepository.findAll(pageable);
            return khoaDaoTaoList.map(it -> it != null ? khoaDaoTaoConverter.khoadaotaoToKhoadaotaoDTO(it) : null);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Phonghoc> getPhonghocList(Map<String, String> params) {
        try {
            String ten = params.get("tenPH");
            if(ten != null){
                return phonghocRepository.findByTenPhongHocContaining(ten);
            }
            return phonghocRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addOrUpdateKhoaDT(Khoadaotao kdt) {
        try{
            this.khoaDaoTaoRepository.save(kdt);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addOrUpdateKhoa(Khoa k) {
        try{
            this.khoaRepository.save(k);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addOrUpdatePhongHoc(Phonghoc ph) {
        try{
            this.phonghocRepository.save(ph);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Khoadaotao getKhoadaotaoById(int id) {
        try{
            return this.khoaDaoTaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Khóa đào tạo không tồn tại!"));
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Khoa getKhoaById(int id) {
        return khoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Khóa không tồn tại!"));
    }

    @Override
    public Phonghoc getPhonghocById(int id) {
        return this.phonghocRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(int id) {
        try{
            this.khoaDaoTaoRepository.deleteById(id);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countKhoa() {
        return this.khoaDaoTaoRepository.count();
    }

    @Override
    public long countKhoaDaoTao() {
        return this.khoaDaoTaoRepository.count();
    }

    @Override
    public long countPhongHoc() {
        return this.khoaDaoTaoRepository.count();
    }
}
