/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.quanlydiemsinhvien.qldsv.service.GiangVienService;
import com.quanlydiemsinhvien.qldsv.service.HeDaoTaoService;
import com.quanlydiemsinhvien.qldsv.service.HocKyService;
import com.quanlydiemsinhvien.qldsv.service.KhoaDaoTaoService;
import com.quanlydiemsinhvien.qldsv.service.KhoaService;
import com.quanlydiemsinhvien.qldsv.service.LopHocService;
import com.quanlydiemsinhvien.qldsv.service.MonHocService;
import com.quanlydiemsinhvien.qldsv.service.MonhocHocKyService;
import com.quanlydiemsinhvien.qldsv.service.PhonghocService;
import com.quanlydiemsinhvien.qldsv.service.SinhVienService;

/**
 *
 * @author FPTSHOP
 */
@RestController
public class ApiAdminController {

    @Autowired
    private LopHocService lopHocService;

    @Autowired
    private KhoaDaoTaoService khoaDaoTaoService;

    @Autowired
    private HocKyService hocKyService;

    @Autowired
    private HeDaoTaoService heDaoTaoService;

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private GiangVienService giaoVienService;

    @Autowired
    private MonhocHocKyService monhocHocKyService;

    @Autowired
    private KhoaService khoaService;

    @Autowired
    private MonHocService monHocService;

    @Autowired
    private PhonghocService phonghocService;

    @DeleteMapping("/giaovu/monhoc/add/{id}")
    public void deleteMH(@PathVariable(value = "id") int id, HttpServletResponse response) {
        boolean success = monHocService.deleteMonHoc(id);
        if (success) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Thiết lập trạng thái NO_CONTENT (204)
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Thiết lập trạng thái
                                                                              // INTERNAL_SERVER_ERROR (500)
        }
    }

    @DeleteMapping("/giaovu/hocki/chitiethocki/update/{id}")
    public void deletePMH(@PathVariable(value = "id") int id, HttpServletResponse response) {
        boolean suscess = monhocHocKyService.deleteById(id);
        if (suscess) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/giaovu/sinhvien/add/{id}")
    public void deleteSV(@PathVariable(value = "id") String id, HttpServletResponse response) {
        boolean suscess = this.sinhVienService.deleteById(id);
        if (suscess) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/giaovu/giangvien/add/{id}")
    public void delete(@PathVariable(value = "id") String id, HttpServletResponse response) {
        boolean success = this.giaoVienService.deleteById(id);
        if (success) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Thiết lập trạng thái NO_CONTENT (204)
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Thiết lập trạng thái
                                                                              // INTERNAL_SERVER_ERROR (500)
        }
    }

    @DeleteMapping("/giaovu/lophoc/add/{id}")
    public void deleteLH(@PathVariable(value = "id") int id, HttpServletResponse response) {
        boolean success = this.lopHocService.deleteById(id);
        if (success) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Thiết lập trạng thái NO_CONTENT (204)
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Thiết lập trạng thái
                                                                              // INTERNAL_SERVER_ERROR (500)
        }

    }

    @DeleteMapping("/giaovu/khoadaotao/add/{id}")
    public void deleteKDT(@PathVariable(value = "id") int id, HttpServletResponse response) {
        boolean success = this.khoaDaoTaoService.deleteById(id);
        if (success) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Thiết lập trạng thái NO_CONTENT (204)
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Thiết lập trạng thái
                                                                              // INTERNAL_SERVER_ERROR (500)
        }
    }

    @DeleteMapping("/giaovu/khoahoc/add/{id}")
    public void deleteKH(@PathVariable(value = "id") int id, HttpServletResponse response) {
        boolean success = this.khoaService.deleteById(id);
        if (success) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Thiết lập trạng thái NO_CONTENT (204)
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Thiết lập trạng thái
                                                                              // INTERNAL_SERVER_ERROR (500)
        }
    }

    @DeleteMapping("/giaovu/phonghoc/add/{id}")
    public void deletePH(@PathVariable(value = "id") int id, HttpServletResponse response) {
        boolean success = this.phonghocService.deleteById(id);
        if (success) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Thiết lập trạng thái NO_CONTENT (204)
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Thiết lập trạng thái
                                                                              // INTERNAL_SERVER_ERROR (500)
        }
    }

    @DeleteMapping("/giaovu/hocky/add/{idHK}")
    public void deleteHK(@PathVariable(value = "idHK") int id, HttpServletResponse response) {
        boolean success = this.hocKyService.deleteById(id);
        if (success) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Thiết lập trạng thái NO_CONTENT (204)
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Thiết lập trạng thái
                                                                              // INTERNAL_SERVER_ERROR (500)
        }
    }

    @DeleteMapping("/giaovu/hedaotao/add/{id}")
    public void deleteHDT(@PathVariable(value = "id") int id, HttpServletResponse response) {
        boolean success = this.heDaoTaoService.deleteById(id);
        if (success) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Thiết lập trạng thái NO_CONTENT (204)
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Thiết lập trạng thái
                                                                              // INTERNAL_SERVER_ERROR (500)
        }
    }
}
