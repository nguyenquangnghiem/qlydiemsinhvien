/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quanlydiemsinhvien.qldsv.request.TaikhoanCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.TaiKhoanService;

/**
 *
 * @author Admin
 */
@RestController
public class ThayDoiMatKhauController {

    @Autowired
    private TaiKhoanService tkService;

    //Sua
    // @GetMapping("/giaovu/thaydoimatkhau")
    // public String thaydoimatkhau(Model model, Authentication authentication) {
    //     model.addAttribute("taikhoanAD", this.tkService.getUserById(tkService.GetIdTaiKhoan(tkService.getLoggedInUserDetails(authentication))));
    //     model.addAttribute("taiKhoan", new Taikhoan());
    //     return "thaydoimatkhau";
    // }

    @PostMapping("/giaovu/thaydoimatkhau")
    public ResponseEntity<?> doiMatKhau(@RequestBody TaikhoanCreateRequest tk, Principal principal) {
        if (tk.getMatKhau().isEmpty() || tk.getXacNhanMk().isEmpty() || tk.getMkMoi().isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else if (tk.getMkMoi().equals(tk.getXacNhanMk())) {
            this.tkService.thayDoiMatKhauAD(tk, principal);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
