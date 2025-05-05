package com.quanlydiemsinhvien.qldsv.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.GiangVienDTO;
import com.quanlydiemsinhvien.qldsv.request.GiangvienCreateRequest;

import lombok.SneakyThrows;

@Component
public class GiangVienConverter {
    @Autowired
    private ModelMapper modelMapper;

    public GiangVienDTO giangVienCreateRequestToGiangVienDTO(GiangvienCreateRequest request) {
        return modelMapper.map(request, GiangVienDTO.class);
    }

    @SneakyThrows
    public GiangVienDTO mapToGiangVienDTO(Map<String, Object> map) {
        Map<String, Object> attributes = (Map<String, Object>) map.get("attributes");
        ArrayList ngaySinhList = ((ArrayList) attributes.get("ngay_sinh"));
        String ngaySinhStr = ngaySinhList == null || ngaySinhList.isEmpty() ? null : (String) ngaySinhList.get(0);
        return GiangVienDTO.builder()
                .diaChi((String) ((ArrayList) attributes.get("dia_chi")).get(0))
                .email((String) map.get("email"))
                .gioiTinh((String) ((ArrayList) attributes.get("gioi_tinh")).get(0))
                .hoTen((String) ((ArrayList) attributes.get("ho_ten")).get(0))
                .idGiangVien((String) ((ArrayList) attributes.get("ma_so")).get(0))
                .idTaiKhoan((String) map.get("id"))
                .ngaySinh(ngaySinhStr != null ? new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinhStr) : null)
                .soDienThoai((String) ((ArrayList) attributes.get("so_dien_thoai")).get(0))
                .build();
    }
}