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

import com.quanlydiemsinhvien.qldsv.converter.KhoaDaoTaoConverter;
import com.quanlydiemsinhvien.qldsv.dto.KhoadaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Khoadaotao;
import com.quanlydiemsinhvien.qldsv.request.KhoaDaoTaoCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.GiaoVuService;
import com.quanlydiemsinhvien.qldsv.service.KhoaDaoTaoService;

/**
 *
 * @author FPTSHOP
 */
@RestController
public class KhoaDaoTaoController {

    @Autowired
    private KhoaDaoTaoService khoaDTService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private KhoaDaoTaoConverter khoaDaoTaoConverter;

    @GetMapping("/giaovu/khoadaotao")
    public Page<KhoadaotaoDTO> list(@RequestParam Map<String, String> params,
                            @RequestParam(name = "page", defaultValue = "1") Integer page,
                            @RequestParam(name = "pageSize", defaultValue = "100") int pageSize) {
        return khoaDTService.getKhoadaotaoList(params, page, pageSize);
    }

    @GetMapping("/giaovu/khoadaotao/add")
    public String addKDT(Authentication auth, Model model) {
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));

        model.addAttribute("khoadaotao", new Khoadaotao());
        return "addkhoadt";
    }

    @GetMapping("/giaovu/khoadaotao/{id}")
    public KhoadaotaoDTO update(Authentication auth, Model model, @PathVariable(value = "id") int id) {
        return khoaDaoTaoConverter.khoadaotaoToKhoadaotaoDTO(khoaDTService.getKhoadaotaoById(id));
    }

    @PostMapping("/giaovu/khoadaotao/add")
    public ResponseEntity<?> add(@RequestBody KhoaDaoTaoCreateRequest kdt) {
        if (this.khoaDTService.addOrUpdateKhoaDT(khoaDaoTaoConverter.khoaDaoTaoCreateRequestToKhoaDaoTao(kdt))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
