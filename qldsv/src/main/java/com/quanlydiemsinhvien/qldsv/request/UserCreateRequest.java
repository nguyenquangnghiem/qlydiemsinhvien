package com.quanlydiemsinhvien.qldsv.request;

import java.util.Date;

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
public class UserCreateRequest {
    private String hoTen;
    private Date ngaySinh;
    private String diaChi;
    private String gioiTinh;
    private String soDienThoai;
    private String email;
    private String matKhau;
    private String xacNhanMk;
    private String idTaiKhoan;
}