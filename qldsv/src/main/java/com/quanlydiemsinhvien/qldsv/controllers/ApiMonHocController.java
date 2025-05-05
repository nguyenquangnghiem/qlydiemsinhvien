/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanlydiemsinhvien.qldsv.converter.MonHocConverter;
import com.quanlydiemsinhvien.qldsv.converter.MonHocDangKyConverter;
import com.quanlydiemsinhvien.qldsv.converter.MonHocHocKyConverter;
import com.quanlydiemsinhvien.qldsv.dto.LopHocDTO;
import com.quanlydiemsinhvien.qldsv.dto.MonhocDTO;
import com.quanlydiemsinhvien.qldsv.dto.MonhocHockyDTO;
import com.quanlydiemsinhvien.qldsv.dto.MonhocdangkyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.DiemMonHoc;
import com.quanlydiemsinhvien.qldsv.service.DiemService;
import com.quanlydiemsinhvien.qldsv.service.LopHocService;
import com.quanlydiemsinhvien.qldsv.service.MonHocService;
import com.quanlydiemsinhvien.qldsv.service.SinhVienService;

/**
 *
 * @author FPTSHOP
 */
@RestController
public class ApiMonHocController {
    
    @Autowired
    private MonHocService monHocService;
    @Autowired
    private SinhVienService svService;
    @Autowired
    private DiemService diemService;
    @Autowired
    private LopHocService daotaoService;

    @Autowired
    private MonHocHocKyConverter monHocHocKyConverter;

    @Autowired
    private MonHocDangKyConverter monHocDangKyConverter;

    @Autowired
    private MonHocConverter monHocConverter;
    

    @GetMapping("/api/monhocgiangvien/")
    @CrossOrigin
    public ResponseEntity<List<MonhocHockyDTO>> listMHSV(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.monHocService.getMonHocByGiangVien(params), HttpStatus.OK);
    }
    @GetMapping("/api/monhocgiangvienchuaday/")
    @CrossOrigin
    public ResponseEntity<List<MonhocHockyDTO>> listMHSVCD(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.monHocService.getMonHocByGiangVienChuaDay(params), HttpStatus.OK);
    }
    @GetMapping("/api/monhocgiangviendaday/")
    @CrossOrigin
    public ResponseEntity<List<MonhocHockyDTO>> listMHSVDD(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.monHocService.getMonHocByGiangVienDaDay(params), HttpStatus.OK);
    }

    @GetMapping("/api/monhoc/")
    @CrossOrigin
    public ResponseEntity<MonhocDTO> getMonHocHocKy(@RequestParam Integer idMonHocHocKy){
        return new ResponseEntity<>(monHocConverter.monhocToMonhocDTO(monHocService.getMonhocByIdMonhocHocKy(idMonHocHocKy)), HttpStatus.OK);
    }
       
    @PostMapping("/api/monhocsinhvien/")
    @CrossOrigin
    public ResponseEntity<List<DiemMonHoc>> listSVMH(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.svService.getSinhvienByMonHoc(params), HttpStatus.OK);
    }
    @PostMapping("/api/monhochocky/")
    @CrossOrigin
    public ResponseEntity<List<MonhocHockyDTO>> listMHHK(@RequestParam Map<String, String> params, Principal principal){
        return new ResponseEntity<>(monHocService.getMonHocHocKy(params, principal).stream().map(it -> monHocHocKyConverter.monhocHockyToMonhocHockyDTO(it)).collect(Collectors.toList()), HttpStatus.OK);
    }
    
    @GetMapping("/api/monhocSVdangky/")
    @CrossOrigin
    public ResponseEntity<List<MonhocdangkyDTO>> listMHSVDK(@RequestParam Map<String, String> params){
        List<MonhocdangkyDTO> monhocDangkyDTOList = monHocService.getMonHocSinhVienDangKy(params).stream().map(it -> monHocDangKyConverter.monhocdangkyToMonhocdangkyDTO(it)).collect(Collectors.toList());
        return new ResponseEntity<>(monhocDangkyDTOList, HttpStatus.OK);
    }
    
    @PostMapping("/api/dangkymonhoc/")
    @CrossOrigin
    public ResponseEntity<String> DangkyMH(@RequestParam Map<String, String> params){
        if(this.diemService.dangKyMonHoc(params)){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.OK);
    }
    
    @DeleteMapping("/api/huydangkymonhoc/")
    @CrossOrigin
    public ResponseEntity<String> HuyDangkyMH( @RequestParam Map<String, String> params){
        if(this.diemService.huyDangKy(params)){
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail", HttpStatus.CREATED);
    }
    
    @GetMapping("/api/dslophoc/")
    @CrossOrigin
    public ResponseEntity<Page<LopHocDTO>> DSLopHoc(@RequestParam Map<String, String> params){

       return new ResponseEntity<>(this.daotaoService.listLopHoc(params, 1, 100), HttpStatus.OK);
    }
    
}
