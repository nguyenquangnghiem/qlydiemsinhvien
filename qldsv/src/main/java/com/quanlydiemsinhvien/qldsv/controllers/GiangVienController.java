/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanlydiemsinhvien.qldsv.dto.GiangVienDTO;
import com.quanlydiemsinhvien.qldsv.request.GiangvienCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.GiangVienService;

@RestController
public class GiangVienController {

    @Autowired
    private GiangVienService gvService;

    @GetMapping("/giaovu/giangvien")
    public List<GiangVienDTO> list(@RequestParam Map<String, String> params) {
        return gvService.getGiangvienList(params);
    }

    @GetMapping("/giaovu/giangvien/{id}")
    public GiangVienDTO getGVById(@PathVariable(value = "id") String id) {
        return gvService.getGiangVienById(id);
    }

    @PostMapping("/giaovu/giangvien/add")
    public ResponseEntity<?> add(@RequestBody GiangvienCreateRequest gv, Principal principal)
            throws UnsupportedEncodingException {
        if (gvService.addOrUpdateGiangVien(gv, principal)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
