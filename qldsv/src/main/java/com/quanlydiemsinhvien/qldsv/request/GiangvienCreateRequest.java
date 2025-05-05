package com.quanlydiemsinhvien.qldsv.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiangvienCreateRequest extends UserCreateRequest {
    private String idGiangVien;
}