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
@Table(name = "hocky")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hocky implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHocKy;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Loaihocky tenHocKy;

    @Column(nullable = false)
    private Date ngayBatDau;

    @Column(nullable = false)
    private Date ngayKetThuc;

    @Column(nullable = false)
    private Date ngayDangKy;

    @Column(nullable = false)
    private Date ngayHetHan;

    @OneToMany(mappedBy = "idHocky", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MonhocHocky> monhocHockySet;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Lophoc idLop;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHocKy != null ? idHocKy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hocky)) {
            return false;
        }
        Hocky other = (Hocky) object;
        if ((this.idHocKy == null && other.idHocKy != null) || (this.idHocKy != null && !this.idHocKy.equals(other.idHocKy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.quanlydiemsinhvien.qldsv.pojo.Hocky[ idHocKy=" + idHocKy + " ]";
    }
}
