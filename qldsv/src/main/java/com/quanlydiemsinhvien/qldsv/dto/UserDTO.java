package com.quanlydiemsinhvien.qldsv.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String hoTen;
    private Date ngaySinh;
    private String diaChi;
    private String gioiTinh;
    private String soDienThoai;
    private String email;
    private String idTaiKhoan;
}