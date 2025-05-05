/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanlydiemsinhvien.qldsv.converter.PhonghocConverter;
import com.quanlydiemsinhvien.qldsv.dto.PhonghocDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Phonghoc;
import com.quanlydiemsinhvien.qldsv.request.PhongHocCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.GiaoVuService;
import com.quanlydiemsinhvien.qldsv.service.KhoaDaoTaoService;

/**
 *
 * @author FPTSHOP
 */

 @RestController
public class PhongHocController {
    
    @Autowired
    private KhoaDaoTaoService khoaDTService;
    
    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private PhonghocConverter phonghocConverter;
    
    @GetMapping("/giaovu/phonghoc")
    public List<PhonghocDTO> listPhongHoc(@RequestParam Map<String, String> params){
        return khoaDTService.getPhonghocList(params).stream().map(it -> phonghocConverter.phonghocToPhonghocDTO(it)).collect(Collectors.toList());
    }
    
    @GetMapping("/giaovu/phonghoc/add")
    public String addPH(Model model, Authentication auth){
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));

        model.addAttribute("phonghocc", new Phonghoc());
        return "addphonghoc";
    }
    
    @GetMapping("/giaovu/phonghoc/{id}")
    public PhonghocDTO update(@PathVariable(value = "id") int id){
        return phonghocConverter.phonghocToPhonghocDTO(khoaDTService.getPhonghocById(id));
    }
    
    @PostMapping("/giaovu/phonghoc/add")
    public ResponseEntity<?> add(@RequestBody PhongHocCreateRequest ph){
        if(this.khoaDTService.addOrUpdatePhongHoc(phonghocConverter.phongHocCreateRequestToPhongHoc(ph))){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
