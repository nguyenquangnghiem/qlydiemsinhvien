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
@Table(name = "nganhdaotao")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Nganhdaotao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNganhDaoTao;

    @Column(nullable = false, length = 100)
    private String tenNganhDaoTao;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Khoadaotao idKhoaDaoTao;

    @OneToMany(mappedBy = "idNganh", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lophoc> lophocSet;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNganhDaoTao != null ? idNganhDaoTao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nganhdaotao)) {
            return false;
        }
        Nganhdaotao other = (Nganhdaotao) object;
        if ((this.idNganhDaoTao == null && other.idNganhDaoTao != null) || (this.idNganhDaoTao != null && !this.idNganhDaoTao.equals(other.idNganhDaoTao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.quanlydiemsinhvien.qldsv.pojo.Nganhdaotao[ idNganhDaoTao=" + idNganhDaoTao + " ]";
    }
}
