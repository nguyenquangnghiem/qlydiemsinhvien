/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;

import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.service.GiaoVuService;
import com.quanlydiemsinhvien.qldsv.service.HocKyService;
import com.quanlydiemsinhvien.qldsv.service.PhieuMonHocService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author FPTSHOP
 */
@Controller
public class PhieuHocKiController {

    @Autowired
    private HocKyService hkService;

    @Autowired GiaoVuService gvuService;

    @Autowired
    private PhieuMonHocService pmhService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @RequestMapping("/giaovu/hocki/addMH")
    public String dangki(Authentication auth, Model model) {
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));

        return "phieuhocki";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @GetMapping("/giaovu/hocki/addMH/{id}")
    public String add(Authentication auth,Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));
        model.addAttribute("hocki", this.hkService.getHockyById(id));
        model.addAttribute("phieumonhoc", new MonhocHocky());
        model.addAttribute("mondachon", this.pmhService.getMonhocByHockyList(id));
        return "phieuhocki";
    }

    @PostMapping("/giaovu/hocki/addMH/{id}")
    public String addMH(Model model, @PathVariable(value = "id") int id, @ModelAttribute(value = "phieumonhoc") MonhocHocky mh) {
        model.addAttribute("hocki", this.hkService.getHockyById(id));
        if (this.pmhService.addPhieuMHHK(mh)) {
            return "redirect:/giaovu/hocki/addMH/" + id;
        }
        return "phieuhocki";
    }

    @GetMapping("/giaovu/hocki/chitiethocki/{id}")
    public String chiTietHk(Authentication auth,Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));
        model.addAttribute("mondachon", this.pmhService.getMonhocByHockyList(id));
        return "listmonhochk";
    }

    @GetMapping("/giaovu/hocki/chitiethocki/update/{id}")
    public String updateCTHK(Authentication auth,Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("giaovu", this.gvuService.getGiaoVu(auth));
        model.addAttribute("phieumonhoc", this.pmhService.getMonhocHocky(id));
        return "updateMHHK";
    }

    @PostMapping("/giaovu/hocki/chitiethocki/update")
    public String update(@ModelAttribute(value = "phieumonhoc") MonhocHocky mh) {
        if (this.pmhService.updatePhieuMHHK(mh)) {
            return "redirect:/giaovu/hocki/chitiethocki/" + mh.getIdHocky().getIdHocKy();
        }
        return "updateMHHK";
    }
    
}
