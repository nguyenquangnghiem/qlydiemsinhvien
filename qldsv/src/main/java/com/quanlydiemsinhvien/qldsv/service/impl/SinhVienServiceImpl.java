/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quanlydiemsinhvien.qldsv.converter.MonHocDangKyConverter;
import com.quanlydiemsinhvien.qldsv.converter.SinhVienConverter;
import com.quanlydiemsinhvien.qldsv.dto.SinhVienDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Diem;
import com.quanlydiemsinhvien.qldsv.pojo.DiemMonHoc;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.pojo.Monhocdangky;
import com.quanlydiemsinhvien.qldsv.repository.DiemRepository;
import com.quanlydiemsinhvien.qldsv.repository.LopHocRepository;
import com.quanlydiemsinhvien.qldsv.repository.MonhocHockyRepository;
import com.quanlydiemsinhvien.qldsv.repository.MonhocdangkyRepository;
import com.quanlydiemsinhvien.qldsv.request.SinhVienCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.KeycloakUserService;
import com.quanlydiemsinhvien.qldsv.service.SinhVienService;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class SinhVienServiceImpl implements SinhVienService {

    @Autowired
    private DiemRepository diemRepository;

    @Autowired
    private MonhocHockyRepository monhocHockyRepository;
    @Autowired
    private MonhocdangkyRepository monhocdangkyRepository;

    @Autowired
    private LopHocRepository lopHocRepository;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @Autowired
    private MonHocDangKyConverter monHocDangKyConverter;

    @Autowired
    private SinhVienConverter sinhVienConverter;

    @Override
    public SinhVienDTO getSinhvien(String idTaiKhoan) {
        return sinhVienConverter.sinhVienToSinhVienDTO(keycloakUserService.getUserById(idTaiKhoan));
    }

    @Override
    public List<SinhVienDTO> getSinhvienList(Map<String, String> params) {
        try {
            String tenSV = params.get("tensv");

            if (tenSV != null) {
                List<Map<String, Object>> sinhVienList = keycloakUserService.getUserByFullNameAndRole(tenSV, "SV");
                return sinhVienList.stream().map(it -> it != null ? sinhVienConverter.sinhVienToSinhVienDTO(it) : null)
                        .collect(Collectors.toList());
            }
            List<Map<String, Object>> sinhVienList = (List<Map<String, Object>>) keycloakUserService
                    .getUsersByRoles("SV").get("users");
            return sinhVienList.stream().map(it -> it != null ? sinhVienConverter.sinhVienToSinhVienDTO(it) : null)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // di chuyen lay danh sach lop hoc qua class lop hoc
    @Override
    public boolean addOrUpdateSinhVien(SinhVienCreateRequest sv) {
        try {
            if (sv.getMatKhau() != null && (!sv.getMatKhau().equals(sv.getXacNhanMk())))
                return false;
            if (sv != null && !sv.getIdTaiKhoan().isBlank()) {
                keycloakUserService.updateUser(sv.getIdTaiKhoan(), sv);
                if(sv.getMatKhau() != null && !sv.getMatKhau().isBlank())
                    keycloakUserService.updateUserPassword(sv.getIdTaiKhoan(), sv.getMatKhau());
            } else if (sv != null && (sv.getIdTaiKhoan() == null || sv.getIdTaiKhoan().isBlank())) {
                if(sv.getMatKhau() == null || sv.getMatKhau().isBlank())
                    return false;
                String keycloakUserId = keycloakUserService
                        .createUserInKeycloak(sinhVienConverter.sinhVienRequestToSinhVienDTO(sv));
                keycloakUserService.updateUserPassword(keycloakUserId, sv.getMatKhau());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SinhVienDTO getSinhVienById(String idSinhVien) {
        return sinhVienConverter.sinhVienToSinhVienDTO(keycloakUserService.getUserById(idSinhVien));
    }

    // xoa sinh vien voi khoa ngoai
    @Override
    public boolean deleteById(String idSinhVien) {
        try {

            keycloakUserService.deleteUser(idSinhVien);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<DiemMonHoc> getSinhvienByMonHoc(Map<String, String> params) {
        String idMonHoc = params.get("monHocId");
        String tenSinhVien = params.get("tenSinhVien");
        MonhocHocky monhocHocky = monhocHockyRepository.findById(Integer.parseInt(idMonHoc))
                .orElseThrow(() -> new RuntimeException("Môn học học kỳ không tồn tại!"));
        List<Monhocdangky> monhocdangkyList = new ArrayList<>();
        if (tenSinhVien != null) {
            List<Map<String, Object>> sinhvienList = keycloakUserService.getUserByFullNameAndRole(tenSinhVien, "SV");
            Map<String, Object> sinhvien = sinhvienList.isEmpty() ? null : sinhvienList.getFirst();
            Monhocdangky monhocdangky = monhocdangkyRepository
                    .findByIdSinhVienAndIdMonHoc((String) sinhvien.get("id"), monhocHocky)
                    .orElseThrow(() -> new RuntimeException("Môn học đăng ký không tồn tại!"));
            monhocdangkyList.add(monhocdangky);
        } else {
            monhocdangkyList.addAll(monhocdangkyRepository.findByIdMonHoc(monhocHocky));
        }

        List<DiemMonHoc> monHocDiemList = new ArrayList<>();

        for (Monhocdangky monHoc : monhocdangkyList) {
            DiemMonHoc monHocDiem = DiemMonHoc
                    .fromMonHocDangKy(monHocDangKyConverter.monhocdangkyToMonhocdangkyDTO(monHoc));
            // Lấy danh sách điểm cho môn học cụ thể
            List<Diem> diemList = diemRepository.findByIdMonHoc(monHoc);
            // Thêm điểm vào danh sách MonHocDiem
            for (Diem diem : diemList) {
                monHocDiem.addDiem(diem); // Diem.getDiem() là phương thức lấy giá trị điểm
            }
            // Thêm MonHocDiem vào danh sách chung
            monHocDiemList.add(monHocDiem);
        }
        return monHocDiemList;
    }

    // dem sv
    @Override
    public Long countSinhVien() {
        return (Long) keycloakUserService.getUsersByRoles("SV").get("total");
    }

    // update 26/9 danh sach sinh vien theo ma lop
    @Override
    public List<SinhVienDTO> getSinhVienByIdLop(int idLop) {
        try {
            return keycloakUserService.getSinhVienByIdLop(idLop).stream()
                    .map(it -> sinhVienConverter.sinhVienToSinhVienDTO(it)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getSinhVienByIdAPI(String idSinhVien) {
        try {
            Map<String, Object> sinhvien = keycloakUserService.getUserById(idSinhVien);
            Map<String, Object> attributes = (Map<String, Object>) sinhvien.get("attributes");
            Lophoc lophoc = lopHocRepository
                    .findById(Integer.valueOf((String) ((ArrayList) attributes.get("id_lop_hoc")).get(0))).get();
            String ngaySinhStr = (String) ((ArrayList) attributes.get("ngay_sinh")).get(0);
            return new Object[] { (String) ((ArrayList) attributes.get("ho_ten")).get(0), (String) ((ArrayList) attributes.get("ma_so")).get(0),
                    new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinhStr), lophoc.getTenLopHoc(),
                    lophoc.getIdNganh().getTenNganhDaoTao(),
                    (String) ((ArrayList) attributes.get("dia_chi")).get(0), (String) sinhvien.get("email") };
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
