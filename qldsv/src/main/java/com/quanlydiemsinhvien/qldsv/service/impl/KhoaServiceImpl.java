package com.quanlydiemsinhvien.qldsv.service.impl;

import com.quanlydiemsinhvien.qldsv.repository.KhoaRepository;
import com.quanlydiemsinhvien.qldsv.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KhoaServiceImpl implements KhoaService {

    @Autowired
    private KhoaRepository khoaRepository;

    @Override
    public boolean deleteById(Integer id) {
        try{
            khoaRepository.deleteById(id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
