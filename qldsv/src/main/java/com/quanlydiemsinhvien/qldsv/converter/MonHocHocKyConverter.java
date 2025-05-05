package com.quanlydiemsinhvien.qldsv.converter;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.MonhocHockyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Hocky;
import com.quanlydiemsinhvien.qldsv.pojo.Monhoc;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.pojo.Phonghoc;
import com.quanlydiemsinhvien.qldsv.repository.MonHocRepository;
import com.quanlydiemsinhvien.qldsv.repository.PhonghocRepository;
import com.quanlydiemsinhvien.qldsv.request.MonHocHocKyCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.KeycloakUserService;

@Component
public class MonHocHocKyConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GiangVienConverter giangVienConverter;

    @Autowired
    private HocKyConverter hocKyConverter;

    @Autowired
    private MonHocConverter monHocConverter;

    @Autowired
    private PhonghocConverter phonghocConverter;

    @Autowired
    private PhonghocRepository phonghocRepository;

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private KeycloakUserService keycloakUserService;

    public MonhocHockyDTO monhocHockyToMonhocHockyDTO(MonhocHocky monhocHocky) {
        MonhocHockyDTO monhocHockyDTO = modelMapper.map(monhocHocky, MonhocHockyDTO.class);
        Map<String, Object> giangvien = keycloakUserService.getUserById(monhocHocky.getIdGiangVien());
        monhocHockyDTO
                .setIdGiangVien(giangVienConverter.mapToGiangVienDTO(giangvien));
        monhocHockyDTO.setIdHocky(hocKyConverter.hocKyToHocKyDTO(monhocHocky.getIdHocky()));
        monhocHockyDTO.setIdMonHoc(monHocConverter.monhocToMonhocDTO(monhocHocky.getIdMonHoc()));
        monhocHockyDTO.setPhongHoc(phonghocConverter.phonghocToPhonghocDTO(monhocHocky.getPhongHoc()));
        return monhocHockyDTO;
    }

    public MonhocHocky monhocHockyCreateRequestToMonHocHocKy(MonHocHocKyCreateRequest request, Hocky hocky) {
        MonhocHocky monhocHocky = modelMapper.map(request, MonhocHocky.class);
        monhocHocky.setIdGiangVien(request.getGiangVien());
        Phonghoc phonghoc = phonghocRepository.findById(request.getPhongHoc()).orElse(null);
        Monhoc monhoc = monHocRepository.findById(request.getIdMonHoc()).orElse(null);
        Map<String, Object> giangvien = keycloakUserService.getUserById(monhocHocky.getIdGiangVien());
        monhocHocky.setIdMonHoc(monhoc);
        monhocHocky.setIdGiangVien((String) giangvien.get("id"));
        monhocHocky.setIdHocky(hocky);
        monhocHocky.setPhongHoc(phonghoc);
        monhocHocky.setSoLuongConLai(request.getSoLuong());
        return monhocHocky;
    }
}