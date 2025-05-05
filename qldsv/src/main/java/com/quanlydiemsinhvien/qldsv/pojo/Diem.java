/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "diem")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDiem;

    @Column(nullable = false)
    private double soDiem;

    @Column(nullable = false)
    private short khoaDiem;

    @JoinColumn(referencedColumnName = "idMonHocDangKy", nullable = false)
    @ManyToOne
    private Monhocdangky idMonHoc;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Loaidiem tenDiem;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiem != null ? idDiem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diem)) {
            return false;
        }
        Diem other = (Diem) object;
        if ((this.idDiem == null && other.idDiem != null) || (this.idDiem != null && !this.idDiem.equals(other.idDiem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.quanlydiemsinhvien.qldsv.pojo.Diem[ idDiem=" + idDiem + " ]";
    }
}
