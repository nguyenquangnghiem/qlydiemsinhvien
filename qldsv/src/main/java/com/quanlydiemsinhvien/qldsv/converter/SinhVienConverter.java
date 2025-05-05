package com.quanlydiemsinhvien.qldsv.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.SinhVienDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;
import com.quanlydiemsinhvien.qldsv.repository.LopHocRepository;
import com.quanlydiemsinhvien.qldsv.request.SinhVienCreateRequest;

import lombok.SneakyThrows;

@Component
public class SinhVienConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LopHocConverter lopHocConverter;

    @Autowired
    private LopHocRepository lopHocRepository;

    @SneakyThrows
    public SinhVienDTO sinhVienToSinhVienDTO(Map<String, Object> sinhvien) {
        Map<String, Object> attributes = (Map<String, Object>) sinhvien.get("attributes");
        ArrayList ngaySinhList = ((ArrayList) attributes.get("ngay_sinh"));
        String ngaySinhStr = ngaySinhList == null || ngaySinhList.isEmpty() ? null : (String) ngaySinhList.get(0);
        Integer idLophoc = Integer.valueOf(
                (String) (((ArrayList) attributes.get("id_lop_hoc")).get(0)));
        Lophoc lophoc = lopHocRepository.findById(idLophoc).get();
        SinhVienDTO sv = SinhVienDTO.builder()
                .diaChi((String) ((ArrayList) attributes.get("dia_chi")).get(0))
                .email((String) sinhvien.get("email"))
                .gioiTinh((String) ((ArrayList) attributes.get("gioi_tinh")).get(0))
                .hoTen((String) ((ArrayList) attributes.get("ho_ten")).get(0))
                .idTaiKhoan((String) sinhvien.get("id"))
                .idSinhVien((String) ((ArrayList) attributes.get("ma_so")).get(0))
                .ngaySinh(ngaySinhStr != null ? new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinhStr) : null)
                .soDienThoai((String) ((ArrayList) attributes.get("so_dien_thoai")).get(0))
                .maLop(
                        lopHocConverter.lopHocToLopHocDTO(
                                lophoc))
                .build();
        return sv;
    }

    public SinhVienDTO sinhVienRequestToSinhVienDTO(SinhVienCreateRequest request) {
        SinhVienDTO sinhVienDTO = modelMapper.map(request, SinhVienDTO.class);
        sinhVienDTO.setMaLop(
                lopHocConverter.lopHocToLopHocDTO(lopHocRepository.findById(request.getMaLop()).orElse(null)));
        return sinhVienDTO;
    }
}