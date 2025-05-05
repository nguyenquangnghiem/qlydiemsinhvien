/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlydiemsinhvien.qldsv.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.quanlydiemsinhvien.qldsv.converter.MonHocDangKyConverter;
import com.quanlydiemsinhvien.qldsv.dto.MonhocdangkyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Diem;
import com.quanlydiemsinhvien.qldsv.pojo.DiemMonHoc;
import com.quanlydiemsinhvien.qldsv.pojo.DiemMonHocComparator;
import com.quanlydiemsinhvien.qldsv.pojo.Hocky;
import com.quanlydiemsinhvien.qldsv.pojo.Loaidiem;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.pojo.Monhocdangky;
import com.quanlydiemsinhvien.qldsv.repository.DiemRepository;
import com.quanlydiemsinhvien.qldsv.repository.LoaidiemRepository;
import com.quanlydiemsinhvien.qldsv.repository.MonHocRepository;
import com.quanlydiemsinhvien.qldsv.repository.MonhocHockyRepository;
import com.quanlydiemsinhvien.qldsv.repository.MonhocdangkyRepository;
import com.quanlydiemsinhvien.qldsv.request.DiemMonHocDangKyRequest;
import com.quanlydiemsinhvien.qldsv.service.DiemService;
import com.quanlydiemsinhvien.qldsv.service.KeycloakUserService;

@Service
@Transactional
public class DiemServiceImpl implements DiemService {

    @Autowired
    private DiemRepository diemRepository;
    @Autowired
    private MonHocRepository monHocRepository;
    @Autowired
    private MonhocdangkyRepository monHocDangKyRepository;
    @Autowired
    private LoaidiemRepository loaidiemRepository;
    @Autowired
    private MonhocHockyRepository monHocHockyRepository;

    @Autowired
    private MonHocDangKyConverter monHocDangKyConverter;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @Override
    public List<Object> getDiemTrungBinhHaiHe(Map<String, String> params) {
        String sinhvienId = params.get("SinhVienId");
        List<Monhocdangky> monhocdangkyList = monHocDangKyRepository.findByIdSinhVien(sinhvienId);
        Set<Integer> idMonHocHocKy = new HashSet<>();
        for (Monhocdangky monhocdangky : monhocdangkyList) {
            idMonHocHocKy.add(monhocdangky.getIdMonHoc().getIdMonHocHocKy());
        }
        List<MonhocHocky> monhocHockyList = monHocHockyRepository.findByIdMonHocHocKyIn(new ArrayList<>(idMonHocHocKy));
        Loaidiem loaidiem = loaidiemRepository.findByTenDiem("Điểm Trung Bình")
                .orElseThrow(() -> new RuntimeException("Không tồn tại loại điểm này!"));
        List<Diem> diemList = diemRepository.findByIdMonHocInAndTenDiem(monhocdangkyList, loaidiem);
        List<Object> result = getObjects(monhocHockyList, diemList);

        return result;
    }

    @NotNull
    private static List<Object> getObjects(List<MonhocHocky> monhocHockyList, List<Diem> diemList) {
        List<Object> result = new ArrayList<>();
        Set<Hocky> hockyList = new HashSet<>();
        for (MonhocHocky monhocHocky : monhocHockyList) {
            hockyList.add(monhocHocky.getIdHocky());
        }
        int mauSo = 0;
        for (Hocky hocky : hockyList) {
            Double diemTrungBinh = 0.0;
            Double diemTrungBinhHe4 = 0.0;
            for (MonhocHocky monhocHocky : monhocHockyList) {

                if (monhocHocky.getIdHocky().equals(hocky)) {

                    for (Diem diem : diemList) {
                        if (monhocHocky.getIdMonHocHocKy()
                                .equals(diem.getIdMonHoc().getIdMonHoc().getIdMonHocHocKy())) {
                            diemTrungBinh += diem.getSoDiem();
                            mauSo += 1;
                        }
                    }
                }

            }
            diemTrungBinh /= (mauSo == 0 ? 1 : mauSo);
            diemTrungBinhHe4 = diemTrungBinh * 0.4;
            mauSo = 0;
            Object[] row = new Object[] { hocky.getTenHocKy().getTenHocKy(), diemTrungBinh, diemTrungBinhHe4 };
            result.add(row);
        }
        return result;
    }

