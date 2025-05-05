package com.quanlydiemsinhvien.qldsv.converter;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.MonhocdangkyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Monhocdangky;
import com.quanlydiemsinhvien.qldsv.service.KeycloakUserService;

@Component
public class MonHocDangKyConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MonHocHocKyConverter monHocHocKyConverter;

    @Autowired
    private SinhVienConverter sinhVienConverter;

    @Autowired
    private KeycloakUserService keycloakUserService;

    public MonhocdangkyDTO monhocdangkyToMonhocdangkyDTO(Monhocdangky monhocdangky) {
        MonhocdangkyDTO monhocdangkyDTO = modelMapper.map(monhocdangky, MonhocdangkyDTO.class);
        monhocdangkyDTO.setIdMonHoc(monHocHocKyConverter.monhocHockyToMonhocHockyDTO(monhocdangky.getIdMonHoc()));
        Map<String, Object> sinhvien = keycloakUserService.getUserById(monhocdangky.getIdSinhVien());
        monhocdangkyDTO.setIdSinhVien(sinhVienConverter.sinhVienToSinhVienDTO(sinhvien));
        return monhocdangkyDTO;
    }
}