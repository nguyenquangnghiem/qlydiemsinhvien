
package com.quanlydiemsinhvien.qldsv.pojo;

import java.util.Comparator;

public class DiemMonHocComparator implements Comparator<DiemMonHoc> {
    @Override
    public int compare(DiemMonHoc monHoc1, DiemMonHoc monHoc2) {
        // So sánh theo thuộc tính hocKySinhVien
        return Integer.compare(monHoc1.getHocKySinhVien(), monHoc2.getHocKySinhVien());
    }
}