/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.pojo;

import java.io.Serializable;
import java.util.Date;
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
@Entity
@Table(name = "monhoc_hocky")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonhocHocky implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMonHoc_HocKy")
    private Integer idMonHocHocKy;

    @Column(nullable = false)
    private int soLuong;

    private int soLuongConLai;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private String idGiangVien;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Hocky idHocky;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Monhoc idMonHoc;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Phonghoc phongHoc;

    @OneToMany(mappedBy = "idMonHoc", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private Set<Monhocdangky> monhocdangkySet;

    @Transient
    private Monhoc[] listMonhocs;
    @Transient
    private Date[] listDateBatDau;
    @Transient
    private Date[] listDateKetThuc;
    @Transient
    private Integer[] listSoLuong;
    @Transient
    private Phonghoc[] listPhonghocs;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonHocHocKy != null ? idMonHocHocKy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonhocHocky)) {
            return false;
        }
        MonhocHocky other = (MonhocHocky) object;
        if ((this.idMonHocHocKy == null && other.idMonHocHocKy != null)
                || (this.idMonHocHocKy != null && !this.idMonHocHocKy.equals(other.idMonHocHocKy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky[ idMonHocHocKy=" + idMonHocHocKy + " ]";
    }
}
