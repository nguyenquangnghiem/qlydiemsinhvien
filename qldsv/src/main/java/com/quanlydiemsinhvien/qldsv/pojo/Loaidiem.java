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
@Table(name = "loaidiem")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Loaidiem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLoaiDiem;

    @Column(nullable = false, length = 500)
    private String tenDiem;

    @OneToMany(mappedBy = "tenDiem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Diem> diemSet;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoaiDiem != null ? idLoaiDiem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loaidiem)) {
            return false;
        }
        Loaidiem other = (Loaidiem) object;
        if ((this.idLoaiDiem == null && other.idLoaiDiem != null) || (this.idLoaiDiem != null && !this.idLoaiDiem.equals(other.idLoaiDiem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.quanlydiemsinhvien.qldsv.pojo.Loaidiem[ idLoaiDiem=" + idLoaiDiem + " ]";
    }
}
