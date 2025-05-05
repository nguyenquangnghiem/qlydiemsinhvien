// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package com.quanlydiemsinhvien.qldsv.controllers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.quanlydiemsinhvien.qldsv.dto.LoaitaikhoanDTO;
// import com.quanlydiemsinhvien.qldsv.pojo.Loaitaikhoan;
// import com.quanlydiemsinhvien.qldsv.service.GiaoVuService;
// import com.quanlydiemsinhvien.qldsv.service.LoaiTaiKhoanService;
// import com.quanlydiemsinhvien.qldsv.service.TaiKhoanService;

// /**
//  *
//  * @author FPTSHOP
//  */
// @RestController
// public class LoaiTaiKhoanController {

//     @Autowired
//     private TaiKhoanService tkService;

//     @Autowired
//     private GiaoVuService gvuService;

//     @Autowired
//     private LoaiTaiKhoanService loaiTaiKhoanService;

//     @GetMapping("/giaovu/loaitaikhoan")
//     public List<LoaitaikhoanDTO> list() {
//         return loaiTaiKhoanService.getLoaiTaiKhoanList();
//     }

//     @RequestMapping("/giaovu/loaitaikhoan")
//     public String loaiTaiKhoan(Model model, Authentication auth) {
//         model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));
//         model.addAttribute("loaitaikhoann", new Loaitaikhoan());
//         return "loaitaikhoan";
//     }

//     @GetMapping("/giaovu/loaitaikhoan/{id}")
//     public String update(Model model, @PathVariable(value = "id") int id, Authentication auth) {
//         model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));
//         model.addAttribute("loaitaikhoann", this.tkService.getLoaiTaiKhoanById(id));
//         return "loaitaikhoan";
//     }

//     @PostMapping("/giaovu/loaitaikhoan")
//     public String add(@ModelAttribute(value = "loaitaikhoann") Loaitaikhoan ltk) {
//         if (this.tkService.addOrUpdateLoaiTK(ltk)) {
//             return "redirect:/giaovu/loaitaikhoan";
//         }
//         return "loaitaikhoan";
//     }
// }
