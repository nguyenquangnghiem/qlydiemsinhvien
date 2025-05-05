/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanlydiemsinhvien.qldsv.converter.KhoaConverter;
import com.quanlydiemsinhvien.qldsv.dto.KhoaDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Khoa;
import com.quanlydiemsinhvien.qldsv.request.KhoaCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.GiaoVuService;
import com.quanlydiemsinhvien.qldsv.service.KhoaDaoTaoService;

/**
 *
 * @author FPTSHOP
 */

 @RestController
public class KhoaHocController {

    @Autowired
    private KhoaDaoTaoService khoaDaoTaoService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private KhoaConverter khoaConverter;

    @GetMapping("/giaovu/khoahoc")
    public Page<KhoaDTO> list(@RequestParam Map<String, String> params,
                            @RequestParam(name = "page", defaultValue = "1") Integer page,
                            @RequestParam(name = "pageSize", defaultValue = "100") int pageSize) {
        return khoaDaoTaoService.getKhoaList(params, page, pageSize);
    }
    @GetMapping("/giaovu/khoahoc/add")
    public String addKH(Model model, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));

        model.addAttribute("khoahocc", new Khoa());
        return "addkhoahoc";
    }

    @GetMapping("/giaovu/khoa/{id}")
    public KhoaDTO update(@PathVariable(value = "id") int id) {
        return khoaConverter.khoaToKhoaDTO(khoaDaoTaoService.getKhoaById(id));
    }

    @PostMapping("/giaovu/khoa/add")
    public ResponseEntity<?> add(@RequestBody KhoaCreateRequest k) {
        if (this.khoaDaoTaoService.addOrUpdateKhoa(khoaConverter.khoaCreateRequestToKhoa(k))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