    @Override
    public void editDiem(List<DiemMonHocDangKyRequest> request) {
        Loaidiem kt1LoaiDiem = loaidiemRepository.findByTenDiem("Điểm KT1")
                .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
        Loaidiem kt2LoaiDiem = loaidiemRepository.findByTenDiem("Điểm KT2")
                .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
        Loaidiem kt3LoaiDiem = loaidiemRepository.findByTenDiem("Điểm KT3")
                .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
        Loaidiem gkLoaiDiem = loaidiemRepository.findByTenDiem("Điểm Giữa Kỳ")
                .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
        Loaidiem ckLoaiDiem = loaidiemRepository.findByTenDiem("Điểm Cuối Kỳ")
                .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
        Loaidiem tbLoaiDiem = loaidiemRepository.findByTenDiem("Điểm Trung Bình")
                .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
        for (DiemMonHocDangKyRequest req : request) {
            Monhocdangky monhocdangky = monHocDangKyRepository.findById(req.getIdMonHocDangKy())
                    .orElseThrow(() -> new RuntimeException("Môn học đăng ký không tồn tại!"));
            if (req.getDiemKT1() != null) {
                Diem diem = diemRepository.findByIdMonHocAndTenDiem(
                        monhocdangky,
                        kt1LoaiDiem).orElse(null);
                if (diem == null) {
                    diemRepository.save(Diem.builder().tenDiem(kt1LoaiDiem).khoaDiem((short) 0).idMonHoc(monhocdangky)
                            .soDiem(req.getDiemKT1()).build());
                } else {
                    diem.setSoDiem(req.getDiemKT1());
                    diemRepository.save(diem);
                }
            }
            if (req.getDiemKT2() != null) {
                Diem diem = diemRepository.findByIdMonHocAndTenDiem(
                        monhocdangky,
                        kt2LoaiDiem).orElse(null);
                if (diem == null) {
                    diemRepository.save(Diem.builder().tenDiem(kt2LoaiDiem).khoaDiem((short) 0).idMonHoc(monhocdangky)
                            .soDiem(req.getDiemKT2()).build());
                } else {
                    diem.setSoDiem(req.getDiemKT2());
                    diemRepository.save(diem);
                }
            }
            if (req.getDiemKT3() != null) {
                Diem diem = diemRepository.findByIdMonHocAndTenDiem(
                        monhocdangky,
                        kt3LoaiDiem).orElse(null);
                if (diem == null) {
                    diemRepository.save(Diem.builder().tenDiem(kt3LoaiDiem).khoaDiem((short) 0).idMonHoc(monhocdangky)
                            .soDiem(req.getDiemKT3()).build());
                } else {
                    diem.setSoDiem(req.getDiemKT3());
                    diemRepository.save(diem);
                }
            }
            if (req.getDiemGK() != null) {
                Diem diem = diemRepository.findByIdMonHocAndTenDiem(
                        monhocdangky,
                        gkLoaiDiem).orElse(null);
                if (diem == null) {
                    diemRepository.save(Diem.builder().tenDiem(gkLoaiDiem).khoaDiem((short) 0).idMonHoc(monhocdangky)
                            .soDiem(req.getDiemGK()).build());
                } else {
                    diem.setSoDiem(req.getDiemGK());
                    diemRepository.save(diem);
                }
            }
            if (req.getDiemCK() != null) {
                Diem diem = diemRepository.findByIdMonHocAndTenDiem(
                        monhocdangky,
                        ckLoaiDiem).orElse(null);
                if (diem == null) {
                    diemRepository.save(Diem.builder().tenDiem(ckLoaiDiem).khoaDiem((short) 0).idMonHoc(monhocdangky)
                            .soDiem(req.getDiemCK()).build());
                } else {
                    diem.setSoDiem(req.getDiemCK());
                    diemRepository.save(diem);
                }
            }
            if (req.getDiemTB() != null) {
                Diem diem = diemRepository.findByIdMonHocAndTenDiem(
                        monhocdangky,
                        tbLoaiDiem).orElse(null);
                if (diem == null) {
                    diemRepository.save(Diem.builder().tenDiem(tbLoaiDiem).khoaDiem((short) 0).idMonHoc(monhocdangky)
                            .soDiem(req.getDiemTB()).build());
                } else {
                    diem.setSoDiem(req.getDiemTB());
                    diemRepository.save(diem);
                }
            }
        }
    }
    //

