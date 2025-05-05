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
@Table(name = "lophoc")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lophoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLopHoc;

    @Column(nullable = false, length = 50)
    private String tenLopHoc;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Khoa idKhoaHoc;

    @OneToMany(mappedBy = "idLop", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Hocky> hockySet;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Hedaotao idHeDaoTao;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Nganhdaotao idNganh;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLopHoc != null ? idLopHoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lophoc)) {
            return false;
        }
        Lophoc other = (Lophoc) object;
        if ((this.idLopHoc == null && other.idLopHoc != null)
                || (this.idLopHoc != null && !this.idLopHoc.equals(other.idLopHoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.quanlydiemsinhvien.qldsv.pojo.Lophoc[ idLopHoc=" + idLopHoc + " ]";
    }
}
