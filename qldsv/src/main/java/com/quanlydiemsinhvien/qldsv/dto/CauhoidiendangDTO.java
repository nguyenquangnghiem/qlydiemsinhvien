package com.quanlydiemsinhvien.qldsv.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CauhoidiendangDTO {
    private Integer idCauHoiDienDan;
    private String noiDungCauHoi;
    private String ngayTao;
    private Map<String, Object> taiKhoan;
}