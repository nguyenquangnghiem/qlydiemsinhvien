/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.pojo;

import java.io.Serializable;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author FPTSHOP
 */
// @Entity
// @Table(name = "taikhoan")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Taikhoan implements Serializable {

    // private static final long serialVersionUID = 1L;

    // @Id
    private String idTaiKhoan;

    // @Column(nullable = false, length = 50)
    // private String tenTaiKhoan;

    // @Column(name = "MatKhau", nullable = false, length = 100)
    // private String matKhau;

    // @Column(length = 1000)
    // private String image;

    // @OneToMany(mappedBy = "idTaiKhoan", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Set<Traloidiendan> traloidiendanSet;

    // // @JoinColumn(name = "ChucVu", nullable = false)
    // // @ManyToOne
    // // private Loaitaikhoan chucVu;

    // @OneToMany(mappedBy = "idTaiKhoan", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Set<Cauhoidiendang> cauhoidiendangSet;

    // @OneToOne(mappedBy = "idTaiKhoan", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Giangvien giangvien;

    // @OneToOne(mappedBy = "idTaiKhoan", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Giaovu giaovu;

    // @OneToOne(mappedBy = "idTaiKhoan", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Sinhvien sinhvien;

    // @Transient
    private String xacNhanMk;

    // @Transient
    private String mkMoi;

    // @Transient
    private Integer maXacNhan;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaiKhoan != null ? idTaiKhoan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taikhoan)) {
            return false;
        }
        Taikhoan other = (Taikhoan) object;
        if ((this.idTaiKhoan == null && other.idTaiKhoan != null) || (this.idTaiKhoan != null && !this.idTaiKhoan.equals(other.idTaiKhoan))) {
            return false;
        }
        return true;
    }
}
