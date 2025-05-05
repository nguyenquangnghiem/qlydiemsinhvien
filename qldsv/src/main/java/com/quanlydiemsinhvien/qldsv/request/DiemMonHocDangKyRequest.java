package com.quanlydiemsinhvien.qldsv.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiemMonHocDangKyRequest {
    private Integer idMonHocDangKy;
    private Double diemKT1;
    private Double diemKT2;
    private Double diemKT3;
    private Double diemGK;
    private Double diemCK;
    private Double diemTB;
}
