package com.quanlydiemsinhvien.qldsv.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiemTrungBinhDTO {
    private String tenHocKy;
    private Double diemTrungBinh;
    private Double diemTrungBinh4;
}
