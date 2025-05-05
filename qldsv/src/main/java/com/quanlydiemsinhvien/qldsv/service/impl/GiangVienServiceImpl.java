/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service.impl;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quanlydiemsinhvien.qldsv.converter.GiangVienConverter;
import com.quanlydiemsinhvien.qldsv.dto.GiangVienDTO;
import com.quanlydiemsinhvien.qldsv.request.GiangvienCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.GiangVienService;
import com.quanlydiemsinhvien.qldsv.service.KeycloakUserService;

/**
 *
 * @author FPTSHOP
 */
@Service
@Transactional
public class GiangVienServiceImpl implements GiangVienService {

    @Autowired
    private KeycloakUserService keycloakUserService;

    @Autowired
    private GiangVienConverter giangVienConverter;

    @Override
    public List<GiangVienDTO> getGiangvienList(Map<String, String> params) {
        try {
            String tenGV = params.get("tenGV");

            if (tenGV != null) {
                List<Map<String, Object>> giangVienList = keycloakUserService.getUserByFullNameAndRole(tenGV, "GV");
                return giangVienList.stream().map(it -> giangVienConverter.mapToGiangVienDTO(it))
                        .collect(Collectors.toList());
            }
            List<Map<String, Object>> giangVienList = (List<Map<String, Object>>) keycloakUserService
                    .getUsersByRoles("GV").get("users");
            return giangVienList.stream().map(it -> giangVienConverter.mapToGiangVienDTO(it))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean addOrUpdateGiangVien(GiangvienCreateRequest gv, Principal principal) {
        try {
            if (gv.getMatKhau() != null && (!gv.getMatKhau().equals(gv.getXacNhanMk())))
                return false;
            if (gv != null && !gv.getIdTaiKhoan().isBlank()) {
                keycloakUserService.updateUser(gv.getIdTaiKhoan(), gv);
                if (gv.getMatKhau() != null && !gv.getMatKhau().isBlank())
                    keycloakUserService.updateUserPassword(gv.getIdTaiKhoan(), gv.getMatKhau());
            } else if (gv != null && (gv.getIdTaiKhoan() == null || gv.getIdTaiKhoan().isBlank())) {
                if (gv.getMatKhau() == null || gv.getMatKhau().isBlank())
                    return false;
                String keycloakUserId = keycloakUserService
                        .createUserInKeycloak(giangVienConverter.giangVienCreateRequestToGiangVienDTO(gv));
                keycloakUserService.updateUserPassword(keycloakUserId, gv.getMatKhau());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public GiangVienDTO getGiangVienById(String idGiangVien) {
        Map<String, Object> gv = (Map<String, Object>) keycloakUserService.getUserById(idGiangVien);
        return giangVienConverter.mapToGiangVienDTO(gv);
    }

    @Override
    public boolean deleteById(String idGiangVien) {
        try {
            keycloakUserService.deleteUser(idGiangVien);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting GiangVien with id: " + idGiangVien);
            return false;
        }
    }

    @Override
    public long countGiangVien() {
        return (Long) keycloakUserService.getUsersByRoles("GV").get("total");
    }
}
