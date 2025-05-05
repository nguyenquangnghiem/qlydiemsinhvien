/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author FPTSHOP
 */
@Entity
@Table(name = "monhocdangky")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Monhocdangky implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMonHocDangKy;

    @Column(length = 20)
    private String xepLoai;

    private Short khoaMon;

    private Short thanhToan;

    private Short trangThai;
    @OneToMany(mappedBy = "idMonHoc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Diem> diemSet;

    @JoinColumn
    @ManyToOne
    private MonhocHocky idMonHoc;

    private String idSinhVien;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonHocDangKy != null ? idMonHocDangKy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monhocdangky)) {
            return false;
        }
        Monhocdangky other = (Monhocdangky) object;
        if ((this.idMonHocDangKy == null && other.idMonHocDangKy != null)
                || (this.idMonHocDangKy != null && !this.idMonHocDangKy.equals(other.idMonHocDangKy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.quanlydiemsinhvien.qldsv.pojo.Monhocdangky[ idMonHocDangKy=" + idMonHocDangKy + " ]";
    }
}
