package com.quanlydiemsinhvien.qldsv.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanlydiemsinhvien.qldsv.dto.HocKyDTO;
import com.quanlydiemsinhvien.qldsv.pojo.Hocky;
import com.quanlydiemsinhvien.qldsv.pojo.Loaihocky;
import com.quanlydiemsinhvien.qldsv.pojo.Lophoc;
import com.quanlydiemsinhvien.qldsv.pojo.MonhocHocky;
import com.quanlydiemsinhvien.qldsv.repository.HocKyRepository;
import com.quanlydiemsinhvien.qldsv.repository.LoaihockyRepository;
import com.quanlydiemsinhvien.qldsv.repository.LopHocRepository;
import com.quanlydiemsinhvien.qldsv.request.HocKyCreateRequest;

@Component
public class HocKyConverter {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LopHocConverter lopHocConverter;

    @Autowired
    private LoaiHocKyConverter loaiHocKyConverter; 

    @Autowired
    private HocKyRepository hocKyRepository;

    @Autowired
    private LoaihockyRepository loaihockyRepository;

    @Autowired
    private LopHocRepository lopHocRepository;

    public HocKyDTO hocKyToHocKyDTO(Hocky hocky){
        HocKyDTO hocKyDTO = modelMapper.map(hocky, HocKyDTO.class);
        hocKyDTO.setIdLop(lopHocConverter.lopHocToLopHocDTO(hocky.getIdLop()));
        hocKyDTO.setTenHocKy(loaiHocKyConverter.loaihockyToLoaihockyDTO(hocky.getTenHocKy()));
        return hocKyDTO;
    }

    public Hocky hocKyCreateRequestToHocKy(HocKyCreateRequest request){
        Hocky hocky = request.getIdHocKy() == null? null : hocKyRepository.findById(request.getIdHocKy()).orElse(null);
        Set<MonhocHocky> monhocHockyList = new HashSet<>();
        if(hocky != null){
            monhocHockyList = hocky.getMonhocHockySet();
        }
        Lophoc lophoc = lopHocRepository.findById(request.getIdLop()).orElse(null);
        Loaihocky loaihocky = loaihockyRepository.findById(request.getTenHocKy()).orElse(null);
        Hocky newHocKy = modelMapper.map(request, Hocky.class);
        newHocKy.setIdLop(lophoc);
        newHocKy.setMonhocHockySet(monhocHockyList);
        newHocKy.setTenHocKy(loaihocky);
        return newHocKy;
    }
}