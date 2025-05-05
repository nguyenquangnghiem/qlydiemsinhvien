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
@Table(name = "phonghoc")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Phonghoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPhongHoc;

    @Column(nullable = false, length = 45)
    private String tenPhongHoc;

    @OneToMany(mappedBy = "phongHoc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MonhocHocky> monhocHockySet;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhongHoc != null ? idPhongHoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phonghoc)) {
            return false;
        }
        Phonghoc other = (Phonghoc) object;
        if ((this.idPhongHoc == null && other.idPhongHoc != null) || (this.idPhongHoc != null && !this.idPhongHoc.equals(other.idPhongHoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.quanlydiemsinhvien.qldsv.pojo.Phonghoc[ idPhongHoc=" + idPhongHoc + " ]";
    }
}
