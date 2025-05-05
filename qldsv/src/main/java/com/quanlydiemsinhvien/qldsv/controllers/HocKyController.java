/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;

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

import com.quanlydiemsinhvien.qldsv.converter.HocKyConverter;
import com.quanlydiemsinhvien.qldsv.dto.HocKyDTO;
import com.quanlydiemsinhvien.qldsv.request.HocKyCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.HocKyService;

/**
 *
 * @author FPTSHOP
 */

 @RestController
public class HocKyController {

    @Autowired
    private HocKyService hkService;

    @Autowired
    private HocKyConverter hocKyConverter;

    @GetMapping("/giaovu/hocky")
    public Page<HocKyDTO> list(@RequestParam Map<String, String> params, @RequestParam(defaultValue="1" ,required = false) Integer page,
    @RequestParam(defaultValue = "100", required=false) int pageSize) {
        return hkService.getHockyList(params, page, pageSize);
    }

    @GetMapping("/giaovu/hocky/{idHK}")
    public HocKyDTO updateHK(@PathVariable(value = "idHK") int id) {
        return hocKyConverter.hocKyToHocKyDTO(hkService.getHockyById(id));
    }

    @PostMapping("/giaovu/hocky/add")
    public ResponseEntity<?> add(@RequestBody HocKyCreateRequest hk) {
        if (this.hkService.addOrUpdateHocKy(hocKyConverter.hocKyCreateRequestToHocKy(hk))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