    @Override
    public double getDiemTrungBinh(Map<String, String> params) {
        String sinhvienId = params.get("SinhVienId");
        List<Monhocdangky> monhocdangkyList = monHocDangKyRepository.findByIdSinhVien(sinhvienId);
        Loaidiem loaidiem = loaidiemRepository.findByTenDiem("Điểm Trung Bình")
                .orElseThrow(() -> new RuntimeException("Không tồn tại loại điểm này!"));
        List<Diem> diemList = diemRepository.findByIdMonHocInAndTenDiem(monhocdangkyList, loaidiem);
        Double diemTrungBinh = 0.0;
        for (Diem diem : diemList) {
            diemTrungBinh += diem.getSoDiem();
        }
        diemTrungBinh /= diemList.size();
        return diemTrungBinh;
    }

    //

    @Override
    public List<DiemMonHoc> getListDiemDangHoc(Map<String, String> params) {
        String sinhVienId = params.get("SinhVienId");
        List<Monhocdangky> monHocList = monHocDangKyRepository.findByIdSinhVien(sinhVienId);
        List<DiemMonHoc> monHocDiemList = new ArrayList<>();

        for (Monhocdangky monHoc : monHocList) {
            if (monHoc.getKhoaMon() == 0) {
                DiemMonHoc monHocDiem = DiemMonHoc
                        .fromMonHocDangKy(monHocDangKyConverter.monhocdangkyToMonhocdangkyDTO(monHoc));
                List<Diem> diemList = diemRepository.findByIdMonHoc(monHoc);
                for (Diem diem : diemList) {
                    monHocDiem.addDiem(diem);
                }
                monHocDiemList.add(monHocDiem);
            }
        }
        try {
            monHocDiemList.sort(new DiemMonHocComparator());
            return monHocDiemList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<DiemMonHoc> getListDiemDaHoc(Map<String, String> params) {
        String sinhVienId = params.get("SinhVienId");
        List<Monhocdangky> monHocList = monHocDangKyRepository.findByIdSinhVien(sinhVienId);
        List<DiemMonHoc> monHocDiemList = new ArrayList<>();

        for (Monhocdangky monHoc : monHocList) {
            if (monHoc.getKhoaMon() == 1) {
                DiemMonHoc monHocDiem = DiemMonHoc
                        .fromMonHocDangKy(monHocDangKyConverter.monhocdangkyToMonhocdangkyDTO(monHoc));

                // Lấy danh sách điểm của môn học
                List<Diem> diemList = diemRepository.findByIdMonHoc(monHoc);
                for (Diem diem : diemList) {
                    monHocDiem.addDiem(diem);
                }
                monHocDiemList.add(monHocDiem);
            }
        }
        try {
            monHocDiemList.sort(new DiemMonHocComparator());
            return monHocDiemList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public DiemMonHoc addDiem(Map<String, String> params) {
        int idMonHocDangKy = Integer.parseInt(params.get("idMonHocDangKy"));
        Double DiemGK = Double.parseDouble(params.get("DiemGK"));
        Double DiemCK = Double.parseDouble(params.get("DiemCK"));
        String KT1 = params.get("DiemKT1");
        String KT2 = params.get("DiemKT2");
        String KT3 = params.get("DiemKT3");
        Double DiemKT1 = -1.0, DiemKT2 = -1.0, DiemKT3 = -1.0;
        if (!KT1.isEmpty()) {
            DiemKT1 = Double.parseDouble(KT1);
        }
        if (!KT2.isEmpty()) {
            DiemKT2 = Double.parseDouble(KT2);
        }
        if (!KT3.isEmpty()) {
            DiemKT3 = Double.parseDouble(KT3);
        }
        DiemMonHoc diem1 = new DiemMonHoc(idMonHocDangKy, DiemGK, DiemCK, DiemKT1, DiemKT2, DiemKT3);
        Monhocdangky monhoc = monHocDangKyRepository.findById(idMonHocDangKy)
                .orElseThrow(() -> new RuntimeException("Môn học đăng kí không tồn tại!"));
        List<Diem> diem = this.diemRepository.findByIdMonHoc(monhoc);
        if (monhoc.getKhoaMon() == 0) {
            Double diemTB = 0.0;
            Double heSoGK = 0.5;
            Double diemKT = 0.0;
            String xepLoai;
            Short trangThai;

            if (diem1.getDiemKT1() >= 0) {
                heSoGK -= 0.1;
                diemKT = diemKT + diem1.getDiemKT1();
            }
            if (diem1.getDiemKT2() >= 0) {
                heSoGK -= 0.1;
                diemKT = diemKT + diem1.getDiemKT2();
            }
            if (diem1.getDiemKT3() >= 0) {
                heSoGK -= 0.1;
                diemKT = diemKT + diem1.getDiemKT3();
            }
            diemTB = (diem1.getDiemGK() * heSoGK) + (diemKT * 0.1) + (diem1.getDiemCK() * 0.5);
            // Tính trạng thái dựa trên điểm trung bình
            DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
            diemTB = Double.valueOf(decimalFormat.format(diemTB));
            if (diemTB >= 5) {
                trangThai = 1;
            } else {
                trangThai = 0;
            }
            if (diemTB >= 9) {
                xepLoai = "Giỏi";
            } else if (diemTB >= 6.5) {
                xepLoai = "Khá";
            } else if (diemTB >= 5) {
                xepLoai = "Trung Bình";
            } else {
                xepLoai = "Yếu";
            }
            for (Diem diemm : diem) {
                if (diemm.getTenDiem().getIdLoaiDiem() == 6) {
                    diemm.setSoDiem(diemTB);
                } else if (diemm.getTenDiem().getIdLoaiDiem() == 1) {
                    diemm.setSoDiem(diem1.getDiemGK());
                } else if (diemm.getTenDiem().getIdLoaiDiem() == 2) {
                    diemm.setSoDiem(diem1.getDiemCK());
                }
            }
            monhoc.setTrangThai(trangThai);
            monhoc.setXepLoai(xepLoai);
            addDiem(monhoc, diem1, diem);
            return diem1;
        }
        return null;
    }

    @Override
    public String setDiemByCSV(Map<String, String> params, MultipartFile file) {

        if (!file.isEmpty()) {
            String idMonHoc = params.get("idMonHoc");
            try (CSVParser parser = CSVParser.parse(file.getInputStream(), StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
                for (CSVRecord record : parser) {
                    String idSinhVien = record.get("idSinhVien");
                    String diemGiuaKy = record.get("diemGiuaKy");
                    String diemCuoiKy = record.get("diemCuoiKy");
                    String diemKT1 = record.get("diemTK1");
                    String diemKT2 = record.get("diemTK2");
                    String diemKT3 = record.get("diemTK3");
                    Double DiemTK1 = Double.parseDouble(diemKT1);
                    Double DiemTK2 = Double.parseDouble(diemKT2);
                    Double DiemTK3 = Double.parseDouble(diemKT3);
                    if (diemKT1 == null) {
                        DiemTK1 = -1.0;
                    }
                    if (diemKT2 == null) {
                        DiemTK2 = -1.0;
                    }
                    if (diemKT3 == null) {
                        DiemTK3 = -1.0;
                    }

                    MonhocHocky monhocHocky = monHocHockyRepository.findById(Integer.parseInt(idMonHoc))
                            .orElseThrow(() -> new RuntimeException("Môn học học kỳ không tồn tại!"));
                    Monhocdangky monHoc = monHocDangKyRepository.findByIdSinhVienAndIdMonHoc(idSinhVien, monhocHocky)
                            .orElseThrow(() -> new RuntimeException("Môn học đăng kí không tồn tại!"));
                    if (monHoc != null && monHoc.getKhoaMon() == 0) {
                        DiemMonHoc diem1 = new DiemMonHoc(monHoc.getIdMonHocDangKy(), Double.parseDouble(diemGiuaKy),
                                Double.parseDouble(diemCuoiKy), DiemTK1, DiemTK2, DiemTK3);
                        List<Diem> diem = this.diemRepository.findByIdMonHoc(monHoc);
                        Double diemTB = 0.0;
                        String xepLoai;
                        Short trangThai;
                        if (diem1.getDiemKT1() == -1) {
                            diemTB = (diem1.getDiemGK() + diem1.getDiemCK()) * 0.5;
                        } else if (diem1.getDiemKT2() == -1) {
                            diemTB = (diem1.getDiemGK() * 0.4 + diem1.getDiemKT1() * 0.1 + diem1.getDiemCK() * 0.5);
                        } else if (diem1.getDiemKT3() == -1) {
                            diemTB = (diem1.getDiemGK() * 0.3 + diem1.getDiemKT1() * 0.1 + diem1.getDiemKT2() * 0.1
                                    + diem1.getDiemCK() * 0.5);
                        } else {
                            diemTB = (diem1.getDiemGK() * 0.2
                                    + (diem1.getDiemKT1() + diem1.getDiemKT2() + diem1.getDiemKT3()) * 0.1
                                    + diem1.getDiemCK() * 0.5);
                        }
                        // Tính trạng thái dựa trên điểm trung bình
                        DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
                        diemTB = Double.valueOf(decimalFormat.format(diemTB));
                        if (diemTB >= 5) {
                            trangThai = 1;
                        } else {
                            trangThai = 0;
                        }
                        if (diemTB >= 9) {
                            xepLoai = "Giỏi";
                        } else if (diemTB >= 6.5) {
                            xepLoai = "Khá";
                        } else if (diemTB >= 5) {
                            xepLoai = "Trung Bình";
                        } else {
                            xepLoai = "Yếu";
                        }
                        for (Diem diemm : diem) {
                            switch (diemm.getTenDiem().getTenDiem()) {
                                case "Điểm Trung Bình":
                                    diemm.setSoDiem(diemTB);
                                    break;
                                case "Điểm Giữa Kỳ":
                                    diemm.setSoDiem(diem1.getDiemGK());
                                    break;
                                case "Điểm Cuối Kỳ":
                                    diemm.setSoDiem(diem1.getDiemCK());
                                    break;
                            }
                        }
                        monHoc.setTrangThai(trangThai);
                        monHoc.setXepLoai(xepLoai);
                        addDiem(monHoc, diem1, diem);
                    }
                }
                return "oke";
            } catch (IOException ex) {
                Logger.getLogger(DiemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return "oke";
    }

    private void addDiem(Monhocdangky monHoc, DiemMonHoc diem1, List<Diem> diem) {

        Loaidiem loaiDiemKt1 = loaidiemRepository.findByTenDiem("Điểm KT1")
                .orElseThrow(() -> new RuntimeException("Không tồn tại loại điểm này"));
        Loaidiem loaiDiemKt2 = loaidiemRepository.findByTenDiem("Điểm KT2")
                .orElseThrow(() -> new RuntimeException("Không tồn tại loại điểm này"));
        Loaidiem loaiDiemKt3 = loaidiemRepository.findByTenDiem("Điểm KT3")
                .orElseThrow(() -> new RuntimeException("Không tồn tại loại điểm này"));
        short khoaDiem = 0;
        diemRepository.deleteByTenDiemIn(Arrays.asList(loaiDiemKt1, loaiDiemKt3, loaiDiemKt2));
        diem.removeIf(diemm -> diemm.getTenDiem().getTenDiem().equals("Điểm KT1")
                || diemm.getTenDiem().getTenDiem().equals("Điểm KT2")
                || diemm.getTenDiem().getTenDiem().equals("Điểm KT3"));

        List<Diem> diemList = new ArrayList<>();
        if (diem1.getDiemKT1() != -1) {
            diemList.add(Diem.builder().tenDiem(loaiDiemKt1).soDiem(diem1.getDiemKT1()).idMonHoc(monHoc)
                    .khoaDiem(khoaDiem).build());
        }
        if (diem1.getDiemKT2() != -1) {
            diemList.add(Diem.builder().tenDiem(loaiDiemKt2).soDiem(diem1.getDiemKT2()).idMonHoc(monHoc)
                    .khoaDiem(khoaDiem).build());
        }
        if (diem1.getDiemKT3() != -1) {
            diemList.add(Diem.builder().tenDiem(loaiDiemKt3).soDiem(diem1.getDiemKT3()).idMonHoc(monHoc)
                    .khoaDiem(khoaDiem).build());
        }
        monHocDangKyRepository.save(monHoc);
        diemRepository.saveAll(diemList);
    }

    @Override
    public DiemMonHoc getDiemByIdDiem(Map<String, String> params) {
        String idMonHocDangKy = params.get("idMonHocDangKy");
        Monhocdangky monhocdangky = monHocDangKyRepository.findById(Integer.parseInt(idMonHocDangKy))
                .orElseThrow(() -> new RuntimeException("Môn học đăng ký không tồn tại!"));
        DiemMonHoc monHocDiem = DiemMonHoc
                .fromMonHocDangKy(monHocDangKyConverter.monhocdangkyToMonhocdangkyDTO(monhocdangky));
        return monHocDiem;
    }

    @Override
    public List<MonhocdangkyDTO> getDiemByidGiangVien(Map<String, String> params) {
        String giangVienId = params.get("idGiangVien");
        String tenSinhVien = params.get("tenSinhVien");
        List<Map<String, Object>> sinhvienList = keycloakUserService.getUserByFullNameAndRole(tenSinhVien, "SV");
        Map<String, Object> sinhvien = sinhvienList.isEmpty() ? null : (Map<String, Object>) sinhvienList.getFirst();
        String idSinhvien = sinhvien != null ? (String) sinhvien.get("id") : "";
        List<MonhocHocky> monhocHockyList = monHocHockyRepository.findByIdGiangVien(giangVienId);
        List<Monhocdangky> monhocdangkyList = monHocDangKyRepository.findByIdMonHocInAndIdSinhVien(monhocHockyList,
                idSinhvien);
        return monhocdangkyList.stream().map(it -> monHocDangKyConverter.monhocdangkyToMonhocdangkyDTO(it))
                .collect(Collectors.toList());
    }
    //

    @Override
    public boolean khoaDiem(Map<String, String> params) {
        try {
            String idMonHoc = params.get("idMonHoc");
            MonhocHocky monhocHocky = monHocHockyRepository.findById(Integer.parseInt((idMonHoc)))
                    .orElseThrow(() -> new RuntimeException("Môn học học kỳ không tồn tại!"));
            List<Monhocdangky> monhocdangky = monHocDangKyRepository.findByIdMonHoc(monhocHocky);
            SimpleMailMessage message = new SimpleMailMessage();
            short khoaDiem = 1;
            for (Monhocdangky monhoc : monhocdangky) {
                List<Diem> diemList = diemRepository.findByIdMonHoc(monhoc);
                // Thêm điểm vào danh sách MonHocDiem
                for (Diem diem : diemList) {
                    // Diem.getDiem() là phương thức lấy giá trị điểm
                    diem.setKhoaDiem(khoaDiem);
                    diemRepository.save(diem);
                }
                monhoc.setKhoaMon(khoaDiem);
                monHocDangKyRepository.save(monhoc);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean dangKyMonHoc(Map<String, String> params) {
        int IdMonHoc = Integer.parseInt(params.get("IdMonHoc"));
        String IdSinhVien = params.get("IdSinhVien");
        MonhocHocky monHocHocKy = monHocHockyRepository.findById(IdMonHoc).orElse(null);
        Monhocdangky monHocDK = monHocDangKyRepository.findByIdSinhVienAndIdMonHoc(IdSinhVien, monHocHocKy)
                .orElse(null);
        if (monHocHocKy == null || monHocHocKy.getSoLuongConLai() <= 0) {
            return false;
        }
        if (monHocDK != null) {
            return false;
        }
        int soLuong = monHocHocKy.getSoLuongConLai() - 1;
        monHocHocKy.setSoLuongConLai(soLuong);
        Short a = 0;
        monHocDK = new Monhocdangky();
        monHocDK.setKhoaMon(a);
        monHocDK.setTrangThai(a);
        monHocDK.setThanhToan(a);
        monHocDK.setIdMonHoc(monHocHocKy);
        monHocDK.setIdSinhVien(IdSinhVien);
        monHocDangKyRepository.save(monHocDK);
        monHocHocKy.getMonhocdangkySet().add(monHocDK);
        monHocHockyRepository.save(monHocHocKy);
        return true;
    }

    @Override
    public Boolean huyDangKy(Map<String, String> params) {
        int IdMonHoc = Integer.parseInt(params.get("IdMonHoc"));
        String IdSinhVien = params.get("IdSinhVien");
        MonhocHocky monhocHocky = monHocHockyRepository.findById(IdMonHoc).orElse(null);
        Monhocdangky monHocDK = monHocDangKyRepository.findByIdSinhVienAndIdMonHoc(IdSinhVien, monhocHocky)
                .orElse(null);
        if (monhocHocky == null) {
            return false;
        }
        if (monHocDK != null) {
            monhocHocky.setSoLuongConLai(monhocHocky.getSoLuongConLai() + 1);
            monHocHockyRepository.save(monhocHocky);
            monHocDangKyRepository.delete(monHocDK);
            return true;
        }
        return false;
    }

    @Override
    public boolean addCotDiem(Map<String, String> params) {
        try {
            String idMonHoc = params.get("idMonHoc");
            MonhocHocky monhocHocky = monHocHockyRepository.findById(Integer.parseInt(idMonHoc))
                    .orElseThrow(() -> new RuntimeException("Môn học học kỳ không tồn tại!"));
            List<Monhocdangky> monhocdangkyList = monHocDangKyRepository.findByIdMonHoc(monhocHocky);
            Loaidiem LoaiKT1 = loaidiemRepository.findByTenDiem("Điểm KT1")
                    .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
            Loaidiem LoaiKT2 = loaidiemRepository.findByTenDiem("Điểm KT2")
                    .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
            Loaidiem LoaiKT3 = loaidiemRepository.findByTenDiem("Điểm KT3")
                    .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
            for (Monhocdangky monHoc : monhocdangkyList) {
                boolean KT1 = true;
                boolean KT2 = true;
                boolean KT3 = true;
                short a = 0;
                List<Diem> diems = diemRepository.findByIdMonHoc(monHoc);
                for (Diem diem : diems) {
                    if (diem.getTenDiem().getTenDiem().equals(LoaiKT3.getTenDiem())) {
                        KT3 = false;
                        KT2 = false;
                        KT1 = false;
                        return false;
                    } else if (diem.getTenDiem().getTenDiem().equals(LoaiKT2.getTenDiem())) {
                        KT2 = false;
                        KT1 = false;
                    } else if (diem.getTenDiem().getTenDiem().equals(LoaiKT1.getTenDiem())) {
                        KT1 = false;
                    }
                }
                if (KT1) {
                    diemRepository.save(Diem.builder().tenDiem(LoaiKT1).idMonHoc(monHoc).khoaDiem(a).build());
                } else if (KT2) {
                    diemRepository.save(Diem.builder().tenDiem(LoaiKT2).idMonHoc(monHoc).khoaDiem(a).build());
                } else if (KT3) {
                    diemRepository.save(Diem.builder().tenDiem(LoaiKT3).idMonHoc(monHoc).khoaDiem(a).build());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCotDiem(Map<String, String> params) {
        String idMonHoc = params.get("idMonHoc");
        MonhocHocky monhocHocky = monHocHockyRepository.findById(Integer.parseInt(idMonHoc))
                .orElseThrow(() -> new RuntimeException("Môn học học kỳ không tồn tại!"));
        List<Monhocdangky> monhocdangkyList = monHocDangKyRepository.findByIdMonHoc(monhocHocky);
        Loaidiem LoaiKT1 = loaidiemRepository.findByTenDiem("Điểm KT1")
                .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
        Loaidiem LoaiKT2 = loaidiemRepository.findByTenDiem("Điểm KT2")
                .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
        Loaidiem LoaiKT3 = loaidiemRepository.findByTenDiem("Điểm KT3")
                .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
        for (Monhocdangky monHoc : monhocdangkyList) {
            boolean KT1 = false;
            boolean KT2 = false;
            boolean KT3 = false;

            Diem diem1 = null, diem2 = null, diem3 = null;
            List<Diem> diems = diemRepository.findByIdMonHoc(monHoc);
            for (Diem diem : diems) {
                if (diem.getTenDiem().getTenDiem().equals(LoaiKT3.getTenDiem())) {
                    diem3 = diem;
                    KT3 = true;
                } else if (diem.getTenDiem().getTenDiem().equals(LoaiKT2.getTenDiem())) {
                    diem2 = diem;
                    KT2 = true;
                } else if (diem.getTenDiem().getTenDiem().equals(LoaiKT1.getTenDiem())) {
                    diem1 = diem;
                    KT1 = true;
                }
            }
            if (KT3) {
                diemRepository.delete(diem3);
            } else if (KT2) {
                diemRepository.delete(diem2);
            } else if (KT1) {
                diemRepository.delete(diem1);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean setDiemTB(Map<String, String> params) {
        try {
            String idMonHoc = params.get("idMonHoc");
            MonhocHocky monhocHocky = monHocHockyRepository.findById(Integer.parseInt(idMonHoc))
                    .orElseThrow(() -> new RuntimeException("Môn học học kỳ không tồn tại!"));
            List<Monhocdangky> monhocdangkyList = monHocDangKyRepository.findByIdMonHoc(monhocHocky);
            Loaidiem LoaiKT1 = loaidiemRepository.findByTenDiem("Điểm KT1")
                    .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
            Loaidiem LoaiKT2 = loaidiemRepository.findByTenDiem("Điểm KT2")
                    .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
            Loaidiem LoaiKT3 = loaidiemRepository.findByTenDiem("Điểm KT3")
                    .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
            Loaidiem LoaiCK = loaidiemRepository.findByTenDiem("Điểm Cuối Kỳ")
                    .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
            Loaidiem LoaiGK = loaidiemRepository.findByTenDiem("Điểm Giữa Kỳ")
                    .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
            Loaidiem LoaiTB = loaidiemRepository.findByTenDiem("Điểm Trung Bình")
                    .orElseThrow(() -> new RuntimeException("Loại điểm không tồn tại!"));
            for (Monhocdangky monHoc : monhocdangkyList) {
                boolean KT1 = false;
                boolean KT2 = false;
                boolean KT3 = false;
                double DiemTB = 0.0;
                Diem GK = null, CK = null, Kt1 = null, Kt2 = null, Kt3 = null, TB = null;
                List<Diem> diems = diemRepository.findByIdMonHoc(monHoc);
                for (Diem diem : diems) {
                    if (diem.getTenDiem().getTenDiem().equals(LoaiKT3.getTenDiem())) {
                        Kt3 = diem;
                        KT3 = true;
                    } else if (diem.getTenDiem().getTenDiem().equals(LoaiKT2.getTenDiem())) {
                        Kt2 = diem;
                        KT2 = true;
                    } else if (diem.getTenDiem().getTenDiem().equals(LoaiKT1.getTenDiem())) {
                        Kt1 = diem;
                        KT1 = true;
                    } else if (diem.getTenDiem().getTenDiem().equals(LoaiCK.getTenDiem())) {
                        CK = diem;
                    } else if (diem.getTenDiem().getTenDiem().equals(LoaiGK.getTenDiem())) {
                        GK = diem;
                    } else if (diem.getTenDiem().getTenDiem().equals(LoaiTB.getTenDiem())) {
                        TB = diem;
                    }
                }

                if (KT3) {
                    DiemTB = GK.getSoDiem() * 0.2 + (Kt1.getSoDiem() + Kt2.getSoDiem() + Kt3.getSoDiem()) * 0.1
                            + CK.getSoDiem() * 0.5;
                } else if (KT2) {
                    DiemTB = GK.getSoDiem() * 0.3 + (Kt1.getSoDiem() + Kt2.getSoDiem()) * 0.1 + CK.getSoDiem() * 0.5;
                } else if (KT1) {
                    DiemTB = GK.getSoDiem() * 0.4 + (Kt1.getSoDiem()) * 0.1 + CK.getSoDiem() * 0.5;
                } else {
                    DiemTB = GK.getSoDiem() * 0.5 + CK.getSoDiem() * 0.5;
                }
                TB.setSoDiem(DiemTB);
                diemRepository.save(TB);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
