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

import com.quanlydiemsinhvien.qldsv.converter.HeDaoTaoConverter;
import com.quanlydiemsinhvien.qldsv.dto.HedaotaoDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Hedaotao;
import com.quanlydiemsinhvien.qldsv.request.HeDaoTaoCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.GiaoVuService;
import com.quanlydiemsinhvien.qldsv.service.HeDaoTaoService;

@RestController
public class HeDaoTaoController {

    @Autowired
    private HeDaoTaoService hdtService;

    @Autowired
    private GiaoVuService gvuService;

    @Autowired
    private HeDaoTaoConverter heDaoTaoConverter;

    @GetMapping("/giaovu/hedaotao")
    public Page<HedaotaoDTO> list(@RequestParam Map<String, String> params, @RequestParam(defaultValue="1" ,required = false) Integer page,
    @RequestParam(defaultValue = "100", required=false) int pageSize) {
        return hdtService.getHedaotaoList(params, page, pageSize);
    }

    @GetMapping("/giaovu/hedaotao/add")
    public String addHDT(Model model, Authentication auth) {
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));
        model.addAttribute("hedaotaoo", new Hedaotao());
        return "addhedaotao";
    }

    @GetMapping("/giaovu/hedaotao/{id}")
    public HedaotaoDTO update(@PathVariable(value = "id") int id) {
        return heDaoTaoConverter.hedaotaoToHedaotaoDTO(hdtService.getHedaotaoById(id));
    }

    @PostMapping("/giaovu/hedaotao/add")
    public ResponseEntity<?> add(@RequestBody HeDaoTaoCreateRequest hdt) {
        if (this.hdtService.addOrUpdateHeDT(heDaoTaoConverter.heDaoTaoCreateRequestToHeDaoTao(hdt))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
