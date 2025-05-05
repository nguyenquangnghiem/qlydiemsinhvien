package com.quanlydiemsinhvien.qldsv.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SinhVienDTO extends UserDTO {
    private String idSinhVien;
    private LopHocDTO maLop;
}