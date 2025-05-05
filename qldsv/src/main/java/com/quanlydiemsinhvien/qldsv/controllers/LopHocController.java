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

import com.quanlydiemsinhvien.qldsv.converter.LopHocConverter;
import com.quanlydiemsinhvien.qldsv.dto.LopHocDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;
import com.quanlydiemsinhvien.qldsv.request.LophocCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.GiaoVuService;
import com.quanlydiemsinhvien.qldsv.service.LopHocService;

/**
 *
 * @author Admin
 */

@RestController
public class LopHocController {

    @Autowired
    private LopHocService daoTaoService;

    @Autowired
    private LopHocConverter lopHocConverter;

    @Autowired
    private GiaoVuService gvuService;

    @GetMapping("/giaovu/lophoc")
    public Page<LopHocDTO> list(@RequestParam Map<String, String> params, @RequestParam(defaultValue="1" ,required = false) Integer page,
    @RequestParam(defaultValue = "100", required=false) int pageSize) {
        return daoTaoService.listLopHoc(params, page, pageSize);
    }

    @GetMapping("/giaovu/lophoc/{id}")
    public LopHocDTO lophoc(@PathVariable(value = "id") int id) {
        return this.daoTaoService.getLopById(id);
    }

    @GetMapping("/giaovu/lophoc/add")
    public String addLopHoc(Authentication auth, Model model) {
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));
        model.addAttribute("lophoc", new Lophoc());
        return "addlophoc";
    }

    @GetMapping("/giaovu/lophoc/add/{id}")
    public String updateLH(Authentication auth, Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));
        model.addAttribute("lophoc", this.daoTaoService.getLopById(id));
        return "addlophoc";
    }

    @PostMapping("/giaovu/lophoc/add")
    public ResponseEntity<?> add(@RequestBody LophocCreateRequest lh) {
        if (this.daoTaoService.addOrUpdateLopHoc(lopHocConverter.lopHocCreateRequestToLopHoc(lh))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
