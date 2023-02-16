package com.wrs.cems.service;



import com.wrs.cems.bean.Emprp;

import java.util.List;

public interface IEmprpService {
    Emprp getEmprpById(Integer id);

    List<Emprp> getAllEmprps();

    List<Emprp> getEmprpAndEmp();

    int deleteEmprpById(Integer id);

    void addEmprp(Emprp emprp);

    int updateEmprp(Emprp emprp);

    List<Emprp> query(String name,String rpWorkId,String rpType);
}
