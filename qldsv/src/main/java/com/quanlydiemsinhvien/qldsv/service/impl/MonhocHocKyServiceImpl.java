package com.quanlydiemsinhvien.qldsv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quanlydiemsinhvien.qldsv.converter.MonHocHocKyConverter;
import com.quanlydiemsinhvien.qldsv.pojo.Hocky;
import com.quanlydiemsinhvien.qldsv.pojo.Monhoc;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.repository.HocKyRepository;
import com.quanlydiemsinhvien.qldsv.repository.MonHocRepository;
import com.quanlydiemsinhvien.qldsv.repository.MonhocHockyRepository;
import com.quanlydiemsinhvien.qldsv.request.MonHocHocKyCreateRequest;
import com.quanlydiemsinhvien.qldsv.service.MonhocHocKyService;

@Service
@Transactional
public class MonhocHocKyServiceImpl implements MonhocHocKyService {

    private final MonHocRepository monHocRepository;

    @Autowired
    private MonhocHockyRepository monHocHockyRepository;

    @Autowired
    private MonHocHocKyConverter monHocHocKyConverter;

    @Autowired
    private HocKyRepository hocKyRepository;

    MonhocHocKyServiceImpl(MonHocRepository monHocRepository) {
        this.monHocRepository = monHocRepository;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            monHocHockyRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void dangKyMonHocAdmin(Integer idHK, List<MonHocHocKyCreateRequest> requests) {
        try {
            Hocky hocky = hocKyRepository.findById(idHK).orElse(null);
            List<MonhocHocky> monhocHockys = requests.stream().filter(it -> it.getNgayBatDau() != null).collect(Collectors.toList()).stream().map(it -> monHocHocKyConverter.monhocHockyCreateRequestToMonHocHocKy(it, hocky)).collect(Collectors.toList());
            List<MonhocHocky> monhocHockyDBLoad = monhocHockys.stream().map(it -> {
                MonhocHocky mhhk = monHocHockyRepository.findByIdMonHoc_IdMonHocAndIdHocky_IdHocKy(it.getIdMonHoc().getIdMonHoc(), it.getIdHocky().getIdHocKy());
                it.setIdMonHocHocKy(mhhk == null? null : mhhk.getIdMonHocHocKy());
                return it;
            }).collect(Collectors.toList());
            monHocHockyRepository.saveAll(monhocHockyDBLoad);
            List<MonHocHocKyCreateRequest> monhocHockyDeleteList = requests.stream().filter(it -> it.getNgayBatDau() == null).collect(Collectors.toList());
            for(MonHocHocKyCreateRequest mhhk : monhocHockyDeleteList){
                Monhoc monhoc = monHocRepository.findById(mhhk.getIdMonHoc()).orElse(null);
                MonhocHocky mhhkdb = monHocHockyRepository.findByIdMonHocAndIdHocky_IdHocKy(monhoc, idHK);
                if(mhhkdb != null){
                    monHocHockyRepository.delete(mhhkdb);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
