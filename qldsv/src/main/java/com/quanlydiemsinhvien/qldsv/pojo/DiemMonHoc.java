///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package com.quanlydiemsinhvien.qldsv.pojo;

import java.io.Serializable;

import com.quanlydiemsinhvien.qldsv.dto.MonhocdangkyDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

///**
// *
// * @author Admin
// */
@Getter
@Setter
//@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiemMonHoc implements Serializable {

    private MonhocdangkyDTO monHoc;
    private int idMonHocDangKy;
    private String tenMonHoc;
    private Double DiemGK = -1.0;
    private Double DiemCK = -1.0;
    private Double DiemKT1 = -1.0;
    private Double DiemKT2 = -1.0;
    private Double DiemKT3 = -1.0;
    private Double DiemTB = -1.0;
    private short khoaMon;
    private short trangThai;
    private String lopHoc;
    private String xepLoai;
    private String hocKy;
    private int hocKySinhVien;

    public static DiemMonHoc fromMonHocDangKy(MonhocdangkyDTO monHoc) {
        return DiemMonHoc.builder()
                .monHoc(monHoc)
                .lopHoc(monHoc.getIdMonHoc().getIdHocky().getIdLop().getTenLopHoc())
                .khoaMon(monHoc.getKhoaMon())
                .trangThai(monHoc.getTrangThai())
                .xepLoai(monHoc.getXepLoai())
                .idMonHocDangKy(monHoc.getIdMonHocDangKy())
                .hocKySinhVien(monHoc.getIdMonHoc().getIdHocky().getIdHocKy())
                .hocKy(monHoc.getIdMonHoc().getIdHocky().getTenHocKy().getTenHocKy())
                .tenMonHoc(monHoc.getIdMonHoc().getIdMonHoc().getTenMonHoc())
                .build();
    }

    public DiemMonHoc(int idMonHocDangKy, Double DiemGK, Double DiemCK, Double DiemKT1, Double DiemKT2, Double DiemKT3) {
        this.idMonHocDangKy = idMonHocDangKy;
        this.DiemGK = DiemGK;
        this.DiemCK = DiemCK;
        this.DiemKT1 = DiemKT1;
        this.DiemKT2 = DiemKT2;
        this.DiemKT3 = DiemKT3;
    }

    public void addDiem(Diem diem) {
        switch (diem.getTenDiem().getIdLoaiDiem()) {
            case 1:
                this.setDiemGK((Double) diem.getSoDiem());
                break;
            case 2:
                this.setDiemCK((Double) diem.getSoDiem());
                break;
            case 3:
                this.setDiemKT1((Double) diem.getSoDiem());
                break;
            case 4:
                this.setDiemKT2((Double) diem.getSoDiem());
                break;
            case 5:
                this.setDiemKT3((Double) diem.getSoDiem());
                break;
            default:
                this.setDiemTB((Double) diem.getSoDiem());
                break;
        }
    }
}
