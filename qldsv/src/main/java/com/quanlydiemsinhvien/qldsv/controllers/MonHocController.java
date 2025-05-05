/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanlydiemsinhvien.qldsv.converter.MonHocConverter;
import com.quanlydiemsinhvien.qldsv.dto.MonhocDTO;
import com.quanlydiemsinhvien.qldsv.dto.MonhocHockyDTO;
import com.quanlydiemsinhvien.qldsv.request.MonHocHocKyCreateRequest;
import com.quanlydiemsinhvien.qldsv.request.MonhocCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.MonHocService;
import com.quanlydiemsinhvien.qldsv.service.MonhocHocKyService;

/**
 *
 * @author FPTSHOP
 */
@RestController
public class MonHocController {

    @Autowired
    private MonHocService mhService;

    @Autowired
    private MonHocConverter monHocConverter;

    @Autowired
    private MonhocHocKyService monhocHocKyService;

    @GetMapping("/giaovu/monhoc")
    public Page<MonhocDTO> list(@RequestParam Map<String, String> params, @RequestParam(defaultValue="1" ,required = false) Integer page,
    @RequestParam(defaultValue = "100", required=false) int pageSize) {
        return mhService.getMonHocList(params, page, pageSize);
    }

    @GetMapping("/giaovu/monhoc/hocky/{idHocKy}")
    public List<MonhocHockyDTO> getMonHocByHocKy(@PathVariable(value = "idHocKy") Integer idHocKy){
        return mhService.getMonhocByHocKy(idHocKy);
    }

    @GetMapping("/giaovu/monhoc/{id}")
    public MonhocDTO updateMH(@PathVariable(value = "id") int id) {
        return this.mhService.getMonHocById(id);
    }

    @PostMapping("/giaovu/monhoc/add")
    public ResponseEntity<?> add(@RequestBody MonhocCreateRequest mh) {
        if (this.mhService.addOrUpdateMonHoc(monHocConverter.monHocCreateRequestToMonHoc(mh))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/giaovu/dangkymonhoc/{idHK}")
    public ResponseEntity<?> dangKyMonHocAdmin(@PathVariable(value = "idHK") Integer idHK, @RequestBody List<MonHocHocKyCreateRequest> requests){
        try {
            monhocHocKyService.dangKyMonHocAdmin(idHK, requests);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
