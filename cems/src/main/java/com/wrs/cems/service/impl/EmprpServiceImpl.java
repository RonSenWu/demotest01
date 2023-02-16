package com.wrs.cems.service.impl;



import com.wrs.cems.bean.Emprp;
import com.wrs.cems.mapper.EmprpMapper;
import com.wrs.cems.service.IEmprpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprpServiceImpl implements IEmprpService {
    @Autowired
    EmprpMapper emprpMapper;

    @Override
    public Emprp getEmprpById(Integer id) {
        return emprpMapper.getEmprpById(id);
    }

    @Override
    public List<Emprp> getAllEmprps() {
        return emprpMapper.getAllEmprps();
    }

    @Override
    public List<Emprp> getEmprpAndEmp() {
        return emprpMapper.getEmprpAndEmp();
    }

    @Override
    public int deleteEmprpById(Integer id) {
        return emprpMapper.deleteEmprpById(id);
    }

    @Override
    public void addEmprp(Emprp emprp) {
         emprpMapper.addEmprp(emprp);
    }

    @Override
    public int updateEmprp(Emprp emprp) {
        return emprpMapper.updateEmprp(emprp);
    }

    @Override
    public List<Emprp> query(String name,String rpWorkId,String rpType) {
        return emprpMapper.query(name,rpWorkId,rpType);
    }
}
