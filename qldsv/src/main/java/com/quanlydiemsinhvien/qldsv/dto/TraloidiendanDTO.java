package com.quanlydiemsinhvien.qldsv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TraloidiendanDTO {
    private Integer idTraLoiDienDan;
    private String noiDungTraLoi;
    private CauhoidiendangDTO idCauHoiDienDan;
    private String idTaiKhoan;
}