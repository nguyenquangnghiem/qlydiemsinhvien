package com.quanlydiemsinhvien.qldsv.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SinhVienCreateRequest extends UserCreateRequest {
    private String idSinhVien;
    private Integer maLop;
}